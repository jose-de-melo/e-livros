<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
	pageEncoding="ISO-8859-1"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=utf-8">
<title>E-Livros</title>
</head>

<style>
* {
	margin: 0;
	padding: 0;
	font-family: sans-serif;
}

body {
	width: 100%;
	heigth: 100%;
}

#category {
	width: 20%;
	float: left;
	margin-left: 20px;
}

#livros {
	width: 80%;
	float: rigth;
}

li {
	list-style: none;
	padding-top: 10px;
}

a {
	text-decoration: none;
	color: #4169E1;
	font-size: 20px;
}

.link {
	display: inline;
}
</style>



<body>

	<h1 style="width: 100px; margin: 0 auto;">ELivros</h1>


	<br>
	<br>

	<div class="search-form">
		<form action="produtos" style="margin-left: 20px;">
			Pesquisar por Título<br> <input type="text" placeholder="Título"
				name="termo"> <input type="submit" value="Pesquisar">
		</form>
	</div>


	<br>
	<br>

	<div style="margin-left: 20px;">
		<ul>
			<li class="link"><a href="index">Home</a><span>&nbsp;&nbsp;&nbsp;&nbsp;|</span></li>
			<li class="link"><a href="produtos?categoria=todos">&nbsp;&nbsp;&nbsp;&nbsp;Produtos</a><span>&nbsp;&nbsp;&nbsp;&nbsp;|</span></li>
			<li class="link"><c:choose>
					<c:when test="${not empty carrinho}">
						<a href="carrinho">&nbsp;&nbsp;&nbsp;&nbsp;Carrinho (${nItens})</a>
						<span>&nbsp;&nbsp;&nbsp;&nbsp;</span>
					</c:when>
					<c:otherwise>
						<a href="carrinho">&nbsp;&nbsp;&nbsp;&nbsp;Carrinho</a>
						<span>&nbsp;&nbsp;&nbsp;&nbsp;</span>
					</c:otherwise>
				</c:choose></li>
		</ul>
	</div>


	<br>
	<br>


	<div id="category">
		<h2>CATEGORIA</h2>

		<ul>
			<c:forEach var="categoria" items="${categorias}">
				<li><a href="produtos?categoria=${categoria }">${categoria}</a></li>
			</c:forEach>
			<li><a href="produtos?categoria=todos">Todos</a></li>
		</ul>
	</div>


	<h2 style="padding-bottom: 10px;">LIVROS</h2>
	<table border="1px">
		<thead>
			<th>TÍTULO</th>
			<th>DESCRIÇÃO</th>
			<th>PREÇO</th>
		</thead>

		<tbody>
			<c:forEach var="livro" items="${livros}">
				<tr>
					<td>${livro.titulo}</td>
					<td>${livro.descricao }</td>
					<td>${ livro.precoFormatado}</td>
					<td><a href="comprar?id=${livro.id}">Comprar</a></td>
				</tr>
			</c:forEach>
		</tbody>

	</table>


</body>
</html>