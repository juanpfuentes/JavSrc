package com.trifulcas.CrudSpring.controller;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import com.trifulcas.CrudSpring.model.Actor;
import com.trifulcas.CrudSpring.repository.ActorRepository;

@Controller
public class ActorController {
	@Autowired
	private ActorRepository actorRepository;

	@GetMapping("/actors")
	public String getAll(Model model, @Param("keyword") String keyword) {

		try {
			List<Actor> actors = new ArrayList<Actor>();
			
			if (keyword == null) {
				actorRepository.findAll().forEach(actors::add);
			} else {
				actorRepository.findByLastNameContainingIgnoreCase(keyword).forEach(actors::add);
				model.addAttribute("keyword", keyword);
			}
			model.addAttribute("actors", actors);
		} catch (Exception e) {
			model.addAttribute("message", e.getMessage());
		}
		return "actors";
	}

	@GetMapping("/actors/new")
	public String add(Model model) {
		Actor actor = new Actor();
		model.addAttribute("actor", actor);
		model.addAttribute("pageTitle", "Crear actor");

		return "actor_form";
	}

	@PostMapping("/actors/save")
	public String save(Actor actor, RedirectAttributes redirectAttributes) {
		try {
			actorRepository.save(actor);
			redirectAttributes.addFlashAttribute("message", "Actor guardado");
		} catch (Exception e) {
			redirectAttributes.addFlashAttribute("message", e.getMessage());
		}
		return "redirect:/actors";
	}

	@GetMapping("/actors/{id}")
	public String editTutorial(@PathVariable("id") Integer id, Model model, RedirectAttributes redirectAttributes) {
		try {
			Actor actor = actorRepository.findById(id).get();

			model.addAttribute("actor", actor);
			model.addAttribute("pageTitle", "Editar Actor (ID: " + id + ")");

			return "actor_form";
		} catch (Exception e) {
			redirectAttributes.addFlashAttribute("message", e.getMessage());
		}
		return "actor_form";
	}

	@GetMapping("/actors/delete/{id}")
	public String delete(@PathVariable("id") Integer id, Model model, RedirectAttributes redirectAttributes) {

		try {
			actorRepository.deleteById(id);

			redirectAttributes.addFlashAttribute("message", "Actor con id=" + id + " eliminado");
		} catch (Exception e) {
			redirectAttributes.addFlashAttribute("message", e.getMessage());
		}

		return "redirect:/actors";
	}

}
