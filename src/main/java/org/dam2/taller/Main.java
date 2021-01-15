package org.dam2.taller;

import java.util.Optional;

import daw.com.Teclado;

public class Main {

	public static void main(String[] args) {
		GenericJPADAO<Taller, Integer> tallerDAO = new GenericJPADAO<>(Taller.class, "Taller");
		insertCoches(tallerDAO);
		// mostrar todos los datos de todos los talleres
		tallerDAO.findAll().forEach(System.out::println);

		//pedir matricula de coche y si existe lo eliminamos de BBDD
		
	}

	public static void insertCoches(GenericJPADAO<Taller, Integer> tallerDAO) {
		// TODO Auto-generated method stub
		//añadir 3 talleres a BBDD
		//solicitar id de taller y añadir 2 coches
		// repetir la misma operacion
		
		Taller t1 = Taller.builder()
				.nombre("Taller1")
				.aval(3000)
				.build();
		tallerDAO.save(t1);
		Taller t2 = Taller.builder()
				.nombre("Taller2")
				.aval(4000)
				.build();		
		tallerDAO.save(t2);
		Taller t3 = Taller.builder()
				.nombre("Taller3")
				.aval(5000)
				.build();
		tallerDAO.save(t3);
		tallerDAO.findAll().forEach(t -> System.out.println("Id: " + t.getId() + ", Nombre: " + t.getNombre()));
		
		for(int i = 0; i < 2; i++) {
			int idTallerBuscado = Teclado.leerInt("Id de taller");	
			Optional<Taller> opTaller = tallerDAO.findById(Integer.valueOf(idTallerBuscado));
			Taller t = opTaller.orElseGet(Taller::new);

			for(int j = 0; j < 2; j++) {
				Coche c = Coche.builder()
						.matricula(Teclado.leerString("Matricula"))
						.marca(Teclado.leerString("Marca"))
						.modelo(Teclado.leerString("Modelo"))
						.color(Teclado.leerString("Color"))
						.conductor(Conductor.builder()
								.dni(Teclado.leerString("DNI conductor"))
								.nombre(Teclado.leerString("Nombre conductor")).build())
						.build();
				
				t.getCoches().add(c);
			}
			tallerDAO.update(t);

		}
		
		
		
		
	}
	
	
	
	
	/**
	 * 
	 * .coch(Coche.builder()
						.matricula("001")
						.color("verde").
						modelo("fiesta")
						.marca("ford")
						.conductor(Conductor.builder()
								.dni("001")
								.nombre("conductor1").build()).build())
				.coch(Coche.builder()
						.matricula("002")
						.color("rojo").
						modelo("panda")
						.marca("seat")
						.conductor(Conductor.builder()
								.dni("002")
								.nombre("conductor2").build()).build())
	 * .coch(Coche.builder()
						.matricula("003")
						.color("blanco").
						modelo("multipla")
						.marca("fiat")
						.conductor(Conductor.builder()
								.dni("003")
								.nombre("conductor3").build()).build())
				.coch(Coche.builder()
						.matricula("004")
						.color("rosa").
						modelo("2cv")
						.marca("citroen")
						.conductor(Conductor.builder()
								.dni("004")
								.nombre("conductor4").build()).build())
	 * 
	 * 
	 * .coch(Coche.builder()
						.matricula("005")
						.color("negro").
						modelo("cordoba")
						.marca("seat")
						.conductor(Conductor.builder()
								.dni("005")
								.nombre("conductor5").build()).build())
				.coch(Coche.builder()
						.matricula("006")
						.color("violeta").
						modelo("jeep")
						.marca("jeep")
						.conductor(Conductor.builder()
								.dni("006")
								.nombre("conductor6").build()).build())
	 * 
	 * 
	 * 
	 * */

}
