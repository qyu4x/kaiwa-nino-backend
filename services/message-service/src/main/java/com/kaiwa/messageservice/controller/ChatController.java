package com.kaiwa.messageservice.controller;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.kaiwa.messageservice.payload.request.ChatRequest;
import com.kaiwa.messageservice.payload.response.ChatResponse;
import com.kaiwa.messageservice.payload.response.WebResponse;
import com.kaiwa.messageservice.service.ChatService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.io.IOException;
import java.util.List;

@RestController
@RequiredArgsConstructor
@RequestMapping(path = "/api/v1/messages")
public class ChatController {

    private final ChatService chatService;

    @PostMapping(
            consumes = MediaType.APPLICATION_JSON_VALUE,
            produces = MediaType.APPLICATION_JSON_VALUE
    )
    public ResponseEntity<WebResponse<String>> create(@RequestBody @Valid ChatRequest chatRequest)
            throws IOException, InterruptedException {
        chatService.send(chatRequest);
        return ResponseEntity.ok(WebResponse.<String>builder()
                .data("OK")
                .build());
    }

    @GetMapping(
            path = "/{roomId}",
            produces = MediaType.APPLICATION_JSON_VALUE
    )
    public ResponseEntity<WebResponse<List<ChatResponse>>> findAllByUserId(@PathVariable(value = "roomId", required = true) String roomId) {
        List<ChatResponse> chatResponses = chatService.findByRoomId(roomId);
        return ResponseEntity.ok(WebResponse.<List<ChatResponse>>builder()
                .data(chatResponses)
                .build());
    }

}
