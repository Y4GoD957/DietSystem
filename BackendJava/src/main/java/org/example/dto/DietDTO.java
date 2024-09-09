package org.example.dto;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class DietDTO {
    private int diet_id;
    private Integer user_id;
    private int height;
    private String gender;
    private int age;
    private double weight;
    private int activities;
    private String diet;
    private double caloric_expenditure;
    private double ideal_weight;
    private double imc;
    private double tmb;
}

