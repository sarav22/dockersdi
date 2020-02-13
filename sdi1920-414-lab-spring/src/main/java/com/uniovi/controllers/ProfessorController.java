package com.uniovi.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import com.uniovi.entities.Professor;
import com.uniovi.service.ProfessorService;

@Controller
public class ProfessorController {
	
	@Autowired // Inyectar el servicio
	private ProfessorService professorService;

	@RequestMapping("/professor/delete/{dni}")
	public String delete(@PathVariable Long dni) {
		professorService.deleteProfessor(dni);
		return "redirect:/professor/list";
	}


	@RequestMapping(value = "/professor/edit", method = RequestMethod.POST)
	public String setEdit(Model model,@ModelAttribute Professor professor) {
		Professor p = professorService.getProfessor(professor.getDni());
		p.setName(professor.getName());
		p.setSurname(professor.getSurname());
		p.setCategory(professor.getCategory());
		professorService.addProfessor(p);
		return "redirect:/professor/details/"+professor.getDni() ;
	}
	
	@RequestMapping(value = "/professor/edit/{dni}")
	public String getEdit(Model model, @PathVariable Long dni, @ModelAttribute Professor professor) {
		model.addAttribute("professor", professorService.getProfessor(dni));
		return "professor/edit";
	}

	@RequestMapping(value = "/professor/add", method = RequestMethod.POST)
	public String setProfessor(@ModelAttribute Professor professor) {
		professorService.addProfessor(professor);
		return "redirect:/professor/list";
	}
	
	@RequestMapping(value ="/professor/add")
	public String getProfessor() {
		return "professor/add";
	}


	@RequestMapping("/professor/details/{dni}")
	public String getDetail(Model model, @PathVariable Long dni) {
		model.addAttribute("professor", professorService.getProfessor(dni));
		return "professor/details";
	}
	
	@RequestMapping("/professor/list")
	public String getList(Model model) {
		model.addAttribute("professorList", professorService.getProfessors());
		return "professor/list";
	}


}
