package org.springframework.samples.petclinic.model;


import lombok.Data;

@Data
public class ProductoDTO {
	private String tipoproductodto;
	private String proveedor;
	private Integer id;
	private String name;
	private double cantMin;
	private double cantAct;
	private double cantMax;
}
