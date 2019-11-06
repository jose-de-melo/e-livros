<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<!DOCTYPE html>
<html>
<head>
	<meta charset="ISO-8859-1">
	<title>ELivros - Login</title>
	
	<style>
		*{
			margin:0;
			padding:0;
			font-family: sans-serif;
		}
		
		#titulo{
			margin-top:50px;
			text-align: center;
		}
		
		#formulario{
			margin-top:50px;
			text-align: center;
			font-size: 20px;
		}
		
		a {
			text-decoration: none;
			color: #4169E1;
			font-size: 15px;
			text-align: center;
		}
		
		#links {
			text-align: center;
		}
	</style>
	
</head>
<body>
	<div id="titulo">
		<h1>ELivros - Logar</h1>
	</div>
	
	<form action="logar" method="post" id="formulario">
		<p>Usuário</p>
		<input type="text" placeholder="Login" name="login"><br><br><p>Senha</p>
		<input type="password" placeholder="Senha" name="senha"><br><br>
		
		<c:choose>
			<c:when test="${compra == true }">
				<input type="hidden" name="compra" value="true">
			</c:when>
			<c:otherwise>
				<input type="hidden" name="compra" value="false">
			</c:otherwise>
		</c:choose>
		<div id="links">
			<input style="margin-left: 40px;" type="submit" value="Logar"> <a href="signIn">Criar Usuário</a>
		</div>
	</form>
	
	
	
	
</body>
</html>