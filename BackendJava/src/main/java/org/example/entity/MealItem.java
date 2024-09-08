package org.example.entity;

import lombok.Getter;
import lombok.Setter;
import jakarta.persistence.*;

@Entity
@Table(name = "meal_items")
@Getter
@Setter
public class MealItem {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int mealItemId;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "meal_id", nullable = false)
    private Meal meal; // Chave estrangeira para Meal

    @Column(nullable = false)
    private String foodName;

    @Column(nullable = false)
    private double quantity;

    @Column(nullable = false)
    private double calories;

    @Column(nullable = false)
    private double protein;

    @Column(nullable = false)
    private double carbs;

    @Column(nullable = false)
    private double fat;
}