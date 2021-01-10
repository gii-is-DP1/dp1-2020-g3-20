package org.springframework.samples.petclinic.model;


import java.time.LocalDate;
import java.util.Set;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.persistence.Table;

import org.springframework.format.annotation.DateTimeFormat;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
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
	private Boolean haLlegado;

	@ManyToOne(optional = false)
	@JoinColumn(name = "proveedor_id")
	private Proveedor proveedor;
	
	@OneToMany(cascade = CascadeType.REMOVE, mappedBy = "pedido")
	private Set<LineaPedido> lineasPedidas;
	

	public Pedido(LocalDate fechaPedido, LocalDate fechaEntrega, Double costeTotal, Boolean haLlegado,
			Proveedor proveedor) {
		super();
		this.fechaPedido = fechaPedido;
		this.fechaEntrega = fechaEntrega;
		this.costeTotal = costeTotal;
		this.haLlegado = haLlegado;
		this.proveedor = proveedor;
	}

	public Pedido() {
		super();
	}

	
	
}
