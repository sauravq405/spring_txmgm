package com.demo.tx.model;

import com.fasterxml.jackson.annotation.JsonInclude;

import java.util.List;

@JsonInclude(JsonInclude.Include.NON_NULL)
public class ApiErrorResponseV2 {
    private int statusCode;
    private String errorMessage;
    private String path;
    private String timestamp;
    private String className;
    private String methodName;
    private String packageName;
    private Integer lineNumber;
    private List<String> stackTrace;
    public ApiErrorResponseV2(){

    }

    public ApiErrorResponseV2(int statusCode, String errorMessage, String path, String timestamp) {
        this.statusCode = statusCode;
        this.errorMessage = errorMessage;
        this.path = path;
        this.timestamp = timestamp;
    }

    public ApiErrorResponseV2(int statusCode, String errorMessage, String path, String timestamp, String className, String methodName, String packageName, Integer lineNumber, List<String> stackTrace) {
        this.statusCode = statusCode;
        this.errorMessage = errorMessage;
        this.path = path;
        this.timestamp = timestamp;
        this.className = className;
        this.methodName = methodName;
        this.packageName = packageName;
        this.lineNumber = lineNumber;
        this.stackTrace = stackTrace;
    }
    // Getters and setters

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

    public String getClassName() {
        return className;
    }

    public void setClassName(String className) {
        this.className = className;
    }

    public String getMethodName() {
        return methodName;
    }

    public void setMethodName(String methodName) {
        this.methodName = methodName;
    }

    public String getPackageName() {
        return packageName;
    }

    public void setPackageName(String packageName) {
        this.packageName = packageName;
    }

    public Integer getLineNumber() {
        return lineNumber;
    }

    public void setLineNumber(Integer lineNumber) {
        this.lineNumber = lineNumber;
    }

    public List<String> getStackTrace() {
        return stackTrace;
    }

    public void setStackTrace(List<String> stackTrace) {
        this.stackTrace = stackTrace;
    }

    @Override
    public String toString() {
        return "ApiErrorResponseV2{" +
                "statusCode=" + statusCode +
                ", errorMessage='" + errorMessage + '\'' +
                ", path='" + path + '\'' +
                ", timestamp='" + timestamp + '\'' +
                ", className='" + className + '\'' +
                ", methodName='" + methodName + '\'' +
                ", packageName='" + packageName + '\'' +
                ", lineNumber=" + lineNumber +
                ", stackTrace=" + stackTrace +
                '}';
    }
}

