package com.kaiwa.contactservice.service;

import com.kaiwa.contactservice.payload.request.ContactRequest;
import com.kaiwa.contactservice.payload.response.ContactResponse;

import java.util.List;

public interface ContactService {
    ContactResponse create(ContactRequest createContactRequest);
    List<ContactResponse> findAllByUserId(String userId);
    void blockContactById(String userId, String savedContactId);
}
