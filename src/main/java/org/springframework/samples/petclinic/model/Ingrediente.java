package org.springframework.samples.petclinic.model;

import java.time.LocalDate;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

import lombok.Data;

@Data
@Entity
@Table(name = "ingredientes")

public class Ingrediente extends BaseEntity{
	 
	
	@ManyToOne
	@JoinColumn(name = "producto_id")
	private Producto producto;

	@ManyToOne
	@JoinColumn(name = "plato_id")
	private Plato plato;
	
	@Column(name = "precioTotal")
	private Integer precioTotal;
	
	
	protected void setPrecioTotal(Integer precioTotal) {
		this.precioTotal = precioTotal;
	}
	
	public Integer getPrecioTotal() {
		return this.precioTotal;
	}
	
	public Producto getProducto() {
		return this.producto;
	}

	public void setProducto(Producto producto) {
		this.producto = producto;
	}

	public Plato getPlato() {
		return this.plato;
	}

	protected void setPlato(Plato plato) {
		this.plato  = plato;
	}
	
	
}
