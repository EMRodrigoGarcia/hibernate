package org.dam2.cuentas;

import javax.persistence.DiscriminatorValue;
import javax.persistence.Entity;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;
import lombok.ToString;
import lombok.experimental.SuperBuilder;

@Data
@NoArgsConstructor
@AllArgsConstructor
@SuperBuilder
@EqualsAndHashCode(callSuper = true)
@ToString(callSuper = true)

@Entity
@DiscriminatorValue(value = "EM")
public class CuentaEmpresa extends Cuenta {
	private String nombreEmpresa;
	private String cifEmpresa;
	private boolean localPropioAlquilado;
	
	@Override
	public boolean comprobarCantidadARetirar(int cantidad) {
		boolean exito = false;
		
		int valorMaximoAvales = this.getClientes().stream().mapToInt(Cliente::getAval).sum();
		
		if (this.getSaldo() - cantidad <= valorMaximoAvales * 2) {
			exito = true;
		}
		
		return exito;
	}
}
