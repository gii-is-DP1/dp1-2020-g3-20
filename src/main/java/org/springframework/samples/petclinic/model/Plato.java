package org.springframework.samples.petclinic.model;

import java.time.LocalDate;
import java.util.HashSet;
import java.util.Set;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.OneToMany;
import javax.persistence.Table;

import lombok.Data;

@Data
@Entity
@Table(name = "platos")
public class Plato extends NamedEntity{
	 
	
	@OneToMany(cascade = CascadeType.ALL, mappedBy = "plato")
	private Set<Ingrediente> ingredientes;
	
	@Column(name = "precio")
	private Integer precio;
	
	@Column(name = "disponible")
	private Boolean disponible;
	
	protected Set<Ingrediente> getIngredientesInternal() {
		if (this.ingredientes == null) {
			this.ingredientes = new HashSet<>();
		}
		return this.ingredientes;
	}
	
	protected void setPrecio(Integer precio) {
		this.precio = precio;
	}
	
	public Integer getPrecio() {
		return this.precio;
	}
	
	public void setdisponible(Boolean disponible) {
		this.disponible = disponible;
	}
	
	public Boolean getdisponible() {
		return this.disponible;
	}
	
	public void addIngrediente(Ingrediente ingrediente) {
		getIngredientesInternal().add(ingrediente);
		ingrediente.setPlato(this);
	}
	
	public boolean removeIngrediente(Ingrediente ingrediente) {
		return getIngredientesInternal().remove(ingrediente);
	}
	
	
	
}
