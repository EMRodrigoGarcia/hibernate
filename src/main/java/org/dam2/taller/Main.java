package org.dam2.taller;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.Optional;
import java.util.function.Predicate;
import java.util.stream.Collectors;

import daw.com.Teclado;

public class Main {

	public static void main(String[] args) {
		GenericJPADAO<Taller, Integer> tallerDAO = new GenericJPADAO<>(Taller.class, "Taller");
		insertCoches(tallerDAO);
		String dniConductor = Teclado.leerString("DNI");
		Conductor conAux = Conductor.builder().dni(dniConductor).build();
		
		//Predicate<Coche> tieneConductor = c -> c.getConductores().contains(conAux); 
		//eliminar todos los coches del conductor con dni dniConductor
		Iterable<Taller> talleres = tallerDAO.findAll();
		
		
		
		
		tallerDAO.findAll().forEach(System.out::println);
		
		
		
	}

	public static void insertCoches(GenericJPADAO<Taller, Integer> tallerDAO) {
		// TODO Auto-generated method stub
		Coche c1 = Coche.builder()
				.matricula("1234AAA")
				.color("rojo")
				.marca("ford")
				.modelo("fiesta")
				.build();
		Coche c2 = Coche.builder()
				.matricula("78945BBB")
				.color("verde")
				.marca("vw")
				.modelo("2cv")
				.build();
		Coche c3 = Coche.builder()
				.matricula("1111CCC")
				.color("blanco")
				.marca("seat")
				.modelo("ibiza")
				.build();

		Conductor con1 = Conductor.builder()
				.dni("001")
				.nombre("rodrigo")
				.coch(c1)
				.coch(c2)
				.coch(c3)
				.build();
		HashSet<Conductor> setConductor = new HashSet<>();
		setConductor.add(con1);
		c1.setConductores(setConductor);
		c2.setConductores(setConductor);
		c3.setConductores(setConductor);
		
		Taller t1 = Taller.builder()
				.nombre("Taller1")
				.aval(Integer.valueOf(1000))
				.coch(c1)
				.coch(c2)
				.coch(c3)
				.build();
		
		tallerDAO.save(t1);
		
		
		
	}
	
	
	
	
	/**
	 * 
	 * //añadir 3 talleres a BBDD
		//solicitar id de taller y añadir 2 coches
		// repetir la misma operacion
		
		/*Taller t1 = Taller.builder().nombre("Taller1").aval(3000).build();
		tallerDAO.save(t1);
		Taller t2 = Taller.builder().nombre("Taller2").aval(4000).build();		
		tallerDAO.save(t2);
		Taller t3 = Taller.builder().nombre("Taller3").aval(5000).build();
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

	 * 
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
