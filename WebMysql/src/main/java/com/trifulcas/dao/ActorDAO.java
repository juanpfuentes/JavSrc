package com.trifulcas.dao;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.List;

public class ActorDAO {

	private Connection con;
	private int max_records = 1000;

	public ActorDAO() {
		try {
			Class.forName("com.mysql.cj.jdbc.Driver");
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

	public Actor getActor(int id) {
		Actor res = null;
		try {
			String sql = "select * from actor where actor_id=?";
			PreparedStatement stmt = con.prepareStatement(sql);

			stmt.setInt(1, id);

			ResultSet rs = stmt.executeQuery();
			if (rs.next()) {
				res = new Actor(rs.getInt("actor_id"), rs.getString("first_name"), rs.getString("last_name"),
						rs.getDate("last_update"));
			}

		} catch (Exception ex) {
			System.out.println(ex);
		}
		return res;
	}

	public List<Actor> getActors(int limite) {
		List<Actor> res = new ArrayList<Actor>();
		try {
			String sql = "select * from actor limit ?";
			PreparedStatement stmt = con.prepareStatement(sql);
			stmt.setInt(1, limite);
			ResultSet rs = stmt.executeQuery();
			while (rs.next()) {
				Actor temp = new Actor(rs.getInt("actor_id"), rs.getString("first_name"), rs.getString("last_name"),
						rs.getDate("last_update"));
				res.add(temp);
			}

		} catch (Exception ex) {
			System.out.println(ex);
		}
		return res;
	}
	
	/**
	 * Devuelve un arraylist de actores cuyo apellido contenga la cadena que le pasamos
	 * @param cad cadena a buscar
	 * @return ArrayList de actores
	 */
	public List<Actor> getActors(String cad) {
		List<Actor> res = new ArrayList<Actor>();
		try {
			String sql = "select * from actor where last_name like ?";
			PreparedStatement stmt = con.prepareStatement(sql);
			stmt.setString(1, '%'+cad+'%');
			ResultSet rs = stmt.executeQuery();
			while (rs.next()) {
				Actor temp = new Actor(rs.getInt("actor_id"), rs.getString("first_name"), rs.getString("last_name"),
						rs.getDate("last_update"));
				res.add(temp);
			}

		} catch (Exception ex) {
			System.out.println(ex);
		}
		return res;
	}

	public List<Actor> getActors() {
		return getActors(max_records);
	}

	public boolean addActor(Actor actor) {
		try {
			String sql = "insert into actor (first_name, last_name) values (?,?)";
			PreparedStatement stmt = con.prepareStatement(sql);
			stmt.setString(1, actor.getFirst_name());
			stmt.setString(2, actor.getLast_name());
			int res = stmt.executeUpdate();
			return res == 1;
		} catch (Exception ex) {
			System.out.println(ex);
		}
		return false;
	}

	public boolean updateActor(Actor actor) {
		try {
			String sql = "update actor set first_name=?, last_name=? where actor_id=?";
			PreparedStatement stmt = con.prepareStatement(sql);
			stmt.setString(1, actor.getFirst_name());
			stmt.setString(2, actor.getLast_name());
			stmt.setInt(3, actor.getActor_id());
			int res = stmt.executeUpdate();
			return res == 1;
		} catch (Exception ex) {
			System.out.println(ex);
		}
		return false;
	}

	public boolean deleteActor(Actor actor) {
		try {
			if (actor != null) {
				return deleteActor(actor.getActor_id());
			}
		} catch (Exception ex) {
			System.out.println(ex);
		}
		return false;
	}

	public boolean deleteActor(int actor_id) {
		try {
			String sql = "delete from actor where actor_id=?";
			PreparedStatement stmt = con.prepareStatement(sql);
			stmt.setInt(1, actor_id);
			int res = stmt.executeUpdate();
			return res == 1;
		} catch (Exception ex) {
			System.out.println(ex);
		}
		return false;
	}

}
