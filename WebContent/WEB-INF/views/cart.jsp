<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
	pageEncoding="ISO-8859-1"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="ISO-8859-1">
<title>ELivros</title>

<style>
* {
	margin: 0;
	padding: 0;
	font-family: sans-serif;
}

h1 {
	width: 150px;
	margin: 0 auto;
}

.carrinho {
	margin-left: 20px;
}

table {
	widht: inherit;
	margin: 0 auto;
}

#category {
	width: 20%;
	float: left;
	margin-left: 20px;
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
</head>
<body>
	<h1>ELivros</h1>

	<br>
	<br>
	<br>


	<div style="width: 200px; margin: 0 auto;">
		<ul>
			<li class="link"><a href="index">Home</a><span>&nbsp;&nbsp;&nbsp;&nbsp;|</span></li>
			<li class="link"><a href="produtos?categoria=todos">&nbsp;&nbsp;&nbsp;&nbsp;Produtos</a></li>
		</ul>
	</div>


	<br>
	<br>


	<div class="carrinho">
		<c:choose>
			<c:when test="${not empty carrinho}">
				<table border="1">
					<thead>
						<th>Title</th>
						<th>Price</th>
						<th>Quantity</th>
					</thead>

					<tbody>
						<c:forEach var="item" items="${carrinho}">
							<tr>
								<td>${item.livro.titulo}</td>
								<td>${item.livro.precoFormatado}</td>

								<td>
									<c:choose>
										<c:when test="${(item.quantidade - 1) > 0  }">
											<button onclick="window.location.href='diminuirQuantidade?id=${item.livro.id}';">-</button>
										</c:when>

										<c:otherwise>
											<button disabled="disabled">-</button>
										</c:otherwise>
									</c:choose> 
									
									${item.quantidade} 
									
									<c:choose>
										<c:when test="${item.disponibilidade() == true }">
											<button onclick="window.location.href='aumentarQuantidade?id=${item.livro.id}';">+</button>
										</c:when>
											
										<c:otherwise>
												<button disabled="disabled">+</button>
										</c:otherwise>
									</c:choose>
								</td>

								<td><a href="remover?id=${item.livro.id}">Withdraw</a></td>
							</tr>
						</c:forEach>

						<tr>
							<td>Total</td>
							<td>${valor}</td>
						</tr>
					</tbody>
				</table>

				<br>
				<br>

				<div style="width: 400px; margin: 0 auto;">
					<ul>
						<li class="link"><a href="produtos?categoria=todos">Continuar
								Comprando</a><span>&nbsp;&nbsp;&nbsp;|&nbsp;&nbsp;&nbsp;</span></li>
						<li class="link"><a href="finalizar">Finalizar Compra</a></li>
					</ul>
				</div>
			</c:when>

			<c:otherwise>
				<h3 style="text-align: center;">Carrinho vazio!</h3>
			</c:otherwise>


		</c:choose>
	</div>



</body>
</html>