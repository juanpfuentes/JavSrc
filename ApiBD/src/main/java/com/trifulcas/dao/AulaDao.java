package com.trifulcas.dao;

import java.sql.Connection;
import java.sql.DriverManager;
import java.util.List;

import com.trifulcas.models.Aula;

public class AulaDao {
	private Connection connection;

	public AulaDao() {
		try {
			Class.forName("com.mysql.cj.jdbc.Driver");

			connection = DriverManager.getConnection("jdbc:mysql://mysql-trifulcas.alwaysdata.net:3306/trifulcas_code",
					"trifulcas_code", "TrifulcasForever");
		} catch (Exception ex) {
			System.err.println("Error al establecer la conexión a la base de datos: " + ex.getMessage());
			return;
		}
	}

	public List<Aula> get() {
		return null;
	}

	public Aula getById(int id) {
		return null;
	}

	public Aula addAula(Aula aula) {
		return null;
	}

	public Aula updateAula(int id, Aula aula) {
		return null;
	}

	public boolean deleteAula(int id) {
		return true;
	}


}
