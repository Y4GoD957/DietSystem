package org.example.service;

import org.example.dto.DietDTO;
import org.example.dto.UserDTO;
import org.example.entity.User;
import org.example.exception.EmailAlreadyExistsException;
import org.example.exception.UserAlreadyExistsException;
import org.example.exception.UserNotFoundException;
import org.example.repository.DietRepository;
import org.example.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
public class UserService {

    @Autowired
    private final UserRepository userRepository;

    @Autowired
    private final DietRepository dietRepository;

    @Autowired
    private final PasswordEncoder passwordEncoder;

    public UserService(UserRepository userRepository, DietRepository dietRepository, PasswordEncoder passwordEncoder) {
        this.userRepository = userRepository;
        this.dietRepository = dietRepository;
        this.passwordEncoder = passwordEncoder;
    }

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

    @Transactional
    public void updateUserProfile(int userId, UserDTO userDTO) {
        // Obter o usuário atual do banco de dados para comparação de senha
        User existingUser = userRepository.findById(userId)
                .orElseThrow(() -> new UserNotFoundException("Usuário não encontrado com o ID: " + userId));

        // Verificar se a nova senha e a confirmação da senha são iguais
        String newPassword = userDTO.getPassword();
        String confirmPassword = userDTO.getConfirmPassword();
        String passwordToSave;

        if (newPassword != null && !newPassword.isEmpty()) {
            if (confirmPassword == null || confirmPassword.isEmpty()) {
                throw new IllegalArgumentException("Confirmação da senha é obrigatória.");
            }

            // Verifica se as senhas coincidem
            if (!newPassword.equals(confirmPassword)) {
                throw new IllegalArgumentException("A nova senha e a confirmação da senha não são iguais.");
            }

            // Criptografar a nova senha após a verificação
            passwordToSave = passwordEncoder.encode(newPassword);
        } else {
            // Manter a senha existente se não houver nova senha
            passwordToSave = existingUser.getPassword();
        }

        // Atualiza os dados do usuário com a senha possivelmente criptografada ou existente
        int updatedRows = userRepository.updateUserProfile(
                userId,
                userDTO.getUsername(),
                userDTO.getEmail(),
                userDTO.getName(),
                userDTO.getPhone(),
                passwordToSave // Usar a senha criptografada ou senha existente
        );

        // Verifica se o usuário foi atualizado
        if (updatedRows == 0) {
            throw new UserNotFoundException("Usuário não encontrado com o ID: " + userId);
        }

        // Atualiza a dieta associada ao usuário
        if (userDTO.getDiets() != null && !userDTO.getDiets().isEmpty()) {
            for (DietDTO dietDTO : userDTO.getDiets()) {
                int updatedDietRows = dietRepository.updateDiet(
                        dietDTO.getDiet_id(),
                        dietDTO.getActivities(),
                        dietDTO.getAge(),
                        dietDTO.getDiet(),
                        dietDTO.getGender(),
                        dietDTO.getHeight(),
                        dietDTO.getWeight()
                        );

                // Verifica se a dieta foi atualizada
                if (updatedDietRows == 0) {
                    throw new RuntimeException("Dieta não encontrada com o ID: " + dietDTO.getDiet_id());
                }
            }
        }
    }

    public UserDTO getUserProfile(int userId) {
        // Busca o usuário no repositório
        User user = userRepository.findById(userId)
                .orElseThrow(() -> new UserNotFoundException("Usuário não encontrado"));

        // Mapeia o usuário para um DTO
        UserDTO userDTO = new UserDTO();
        userDTO.setUser_id(user.getUserId());
        userDTO.setUsername(user.getUsername());
        userDTO.setEmail(user.getEmail());
        userDTO.setName(user.getName());
        userDTO.setPhone(user.getPhone());

        // Se houver dieta associada ao usuário, mapeia a lista de dietas
        if (user.getDiets() != null) {
            List<DietDTO> dietDTOs = user.getDiets().stream()
                    .map(diet -> {
                        DietDTO dietDTO = new DietDTO();
                        dietDTO.setDiet_id(diet.getDiet_id());

                        // Acessa o user associado à dieta para obter o user_id
                        if (diet.getUser() != null) {
                            dietDTO.setUser_id(diet.getUser().getUserId()); // Certifique-se de que o método getter na classe User é 'getUserId()'
                        } else {
                            dietDTO.setUser_id(null); // Ou outro valor apropriado se não houver usuário associado
                        }

                        dietDTO.setActivities(diet.getActivities());
                        dietDTO.setAge(diet.getAge());
                        dietDTO.setDiet(diet.getDiet());
                        dietDTO.setGender(diet.getGender());
                        dietDTO.setHeight(diet.getHeight());
                        dietDTO.setWeight(diet.getWeight());
                        return dietDTO;
                    })
                    .collect(Collectors.toList());
            userDTO.setDiets(dietDTOs);
        }
        return userDTO;
    }
}