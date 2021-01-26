package org.dam2.cuentas;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.List;

import daw.com.Teclado;
import hibernateDAO.GenericJPADAO;

public class App {

	public static void main(String[] args) {
		// TODO Auto-generated method stub
		GenericJPADAO<Cliente, String> clienteDAO = new GenericJPADAO<>(Cliente.class, "Cuentas");
		GenericJPADAO<Cuenta, String> cuentaDAO = new GenericJPADAO<>(Cuenta.class, "Cuentas");
		cargarDatos(clienteDAO);
		//clienteDAO.findAll().forEach(System.out::println);
/*
		menuCuenta(cuentaDAO);
		
		//ingresar dinero
		//n de cuenta
		//cantidad
		//mostrar saldo
		cuentaDAO.findAll().forEach(c -> System.out.println(c.getNumCuenta()));
		String nCuenta = Teclado.leerString("N de cuenta");
		int cantidad = Teclado.leerInt("Cantidad a ingresar");
		
		Cuenta c = cuentaDAO.findById(nCuenta).orElse(CuentaParticular.builder().build());
		
		c.ingresar(cantidad);
		
		cuentaDAO.update(c);
		
		
		//retirar dinero
		//n de cuenta
		//cantidad a retirar
		//mostrar saldo actual, si ha sido posible, si no, se mostrara la cantidad maxima
		cuentaDAO.findAll().forEach(cu -> System.out.println(cu.getNumCuenta()));
		nCuenta = Teclado.leerString("N de cuenta");
		cantidad = Teclado.leerInt("Cantidad");
		
		c = cuentaDAO.findById(nCuenta).orElse(CuentaParticular.builder().build());
		if (c.retirar(cantidad)) {
			System.out.println("Se ha realizado la operacion con exito");
			System.out.println("Saldo nuevo: " + c.getSaldo());
		}else {
			System.out.println("Fallo en la operacion");
		}
		
		//realizar transferencia
		//n de cuenta origen
		//n de cuenta destino
		//cantidad a transferir
		//si se puede realizar mostrar nuevo sald, comision
		// si no, mostrar cantidad maxima
		cuentaDAO.findAll().forEach(cu -> System.out.println(cu.getNumCuenta()));
		String nCuentaOrigen = Teclado.leerString("Cuenta origen");
		String nCuentaDestino = Teclado.leerString("Cuenta destino");
		cantidad = Teclado.leerInt("Cantidad a transferir");
		Cuenta cOrigen = cuentaDAO.findById(nCuentaOrigen).orElse(CuentaParticular.builder().build());
		Cuenta cDestino = cuentaDAO.findById(nCuentaDestino).orElse(CuentaParticular.builder().build());
		
		if (cOrigen.transferir(cantidad, cDestino)) {
			System.out.println("Transferencia realizada con exito");
		}else {
			System.out.println("Fallo en la transferencia");
		}
		
		
		cuentaDAO.findAll().forEach(cu -> System.out.println(cu.getNumCuenta()));
		nCuenta = Teclado.leerString("N cuenta");
		c = cuentaDAO.findById(nCuenta).orElse(CuentaParticular.builder().build());
		
		System.out.println(c);
		System.out.println("Hora del sistema: " + LocalDateTime.now().format(DateTimeFormatter.ofPattern("dd/MM/yyyy hh:mm")));
		*/
		//aniadir un cliente ya existente a una cuenta ya existente
		
		addClienteExistente(cuentaDAO, clienteDAO);
		cuentaDAO.findAll().forEach(System.out::println);
	}

