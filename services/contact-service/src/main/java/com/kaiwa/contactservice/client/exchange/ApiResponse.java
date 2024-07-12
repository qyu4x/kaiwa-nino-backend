package com.kaiwa.contactservice.client.exchange;

import lombok.*;

@AllArgsConstructor
@NoArgsConstructor
@Builder
@Getter
@Setter
public class ApiResponse<T>{
    private T data;
}
