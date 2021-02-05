package org.springframework.samples.petclinic.model;

import java.util.Set;

import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.persistence.Table;

import org.springframework.samples.petclinic.validators.ValidatePlatoPedidoDisponible;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@Entity
@ValidatePlatoPedidoDisponible
@Table(name = "platopedido")
public class PlatoPedido extends BaseEntity{
	@ManyToOne
	@JoinColumn(name = "estadoplato")
	private EstadoPlato estadoplato;
	
	@ManyToOne(optional=false)
	@JoinColumn(name = "plato_id")
	private Plato plato;
	
	@OneToMany(cascade = CascadeType.REMOVE, mappedBy = "pp")
	private Set<IngredientePedido> ingredientesPedidos;
	
	@ManyToOne
	@JoinColumn(name = "comanda_id")
	private Comanda comanda;
}
