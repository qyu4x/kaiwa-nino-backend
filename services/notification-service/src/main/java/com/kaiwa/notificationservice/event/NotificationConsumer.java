package com.kaiwa.notificationservice.event;

import com.kaiwa.notificationservice.entity.Notification;
import com.kaiwa.notificationservice.event.message.exchange.MessageNotification;
import com.kaiwa.notificationservice.repository.NotificationRepository;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.kafka.annotation.KafkaListener;
import org.springframework.stereotype.Service;

import java.time.Instant;
import java.time.LocalDateTime;


@Slf4j
@Service
@RequiredArgsConstructor
public class NotificationConsumer {

    private final NotificationRepository notificationRepository;

    @KafkaListener(topics = "message-topic")
    public void messageNotificationListener(MessageNotification messageNotification) {
        Long receivedAtConsumerAt = Instant.now().toEpochMilli();
        log.info(String.format("Consuming the message from message-topic Topic: %s at: %s", messageNotification, LocalDateTime.now()));
        notificationRepository.save(Notification.builder()
                        .sender(messageNotification.getUserSender())
                        .recipient(messageNotification.getUserRecipient())
                        .message(messageNotification.getMessage())
                        .sentFromBrokerAt(messageNotification.getSentFromBrokerAt())
                        .receivedByConsumerAt(receivedAtConsumerAt)
                        .createdAt(Instant.now().toEpochMilli())
                        .updatedAt(0L)
                .build());
    }

}
