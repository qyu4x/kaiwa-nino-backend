package com.kaiwa.contactservice.converter;

import jakarta.persistence.AttributeConverter;
import jakarta.persistence.Converter;

import java.time.Instant;
import java.util.Objects;

@Converter
public class InstantToLongConverter implements AttributeConverter<Instant, Long> {
    @Override
    public Long convertToDatabaseColumn(Instant instant) {
        return instant.toEpochMilli();
    }

    @Override
    public Instant convertToEntityAttribute(Long aLong) {
        if (Objects.isNull(aLong)) {
            return null;
        }
        return Instant.ofEpochMilli(aLong);
    }
}
