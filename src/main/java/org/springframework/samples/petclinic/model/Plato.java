package org.springframework.samples.petclinic.model;

import javax.persistence.Entity;

import lombok.Data;

@Data
@Entity
public class Plato extends NamedEntity{
	
	private String precio;
}
