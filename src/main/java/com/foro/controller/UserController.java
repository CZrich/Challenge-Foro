package com.foro.controller;

import com.foro.domain.user.SingupUserDto;
import com.foro.domain.user.User;
import com.foro.domain.user.UserService;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpMethod;
import org.springframework.http.HttpStatus;
import org.springframework.http.RequestEntity;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/singup")
public class UserController {

    @Autowired
    private UserService userService;

    @PostMapping
    public ResponseEntity<User> registerUser(@RequestBody @Valid SingupUserDto singupUserDto){

        var user=userService.createUser(singupUserDto);

        return ResponseEntity.status(HttpStatus.CREATED).body(user);


    }
}
