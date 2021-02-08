package org.springframework.samples.petclinic.repository;

import org.springframework.data.repository.CrudRepository;
import org.springframework.samples.petclinic.model.Cocinero;
import org.springframework.dao.DataAccessException;

public interface CocineroRepository extends CrudRepository<Cocinero, Integer>{

	Cocinero findCocineroById(int cocineroId) throws DataAccessException;
}
