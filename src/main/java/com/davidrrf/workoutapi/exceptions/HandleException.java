package com.davidrrf.workoutapi.exceptions;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ExceptionHandler;

public class HandleException {
    @ExceptionHandler(ResourceErrorException.class)
    public ResponseEntity<ErrorResponse> handleError(ResourceErrorException e) {
        ErrorResponse errorResponse = new ErrorResponse();
        errorResponse.setErrorMessage(e.getMessage());
        errorResponse.setErrorCode(e.getStatusCode());

        return ResponseEntity.status(e.getStatusCode()).body(errorResponse);
    }
}
