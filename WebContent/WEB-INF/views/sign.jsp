<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<title>ELivros - Sign Up</title>
	
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
		<h1>ELivros - Criar Usuário</h1>
	</div>
	
	<form action="criarUsuario" method="post" id="formulario">
		<p>Usuário</p>
		<input type="text" placeholder="Login" name="login"><br><br><p>Senha</p>
		<input type="password" placeholder="Senha" name="senha"><br><br>
		
		<div id="links">
			<input style="margin-left: 40px;" type="submit" value="Cadastrar"> <a href="index">Voltar</a>
		</div>
	</form>
	
</body>
</html>