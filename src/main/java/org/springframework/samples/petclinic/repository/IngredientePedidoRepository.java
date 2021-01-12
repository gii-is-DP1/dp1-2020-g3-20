package org.springframework.samples.petclinic.repository;

import java.util.Collection;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataAccessException;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.query.Param;
import org.springframework.samples.petclinic.model.Ingrediente;
import org.springframework.samples.petclinic.model.IngredientePedido;
import org.springframework.samples.petclinic.model.Producto;

public interface IngredientePedidoRepository extends CrudRepository<IngredientePedido, Integer>{

	@Autowired
	@Query("SELECT ptype FROM Ingrediente ptype ORDER BY ptype.id")
	Collection<Ingrediente> encontrarIngredientes() throws DataAccessException;
	
	
	@Autowired
	@Query(value = "SELECT i.* FROM INGREDIENTE i "
			+ "INNER JOIN INGEDIENTE_PEDIDO ip ON i.id = ip.ingrediente_id "
			+ "WHERE ip.id = :ingrediente_pedido_id%", nativeQuery = true)
	Ingrediente ingredienteAsociado(@Param("ingrediente_pedido_id") Integer ingrediente_pedido_id) throws DataAccessException;
}
