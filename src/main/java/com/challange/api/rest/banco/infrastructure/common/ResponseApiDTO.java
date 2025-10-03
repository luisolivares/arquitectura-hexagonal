package com.challange.api.rest.banco.infrastructure.common;

import com.challange.api.rest.banco.infrastructure.handler.ApiError;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class ResponseApiDTO<T> {
    private T data;
    private ApiError error;
}
