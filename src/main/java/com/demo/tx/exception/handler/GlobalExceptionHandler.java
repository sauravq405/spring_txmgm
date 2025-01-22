package com.demo.tx.exception.handler;

import com.demo.tx.exception.InsufficientFundsException;
import com.demo.tx.model.ApiErrorResponse;
import com.demo.tx.model.ApiErrorWrapper;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.context.request.WebRequest;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

//@ControllerAdvice
public class GlobalExceptionHandler {

    @ExceptionHandler(Exception.class)
    public ResponseEntity<ApiErrorWrapper> handleAllExceptions(Exception ex, WebRequest request) {
        String timestamp = LocalDateTime.now().format(DateTimeFormatter.ISO_DATE_TIME);

        ApiErrorResponse errorResponse = new ApiErrorResponse(
                HttpStatus.INTERNAL_SERVER_ERROR.value(),
                ex.getMessage(),
                request.getDescription(false).replace("uri=", ""),
                timestamp
        );
        // Wrap the error response
        ApiErrorWrapper wrappedResponse = new ApiErrorWrapper(errorResponse);
        return new ResponseEntity<>(wrappedResponse, HttpStatus.INTERNAL_SERVER_ERROR);
    }

    @ExceptionHandler(IllegalArgumentException.class)
    public ResponseEntity<ApiErrorWrapper> handleIllegalArgumentException(IllegalArgumentException ex, WebRequest request) {
        String timestamp = LocalDateTime.now().format(DateTimeFormatter.ISO_DATE_TIME);

        ApiErrorResponse errorResponse = new ApiErrorResponse(
                HttpStatus.BAD_REQUEST.value(),
                ex.getMessage(),
                request.getDescription(false).replace("uri=", ""),
                timestamp
        );
        // Wrap the error response
        ApiErrorWrapper wrappedResponse = new ApiErrorWrapper(errorResponse);
        return new ResponseEntity<>(wrappedResponse, HttpStatus.BAD_REQUEST);
    }

    @ExceptionHandler(NullPointerException.class)
    public ResponseEntity<ApiErrorWrapper> handleNullPointerException(NullPointerException ex, WebRequest request) {
        String timestamp = LocalDateTime.now().format(DateTimeFormatter.ISO_DATE_TIME);

        ApiErrorResponse errorResponse = new ApiErrorResponse(
                HttpStatus.BAD_REQUEST.value(),
                ex.getMessage(),
                request.getDescription(false).replace("uri=", ""),
                timestamp
        );
        // Wrap the error response
        ApiErrorWrapper wrappedResponse = new ApiErrorWrapper(errorResponse);
        return new ResponseEntity<>(wrappedResponse, HttpStatus.BAD_REQUEST);
    }

    @ExceptionHandler(InsufficientFundsException.class)
    public ResponseEntity<ApiErrorWrapper> handleFlightBookingException(InsufficientFundsException ex, WebRequest request) {
        String timestamp = LocalDateTime.now().format(DateTimeFormatter.ISO_DATE_TIME);

        ApiErrorResponse errorResponse = new ApiErrorResponse(
                HttpStatus.BAD_REQUEST.value(),
                ex.getMessage(),
                request.getDescription(false).replace("uri=", ""),
                timestamp
        );
        // Wrap the error response
        ApiErrorWrapper wrappedResponse = new ApiErrorWrapper(errorResponse);
        return new ResponseEntity<>(wrappedResponse, HttpStatus.BAD_REQUEST);
    }

}

