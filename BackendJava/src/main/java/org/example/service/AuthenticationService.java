package org.example.service;

import io.jsonwebtoken.Claims;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;
import io.jsonwebtoken.security.Keys;
import org.example.dto.LoginResponseDTO;
import org.example.dto.UserDTO;
import org.example.entity.User;
import org.example.repository.DietRepository;
import org.example.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.security.Key;
import java.util.Date;

@Service
public class AuthenticationService {

    @Autowired
    private final UserRepository userRepository;

    @Autowired
    private final DietRepository dietRepository;

    @Autowired
    private final PasswordEncoder passwordEncoder;

    private final Key key = Keys.secretKeyFor(SignatureAlgorithm.HS512);

    public AuthenticationService(UserRepository userRepository, DietRepository dietRepository, PasswordEncoder passwordEncoder) {
        this.userRepository = userRepository;
        this.dietRepository = dietRepository;
        this.passwordEncoder = passwordEncoder;
    }

    // Método de autenticação modificado para redirecionar com base nos dados de dieta
    public LoginResponseDTO authenticate(UserDTO userDTO) throws Exception {
        User user = userRepository.findByEmail(userDTO.getEmail())
                .orElseThrow(() -> new Exception("Usuário não encontrado"));

        if (passwordEncoder.matches(userDTO.getPassword(), user.getPassword())) {
            String token = generateToken(user);
            boolean hasDietData = dietRepository.existsByUserId(user.getUserId());

            String redirectEndpoint = hasDietData ? "/Services" : "/diet";

            return new LoginResponseDTO(
                    true,
                    token,
                    "Autenticação bem-sucedida",
                    user.getEmail(),
                    user.getUserId(),
                    redirectEndpoint
            );
        } else {
            throw new Exception("Credenciais inválidas");
        }
    }

    private String generateToken(User user) {
        long nowMillis = System.currentTimeMillis();
        Date now = new Date(nowMillis);

        return Jwts.builder()
                .setSubject(user.getEmail())
                .claim("user_id", user.getUserId())
                .setIssuedAt(now)
                .setExpiration(new Date(nowMillis + 3600000)) // Token válido por 1 hora
                .signWith(key)
                .compact();
    }

    public Claims validateToken(String token) {
        try {
            return Jwts.parserBuilder()
                    .setSigningKey(key)
                    .build()
                    .parseClaimsJws(token)
                    .getBody();
        } catch (Exception e) {
            return null; // Retorna null se o token for inválido ou expirar
        }
    }
}
