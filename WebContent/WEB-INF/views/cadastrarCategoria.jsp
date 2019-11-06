<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="ISO-8859-1">
<title>ELivros - Gerência - Cadastro de Categorias</title>
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

#formulario{
			margin-top:50px;
			text-align: center;
			font-size: 20px;
}

a {
	text-decoration: none;
	color: #4169E1;
	font-size: 16px;
	text-align: center;
}

#links {
	text-align: center;
}
</style>

<body>
	<div id="text">
		<h1>Cadastrar Categoria</h1>
	</div>
	
	<form action="cadCat" method="post" id="formulario">
		<p>Nova Categoria</p>
		<br>
		<input type="text" placeholder="Nome" name="nome"><br><br>
	
		<input style="margin-left: 40px;" type="submit" value="Cadastrar">
		<a href="home">Cancelar</a>
				
	</form>
</body>
</html>