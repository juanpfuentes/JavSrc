package com.trifulcas.model;

import java.sql.Timestamp;
import java.util.ArrayList;
import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import javax.persistence.Table;



@Entity
@Table(name = "country")
public class Country {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "country_id")
    private int countryId;
    
    @Column(name = "country", nullable = false, length = 50)
    private String country;
    
    @Column(name = "last_update", nullable = false)
    private Timestamp lastUpdate;
    
    // Constructor vacío para Hibernate
    public Country() {}
    
    // Constructor con parámetros para crear objetos Country
    public Country(String country, Timestamp lastUpdate) {
        this.country = country;
        this.lastUpdate = lastUpdate;
    }
    
    // Getters y Setters de las propiedades
    public int getCountryId() {
        return countryId;
    }

    public void setCountryId(Short countryId) {
        this.countryId = countryId;
    }

    public String getCountry() {
        return country;
    }

    public void setCountry(String country) {
        this.country = country;
    }

    public Timestamp getLastUpdate() {
        return lastUpdate;
    }

    public void setLastUpdate(Timestamp lastUpdate) {
        this.lastUpdate = lastUpdate;
    }

	@Override
	public String toString() {
		return "Country [countryId=" + countryId + ", country=" + country + ", lastUpdate=" + lastUpdate + ", cities="
				 + "]";
	}

   
}
