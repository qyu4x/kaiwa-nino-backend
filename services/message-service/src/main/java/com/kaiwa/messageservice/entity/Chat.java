package com.kaiwa.messageservice.entity;

import com.kaiwa.messageservice.converter.InstantToLongConverter;
import jakarta.persistence.*;
import lombok.*;

import java.io.Serializable;
import java.time.Instant;

@AllArgsConstructor
@NoArgsConstructor
@Builder
@Getter
@Setter
@Entity
@ToString
@Table(name = "chats")
public class Chat extends AuditableEntity implements Serializable {

    @Id
    private String id;

    @Column(name = "room_id")
    private String roomId;

    @Column(name = "user_sender_id")
    private String userSenderId;

    @Column(name = "user_recipient_id")
    private String userRecipientId;

    @Lob
    @Column(columnDefinition = "TEXT")
    private String message;

    @Column(name = "read_at")
    private Long readAt;

    @Column(name = "is_active")
    private Boolean isActive;
}
