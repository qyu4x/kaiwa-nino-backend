package com.kaiwa.notificationservice.entity;

import com.kaiwa.notificationservice.event.message.exchange.MessageSent;
import com.kaiwa.notificationservice.event.message.exchange.UserRecipient;
import com.kaiwa.notificationservice.event.message.exchange.UserSender;
import lombok.*;
import org.springframework.data.annotation.CreatedDate;
import org.springframework.data.annotation.Id;
import org.springframework.data.annotation.LastModifiedDate;
import org.springframework.data.mongodb.core.mapping.Document;

import java.time.LocalDateTime;

@AllArgsConstructor
@NoArgsConstructor
@Builder
@Getter
@Setter
@Document(collection = "notifications")
public class Notification {

    @Id
    private String id;

    private UserSender sender;

    private UserRecipient recipient;

    private MessageSent message;

    private Long sentFromBrokerAt;

    private Long receivedByConsumerAt;

    private Long createdAt;

    private Long updatedAt;

}
