package org.springframework.samples.petclinic.model;

import java.time.LocalDate;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Table;

import lombok.Data;

@Data
@Entity
@Table(name = "platospedidos")
public class PlatoPedido extends BaseEntity{
	 
	
	@Column(name = "estado_plato")
	private EstadoPlato estadoPlato;
	
	
	protected void setEstadoPlato(EstadoPlato estadoPlato) {
		this.estadoPlato = estadoPlato;
	}
	
	public Integer getEstadoPlato() {
		return this.estadoPlato;
	}
	
	
	
	
	
}
