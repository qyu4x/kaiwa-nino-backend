package com.kaiwa.messageservice.repository;

import com.kaiwa.messageservice.entity.Chat;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;
import java.util.Optional;

public interface ChatRepository extends JpaRepository<Chat, String> {
    List<Chat> findAllByRoomIdAndIsActiveTrue(String roomId);

    Chat findFirstByUserSenderIdAndUserRecipientIdOrUserSenderIdAndUserRecipientId(
            String userSenderId, String userRecipientId,
            String userRecipientId2, String userSenderId2);

    Optional<Chat> findByUserRecipientIdAndUserSenderId(String userSenderId, String userRecipientId);

    List<Chat> findByRoomIdAndIsActiveTrueOrderByCreatedAtAsc(String roomId);
}
