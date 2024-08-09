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
    private int user_id;

    public LoginResponseDTO(boolean success, String token, String message, String email, int user_id) {
        this.success = success;
        this.token = token;
        this.message = message;
        this.email = email;
        this.user_id = user_id;
    }
}
