package jcms.daw.spring.livraria.jdbc;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class FabricaConexao {
	public static Connection getConnection(){
		Connection conexao = null;
		try {
			Class.forName("org.postgresql.Driver");
			conexao=DriverManager.getConnection("jdbc:postgresql://localhost/elivros", "postgres","aluno");
		} catch (SQLException e) {
			e.printStackTrace();
		} catch (ClassNotFoundException e) {
			e.printStackTrace();
		}
		return conexao;
	}
}