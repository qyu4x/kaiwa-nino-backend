package com.kaiwa.contactservice.payload.response;


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
