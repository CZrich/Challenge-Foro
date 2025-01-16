package com.foro.domain.course;

import jakarta.validation.constraints.NotBlank;

public record CourseRegisterDto(
        @NotBlank
        String name,
        @NotBlank
        String category
) {

        public CourseRegisterDto(Course course){
                this(course.getName(),course.getCategory().toString());
        }
}
