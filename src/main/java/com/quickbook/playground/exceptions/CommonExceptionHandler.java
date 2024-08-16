package com.quickbook.playground.exceptions;

import org.springframework.http.ProblemDetail;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestControllerAdvice;
import org.springframework.web.reactive.function.client.WebClientResponseException;

import java.io.IOException;
import java.util.Objects;

import static org.springframework.http.HttpStatus.BAD_REQUEST;

@RestControllerAdvice
public class CommonExceptionHandler {

    @ExceptionHandler(IOException.class)
    @ResponseStatus(BAD_REQUEST)
    public ResponseEntity<ProblemDetail> handleIOException(IOException ex) {
        ProblemDetail pd = ProblemDetail.forStatusAndDetail(BAD_REQUEST, ex.getMessage());
        pd.setTitle("IO Exception");
        return ResponseEntity.badRequest().body(pd);
    }

    @ExceptionHandler(WebClientResponseException.class)
    @ResponseStatus(BAD_REQUEST)
    public ResponseEntity<ProblemDetail> handleWebClientResponseException(WebClientResponseException ex) {
        ProblemDetail pd = ProblemDetail.forStatusAndDetail(BAD_REQUEST, ex.getMessage());
        pd.setTitle("WebClientResponse Exception");
        pd.setType(Objects.requireNonNull(ex.getRequest()).getURI());
        return ResponseEntity.badRequest().body(pd);
    }

    @ExceptionHandler(RuntimeException.class)
    @ResponseStatus(BAD_REQUEST)
    public ResponseEntity<ProblemDetail> handleRuntimeException(RuntimeException ex) {
        ProblemDetail pd = ProblemDetail.forStatusAndDetail(BAD_REQUEST, ex.getMessage());
        pd.setTitle("Runtime Exception");
        return ResponseEntity.badRequest().body(pd);
    }
}
