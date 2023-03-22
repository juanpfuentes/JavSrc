package com.trifulcas.model;

import java.sql.Timestamp;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

@Entity
@Table(name = "address")
public class Address {
    
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "address_id")
    private int id;
    
    @Column(name = "address")
    private String address;
    
    @Column(name = "address2")
    private String address2;
    
    @Column(name = "district")
    private String district;
    
    @ManyToOne
    @JoinColumn(name = "city_id", referencedColumnName = "city_id")
    private City city;
    
    @Column(name = "postal_code")
    private String postalCode;
    
    @Column(name = "phone")
    private String phone;
    
    @Column(name = "last_update")
    private Timestamp lastUpdate;

	public Address(int id, String address, String address2, String district, City city, String postalCode, String phone,
			Timestamp lastUpdate) {
		this.id = id;
		this.address = address;
		this.address2 = address2;
		this.district = district;
		this.city = city;
		this.postalCode = postalCode;
		this.phone = phone;
		this.lastUpdate = lastUpdate;
	}
    
   public Address() {
	   
   }

public int getId() {
	return id;
}

public void setId(int id) {
	this.id = id;
}

public String getAddress() {
	return address;
}

public void setAddress(String address) {
	this.address = address;
}

public String getAddress2() {
	return address2;
}

public void setAddress2(String address2) {
	this.address2 = address2;
}

public String getDistrict() {
	return district;
}

public void setDistrict(String district) {
	this.district = district;
}

public City getCity() {
	return city;
}

public void setCity(City city) {
	this.city = city;
}

public String getPostalCode() {
	return postalCode;
}

public void setPostalCode(String postalCode) {
	this.postalCode = postalCode;
}

public String getPhone() {
	return phone;
}

public void setPhone(String phone) {
	this.phone = phone;
}

public Timestamp getLastUpdate() {
	return lastUpdate;
}

public void setLastUpdate(Timestamp lastUpdate) {
	this.lastUpdate = lastUpdate;
}

@Override
public String toString() {
	return "Address [id=" + id + ", address=" + address + ", address2=" + address2 + ", district=" + district
			+ ", city=" + city + ", postalCode=" + postalCode + ", phone=" + phone + ", lastUpdate=" + lastUpdate + "]";
}
   
    
}
