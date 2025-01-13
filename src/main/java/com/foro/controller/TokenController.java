package com.foro.controller;


import com.foro.domain.user.LoginUserDto;
import com.foro.security.ResponseJWTToken;
import com.foro.security.TokenService;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/login")
public class TokenController {

   @Autowired
   private TokenService tokenService;

    @PostMapping
    public ResponseEntity<ResponseJWTToken>  authUser(@RequestBody @Valid LoginUserDto loginUserDto){
        var jwt= this.tokenService.login(loginUserDto);

        return  ResponseEntity.ok(new ResponseJWTToken(jwt));

    }
}
