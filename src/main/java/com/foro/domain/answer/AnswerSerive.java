package com.foro.domain.answer;

import com.foro.domain.exceptios.RequestException;
import com.foro.domain.topic.TopicRepository;
import com.foro.domain.user.UserRepository;
import org.antlr.v4.runtime.atn.SemanticContext;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.http.HttpStatus;
import org.springframework.http.HttpStatusCode;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;

@Service
public class AnswerSerive {

    @Autowired
    private AnswerRepository answerRepository;

    @Autowired
    private TopicRepository topicRepository;

    @Autowired
    private UserRepository userRepository;


    public Answer  createAnswer( AnswerRegisterDto answerRegisterDto){

        if(!topicRepository.existsById(answerRegisterDto.idTopic())){
            throw  new RequestException("doesn't exist topic with " + answerRegisterDto.idTopic() + "id", HttpStatus.BAD_REQUEST);
        }

        if(!userRepository.existsById(answerRegisterDto.idAuhtor())){
            throw  new RequestException("doesn't exist user with " + answerRegisterDto.idAuhtor() + "id", HttpStatus.BAD_REQUEST);
        }
        Answer answer = new Answer();
        answer.setMessage(answerRegisterDto.message());
        answer.setDateCreation(LocalDateTime.now());
        answer.setSolution(answerRegisterDto.solution());
        var author=userRepository.findById(answerRegisterDto.idAuhtor());
        author.ifPresent(answer::setAuthor);

        var topic = topicRepository.findById(answerRegisterDto.idTopic());
        topic.ifPresent(answer::setTopic);


        return  answerRepository.save(answer);

    }

    public  ResponseEntity updateAnswer( Long id, AnswerRegisterDto answerRegisterDto){

        Answer answer =getAnswer(id);
        if(!topicRepository.existsById(answerRegisterDto.idTopic())){
            throw  new RequestException("doesn't exist topic with " + answerRegisterDto.idTopic() + "id", HttpStatus.BAD_REQUEST);
        }

        if(!userRepository.existsById(answerRegisterDto.idAuhtor())){
            throw  new RequestException("doesn't exist user with " + answerRegisterDto.idAuhtor() + "id", HttpStatus.BAD_REQUEST);
        }

        answer.setMessage(answerRegisterDto.message());
        answer.setDateCreation(LocalDateTime.now());
        answer.setSolution(answerRegisterDto.solution());
        var author=userRepository.findById(answerRegisterDto.idAuhtor());
        author.ifPresent(answer::setAuthor);

        var topic = topicRepository.findById(answerRegisterDto.idTopic());
        topic.ifPresent(answer::setTopic);
        return  ResponseEntity.status(HttpStatusCode.valueOf(204)).build();
    }



    public Page<Answer> listAnswer(Pageable pageable){
        return answerRepository.findAll(pageable);
    }

    public Answer getAnswer(Long id){
        if(!answerRepository.existsById(id)){
            throw  new RequestException("doesn't exist answer with "+ id +"id",HttpStatus.BAD_REQUEST);
        }

        return answerRepository.findById(id).get();
    }

    public ResponseEntity<SemanticContext.Empty> deleteAnswer(Long id){
        if(!answerRepository.existsById(id)){
            throw  new RequestException("doesn't exist answer with "+ id +"id",HttpStatus.BAD_REQUEST);
        }

        answerRepository.deleteById(id);

        return   ResponseEntity.status(HttpStatusCode.valueOf(204)).build();

    }
}
