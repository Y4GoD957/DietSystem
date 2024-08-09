package org.example.service;

import org.example.entity.User;
import org.example.exception.EmailAlreadyExistsException;
import org.example.exception.UserAlreadyExistsException;
import org.example.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class UserService {

    @Autowired
    private UserRepository userRepository;

    @Autowired
    private PasswordEncoder passwordEncoder;

    public void createUser(User user) throws UserAlreadyExistsException, EmailAlreadyExistsException {
        if (userRepository.findByUsername(user.getUsername()).isPresent()) {
            throw new UserAlreadyExistsException("Usuário já existente!");
        }
        if (userRepository.findByEmail(user.getEmail()).isPresent()) {
            throw new EmailAlreadyExistsException("E-mail já existente!");
        }
        user.setPassword(passwordEncoder.encode(user.getPassword()));
        userRepository.save(user);
    }

    public void addContact(User user) {
        userRepository.save(user);
    }

    public User readUser(int userId) {
        return userRepository.findById(userId).orElse(null);
    }

    public List<User> readAllUsers() {
        return userRepository.findAll();
    }

    public void updateUser(User user) {
        userRepository.save(user);
    }

    public void deleteUser(int userId) {
        userRepository.deleteById(userId);
    }

    public void deleteAllUsers() {
        userRepository.deleteAll();
    }

    public User findByEmail(String email) {
        return userRepository.findByEmail(email).orElse(null);
    }

    public Optional<User> findById(int userId) {
        return userRepository.findById(userId);
    }

    public int getUserId(int user_id) {
        Optional<User> user = userRepository.findById(user_id);
        return user.map(User::getUser_id).orElse(-1); // Retorna o user_id do usuário ou -1 se não encontrado
    }
}