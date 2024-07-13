package com.kaiwa.messageservice.payload.response;

import lombok.*;

@AllArgsConstructor
@NoArgsConstructor
@Builder
@Getter
@Setter
public class ChatResponse {

    private String id;

    private String roomId;

    private String userSenderId;

    private String userRecipientId;

    private String message;

    private  Long readAt;

    private Boolean isActive;

    private Long createdAt;

}
