package com.foro.domain.user;

import jakarta.persistence.*;
import lombok.*;
import org.springframework.security.crypto.password.PasswordEncoder;

@Entity(name="User")
@Table(name = "users")
@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
@EqualsAndHashCode(of ="id")
public class User {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    @Setter
    @Column(unique = true)
    private String email;
    @Setter
    private String password;

    public  Boolean isLogin(String password, PasswordEncoder passwordEncoder){
        return  passwordEncoder.matches(password,this.password);
    }



}
