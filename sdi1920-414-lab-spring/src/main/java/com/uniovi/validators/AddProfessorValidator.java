package com.uniovi.validators;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.validation.Errors;
import org.springframework.validation.ValidationUtils;
import org.springframework.validation.Validator;

import com.uniovi.entities.Professor;
import com.uniovi.entities.User;
import com.uniovi.service.ProfessorService;
@Component
public class AddProfessorValidator implements Validator {

	@Autowired
	ProfessorService professorsService;

	@Override
	public boolean supports(Class<?> aClass) {
		return User.class.equals(aClass);
	}

	@Override
	public void validate(Object target, Errors errors) {
		Professor professor = (Professor) target;
		ValidationUtils.rejectIfEmptyOrWhitespace(errors, "dni", "Error.empty");
		char[] professorDni = professor.getDni().toCharArray();
		if (professorDni.length!=9) {
			errors.rejectValue("dni", "Error.professor.dnilength");
		}
		for (int i = 0; i < professorDni.length; i++) {
			if(i==professorDni.length-1) {
				if (!Character.isAlphabetic(professorDni[i])) {
					errors.rejectValue("dni", "Error.professor.digit");
				}
				
			}else {
				if (!Character.isDigit(professorDni[i])) {
					errors.rejectValue("dni", "Error.professor.digit");
				}
			}
		}
		
		for (Professor p : professorsService.getProfessors()) {
			if(p.getDni().equals(professor.getDni())) {
				errors.rejectValue("dni", "Error.professor.unique");
			}
		}
	}
}
