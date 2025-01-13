package com.foro.domain.topic;

import com.foro.domain.course.CourseRepository;
import com.foro.domain.user.UserRepository;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import org.springframework.http.HttpStatusCode;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;


@Service
public class TopicService {

    @Autowired
    private  TopicRepository topicRepository;
    @Autowired
    private UserRepository userRepository;
    @Autowired
    private CourseRepository courseRepository;




    public Topic createTopic( RegisterTopicDto topicDto){
         Topic topic =new Topic();


        var author=userRepository.findById(topicDto.idAuthor());
        if(author.isEmpty()){
            throw new RuntimeException("author doesn't exist with this id");
        }
        author.ifPresent(topic::setAuthor);
        var course= courseRepository.findById(topicDto.idCourse());
        if(course.isEmpty()){
            throw  new RuntimeException("course  doesn't exist with this id");
        }

         course.ifPresent(topic::setCourse);
         topic.setTitle(topicDto.title());
         topic.setMessage(topicDto.message());
         topic.setStatus(true);
         topic.setDateCreation(topic.getTime());



         return  topicRepository.save(topic);
    }

    public Page<DetailTopic> listTopics( Pageable pageable){
        return  topicRepository.findAll(pageable).map(DetailTopic::new);
    }

    public  Page<DetailTopic>listTopicsByCourseAndYear(Pageable pageable,SearchTopicDto searchTopicDto){

        return topicRepository.listTopicsForCourseAndYear(pageable,searchTopicDto.year(),searchTopicDto.nameCourse())
                .map(DetailTopic::new);
    }

    public  Topic getTopic(Long id){
        var topic= topicRepository.findById(id);
        if(topic.isEmpty()){
            throw new RuntimeException("doesn't topic exist with "+id +" id");
        }
        return topic.get();
    }

    public ResponseEntity updateTopic(Long id,RegisterTopicDto registerTopicDto){
        var topic=getTopic(id);
        var author=userRepository.findById(registerTopicDto.idAuthor());
        if(author.isEmpty()){
            throw new RuntimeException("author doesn't exist with this id");
        }
        author.ifPresent(topic::setAuthor);
        var course= courseRepository.findById(registerTopicDto.idCourse());
        if(course.isEmpty()){
            throw  new RuntimeException("course  doesn't exist with this id");
        }

        course.ifPresent(topic::setCourse);
        topic.setTitle(registerTopicDto.title());
        topic.setMessage(registerTopicDto.message());
        topic.setStatus(true);
        topic.setDateCreation(topic.getTime());

        return  ResponseEntity.status(HttpStatusCode.valueOf(204)).build();
    }

    public  ResponseEntity deleteTopic(Long id){

        topicRepository.deleteById(id);
        return ResponseEntity.status(HttpStatusCode.valueOf(204)).build();
    }





    public Boolean tipicIsExist(String title, String message){
        var topic= topicRepository.findByTitleAndMessage(title,message);
        return topic.isPresent();
    }
}
