package com.davidrrf.workoutapi.exceptions;

public class ResourceErrorException extends RuntimeException {
    private final int statusCode;
    public ResourceErrorException(String message, int statusCode) {
        super(message);
        this.statusCode = statusCode;
    }
    public ResourceErrorException(String message, int statusCode, Throwable cause) {
        super(message, cause);
        this.statusCode = statusCode;
    }
    public int getStatusCode() {
        return statusCode;
    }
}
