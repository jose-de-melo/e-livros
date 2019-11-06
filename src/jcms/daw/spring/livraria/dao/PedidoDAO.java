package jcms.daw.spring.livraria.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import jcms.daw.spring.livraria.jdbc.FabricaConexao;
import jcms.daw.spring.livraria.modelo.Livro;
import jcms.daw.spring.livraria.modelo.Pedido;

public class PedidoDAO {
	private Connection conexao;

	public PedidoDAO() {
		conexao = FabricaConexao.getConnection();
	}
	
	
	public void cadastrar(Pedido pedido) {
		String sql = "INSERT INTO pedido(npedido, idlivro, valor, idcliente, quantidadelivro) VALUES(?,?,?,?,?)";
		
		try {
			
			Long nPedido = getProximoNumeroPedido();
			
			if(nPedido == null)
				nPedido = 1L;
			else
				nPedido++;
			
			for (Livro livro : pedido.getLivros()) {
				PreparedStatement ps = conexao.prepareStatement(sql);
				
				ps.setLong(1, nPedido);
				ps.setLong(2, livro.getId());
				ps.setDouble(3, livro.getPreco());
				ps.setLong(4, pedido.getUsuario().getId());
				ps.setInt(5, livro.getQuantidade());
				
				ps.execute();
				ps.close();
			}
			
		} catch (SQLException e) {
			e.printStackTrace();
		}
		
	}

	private Long getProximoNumeroPedido() {
		Long nPedido = null;
		String sql = "SELECT MAX(npedido) FROM pedido";
		
		try {
			PreparedStatement ps = conexao.prepareStatement(sql);
			
			ResultSet rs = ps.executeQuery();
			
			if(rs.next())		
				nPedido = rs.getLong("MAX");
			
			rs.close();
			ps.close();
			
		} catch (SQLException e) {
			e.printStackTrace();
		}
		
		return nPedido;
	}
	
	public List<Pedido> pedidosDoId(Long id){
		List<Pedido> pedidos = new ArrayList<Pedido>();
		
		String sql = "SELECT * FROM pedido WHERE idcliente = ? ";
		
		try {
			PreparedStatement ps = conexao.prepareStatement(sql);
			
			ps.setLong(1, id);
			
			ResultSet rs = ps.executeQuery();
			
			Pedido pedido;
			while(rs.next()) {
					pedido = new Pedido();
					pedido.setUsuario(new UsuarioDAO().getUserById(id));
					Livro livro = new LivroDAO().buscaPorID(rs.getLong("idlivro"));
					livro.setQuantidade(rs.getInt("quantidadelivro"));
					pedido.getLivros().add(livro);
					pedido.setNumero(rs.getLong("npedido"));
					
					
					pedidos.add(pedido);
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return pedidos;
	}
	
	
	
}
