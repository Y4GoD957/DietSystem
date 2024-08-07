package org.example.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.Customizer;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configurers.AbstractHttpConfigurer;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.SecurityFilterChain;

@Configuration
@EnableWebSecurity
public class SecurityConfig {

    @Bean
    public PasswordEncoder passwordEncoder() {
        return new BCryptPasswordEncoder();
    }

    @Bean
    public SecurityFilterChain securityFilterChain(HttpSecurity http) throws Exception {
        http
                .csrf(AbstractHttpConfigurer::disable) // Desabilita CSRF para APIs
                .authorizeHttpRequests(authorizeRequests -> authorizeRequests
                        .requestMatchers("/api/auth/login",
                                "/api/users/register",
                                "/api/users/{user_id}",
                                "/api/users/all",
                                "/api/users/contact",
                                "/api/users/contact/{email}").permitAll() // Permite acesso sem autenticação
                        .requestMatchers("/api/usage/calculator",
                                "/api/users/contact").authenticated() // Requer autenticação para endpoints específicos
                        .anyRequest().authenticated()) // Requer autenticação para outras requisições
                .httpBasic(Customizer.withDefaults()); // Configura autenticação básica

        return http.build();
    }
}

