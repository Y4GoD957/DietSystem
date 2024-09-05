package org.example.service;

import org.example.dto.MetricsDTO;
import org.example.entity.Diet;
import org.example.entity.User;
import org.example.exception.ResourceNotFoundException;
import org.example.repository.DietRepository;
import org.example.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

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
        Optional<Diet> existingDiets = dietRepository.findByUserUserId(user.getUserId());
        if (existingDiets.isPresent()) {
            // Atualizar a primeira dieta existente (se o objetivo for atualizar apenas uma dieta)
            Diet dietToUpdate = existingDiets.get();
            dietToUpdate.setHeight(diet.getHeight());
            dietToUpdate.setGender(diet.getGender());
            dietToUpdate.setAge(diet.getAge());
            dietToUpdate.setWeight(diet.getWeight());
            dietToUpdate.setActivities(diet.getActivities());
            dietToUpdate.setDiet(diet.getDiet());
            return dietRepository.save(dietToUpdate);
        } else {
            // Criar uma nova dieta
            return dietRepository.save(diet);
        }
    }

    // Novo método para verificar se o usuário tem dados de dieta
    public boolean userHasDiet(int userId) {
        return !dietRepository.findByUserUserId(userId).isEmpty();
    }

    public double calculateTMB(String gender, int age, double height, double weight) {
        if ("Masculino".equalsIgnoreCase(gender)) {
            return 66 + (13.7 * weight) + (5 * height) - (6.8 * age);
        } else if ("Feminino".equalsIgnoreCase(gender)) {
            return 655 + (9.6 * weight) + (1.8 * height) - (4.7 * age);
        }
        throw new IllegalArgumentException("Gênero Inválido");
    }

    public double calculateIMC(double height, double weight) {
        return weight / Math.pow(height / 100, 2);
    }

    public double calculateCaloricExpenditure(double tmb, int activities) {
        // Fator de atividade: leve = 1.2, moderado = 1.55, intenso = 1.9
        double activityFactor = activities == 1 ? 1.2 :
                                activities == 2 ? 1.55 :
                                activities == 3 ? 1.55 :
                                1.9;  // Valor padrão para atividades fora da faixa esperada
        return tmb * activityFactor;
    }

    public double calculateIdealWeight(double height) {
        return 22 * Math.pow(height / 100, 2);
    }

    public MetricsDTO getMetricsByUserId(int userId) {
        // Obtém a dieta associada ao userId
        Optional<Diet> optionalDiet = dietRepository.findByUserUserId(userId);

        // Verifica se a dieta foi encontrada
        if (optionalDiet.isEmpty()) {
            throw new ResourceNotFoundException("Dieta não encontrada para o usuário ID " + userId);
        }

        Diet diet = optionalDiet.get();

        // Cálculos
        double tmb = calculateTMB(diet.getGender(), diet.getAge(), diet.getHeight(), diet.getWeight());
        double caloric_expenditure = calculateCaloricExpenditure(tmb, diet.getActivities());
        double ideal_weight = calculateIdealWeight(diet.getHeight());
        double imc = calculateIMC(diet.getHeight(), diet.getWeight());

        // Definindo valores no DTO
        MetricsDTO metrics = new MetricsDTO();
        metrics.setTmb(tmb);
        metrics.setCaloric_expenditure(caloric_expenditure);
        metrics.setIdeal_weight(ideal_weight);
        metrics.setImc(imc);

        return metrics;
    }
}