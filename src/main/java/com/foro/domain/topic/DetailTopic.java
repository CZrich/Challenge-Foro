package com.foro.domain.topic;

import com.foro.domain.course.Course;
import com.foro.domain.user.UserDataDto;

import java.time.LocalDateTime;

public record DetailTopic(
        Long id,
        String title,
        String message,
        LocalDateTime localDateTime,
        UserDataDto userDataDto,
        Course course

) {
    public DetailTopic(Topic topic) {
        this(topic.getId(),topic.getTitle(),topic.getMessage(),topic.getDateCreation(),new UserDataDto(topic.getAuthor()),topic.getCourse());
    }
}
