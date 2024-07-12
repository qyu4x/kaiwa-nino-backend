package com.kaiwa.notificationservice.entity;

import com.kaiwa.notificationservice.client.message.MessageSent;
import com.kaiwa.notificationservice.client.message.UserRecipient;
import com.kaiwa.notificationservice.client.message.UserSender;
import lombok.*;
import org.springframework.data.annotation.CreatedDate;
import org.springframework.data.annotation.Id;
import org.springframework.data.annotation.LastModifiedDate;
import org.springframework.data.mongodb.core.mapping.Document;

import java.time.Instant;
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

    @CreatedDate
    private LocalDateTime createdAt;

    @LastModifiedDate
    private LocalDateTime updatedAt;

}
