package org.springframework.samples.petclinic.model;


import java.util.Set;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.OneToMany;
import javax.persistence.Table;

import org.springframework.data.annotation.Version;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@Entity
@Table(name = "platos")
public class Plato extends NamedEntity{
	
	@Column(name = "precio")
	private Double precio;
	
	@Column(name = "disponible")
	private Boolean disponible;
	
	@OneToMany(mappedBy = "plato")
	private Set<Ingrediente> ingredientes;

}
