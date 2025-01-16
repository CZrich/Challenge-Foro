package com.foro.domain.course;

public record CourseDetailDto(
        Long id,
        String name,
        String category
) {
    public CourseDetailDto( Course course){
        this(course.getId(), course.getName(), course.getCategory().toString());
    }

}
