package io.kk.remitlyhomeexercise.exception;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

@ControllerAdvice
public class GlobalExceptionHandler {

    @ExceptionHandler(BankNotFoundException.class)
    public ResponseEntity<ErrorResponse> handleBankNotFoundException(BankNotFoundException e) {
        ErrorResponse errorResponse = new ErrorResponse("404", e.getMessage());
        return new ResponseEntity<>(errorResponse, HttpStatus.NOT_FOUND);
    }

    @ExceptionHandler(BankAlreadyExistsException.class)
    public ResponseEntity<ErrorResponse> handleBankAlreadyExists(BankAlreadyExistsException e) {
        ErrorResponse errorResponse = new ErrorResponse("409", e.getMessage());
        return new ResponseEntity<>(errorResponse, HttpStatus.CONFLICT);
    }

    @ExceptionHandler(SwiftCodeException.class)
    public ResponseEntity<ErrorResponse> handleSwiftCodeException(SwiftCodeException e) {
        ErrorResponse errorResponse = new ErrorResponse("400", e.getMessage());
        return new ResponseEntity<>(errorResponse, HttpStatus.BAD_REQUEST);
    }
}
