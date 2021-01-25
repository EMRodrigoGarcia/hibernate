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
public abstract class Cuenta implements Serializable {

	// id
	@Id
	@EqualsAndHashCode.Include
	private String numCuenta;
	private int saldo;

	// relacion muchos-muchos
	// relacion clientes - cuentas
	@Singular
	@ManyToMany(mappedBy = "cuentas", fetch = FetchType.EAGER)
	private Set<Cliente> clientes;

	@Override
	public String toString() {
		return "Cuenta [clientes=" + clientes.stream().map(Cliente::getNif).collect(Collectors.toList())
				+ ", numCuenta=" + numCuenta + ", saldo=" + saldo + "]";
	}

	public void ingresar(int cantidad) {
		this.setSaldo(this.getSaldo() + cantidad);
	}

	public boolean retirar(int cantidad) {
		boolean exito = false;

		if (comprobarCantidadARetirar(cantidad)) {
			this.setSaldo(this.getSaldo() - cantidad);
			exito = true;
		} else {
			exito = false;
		}

		return exito;
	}

	public abstract boolean comprobarCantidadARetirar(int cantidad);

	public boolean transferir(int cantidad, Cuenta cuentaRecibidora) {
		boolean exito = false;
		calcularComision(cantidad);

		if (comprobarCantidadARetirar(cantidad)) {
			this.setSaldo(this.getSaldo() - cantidad);
			cuentaRecibidora.setSaldo(cuentaRecibidora.getSaldo() + cantidad);
			exito = true;
		} else {
			exito = false;
		}

		return exito;
	}

	public void calcularComision(int cantidad) {
		float ratioComision = 0.2f;
		int maxComision = 4;
		if (this instanceof CuentaEmpresa) {
			ratioComision = 0.1f;
			maxComision = 6;
		}
		// calculo de comision
		if (cantidad * ratioComision < maxComision) {
			this.setSaldo(this.getSaldo() - (int) (cantidad * ratioComision));
		} else {
			this.setSaldo(this.getSaldo() - maxComision);
		}
	}

}
