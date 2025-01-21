package com.demo.tx.model;

public class ApiErrorWrapper {
    private ApiErrorResponse errorResponse;

    public ApiErrorWrapper(ApiErrorResponse errorResponse) {
        this.errorResponse = errorResponse;
    }

    // Getter and setter
    public ApiErrorResponse getErrorResponse() {
        return errorResponse;
    }

    public void setErrorResponse(ApiErrorResponse errorResponse) {
        this.errorResponse = errorResponse;
    }
}

