package com.foro.domain.exceptios;

import jakarta.validation.constraints.NotNull;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

@RestControllerAdvice
public class ErrorHandler {

    @ExceptionHandler(RequestException.class)
    public ResponseEntity<ErrorDto> badResquest(RequestException resquestException){
        return new ResponseEntity<>(new ErrorDto(resquestException.getMessage(),resquestException.getHttpStatus()),resquestException.getHttpStatus());
    }

    @ExceptionHandler(MethodArgumentNotValidException.class)
    public ResponseEntity tratarError400( MethodArgumentNotValidException e){
        var errores = e.getFieldErrors().stream().map(ErrorDataValidation::new).toList();
        return  ResponseEntity.badRequest().body(errores);
    }


}
