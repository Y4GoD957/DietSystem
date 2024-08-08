package org.example.controller;

import org.example.dto.DietDTO;
import org.example.entity.Diet;
import org.example.entity.User;
import org.example.service.DietService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/diet")
public class DietController {

    @Autowired
    private DietService dietService;

    @PostMapping("/save-diet")
    public ResponseEntity<String> saveDiet(@RequestBody DietDTO dietDto) {
        try {
            // Validar o DietDTO antes de salvar a dieta
            if (dietDto.getUserId() <= 0 || dietDto.getHeight() == null || dietDto.getGender() == null ||
                    dietDto.getAge() <= 0 || dietDto.getActivities() <= 0 || dietDto.getDiet() == null) {
                return ResponseEntity.status(400).body("Dados de entrada inválidos");
            }

            // Verificar se o usuário existe
            User user = dietService.findById(dietDto.getUserId())
                    .orElseThrow(() -> new RuntimeException("Usuário não encontrado!"));

            // Criar a entidade Diet
            Diet diet = new Diet();
            diet.setHeight(dietDto.getHeight());
            diet.setGender(dietDto.getGender());
            diet.setAge(dietDto.getAge());
            diet.setActivities(dietDto.getActivities());
            diet.setDiet(dietDto.getDiet());
            diet.setUser(user);

            // Salvar a dieta
            dietService.saveDiet(diet);
            return ResponseEntity.status(201).body("Dieta salva com sucesso");
        } catch (Exception e) {
            e.printStackTrace(); // Log para depuração
            return ResponseEntity.status(500).body("Erro interno no servidor");
        }
    }
}