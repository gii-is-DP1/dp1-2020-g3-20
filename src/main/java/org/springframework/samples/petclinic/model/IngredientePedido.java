package org.springframework.samples.petclinic.model;


import javax.persistence.Column;
import javax.persistence.Table;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@Table(name = "ingediente_pedido")
public class IngredientePedido extends NamedEntity{
	@Column(name = "cant_pedida")
	private Integer cantidadPedida;
}
