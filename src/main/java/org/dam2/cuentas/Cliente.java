package org.dam2.cuentas;

import java.util.Set;
import java.util.stream.Collectors;

import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.JoinTable;
import javax.persistence.ManyToMany;
import javax.persistence.OneToMany;
import javax.persistence.Table;

import org.hibernate.annotations.ManyToAny;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;
import lombok.Singular;
import lombok.experimental.SuperBuilder;

@SuperBuilder
@Data
@NoArgsConstructor
@AllArgsConstructor
@EqualsAndHashCode(onlyExplicitlyIncluded = true)

@Entity
@Table(name = "clientes")
public class Cliente {
	@Id
	@EqualsAndHashCode.Include
	private String nif;
	private String nombre;
	private int aval;

	// relacion 1-muchos
	@Singular
	@OneToMany(cascade = CascadeType.ALL, fetch = FetchType.EAGER)
	@JoinColumn(name = "FK_CLIENTE")
	private Set<Telefono> telefonos;

	// relacion muchos-muchos
	@Singular
	@ManyToMany(cascade = CascadeType.ALL, fetch = FetchType.EAGER)
	@JoinTable(name = "REL_CLIENTES_CUENTAS", joinColumns = {
			@JoinColumn(name = "FK_CLIENTE", nullable = false) }, inverseJoinColumns = {
					@JoinColumn(name = "FK_CUENTA", nullable = false) })
	private Set<Cuenta> cuentas;

	@Override
	public String toString() {
		return "Cliente [nif=" + nif + ", nombre=" + nombre + ", aval=" + aval + ", telefonos=" + telefonos
				+ ", cuentas=" + cuentas.stream().map(Cuenta::getNumCuenta).collect(Collectors.toList()) + "]";
	}

}
