package org.dam2.seguros;

import java.io.Serializable;
import java.time.LocalDate;
import java.time.LocalDateTime;

import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;
@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
@EqualsAndHashCode(onlyExplicitlyIncluded = true)
@Entity
@Table(name = "Asistencias_Medicas")
public class AsistenciaMedica implements Serializable{
	@Id
	@EqualsAndHashCode.Include
	private Integer idAsistenciaMedica;
	private String breveDescripcion;
	private String lugar;
	private String explicacion;
	private String tipoAsistencia;
	private LocalDateTime fecha;
	private long importe;
}
