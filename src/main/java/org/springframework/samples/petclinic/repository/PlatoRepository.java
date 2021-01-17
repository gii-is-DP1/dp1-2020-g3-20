package org.springframework.samples.petclinic.repository;


import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataAccessException;
import org.springframework.data.jpa.repository.Query;

import org.springframework.data.repository.CrudRepository;
import org.springframework.samples.petclinic.model.Ingrediente;
import org.springframework.samples.petclinic.model.Plato;

public interface PlatoRepository extends CrudRepository<Plato, Integer>{
	
	@Autowired
	@Query("SELECT ing FROM Ingrediente ing ")
	List<Ingrediente> encontrarIngredientes() throws DataAccessException;
}
