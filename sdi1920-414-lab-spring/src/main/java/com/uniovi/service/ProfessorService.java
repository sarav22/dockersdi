package com.uniovi.service;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.uniovi.entities.Professor;
import com.uniovi.repositories.ProfessorRepository;

@Service
public class ProfessorService {

	@Autowired
	private ProfessorRepository professorRepository;

	public List<Professor> getProfessors() {
		List<Professor> profs = new ArrayList<Professor>();
		professorRepository.findAll().forEach(profs::add);
		return profs;
	}

	public Professor getProfessor(Long dni) {
		return professorRepository.findById(dni).get();
	}

	public void addProfessor(Professor professor) {			
		professorRepository.save(professor);
	}

	public void deleteProfessor(Long dni) {
		professorRepository.deleteById(dni);
	}
}