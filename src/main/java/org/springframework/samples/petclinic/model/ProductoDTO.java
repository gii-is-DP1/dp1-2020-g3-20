package org.springframework.samples.petclinic.model;


import lombok.Data;

@Data
public class ProductoDTO {
	private Integer id;
	private String name;
	private String tipoproductodto;
	private int cantMin;
	private int cantAct;
	private int cantMax;
}
