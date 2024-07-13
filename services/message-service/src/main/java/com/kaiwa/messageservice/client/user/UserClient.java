package com.kaiwa.messageservice.client.user;

import com.kaiwa.messageservice.client.exchange.ApiResponse;
import com.kaiwa.messageservice.client.exchange.user.UserResponse;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

@FeignClient(
        name = "fetch-user-service",
        url = "${application.config.user-url}"
)
public interface UserClient {

    @GetMapping(value = "/{userId}")
    ResponseEntity<ApiResponse<UserResponse>> findById(@PathVariable(value = "userId", required = true) String userId);

}
