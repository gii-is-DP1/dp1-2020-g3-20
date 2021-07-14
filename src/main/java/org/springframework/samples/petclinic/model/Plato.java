package org.springframework.samples.petclinic.model;


import java.util.Set;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.OneToMany;
import javax.persistence.Table;
import javax.validation.constraints.NotNull;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@Entity
@Table(name = "platos")
public class Plato extends NamedEntity{
	
	@NotNull
	@Column(name = "precio")
	private Double precio;
	
	@Column(name = "disponible")
	private Boolean disponible;
	
	@OneToMany(cascade = CascadeType.REMOVE,mappedBy = "plato")
	private Set<Ingrediente> ingredientes;

	
	@OneToMany(cascade = CascadeType.REMOVE,mappedBy = "plato")
	private Set<PlatoPedido> platopedidos;

}
