package org.springframework.samples.petclinic.model;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@Entity
@Table(name = "lineapedido")
public class LineaPedido extends BaseEntity{

	@Column(name = "cantidad")        
	private double cantidad;
	
	@ManyToOne(optional = false)
	@JoinColumn(name = "producto_id")
	private Producto producto;
	
	@ManyToOne(optional = false)
	@JoinColumn(name = "pedido_id")
	private Pedido pedido;
	
}
