package com.kaiwa.messageservice.payload.request;

import jakarta.validation.constraints.NotBlank;
import lombok.*;

@AllArgsConstructor
@NoArgsConstructor
@Builder
@Getter
@Setter
public class ChatRequest {

    @NotBlank(message = "User sender is required")
    private String userSenderId;

    @NotBlank(message = "User Recipient is required")
    private String userRecipientId;

    @NotBlank(message = "Message is required")
    private String message;

}
