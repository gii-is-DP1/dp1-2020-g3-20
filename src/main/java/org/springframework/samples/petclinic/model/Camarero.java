package org.springframework.samples.petclinic.model;

import java.util.Set;

import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.OneToMany;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@Entity
public class Camarero extends Empleado{
	
	@OneToMany(cascade = CascadeType.REMOVE, mappedBy = "camarero")
	private Set<Comanda> comandas;
	
}
