package org.example.dto;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class DietDTO {
    private int diet_id;
    private Integer user_id;
    private String height;
    private String gender;
    private int age;
    private int activities;
    private String diet;
}

