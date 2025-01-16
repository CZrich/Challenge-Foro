package com.foro.controller;

import com.foro.domain.answer.AnswerDetailDto;
import com.foro.domain.answer.AnswerRegisterDto;
import com.foro.domain.answer.AnswerSerive;
import io.swagger.v3.oas.annotations.security.SecurityRequirement;
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
@RequestMapping("/answer")

public class AnswerCrontroller {


    @Autowired
    AnswerSerive answerSerive;

    @PostMapping
    public ResponseEntity<AnswerDetailDto> makeAnswer(@RequestBody @Valid AnswerRegisterDto answerRegisterDto){

       var answer=  answerSerive.createAnswer(answerRegisterDto);

       return new ResponseEntity<>(new AnswerDetailDto(answer),HttpStatus.CREATED);
    }

    @GetMapping
    public Page<AnswerDetailDto> getAllAnswers( @PageableDefault(size= 10, sort={"dateCreation"}) Pageable pageable){
        return  answerSerive.listAnswer(pageable).map(AnswerDetailDto::new);
    }

    @GetMapping("/{id}")
    public  ResponseEntity<AnswerDetailDto> getAnswer(@PathVariable Long id){

       var answer= answerSerive.getAnswer(id);

       return ResponseEntity.ok(new AnswerDetailDto(answer));
    }

    @PutMapping("/{id}")
    @Transactional
    public  ResponseEntity updateAnswer(@PathVariable Long id, @RequestBody AnswerRegisterDto answerRegisterDto){

        return   answerSerive.updateAnswer(id,answerRegisterDto);

    }

    @DeleteMapping("/{id}")
    public  ResponseEntity deleteAnswer(@PathVariable Long id){

        return   answerSerive.deleteAnswer(id);

    }



}
