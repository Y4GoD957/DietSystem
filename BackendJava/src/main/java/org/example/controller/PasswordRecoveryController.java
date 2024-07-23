package org.example.controller;

import org.example.service.PasswordRecoveryService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.Map;

@RestController
@RequestMapping("/api/users")
public class PasswordRecoveryController {

    @Autowired
    private PasswordRecoveryService passwordRecoveryService;

    @PostMapping("/recover-password")
    public ResponseEntity<String> recoverPassword(@RequestBody Map<String, String> request) {
        String email = request.get("email");
        passwordRecoveryService.sendRecoveryEmail(email);
        return ResponseEntity.status(HttpStatus.OK).body("E-mail de recuperação enviado com sucesso");
    }

    @PostMapping("/verify-code")
    public ResponseEntity<String> verifyCode(@RequestBody Map<String, String> request) {
        String email = request.get("email");
        String code = request.get("code");
        boolean isCodeValid = passwordRecoveryService.verifyCode(email, code);

        if (isCodeValid) {
            return ResponseEntity.status(HttpStatus.OK).body("Código verificado com sucesso");
        } else {
            return ResponseEntity.status(HttpStatus.UNAUTHORIZED).body("Código inválido ou expirado");
        }
    }

    @PostMapping("/reset-password")
    public ResponseEntity<String> resetPassword(@RequestBody Map<String, String> request) {
        String email = request.get("email");
        String newPassword = request.get("newPassword");
        passwordRecoveryService.resetPassword(email, newPassword);
        return ResponseEntity.status(HttpStatus.OK).body("Senha alterada com sucesso");
    }
}