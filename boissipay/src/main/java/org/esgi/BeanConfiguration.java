package org.esgi;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import redis.clients.jedis.JedisPooled;

@Configuration
public class BeanConfiguration {

    @Bean
    public JedisPooled jedisPool() {
        return new JedisPooled("localhost", 6379);
    }
}
