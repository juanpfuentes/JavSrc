package com.trifulcas.CrudSpring.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import com.trifulcas.CrudSpring.model.Actor;

public interface ActorRepository extends JpaRepository<Actor, Integer> {
	  List<Actor> findByLastNameContainingIgnoreCase(String last_name);
	}