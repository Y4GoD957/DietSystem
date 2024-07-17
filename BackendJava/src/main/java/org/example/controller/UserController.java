package org.example.controller;

import org.example.dto.UserDTO;
import org.example.entity.User;
import org.example.exception.EmailAlreadyExistsException;
import org.example.exception.UserAlreadyExistsException;
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

    @PostMapping("/register")
    public ResponseEntity<String> createUser(@RequestBody UserDTO userDto) {
        try {
            User user = new User();
            user.setUsername(userDto.getUsername());
            user.setEmail(userDto.getEmail());
            user.setPassword(userDto.getPassword());

            userService.createUser(user);
            return ResponseEntity.status(201).body("Usuário criado com sucesso");
        } catch (UserAlreadyExistsException | EmailAlreadyExistsException e) {
            return ResponseEntity.status(400).body(e.getMessage());
        } catch (Exception e) {
            return ResponseEntity.status(500).body("Erro interno no servidor");
        }
    }

    @PostMapping("/contact")
    public ResponseEntity<String> addContact(@RequestBody UserDTO userDto) {
        try {
            User user = userService.findByEmail(userDto.getEmail());
            if (user == null) {
                return ResponseEntity.status(404).body("Usuário não encontrado");
            }
            user.setName(userDto.getName());
            user.setPhone(userDto.getPhone());
            user.setMessage(userDto.getMessage());

            userService.addContact(user);
            return ResponseEntity.status(200).body("Informações de contato adicionadas com sucesso");
        } catch (Exception e) {
            return ResponseEntity.status(500).body("Erro interno no servidor");
        }
    }

    @GetMapping("/contact/{email}")
    public ResponseEntity<?> getContactByEmail(@PathVariable String email) {
        User user = userService.findByEmail(email);
        if (user != null) {
            return ResponseEntity.ok(user);
        }
        return ResponseEntity.notFound().build();
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
    public ResponseEntity<String> updateUser(@PathVariable int user_id, @RequestBody User u) {
        u.setUser_id(user_id);
        userService.updateUser(u);
        return ResponseEntity.ok("Usuário atualizado com sucesso");
    }

    @DeleteMapping("/{user_id}")
    public ResponseEntity<String> deleteUser(@PathVariable int user_id) {
        userService.deleteUser(user_id);
        return ResponseEntity.ok("Usuário deletado com sucesso");
    }

    @DeleteMapping("/all")
    public ResponseEntity<String> deleteAllUsers() {
        userService.deleteAllUsers();
        return ResponseEntity.ok("Todos os usuários foram deletados com sucesso");
    }
}
