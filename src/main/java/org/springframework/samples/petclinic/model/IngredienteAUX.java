package org.springframework.samples.petclinic.model;

import javax.persistence.Column;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.OneToOne;

import lombok.Data;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class IngredienteAUX extends BaseEntity{

	
	private Integer cantidadUsualPP;
	
	private Producto producto;
	
	private String platoaux;
	
	
}
