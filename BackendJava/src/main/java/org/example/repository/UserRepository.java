package org.example.repository;

import org.example.entity.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface UserRepository extends JpaRepository<User, Integer> {

    Optional<User> findByUsername(String username);

    Optional<User> findByEmail(String email);

    @Modifying
    @Query("UPDATE User u SET u.username = :username, u.position = :position, u.email = :email, u.name = :name, u.phone = :phone, u.password = :password WHERE u.userId = :userId")
    int updateUserProfile(@Param("userId") int userId,
                          @Param("position") String position,
                          @Param("username") String username,
                          @Param("email") String email,
                          @Param("name") String name,
                          @Param("phone") String phone,
                          @Param("password") String password);
}