package org.dam2.seguros;

import java.io.Serializable;

import javax.persistence.Entity;
import javax.persistence.Table;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
@Entity
@Table(name = "Enfermedades")
public class Enfermedad implements Serializable{
	private boolean corazon;
	private boolean estomacal;
	private boolean rinyones;
	private boolean alergia;
	private String nombreAlergia;
}
