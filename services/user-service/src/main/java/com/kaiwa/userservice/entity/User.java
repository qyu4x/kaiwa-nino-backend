package com.kaiwa.userservice.entity;

import jakarta.persistence.*;
import lombok.*;

@AllArgsConstructor
@NoArgsConstructor
@Builder
@Getter
@Setter
@Entity
@Table(name = "users")
public class User extends AuditableEntity{

    @Id
    private String id;

    private String username;

    private String email;

    @Enumerated(EnumType.STRING)
    private Role role;

    @Column(name = "full_name")
    private String fullName;

    @Lob
    @Column(columnDefinition="TEXT")
    private String biography;

    @Column(name = "avatar_url")
    private String avatarUrl;

    @Column(name = "is_active")
    private Boolean isActive;

}
