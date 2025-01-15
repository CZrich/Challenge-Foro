package com.foro.domain.topic;

import com.foro.domain.course.Course;
import com.foro.domain.user.UserDataDto;

import java.time.LocalDateTime;

public record TopicDetailDto(
        Long id,
        String title,
        String message,
        LocalDateTime localDateTime,
        UserDataDto userDataDto,
        Course course

) {
    public TopicDetailDto(Topic topic) {
        this(topic.getId(),topic.getTitle(),topic.getMessage(),topic.getDateCreation(),new UserDataDto(topic.getAuthor()),topic.getCourse());
    }
}
