package com.kaiwa.notificationservice.event.message.exchange;

import lombok.*;

@AllArgsConstructor
@NoArgsConstructor
@Builder
@Getter
@Setter
public class MessageSent {

    private String id;

    private String message;

    private Long createdAt;

}
