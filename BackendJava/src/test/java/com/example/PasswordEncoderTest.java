package com.example;

import org.junit.jupiter.api.Test;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;

import static org.junit.jupiter.api.Assertions.assertTrue;
import static org.junit.jupiter.api.Assertions.assertFalse;

public class PasswordEncoderTest {

    private final PasswordEncoder passwordEncoder = new BCryptPasswordEncoder();

    @Test
    public void testPasswordHashing() {
        String rawPassword = "123123123"; // A senha original para comparação
        String existingHash = "$2a$10$SM.yaAru4jPBjRDg7V5d1.ApBgM.V0RhsTXrnwxElewTQ5Ta10DUm"; // Corrija o formato do hash, deve começar com $2a$ para bcrypt

        // Testa se a senha bruta corresponde ao hash existente
        boolean matches = passwordEncoder.matches(rawPassword, existingHash);

        System.out.println("Senha original: " + rawPassword);
        System.out.println("Hash existente: " + existingHash);
        System.out.println("Senha corresponde ao hash? " + matches);

        // Verifica se a senha original corresponde ao hash fornecido
        assertTrue(matches, "A senha original deve corresponder ao hash existente");

        // Testa se uma senha incorreta NÃO corresponde ao hash existente
        String wrongPassword = "senhaErrada123";
        boolean wrongMatches = passwordEncoder.matches(wrongPassword, existingHash);

        System.out.println("Senha incorreta: " + wrongPassword);
        System.out.println("Senha incorreta corresponde ao hash? " + wrongMatches);

        // Verifica se uma senha errada não corresponde ao hash
        assertFalse(wrongMatches, "Uma senha incorreta não deve corresponder ao hash");
    }
}