package org.example.entity;

import com.fasterxml.jackson.annotation.JsonBackReference;
import lombok.Getter;
import lombok.Setter;

import jakarta.persistence.*;

@Entity
@Table(name = "diets", uniqueConstraints = @UniqueConstraint(columnNames = "user_id"))
@Getter
@Setter
public class Diet {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int diet_id;

    @Column(nullable = false)
    private int height;

    @Column(nullable = false)
    private String gender;

    @Column(nullable = false)
    private int age;

    @Column(nullable = false)
    private double weight;

    @Column(nullable = false)
    private int activities;

    @Column(nullable = false)
    private String diet;

    @Column
    private double tmb;

    @Column
    private double imc;

    @Column(name = "caloric_expediture")
    private double caloricExpenditure;

    @Column(name = "ideal_weight")
    private double idealWeight;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "user_id", nullable = false)
    @JsonBackReference
    private User user; // Chave estrangeira para User
}
