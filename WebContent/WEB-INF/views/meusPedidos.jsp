<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
	pageEncoding="ISO-8859-1"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<title>ELivros</title>

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

#livros {
	margin: 0 auto;
	width: 500px;
}

li {
	list-style: none;
	padding-top: 10px;
}

a {
	text-decoration: none;
	color: #4169E1;
	font-size: 20px;
	width: 500px;
	margin-left: 130px;	
}
</style>
</head>
<body>


	<h1 style="width: 100px; margin: 0 auto;">ELivros</h1>


	<br>
	<br>



	<br>
	<br>


	<div id="livros">
		<h2 style="padding-left: 130px;">MEUS PEDIDOS</h2>

		<br>

		<table border="1px">
			<thead>
				<th>NÚMERO DO PEDIDO</th>
				<th>PRODUTO</th>
				<th>PREÇO</th>
				<th>QUANTIDADE</th>
			</thead>

			<tbody>
				<c:forEach var="pedido" items="${pedidos}">
					<tr>
						<td>${pedido.numero}</td>
						<td>${pedido.livros[0].titulo}</td>
						<td>${pedido.livros[0].precoFormatado}</td>
						<td>${ pedido.livros[0].quantidade}</td>
					</tr>
					
				</c:forEach>
			</tbody>
		</table>

		<br>
		<br> <a href="index">Voltar à Página Principal</a>
	</div>
</body>
</html>