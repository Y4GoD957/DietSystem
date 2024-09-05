package org.example.controller;

import org.example.dto.DietDTO;
import org.example.dto.MetricsDTO;
import org.example.entity.Diet;
import org.example.entity.User;
import org.example.exception.ResourceNotFoundException;
import org.example.repository.DietRepository;
import org.example.service.DietService;
import org.example.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.HashMap;
import java.util.Map;
import java.util.Optional;

@RestController
@RequestMapping("/api/diet")
public class DietController {

    @Autowired
    private final DietService dietService;

    @Autowired
    private final UserService userService;

    @Autowired
    private final DietRepository dietRepository;

    public DietController(DietService dietService, UserService userService, DietRepository dietRepository) {
        this.dietService = dietService;
        this.userService = userService;
        this.dietRepository = dietRepository;
    }

    @PostMapping("/save-diet")
    public ResponseEntity<String> saveDiet(@RequestBody DietDTO dietDto) {
        try {
            // Validar o DietDTO antes de salvar a dieta
            if (dietDto.getUser_id() <= 0 || dietDto.getHeight() == 0 || dietDto.getGender() == null ||
                    dietDto.getAge() <= 0 || dietDto.getWeight() == 0.0 || dietDto.getActivities() <= 0 ||
                    dietDto.getDiet() == null) {
                return ResponseEntity.status(400).body("Dados de entrada inválidos");
            }

            // Verificar se o usuário existe
            User user = userService.findById(dietDto.getUser_id())
                    .orElseThrow(() -> new RuntimeException("Usuário não encontrado!"));

            // Criar a entidade Diet
            Diet diet = new Diet();
            diet.setActivities(dietDto.getActivities());
            diet.setAge(dietDto.getAge());
            diet.setDiet(dietDto.getDiet());
            diet.setGender(dietDto.getGender());
            diet.setHeight(dietDto.getHeight());
            diet.setWeight(dietDto.getWeight());
            diet.setUser(user);

            // Salvar ou atualizar a dieta
            dietService.saveDiet(diet);
            return ResponseEntity.status(201).body("Dieta salva ou atualizada com sucesso");
        } catch (Exception e) {
            e.printStackTrace(); // Log para depuração
            return ResponseEntity.status(500).body("Erro interno no servidor");
        }
    }

    @GetMapping("/check/{user_id}")
    public ResponseEntity<Map<String, Boolean>> checkDietData(@PathVariable("user_id") int userId) {
        boolean hasDietData = dietService.userHasDiet(userId);
        Map<String, Boolean> response = new HashMap<>();
        response.put("hasDietData", hasDietData);
        return ResponseEntity.ok(response);
    }

    @GetMapping("/test")
    public ResponseEntity<String> testEndpoint() {
        return ResponseEntity.ok("Endpoint is working!");
    }

    @PutMapping("/calculate/{userId}")
    public void calculateAndSaveDiet(@PathVariable int userId) {
        // Encontre todas as dietas associadas ao usuário
        Optional<Diet> optionalDiet = dietRepository.findByUserUserId(userId);

        if (optionalDiet.isPresent()) {
            // Supondo que você deseja atualizar a dieta encontrada
            Diet diet = optionalDiet.get();

            // Cálculos
            double tmb = dietService.calculateTMB(diet.getGender(), diet.getAge(), diet.getHeight(), diet.getWeight());
            double imc = dietService.calculateIMC(diet.getHeight(), diet.getWeight());
            double caloric_expenditure = dietService.calculateCaloricExpenditure(tmb, diet.getActivities());
            double ideal_weight = dietService.calculateIdealWeight(diet.getHeight());

            // Atualize os valores calculados no objeto Diet
            diet.setTmb(tmb);
            diet.setImc(imc);
            diet.setCaloricExpenditure(caloric_expenditure);
            diet.setIdealWeight(ideal_weight);

            // Salve os valores calculados no banco de dados
            dietRepository.save(diet);  // Aqui é onde os valores são salvos no banco de dados
        } else {
            // Lidar com o caso onde não há dietas para o usuário
            System.out.println("Nenhuma dieta encontrada para o usuário com ID: " + userId);
        }
    }

    @GetMapping("/metrics/{userId}")
    public ResponseEntity<MetricsDTO> getMetrics(@PathVariable int userId) {
        try {
            MetricsDTO metrics = dietService.getMetricsByUserId(userId);
            return ResponseEntity.ok(metrics);
        } catch (ResourceNotFoundException ex) {
            return ResponseEntity.notFound().build();
        }
    }

    @GetMapping("/diet-settings/{user_id}")
    public ResponseEntity<DietDTO> getDietSettings(@PathVariable int user_id) {
        DietDTO diet = dietService.getDietSettings(user_id);
        return ResponseEntity.ok(diet);
    }
}