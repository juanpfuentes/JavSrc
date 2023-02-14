package com.trifulcas.dao;

import java.sql.Date;

public class Actor {

	private int actor_id;
	private String first_name;
	private String last_name;
	private Date last_update;
	
	public Actor(int actor_id, String first_name, String last_name, Date last_update) {
		this.actor_id = actor_id;
		this.first_name = first_name;
		this.last_name = last_name;
		this.last_update = last_update;
	}
	
	/**
	 * @return the actor_id
	 */
	public int getActor_id() {
		return actor_id;
	}
	/**
	 * @param actor_id the actor_id to set
	 */
	public void setActor_id(int actor_id) {
		this.actor_id = actor_id;
	}
	/**
	 * @return the first_name
	 */
	public String getFirst_name() {
		return first_name;
	}
	/**
	 * @param first_name the first_name to set
	 */
	public void setFirst_name(String first_name) {
		this.first_name = first_name;
	}
	
	/**
	 * @return the last_name
	 */
	public String getLast_name() {
		return last_name;
	}
	/**
	 * @param last_name the last_name to set
	 */
	public void setLast_name(String last_name) {
		this.last_name = last_name;
	}
	/**
	 * @return the last_update
	 */
	public Date getLast_update() {
		return last_update;
	}
	/**
	 * @param last_update the last_update to set
	 */
	public void setLast_update(Date last_update) {
		this.last_update = last_update;
	}
	
	public String toString() {
		return actor_id+" | "+first_name+" | "+last_name+" | "+last_update;
	}
	
}
