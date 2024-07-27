package com.kaiwa.messageservice.event.notification;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.kaiwa.messageservice.config.NatsConfig;
import com.kaiwa.messageservice.event.notification.exchange.MessageNotificationRequest;
import com.kaiwa.messageservice.helper.JsonConverter;
import io.nats.client.Connection;
import io.nats.client.JetStream;
import io.nats.client.JetStreamApiException;
import io.nats.client.JetStreamManagement;
import io.nats.client.api.StorageType;
import io.nats.client.api.StreamConfiguration;
import io.nats.client.impl.NatsMessage;
import jakarta.annotation.PostConstruct;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.apache.logging.log4j.util.Strings;
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

    private static final String STREAM_NAME = "message-stream";

    private static final String NATS_SUBJECT = "notification";

    public void sendMessageNotification(MessageNotificationRequest messageNotification)
            throws IOException, InterruptedException, JetStreamApiException {
        Connection nc = natsConfig.natsConnection();

        JetStream js = nc.jetStream();

        nc.jetStreamManagement()
                .addStream(StreamConfiguration.builder()
                        .name(STREAM_NAME)
                        .storageType(StorageType.File)
                        .subjects(NATS_SUBJECT + ".*")
                        .build());

        log.info("Data sent to Nats subject: [{}], with data: [{}], at timestamp: [{}]", NATS_SUBJECT, messageNotification, LocalDateTime.now());
        messageNotification.setSentFromBrokerAt(Instant.now().toEpochMilli());

        js.publish(NATS_SUBJECT + ".message", jsonConverter.messageNotificationRequestToJson(messageNotification).getBytes());
        log.info("Success publish message");
    }

}
