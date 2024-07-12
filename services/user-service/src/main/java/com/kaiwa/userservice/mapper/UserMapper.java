package com.kaiwa.userservice.mapper;

import com.kaiwa.userservice.entity.DefaultBiography;
import com.kaiwa.userservice.entity.Role;
import com.kaiwa.userservice.entity.User;
import com.kaiwa.userservice.payload.request.UserRequest;
import com.kaiwa.userservice.payload.response.UserResponse;
import org.springframework.stereotype.Component;

import java.util.Objects;
import java.util.UUID;

@Component
public class UserMapper {

    public User toUser(UserRequest userRequest) {
        return User.builder()
                .id(UUID.randomUUID().toString())
                .username(userRequest.getUsername())
                .email(userRequest.getEmail())
                .role(Role.USER)
                .fullName(userRequest.getFullName())
                .biography(Objects.isNull(userRequest.getBiography()) ? DefaultBiography.DEFAULT.getBiography() : userRequest.getBiography())
                .avatarUrl(null)
                .isActive(true)
                .build();
    }

    public UserResponse toUserResponse(User user) {
        return UserResponse.builder()
                .id(user.getId())
                .username(user.getUsername())
                .email(user.getEmail())
                .role(user.getRole().name())
                .fullName(user.getFullName())
                .biography(user.getBiography())
                .avatarUrl(user.getAvatarUrl())
                .isActive(user.getIsActive())
                .createdAt(user.getCreatedAt().toEpochMilli())
                .build();
    }

}
