package com.kaiwa.contactservice.service;

import com.kaiwa.contactservice.payload.request.ContactRequest;
import com.kaiwa.contactservice.payload.response.ContactResponse;

import java.util.List;

public interface ContactService {
    ContactResponse create(ContactRequest createContactRequest);
    List<ContactResponse> findAllByUserId(String userId);

    ContactResponse findById(String userId, String savedContactId);

    Boolean existsById(String userId, String savedContactId);
    void blockContactById(String userId, String savedContactId);
}
