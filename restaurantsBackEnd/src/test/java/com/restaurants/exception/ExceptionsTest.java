package com.restaurants.exception;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.mockito.ArgumentMatchers.any;

@ExtendWith(MockitoExtension.class)
public class ExceptionsTest {

    @InjectMocks
    private GlobalExceptionHandler target;


    @Test
    public void handleServiceException() {
        ServiceException exception = new ServiceException(HttpStatus.BAD_REQUEST,new ErrorResponse("Test Message"));
        ResponseEntity<ErrorResponse> actual = target.handleServiceException(exception);
        assertNotNull(actual);
        assertEquals(HttpStatus.BAD_REQUEST,actual.getStatusCode());
    }

    @Test
    public void handleException() {
        Exception exception = new Exception("Test Message");
        ResponseEntity<ErrorResponse> actual = target.handleException(exception);
        assertNotNull(actual);
        assertEquals(HttpStatus.INTERNAL_SERVER_ERROR,actual.getStatusCode());
    }


}
