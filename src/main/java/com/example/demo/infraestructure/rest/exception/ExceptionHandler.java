package com.example.demo.infraestructure.rest.exception;

import com.example.demo.domain.PriceNotFoundException;
import org.springframework.http.HttpStatus;
import org.springframework.http.ProblemDetail;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;

@ControllerAdvice
public class ExceptionHandler {

    @org.springframework.web.bind.annotation.ExceptionHandler(PriceNotFoundException.class)
    public ResponseEntity<ProblemDetail> handlePriceNotFoundException(PriceNotFoundException ex) {
        var problemDetail = ProblemDetail.forStatusAndDetail(HttpStatus.NOT_FOUND, ex.getMessage());
        problemDetail.setTitle("Price not found");

        return ResponseEntity.status(HttpStatus.NOT_FOUND).body(problemDetail);
    }

}
