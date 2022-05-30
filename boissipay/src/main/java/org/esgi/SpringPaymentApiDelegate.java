package org.esgi;

import org.esgi.boissipay.api.PaymentApiDelegate;
import org.esgi.boissipay.model.Payment;
import org.esgi.boissipay.model.PaymentOrder;
import org.esgi.boissipay.model.PaymentResponse;
import org.esgi.boissipay.model.PaymentStatus;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import redis.clients.jedis.JedisPooled;

import java.util.List;
import java.util.UUID;

@Service
public class SpringPaymentApiDelegate implements PaymentApiDelegate {

    private final JedisPooled jedisPool;

    public SpringPaymentApiDelegate(JedisPooled jedisPool) {
        this.jedisPool = jedisPool;
    }

    public PaymentResponse pay(Payment payment) {
        var totalAmount = payment.getPaymentOrders().stream().mapToDouble(PaymentOrder::getAmount).sum();
        var response = new PaymentResponse();

        if (totalAmount < 0) {
            response.setPaymentStatus(PaymentStatus.FAILURE);
        } else {
            response.setPaymentStatus(PaymentStatus.SUCCESS);
            if (!jedisPool.hexists("payment", payment.getCheckoutId().toString())) {
                jedisPool.hset("payment",payment.getCheckoutId().toString(), payment.toString());
            }
        }
        return response;
    }

    @Override
    public ResponseEntity<PaymentResponse> createPayment(Payment body) {
        var response = pay(body);
        return new ResponseEntity<>(response,
                response.getPaymentStatus() == PaymentStatus.SUCCESS ?
                        HttpStatus.OK : HttpStatus.BAD_REQUEST
        );
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
