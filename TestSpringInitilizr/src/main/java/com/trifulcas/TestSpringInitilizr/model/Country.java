package com.trifulcas.TestSpringInitilizr.model;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Table;

@Entity
@Table(name = "country")
public class Country {
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "country_id")
	private int country_id;

	@Column(name = "country")
	private String country;

	public Country(int country_id, String country) {
		this.country_id = country_id;
		this.country = country;
	}

	public Country() {

	}

	public int getCountry_id() {
		return country_id;
	}

	public void setCountry_id(int country_id) {
		this.country_id = country_id;
	}

	public String getCountry() {
		return country;
	}

	public void setCountry(String country) {
		this.country = country;
	}

	@Override
	public String toString() {
		return "Country [country_id=" + country_id + ", country=" + country + "]";
	}

}
