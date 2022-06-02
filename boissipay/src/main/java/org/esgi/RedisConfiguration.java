package org.esgi;

import org.springframework.boot.context.properties.ConfigurationProperties;

@ConfigurationProperties("redis")
public class RedisConfiguration {
    private String host;
    private int port;
    private static long redisTll = 60 * 60 * 24;

    public String getHost() {
        return host;
    }

    public void setHost(String host) {
        this.host = host;
    }

    public int getPort() {
        return port;
    }

    public void setPort(int port) {
        this.port = port;
    }

    public static long getRedisTll() {
        return redisTll;
    }

    public static void setRedisTll(long redisTll) {
        RedisConfiguration.redisTll = redisTll;
    }

}
