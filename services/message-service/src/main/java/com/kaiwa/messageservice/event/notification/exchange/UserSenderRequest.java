package com.kaiwa.messageservice.event.notification.exchange;

import lombok.*;

@AllArgsConstructor
@NoArgsConstructor
@Builder
@Getter
@Setter
public class UserSenderRequest {

    private String id;

    private String username;

    private String email;

    private String fullName;

}
