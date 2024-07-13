package com.kaiwa.messageservice.client.exchange.contact;

import lombok.*;

@AllArgsConstructor
@NoArgsConstructor
@Builder
@Getter
@Setter
public class ContactResponse {

    private String id;

    private String userId;

    private String savedContactId;

    private Boolean isBlocked;

    private Boolean isActive;

    private Long createdAt;

}
