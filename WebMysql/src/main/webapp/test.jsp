<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>
</head>
<body>
<input type="number" id="actor_id"/>
<input type="text" id="nombre"/>
<input type="text" id="apellido"/>
<button onClick="alta()">Añadir</button>
<button onClick="modificar()">Modificar</button>
<button onClick="eliminar()">Eliminar</button>
	<script>
console.log("hola");

function alta(){
	let actor={
			first_name:document.getElementById("nombre").value,
			last_name:document.getElementById("apellido").value
	}
	console.log(actor)
	fetch('http://localhost:8080/sakilaWeb/apirest', {
		  method: 'post',
		  headers: {
		    'Accept': 'application/json, text/plain, */*',
		    'Content-Type': 'application/json'
		  },
		  body:JSON.stringify(actor)
		}).then(
				// Cuando acaba la petición llegamos aquí, y tenemos una respuesta que tiene varias cosas
				// Si todo ha ido ok, el estado de la petición, y los datos
				(res) => res.json()
					)
		  .then((res) => {
			  console.log(res)
			  });
}

function modificar(){
	let actor={
			first_name:document.getElementById("nombre").value,
			last_name:document.getElementById("apellido").value
	}
	let actor_id=document.getElementById("actor_id").value;

	console.log(actor)
	fetch('http://localhost:8080/sakilaWeb/apirest/'+actor_id, {
		  method: 'put',
		  headers: {
		    'Accept': 'application/json, text/plain, */*',
		    'Content-Type': 'application/json'
		  },
		  body:JSON.stringify(actor)
		}).then(
				// Cuando acaba la petición llegamos aquí, y tenemos una respuesta que tiene varias cosas
				// Si todo ha ido ok, el estado de la petición, y los datos
				(res) => res.json()
					)
		  .then((res) => {
			  console.log(res)
			  });
}
function eliminar(){
	let actor_id=document.getElementById("actor_id").value;

	fetch('http://localhost:8080/sakilaWeb/apirest/'+actor_id, {
		  method: 'DELETE',
		  headers: {
		    'Accept': 'application/json, text/plain, */*',
		    'Content-Type': 'application/json'
		  }
		}).then(
				// Cuando acaba la petición llegamos aquí, y tenemos una respuesta que tiene varias cosas
				// Si todo ha ido ok, el estado de la petición, y los datos
				(res) => res.json()
					)
		  .then((res) => {
			  console.log(res)
			  });
}
// Esto es asíncrono. Se manda la petición y se sigue con la ejecución del código
fetch('http://localhost:8080/sakilaWeb/apirest/1', {
  method: 'get',
  headers: {
    'Accept': 'application/json, text/plain, */*',
    'Content-Type': 'application/json'
  }
}).then(
		// Cuando acaba la petición llegamos aquí, y tenemos una respuesta que tiene varias cosas
		// Si todo ha ido ok, el estado de la petición, y los datos
		(res) => res.json()
			)
  .then((res) => {
	  console.log(res)
	  console.log(res.first_name)
	  document.getElementById("nombre").value=res.first_name;
	  document.getElementById("apellido").value=res.last_name;
	  });
console.log("adios");
</script>
</body>
</html>