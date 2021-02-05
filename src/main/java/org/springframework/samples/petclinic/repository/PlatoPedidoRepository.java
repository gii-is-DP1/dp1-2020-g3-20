package org.springframework.samples.petclinic.repository;


import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataAccessException;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.samples.petclinic.model.EstadoPlato;
import org.springframework.samples.petclinic.model.IngredientePedido;
import org.springframework.samples.petclinic.model.PlatoPedido;
import org.springframework.samples.petclinic.model.Producto;
import org.springframework.samples.petclinic.model.Proveedor;



public interface PlatoPedidoRepository extends CrudRepository<PlatoPedido, Integer>{
	@Autowired
	@Query("SELECT ep FROM EstadoPlato ep ORDER BY ep.id")
	List<EstadoPlato> encontrarEstadosPlato() throws DataAccessException;
	
	@Autowired
	@Query("SELECT pl.name FROM Plato pl ORDER BY pl.id")
	List<String> encontrarPlatos() throws DataAccessException;
	
	@Autowired
	@Query("SELECT ip FROM IngredientePedido ip ORDER BY ip.id")
	List<IngredientePedido> encontrarIngredientesPedido() throws DataAccessException;
	
}
