package com.kaiwa.contactservice.payload.response;

import lombok.*;

import java.time.LocalDateTime;

@AllArgsConstructor
@NoArgsConstructor
@Builder
@Getter
@Setter
public class ErrorResponse<T>{

    private String apiPath;

    private Integer errorCode;

    private T errorMessage;

    private LocalDateTime errorTime;
}
