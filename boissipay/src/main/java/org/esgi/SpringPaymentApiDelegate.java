package org.esgi;

import redis.clients.jedis.JedisPooled;

public class SpringPaymentApiDelegate implements PaymentApiDelegate {

    private final JedisPooled jedisPool;
    public SpringPaymentApiDelegate(JedisPooled jedisPool) {
        this.jedisPool = jedisPool;
    }

    public PaymentResponse pay(Payment payment) {
        if (payment.amout() < 0) {
            return PaymentResponse.ERROR_INVALID_AMOUNT;
        }
        jedisPool.sadd("payments", payment.toString());
        return PaymentResponse.SUCCESS;
    }
}
