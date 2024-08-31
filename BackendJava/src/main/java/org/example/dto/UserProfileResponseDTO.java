package org.example.dto;

import lombok.Getter;
import lombok.Setter;
import org.example.entity.Diet;
import org.example.entity.User;

@Getter
@Setter
public class UserProfileResponseDTO {
    private int user_id;
    private String username;
    private String email;
    private String name;
    private String phone;
    private Diet diet;

    public UserProfileResponseDTO(User user) {
        this.username = user.getUsername();
        this.email = user.getEmail();
        this.name = user.getName();
        this.phone = user.getPhone();
        this.diet = user.getDiets().isEmpty() ? null : user.getDiets().iterator().next(); // Considera o primeiro diet, se existir
    }
}

