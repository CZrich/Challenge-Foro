package com.foro.domain.user;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;

import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.web.server.ResponseStatusException;

@Service
public class UserService {
    @Autowired
    private  UserRepository userRepository;

   @Autowired
    BCryptPasswordEncoder bCryptPasswordEncoder;

    public User createUser( SingupUserDto singupUserDto){
      var userOpt= userRepository.findByEmail(singupUserDto.email());
      if(userOpt.isPresent()){
          throw  new ResponseStatusException(HttpStatus.BAD_REQUEST, "Email already exists.");
      }
     User user= new User();
      user.setEmail(singupUserDto.email());
      user.setPassword(bCryptPasswordEncoder.encode(singupUserDto.password()));


      return   userRepository.save(user);
    }

    public User getUser(Long id){
        if(!userRepository.existsById(id)){
            throw  new RuntimeException("user doesn't exist with "+id+" id");
        }
        return  userRepository.findById(id).get();
    }
}


