package com.kaiwa.messageservice.event.notification;

import com.kaiwa.messageservice.event.notification.exchange.MessageNotificationRequest;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.kafka.support.KafkaHeaders;
import org.springframework.messaging.Message;
import org.springframework.messaging.support.MessageBuilder;
import org.springframework.stereotype.Service;

import java.time.Instant;
import java.time.LocalDateTime;

@Slf4j
@Service
@RequiredArgsConstructor
public class NotificationProducer {

    private final KafkaTemplate<String, MessageNotificationRequest> kafkaTemplate;

    private static final String KAFKA_TOPIC = "message-topic";

    public void sendMessageNotification(MessageNotificationRequest messageNotification) {
        log.info("Data sent to Kafka topic: [{}], with data: [{}], at timestamp: [{}]", KAFKA_TOPIC, messageNotification, LocalDateTime.now());
        messageNotification.setSentFromBrokerAt(Instant.now().toEpochMilli());
        Message<MessageNotificationRequest> message = MessageBuilder
                .withPayload(messageNotification)
                .setHeader(KafkaHeaders.TOPIC, KAFKA_TOPIC)
                .build();

        kafkaTemplate.send(message);
    }

}