	private static void addClienteExistente(GenericJPADAO<Cuenta, String> cuentaDAO,
			GenericJPADAO<Cliente, String> clienteDAO) {

		// buscar clientes
		// seleccionar cliente
		// buscar cuentas
		// seleccionar cuenta
		// add cliente a cuenta
		String nif;
		String nCuenta;
		clienteDAO.findAll().forEach(cli -> System.out.println(cli.getNif()));
		do {
			nif = Teclado.leerString("Nif");
		} while (clienteDAO.findById(nif).isEmpty());
		
		Cliente c = clienteDAO.findById(nif).get();
		
		cuentaDAO.findAll().forEach(cue -> System.out.println(cue.getNumCuenta()));
		do {
			nCuenta = Teclado.leerString("ncc");
		} while (cuentaDAO.findById(nCuenta).isEmpty());
		
		Cuenta cu = cuentaDAO.findById(nCuenta).get();
		
		//cu.getClientes().add(c);
		c.getCuentas().add(cu);
		//cuentaDAO.update(cu);
		clienteDAO.update(c);
	}

	public static void menuCuenta(GenericJPADAO<Cuenta, String> cuentaDAO) {
		// seleccionar si la cuenta es de empresa o particular
		// conseguir los datos de cuenta + datos exclusivos de clase
		// aniadir clientes como titulares (seleccionar datos)
		Cuenta c = null;
		do {
			if (Teclado.leerString("Particular (1) o empresa (2)").equals("1")) {
				c = CuentaParticular.builder()
						.numCuenta(Teclado.leerString("Num cuenta"))
						.saldo(Teclado.leerInt("Saldo"))
						.emitidaTarjeta(Teclado.leerString("Emitida tarjeta? (S/N)").equalsIgnoreCase("S"))
						.cliente(Cliente.builder()
								.nif(Teclado.leerString("nif"))
								.nombre(Teclado.leerString("Nombre"))
								.aval(Teclado.leerInt("aval"))
								.telefono(Telefono.builder()
										.numero(Teclado.leerString("Numero"))
										.compania("Compania")
										.build())
								.build())
						.build();
			} else {
				c = CuentaEmpresa.builder()
						.numCuenta(Teclado.leerString("Num cuenta"))
						.saldo(Teclado.leerInt("Saldo"))
						.cifEmpresa(Teclado.leerString("Cif empresa"))
						.nombreEmpresa(Teclado.leerString("Nombre empresa"))
						.cliente(Cliente.builder()
								.nif(Teclado.leerString("nif"))
								.nombre(Teclado.leerString("Nombre"))
								.aval(Teclado.leerInt("aval"))
								.telefono(Telefono.builder()
										.numero(Teclado.leerString("Numero"))
										.compania("Compania")
										.build())
								.build())
						.build();
			}
			cuentaDAO.save(c);
		} while (Teclado.leerString("Continuar? (S/N)").equalsIgnoreCase("S"));
	}

	public static void cargarDatos(GenericJPADAO<Cliente, String> clienteDAO) {
		CuentaParticular cp1 = CuentaParticular.builder().numCuenta("001").saldo(3000).emitidaTarjeta(true).build();
		CuentaEmpresa ce1 = CuentaEmpresa.builder().numCuenta("002").saldo(30000000).cifEmpresa("01")
				.nombreEmpresa("empresa1").build();

		List<Cuenta> cuentas = new ArrayList<>();
		cuentas.add(cp1);
		cuentas.add(ce1);

		Cliente c1 = Cliente.builder().nif("0001").nombre("Rodrigo").aval(300)
				.telefono(Telefono.builder().numero("123456789").compania("Movistar").build()).cuentas(cuentas).build();
		clienteDAO.save(c1);

		CuentaParticular cp2 = CuentaParticular.builder().numCuenta("003").saldo(2000).emitidaTarjeta(false).build();
		CuentaEmpresa ce2 = CuentaEmpresa.builder().numCuenta("004").saldo(30000000).cifEmpresa("02")
				.nombreEmpresa("empresa2").build();

		cuentas = new ArrayList<>();
		cuentas.add(cp2);
		cuentas.add(ce2);

		Cliente c2 = Cliente.builder().nif("0002").nombre("Alejandro").aval(200)
				.telefono(Telefono.builder().numero("789456123").compania("Vodafone").build()).cuentas(cuentas).build();
		clienteDAO.save(c2);
	}

}
