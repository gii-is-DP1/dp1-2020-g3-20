package org.springframework.samples.petclinic.repository;

import org.springframework.data.repository.CrudRepository;
import org.springframework.samples.petclinic.model.LineaPedido;

public interface LineaPedidoRepository extends CrudRepository<LineaPedido, Integer>{

	Iterable<LineaPedido> findByPedidoId(Integer pedidoID);
	
	Iterable<LineaPedido> findByProductoId(Integer productoID);
}
