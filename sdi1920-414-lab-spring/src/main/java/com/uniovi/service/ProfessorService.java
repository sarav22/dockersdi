package com.uniovi.service;

import java.util.LinkedList;
import java.util.List;

import javax.annotation.PostConstruct;

import org.springframework.stereotype.Service;

import com.uniovi.entities.Professor;

@Service
public class ProfessorService {

	private List<Professor> professorList = new LinkedList<Professor>();

	@PostConstruct
	public void init() {
		professorList.add(new Professor(123L, "Juan", "García", ""));
		professorList.add(new Professor(234L, "Laura","Martínez", ""));
	}

	public List<Professor> getProfessors() {
		return professorList;
	}

	public Professor getProfessor(Long dni) {
		return professorList.stream().filter(professor -> professor.getDni().equals(dni)).findFirst().get();
	}

	public void addProfessor(Professor professor) {
		// Si en Id es null le asignamos el ultimo + 1 de la lista
		if (professor.getDni() == null) {
			professor.setDni(professorList.get(professorList.size() - 1).getDni() + 1);
		}
		professorList.add(professor);
	}

	public void deleteProfessor(Long dni) {
		professorList.removeIf(professor -> professor.getDni().equals(dni));
	}
}