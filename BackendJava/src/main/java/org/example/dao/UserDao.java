package org.example.dao;

import org.example.conexao.Conexao;
import org.example.entity.User;
import org.springframework.stereotype.Repository;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;

@Repository
public class UserDao {

    private static final Logger logger = Logger.getLogger(UserDao.class.getName());

    public void createUser(User u) {
        String sql = "INSERT INTO usuarios (username, email, password) VALUES (?, ?, ?)";

        try (Connection conn = Conexao.getConexao();
             PreparedStatement stmt = conn.prepareStatement(sql)) {

            stmt.setString(1, u.getUsername());
            stmt.setString(2, u.getEmail());
            stmt.setString(3, u.getPassword());

            stmt.executeUpdate();

            logger.log(Level.INFO, "Usuário salvo com sucesso!");
        } catch (SQLException e) {
            logger.log(Level.SEVERE, "Erro ao salvar usuário: ", e);
            throw new RuntimeException("Erro ao salvar usuário: " + e.getMessage());
        }
    }

    public User readUser(int user_id) {
        String sql = "SELECT * FROM usuarios WHERE user_id = ?";
        User u = null;

        try (Connection conn = Conexao.getConexao();
             PreparedStatement stmt = conn.prepareStatement(sql)) {

            stmt.setInt(1, user_id);
            try (ResultSet rs = stmt.executeQuery()) {
                if (rs.next()) {
                    u = new User();
                    u.setUser_id(rs.getInt("user_id"));
                    u.setUsername(rs.getString("username"));
                    u.setEmail(rs.getString("email"));
                    u.setPassword(rs.getString("password"));
                }
            }

        } catch (SQLException e) {
            logger.log(Level.SEVERE, "Erro ao ler usuário: ", e);
            throw new RuntimeException("Erro ao ler usuário: " + e.getMessage());
        }

        return u;
    }

    public List<User> readAllUsers() {
        String sql = "SELECT * FROM usuarios";
        List<User> users = new ArrayList<>();

        try (Connection conn = Conexao.getConexao();
             PreparedStatement stmt = conn.prepareStatement(sql);
             ResultSet rs = stmt.executeQuery()) {

            while (rs.next()) {
                User u = new User();
                u.setUser_id(rs.getInt("user_id"));
                u.setUsername(rs.getString("username"));
                u.setEmail(rs.getString("email"));
                u.setPassword(rs.getString("password"));
                users.add(u);
            }

        } catch (SQLException e) {
            logger.log(Level.SEVERE, "Erro ao ler usuários: ", e);
            throw new RuntimeException("Erro ao ler usuários: " + e.getMessage());
        }

        return users;
    }

    public void updateUser(User u) {
        String sql = "UPDATE usuarios SET username = ?, email = ?, password = ? WHERE user_id = ?";

        try (Connection conn = Conexao.getConexao();
             PreparedStatement stmt = conn.prepareStatement(sql)) {

            stmt.setString(1, u.getUsername());
            stmt.setString(2, u.getEmail());
            stmt.setString(3, u.getPassword());
            stmt.setInt(4, u.getUser_id());

            stmt.executeUpdate();

            logger.log(Level.INFO, "Usuário atualizado com sucesso!");

        } catch (SQLException e) {
            logger.log(Level.SEVERE, "Erro ao atualizar usuário: ", e);
            throw new RuntimeException("Erro ao atualizar usuário: " + e.getMessage());
        }
    }

    public void deleteUser(int user_id) {
        String sql = "DELETE FROM usuarios WHERE user_id = ?";

        try (Connection conn = Conexao.getConexao();
             PreparedStatement stmt = conn.prepareStatement(sql)) {

            stmt.setInt(1, user_id);
            stmt.executeUpdate();

            logger.log(Level.INFO, "Usuário deletado com sucesso!");

        } catch (SQLException e) {
            logger.log(Level.SEVERE, "Erro ao deletar usuário: ", e);
            throw new RuntimeException("Erro ao deletar usuário: " + e.getMessage());
        }
    }
}
