package com.kaiwa.userservice.service.impl;

import com.kaiwa.userservice.entity.User;
import com.kaiwa.userservice.exception.DataAlreadyExistsException;
import com.kaiwa.userservice.exception.DataNotFoundException;
import com.kaiwa.userservice.mapper.UserMapper;
import com.kaiwa.userservice.payload.request.UserRequest;
import com.kaiwa.userservice.payload.response.UserResponse;
import com.kaiwa.userservice.repository.UserRepository;
import com.kaiwa.userservice.service.UserService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class UserServiceImpl implements UserService {

    private final UserRepository userRepository;

    private final UserMapper userMapper;

    @Override
    @Transactional
    public UserResponse create(UserRequest userRequest) {
        userRepository.findByEmailAndIsActiveIsTrue(userRequest.getEmail())
                .ifPresent(user ->  {
                    throw new DataAlreadyExistsException("Email already exists");
                });

        userRepository.findByUsernameAndIsActiveIsTrue(userRequest.getUsername())
                .ifPresent(user -> {
                    throw new DataAlreadyExistsException("Username already exists");
                });

        User user = userMapper.toUser(userRequest);
        return userMapper.toUserResponse(userRepository.save(user));
    }

    @Override
    @Transactional(readOnly = true)
    public UserResponse findById(String userId) {
        return userMapper.toUserResponse(userRepository.findByIdAndIsActiveTrue(userId)
                .orElseThrow(() -> new DataNotFoundException("User not found")));
    }

    @Override
    @Transactional
    public List<UserResponse> findAll() {
        return userRepository.findAllByIsActiveIsTrue().stream()
                .map(userMapper::toUserResponse)
                .collect(Collectors.toList());
    }

    @Override
    @Transactional
    public void deleteById(String userId) {
        User user = userRepository.findByIdAndIsActiveTrue(userId)
                .orElseThrow(() -> new DataNotFoundException("User not found"));

        user.setIsActive(false);
        userRepository.save(user);
    }

    @Override
    @Transactional
    public Boolean existById(String userId) {
        return userRepository.existsByIdAndIsActiveTrue(userId);
    }
}
