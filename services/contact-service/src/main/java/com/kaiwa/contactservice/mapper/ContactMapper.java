package com.kaiwa.contactservice.mapper;

import com.kaiwa.contactservice.entity.Contact;
import com.kaiwa.contactservice.payload.request.ContactRequest;
import com.kaiwa.contactservice.payload.response.ContactResponse;
import org.springframework.stereotype.Component;

import java.util.UUID;

@Component
public class ContactMapper {

    public Contact toContact(ContactRequest createContactRequest) {
        return Contact.builder()
                .id(UUID.randomUUID().toString())
                .userId(createContactRequest.getUserId())
                .savedContactId(createContactRequest.getSavedContactId())
                .isBlocked(false)
                .isActive(true)
                .build();
    }

    public ContactResponse toContactResponse(Contact contact) {
        return ContactResponse.builder()
                .id(contact.getId())
                .userId(contact.getUserId())
                .savedContactId(contact.getSavedContactId())
                .isBlocked(contact.getIsBlocked())
                .isActive(contact.getIsActive())
                .createdAt(contact.getCreatedAt().toEpochMilli())
                .build();
    }

}
