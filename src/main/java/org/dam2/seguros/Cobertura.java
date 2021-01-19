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
@Table(name = "Coberturas")
public class Cobertura implements Serializable{
	private boolean oftalmologia;
	private boolean dental;
	private boolean fecundacionVitro;
}
