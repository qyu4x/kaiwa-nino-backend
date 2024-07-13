package com.kaiwa.messageservice.service.impl;

import com.kaiwa.messageservice.client.contact.ContactClient;
import com.kaiwa.messageservice.client.exchange.ApiResponse;
import com.kaiwa.messageservice.client.exchange.user.UserResponse;
import com.kaiwa.messageservice.client.user.UserClient;
import com.kaiwa.messageservice.entity.Chat;
import com.kaiwa.messageservice.event.notification.NotificationProducer;
import com.kaiwa.messageservice.mapper.ChatMapper;
import com.kaiwa.messageservice.payload.request.ChatRequest;
import com.kaiwa.messageservice.payload.response.ChatResponse;
import com.kaiwa.messageservice.repository.ChatRepository;
import com.kaiwa.messageservice.service.ChatService;
import jakarta.persistence.EntityManager;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Isolation;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.server.ResponseStatusException;

import java.time.Instant;
import java.util.List;
import java.util.Objects;
import java.util.UUID;

@Slf4j
@Service
@RequiredArgsConstructor
public class ChatServiceImpl implements ChatService {

    private final UserClient userClient;

    private final ContactClient contactClient;

    private final ChatRepository chatRepository;

    private final NotificationProducer notificationProducer;

    private final ChatMapper chatMapper;

    private void checkContactExists(String userId, String savedContactId) {
        ResponseEntity<ApiResponse<Boolean>> userExistsResponse = contactClient.existsByUserIdAndSavedContactId(userId, savedContactId);
        if (userExistsResponse.getStatusCode().is5xxServerError()) {
            throw new ResponseStatusException(HttpStatus.INTERNAL_SERVER_ERROR, "Oops error, please contact admin!");
        }

        if (!Objects.requireNonNull(userExistsResponse.getBody()).getData()) {
            throw new ResponseStatusException(HttpStatus.NOT_FOUND, "Contact not found");
        }
    }

    private UserResponse findUserById(String userId) {
        ResponseEntity<ApiResponse<UserResponse>> userResponse = userClient.findById(userId);
        if (userResponse.getStatusCode().is4xxClientError()) {
            throw new ResponseStatusException(HttpStatus.NOT_FOUND, "User not found");
        }

        if (userResponse.getStatusCode().is5xxServerError()) {
            throw new ResponseStatusException(HttpStatus.INTERNAL_SERVER_ERROR, "Oops error, please contact admin!");
        }

        return Objects.requireNonNull(userResponse.getBody()).getData();
    }

//    public Chat isChatRoomAvailable(String userSenderId, String userRecipientId) {
//        System.out.println("here 1-1");
//        return chatRepository.findByUserSenderIdAndUserRecipientId(userSenderId, userRecipientId)
//                .orElse(Chat.builder().build());
//    }


    @Override
    @Transactional
    public void send(ChatRequest chatRequest) {
        if (chatRequest.getUserSenderId().equals(chatRequest.getUserRecipientId())) {
            throw new ResponseStatusException(HttpStatus.BAD_REQUEST, "User Sender and User Recipient must not be same!");
        }

        UserResponse userSenderResponse = findUserById(chatRequest.getUserSenderId());
        UserResponse userRecipientResponse = findUserById(chatRequest.getUserRecipientId());
        checkContactExists(chatRequest.getUserSenderId(), chatRequest.getUserRecipientId());

        Chat chat = chatMapper.toChat(chatRequest);
        chat.setRoomId(UUID.randomUUID().toString());

        List<Chat> isRoomAvailableResponse = chatRepository
                .findByUserSenderIdAndUserRecipientIdOrUserSenderIdAndUserRecipientId(
                        chatRequest.getUserSenderId(),
                        chatRequest.getUserRecipientId(),
                        chatRequest.getUserRecipientId(),
                        chatRequest.getUserSenderId());

        if (!isRoomAvailableResponse.isEmpty()) {
            chat.setRoomId(isRoomAvailableResponse.get(0).getRoomId());
        }

        Chat createdChatResponse = chatRepository.save(chat);

        notificationProducer.sendMessageNotification(
                chatMapper.toMessageNotificationRequest(userSenderResponse, userRecipientResponse, createdChatResponse));
    }

    @Override
    @Transactional
    public List<ChatResponse> findByRoomId(String roomId) {
        return chatRepository.findByRoomIdAndIsActiveTrueOrderByCreatedAtAsc(roomId).stream()
                .map(chatMapper::toChatResponse)
                .toList();
    }
}

