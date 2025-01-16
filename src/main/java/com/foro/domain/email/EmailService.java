package com.foro.domain.email;

import org.springframework.stereotype.Service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.mail.javamail.JavaMailSender;



@Service
public class EmailService {

    @Autowired
    private JavaMailSender mailSender;

    public void sendPasswordResetEmail(String toEmail, String resetToken) {
        String resetLink = "http://localhost:2025/auth/password-reset?token=" + resetToken;

        // Crear el mensaje de correo
        SimpleMailMessage message = new SimpleMailMessage();
        message.setTo(toEmail);
        message.setSubject("Reset Password");
        message.setText("Hello,\n\nYou have requested to reset your password. Please click the following link to reset it:\n\n" +
                 resetLink + "\n\nIf you did not request this change, please ignore this message.\n\nRegards,\nTeam Forum");

        // Enviar el correo
        mailSender.send(message);
    }
}
