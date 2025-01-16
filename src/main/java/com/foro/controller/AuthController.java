package com.foro.controller;

import com.foro.domain.email.EmailService;
import com.foro.domain.user.SingupUserDto;
import com.foro.domain.user.UserDataDto;
import com.foro.domain.user.UserRepository;
import com.foro.domain.user.UserService;
import com.foro.security.TokenService;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.oauth2.jwt.JwtDecoder;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.server.ResponseStatusException;

@RestController
@RequestMapping("/auth")
public class AuthController {

    @Autowired
    private UserService userService;

    @PostMapping("/signup")
    public ResponseEntity<UserDataDto> registerUser(@RequestBody @Valid SingupUserDto singupUserDto){

        var user=userService.createUser(singupUserDto);

        return ResponseEntity.status(HttpStatus.CREATED).body(new UserDataDto(user));


    }

    @PostMapping("/password-reset/request")
    public ResponseEntity<Void> requestPasswordReset(@RequestParam String email) {

        userService.requestPasswordReset(email);
        return ResponseEntity.ok().build();
    }

    @PostMapping("/password-reset")
    public ResponseEntity<Void> resetPassword(@RequestParam String token, @RequestParam String newPassword) {
        userService.resetPassword(token,newPassword);

        return ResponseEntity.ok().build();
    }
}
