package org.example.conexao;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.logging.Level;
import java.util.logging.Logger;

public class Conexao {

    private static final String URL = "jdbc:mysql://localhost:3306/system_diet";
    private static final String USER = "root";
    private static final String PASSWORD = "";

    private static Connection connection;

    // Singleton pattern for the database connection
    public static synchronized Connection getConexao() {
        if (connection == null) {
            try {
                connection = DriverManager.getConnection(URL, USER, PASSWORD);
            } catch (SQLException e) {
                throw new RuntimeException("Erro na conexão: ", e);
            }
        }
        return connection;
    }

    // Method to close the connection
    public static void closeConexao() {
        if (connection != null) {
            try {
                connection.close();
                connection = null; // Reset connection after closing
            } catch (SQLException e) {
                Logger.getLogger(Conexao.class.getName()).log(Level.SEVERE, "Erro ao fechar a conexão", e);
            }
        }
    }

    // Method to close the connection and statement
    public static void closeConexao(PreparedStatement stmt) {
        if (stmt != null) {
            try {
                stmt.close();
            } catch (SQLException e) {
                Logger.getLogger(Conexao.class.getName()).log(Level.SEVERE, "Erro ao fechar o PreparedStatement", e);
            }
        }
        closeConexao(); // Close the connection after statement
    }

    // Method to close the connection, statement, and result set
    public static void closeConexao(PreparedStatement stmt, ResultSet rs) {
        if (rs != null) {
            try {
                rs.close();
            } catch (SQLException e) {
                Logger.getLogger(Conexao.class.getName()).log(Level.SEVERE, "Erro ao fechar o ResultSet", e);
            }
        }
        closeConexao(stmt); // Close the statement and connection
    }
}