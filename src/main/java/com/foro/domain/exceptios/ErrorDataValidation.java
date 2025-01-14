package com.foro.domain.exceptios;

import org.springframework.validation.FieldError;

public record ErrorDataValidation(String field,String error) {

        public ErrorDataValidation(FieldError error) {
            this(error.getField(), error.getDefaultMessage());
        }

}
