<%@ page language="java" contentType="text/html; charset=utf-8"
	pageEncoding="utf-8"%>
<%@ taglib uri="http://java.sun.com/jstl/core" prefix="c"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="utf-8">
<title>Editar actor</title>
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

	<h1>Editar actor</h1>
	 <form action="./actualizar">
    <input type="hidden" class="form-control" name="actor_id" value="<c:out value='${actor.actor_id}' />">
  <div class="form-group">
    <label for="email">Nombre:</label>
    <input type="text" class="form-control" placeholder="Nombre" name="first_name" value="<c:out value='${actor.first_name}' />">
  </div>
  <div class="form-group">
    <label for="pwd">Apellido:</label>
    <input type="text" class="form-control" placeholder="Apellido" name="last_name" value="<c:out value='${actor.last_name}' />">
  </div>
  <button type="submit" class="btn btn-primary">Enviar</button>
</form> 

</body>
</html>