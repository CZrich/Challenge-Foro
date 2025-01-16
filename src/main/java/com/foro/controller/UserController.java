package com.foro.controller;

import com.foro.domain.user.UserDataDto;
import com.foro.domain.user.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.web.PageableDefault;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/user")
public class UserController {


    @Autowired
    private UserService userService;

    @GetMapping
    public Page<UserDataDto> getUsers(@PageableDefault(size = 10,sort={"email"})Pageable pageable){

        return userService.getAllUser(pageable).map(UserDataDto::new);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity deleteUser(@PathVariable Long id){
        return  userService.deleteUser(id);
    }


}
