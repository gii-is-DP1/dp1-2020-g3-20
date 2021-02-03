package org.springframework.samples.petclinic.model;


import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class IngredienteAUX extends BaseEntity{

	private Double cantidadUsualPP;
	private Producto producto;
	private String platoaux;
	
}
