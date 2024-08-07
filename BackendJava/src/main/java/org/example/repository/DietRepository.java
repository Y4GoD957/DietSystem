package org.example.repository;

import org.example.entity.Diet;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface DietRepository extends JpaRepository<Diet, Long> {

    List<Diet> findByUser_UserId(int userId);

}
