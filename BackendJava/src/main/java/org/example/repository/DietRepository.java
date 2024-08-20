package org.example.repository;

import org.example.entity.Diet;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface DietRepository extends JpaRepository<Diet, Integer> {

    // Retorna uma dieta para um user_id específico
    List<Diet> findByUserUserId(int userId); // Utilizando userId ao invés de user_id
}
