package org.dam2.taller;

import java.io.Serializable;
import java.util.HashSet;
import java.util.Set;

import javax.persistence.CascadeType;
import javax.persistence.Embedded;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.JoinTable;
import javax.persistence.ManyToMany;
import javax.persistence.OneToOne;
import javax.persistence.Table;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;
import lombok.Singular;
import lombok.ToString;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
@EqualsAndHashCode(onlyExplicitlyIncluded = true)
@Entity
@Table(name = "coches")
public class Coche implements Serializable{
	@Id
	@EqualsAndHashCode.Include
	private String matricula;
	private String color;
	private String modelo;
	private String marca;

//	@OneToOne(cascade = CascadeType.ALL, fetch = FetchType.EAGER)
//	@JoinColumn(name = "FK_CONDUCTOR_DNI")
	@Singular
	@ManyToMany(cascade = CascadeType.ALL, fetch = FetchType.EAGER)
	@JoinTable(joinColumns = {@JoinColumn(name="CONDUCTOR_DNI")}, inverseJoinColumns = {@JoinColumn(name = "COCHE_ID")})
	private Set<Conductor> conductores;

	@Override
	public String toString() {
		return "Coche [matricula=" + matricula + ", color=" + color + ", modelo=" + modelo + ", marca=" + marca + ", conductores=" + conductores + "]";
	}
	
}
