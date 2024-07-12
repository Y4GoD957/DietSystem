package org.example.controller;

import org.example.entity.User;
import org.example.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/users")
public class UserController {

    @Autowired
    private UserService userService;

    @PostMapping
    public ResponseEntity<String> createUser(@RequestBody User u) {
        userService.createUser(u);
        return ResponseEntity.status(201).body("Usuário criado com sucesso");
    }

    @GetMapping("/{user_id}")
    public ResponseEntity<?> getUser(@PathVariable int user_id) {
        User u = userService.readUser(user_id);
        if (u != null) {
            return ResponseEntity.ok(u);
        } else {
            return ResponseEntity.status(404).body("Usuário não encontrado");
        }
    }

    @GetMapping
    public ResponseEntity<List<User>> getAllUsers() {
        List<User> users = userService.readAllUsers();
        return ResponseEntity.ok(users);
    }

    @PutMapping("/{user_id}")
    public ResponseEntity<String> updateUser(@PathVariable int userId, @RequestBody User u) {
        u.setUser_id(userId);
        userService.updateUser(u);
        return ResponseEntity.ok("Usuário atualizado com sucesso");
    }

    @DeleteMapping("/{user_id}")
    public ResponseEntity<String> deleteUser(@PathVariable int user_id) {
        userService.deleteUser(user_id);
        return ResponseEntity.ok("Usuário deletado com sucesso");
    }
}
