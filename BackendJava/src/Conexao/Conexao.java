package Conexao;

import java.sql.*;
import java.util.logging.Level;
import java.util.logging.Logger;

public class Conexao {

    private static final String url = "jdbc:mysql://localhost:3306/system_diet";
    private static final String user = "root";
    private static final String password = "";

    public static Connection getConexao(){
        try {
            return DriverManager.getConnection(url, user, password);
        } catch (SQLException e) {
            throw new RuntimeException("Erro na conexão: ", e);
        }
    }

    public static void closeConexao(Connection conn){
        try {
            if (conn != null) {
                conn.close();
            }
        } catch (SQLException e) {
            Logger.getLogger(Conexao.class.getName()).log(Level.SEVERE, null, e);
        }
    }

    public static void closeConexao(Connection conn, PreparedStatement stmt){

        closeConexao(conn);

        try {

            if (stmt != null) {
                stmt.close();
            }

        } catch (SQLException e) {
            Logger.getLogger(Conexao.class.getName()).log(Level.SEVERE, null, e);
        }
    }

    public static void closeConexao(Connection conn, PreparedStatement stmt, ResultSet rs){

        closeConexao(conn, stmt);

        try {

            if (rs != null) {
                rs.close();
            }

        } catch (SQLException e) {
            Logger.getLogger(Conexao.class.getName()).log(Level.SEVERE, null, e);
        }
    }
}
