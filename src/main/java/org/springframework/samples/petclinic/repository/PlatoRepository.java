package org.springframework.samples.petclinic.repository;

import java.util.List;

import org.springframework.dao.DataAccessException;
import org.springframework.data.jpa.repository.Query;

import org.springframework.data.repository.CrudRepository;
import org.springframework.samples.petclinic.model.Ingrediente;
import org.springframework.samples.petclinic.model.Plato;

public interface PlatoRepository extends CrudRepository<Plato, Integer>{
	
	@Query("SELECT ing FROM Ingrediente ing ")
	List<Ingrediente> encontrarIngredientes() throws DataAccessException;
	
	@Query("SELECT pl FROM Plato pl WHERE pl.disponible=true ORDER BY pl.id")
	List<Plato> findAllAvailable() throws DataAccessException;
}
