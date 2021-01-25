package org.dam2.cuentas;

import java.io.Serializable;
import java.util.Set;
import java.util.stream.Collectors;

import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.Id;
import javax.persistence.Inheritance;
import javax.persistence.InheritanceType;
import javax.persistence.ManyToMany;
import javax.persistence.Table;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;
import lombok.Singular;
import lombok.experimental.SuperBuilder;

@SuperBuilder
@Data
@EqualsAndHashCode(onlyExplicitlyIncluded = true)
@NoArgsConstructor
@AllArgsConstructor

@Entity
@Table(name = "cuentas")
@Inheritance(strategy = InheritanceType.SINGLE_TABLE)
public abstract class Cuenta implements Serializable{
	
	//relacion muchos-muchos
	//relacion clientes - cuentas
	@Singular
	@ManyToMany(mappedBy = "cuentas", fetch = FetchType.EAGER)
	private Set<Cliente> clientes;
	//id
	@Id
	@EqualsAndHashCode.Include
	private String numCuenta;
	private int saldo;
	@Override
	public String toString() {
		return "Cuenta [clientes=" + clientes.stream().map(Cliente::getNombre).collect(Collectors.toList()) + ", numCuenta=" + numCuenta + ", saldo=" + saldo + "]";
	}
	
	public void ingresar(int cantidad) {
		this.setSaldo(this.getSaldo() + cantidad);
	}
	
	public abstract boolean retirar(int cantidad);
	
	public abstract boolean comprobarCantidadARetirar(int cantidad);
	
	public abstract boolean transferir(int cantidad, Cuenta cuentaRecibidora);
	
}
