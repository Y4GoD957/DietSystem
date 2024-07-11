package DAO;

import Conexao.Conexao;
import Entity.Usuario;

import javax.swing.*;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;

public class UsuarioDAO {

    public void cadastrarUsuario(Usuario u){

        Connection conn = Conexao.getConexao();
        PreparedStatement stmt = null;

        try {
            stmt = conn.prepareStatement("INSERT INTO usuarios (USERNAME, EMAIL, PASSWORD) " +
                                             "VALUES (?, ?, ?)");
            stmt.setString(1, u.getUsername());
            stmt.setString(2, u.getEmail());
            stmt.setString(3, u.getPassword());

            stmt.executeUpdate();

            JOptionPane.showMessageDialog(null, "Salvo com sucesso!");
        } catch (SQLException e) {
            JOptionPane.showMessageDialog(null, "Erro ao salvar: " + e);
        } finally {
            Conexao.closeConexao(conn, stmt);
        }

    }
}
