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

    public Diet saveDiet(Long userId, Diet diet) {
        User user = userRepository.findById(Math.toIntExact(userId)).orElseThrow(() -> new RuntimeException("Usuário não encontrado!"));
        diet.setUser(user);
        return dietRepository.save(diet);
    }

    public List<Diet> getDietsByUserId(int user_id) {
        Optional<User> user = userRepository.findByUser_id(user_id);
        if (user != null) {
            return dietRepository.findByUser_UserId(user_id);
        }
        return null;
    }
}
