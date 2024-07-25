package org.example.service;

import org.example.entity.User;
import org.example.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.security.SecureRandom;
import java.time.LocalDateTime;
import java.util.Optional;

@Service
public class PasswordRecoveryService {

    @Autowired
    private UserRepository userRepository;

    @Autowired
    private EmailService emailService;

    @Autowired
    private PasswordEncoder passwordEncoder;

    private static final SecureRandom random = new SecureRandom();
    private static final int CODE_LENGTH = 6;
    private static final int CODE_EXPIRATION_MINUTES = 30;

    public void sendRecoveryEmail(String email) {
        Optional<User> optionalUser = userRepository.findByEmail(email);

        if (optionalUser.isPresent()) {
            User user = optionalUser.get();
            String verificationCode = generateCode();
            LocalDateTime expirationTime = LocalDateTime.now().plusMinutes(CODE_EXPIRATION_MINUTES);

            user.setVerificationCode(verificationCode);
            user.setVerificationCodeExpiration(expirationTime);
            userRepository.save(user);

            String subject = "Código de Recuperação de Senha";
            String body = "Seu código de recuperação de senha é: " + verificationCode;
            emailService.sendSimpleEmail(email, subject, body);
        }
    }

    public boolean verifyCode(String email, String code) {
        Optional<User> optionalUser = userRepository.findByEmail(email);

        if (optionalUser.isPresent()) {
            User user = optionalUser.get();
            return user.getVerificationCode().equals(code) && user.getVerificationCodeExpiration().isAfter(LocalDateTime.now());
        }
        return false;
    }

    public void resetPassword(String email, String newPassword) {
        Optional<User> optionalUser = userRepository.findByEmail(email);

        if (optionalUser.isPresent()) {
            User user = optionalUser.get();
            user.setPassword(passwordEncoder.encode(newPassword));
            user.setVerificationCode(null);
            user.setVerificationCodeExpiration(null);
            userRepository.save(user);
        }
    }

    private String generateCode() {
        int code = random.nextInt((int) Math.pow(10, CODE_LENGTH));
        return String.format("%06d", code);
    }
}