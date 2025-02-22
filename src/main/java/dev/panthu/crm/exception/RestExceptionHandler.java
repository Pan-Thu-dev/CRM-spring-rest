package dev.panthu.crm.exception;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestController;

import java.time.LocalDateTime;

@ControllerAdvice(annotations = RestController.class)  // Only for REST controllers
public class RestExceptionHandler {
    
    @ExceptionHandler
    public ResponseEntity<CustomerErrorResponse> handleException(CustomerNotFoundException exc) {
        CustomerErrorResponse error = new CustomerErrorResponse(
                HttpStatus.NOT_FOUND.value(),
                exc.getMessage(),
                LocalDateTime.now()
        );
        return new ResponseEntity<>(error, HttpStatus.NOT_FOUND);
    }

    @ExceptionHandler
    public ResponseEntity<CustomerErrorResponse> handleException(Exception exc) {
        CustomerErrorResponse error = new CustomerErrorResponse(
                HttpStatus.BAD_REQUEST.value(),
                exc.getMessage(),
                LocalDateTime.now()
        );
        return new ResponseEntity<>(error, HttpStatus.BAD_REQUEST);
    }
} 