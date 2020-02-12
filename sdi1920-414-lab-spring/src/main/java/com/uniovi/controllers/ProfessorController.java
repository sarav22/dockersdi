package com.uniovi.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import com.uniovi.entities.Professor;
import com.uniovi.service.ProfessorService;

@RestController
public class ProfessorController {
	
	@Autowired // Inyectar el servicio
	private ProfessorService professorService;

	@RequestMapping("/professor/delete/{dni}")
	public String delete(@PathVariable Long dni) {
		professorService.deleteProfessor(dni);
		return "Deleted";
	}

	@RequestMapping(value = "/professor/edit", method = RequestMethod.POST)
	public String edit(@ModelAttribute Professor professor) {
		professorService.deleteProfessor(professor.getDni());
		professorService.addProfessor(professor);
		return "Edited professor";
	}

	@RequestMapping(value = "/professor/add", method = RequestMethod.POST)
	public String setProfessor(@ModelAttribute Professor professor) {
		professorService.addProfessor(professor);
		return "added";
	}


	@RequestMapping("/professor/details/{dni}")
	public String getDetail(@PathVariable Long dni) {
		return professorService.getProfessor(dni).toString();
	}
}
