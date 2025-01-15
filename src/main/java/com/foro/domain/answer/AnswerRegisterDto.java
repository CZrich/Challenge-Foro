package com.foro.domain.answer;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;

public record AnswerRegisterDto(
        @NotBlank
        String message,
        @NotNull
        Long idAuhtor,
        @NotNull
        Long idTopic,
        @NotNull
        Boolean solution


) {
}
