package com.trifulcas.ApiAula.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import com.trifulcas.ApiAula.model.Aula;

public interface AulaRepository 
	extends JpaRepository<Aula, Integer> {
	  List<Aula> findByCapacidadGreaterThanEqual(Integer capacidad);

}
