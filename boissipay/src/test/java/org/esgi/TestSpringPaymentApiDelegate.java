package org.esgi;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import redis.clients.jedis.JedisPooled;

import static org.assertj.core.api.Assertions.assertThat;

public class TestSpringPaymentApiDelegate {
    private JedisPooled jedisPooled;
    private SpringPaymentApiDelegate springPaymentApiDelegate;
    private Payment payment1;
    private Payment invalidPayment;

    @BeforeEach
    public void setUp() {
        jedisPooled = new JedisPooled("localhost", 6379);
        springPaymentApiDelegate = new SpringPaymentApiDelegate(jedisPooled);

        payment1 = new Payment("1", 2.0, "EUR", "1");
        invalidPayment = new Payment("1", -2.0, "EUR", "2");
    }

    @Test
    public void testPayment() {
       var payementResponse = springPaymentApiDelegate.pay(payment1);
       assertThat(payementResponse).isEqualTo(PaymentResponse.SUCCESS);
       var payment = jedisPooled.spop("payments");
       assertThat(payment).isEqualTo(payment1.toString());
       assertThat(jedisPooled.scard("payments")).isEqualTo(0);
    }

    @Test
    public void testInvalidPayment() {
        var payementResponse = springPaymentApiDelegate.pay(invalidPayment);
        assertThat(payementResponse).isEqualTo(PaymentResponse.ERROR_INVALID_AMOUNT);
        assertThat(jedisPooled.scard("payments")).isEqualTo(0);
    }
    @Test
    public  void testPayTwice() {
        springPaymentApiDelegate.pay(payment1);
        var payementResponse = springPaymentApiDelegate.pay(payment1);
        assertThat(payementResponse).isEqualTo(PaymentResponse.SUCCESS);
        assertThat(jedisPooled.scard("payments")).isEqualTo(1);
        jedisPooled.spop("payments");
    }
}
