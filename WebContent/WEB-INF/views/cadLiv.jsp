<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<!DOCTYPE html>
<html>
<head>
	<meta charset="ISO-8859-1">
	<title>ELivros - Cadastrar Livro</title>
	
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
		<h1>ELivros - Cadastrar Livro</h1>
	</div>
	
	<form action="cadLiv" method="post" id="formulario">
		<p>Título</p>
		<input type="text" placeholder="Título" name="titulo"><br><br>
		<p>Autor</p>
		<input type="text" placeholder="Autor" name="autor"><br><br>
		<p>Descrição</p>
		<textarea rows="5" cols="20" name="descricao" placeholder="Descrição"></textarea>
		<br><br>
		<p>Categoria</p>
		
		<select name="categoria">
			<c:forEach var="categoria" items="${itens}">
				<option value="${categoria}">${categoria }</option>
			</c:forEach>
		</select>
		
		<br>
		<br>
		
		<p>Quantidade</p>
		<input type="text" placeholder="Quantidade" name="quantidade"><br><br>
		
		<p>Preço</p>
		<input type="text" placeholder="Preço" name="preco"><br><br>
		
		
		<div id="links">
			<input style="margin-left: 40px;" type="submit" value="Cadastrar"> <a href="home">Cancelar</a>
		</div>
	</form>
	
	
	
	
</body>
</html>