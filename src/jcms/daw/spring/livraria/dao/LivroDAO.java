package jcms.daw.spring.livraria.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import jcms.daw.spring.livraria.jdbc.FabricaConexao;
import jcms.daw.spring.livraria.modelo.Livro;

public class LivroDAO {
	private Connection conexao;

	public LivroDAO() {
		conexao = FabricaConexao.getConnection();
	}


	public List<Livro> listarTodos(){
		List<Livro> livros = new ArrayList<Livro>();

		String sql = "SELECT * FROM livro";

		try {
			PreparedStatement ps = conexao.prepareStatement(sql);
			ResultSet rs = ps.executeQuery();

			Livro livro;
			while(rs.next()) {
				livro = new Livro();

				livro.setAutor(rs.getString("autor"));
				livro.setCategoria(rs.getString("categoria"));
				livro.setDescricao(rs.getString("descricao"));
				livro.setTitulo(rs.getString("titulo"));
				livro.setId(rs.getLong("id"));
				livro.setPreco(rs.getDouble("preco"));
				livro.setQuantidade(rs.getInt("quantidade"));

				if(livro.getQuantidade() > 0)
					livros.add(livro);
			}

			rs.close();
			ps.close();
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return livros;	
	}

	public Livro buscarLivroPorID(Long id) {
		String sql = "SELECT * FROM livro WHERE id = ?";
		Livro livro = null;
		try {
			PreparedStatement ps = conexao.prepareStatement(sql);
			ps.setLong(1, id);
			ResultSet rs = ps.executeQuery();

			if(rs.next()) {
				livro = new Livro();

				livro.setAutor(rs.getString("autor"));
				livro.setCategoria(rs.getString("categoria"));
				livro.setDescricao(rs.getString("descricao"));
				livro.setTitulo(rs.getString("titulo"));
				livro.setId(rs.getLong("id"));
				livro.setPreco(rs.getDouble("preco"));
				livro.setQuantidade(rs.getInt("quantidade"));

			}

			rs.close();
			ps.close();
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return livro;	
	}

	public List<Livro> listarPorCategoria(String categoria){
		List<Livro> livros = new ArrayList<Livro>();

		String sql = "SELECT * FROM livro WHERE categoria = ?";

		try {
			PreparedStatement ps = conexao.prepareStatement(sql);
			ps.setString(1, categoria);
			ResultSet rs = ps.executeQuery();

			Livro livro;
			while(rs.next()) {
				livro = new Livro();

				livro.setAutor(rs.getString("autor"));
				livro.setCategoria(rs.getString("categoria"));
				livro.setDescricao(rs.getString("descricao"));
				livro.setTitulo(rs.getString("titulo"));
				livro.setId(rs.getLong("id"));
				livro.setPreco(rs.getDouble("preco"));
				livro.setQuantidade(rs.getInt("quantidade"));
				
				if(livro.getQuantidade() > 0)
					livros.add(livro);
			}

			rs.close();
			ps.close();
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return livros;	
	}
	
	
	public Livro buscaPorID(Long id) {
		Livro livro = null;
		
		
		String sql = "SELECT * FROM livro WHERE id = ?";

		try {
			PreparedStatement ps = conexao.prepareStatement(sql);
			ps.setLong(1, id);
			ResultSet rs = ps.executeQuery();

			if(rs.next()) {
				livro = new Livro();

				livro.setAutor(rs.getString("autor"));
				livro.setCategoria(rs.getString("categoria"));
				livro.setDescricao(rs.getString("descricao"));
				livro.setTitulo(rs.getString("titulo"));
				livro.setId(rs.getLong("id"));
				livro.setPreco(rs.getDouble("preco"));
				livro.setQuantidade(rs.getInt("quantidade"));
			}

			rs.close();
			ps.close();
		} catch (SQLException e) {
			e.printStackTrace();
		}
		
		return livro;
		
	}

	public List<Livro> listarPorTitulo(String titulo){
		List<Livro> livros = new ArrayList<Livro>();

		String sql = "SELECT * FROM livro WHERE titulo ilike ?";

		try {
			PreparedStatement ps = conexao.prepareStatement(sql);
			ps.setString(1, titulo + "%");
			ResultSet rs = ps.executeQuery();

			Livro livro;
			while(rs.next()) {
				livro = new Livro();

				livro.setAutor(rs.getString("autor"));
				livro.setCategoria(rs.getString("categoria"));
				livro.setDescricao(rs.getString("descricao"));
				livro.setTitulo(rs.getString("titulo"));
				livro.setId(rs.getLong("id"));
				livro.setPreco(rs.getDouble("preco"));
				livro.setQuantidade(rs.getInt("quantidade"));

				livros.add(livro);
			}

			rs.close();
			ps.close();
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return livros;	
	}



	public List<String> categorias(){
		List<String> categorias = new ArrayList<String>();

		List<Livro> livros = listarTodos();

		for (Livro livro : livros) {
			if(!categorias.contains(livro.getCategoria())) {
				categorias.add(livro.getCategoria());
			}
		}
		return categorias;
	}


	public void cadastrar(Livro livro) {
		String sql = "INSERT INTO livro(titulo, autor, descricao, categoria, preco, quantidade) VALUES(?,?,?,?,?,?)";
		
		try {
			PreparedStatement ps = conexao.prepareStatement(sql);
			
			ps.setString(1, livro.getTitulo());
			ps.setString(2, livro.getAutor());
			ps.setString(3, livro.getDescricao());
			ps.setString(4, livro.getCategoria());
			ps.setDouble(5, livro.getPreco());
			ps.setInt(6, livro.getQuantidade());
			
			ps.execute();
			ps.close();
		} catch (SQLException e) {
			e.printStackTrace();
		}
	}
}