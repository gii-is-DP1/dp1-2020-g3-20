package org.springframework.samples.petclinic.model;

import javax.persistence.Entity;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.OneToOne;
import javax.persistence.Table;

import lombok.Data;

@Data
@Entity
@Table(name = "platopedido")
public class PlatoPedido extends BaseEntity{
	@ManyToOne
	@JoinColumn(name = "estadoplato")
	private EstadoPlato estadoplato;
	
	@ManyToOne(optional=false)
	@JoinColumn(name = "plato_id")
	private Plato plato;
	

}
