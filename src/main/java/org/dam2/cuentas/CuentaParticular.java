package org.dam2.cuentas;

import java.io.Serializable;

import javax.persistence.DiscriminatorValue;
import javax.persistence.Entity;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;
import lombok.ToString;
import lombok.experimental.SuperBuilder;

@SuperBuilder
@Data
@NoArgsConstructor
@AllArgsConstructor
@EqualsAndHashCode(callSuper = true)
@ToString(callSuper = true)
@Entity
@DiscriminatorValue(value = "PA")
public class CuentaParticular extends Cuenta implements Serializable {
	private boolean emitidaTarjeta;

	@Override
	public boolean comprobarCantidadARetirar(int cantidad) {
		boolean exito = false;

		int valorMaximoAvales = this.getClientes().stream().mapToInt(Cliente::getAval).sum();

		if (this.getSaldo() - cantidad < valorMaximoAvales / 2) {
			exito = true;
		}

		return exito;
	}
}
