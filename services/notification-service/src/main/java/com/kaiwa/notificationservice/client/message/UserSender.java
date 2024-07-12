package com.kaiwa.notificationservice.client.message;

import lombok.*;

@AllArgsConstructor
@NoArgsConstructor
@Builder
@Getter
@Setter
public class UserSender {

    private String id;

    private String username;

    private String email;

    private String fullName;

}