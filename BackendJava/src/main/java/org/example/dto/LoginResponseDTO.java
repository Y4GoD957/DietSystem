package org.example.dto;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class LoginResponseDTO {
    private boolean success;
    private String token;
    private String message;
    private String email;

    public LoginResponseDTO(boolean success, String token, String message, String email) {
        this.success = success;
        this.token = token;
        this.message = message;
        this.email = email;
    }
}
