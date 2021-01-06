package org.springframework.samples.petclinic.model;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.persistence.OneToOne;

import lombok.Data;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@Entity
public class Ingrediente extends BaseEntity{
	
	@Column(name="cantidad")
	private Integer cantidadUsualPP;
	
	@ManyToOne(optional=false)
	@JoinColumn(name = "producto_id")
	private Producto producto;
	
	@ManyToOne
	@JoinColumn(name = "plato_id")
	private Plato plato;

	@Override
	public String toString() {
		return "Ingrediente ["+"id"+this.getId()+ "cantidadUsualPP=" + cantidadUsualPP + ", producto=" + producto + ", plato=" + plato + "]";
	}
	
	
}