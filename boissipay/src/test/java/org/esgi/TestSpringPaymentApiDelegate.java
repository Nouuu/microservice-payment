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
    static final String REDIS_IMAGE = "redis:5.0.3-alpine";
    static final int REDIS_PORT = 6379;

    @Container
    public GenericContainer redis = new GenericContainer(REDIS_IMAGE)
            .withExposedPorts(REDIS_PORT);
    private JedisPooled jedisPooled;
    private SpringPaymentApiDelegate springPaymentApiDelegate;
    private Payment validPayment;
    private Payment invalidPayment;

    private BuyerInfo setupBuyerInfo() {
        var buyerInfo = new BuyerInfo();
        buyerInfo.setId(UUID.randomUUID());
        buyerInfo.setName("John Doe");
        return buyerInfo;
    }

    private PaymentOrder createPaymentOrderWithAmount(double amount) {
        var paymentOrder = new PaymentOrder();
        paymentOrder.setPaymentOrderId(UUID.randomUUID());
        paymentOrder.setAmount(amount);
        paymentOrder.setCurrency("EUR");
        paymentOrder.setSellerAccount("ESGI");
        return paymentOrder;
    }

    private Payment createValidPayment(BuyerInfo buyerInfo, ArrayList<PaymentOrder> validPaymentOrders) {
        var validPayment = new Payment();
        validPayment.setCheckoutId(UUID.randomUUID());
        validPayment.setBuyerInfo(buyerInfo);
        validPayment.setCreditCardInfo("1234");
        validPayment.setPaymentOrders(validPaymentOrders);
        return validPayment;
    }
    private Payment createInvalidPayment(BuyerInfo buyerInfo, ArrayList<PaymentOrder> invalidPaymentOrders) {
        var invalidPayment = new Payment();
        invalidPayment.setCheckoutId(UUID.randomUUID());
        invalidPayment.setBuyerInfo(buyerInfo);
        invalidPayment.setCreditCardInfo("1234");
        invalidPayment.setPaymentOrders(invalidPaymentOrders);
        return invalidPayment;
    }
    private void setupValidPayment(BuyerInfo buyerInfo) {
        var paymentOrder = createPaymentOrderWithAmount(10.0);
        var validPaymentOrders = new ArrayList<PaymentOrder>();
        validPaymentOrders.add(paymentOrder);
        validPayment = createValidPayment(buyerInfo, validPaymentOrders);
    }
    private void setupInvalidPayment(BuyerInfo buyerInfo) {
        var paymentOrder = createPaymentOrderWithAmount(-20.0);
        var invalidPaymentOrders = new ArrayList<PaymentOrder>();
        invalidPaymentOrders.add(paymentOrder);
        invalidPayment = createInvalidPayment(buyerInfo, invalidPaymentOrders);
    }

    @BeforeEach
    public void setUp() {
        jedisPooled = new JedisPooled(redis.getHost(), redis.getFirstMappedPort());
        springPaymentApiDelegate = new SpringPaymentApiDelegate(jedisPooled);

        var buyerInfo = setupBuyerInfo();
        setupValidPayment(buyerInfo);
        setupInvalidPayment(buyerInfo);
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
        assertThat(jedisPooled.hlen("payment")).isEqualTo(1);
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
    }
}
