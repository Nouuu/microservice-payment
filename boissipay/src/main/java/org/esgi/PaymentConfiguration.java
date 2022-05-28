package org.esgi;

import org.springframework.context.annotation.Configuration;
import redis.clients.jedis.JedisPooled;

@Configuration
public class PaymentConfiguration {
    public SpringPaymentApiDelegate springPaymentApiDelegate() {
        return new SpringPaymentApiDelegate(new JedisPooled("localhost", 6379));
    }
}
