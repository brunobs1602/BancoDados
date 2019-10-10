package visao;

import java.util.List;

import dao.UsuarioDao;
import modelo.Usuario;

public class TestaSelect {

	public static void main(String[] args) {
		
		UsuarioDao usuarioDao = new UsuarioDao();
		List<Usuario> listaUsuario = null;
		listaUsuario = usuarioDao.select();
		
		int id;
		String nome;
		String email;
		String info = "Resgistro de Usuarios:";
		
		for(Usuario usuarioTMP : listaUsuario) {
			id = usuarioTMP.getId();
			nome = usuarioTMP.getNome();
			email = usuarioTMP.getEmail();
			
			info += "\n\nID: " + id;
			info += "\nNome: " + nome;
			info += "\nE-mail: " + email;
			}
		System.out.println(info);
	}
}
