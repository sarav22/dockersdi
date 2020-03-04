package com.uniovi.repositories;

import org.springframework.data.repository.CrudRepository;

import com.uniovi.entities.Professor;
import com.uniovi.entities.User;


public interface ProfessorRepository  extends CrudRepository<Professor, Long>{
	
	Professor findByDni(String dni);

}
