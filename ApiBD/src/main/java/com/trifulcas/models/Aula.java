package com.trifulcas.models;

public class Aula {

	private int id;
	private String nombre;
	private int capacidad;
	
	
	
	public Aula(int id, String nombre, int capacidad) {
		this.id = id;
		this.nombre = nombre;
		this.capacidad = capacidad;
	}
	
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
	@Override
	public String toString() {
		return "Aula [id=" + id + ", nombre=" + nombre + ", capacidad=" + capacidad + "]";
	}
	
	
}
