package com.foro.domain.exceptios;

import org.springframework.http.HttpStatus;

public record ErrorDto(
         String message,
         HttpStatus httpStatus

) {
    public ErrorDto(String message ,HttpStatus httpStatus){
        this.message=message;
        this.httpStatus=httpStatus;
    }
}
