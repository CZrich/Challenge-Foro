package com.foro.domain.user;


import com.foro.domain.email.EmailService;
import com.foro.domain.exceptios.RequestException;
import com.foro.security.TokenService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.http.HttpStatus;

import org.springframework.http.HttpStatusCode;
import org.springframework.http.ResponseEntity;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.oauth2.jwt.JwtDecoder;
import org.springframework.stereotype.Service;


@Service
public class UserService {
    @Autowired
    private  UserRepository userRepository;

   @Autowired
    BCryptPasswordEncoder bCryptPasswordEncoder;

   @Autowired
   private EmailService emailService;

   @Autowired
   private TokenService tokenService;
   @Autowired
    private JwtDecoder jwtDecoder;

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


    public User updateUser(Long id, UpdateUserDto updateUserDto) {

        User user = userRepository.findById(id)
                .orElseThrow(() -> new RequestException("User not found with id " + id, HttpStatus.NOT_FOUND));

        if (updateUserDto.email() != null && !updateUserDto.email().equals(user.getEmail())) {
            if (userRepository.findByEmail(updateUserDto.email()).isPresent()) {
                throw new RequestException("Email already in use.", HttpStatus.BAD_REQUEST);
            }
            user.setEmail(updateUserDto.email());
        }


        if (updateUserDto.password() != null) {
            user.setPassword(bCryptPasswordEncoder.encode(updateUserDto.password()));
        }

        return userRepository.save(user);
    }



    public User getUser(Long id){
        if(!userRepository.existsById(id)){
            throw  new RequestException("user doesn't exist with "+id+" id",HttpStatus.BAD_REQUEST);
        }
        return  userRepository.findById(id).get();
    }

    public Page<User> getAllUser(Pageable pageable){

        return  userRepository.findAll(pageable);
    }

    public ResponseEntity deleteUser( Long id){
        if(!userRepository.existsById(id)){
            throw  new RequestException("user doesn't exist with "+id+" id",HttpStatus.BAD_REQUEST);
        }
         userRepository.deleteById(id);
        return ResponseEntity.status(HttpStatusCode.valueOf(204)).build();
    }




    public void resetPassword(String token, String newPassword) {
        // Decodificar y validar el token
        var claims = jwtDecoder.decode(token);

        // Verificar que el token tiene el propósito correcto
        if (!"password-reset".equals(claims.getClaimAsString("purpose"))) {
            throw new RequestException("Invalid token purpose", HttpStatus.BAD_REQUEST);
        }

        // Obtener el email del token
        String email = claims.getSubject();

        // Buscar el usuario por email
        User user = userRepository.findByEmail(email)
                .orElseThrow(() -> new RequestException("User not found with email " + email, HttpStatus.NOT_FOUND));

        // Actualizar la contraseña
        user.setPassword(bCryptPasswordEncoder.encode(newPassword));
        userRepository.save(user);
    }




    public void requestPasswordReset(String email) {
        // Verificar si el usuario existe
        User user = userRepository.findByEmail(email)
                .orElseThrow(() -> new RequestException("User not found with email " + email, HttpStatus.NOT_FOUND));

        // Generar el token JWT
        String resetToken = tokenService.generatePasswordResetToken(user.getEmail());

        // Enviar el token por correo electrónico
        emailService.sendPasswordResetEmail(user.getEmail(), resetToken);
    }
/*
    private String generateResetToken(User user) {
        var now = Instant.now();
        var expireIn = 3600L; // Token válido por 1 hora
        var claims = JwtClaimsSet.builder()
                .issuer("foro-api")
                .issuedAt(now)
                .expiresAt(now.plusSeconds(expireIn))
                .subject(user.getEmail())
                .claim("purpose", "password-reset")
                .build();

        return jwtEncoder.encode(JwtEncoderParameters.from(claims)).getTokenValue();
    }
*/


}


