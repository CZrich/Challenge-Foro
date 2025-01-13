package com.foro.domain.topic;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;

public record RegisterTopicDto(
        @NotBlank
        String title,
        @NotBlank
        String message,
        @NotNull
        Long idAuthor,
        @NotNull
        Long idCourse


) {
}
