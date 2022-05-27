package org.esgi;

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
