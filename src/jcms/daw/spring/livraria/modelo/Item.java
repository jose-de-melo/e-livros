package jcms.daw.spring.livraria.modelo;

public class Item {
	private Livro livro;
	private int quantidade;
	
	public Item() {
	}
	
	public Livro getLivro() {
		return livro;
	}
	
	public void setLivro(Livro livro) {
		this.livro = livro;
	}
	
	public int getQuantidade() {
		return quantidade;
	}
	
	public void setQuantidade(int quantidade) {
		this.quantidade = quantidade;
	}
	
	public boolean disponibilidade() {
		if(quantidade + 1 > livro.getQuantidade()) {
			return false;
		}
		return true;
	}
	
	public void aumentarQuantidade() {
		quantidade++;
	}
	
	public void diminuirQuantidade() {
		quantidade--;
	}
	
	
	
}
