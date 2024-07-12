package com.kaiwa.contactservice.repository;

import com.kaiwa.contactservice.entity.Contact;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface ContactRepository extends JpaRepository<Contact, String> {
    Optional<Contact> findByUserIdAndSavedContactIdAndIsActiveTrue(String userId, String savedContactId);
}
