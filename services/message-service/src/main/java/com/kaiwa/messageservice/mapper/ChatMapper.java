package com.kaiwa.messageservice.mapper;

import com.kaiwa.messageservice.client.exchange.user.UserResponse;
import com.kaiwa.messageservice.entity.Chat;
import com.kaiwa.messageservice.event.notification.exchange.MessageNotificationRequest;
import com.kaiwa.messageservice.event.notification.exchange.MessageSentRequest;
import com.kaiwa.messageservice.event.notification.exchange.UserRecipientRequest;
import com.kaiwa.messageservice.event.notification.exchange.UserSenderRequest;
import com.kaiwa.messageservice.payload.request.ChatRequest;
import com.kaiwa.messageservice.payload.response.ChatResponse;
import org.springframework.stereotype.Component;

import java.time.Instant;
import java.util.UUID;

@Component
public class ChatMapper {

    public Chat toChat(ChatRequest chatRequest) {
        return Chat.builder()
                .id(UUID.randomUUID().toString())
                .roomId(null)
                .userSenderId(chatRequest.getUserSenderId())
                .userRecipientId(chatRequest.getUserRecipientId())
                .message(chatRequest.getMessage())
                .readAt(Instant.MIN)
                .isActive(true)
                .build();
    }

    public ChatResponse toChatResponse(Chat chat) {
        return ChatResponse.builder()
                .id(chat.getId())
                .roomId(chat.getRoomId())
                .userSenderId(chat.getUserSenderId())
                .userRecipientId(chat.getUserRecipientId())
                .message(chat.getMessage())
                .readAt(chat.getReadAt().toEpochMilli())
                .isActive(chat.getIsActive())
                .createdAt(chat.getCreatedAt().toEpochMilli())
                .build();
    }

    public MessageNotificationRequest toMessageNotificationRequest(UserResponse userSender, UserResponse userRecipient,
                                                                   Chat chat) {
        return MessageNotificationRequest.builder()
                .userSender(
                        UserSenderRequest.builder()
                                .id(userSender.getId())
                                .email(userSender.getEmail())
                                .username(userSender.getUsername())
                                .fullName(userSender.getFullName())
                                .build()
                )
                .userRecipient(
                        UserRecipientRequest.builder()
                                .id(userRecipient.getId())
                                .email(userRecipient.getEmail())
                                .username(userRecipient.getUsername())
                                .fullName(userRecipient.getFullName())
                                .build()
                )
                .message(
                        MessageSentRequest.builder()
                                .id(chat.getId())
                                .message(chat.getMessage())
                                .createdAt(chat.getUpdatedAt().toEpochMilli())
                                .build()
                )
                .sentFromBrokerAt(null)
                .build();
    }
}
