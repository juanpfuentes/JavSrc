package com.trifulcas.dao;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.List;

public class ActorDAO {
	// En esta variable almacenamos la conexión a la base de datos
	private Connection con;

	public ActorDAO() {
		try {
			// Esta línea registra el driver
			Class.forName("com.mysql.cj.jdbc.Driver");
			// Aquí creamos la conexión con:
			
			String driver="jdbc:mysql";
			String url="mysql-trifulcas.alwaysdata.net";
			String port="3306";
			String bd="trifulcas_code";
			String user="trifulcas_code";
			String pass="TrifulcasForever";
			con = DriverManager.getConnection(driver+"://"+url+":"+port+"/"+bd, user, pass);
			
		} catch (Exception ex) {
			System.out.println(ex);
		}
	}
	
	
	/**
	 * Paso al método el id del actor que quiero obtener Me devuelve un POJO que
	 * almacena los datos del registro de la base de datos
	 * 
	 * @param id del registro de la base de datos
	 * @return Actor (pojo con los datos)
	 */
	public Actor getActor(int id) {
		try {

			// Ir a la base de datos, recuperar el registro que tenga el id
			// Meterlo dentro de un POJO y devolverlo

			// Me creo la sentencia sql que recupera un préstamo con un id
			// Y le pongo un parámetro que será el id. Se representa con un ?
			String sql = "select * from actor where actor_id=?";

			// Una vez tengo el sql me creo la sentencia preparada
			PreparedStatement stmt = con.prepareStatement(sql);

			// Poniendo en el primer parámetro el valor que me están pasando del id
			stmt.setInt(1, id);

			// Recuperamos los resultados dentro de un ResultSet
			ResultSet rs = stmt.executeQuery();

			// El resultset NO COGE todos los registros del select y los tiene como en un
			// array
			// El resultset es como si fuera un puntero, una flecha que nos apunta al primer
			// Registro del select. Para recuperar hay que usar next
			if (rs.next()) {
				// Si hemos podido obtener un registro en rs tenemos todos los datos
				// del registro de la base de datos. ¿Cómo? con rs.get del tipo y nombre
				// de la columna. Ej. rs.getInt(1) o rs.getInt("id")

				// ¿Qué hago con estos datos? Empaquetarlos dentro de un POJO para poder
				// devolverlos
				Actor resultado = new Actor(rs.getInt("actor_id"), rs.getString("first_name"),
						rs.getString("last_name"));

				return resultado;

			} else {
				return null;
			}

		} catch (Exception ex) {
			System.out.println(ex);
			return null;
		}
	}

	public List<Actor> getActores() {
		try {

			String sql = "select * from actor";

			PreparedStatement stmt = con.prepareStatement(sql);

			ResultSet rs = stmt.executeQuery();

			// Para almacenar todos los registros tengo que crear un arraylist
			List<Actor> resultado = new ArrayList<Actor>();

			// En esta ocasión no tengo un if porque son varios registros.
			// Tengo que hacer un bucle hasta que no haya registros o lo que es lo mismo
			// MIENTRAS tengamos registros vamos recorriendo
			while (rs.next()) {
				Actor elemento = new Actor(rs.getInt("actor_id"), rs.getString("first_name"),
						rs.getString("last_name"));
				resultado.add(elemento);
			}
			return resultado;

		} catch (Exception ex) {
			System.out.println(ex);
			return null;
		}

	}

	/**
	 * Añade un actor a la base de datos
	 * 
	 * @param actor El POJO con el actor a añadir
	 * @return int el número de filas afectadas (0: ninguna, 1: se ha insertado)
	 */
	public int addActor(Actor actor) {
		try {

			// Igual que en los métodos anteriores tengo que poner el sql
			// De lo que quiero hacer. En este caso es un insert y... tengo tres parámetros
			String sql="insert into actor (first_name,last_name) values (?,?)";
			
			PreparedStatement stmt = con.prepareStatement(sql);

			// Tengo que pasar los parámetros
			stmt.setString(1,actor.getName());
			stmt.setString(2, actor.getSurname());
			
			// Utilizo executeUpdate porque modifico los datos, no obtengo resultados
			// Devuelvo lo que me devuelve el método que son el número de filas afectadas
			return stmt.executeUpdate();
			
		} catch (Exception ex) {
			System.out.println(ex);
			return 0;
		}

	}
	/**
	 * Elimina un actor de la base de datos
	 * 
	 * @param id el id del actor a eliminar
	 * @return int número de filas afectadas (0: no se ha eliminado nada, 1: se ha eliminado el actor
	 */
	public int deleteActor(int id) {
		try {

			// Igual que en los métodos anteriores tengo que poner el sql
			// De lo que quiero hacer. En este caso es un delete y... tengo un parámetro
			String sql="delete from actor where actor_id=?";
			
			PreparedStatement stmt = con.prepareStatement(sql);

			// Tengo que pasar los parámetros
			stmt.setInt(1, id);
			
			// Utilizo executeUpdate porque modifico los datos, no obtengo resultados
			// Devuelvo lo que me devuelve el método que son el número de filas afectadas
			return stmt.executeUpdate();
			
		} catch (Exception ex) {
			System.out.println(ex);
			return 0;
		}

	}
	
	/**
	 * Modifica los datos de un actor
	 * 
	 * @param actor el actor a modificar
	 * @return int el número de filas afectadas (0: ninguna, 1: se ha modificado)
	 */
	public int updateActor(Actor actor) {
		try {

			// Igual que en los métodos anteriores tengo que poner el sql
			// De lo que quiero hacer. En este caso es un update y... tengo cuatro parámetros
			String sql="update actor set first_name=?,last_name=? where actor_id=?";
			
			PreparedStatement stmt = con.prepareStatement(sql);

			// Tengo que pasar los parámetros
			stmt.setString(1,actor.getName());
			stmt.setString(2, actor.getSurname());
				stmt.setInt(4, actor.getId());
			
			// Utilizo executeUpdate porque modifico los datos, no obtengo resultados
			// Devuelvo lo que me devuelve el método que son el número de filas afectadas
			return stmt.executeUpdate();
			
		} catch (Exception ex) {
			System.out.println(ex);
			return 0;
		}

	}
	
	
}