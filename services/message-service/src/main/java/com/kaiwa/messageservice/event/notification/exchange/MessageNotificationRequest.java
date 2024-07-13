package com.kaiwa.messageservice.event.notification.exchange;

import lombok.*;

@AllArgsConstructor
@NoArgsConstructor
@Builder
@Getter
@Setter
public class MessageNotificationRequest {

    private UserRecipientRequest userRecipient;

    private UserSenderRequest userSender;

    private MessageSentRequest message;

    private Long sentFromBrokerAt;
}
