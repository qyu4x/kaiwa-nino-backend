package com.kaiwa.messageservice.service;

import com.kaiwa.messageservice.payload.request.ChatRequest;
import com.kaiwa.messageservice.payload.response.ChatResponse;

import java.util.List;

public interface ChatService {

    void send(ChatRequest chatRequest);

    List<ChatResponse> findByRoomId(String roomId);

}
