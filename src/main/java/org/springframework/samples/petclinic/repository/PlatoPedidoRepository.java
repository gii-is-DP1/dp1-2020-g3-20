package org.springframework.samples.petclinic.repository;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataAccessException;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.samples.petclinic.model.EstadoPlato;
import org.springframework.samples.petclinic.model.Plato;
import org.springframework.samples.petclinic.model.PlatoPedido;


public interface PlatoPedidoRepository extends CrudRepository<PlatoPedido, Integer>{
	@Autowired
	@Query("SELECT ptype FROM EstadoPlato ptype ORDER BY ptype.id")
	List<EstadoPlato> encontrarEstadosPlato() throws DataAccessException;
	
	@Autowired
	@Query("SELECT ptype.name FROM Plato ptype ORDER BY ptype.id")
	List<String> encontrarPlatos() throws DataAccessException;
}
