package org.example.service;

import org.example.dao.UserDao;
import org.example.entity.User;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class UserService {

    private final UserDao uDao = new UserDao();

    public void createUser(User u) {
        uDao.createUser(u);
    }

    public User readUser(int userId) {
        return uDao.readUser(userId);
    }

    public List<User> readAllUsers() {
        return uDao.readAllUsers();
    }

    public void updateUser(User u) {
        uDao.updateUser(u);
    }

    public void deleteUser(int userId) {
        uDao.deleteUser(userId);
    }
}
