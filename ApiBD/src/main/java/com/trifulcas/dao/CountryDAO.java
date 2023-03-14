package com.trifulcas.dao;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import com.trifulcas.models.Country;

public class CountryDAO {
	private Connection connection;

	public CountryDAO() {
		try {
			Class.forName("com.mysql.cj.jdbc.Driver");
			
			connection = DriverManager.getConnection("jdbc:mysql://mysql-trifulcas.alwaysdata.net:3306/trifulcas_code",
					"trifulcas_code", "TrifulcasForever");
		} catch (Exception ex) {
			System.err.println("Error al establecer la conexión a la base de datos: " + ex.getMessage());
			return;
		}

	}



	public boolean crearPais(int country_id, String country) {
		if (connection == null) {
			System.out.println("no se ha podido conectar a la Base de Datos");
			return false;
		}
		try {
			PreparedStatement ps = connection.prepareStatement("insert into country (country_id, country) values(?,?)");
			ps.setInt(1, country_id);
			ps.setString(2, country);
			int filasAfectadas = ps.executeUpdate();
			return filasAfectadas == 1;
		} catch (Exception ex) {
			System.out.println("Error al añadir el país");
			System.out.println(ex.getMessage());
			return false;
		}
	}

	public boolean borrarPais(int country_id) {
		try {
			PreparedStatement ps = connection.prepareStatement("DELETE FROM country WHERE country_id = ?");
			ps.setInt(1, country_id);

			int filasBorradas = ps.executeUpdate();
			return filasBorradas > 0;
		} catch (SQLException ex) {
			System.err.println("Error al borrar el país: " + ex.getMessage());
			return false;
		}
	}

	public List<Country> paises() {
		try {
			PreparedStatement ps = connection.prepareStatement("SELECT * FROM country");
			List<Country> res=new ArrayList<>();

			ResultSet result = ps.executeQuery();

			while (result.next()) {
				Country country = new Country(result.getInt("country_id"),result.getString("country"));
				country.setLast_update(result.getString("last_update"));
				res.add(country);

			}
			return res;
		} catch (SQLException ex) {
			System.err.println("Error al buscar el país por id: " + ex.getMessage());
			return null;
		}
	}
	
	public Country paisPorId(int country_id) {
		try {
			PreparedStatement ps = connection.prepareStatement("SELECT * FROM country WHERE country_id = ?");
			ps.setInt(1, country_id);

			ResultSet result = ps.executeQuery();

			if (result.next()) {
				Country country = new Country(country_id, null);
				country.setCountry_id(result.getInt("country_id"));
				country.setCountry(result.getString("country"));
				country.setLast_update(result.getString("last_update"));

				return country;
			} else {
				return null;
			}
		} catch (SQLException ex) {
			System.err.println("Error al buscar el país por id: " + ex.getMessage());
			return null;
		}
	}

	public boolean cambiarPais(int country_id, String newCountry) {
		try {

			PreparedStatement ps = connection.prepareStatement("UPDATE country SET country = ? WHERE country_id = ?");
			ps.setString(1, newCountry);
			ps.setInt(2, country_id);

			int filasAfectadas = ps.executeUpdate();
			return filasAfectadas > 0;
		} catch (SQLException ex) {
			System.err.println("Error al actualizar el nombre del país: " + ex.getMessage());
			return false;
		}

	}
}
