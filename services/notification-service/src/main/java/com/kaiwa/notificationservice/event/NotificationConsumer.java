package com.kaiwa.notificationservice.event;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.kaiwa.notificationservice.config.NatsConfig;
import com.kaiwa.notificationservice.entity.Notification;
import com.kaiwa.notificationservice.event.message.exchange.MessageNotification;
import com.kaiwa.notificationservice.helper.JsonConverter;
import com.kaiwa.notificationservice.repository.NotificationRepository;
import io.nats.client.*;
import jakarta.annotation.PostConstruct;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.apache.logging.log4j.util.Strings;
import org.springframework.stereotype.Component;

import java.io.IOException;
import java.nio.charset.StandardCharsets;
import java.time.Instant;
import java.time.LocalDateTime;
import java.util.Arrays;


@Slf4j
@Component
@RequiredArgsConstructor
public class NotificationConsumer {

    private final NatsConfig natsConfig;

    private final JsonConverter jsonConverter;

    private final NotificationRepository notificationRepository;

    private static final String STREAM_DURABLE = "message-durable";

    private static final String STREAM_NAME = "message-stream";

    private static final String NATS_SUBJECT = "notification";

    @PostConstruct
    public void messageNotificationListener() throws IOException, InterruptedException, JetStreamApiException {

        Connection nc = natsConfig.natsConnection();
        JetStream js = nc.jetStream();

        PushSubscribeOptions so = PushSubscribeOptions.builder()
                .durable(STREAM_DURABLE)
                .build();

        MessageHandler handler = (msg) -> {
            Long receivedAtConsumerAt = Instant.now().toEpochMilli();
            log.info(String.format("Consuming the message from message.notification Subject: %s at: %s", new String(msg.getData()), LocalDateTime.now()));
            try {
                MessageNotification messageNotification = jsonConverter.jsonToMessageNotification(new String(msg.getData()));
                notificationRepository.save(Notification.builder()
                        .sender(messageNotification.getUserSender())
                        .recipient(messageNotification.getUserRecipient())
                        .message(messageNotification.getMessage())
                        .sentFromBrokerAt(messageNotification.getSentFromBrokerAt())
                        .receivedByConsumerAt(receivedAtConsumerAt)
                        .createdAt(Instant.now().toEpochMilli())
                        .updatedAt(0L)
                        .build());
            } catch (JsonProcessingException exception) {
                log.error("Error processing message: ", exception);
            }
            msg.ack();
        };

        Dispatcher dispatcher = nc.createDispatcher(handler);
        boolean autoAck = false;

        js.subscribe(Strings.concat(NATS_SUBJECT, ".message"), dispatcher, handler, autoAck, so);
    }

}
