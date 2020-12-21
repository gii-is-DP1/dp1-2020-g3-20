package org.springframework.samples.petclinic.model;


import java.time.LocalDate;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

import org.springframework.format.annotation.DateTimeFormat;

import lombok.Data;


@Data
@Entity
@Table(name = "pedido")
public class Pedido extends BaseEntity{

	@Column(name = "fechapedido")        
	@DateTimeFormat(pattern = "yyyy/MM/dd")
	private LocalDate fechaPedido;
	
	
	@Column(name = "fechaentrega")        
	@DateTimeFormat(pattern = "yyyy/MM/dd")
	private LocalDate fechaEntrega;
	
	
	@Column(name = "costetotal")        
	private Double costeTotal;
	
	@Column(name = "hallegado")        
	private String haLlegado;
	

	@ManyToOne(optional = false)
	@JoinColumn(name = "proveedor_id")
	private Proveedor proveedor;
    
	
	
	public LocalDate getFechaPedido() {
		return this.fechaPedido;
	}
	
	public void setFechaPedido (LocalDate fechaPedido) {
		this.fechaPedido = fechaPedido;
	}
	
	
	public LocalDate getFechaEntrega() {
		return this.fechaEntrega;
	}
	
	public void setFechaEntrega (LocalDate fechaEntrega) {
		this.fechaEntrega = fechaEntrega;
	}
	
	public Double getCosteTotal() {
		return this.costeTotal;
	}
	
	public void setCosteTotal (Double costeTotal) {
		this.costeTotal = costeTotal;
	}
	
	public String getHaLlegado() {
		return this.haLlegado;
	}
	
	public void setHaLlegado (String haLlegado) {
		this.haLlegado = haLlegado;
	}
	
	public Proveedor getProveedor() {
		return this.proveedor;
	}
	
	public void setProveedor (Proveedor proveedor) {
		this.proveedor = proveedor;
	}

}
