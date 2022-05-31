package org.esgi;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import redis.clients.jedis.JedisPooled;

@Configuration
public class BeanConfiguration {

    private final RedisConfiguration redisConfiguration;

    public BeanConfiguration(RedisConfiguration redisConfiguration) {
        this.redisConfiguration = redisConfiguration;
    }

    @Bean
    public JedisPooled jedisPool() {
        return new JedisPooled(redisConfiguration.getHost(), redisConfiguration.getPort());
    }
}
