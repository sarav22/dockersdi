package com.uniovi.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import com.uniovi.entities.Professor;
import com.uniovi.service.ProfessorService;
import com.uniovi.validators.AddProfessorValidator;

@Controller
public class ProfessorController {
	
	@Autowired // Inyectar el servicio
	private ProfessorService professorService;
	
	@Autowired
	private AddProfessorValidator professorValidator;

	@RequestMapping("/professor/delete/{id}")
	public String delete(@PathVariable Long id) {
		professorService.deleteProfessor(id);
		return "redirect:/professor/list";
	}


	@RequestMapping(value = "/professor/edit", method = RequestMethod.POST)
	public String setEdit(Model model,@ModelAttribute Professor professor) {
		Professor p = professorService.getProfessor(professor.getId());
		p.setName(professor.getName());
		p.setSurname(professor.getSurname());
		p.setCategory(professor.getCategory());
		professorService.addProfessor(p);
		return "redirect:/professor/details/"+professor.getId() ;
	}
	
	@RequestMapping(value = "/professor/edit/{id}")
	public String getEdit(Model model, @PathVariable Long id, @ModelAttribute Professor professor) {
		model.addAttribute("professor", professorService.getProfessor(id));
		return "professor/edit";
	}

	@RequestMapping(value = "/professor/add", method = RequestMethod.POST)
	public String setProfessor(@Validated Professor professor , BindingResult result) {
		professorValidator.validate(professor,result);
		if(result.hasErrors()) {
			return "professor/add";
		}
		professorService.addProfessor(professor);
		return "redirect:/professor/list";
	}
	
	@RequestMapping(value ="/professor/add")
	public String getProfessor(Model model) {
		model.addAttribute("professor", new Professor());
		return "professor/add";
	}


	@RequestMapping("/professor/details/{id}")
	public String getDetail(Model model, @PathVariable Long id) {
		model.addAttribute("professor", professorService.getProfessor(id));
		return "professor/details";
	}
	
	@RequestMapping("/professor/list")
	public String getList(Model model) {
		model.addAttribute("professorList", professorService.getProfessors());
		return "professor/list";
	}


}
