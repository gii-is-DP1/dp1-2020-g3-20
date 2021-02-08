package org.springframework.samples.petclinic.repository;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataAccessException;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.samples.petclinic.model.Comanda;
import org.springframework.samples.petclinic.model.TipoProducto;

public interface ComandaRepository extends CrudRepository<Comanda, Integer>{
	
	@Autowired
	@Query("SELECT tp FROM TipoProducto tp ORDER BY tp.id")
	List<TipoProducto> encontrarTiposProducto() throws DataAccessException;
	
	@Query("SELECT MAX(c.id) FROM Comanda c")
	Integer findLastId() throws DataAccessException;
}
