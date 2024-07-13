package com.kaiwa.messageservice.event.notification.exchange;

import lombok.*;

@AllArgsConstructor
@NoArgsConstructor
@Builder
@Getter
@Setter
public class MessageSentRequest {

    private String id;

    private String message;

    private Long createdAt;


}
