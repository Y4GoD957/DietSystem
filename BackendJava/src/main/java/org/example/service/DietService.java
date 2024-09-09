package org.example.service;

import org.example.dto.DietDTO;
import org.example.dto.MetricsDTO;
import org.example.entity.Diet;
import org.example.entity.User;
import org.example.exception.ResourceNotFoundException;
import org.example.exception.UserNotFoundException;
import org.example.repository.DietRepository;
import org.example.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.math.BigDecimal;
import java.math.RoundingMode;
import java.util.Optional;

@Service
public class DietService {

    @Autowired
    private final DietRepository dietRepository;

    @Autowired
    private final UserRepository userRepository;

    public DietService(DietRepository dietRepository, UserRepository userRepository) {
        this.dietRepository = dietRepository;
        this.userRepository = userRepository;
    }

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
        return dietRepository.findByUserUserId(userId).isPresent();
    }

    public double calculateTMB(String gender, int age, double height, double weight) {
        double tmb;
        if ("Masculino".equalsIgnoreCase(gender)) {
            tmb = 66 + (13.7 * weight) + (5 * height) - (6.8 * age);
        } else if ("Feminino".equalsIgnoreCase(gender)) {
            tmb = 655 + (9.6 * weight) + (1.8 * height) - (4.7 * age);
        } else {
            throw new IllegalArgumentException("Gênero Inválido");
        }
        return BigDecimal.valueOf(tmb).setScale(2, RoundingMode.HALF_UP).doubleValue(); // Formata com 2 casas decimais
    }

    public double calculateIMC(double height, double weight) {
        double imc = weight / Math.pow(height / 100, 2);
        return BigDecimal.valueOf(imc).setScale(2, RoundingMode.HALF_UP).doubleValue(); // Formata com 2 casas decimais
    }

    public double calculateCaloricExpenditure(double tmb, int activities) {
        double activityFactor = activities == 0 ? 1.0 :
                                activities == 1 ? 1.2 :
                                activities == 2 ? 1.375 :
                                activities == 3 ? 1.375 :
                                        activities == 4 ? 1.55 :
                                                activities == 5 ? 1.55 :
                                                        activities == 6 ? 1.725 :
                                                                1.9;
        double caloricExpenditure = tmb * activityFactor;
        return BigDecimal.valueOf(caloricExpenditure).setScale(2, RoundingMode.HALF_UP).doubleValue(); // Formata com 2 casas decimais
    }

    public double calculateIdealWeight(double height) {
        double idealWeight = 22 * Math.pow(height / 100, 2);
        return BigDecimal.valueOf(idealWeight).setScale(2, RoundingMode.HALF_UP).doubleValue(); // Formata com 2 casas decimais
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
        double caloricExpenditure = calculateCaloricExpenditure(tmb, diet.getActivities());
        double idealWeight = calculateIdealWeight(diet.getHeight());
        double imc = calculateIMC(diet.getHeight(), diet.getWeight());

        // Atualizar os valores calculados na tabela Diet
        diet.setTmb(tmb);
        diet.setCaloricExpenditure(caloricExpenditure);
        diet.setIdealWeight(idealWeight);
        diet.setImc(imc);

        // Salvar os novos valores no banco de dados
        dietRepository.save(diet);

        // Definir valores no DTO para resposta
        MetricsDTO metrics = new MetricsDTO();
        metrics.setTmb(tmb);
        metrics.setCaloric_expenditure(caloricExpenditure);
        metrics.setIdeal_weight(idealWeight);
        metrics.setImc(imc);

        return metrics;
    }

    public DietDTO getDietSettings(int userId) {
        User user = userRepository.findById(userId).orElseThrow(() -> new UserNotFoundException("Usuário não encontrado"));
        Diet diet = user.getDiets().stream().findFirst().orElseThrow(() -> new RuntimeException("Dieta não encontrada"));
        return convertToDietDTO(diet);
    }

    private DietDTO convertToDietDTO(Diet diet) {
        DietDTO dietDTO = new DietDTO();
        dietDTO.setDiet_id(diet.getDiet_id());
        dietDTO.setActivities(diet.getActivities());
        dietDTO.setAge(diet.getAge());
        dietDTO.setDiet(diet.getDiet());
        dietDTO.setGender(diet.getGender());
        dietDTO.setHeight(diet.getHeight());
        dietDTO.setWeight(diet.getWeight());
        dietDTO.setUser_id(diet.getUser().getUserId());
        return dietDTO;
    }
}