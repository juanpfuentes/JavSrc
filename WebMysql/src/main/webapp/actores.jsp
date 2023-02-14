<%@page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@taglib uri="http://java.sun.com/jstl/core" prefix="c" %>
<!DOCTYPE html>
<html>
<head>
<meta charset="ISO-8859-1">
<title>Lista de actores</title>
<!-- Latest compiled and minified CSS -->
<link rel="stylesheet"
	href="https://maxcdn.bootstrapcdn.com/bootstrap/4.5.2/css/bootstrap.min.css">

<!-- jQuery library -->
<script
	src="https://ajax.googleapis.com/ajax/libs/jquery/3.5.1/jquery.min.js"></script>

<!-- Popper JS -->
<script
	src="https://cdnjs.cloudflare.com/ajax/libs/popper.js/1.16.0/umd/popper.min.js"></script>

<!-- Latest compiled JavaScript -->
<script
	src="https://maxcdn.bootstrapcdn.com/bootstrap/4.5.2/js/bootstrap.min.js"></script>
<link rel="stylesheet" type="text/css" href="style.css">
</head>
<body>
	<h1>Lista de actores</h1>
	<p><a href="./nuevo" class="btn btn-primary">Nuevo actor</a></p>


	<table class="table table-bordered">
		<thead>
			<tr>
				<th>ID</th>
				<th>Nombre</th>
				<th>Apellido</th>
				<th>Fecha</th>
				<th>Acciones</th>
			</tr>
		</thead>
		<tbody>
			<!--   for (Todo todo: todos) {  -->
			<c:forEach var="actor" items="${actores}">

				<tr>
					<td><c:out value="${actor.actor_id}" /></td>
					<td><c:out value="${actor.first_name}" /></td>
					<td><c:out value="${actor.last_name}" /></td>
					<td><c:out value="${actor.last_update}" /></td>
					<td><a href="editar?id=<c:out value='${actor.actor_id}' />">Editar</a>
						&nbsp;&nbsp;&nbsp;&nbsp; <a
						href="borrar?id=<c:out value='${actor.actor_id}' />">Borrar</a></td>
				</tr>
			</c:forEach>
			<!-- } -->
		</tbody>

	</table>

</body>
</html>