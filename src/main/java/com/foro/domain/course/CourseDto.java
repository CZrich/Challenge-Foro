package com.foro.domain.course;

import jakarta.validation.constraints.NotBlank;

public record CourseDto(
        @NotBlank
        String name,
        @NotBlank
        String category
) {
}
