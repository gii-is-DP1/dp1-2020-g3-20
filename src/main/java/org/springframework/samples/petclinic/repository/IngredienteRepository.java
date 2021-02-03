package org.springframework.samples.petclinic.repository;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataAccessException;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.samples.petclinic.model.Ingrediente;
import org.springframework.samples.petclinic.model.Producto;

public interface IngredienteRepository extends CrudRepository<Ingrediente, Integer> {

	@Autowired
	@Query("SELECT ptype FROM Producto ptype ORDER BY ptype.id")
	List<Producto> encontrarProductos() throws DataAccessException;
	
}

