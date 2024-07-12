package com.kaiwa.contactservice.service;

import com.kaiwa.contactservice.payload.request.ContactRequest;
import com.kaiwa.contactservice.payload.response.ContactResponse;

public interface ContactService {
    ContactResponse create(ContactRequest createContactRequest);

}
