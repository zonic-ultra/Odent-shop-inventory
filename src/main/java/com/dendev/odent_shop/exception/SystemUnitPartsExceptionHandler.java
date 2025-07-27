package com.dendev.odent_shop.exception;

import com.dendev.odent_shop.entity.SystemUnitPartsErrorResponse;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

@ControllerAdvice
public class SystemUnitPartsExceptionHandler {

    @ExceptionHandler
    public ResponseEntity<SystemUnitPartsErrorResponse> studentException(SystemUnitPartsException systemUnitPartsException){
        SystemUnitPartsErrorResponse systemUnitPartsErrorResponse = new SystemUnitPartsErrorResponse();
        systemUnitPartsErrorResponse.setStatus(HttpStatus.NOT_FOUND.value());
        systemUnitPartsErrorResponse.setMessage((systemUnitPartsException.getMessage()));
        systemUnitPartsErrorResponse.setTimeStamp(System.currentTimeMillis());

        return new ResponseEntity<>(systemUnitPartsErrorResponse, HttpStatus.NOT_FOUND);
    }

}
