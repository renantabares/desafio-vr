package br.com.vr.exceptions;

import lombok.Getter;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

    @RestControllerAdvice
    @Getter
    public class GlobalExceptionHandler {

        @ExceptionHandler(CardAlreadyExistsException.class)
        public ResponseEntity<ErrorResponse> handleCardAlreadyExists(CardAlreadyExistsException ex) {
            ErrorResponse error = new ErrorResponse(
                    HttpStatus.UNPROCESSABLE_ENTITY.value(),
                    ex.getMessage(),
                    System.currentTimeMillis()
            );
            return new ResponseEntity<>(error, HttpStatus.UNPROCESSABLE_ENTITY);
        }
        @ExceptionHandler(CardNotFoundException.class)
        public ResponseEntity<ErrorResponse> handleCardNotFound(CardNotFoundException ex) {
            ErrorResponse error = new ErrorResponse(
                    HttpStatus.NOT_FOUND.value(),
                    ex.getMessage(),
                    System.currentTimeMillis()
            );
            return new ResponseEntity<>(error, HttpStatus.NOT_FOUND);
        }
        @Getter
        public static class ErrorResponse {
            private int status;
            private String message;
            private long timestamp;

            public ErrorResponse(int status, String message, long timestamp) {
                this.status = status;
                this.message = message;
                this.timestamp = timestamp;
            }
            public int getStatus() { return status; }
            public String getMessage() { return message; }
            public long getTimestamp() { return timestamp; }

        }
    }

