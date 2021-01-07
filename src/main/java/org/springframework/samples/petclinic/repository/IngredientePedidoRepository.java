package org.springframework.samples.petclinic.repository;

import java.util.Collection;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataAccessException;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.samples.petclinic.model.Ingrediente;
import org.springframework.samples.petclinic.model.IngredientePedido;
import org.springframework.samples.petclinic.model.Producto;

public interface IngredientePedidoRepository extends CrudRepository<IngredientePedido, Integer>{

	@Autowired
	@Query("SELECT ptype FROM Ingrediente ptype ORDER BY ptype.id")
	Collection<Ingrediente> encontrarIngredientes() throws DataAccessException;
}
