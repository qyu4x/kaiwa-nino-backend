package com.kaiwa.messageservice.entity;

import com.kaiwa.messageservice.converter.InstantToLongConverter;
import jakarta.persistence.*;
import lombok.*;

import java.time.Instant;

@AllArgsConstructor
@NoArgsConstructor
@Builder
@Getter
@Setter
@Entity
@Table(name = "chats")
public class Chat extends AuditableEntity{

    @Id
    private String id;

    @Column(name = "user_sender_id")
    private String userSenderId;

    @Column(name = "user_recipient_id")
    private String userRecipientId;

    @Lob
    @Column(columnDefinition = "TEXT")
    private String message;

    @Column(name = "read_at")
    @Convert(converter = InstantToLongConverter.class)
    private Instant readAt;

    @Column(name = "is_active")
    private Boolean isActive;
}
