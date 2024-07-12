package com.kaiwa.contactservice.controller;

import com.kaiwa.contactservice.payload.request.ContactRequest;
import com.kaiwa.contactservice.payload.response.ContactResponse;
import com.kaiwa.contactservice.payload.response.WebResponse;
import com.kaiwa.contactservice.service.ContactService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequiredArgsConstructor
@RequestMapping(path = "/api/v1/contacts")
public class ContactController {

    private final ContactService contactService;

    @PostMapping(
            consumes = MediaType.APPLICATION_JSON_VALUE,
            produces = MediaType.APPLICATION_JSON_VALUE
    )
    public ResponseEntity<WebResponse<ContactResponse>> create(@RequestBody @Valid ContactRequest contactRequest) {
        ContactResponse contactResponse = contactService.create(contactRequest);
        return ResponseEntity.ok(WebResponse.<ContactResponse>builder()
                .data(contactResponse)
                .build());

    }
}
