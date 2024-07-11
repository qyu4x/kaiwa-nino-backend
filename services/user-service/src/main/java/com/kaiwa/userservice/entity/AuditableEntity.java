package com.kaiwa.userservice.entity;

import com.kaiwa.userservice.converter.InstantToLongConverter;
import jakarta.persistence.Column;
import jakarta.persistence.Convert;
import jakarta.persistence.EntityListeners;
import jakarta.persistence.MappedSuperclass;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.springframework.data.annotation.CreatedDate;
import org.springframework.data.annotation.LastModifiedDate;
import org.springframework.data.jpa.domain.support.AuditingEntityListener;

import java.time.Instant;

@AllArgsConstructor
@NoArgsConstructor
@MappedSuperclass
@Getter
@Setter
@EntityListeners({AuditingEntityListener.class})
public class AuditableEntity {

    @CreatedDate
    @Column(name = "created_at", updatable = false)
    @Convert(converter = InstantToLongConverter.class)
    private Instant createdAt;

    @LastModifiedDate
    @Column(name = "updated_at", insertable = false)
    @Convert(converter = InstantToLongConverter.class)
    private Instant updatedAt;

}
