package dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import banco.ConexaoMysql;
import modelo.Usuario;

public class UsuarioDao {
	
	private Connection con;
	private ConexaoMysql conexao;
	
	public UsuarioDao() {
		this.conexao = ConexaoMysql.getInstancia();
		this.con = conexao.conecta();
	}
	public void insert(Usuario usuario) {
		String sql = "insert into tb_usuarios" + "(nome, email)" + "values(?,?)";
	
	try {
		PreparedStatement stmt = this.con.prepareStatement(sql);
	
		stmt.setString(1, usuario.getNome());
		stmt.setString(2,usuario.getEmail());
		
		stmt.execute();
		stmt.close();
	}catch (SQLException e) {
		throw new RuntimeException(e);
	}
	}
	public List<Usuario> select(){
		try {
			List<Usuario> listaUsuarios = new ArrayList<Usuario>();
			String sql = "select * from tb_usuarios";
			PreparedStatement stmt = this.con.prepareStatement(sql);
			ResultSet rs = stmt.executeQuery();
			while(rs.next()) {
			Usuario user = new Usuario();
			user.setId(rs.getInt("id_usuarios"));
			user.setNome(rs.getString("nome"));
			user.setEmail(rs.getString("email"));
			
			listaUsuarios.add(user);
		}
		rs.close();
		stmt.close();
		return listaUsuarios;
	}catch (SQLException e) {
		throw new RuntimeException(e);
		}
	}
	public void excluir (int id) {
		String sql = "delete from tb_usuarios where id_usuarios = " + id;
		
		try {
			PreparedStatement stmt = this.con.prepareStatement(sql);
			stmt.execute();
			stmt.close();
		}catch (SQLException e) {
			throw new RuntimeException(e);
		}
	}
	public List<Usuario> selectById(String id){
		try {
			List<Usuario> listaUsuarios = new ArrayList<Usuario>();
			String sql = "select * from tb_usuarios where id_usuarios = " + id;
			PreparedStatement stmt = this.con.prepareStatement(sql);
			ResultSet rs = stmt.executeQuery();
			while(rs.next()) {
			Usuario user = new Usuario();
			user.setId(rs.getInt("id_usuarios"));
			user.setNome(rs.getString("nome"));
			user.setEmail(rs.getString("email"));
			
			listaUsuarios.add(user);
		}
		rs.close();
		stmt.close();
		return listaUsuarios;
	}catch (SQLException e) {
		throw new RuntimeException(e);
		}
	}
	public List<Usuario> selectById(int id){
		try {
		List<Usuario> users = new ArrayList<Usuario>();
		String sql = "select * from tb_usuarios where id_usuarios = " + id;
		PreparedStatement stmt = this.con.prepareStatement(sql);
		ResultSet rs = stmt.executeQuery();
		while(rs.next()) {
		Usuario u = new Usuario();
		u.setId(rs.getInt("id_usuarios"));
		u.setNome(rs.getString("nome"));
		u.setEmail(rs.getString("email"));
		users.add(u);
		}
		rs.close();
		stmt.close();
		return users;
		}catch (SQLException e) {
		throw new RuntimeException(e);
		}
	}
	public void update(Usuario user) {
		String sql = "update tb_usuarios set "
		+ "nome = ?, email = ? "
		+ "where id_usuarios = ?";
		try {
		PreparedStatement stmt = this.con.prepareStatement(sql);
		stmt.setString(1, user.getNome());
		stmt.setString(2, user.getEmail());
		stmt.setInt(3, user.getId());
		stmt.execute();
		stmt.close();
		}catch (SQLException e) {
		throw new RuntimeException(e);
		}
		}
}
