package org.springframework.samples.petclinic.model;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Table;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@Entity
@Table(name = "platos")
public class Plato extends NamedEntity{
	
	@Column(name = "precio")
	private Integer precio;
	
	@Column(name = "disponible")
	private Boolean disponible;
	
}
