package org.dam2.herencia;

import hibernateDAO.GenericJPADAO;

public class App {
	public static void main(String[] args) {
		GenericJPADAO<Persona, String> personaDAO = new GenericJPADAO<>(Persona.class, "Herencia");
		personaDAO.findAll().forEach(System.out::println);
	}
}
