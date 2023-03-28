package com.trifulcas.TestSpringInitilizr.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.trifulcas.TestSpringInitilizr.model.Country;

public interface CountryRepository extends JpaRepository<Country,Integer>{

}
