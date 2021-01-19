package org.dam2.herencia;


import javax.persistence.Column;
import javax.persistence.DiscriminatorValue;

import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.ToString;
import lombok.experimental.SuperBuilder;

@Data
@SuperBuilder
@ToString(callSuper = true)
@EqualsAndHashCode(callSuper = true)
@DiscriminatorValue(value = "AL")
public class Alumno extends Persona {
	@Column(length = 4)
	private String grupo;
	@Column(length=30)
	private String estudiosAnteriores;
}
