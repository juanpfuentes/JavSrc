package com.trifulcas.dao;

public class Actor {
	
	private int id;
	private String name;
	private String surname;
	
	public Actor(int id, String name, String surname) {
		super();
		this.id = id;
		this.name = name;
		this.surname = surname;
	}
	
	public int getId() {
		return id;
	}
	public void setId(int id) {
		this.id = id;
	}
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public String getSurname() {
		return surname;
	}
	public void setSurname(String surname) {
		this.surname = surname;
	}

	@Override
	public String toString() {
		return "Actor [id=" + id + ", name=" + name + ", surname=" + surname + "]";
	}
}
