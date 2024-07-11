import DAO.UsuarioDAO;
import Entity.Usuario;


public class Main {
    public static void main(String[] args)  throws Exception{

        Usuario u = new Usuario();
        u.setUsername("Y4GoD");
        u.setEmail("yago@gmail.com");
        u.setPassword("123123");

        new UsuarioDAO().cadastrarUsuario(u);


    }
}