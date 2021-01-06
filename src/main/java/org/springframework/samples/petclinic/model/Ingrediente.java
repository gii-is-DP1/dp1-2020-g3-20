package org.springframework.samples.petclinic.model;

import javax.persistence.Entity;
import javax.persistence.JoinColumn;
import javax.persistence.OneToOne;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@Entity
public class Ingrediente extends BaseEntity{
	
	private Integer cantidadUsualPP;
	
	@OneToOne(optional=false)
	@JoinColumn(name = "producto_id")
	private Producto producto;
}