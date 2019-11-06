package jcms.daw.spring.livraria.controller;


import java.util.ArrayList;
import java.util.List;

import javax.servlet.http.HttpSession;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;

import jcms.daw.spring.livraria.dao.CategoriaDAO;
import jcms.daw.spring.livraria.dao.LivroDAO;
import jcms.daw.spring.livraria.dao.LoginDAO;
import jcms.daw.spring.livraria.dao.PedidoDAO;
import jcms.daw.spring.livraria.dao.UsuarioDAO;
import jcms.daw.spring.livraria.modelo.Item;
import jcms.daw.spring.livraria.modelo.Livro;
import jcms.daw.spring.livraria.modelo.Pedido;
import jcms.daw.spring.livraria.modelo.Usuario;

@Controller
public class GeneralController {

	@RequestMapping("/")
	public String index() {
		return "index";
	}

	@RequestMapping("/home")
	public String home(HttpSession session){
		Usuario user = (Usuario) session.getAttribute("usuario");

		if(user.getPerfil().equals("USER")) {		
			return index();
		}else if(user.getPerfil().equals("ADMIN")){
			return "homeAdm";
		}else {
			return "homeGer";
		}
	}


	@RequestMapping("/index")
	public String index(HttpSession session){
		return index();
	}

	@RequestMapping("/carrinho")
	public String carrinho(HttpSession session){
		double valorTotal = 0;
		@SuppressWarnings("unchecked")
		List<Item> itens = (ArrayList<Item>) session.getAttribute("carrinho");

		if(itens != null) {
			for (Item item : itens) {
				valorTotal += item.getLivro().getPreco() * item.getQuantidade();
			}

			session.setAttribute("valor", String.format("R$ %1.2f", valorTotal));
		}
		return "cart";
	}

	@RequestMapping("/produtos")	
	public String produtos(String categoria, Model model, String termo) {
		LivroDAO dao = new LivroDAO();

		if(termo != null) {
			model.addAttribute("livros", dao.listarPorTitulo(termo));
		}else {
			if(categoria.equals("todos")) {
				model.addAttribute("livros", dao.listarTodos());
			}else {
				model.addAttribute("livros", dao.listarPorCategoria(categoria));
			}
		}

		model.addAttribute("categorias", dao.categorias());

		return "listaProdutos";
	}

	@SuppressWarnings("unchecked")
	@RequestMapping("/comprar")
	public String comprar(HttpSession session, Livro livro) {
		List<Item> itens = (ArrayList<Item>) session.getAttribute("carrinho");
		
		Livro lv = new LivroDAO().buscaPorID(livro.getId());
		boolean livroEncontrado = false;
		if(itens == null) {
			itens = new ArrayList<Item>();
			Item item = new Item();
			item.setLivro(lv);
			item.setQuantidade(1);
			itens.add(item);
		}else {
			for (Item item : itens) {
				if(item.getLivro().getId() == lv.getId()) {
					livroEncontrado = true;
					if(item.disponibilidade()) {
						item.aumentarQuantidade();
						break;
					}
				}
			}
			
			if(!livroEncontrado) {
				Item item = new Item();
				item.setLivro(lv);
				item.setQuantidade(1);
				itens.add(item);
			}
		}
		
		session.setAttribute("nItens", itens.size());
		session.setAttribute("carrinho", itens);

		return "redirect:produtos?categoria=todos";
	}

	@RequestMapping("/remover")
	public String remover(HttpSession session, Long id) {
		@SuppressWarnings("unchecked")
		List<Item> carrinho = (ArrayList<Item>) session.getAttribute("carrinho");

		for (Item it : carrinho) {
			if(it.getLivro().getId() == id) {
				carrinho.remove(it);
				session.setAttribute("carrinho", carrinho);
				int nItens = (int) session.getAttribute("nItens");
				nItens--;
				session.setAttribute("nItens", nItens);
				break;
			}
		}

		return "redirect:carrinho";
	}

	@RequestMapping("/finalizar")
	public String finalizarCompra(HttpSession session) {
		String url = "";

		Object usuario = session.getAttribute("usuario");

		if(usuario != null) {
			PedidoDAO dao = new PedidoDAO();
			@SuppressWarnings("unchecked")
			List<Item> itens = (ArrayList<Item>) session.getAttribute("carrinho");
			Usuario user = (Usuario) session.getAttribute("usuario");
			
			List<Livro> carrinho = new ArrayList<Livro>();
			
			for (Item item : itens) {
				item.getLivro().setQuantidade(item.getQuantidade());
				carrinho.add(item.getLivro());
			}
			
			Pedido pedido = new Pedido();
			pedido.setLivros(carrinho);
			pedido.setUsuario(user);

			session.setAttribute("compra", false);
			session.setAttribute("carrinho", null);
			session.setAttribute("nItens", 0);

			dao.cadastrar(pedido);


			url = "compraEfetuada";
		}else {
			url = "redirect:log?compra=true";
		}

		return url;
	}

	@RequestMapping("/logar")
	public String logar(HttpSession session, boolean compra, String login, String senha) {
		String url = "";

		Usuario usuario = new Usuario();
		usuario.setLogin(login);
		usuario.setSenha(senha);

		LoginDAO dao = new LoginDAO();

		if(dao.verificarLogin(usuario)) {
			Usuario user = dao.getUsuarioPorLogin(login);
			session.setAttribute("usuario", user);
			if(compra)
				url = "redirect:carrinho";
			else
				return "redirect:home";
		}else {
			if(compra)
				url = "redirect:log?compra=true";
			else
				url = "login";
		}

		return url;
	}

