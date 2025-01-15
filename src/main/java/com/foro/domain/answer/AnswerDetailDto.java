package com.foro.domain.answer;

import com.foro.domain.topic.TopicDetailDto;
import com.foro.domain.user.UserDataDto;

import java.time.LocalDateTime;

public record AnswerDetailDto(

        Long id,
        String message,
        LocalDateTime date,
        String user,
        String topicTitle


) {
    public AnswerDetailDto(Answer answer){
        this(answer.getId(), answer.getMessage(),answer.getDateCreation(),answer.getAuthor().getEmail(),answer.getTopic().getTitle());
    }

}
