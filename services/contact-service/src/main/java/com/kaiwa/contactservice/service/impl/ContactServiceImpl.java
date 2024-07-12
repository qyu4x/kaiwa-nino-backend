package com.kaiwa.contactservice.service.impl;

import com.kaiwa.contactservice.client.exchange.ApiResponse;
import com.kaiwa.contactservice.client.user.UserClient;
import com.kaiwa.contactservice.entity.Contact;
import com.kaiwa.contactservice.mapper.ContactMapper;
import com.kaiwa.contactservice.payload.request.ContactRequest;
import com.kaiwa.contactservice.payload.response.ContactResponse;
import com.kaiwa.contactservice.repository.ContactRepository;
import com.kaiwa.contactservice.service.ContactService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.server.ResponseStatusException;

import java.util.List;
import java.util.Objects;

@Service
@RequiredArgsConstructor
public class ContactServiceImpl implements ContactService {

    private final ContactRepository contactRepository;

    private final UserClient userClient;

    private final ContactMapper contactMapper;


    private void checkUserExists(String userId) {
        ResponseEntity<ApiResponse<Boolean>> userExistsResponse = userClient.existsByUserIdAndIsActiveTrue(userId);
        if (userExistsResponse.getStatusCode().is5xxServerError()) {
            throw new ResponseStatusException(HttpStatus.INTERNAL_SERVER_ERROR, "Oops error, please contact admin!");
        }

        if (!Objects.requireNonNull(userExistsResponse.getBody()).getData()) {
            throw new ResponseStatusException(HttpStatus.NOT_FOUND, "User not found");
        }
    }

    @Override
    @Transactional
    public ContactResponse create(ContactRequest contactRequest) {
        if (contactRequest.getUserId().equals(contactRequest.getSavedContactId())) {
            throw new ResponseStatusException(HttpStatus.BAD_REQUEST, "User and contact to be saved cannot be the same!");
        }

        contactRepository.findByUserIdAndSavedContactIdAndIsActiveTrue(contactRequest.getUserId(), contactRequest.getSavedContactId())
                .ifPresent(user -> {
                    throw new ResponseStatusException(HttpStatus.CONFLICT, "Contact already saved in your device");
                });

        checkUserExists(contactRequest.getUserId());
        checkUserExists(contactRequest.getSavedContactId());

        Contact contact = contactMapper.toContact(contactRequest);
        return contactMapper.toContactResponse(contactRepository.save(contact));
    }

    @Override
    @Transactional
    public List<ContactResponse> findAllByUserId(String userId) {
        return contactRepository.findAllByUserIdAndIsActiveTrue(userId).stream()
                .map(contactMapper::toContactResponse)
                .toList();
    }
}
