package org.example.dto;

import lombok.Getter;
import lombok.Setter;

import java.util.List;

@Getter
@Setter
public class UserDTO {
    private String username;
    private String email;
    private String password;
    private String confirmPassword;
    private int user_id;
    private String position;
    private String name;
    private String phone;
    private List<DietDTO> diets;
}