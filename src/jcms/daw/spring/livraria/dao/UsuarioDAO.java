package jcms.daw.spring.livraria.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import jcms.daw.spring.livraria.jdbc.FabricaConexao;
import jcms.daw.spring.livraria.modelo.Usuario;

public class UsuarioDAO {
	private Connection conexao;

	public UsuarioDAO() {
		conexao = FabricaConexao.getConnection();
	}
	
	public void cadastrar(Usuario usuario) {
		String sql = "INSERT INTO usuarios(login, senha, perfil) VALUES(?,?,?)";
		
		try {
			PreparedStatement ps = conexao.prepareStatement(sql);
			
			ps.setString(1, usuario.getLogin());
			ps.setString(2, usuario.getSenha());
			ps.setString(3, usuario.getPerfil());
			
			ps.execute();
			ps.close();
		} catch (SQLException e) {
			e.printStackTrace();
		}
	}
	
	public Usuario getUserByLogin(String login) {
		String sql = "SELECT * FROM usuarios WHERE login = ?";
		Usuario usuario = null;
	
		try {
			PreparedStatement ps = conexao.prepareStatement(sql);
			
			ps.setString(1, login);
			
			ResultSet rs = ps.executeQuery();
			
			if (rs.next()) {
				usuario = new Usuario();
				
				usuario.setLogin(login);
				usuario.setSenha(rs.getString("senha"));
				usuario.setPerfil(rs.getString("perfil"));
			}
			
			rs.close();
			ps.close();
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return usuario;
	}
	
	public Usuario getUserById(Long id) {
		String sql = "SELECT * FROM usuarios WHERE id = ?";
		Usuario usuario = null;
	
		try {
			PreparedStatement ps = conexao.prepareStatement(sql);
			
			ps.setLong(1, id);
			
			ResultSet rs = ps.executeQuery();
			
			if (rs.next()) {
				usuario = new Usuario();
				
				usuario.setId(id);
				usuario.setLogin(rs.getString("login"));
				usuario.setSenha(rs.getString("senha"));
				usuario.setPerfil(rs.getString("perfil"));
			}
			
			rs.close();
			ps.close();
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return usuario;
	}
	
	
	
	public void alterar(Usuario usuario) {
		String sql = "UPDATE usuarios SET login=?, senha=? WHERE id=?";
		try {
			PreparedStatement ps = conexao.prepareStatement(sql);
			
			ps.setString(1, usuario.getLogin());
			ps.setString(2, usuario.getSenha());
			ps.setLong(3, usuario.getId());
			
			ps.execute();
			ps.close();
			
		} catch (SQLException e) {
			e.printStackTrace();
		}
	
	}
	
	
	
	
	
	
}
