package org.esgi;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.context.properties.ConfigurationPropertiesScan;

@SpringBootApplication
@ConfigurationPropertiesScan
public class PaymentSpringApplication {
    public static void main(String[] args) {
        SpringApplication.run(PaymentSpringApplication.class, args);
    }
}
