package jcms.daw.spring.livraria.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import jcms.daw.spring.livraria.jdbc.FabricaConexao;
import jcms.daw.spring.livraria.modelo.Usuario;

public class LoginDAO {
	private Connection conexao;

	public LoginDAO() {
		conexao = FabricaConexao.getConnection();
	}
	
	public Usuario getUsuarioPorLogin(String login) {
		String sql = "SELECT * FROM usuarios WHERE login = ?";
		
		Usuario usuario =  null;
		try {
			PreparedStatement ps = conexao.prepareStatement(sql);
			ps.setString(1, login);
			
			ResultSet rs = ps.executeQuery();
			
			if(rs.next()) {
				usuario = new Usuario();
				usuario.setId(rs.getLong("id"));
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
	
	
	public boolean verificarLogin(Usuario usuario) {
		String sql = "SELECT * FROM usuarios WHERE login = ? AND senha = ?";
		boolean usuarioOK = false;
		
		try {
			PreparedStatement ps = conexao.prepareStatement(sql);
			ps.setString(1, usuario.getLogin());
			ps.setString(2, usuario.getSenha());
			
			ResultSet rs = ps.executeQuery();
			
			if(rs.next()) {
				usuarioOK = true;
			}
			
			rs.close();
			ps.close();
			
		} catch (SQLException e) {
			e.printStackTrace();
		}
		
		return usuarioOK;
	}
	
	
	
}
