package com.kaiwa.contactservice.client.user;

import com.kaiwa.contactservice.client.exchange.ApiResponse;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

@FeignClient(
        name = "user-service",
        url = "${application.config.user-url}"
)
public interface UserClient {

    @GetMapping(value = "/{userId}/exists")
    ResponseEntity<ApiResponse<Boolean>> existsByUserIdAndIsActiveTrue(@PathVariable(value = "userId", required = true) String userId);
}
