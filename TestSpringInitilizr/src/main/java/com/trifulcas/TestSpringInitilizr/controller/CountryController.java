package com.trifulcas.TestSpringInitilizr.controller;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.trifulcas.TestSpringInitilizr.model.Country;
import com.trifulcas.TestSpringInitilizr.repository.CountryRepository;

@RestController
@RequestMapping("/api")
@CrossOrigin(origins = "*", allowedHeaders = "*")
public class CountryController {

	@Autowired
	CountryRepository countryRepository;

	@GetMapping("/countries")
	public ResponseEntity<List<Country>> getAll() {
		try {
			List<Country> paises = countryRepository.findAll();

			if (paises.isEmpty()) {
				return new ResponseEntity<>(HttpStatus.NO_CONTENT);
			}
			return new ResponseEntity<>(paises, HttpStatus.OK);
		} catch (Exception e) {
			return new ResponseEntity<>(null, HttpStatus.INTERNAL_SERVER_ERROR);
		}
	}

	@GetMapping("/countries/{id}")
	public ResponseEntity<Country> getById(@PathVariable("id") int id) {
		Optional<Country> country = countryRepository.findById(id);

		if (country.isPresent()) {
			return new ResponseEntity<>(country.get(), HttpStatus.OK);
		} else {
			return new ResponseEntity<>(HttpStatus.NOT_FOUND);
		}
	}

	@PostMapping("/countries")
	public ResponseEntity<Country> create(@RequestBody Country country) {
		try {
			Country newCountry = new Country(0, country.getCountry());
			newCountry = countryRepository.save(newCountry);
			return new ResponseEntity<>(newCountry, HttpStatus.OK);
		} catch (Exception ex) {
			System.out.println(ex.getMessage());
			return new ResponseEntity<>(null, HttpStatus.INTERNAL_SERVER_ERROR);
		}
	}

	@PutMapping("countries/{id}")
	public ResponseEntity<Country> update(@PathVariable("id") int id, @RequestBody Country country) {
		try {
			Optional<Country> newCountry = countryRepository.findById(id);
			if (newCountry.isPresent()) {
				Country updCountry = newCountry.get();
				updCountry.setCountry(country.getCountry());
				updCountry = countryRepository.save(updCountry);
				return new ResponseEntity<>(updCountry, HttpStatus.OK);
			} else {
				return new ResponseEntity<>(HttpStatus.NOT_FOUND);
			}

		} catch (Exception ex) {
			System.out.println(ex.getMessage());
			return new ResponseEntity<>(null, HttpStatus.INTERNAL_SERVER_ERROR);
		}
	}

	@DeleteMapping("/countries/{id}")
	public ResponseEntity<HttpStatus> delete(@PathVariable("id") int id){
		try {
			countryRepository.deleteById(id);
			return ResponseEntity.ok(null);
		} catch (Exception ex) {
			System.out.println(ex.getMessage());
			return new ResponseEntity<>(null, HttpStatus.INTERNAL_SERVER_ERROR);
		}	
	}
	
}
