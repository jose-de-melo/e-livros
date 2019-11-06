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
		
		#text{
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
		
		#links{
			text-align: center;
		}
	
	</style>
</head>
<body>
	<div id="text">
		<h1>Nome de usu�rio ou senha inv�lidos!</h1>
	</div>
	
	<div id="links">
		<c:choose>
			<c:when test="${sessionScope.alterar == true }">
				<a href="alterar">Voltar</a>
			</c:when>
			<c:otherwise>
				<a href="signIn">Voltar</a>
			</c:otherwise>
		</c:choose>
		
	</div>
</body>
</html>