package com.kaiwa.messageservice.client.contact;

import com.kaiwa.messageservice.client.exchange.ApiResponse;
import com.kaiwa.messageservice.client.exchange.contact.ContactResponse;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

@FeignClient(
        name = "user-service",
        url = "${application.config.contact-url}"
)
public interface ContactClient {
    @GetMapping(value = "/{userId}/exists/{savedContactId}")
    ResponseEntity<ApiResponse<Boolean>> existsByUserIdAndSavedContactId(@PathVariable(value = "userId", required = true) String userId,
                                                                               @PathVariable(value = "savedContactId", required = true) String savedContactId);

}
