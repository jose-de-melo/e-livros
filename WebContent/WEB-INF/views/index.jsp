<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
	pageEncoding="ISO-8859-1"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<title>Livraria E-Livros</title>
</head>
<style>
* {
	margin: 0;
	padding: 0;
	font-family: sans-serif;
}

#text {
	margin-top: 100px;
	text-align: center;
	margin-bottom: 50px;
}

a {
	text-decoration: none;
	color: #4169E1;
	font-size: 20px;
	text-align: center;
}

#links {
	text-align: center;
}
</style>
<body>
	<div id="text">
		<h1>Bem-vindo � Livraria E-livros!</h1>
	</div>


	<div id="links">
		<a href="produtos?categoria=todos">Nossos
			Produtos&nbsp;&nbsp;&nbsp;</a>




		<c:choose>
			<c:when test="${usuario == null }">
				<a href="login">Login</a>
			</c:when>

			<c:otherwise>
				<a href="alterar">Alterar Dados&nbsp;&nbsp;&nbsp;</a>
				<a href="meusPedidos">Meus Pedidos&nbsp;&nbsp;&nbsp;</a>
				<a href="logout">Logout</a>
			</c:otherwise>
		</c:choose>
	</div>

</body>
</html>