package com.trifulcas.test;

import com.trifulcas.model.Address;
import com.trifulcas.model.City;
import com.trifulcas.model.Country;
import com.trifulcas.model.DAO;

public class TestGeneric {
	public static void main(String[] args) {
		DAO<City> alumnoDao = new DAO<City>(City.class);
		System.out.println(alumnoDao.get(2));
		DAO<Address> co = new DAO<Address>(Address.class);
		System.out.println(co.get(1));
		
	}
}
