package org.springframework.samples.petclinic.repository;

import org.springframework.dao.DataAccessException;
import org.springframework.data.repository.CrudRepository;
import org.springframework.samples.petclinic.model.Camarero;


public interface CamareroRepository extends CrudRepository<Camarero, Integer>{
	Camarero findCamareroById(int camarero) throws DataAccessException;

}
