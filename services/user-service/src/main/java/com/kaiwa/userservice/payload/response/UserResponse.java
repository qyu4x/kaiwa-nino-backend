package com.kaiwa.userservice.payload.response;

import lombok.*;

@AllArgsConstructor
@NoArgsConstructor
@Builder
@Getter
@Setter
public class UserResponse {

    private String id;

    private String username;

    private String email;

    private String role;

    private String fullName;

    private String biography;

    private String avatarUrl;

    private Boolean isActive;

    private Long createdAt;

}
