package com.demo.tx.exception.handler;

import com.demo.tx.exception.InsufficientFundsException;
import com.demo.tx.model.ApiErrorResponseV2;
import com.demo.tx.model.ApiErrorWrapperV2;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.SerializationFeature;
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

    private Throwable getRootCause(Throwable throwable) {
        Throwable cause = throwable.getCause();
        return (cause == null) ? throwable : getRootCause(cause);
    }

    private ResponseEntity<ApiErrorWrapperV2> buildErrorResponse(Throwable ex, WebRequest request, HttpStatus status) {
        String timestamp = LocalDateTime.now().format(DateTimeFormatter.ISO_DATE_TIME);
        String activeProfile = environment.getProperty("spring.profiles.active");

        // Create base error response
        ApiErrorResponseV2 errorResponse = new ApiErrorResponseV2(
                status.value(),
                ex.getMessage(),
                request.getDescription(false).replace("uri=", ""),
                timestamp
        );

        // Populate additional fields for logging purposes
        populateAdditionalFields(errorResponse, ex);

        //ideally, there shouldn't be a SUCCESS case scenario where control would come to @ControllerAdvice annotated class
        //Still if you want to double-check and apply validation before logging error details.
        int httpStatusCode = status.value();
        if (httpStatusCode >= 200 && httpStatusCode < 300) {
            System.out.println("Ignoring to log sensitive fields");
        } else {
            // Print the entire object to the logs
            logErrorDetails(errorResponse);
        }


        // Remove additional fields for API response in non-dev/uat environments
        if (!isDevOrUat(activeProfile)) {
            stripSensitiveFields(errorResponse);
        }

        ApiErrorWrapperV2 wrappedResponse = new ApiErrorWrapperV2(errorResponse);
        return new ResponseEntity<>(wrappedResponse, status);
    }

    private boolean isDevOrUat(String profile) {
        return profile != null && (profile.equals("dev") || profile.equals("uat"));
    }

    private void populateAdditionalFields(ApiErrorResponseV2 errorResponse, Throwable ex) {
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

    private void stripSensitiveFields(ApiErrorResponseV2 errorResponse) {
        errorResponse.setClassName(null);
        errorResponse.setMethodName(null);
        errorResponse.setPackageName(null);
        errorResponse.setLineNumber(null);
        errorResponse.setStackTrace(null);
    }

    private void logErrorDetails(ApiErrorResponseV2 errorResponse) {
        try {
            ObjectMapper objectMapper = new ObjectMapper();
            objectMapper.enable(SerializationFeature.INDENT_OUTPUT); // Pretty print JSON
            System.out.println("Error Details (Full):");
            System.out.println(objectMapper.writeValueAsString(errorResponse));
        } catch (JsonProcessingException e) {
            System.err.println("Error logging details as JSON: " + e.getMessage());
        }
    }


}
