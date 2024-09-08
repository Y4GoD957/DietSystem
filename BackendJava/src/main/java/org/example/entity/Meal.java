package org.example.entity;

import lombok.Getter;
import lombok.Setter;
import jakarta.persistence.*;
import java.sql.Time;

@Entity
@Table(name = "meals")
@Getter
@Setter
public class Meal {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int mealId;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "diet_id", nullable = false)
    private Diet diet; // Chave estrangeira para Diet

    @Column(nullable = false)
    private String mealName;

    @Column(nullable = false)
    private Time mealTime;

    @Column(nullable = false)
    private double totalCalories;

    @Column(nullable = false)
    private double totalProtein;

    @Column(nullable = false)
    private double totalCarbs;

    @Column(nullable = false)
    private double totalFat;
}
