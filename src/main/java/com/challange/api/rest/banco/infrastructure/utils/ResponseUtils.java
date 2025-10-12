package com.challange.api.rest.banco.infrastructure.utils;

import com.challange.api.rest.banco.infrastructure.common.ResponseApiDTO;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Component;

@Component
public class ResponseUtils {

    public static final String STATUS = "status";
    public static final String OK = "OK";
    public static final String SUCCESS_FALSE = "FALSE";
    public static final String TYPE_STATUS_ERROR = "error";


    public ResponseEntity formatOKResponse(Object responseObject) {
        return new ResponseEntity(responseApiDTO(responseObject), HttpStatus.OK);
    }

    private ResponseApiDTO responseApiDTO(Object responseObject) {
        return ResponseApiDTO
                .builder()
                .data(responseObject)
                .error(null)
                .build();
    }

}
