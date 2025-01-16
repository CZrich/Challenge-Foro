package com.foro.domain.topic;

import com.foro.domain.course.CourseRepository;
import com.foro.domain.exceptios.RequestException;
import com.foro.domain.user.UserRepository;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import org.springframework.data.web.PageableDefault;
import org.springframework.http.HttpStatus;
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

        if(topicRepository.findByTitleAndMessage(topicDto.title(),topicDto.message()).isPresent()){
            throw new RequestException(" topic already exist",HttpStatus.BAD_REQUEST);
        }
         Topic topic =new Topic();


        var author=userRepository.findById(topicDto.idAuthor());
        if(author.isEmpty()){
            throw new RequestException("author doesn't exist with this id " + topicDto.idAuthor(),HttpStatus.BAD_REQUEST);
        }
        author.ifPresent(topic::setAuthor);
        var course= courseRepository.findById(topicDto.idCourse());
        if(course.isEmpty()){
            throw  new RequestException("course  doesn't exist with this id "+topicDto.idCourse(),HttpStatus.BAD_REQUEST);
        }

         course.ifPresent(topic::setCourse);
         topic.setTitle(topicDto.title());
         topic.setMessage(topicDto.message());
         topic.setStatus(true);
         topic.setDateCreation(topic.getTime());



         return  topicRepository.save(topic);
    }

    public Page<TopicDetailDto> listTopics( Pageable pageable){
        return  topicRepository.findAll(pageable).map(TopicDetailDto::new);
    }

    public  Page<TopicDetailDto>listTopicsByCourseAndYear(Pageable pageable, SearchTopicDto searchTopicDto){

        return topicRepository.listTopicsForCourseAndYear(pageable,searchTopicDto.year(),searchTopicDto.nameCourse())
                .map(TopicDetailDto::new);
    }

    public  Topic getTopic(Long id){
        var topic= topicRepository.findById(id);
        if(topic.isEmpty()){
            throw new RequestException("doesn't topic exist with "+id +" id", HttpStatus.BAD_REQUEST);
        }
        return topic.get();
    }

    public ResponseEntity updateTopic(Long id,RegisterTopicDto registerTopicDto){
        var topic=getTopic(id);
        var author=userRepository.findById(registerTopicDto.idAuthor());
        if(author.isEmpty()){
            throw new RequestException("author doesn't exist with this id " + registerTopicDto.idAuthor(),HttpStatus.BAD_REQUEST);
        }
        author.ifPresent(topic::setAuthor);
        var course= courseRepository.findById(registerTopicDto.idCourse());
        if(course.isEmpty()){
            throw  new RequestException("course  doesn't exist with this id "+registerTopicDto.idCourse(),HttpStatus.BAD_REQUEST);
        }

        course.ifPresent(topic::setCourse);
        topic.setTitle(registerTopicDto.title());
        topic.setMessage(registerTopicDto.message());
        topic.setStatus(true);
        topic.setDateCreation(topic.getTime());

        return  ResponseEntity.status(HttpStatusCode.valueOf(204)).build();
    }

    public  ResponseEntity deleteTopic(Long id){
        if(!topicRepository.existsById(id)){
            throw new RequestException ("topic doesn't exist with this id "+id,HttpStatus.BAD_REQUEST);
        }
        topicRepository.deleteById(id);
        return ResponseEntity.status(HttpStatusCode.valueOf(204)).build();
    }






}
