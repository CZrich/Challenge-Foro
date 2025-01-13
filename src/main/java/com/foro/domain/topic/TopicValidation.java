package com.foro.domain.topic;

import com.foro.domain.course.CourseRepository;
import com.foro.domain.user.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
public class TopicValidation {

    @Autowired
    private UserRepository userRepository;
    @Autowired
    private CourseRepository courseRepository;
    @Autowired
    private TopicRepository topicRepository;

    public Boolean validateAuthorAndCourse(Long idAuthor,Long idCourse){
     return  validateAuthor(idAuthor) && validateCourse(idCourse);

    }

    public Boolean validateAuthor(Long idAuthor){
        return userRepository.existsById(idAuthor);
    }

    public Boolean validateCourse(Long idCourse){
        return courseRepository.existsById(idCourse);
    }

    public  Boolean validateTopic(Long id){
        return  topicRepository.existsById(id);
    }

}
