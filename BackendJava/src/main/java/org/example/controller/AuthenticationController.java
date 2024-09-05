package org.example.controller;

import org.example.dto.LoginResponseDTO;
import org.example.dto.UserDTO;
import org.example.service.AuthenticationService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.HashMap;
import java.util.Map;

@RestController
@RequestMapping("/api/auth")
public class AuthenticationController {

    @Autowired
    private final AuthenticationService authenticationService;

    public AuthenticationController(AuthenticationService authenticationService) {
        this.authenticationService = authenticationService;
    }

    @PostMapping("/login")
    public ResponseEntity<LoginResponseDTO> login(@RequestBody UserDTO userDTO) {
        try {
            LoginResponseDTO response = authenticationService.authenticate(userDTO);
            return ResponseEntity.ok(response);
        } catch (Exception e) {
            // Ajuste para incluir o redirectEndpoint padrão
            String redirectEndpoint = "/diet"; // Definindo um endpoint padrão em caso de falha
            LoginResponseDTO response = new LoginResponseDTO(false, null, e.getMessage(), null, -1, redirectEndpoint);
            return ResponseEntity.status(HttpStatus.UNAUTHORIZED).body(response);
        }
    }

    @GetMapping("/validate-token")
    public ResponseEntity<Map<String, Object>> validateToken(@RequestParam String token) {
        boolean isValid = authenticationService.validateToken(token) != null;
        Map<String, Object> response = new HashMap<>();
        response.put("isValid", isValid);
        return ResponseEntity.ok(response);
    }
}