package org.example.repository;

import org.example.entity.Diet;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;

public interface DietRepository extends JpaRepository<Diet, Integer> {

    // Retorna uma dieta para um user_id específico
    List<Diet> findByUserUserId(int userId); // Utilizando userId ao invés de user_id

    // Verifica se existe pelo menos uma dieta associada ao user_id
    @Query("SELECT CASE WHEN COUNT(d) > 0 THEN TRUE ELSE FALSE END FROM Diet d WHERE d.user.userId = :userId")
    boolean existsByUserId(@Param("userId") int userId);
}
