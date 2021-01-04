package org.springframework.samples.petclinic.model;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

import lombok.Data;

@Data
@Entity
@Table(name = "lineapedido")
public class LineaPedido extends BaseEntity{

	@Column(name = "cantidad")        
	private double cantidad;
	
	@Column(name = "precio")        
	private Double precio;
	

	@ManyToOne(optional = false)
	@JoinColumn(name = "producto_id")
	private Producto producto;
	

	@ManyToOne(optional = false)
	@JoinColumn(name = "pedido_id")
	private Pedido pedido;
	
    
	
	
	public double getCantidad() {
		return this.cantidad;
	}
	
	public void setCantidad (double cantidad) {
		this.cantidad = cantidad;
	}
	
	public Double getPrecio() {
		return this.precio;
	}
	
	public void setPrecio (Double precio) {
		this.precio = precio;
	}
	
	public Producto getProducto() {
		return this.producto;
	}
	
	public void setProducto (Producto producto) {
		this.producto = producto;
	}
	
	public Pedido getPedido() {
		return this.pedido;
	}
	
	public void setPedido (Pedido pedido) {
		this.pedido = pedido;
	}
	
}
