package com.demo.tx.model;

public class ApiErrorWrapperV2 {
    private ApiErrorResponseV2 errorResponse;

    public ApiErrorWrapperV2(ApiErrorResponseV2 errorResponse) {
        this.errorResponse = errorResponse;
    }

    // Getter and setter
    public ApiErrorResponseV2 getErrorResponse() {
        return errorResponse;
    }

    public void setErrorResponse(ApiErrorResponseV2 errorResponse) {
        this.errorResponse = errorResponse;
    }
}

