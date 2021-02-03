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
	@Query("SELECT ptype FROM EstadoPlato ptype ORDER BY ptype.id")
	List<EstadoPlato> encontrarEstadosPlato() throws DataAccessException;
	
	@Autowired
	@Query("SELECT ptype.name FROM Plato ptype ORDER BY ptype.id")
	List<String> encontrarPlatos() throws DataAccessException;
	
	@Autowired
	@Query("SELECT ptype FROM IngredientePedido ptype ORDER BY ptype.id")
	List<IngredientePedido> encontrarIngredientesPedido() throws DataAccessException;
	
//	@Query("SELECT pp FROM PlatoPedido pp WHERE comandaId=pp.comanda.")
//	List<PlatoPedido> findByComanda(int comandaId) throws DataAccessException;
	
//	@Autowired
//	@Query("SELECT ip.* FROM IngredientePedido ip INNER JOIN PlatoPedido pp "
//			+ "ON pp.platoPedido_id = ip.platoPedido_id "
//			+ "WHERE pp.platoPedido_id = id LIKE :id%")
//	List<IngredientePedido> encontrarIngredientePedido() throws DataAccessException;
	
}
