package com.foro.controller;

import com.foro.domain.topic.*;

import jakarta.transaction.Transactional;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.web.PageableDefault;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;


@RestController
@RequestMapping("/topic")
public class TopicController {

    @Autowired
    private TopicService topicService;
    @Autowired
    private TopicValidation topicValidation;


    @PostMapping
    public ResponseEntity create(@RequestBody @Valid RegisterTopicDto topicDto){

        if(topicService.tipicIsExist(topicDto.title(),topicDto.message())){
            return  ResponseEntity.status(HttpStatus.BAD_REQUEST).body(" topic already exist");
        }
        var topic=topicService.createTopic(topicDto);
        var topicDetail= new DetailTopic(topic);
        return ResponseEntity.status(HttpStatus.CREATED).body(topicDetail);
    }

    @GetMapping
    public Page<DetailTopic> getTopics(@PageableDefault(size = 10,sort = {"dateCreation"})Pageable pageable){
        return  topicService.listTopics(pageable);
    }
    @GetMapping("/data")
    public Page<DetailTopic> getTopicsByYearAndCourse(@PageableDefault(size = 10,sort = {"dateCreation"})Pageable pageable,@RequestBody @Valid  SearchTopicDto searchTopicDto){
        return topicService.listTopicsByCourseAndYear(pageable,searchTopicDto);
    }
    @GetMapping("/{id}")
    public  DetailTopic showTopicDetail(@PathVariable  Long id){

        return  new DetailTopic( topicService.getTopic(id));
    }

    @PutMapping("/{id}")
    @Transactional
    public  ResponseEntity updateTopic(@PathVariable Long id,@RequestBody RegisterTopicDto registerTopicDto){
        if(!topicValidation.validateCourse(registerTopicDto.idCourse())){
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body("doesn't exist course with  this id "+registerTopicDto.idCourse());
        }
        if(!topicValidation.validateCourse(registerTopicDto.idAuthor())){
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body("doesn't exist author with  this id "+registerTopicDto.idAuthor());
        }
        if(!topicValidation.validateTopic(id)){
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body("topic doesn't exist with this id "+id);
        }
        return topicService.updateTopic(id,registerTopicDto);

    }

    @DeleteMapping("/{id}")
    public  ResponseEntity deleteTopic(@PathVariable Long id){
        if(!topicValidation.validateTopic(id)){
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body("topic doesn't exist with this id "+id);
        }
        return topicService.deleteTopic(id);
    }





}
