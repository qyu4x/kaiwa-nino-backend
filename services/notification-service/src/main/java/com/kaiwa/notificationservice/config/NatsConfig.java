package com.kaiwa.notificationservice.config;

import io.nats.client.Connection;
import io.nats.client.Nats;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import java.io.IOException;

@Configuration
public class NatsConfig {

    @Value("${spring.nats.bootstrap-servers:nats://localhost:4222}")
    private String natsBootstrapServer;

    @Bean
    public Connection natsConnection()
            throws IOException, InterruptedException {
        return Nats.connect(natsBootstrapServer);
    }
}
