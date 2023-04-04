package com.trifulcas.ApiRestSpring.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import com.trifulcas.ApiRestSpring.model.Actor;

public interface ActorRepository extends JpaRepository<Actor, Long> {
	  List<Actor> findByLastNameContaining(String last_name);
	  List<Actor> findByFirstNameStartsWith(String name);
	}