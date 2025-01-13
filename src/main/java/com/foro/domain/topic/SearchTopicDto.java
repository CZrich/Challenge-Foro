package com.foro.domain.topic;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;

public record SearchTopicDto(
        @NotBlank
        String nameCourse,
        @NotNull
        Integer year
) {
}
