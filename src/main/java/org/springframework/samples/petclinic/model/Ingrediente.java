package org.springframework.samples.petclinic.model;

import java.time.LocalDate;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Table;

import lombok.Data;

@Data
@Entity
@Table(name = "Comandas")

public class Ingrediente extends BaseEntity{
	 
	
	@Column(name = "precioTotal")
	private Integer precioTotal;
	
	
	protected void setPrecioTotal(Integer precioTotal) {
		this.precioTotal = precioTotal;
	}
	
	public Integer getPrecioTotal() {
		return this.precioTotal;
	}
	
	
	
}
