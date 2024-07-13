package com.kaiwa.notificationservice.client.message;

import lombok.*;

@AllArgsConstructor
@NoArgsConstructor
@Builder
@Getter
@Setter
public class MessageNotification {

    private UserRecipient userRecipient;

    private UserSender userSender;

    private MessageSent message;

    private Long sentFromBrokerAt;

}
