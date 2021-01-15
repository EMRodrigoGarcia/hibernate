package org.dam2.taller;

public class Main {

	public static void main(String[] args) {
		GenericJPADAO<Taller, Integer> tallerDAO = new GenericJPADAO<>(Taller.class, "Taller");
		insertCoches(tallerDAO);
		// mostrar todos los datos de todos los talleres
		//pedir matricula de coche y si existe lo eliminamos de BBDD
		
		tallerDAO.findAll().forEach(System.out::println);
	}

	public static void insertCoches(GenericJPADAO<Taller, Integer> tallerDAO) {
		// TODO Auto-generated method stub
		//añadir 3 coches a BBDD
		//solicitar id de taller y añadir 2 coches
		// repetir la misma operacion
		
		Taller t1 = Taller.builder()
				.id(1)
				.nombre("Taller1")
				.aval(3000)
				.build();
		tallerDAO.save(t1);
		Taller t2 = Taller.builder()
				.id(2)
				.nombre("Taller2")
				.aval(4000)
				.build();		
		tallerDAO.save(t2);
		Taller t3 = Taller.builder()
				.id(3)
				.nombre("Taller3")
				.aval(5000)
				.build();
		tallerDAO.save(t3);
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