	@RequestMapping("/login")
	public String login(HttpSession session) {
		session.setAttribute("compra", false);
		return "login";
	}

	@RequestMapping("/cadastroGerente")
	public String cadastrarGerente() {
		return "cadastrarGer";
	}

	@RequestMapping("/cancelarCadastroGerente")
	public String cancelarCadastroGerente() {
		return "homeAdm";
	}

	@RequestMapping("/cadastrarGerente")
	public String cadastrarCadastroGerente(String login, String senha) {
		if(!login.equals("") && !senha.equals("")) {
			Usuario ger = new Usuario();

			ger.setLogin(login);
			ger.setSenha(senha);
			ger.setPerfil("GERENTE");

			new UsuarioDAO().cadastrar(ger);

			return "gerenteCadastrado";
		}else {
			return "redirect:cadastroGerente?mensagem=true";
		}
	}


	@RequestMapping("/log")
	public String login(HttpSession session, boolean compra) {
		session.setAttribute("compra", compra);
		return "login";
	}

	@RequestMapping("/logout")
	public String logout(HttpSession session) {
		session.invalidate();
		return index();
	}

	@RequestMapping("/signIn")
	public String cadastrarUsuario() {
		return "sign";
	}

	@RequestMapping("/cadastrarCategoria")
	public String cadastrarCat() {
		return "cadastrarCategoria";
	}

	@RequestMapping("/cadCat")
	public String cadCat(String nome) {
		if(nome.equals("")) {
			return "redirect:cadastrarCategoria";
		}

		new CategoriaDAO().cadastrar(nome);
		return "categoriaCadastrada";
	}
	
	@RequestMapping("/cadastrarLivro")
	public String cadastrarLivro(HttpSession session) {
		session.setAttribute("itens", new CategoriaDAO().listar());
		return "cadLiv";
	}
	
	@RequestMapping("/cadLiv")
	public String cadLiv(String titulo, String autor, String descricao, String categoria, String quantidade, String preco) {
		if(titulo.equals("") || autor.equals("") || categoria.equals("") || quantidade.equals("") || descricao.equals("") || preco.equals("") ) {
			return "redirect:cadastrarLivro";
		}
		
		Livro livro = new Livro();
		livro.setAutor(autor);
		livro.setCategoria(categoria);
		livro.setDescricao(descricao);
		livro.setTitulo(titulo);
		livro.setQuantidade(Integer.parseInt(quantidade));
		livro.setPreco(Double.parseDouble(preco));
		
		new LivroDAO().cadastrar(livro);
		
		
		return "livroCadastrado";
	}
	
	
	@RequestMapping("/criarUsuario")
	public String cadastrarUsuario(String login, String senha) {
		if(login.equals("") || senha.equals("")) {
			return "usuarioInvalido";
		}
		
		UsuarioDAO dao = new UsuarioDAO();
		
		if(dao.getUserByLogin(login) != null)
			return "usuarioInvalido";
		
		Usuario usuario = new Usuario();
		usuario.setLogin(login);
		usuario.setSenha(senha);
		usuario.setPerfil("USER");
		dao.cadastrar(usuario);
		return "usuarioCadastrado";
	}
	
	@RequestMapping("/alterar")
	public String requestAlterarDados(HttpSession session) {
		session.setAttribute("alterar", false);
		return "alterarUsuario";
	}

	
	@RequestMapping("/dados")
	public String dadosInvalidos(HttpSession session, boolean alterar) {
		session.setAttribute("alterar",true);
		return "usuarioInvalido";
	}
	
	@RequestMapping("/alterarDados")
	public String alterarDados(HttpSession session, String login, String senha) {
		if(login.equals("") || senha.equals("")) {
			return "redirect:dados?alterar=true";
		}
		
		UsuarioDAO dao = new UsuarioDAO();
		Usuario usuario = dao.getUserByLogin(login);
		
		
		if(usuario != null)
			if(!usuario.getLogin().equals(login))
				return "redirect:dados?alterar=true";
			
		usuario = (Usuario) session.getAttribute("usuario");
		
		usuario.setLogin(login);
		usuario.setSenha(senha);
		
		dao.alterar(usuario);
		
		return "dadosAlterados";
	}
	
	@RequestMapping("/meusPedidos")
	public String meusPedidos(HttpSession session) {
		session.setAttribute("pedidos", new PedidoDAO().pedidosDoId(((Usuario)session.getAttribute("usuario")).getId()));
		return "meusPedidos";
	}
	
	@RequestMapping("/aumentarQuantidade")
	public String aumentarQtd(HttpSession session, Long id) {
		@SuppressWarnings("unchecked")
		List<Item> itens = (ArrayList<Item>) session.getAttribute("carrinho");
		
		for (Item item : itens) {
			if(item.getLivro().getId() == id) {
				item.aumentarQuantidade();
				break;
			}
		}
		return "redirect:carrinho";
	}
	
	@RequestMapping("/diminuirQuantidade")
	public String diminuirQtd(HttpSession session, Long id) {
		@SuppressWarnings("unchecked")
		List<Item> itens = (ArrayList<Item>) session.getAttribute("carrinho");
		
		for (Item item : itens) {
			if(item.getLivro().getId() == id) {
				item.diminuirQuantidade();
				break;
			}
		}
		return "redirect:carrinho";
	}


}