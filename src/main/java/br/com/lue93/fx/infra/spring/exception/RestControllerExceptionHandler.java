package br.com.lue93.fx.infra.spring.exception;

import br.com.lue93.fx.application.services.rate.WiseRateServiceException;
import feign.RetryableException;
import org.springframework.http.ResponseEntity;
import org.springframework.http.converter.HttpMessageNotReadableException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

@RestControllerAdvice
public class RestControllerExceptionHandler {

    @ExceptionHandler(RetryableException.class)
    public ResponseEntity handle500() {
        return ResponseEntity.internalServerError().build();
    }

    @ExceptionHandler(WiseRateServiceException.class)
    public ResponseEntity handle503() {
        return ResponseEntity.internalServerError().build();
    }

    @ExceptionHandler(HttpMessageNotReadableException.class)
    public ResponseEntity handle400() {
        return ResponseEntity.badRequest().build();
    }

}
