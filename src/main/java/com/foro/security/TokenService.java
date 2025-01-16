package com.foro.security;

import com.foro.domain.exceptios.RequestException;
import com.foro.domain.user.LoginUserDto;
import com.foro.domain.user.UserRepository;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.oauth2.jwt.JwtClaimsSet;
import org.springframework.security.oauth2.jwt.JwtEncoder;
import org.springframework.security.oauth2.jwt.JwtEncoderParameters;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.server.ResponseStatusException;

import java.time.Instant;

@Service
public class TokenService {

    @Autowired
     private JwtEncoder jwtEncoder;

    @Autowired
    BCryptPasswordEncoder bCryptPasswordEncoder;

    @Autowired
    UserRepository userRepository;

    public String login( LoginUserDto loginUserDto){
        var userOpt= userRepository.findByEmail(loginUserDto.email());
        if(userOpt.isEmpty()){
            throw  new RequestException("email not found",HttpStatus.BAD_REQUEST);
        }
        var loged=userOpt.get().isLogin(loginUserDto.password(),bCryptPasswordEncoder);
        if(!loged){
            throw  new RequestException("incorrect  email/passsword",HttpStatus.BAD_REQUEST);
        }
        var now= Instant.now();
        var expireIn=3600L;
        var claims= JwtClaimsSet.builder()
                .issuer("foro-api")
                .issuedAt(now)
                .expiresAt(now.plusSeconds(expireIn))
                .subject(loginUserDto.email())
                .build();

        return jwtEncoder.encode(JwtEncoderParameters.from(claims)).getTokenValue();
    }


    public String generatePasswordResetToken(String email) {
        var userOpt = userRepository.findByEmail(email);
        if (userOpt.isEmpty()) {
            throw new ResponseStatusException(HttpStatus.BAD_REQUEST, "Email not found");
        }

        var now = Instant.now();
        var expireIn = 3600L; // Token válido por 1 hora
        var claims = JwtClaimsSet.builder()
                .issuer("foro-api")
                .issuedAt(now)
                .expiresAt(now.plusSeconds(expireIn))
                .subject(email) // Email del usuario
                .claim("purpose", "password-reset") // Propósito del token
                .build();

        return jwtEncoder.encode(JwtEncoderParameters.from(claims)).getTokenValue();
    }
}
