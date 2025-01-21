package com.demo.tx.model;

public class ApiErrorResponse {
    private int statusCode;
    private String errorMessage;
    private String path;
    private String timestamp;

    public ApiErrorResponse() {
    }

    public ApiErrorResponse(int statusCode, String errorMessage, String path, String timestamp) {
        this.statusCode = statusCode;
        this.errorMessage = errorMessage;
        this.path = path;
        this.timestamp = timestamp;
    }

    public int getStatusCode() {
        return statusCode;
    }

    public void setStatusCode(int statusCode) {
        this.statusCode = statusCode;
    }

    public String getErrorMessage() {
        return errorMessage;
    }

    public void setErrorMessage(String errorMessage) {
        this.errorMessage = errorMessage;
    }

    public String getPath() {
        return path;
    }

    public void setPath(String path) {
        this.path = path;
    }

    public String getTimestamp() {
        return timestamp;
    }

    public void setTimestamp(String timestamp) {
        this.timestamp = timestamp;
    }

    @Override
    public String toString() {
        return "ApiErrorResponse{" +
                "statusCode=" + statusCode +
                ", errorMessage='" + errorMessage + '\'' +
                ", path='" + path + '\'' +
                ", timestamp='" + timestamp + '\'' +
                '}';
    }
}

