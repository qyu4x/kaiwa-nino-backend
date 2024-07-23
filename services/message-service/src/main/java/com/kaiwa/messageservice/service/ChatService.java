package com.kaiwa.messageservice.service;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.kaiwa.messageservice.payload.request.ChatRequest;
import com.kaiwa.messageservice.payload.response.ChatResponse;

import java.io.IOException;
import java.util.List;

public interface ChatService {

    void send(ChatRequest chatRequest) throws IOException, InterruptedException;

    List<ChatResponse> findByRoomId(String roomId);

}
