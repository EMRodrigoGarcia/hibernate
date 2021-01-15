package org.dam2.hibernate;

import java.time.LocalDate;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;


public class App {

	public static void main(String[] args) {
		EntityManagerFactory factory = Persistence.createEntityManagerFactory("hibernate");
		EntityManager manager = factory.createEntityManager();
		
		manager.getTransaction().begin();
		Alumno a = Alumno.builder().nombre("rodrigo").fechaNac(LocalDate.now()).build();
		manager.persist(a);
		manager.getTransaction().commit();
		System.out.println(manager.find(Alumno.class, Integer.valueOf(1)));
		manager.close();
	
	}

}
