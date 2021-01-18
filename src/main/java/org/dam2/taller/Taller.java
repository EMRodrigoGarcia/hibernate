package org.dam2.taller;

import java.io.Serializable;
import java.util.Set;

import javax.persistence.CascadeType;
//import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.OneToMany;
import javax.persistence.Table;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.Singular;


@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
@Entity
@Table(name = "talleres")
public class Taller implements Serializable{
	@Id
	@GeneratedValue(strategy = GenerationType.TABLE)
	private Integer id;
	//@Column(name = "NOMBRE_TALLER")
	private String nombre;
	//@Column(name = "AVAL_MAXIMO")
	private Integer aval;
	@OneToMany(cascade = CascadeType.ALL, fetch = FetchType.EAGER)
	@JoinColumn(name = "FK_ID_TALLER")
	@Singular
	private Set<Coche> coches;
	@Override
	public String toString() {
		return "Taller [id=" + id + ", nombre=" + nombre + ", aval=" + aval + ", coches=" + coches + "]";
	}
	
}
