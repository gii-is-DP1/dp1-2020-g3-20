package org.springframework.samples.petclinic.model;

import java.time.LocalDateTime;
import java.util.Set;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.OneToMany;
import javax.persistence.Table;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@Entity
@Table(name = "comanda")
public class Comanda extends BaseEntity{
	 
	@Column(name = "precio_total")
	private Integer precioTotal;
	
	@Column(name = "fecha_creado")
	private LocalDateTime fechaCreado;
	
	@Column(name = "fecha_finalizado")
	private LocalDateTime fechaFinalizado;
	
	@OneToMany(cascade = CascadeType.REMOVE, mappedBy = "comanda")
	private Set<PlatoPedido> platosPedidos;
	
}
