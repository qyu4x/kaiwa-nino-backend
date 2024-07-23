package com.kaiwa.messageservice.event.notification;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.kaiwa.messageservice.config.NatsConfig;
import com.kaiwa.messageservice.event.notification.exchange.MessageNotificationRequest;
import com.kaiwa.messageservice.helper.JsonConverter;
import io.nats.client.Connection;
import io.nats.client.impl.NatsMessage;
import jakarta.annotation.PostConstruct;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import java.io.IOException;
import java.time.Instant;
import java.time.LocalDateTime;

@Slf4j
@Service
@RequiredArgsConstructor
public class NotificationProducer {

    private final JsonConverter jsonConverter;

    private final NatsConfig natsConfig;

    private static final String NATS_SUBJECT = "message-subject";

    public void sendMessageNotification(MessageNotificationRequest messageNotification)
            throws IOException, InterruptedException {
        log.info("Data sent to Nats subject: [{}], with data: [{}], at timestamp: [{}]", NATS_SUBJECT, messageNotification, LocalDateTime.now());
        messageNotification.setSentFromBrokerAt(Instant.now().toEpochMilli());

        natsConfig.natsConnection().publish(NATS_SUBJECT,
                jsonConverter.messageNotificationRequestToJson(messageNotification)
                        .getBytes());
        log.info("Success publish message");
    }

}
