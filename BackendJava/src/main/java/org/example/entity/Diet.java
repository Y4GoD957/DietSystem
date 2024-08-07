package org.example.entity;

import lombok.Getter;
import lombok.Setter;

import jakarta.persistence.*;

@Entity
@Table(name = "diets")
@Getter
@Setter
public class Diet {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int diet_id;

    @Column(nullable = false)
    private String height;

    @Column(nullable = false)
    private String gender;

    @Column(nullable = false)
    private int age;

    @Column(nullable = false)
    private int activities;

    @Column(nullable = false)
    private String diet;

    @ManyToOne
    @JoinColumn(name = "user_id", nullable = false)
    private User user; // Chave estrangeira para User
}