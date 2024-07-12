package com.kaiwa.userservice.payload.response;

import lombok.*;

@AllArgsConstructor
@NoArgsConstructor
@Builder
@Getter
@Setter
public class WebResponse <T>{
    private T data;
}
