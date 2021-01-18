package org.dam2.taller;

import java.io.Serializable;
import java.util.Set;

import javax.persistence.CascadeType;
import javax.persistence.Embeddable;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToMany;
import javax.persistence.OneToMany;
import javax.persistence.OneToOne;
import javax.persistence.Table;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;
import lombok.Singular;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
@EqualsAndHashCode(onlyExplicitlyIncluded = true)
@Entity
@Table(name = "conductores")
public class Conductor implements Serializable{
	@Id
	@EqualsAndHashCode.Include
	private String dni;
	private String nombre;
	@Singular
	@ManyToMany(mappedBy = "conductores")
	private Set<Coche> coches;
	@Override
	public String toString() {
		return "Conductor [dni=" + dni + ", nombre=" + nombre + "]";
	}
	
	
	
}
