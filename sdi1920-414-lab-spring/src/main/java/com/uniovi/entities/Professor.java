package com.uniovi.entities;

public class Professor {

	private Long dni;
	private String name;
	private String surname;
	private String category;
	
	public Professor() {
		
	}
	
	public Professor(Long dni, String name, String surname, String category) {
		super();
		this.dni = dni;
		this.name = name;
		this.surname = surname;
		this.category = category;
	}
	
	
	@Override
	public String toString() {
		return "Professor [dni=" + dni + ", name=" + name + ", surname=" + surname + ", category=" + category + "]";
	}


	public Long getDni() {
		return dni;
	}
	public void setDni(Long dni) {
		this.dni = dni;
	}
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public String getSurname() {
		return surname;
	}
	public void setSurname(String surname) {
		this.surname = surname;
	}
	public String getCategory() {
		return category;
	}
	public void setCategory(String category) {
		this.category = category;
	}

	
}