package org.esgi;

import redis.clients.jedis.JedisPooled;
import java.util.List;
import java.util.UUID;
import org.esgi.boissipay.api.PaymentApiDelegate;
import org.esgi.boissipay.model.Payment;
import org.esgi.boissipay.model.PaymentResponse;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

@Service
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

    @Override
    public ResponseEntity<PaymentResponse> createPayment(Payment body) {
        return new ResponseEntity<>(HttpStatus.OK);
    }

    @Override
    public ResponseEntity<Void> deletePaymentByCheckoutId(UUID checkoutId) {
        return new ResponseEntity<>(HttpStatus.OK);
    }

    @Override
    public ResponseEntity<Payment> getPaymentByCheckoutId(UUID checkoutId) {
        return new ResponseEntity<>(HttpStatus.OK);
    }

    @Override
    public ResponseEntity<List<Payment>> getSucceededPayments() {
        return new ResponseEntity<>(HttpStatus.OK);
    }
}
