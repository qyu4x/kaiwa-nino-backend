package com.kaiwa.contactservice.entity;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import lombok.*;

@AllArgsConstructor
@NoArgsConstructor
@Builder
@Getter
@Setter
@Entity
@Table(name = "contacts")
public class Contact extends AuditableEntity{

    @Id
    private String id;

    @Column(name = "user_id")
    private String userId;

    @Column(name = "saved_contact_id")
    private String savedContactId;

    @Column(name = "is_blocked")
    private Boolean isBlocked;

    @Column(name = "is_active")
    private Boolean isActive;
}
