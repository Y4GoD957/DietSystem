package org.example.service;

import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;
import io.jsonwebtoken.security.Keys;
import org.example.dto.UserDTO;
import org.example.entity.User;
import org.example.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.security.Key;
import java.util.Date;

@Service
public class AuthenticationService {

    @Autowired
    private UserRepository userRepository;

    @Autowired
    private PasswordEncoder passwordEncoder;

    private final Key key = Keys.secretKeyFor(SignatureAlgorithm.HS512); // Gera uma chave segura para HS512

    @Value("${jwt.secret}")
    private String SECRET_KEY;

    public String authenticate(UserDTO userDTO) throws Exception {
        User user = userRepository.findByEmail(userDTO.getEmail())
                .orElseThrow(() -> new Exception("Usuário não encontrado"));

        if (passwordEncoder.matches(userDTO.getPassword(), user.getPassword())) {
            return generateToken(user);
        } else {
            throw new Exception("Credenciais inválidas");
        }
    }

    private String generateToken(User user) {
        long nowMillis = System.currentTimeMillis();
        Date now = new Date(nowMillis);

        return Jwts.builder()
                .setSubject(user.getEmail())
                .setIssuedAt(now)
                .setExpiration(new Date(nowMillis + 3600000)) // Token válido por 1 hora
                .signWith(key, SignatureAlgorithm.HS512) // Assina o token com a chave gerada
                .compact();
    }
}
