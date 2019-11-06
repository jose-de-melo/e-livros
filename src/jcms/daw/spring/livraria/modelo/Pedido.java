package jcms.daw.spring.livraria.modelo;

import java.util.ArrayList;
import java.util.List;

public class Pedido {
	private Long numero;
	private List<Livro> livros;
	private Usuario usuario;
	
	public Pedido() {
		livros = new ArrayList<Livro>();
	}

	public Long getNumero() {
		return numero;
	}

	public void setNumero(Long numero) {
		this.numero = numero;
	}

	public List<Livro> getLivros() {
		return livros;
	}

	public void setLivros(List<Livro> livros) {
		this.livros = livros;
	}

	public Usuario getUsuario() {
		return usuario;
	}

	public void setUsuario(Usuario usuario) {
		this.usuario = usuario;
	}
}