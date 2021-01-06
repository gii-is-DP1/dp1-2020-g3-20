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
	@Query("SELECT ptype FROM TipoProducto ptype ORDER BY ptype.id")
	List<TipoProducto> encontrarTiposProducto() throws DataAccessException;
}
