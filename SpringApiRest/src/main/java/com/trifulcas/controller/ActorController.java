package com.trifulcas.controller;

import java.util.ArrayList;
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
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.trifulcas.model.Actor;
import com.trifulcas.repository.ActorRepository;

@RestController
@RequestMapping("/api")
public class ActorController {

  @Autowired
  ActorRepository actorRepository;

  @GetMapping("/actors")
  public ResponseEntity<List<Actor>> getAllActors(@RequestParam(required = false) String last_name) {
    try {
      List<Actor> actores = new ArrayList<Actor>();

      if (last_name == null)
        actorRepository.findAll().forEach(actores::add);
      else
        actorRepository.findByLast_nameContaining(last_name).forEach(actores::add);

      if (actores.isEmpty()) {
        return new ResponseEntity<>(HttpStatus.NO_CONTENT);
      }

      return new ResponseEntity<>(actores, HttpStatus.OK);
    } catch (Exception e) {
      return new ResponseEntity<>(null, HttpStatus.INTERNAL_SERVER_ERROR);
    }
  }

  @GetMapping("/actors/{id}")
  public ResponseEntity<Actor> getActorById(@PathVariable("id") long id) {
    Optional<Actor> actor = actorRepository.findById(id);

    if (actor.isPresent()) {
      return new ResponseEntity<>(actor.get(), HttpStatus.OK);
    } else {
      return new ResponseEntity<>(HttpStatus.NOT_FOUND);
    }
  }

  @PostMapping("/actors")
  public ResponseEntity<Actor> createActor(@RequestBody Actor actor) {
    try {
      Actor _actor = actorRepository
          .save(new Actor(0,actor.getFirst_name(), actor.getLast_name(), null));
      return new ResponseEntity<>(_actor, HttpStatus.CREATED);
    } catch (Exception e) {
      return new ResponseEntity<>(null, HttpStatus.INTERNAL_SERVER_ERROR);
    }
  }

  @PutMapping("/actors/{id}")
  public ResponseEntity<Actor> updateActor(@PathVariable("id") long id, @RequestBody Actor newActor) {
    Optional<Actor> actor = actorRepository.findById(id);

    if (actor.isPresent()) {
      Actor _actor = actor.get();
      _actor.setFirst_name(newActor.getFirst_name());
      _actor.setLast_name(newActor.getLast_name());
      _actor.setLast_update(newActor.getLast_update());
      return new ResponseEntity<>(actorRepository.save(_actor), HttpStatus.OK);
    } else {
      return new ResponseEntity<>(HttpStatus.NOT_FOUND);
    }
  }

  @DeleteMapping("/actors/{id}")
  public ResponseEntity<HttpStatus> deleteActor(@PathVariable("id") long id) {
    try {
      actorRepository.deleteById(id);
      return new ResponseEntity<>(HttpStatus.NO_CONTENT);
    } catch (Exception e) {
      return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
    }
  }

  @DeleteMapping("/actors")
  public ResponseEntity<HttpStatus> deleteAllActors() {
    try {
      actorRepository.deleteAll();
      return new ResponseEntity<>(HttpStatus.NO_CONTENT);
    } catch (Exception e) {
      return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
    }

  }

  

}