package com.foro.domain.user;


import com.foro.domain.exceptios.RequestException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;

import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;


@Service
public class UserService {
    @Autowired
    private  UserRepository userRepository;

   @Autowired
    BCryptPasswordEncoder bCryptPasswordEncoder;

    public User createUser( SingupUserDto singupUserDto){
      var userOpt= userRepository.findByEmail(singupUserDto.email());
      if(userOpt.isPresent()){
          throw  new RequestException("Email already exists.",HttpStatus.BAD_REQUEST );
      }
     User user= new User();
      user.setEmail(singupUserDto.email());
      user.setPassword(bCryptPasswordEncoder.encode(singupUserDto.password()));


      return   userRepository.save(user);
    }

    public User getUser(Long id){
        if(!userRepository.existsById(id)){
            throw  new RequestException("user doesn't exist with "+id+" id",HttpStatus.BAD_REQUEST);
        }
        return  userRepository.findById(id).get();
    }
}


