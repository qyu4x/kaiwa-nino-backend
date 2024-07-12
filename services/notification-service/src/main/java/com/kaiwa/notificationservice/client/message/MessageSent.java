package com.kaiwa.notificationservice.client.message;

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

    private Long updatedAt;


}
