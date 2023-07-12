package com.restaurants.exception;

import lombok.Getter;
import lombok.Setter;
import org.springframework.http.HttpStatus;

@Getter
@Setter
public class ServiceException extends RuntimeException {

    private final HttpStatus httpStatus;
    private final ErrorResponse errorResponse;


    public ServiceException(HttpStatus httpStatus, ErrorResponse errorResponse) {
        super(errorResponse.getMessage());
        this.httpStatus = httpStatus;
        this.errorResponse = errorResponse;
    }

}
