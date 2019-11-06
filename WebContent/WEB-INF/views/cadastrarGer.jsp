<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="ISO-8859-1">
<title>ELivros - Cadastro de Gerentes</title>
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
		<h1>Cadastrar Gerente</h1>
	</div>
	
	<form action="cadastrarGerente" method="post" id="formulario">
		<p>Novo Gerente</p>
		
		<br><p>Login</p><br>
		<input type="text" placeholder="Login" name="login"><br><br>
		<p>Senha</p>
		<input type="password" placeholder="Senha" name="senha"><br><br>
	
		<input style="margin-left: 40px;" type="submit" value="Cadastrar">
		<a href="cancelarCadastroGerente">Cancelar</a>
		

	</form>
</body>
</html>