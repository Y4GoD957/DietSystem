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
        if (user == null || !userRepository.existsById(user.getUserId())) {
            throw new RuntimeException("Usuário não encontrado!");
        }

        // Verificar se já existe uma dieta para o usuário
        List<Diet> existingDiets = dietRepository.findByUserUserId(user.getUserId());
        if (!existingDiets.isEmpty()) {
            // Atualizar a primeira dieta existente (se o objetivo for atualizar apenas uma dieta)
            Diet dietToUpdate = existingDiets.get(0);
            dietToUpdate.setHeight(diet.getHeight());
            dietToUpdate.setGender(diet.getGender());
            dietToUpdate.setAge(diet.getAge());
            dietToUpdate.setActivities(diet.getActivities());
            dietToUpdate.setDiet(diet.getDiet());
            return dietRepository.save(dietToUpdate);
        } else {
            // Criar uma nova dieta
            return dietRepository.save(diet);
        }
    }

    public Optional<User> findById(int userId) {
        return userRepository.findById(userId);
    }

    public List<Diet> getDietByUserId(int userId) {
        return dietRepository.findByUserUserId(userId);
    }

    // Novo método para verificar se o usuário tem dados de dieta
    public boolean userHasDiet(int userId) {
        return !dietRepository.findByUserUserId(userId).isEmpty();
    }

}