package com.trifulcas.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import com.trifulcas.model.Actor;

public interface ActorRepository extends JpaRepository<Actor, Long> {
	  List<Actor> findByLast_nameContaining(String last_name);
	}