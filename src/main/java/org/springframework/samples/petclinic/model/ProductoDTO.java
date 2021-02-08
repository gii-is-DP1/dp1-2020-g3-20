package org.springframework.samples.petclinic.model;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class ProductoDTO {

	private String tipoproductodto;
	private String proveedor;
	private Integer id;
	private String name;
	private double cantMin;
	private double cantAct;
	private double cantMax;
	private Integer version;

}
