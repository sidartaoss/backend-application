package br.com.challange.backcode.exceptions;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

@RestControllerAdvice
public class GlobalExceptionHandler {

    @ExceptionHandler(value = NotFoundException.class)
    public ResponseEntity<ApiError> handleNotFoundException(final NotFoundException ex) {
        return ResponseEntity.status(HttpStatus.NOT_FOUND).body(ApiError.from(ex));
    }

    @ExceptionHandler(value = AuthException.class)
    public ResponseEntity<ApiError> handleAuthException(final AuthException ex) {
        return ResponseEntity.status(HttpStatus.UNAUTHORIZED).body(ApiError.from(ex));
    }

    public record ApiError(String message) {
        static ApiError from(final RuntimeException ex) {
            return new ApiError(ex.getMessage());
        }
    }
}