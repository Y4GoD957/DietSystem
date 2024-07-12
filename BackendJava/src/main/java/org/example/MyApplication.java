package org.example;

import org.example.entity.User;
import org.example.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
public class MyApplication implements CommandLineRunner {

    @Autowired
    private UserService userService;

    public static void main(String[] args) {
        SpringApplication.run(MyApplication.class, args);
    }

    @Override
    public void run(String... args) throws Exception {
        // Lógica inicial, por exemplo, criar um usuário
        User user = new User();
        user.setUsername("Toz4tti");
        user.setEmail("duda@gmail.com");
        user.setPassword("480548");

        userService.createUser(user);
    }
}