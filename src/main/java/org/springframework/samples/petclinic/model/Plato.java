package org.springframework.samples.petclinic.model;

import java.time.LocalDate;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Table;

import lombok.Data;

@Data
@Entity
@Table(name = "platos")
public class Plato extends NamedEntity{
	 
	
	@Column(name = "precio")
	private Integer precio;
	
	@Column(name = "disponible")
	private Boolean disponible;
	
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
	
	
	
	
}
