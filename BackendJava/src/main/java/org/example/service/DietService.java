package org.example.service;

import org.example.entity.Diet;
import org.example.entity.User;
import org.example.repository.DietRepository;
import org.example.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class DietService {

    @Autowired
    private DietRepository dietRepository;

    @Autowired
    private UserRepository userRepository;

    public Diet saveDiet(Diet diet) {
        User user = diet.getUser();
        if (user == null || !userRepository.existsById(user.getUser_id())) {
            throw new RuntimeException("Usuário não encontrado!");
        }
        return dietRepository.save(diet);
    }

    public Optional<User> findById(int userId) {
        return userRepository.findById(userId);
    }

    public List<Diet> getDietsByUserId(int userId) {
        if (userRepository.existsById(userId)) {
            return dietRepository.findById(userId);
        }
        return List.of();
    }
}