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


    @PostMapping
    public ResponseEntity create(@RequestBody @Valid RegisterTopicDto topicDto){


        var topic=topicService.createTopic(topicDto);
        var topicDetail= new TopicDetailDto(topic);
        return ResponseEntity.status(HttpStatus.CREATED).body(topicDetail);
    }

    @GetMapping
    public Page<TopicDetailDto> getTopics( @PageableDefault(page = 0,size = 10,sort = {"dateCreation"})Pageable pageable){
        return  topicService.listTopics(pageable);
    }
    @GetMapping("/data")
    public Page<TopicDetailDto> getTopicsByYearAndCourse(@PageableDefault(size = 10,sort = {"dateCreation"})Pageable pageable, @RequestBody @Valid  SearchTopicDto searchTopicDto){
        return topicService.listTopicsByCourseAndYear(pageable,searchTopicDto);
    }
    @GetMapping("/{id}")
    public TopicDetailDto showTopicDetail(@PathVariable  Long id){

        return  new TopicDetailDto( topicService.getTopic(id));
    }

    @PutMapping("/{id}")
    @Transactional
    public  ResponseEntity updateTopic(@PathVariable Long id,@RequestBody @Valid RegisterTopicDto registerTopicDto){

        return topicService.updateTopic(id,registerTopicDto);

    }

    @DeleteMapping("/{id}")
    public  ResponseEntity deleteTopic(@PathVariable Long id){

        return topicService.deleteTopic(id);
    }





}
