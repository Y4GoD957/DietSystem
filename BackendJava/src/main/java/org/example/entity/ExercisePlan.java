package org.example.entity;

import lombok.Getter;
import lombok.Setter;
import jakarta.persistence.*;

@Entity
@Table(name = "exercise_plans")
@Getter
@Setter
public class ExercisePlan {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int planId;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "diet_id", nullable = false)
    private Diet diet; // Chave estrangeira para Diet

    @Column(nullable = false)
    private String objective;

    @Column(nullable = false)
    private int durationWeeks;

    @Column(name = "total_calories_burned")
    private double totalCaloriesBurned;
}