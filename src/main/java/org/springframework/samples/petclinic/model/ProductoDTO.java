package org.springframework.samples.petclinic.model;


import lombok.Data;

@Data
public class ProductoDTO {
	private String tipoproductodto;
	
	private Integer id;
	private String name;
	private int cantMin;
	private int cantAct;
	private int cantMax;
}
