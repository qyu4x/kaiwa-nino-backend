package com.kaiwa.contactservice.payload.request;

import jakarta.validation.constraints.NotBlank;
import lombok.*;

@AllArgsConstructor
@NoArgsConstructor
@Builder
@Getter
@Setter
public class ContactRequest {

    @NotBlank(message = "User id is required")
    private String userId;

    @NotBlank(message = "Saved Contact id is required")
    private String savedContactId;

}
