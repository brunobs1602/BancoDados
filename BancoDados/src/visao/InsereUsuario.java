package visao;

import dao.UsuarioDao;
import modelo.Usuario;

public class InsereUsuario {

	public static void main(String[] args) {
		Usuario usuario = new Usuario ();
		UsuarioDao usuarioDAO = new UsuarioDao();
		
		usuario.setNome("Bruno Barreiro dos Santos");
		usuario.setEmail("brunobarreiro@gmail.com");
		
		usuarioDAO.insert(usuario);
		
		System.out.println("Usuario inserido com sucesso.");
	}
}
