package org.springframework.samples.petclinic.repository;

import org.springframework.dao.DataAccessException;
import org.springframework.data.repository.CrudRepository;
import org.springframework.samples.petclinic.model.Cocinero;


public interface CocineroRepository extends CrudRepository<Cocinero, Integer>{
	Cocinero findCocineroById(int cocineroId) throws DataAccessException;
}
