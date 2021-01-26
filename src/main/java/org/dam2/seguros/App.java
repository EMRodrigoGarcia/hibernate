package org.dam2.seguros;

import hibernateDAO.GenericJPADAO;

public class App {

	public static void main(String[] args) {
		// TODO Auto-generated method stub
		GenericJPADAO<Seguro, Integer> seguroDAO = new GenericJPADAO<>(Seguro.class, "Seguros");
		seguroDAO.findAll().forEach(System.out::println);
	}

}
