package com.kaiwa.userservice.entity;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;


@AllArgsConstructor
@Getter
public enum DefaultBiography {
    DEFAULT(""),
    BUSY("Busy"),
    AT_WORK("At Work");

    private final String biography;
}
