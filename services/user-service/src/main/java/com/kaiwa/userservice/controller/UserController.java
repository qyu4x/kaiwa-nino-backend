package com.kaiwa.userservice.controller;

import com.kaiwa.userservice.payload.request.UserRequest;
import com.kaiwa.userservice.payload.response.UserResponse;
import com.kaiwa.userservice.payload.response.WebResponse;
import com.kaiwa.userservice.service.UserService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequiredArgsConstructor
@RequestMapping(path = "/api/v1/users")
public class UserController {

    private final UserService userService;


    @PostMapping(
            consumes = MediaType.APPLICATION_JSON_VALUE,
            produces = MediaType.APPLICATION_JSON_VALUE
    )
    private ResponseEntity<WebResponse<UserResponse>> create(@RequestBody @Valid UserRequest userRequest) {
        UserResponse userResponse = userService.create(userRequest);
        return ResponseEntity.ok(WebResponse.<UserResponse>builder()
                .data(userResponse)
                .build());
    }

    @GetMapping(
            path = "/{userId}",
            produces = MediaType.APPLICATION_JSON_VALUE
    )
    private ResponseEntity<WebResponse<UserResponse>> findById(@PathVariable(value = "userId", required = true) String userId) {
        UserResponse userResponse = userService.findById(userId);
        return ResponseEntity.ok(WebResponse.<UserResponse>builder()
                .data(userResponse)
                .build());
    }


    @GetMapping(
            produces = MediaType.APPLICATION_JSON_VALUE
    )
    private ResponseEntity<WebResponse<List<UserResponse>>> findAll() {
        List<UserResponse> userResponses = userService.findAll();
        return ResponseEntity.ok(WebResponse.<List<UserResponse>>builder()
                .data(userResponses)
                .build());
    }

    @DeleteMapping(
            path = "/{userId}",
            produces = MediaType.APPLICATION_JSON_VALUE
    )
    private ResponseEntity<WebResponse<String>> deleteById(@PathVariable(value = "userId", required = true) String userId) {
        userService.deleteById(userId);
        return ResponseEntity.ok(WebResponse.<String>builder()
                .data("OK")
                .build());
    }
}
