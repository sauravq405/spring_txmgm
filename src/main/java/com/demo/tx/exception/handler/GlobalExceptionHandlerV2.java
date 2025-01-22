package com.demo.tx.exception.handler;

import com.demo.tx.exception.InsufficientFundsException;
import com.demo.tx.model.ApiErrorResponseV2;
import com.demo.tx.model.ApiErrorWrapperV2;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.env.Environment;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.context.request.WebRequest;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.Arrays;
import java.util.stream.Collectors;

@ControllerAdvice
public class GlobalExceptionHandlerV2 {

    @Autowired
    private Environment environment;

    @ExceptionHandler(Exception.class)
    public ResponseEntity<ApiErrorWrapperV2> handleAllExceptions(Exception ex, WebRequest request) {
        return buildErrorResponse(ex, request, HttpStatus.INTERNAL_SERVER_ERROR);
    }

    @ExceptionHandler(IllegalArgumentException.class)
    public ResponseEntity<ApiErrorWrapperV2> handleIllegalArgumentException(IllegalArgumentException ex, WebRequest request) {
        return buildErrorResponse(ex, request, HttpStatus.BAD_REQUEST);
    }

    @ExceptionHandler(InsufficientFundsException.class)
    public ResponseEntity<ApiErrorWrapperV2> handleInsufficientFundsException(InsufficientFundsException ex, WebRequest request) {
        return buildErrorResponse(ex, request, HttpStatus.BAD_REQUEST);
    }

    private ResponseEntity<ApiErrorWrapperV2> buildErrorResponse(Throwable ex, WebRequest request, HttpStatus status) {
        String timestamp = LocalDateTime.now().format(DateTimeFormatter.ISO_DATE_TIME);
        // Check active profile
        String activeProfile = environment.getProperty("spring.profiles.active");
        ApiErrorResponseV2 errorResponse = new ApiErrorResponseV2(
                status.value(),
                ex.getMessage(),
                request.getDescription(false).replace("uri=", ""),
                timestamp
        );
        if (activeProfile != null && (activeProfile.equals("dev") || activeProfile.equals("uat"))) {
            // Initialize additional fields for dev or test environment
            Throwable rootCause = getRootCause(ex);
            StackTraceElement element = rootCause.getStackTrace()[0];
            errorResponse.setClassName(element.getClassName());
            errorResponse.setMethodName(element.getMethodName());
            errorResponse.setPackageName(element.getClassName().substring(0, element.getClassName().lastIndexOf('.')));
            errorResponse.setLineNumber(element.getLineNumber());
            errorResponse.setStackTrace(Arrays.stream(ex.getStackTrace())
                    .map(StackTraceElement::toString)
                    .collect(Collectors.toList()));
        }
        ApiErrorWrapperV2 wrappedResponse = new ApiErrorWrapperV2(errorResponse);
        return new ResponseEntity<>(wrappedResponse, status);
    }

    private Throwable getRootCause(Throwable throwable) {
        Throwable cause = throwable.getCause();
        return (cause == null) ? throwable : getRootCause(cause);
    }
}
