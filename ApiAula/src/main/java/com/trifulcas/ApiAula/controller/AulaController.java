package com.trifulcas.ApiAula.controller;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.trifulcas.ApiAula.model.Aula;
import com.trifulcas.ApiAula.repository.AulaRepository;

@RestController
@RequestMapping("/api")
public class AulaController {
	 @Autowired
	  AulaRepository aulaRepository;

	  @GetMapping("/aulas")
	  public ResponseEntity<List<Aula>> getAll(@RequestParam(required = false) Integer capacidad) {
	    try {
	      List<Aula> aulas= new ArrayList<Aula>();

	      if (capacidad == null)
	    	  aulaRepository.findAll().forEach(aulas::add);
	      else
	    	  aulaRepository.findByCapacidadGreaterThanEqual(capacidad).forEach(aulas::add);
	      
	      if (aulas.isEmpty()) {
	        return new ResponseEntity<>(HttpStatus.NO_CONTENT);
	      }

	      return new ResponseEntity<>(aulas, HttpStatus.OK);
	    } catch (Exception e) {
	      return new ResponseEntity<>(null, HttpStatus.INTERNAL_SERVER_ERROR);
	    }
	  }
}
