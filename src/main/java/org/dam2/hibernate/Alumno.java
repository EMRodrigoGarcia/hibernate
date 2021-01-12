package org.dam2.hibernate;

import java.time.LocalDate;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

import lombok.Builder;
import lombok.Data;
import lombok.EqualsAndHashCode;

@Data
@EqualsAndHashCode(onlyExplicitlyIncluded = true)
@Builder

@Entity
@Table(name = "Alumnos")
public class Alumno {
	@Id
	@GeneratedValue(strategy = GenerationType.TABLE)
	private Integer id;
	@Column(length = 10, name = "name")
	private String nombre;
	@Column(name="FECHA_NAC")
	private LocalDate fechaNac;
	

}
