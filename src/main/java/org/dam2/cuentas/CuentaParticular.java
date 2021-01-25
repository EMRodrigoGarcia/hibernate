package org.dam2.cuentas;

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
public class CuentaParticular extends Cuenta {
	private boolean emitidaTarjeta;

	@Override
	public boolean retirar(int cantidad) {
		// TODO Auto-generated method stub
		return false;
	}

	@Override
	public boolean comprobarCantidadARetirar(int cantidad) {
		// TODO Auto-generated method stub
		return false;
	}

	@Override
	public boolean transferir(int cantidad, Cuenta cuentaRecibidora) {
		// TODO Auto-generated method stub
		return false;
	}
}
