package com.kaiwa.userservice.service;

import com.kaiwa.userservice.payload.request.UserRequest;
import com.kaiwa.userservice.payload.response.UserResponse;

import java.util.List;

public interface UserService {
    UserResponse create(UserRequest userRequest);
    UserResponse findById(String userId);
    List<UserResponse> findAll();

    void deleteById(String userId);
}
