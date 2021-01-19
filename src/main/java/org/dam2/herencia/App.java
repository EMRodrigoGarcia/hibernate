package org.dam2.herencia;

import daw.com.Teclado;
import hibernateDAO.GenericJPADAO;

public class App {
	public static void main(String[] args) {
		GenericJPADAO<Persona, String> personaDAO = new GenericJPADAO<>(Persona.class, "Herencia");
		personaDAO.findAll().forEach(System.out::println);
		
		addPersonas(personaDAO);
		
		GenericJPADAO<Profesor, String> profesorDAO = new GenericJPADAO<>(Profesor.class, "Herencia");
		profesorDAO.findAll().forEach(System.out::println);
		
		GenericJPADAO<Alumno, String> alumnoDAO = new GenericJPADAO<>(Alumno.class, "Herencia");
		alumnoDAO.findAll().forEach(System.out::println);
		
		String nif = Teclado.leerString("NIF");
		
		Persona p = personaDAO.findById(nif).orElse(new Persona());
		
		personaDAO.delete(p);
		
		personaDAO.findAll().forEach(System.out::println);
	}

	private static void addPersonas(GenericJPADAO<Persona, String> personaDAO) {
		// TODO Auto-generated method stub
		Persona p1 = Persona.builder().nif("001").nombre("Persona1").build();
		Persona p2 = Persona.builder().nif("002").nombre("Persona2").build();
		Persona p3 = Persona.builder().nif("003").nombre("Persona3").build();
		Persona p4 = Persona.builder().nif("004").nombre("Persona4").build();
		
		personaDAO.save(p1);
		personaDAO.save(p2);
		personaDAO.save(p3);
		personaDAO.save(p4);
		
		Profesor pr1 = Profesor.builder().nif("005").nombre("Profesor1").departamento("Informatica").despacho("01").build();
		Profesor pr2 = Profesor.builder().nif("006").nombre("Profesor2").departamento("Lengua").despacho("02").build();
		
		personaDAO.save(pr1);
		personaDAO.save(pr2);
		
		Alumno al1 = Alumno.builder().nif("007").nombre("Alumno1").grupo("Gr1").estudiosAnteriores("Bachillerato").build();
		Alumno al2 = Alumno.builder().nif("008").nombre("Alumno2").grupo("Gr2").estudiosAnteriores("Grado Medio").build();
		
		personaDAO.save(al1);
		personaDAO.save(al2);
	}
	
	
}
