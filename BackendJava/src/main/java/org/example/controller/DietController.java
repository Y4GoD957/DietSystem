package org.example.controller;

import org.example.entity.Diet;
import org.example.service.DietService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/diet")
public class DietController {

    @Autowired
    private DietService dietService;

    @PostMapping("/{userId}")
    public ResponseEntity<?> saveDiet(@PathVariable Long userId, @RequestBody Diet diet) {
        Diet savedDiet = dietService.saveDiet(userId, diet);
        return ResponseEntity.ok(savedDiet);
    }
}
