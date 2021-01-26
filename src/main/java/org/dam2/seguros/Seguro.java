package org.dam2.seguros;

import java.io.Serializable;
import java.time.LocalDate;
import java.util.Set;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.OneToMany;
import javax.persistence.OneToOne;
import javax.persistence.PrimaryKeyJoinColumn;
import javax.persistence.Table;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;
import lombok.Singular;
@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
@EqualsAndHashCode(onlyExplicitlyIncluded = true)
@Entity
@Table(name = "Seguros")
public class Seguro implements Serializable{
	@Id
	@EqualsAndHashCode.Include
	private Integer idSeguro;
	private String nif;
	private String nombre;
	private String apellidos;
	private Integer edad;
	@Column(length = 1)
	private char sexo;
	private boolean casado;
	@Column(name = "num_hijos")
	private Integer numHijos;
	private boolean embarazada;
	
	private LocalDate fechaCreacion;
	
	//relaciones 1-1
	@OneToOne(cascade = CascadeType.ALL, fetch=FetchType.EAGER)
	@PrimaryKeyJoinColumn
	private Cobertura cobertura;
	@OneToOne(cascade = CascadeType.ALL, fetch=FetchType.EAGER)
	@PrimaryKeyJoinColumn
	private Enfermedad enfermedad;
	
	//relaciones 1-muchos
	@Singular
	@OneToMany(cascade = CascadeType.ALL, fetch = FetchType.EAGER)
	@JoinColumn(name="FK_ASISTENCIA")
	private Set<AsistenciaMedica> asistencias;
	
}
