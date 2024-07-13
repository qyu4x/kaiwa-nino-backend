package com.kaiwa.messageservice.repository;

import com.kaiwa.messageservice.entity.Chat;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;
import java.util.Optional;

public interface ChatRepository extends JpaRepository<Chat, String> {
    List<Chat> findAllByRoomIdAndIsActiveTrue(String roomId);

    Optional<Chat> findByUserSenderIdAndUserRecipientId(String userSenderId, String userRecipientId);

    Optional<Chat> findByRoomIdAndIsActiveTrueOrderByCreatedAtAsc(String roomId);
}
