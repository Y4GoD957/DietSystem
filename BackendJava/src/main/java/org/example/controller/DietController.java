package org.example.controller;

import org.example.dto.DietDTO;
import org.example.entity.Diet;
import org.example.entity.User;
import org.example.service.DietService;
import org.example.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.HashMap;
import java.util.Map;

@RestController
@RequestMapping("/api/diet")
public class DietController {

    @Autowired
    private DietService dietService;

    @Autowired
    private UserService userService;

    @PostMapping("/save-diet")
    public ResponseEntity<String> saveDiet(@RequestBody DietDTO dietDto) {
        try {
            // Validar o DietDTO antes de salvar a dieta
            if (dietDto.getUser_id() <= 0 || dietDto.getHeight() == null || dietDto.getGender() == null ||
                    dietDto.getAge() <= 0 || dietDto.getActivities() <= 0 || dietDto.getDiet() == null) {
                return ResponseEntity.status(400).body("Dados de entrada inválidos");
            }

            // Verificar se o usuário existe
            User user = userService.findById(dietDto.getUser_id())
                    .orElseThrow(() -> new RuntimeException("Usuário não encontrado!"));

            // Criar a entidade Diet
            Diet diet = new Diet();
            diet.setHeight(dietDto.getHeight());
            diet.setGender(dietDto.getGender());
            diet.setAge(dietDto.getAge());
            diet.setActivities(dietDto.getActivities());
            diet.setDiet(dietDto.getDiet());
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

}