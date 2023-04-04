package com.trifulcas.ApiAula.model;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Table;

@Entity
@Table(name="aula")
public class Aula {
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "id")
	private int id;

	@Column(name = "nombre")
	private String nombre;

	@Column(name = "capacidad")
	private int capacidad;

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public String getNombre() {
		return nombre;
	}

	public void setNombre(String nombre) {
		this.nombre = nombre;
	}

	public int getCapacidad() {
		return capacidad;
	}

	public void setCapacidad(int capacidad) {
		this.capacidad = capacidad;
	}

	public Aula(int id, String nombre, int capacidad) {
		this.id = id;
		this.nombre = nombre;
		this.capacidad = capacidad;
	}
	public Aula() {
		
	}

	@Override
	public String toString() {
		return "Aula [id=" + id + ", nombre=" + nombre + ", capacidad=" + capacidad + "]";
	}
	
}
