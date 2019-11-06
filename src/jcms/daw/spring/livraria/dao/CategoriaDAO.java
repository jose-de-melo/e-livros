package jcms.daw.spring.livraria.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import jcms.daw.spring.livraria.jdbc.FabricaConexao;

public class CategoriaDAO {
	private Connection conexao;

	public CategoriaDAO() {
		conexao = FabricaConexao.getConnection();
	}
	
	public void cadastrar(String nomeCategoria) {
		String sql = "INSERT INTO categoria(nome) VALUES(?)";
		
		try {
			PreparedStatement ps = conexao.prepareStatement(sql);
			
			ps.setString(1, nomeCategoria);
			
			ps.execute();
			ps.close();
		} catch (SQLException e) {
			e.printStackTrace();
		}
	}
	
	public List<String> listar(){
		List<String> categorias = new ArrayList<String>();
		
		String sql = "SELECT * FROM categoria";
		
		try {
			PreparedStatement ps = conexao.prepareStatement(sql);
			
			ResultSet rs = ps.executeQuery();
			
			while(rs.next()) {
				categorias.add(rs.getString("nome"));
			}
			
			rs.close();
			ps.close();
			
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return categorias;
	}
	
}
