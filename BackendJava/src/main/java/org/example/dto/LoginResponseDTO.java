package org.example.dto;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class LoginResponseDTO {
    private boolean success;
    private String token;
    private String message;

    public LoginResponseDTO(boolean success, String token, String message) {
        this.success = success;
        this.token = token;
        this.message = message;
    }
}
