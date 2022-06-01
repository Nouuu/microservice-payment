package org.esgi;

import org.esgi.boissipay.model.BuyerInfo;
import org.esgi.boissipay.model.Payment;
import org.esgi.boissipay.model.PaymentOrder;
import org.esgi.boissipay.model.PaymentResponse;
import org.esgi.boissipay.model.PaymentStatus;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.testcontainers.containers.GenericContainer;
import org.testcontainers.junit.jupiter.Container;
import org.testcontainers.junit.jupiter.Testcontainers;
import redis.clients.jedis.JedisPooled;

import java.util.ArrayList;
import java.util.UUID;

import static org.assertj.core.api.Assertions.assertThat;

@Testcontainers
class TestSpringPaymentApiDelegate {

    @Container
    public GenericContainer redis = new GenericContainer("redis:5.0.3-alpine")
            .withExposedPorts(6379);
    private JedisPooled jedisPooled;
    private SpringPaymentApiDelegate springPaymentApiDelegate;
    private Payment validPayment;
    private Payment invalidPayment;

    @BeforeEach
    public void setUp() {
        jedisPooled = new JedisPooled(redis.getHost(), redis.getFirstMappedPort());
        springPaymentApiDelegate = new SpringPaymentApiDelegate(jedisPooled);
        var buyerInfo = new BuyerInfo();
        buyerInfo.setId(UUID.randomUUID());
        buyerInfo.setName("John Doe");

        var paymentOrder1 = new PaymentOrder();
        paymentOrder1.setPaymentOrderId(UUID.randomUUID());
        paymentOrder1.setAmount(10.);
        paymentOrder1.setCurrency("EUR");
        paymentOrder1.setSellerAccount("ESGI");

        var paymentOrder2 = new PaymentOrder();
        paymentOrder2.setPaymentOrderId(UUID.randomUUID());
        paymentOrder2.setAmount(-20.);
        paymentOrder2.setCurrency("EUR");
        paymentOrder2.setSellerAccount("ESGI");

        var validPaymentOrders = new ArrayList<PaymentOrder>();
        validPaymentOrders.add(paymentOrder1);

        var invalidPaymentOrders = new ArrayList<PaymentOrder>();
        invalidPaymentOrders.add(paymentOrder2);

        validPayment = new Payment();
        validPayment.setCheckoutId(UUID.randomUUID());
        validPayment.setBuyerInfo(buyerInfo);
        validPayment.setCreditCardInfo("1234");
        validPayment.setPaymentOrders(validPaymentOrders);

        invalidPayment = new Payment();
        invalidPayment.setCheckoutId(UUID.randomUUID());
        invalidPayment.setBuyerInfo(buyerInfo);
        invalidPayment.setCreditCardInfo("1234");
        invalidPayment.setPaymentOrders(invalidPaymentOrders);
    }

    @Test
    public void testPayment() {
        var expectedResponse = new PaymentResponse();
        expectedResponse.setCheckoutId(validPayment.getCheckoutId());
        expectedResponse.setPaymentStatus(PaymentStatus.SUCCESS);

        var payementResponse = springPaymentApiDelegate.pay(validPayment);
        assertThat(payementResponse).isEqualTo(expectedResponse);
        var payment = jedisPooled.hget("payment", validPayment.getCheckoutId().toString());
        assertThat(payment).isEqualTo(validPayment.toString());
//        assertThat(jedisPooled.scard("payment")).isZero();
    }

    @Test
    public void testInvalidPayment() {
        var expectedResponse = new PaymentResponse();
        expectedResponse.setCheckoutId(invalidPayment.getCheckoutId());
        expectedResponse.setPaymentStatus(PaymentStatus.FAILURE);

        var payementResponse = springPaymentApiDelegate.pay(invalidPayment);
        assertThat(payementResponse).isEqualTo(expectedResponse);
        assertThat(jedisPooled.hlen("payment")).isZero();
    }

    @Test
    public void testPayTwice() {
        var expectedResponse = new PaymentResponse();
        expectedResponse.setCheckoutId(validPayment.getCheckoutId());
        expectedResponse.setPaymentStatus(PaymentStatus.SUCCESS);

        springPaymentApiDelegate.pay(validPayment);
        var payementResponse = springPaymentApiDelegate.pay(validPayment);
        assertThat(payementResponse).isEqualTo(expectedResponse);
        assertThat(jedisPooled.hlen("payment")).isEqualTo(1);
//        jedisPooled.spop("payments");
    }
}
