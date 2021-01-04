package org.springframework.samples.petclinic.model;

import javax.persistence.Entity;
import javax.persistence.JoinColumn;
import javax.persistence.OneToOne;

import lombok.Data;

@Data
@Entity
public class Ingrediente extends BaseEntity{
	
	private Integer cantidadUsualPP;
	
	@OneToOne(optional=false)
	@JoinColumn(name = "producto_id")
	private Producto producto;
}