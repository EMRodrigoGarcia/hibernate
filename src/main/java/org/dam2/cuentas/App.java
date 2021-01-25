package org.dam2.cuentas;

import hibernateDAO.GenericJPADAO;

public class App {

	public static void main(String[] args) {
		// TODO Auto-generated method stub
		GenericJPADAO<Cliente, String> clienteDAO = new GenericJPADAO<>(Cliente.class, "Cuentas");
		
		clienteDAO.findAll().forEach(System.out::println);
	}

}
