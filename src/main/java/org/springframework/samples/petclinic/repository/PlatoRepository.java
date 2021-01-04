package org.springframework.samples.petclinic.repository;

import java.util.Collection;



import org.springframework.dao.DataAccessException;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.query.Param;
import org.springframework.samples.petclinic.model.Owner;
import org.springframework.samples.petclinic.model.Plato;

public interface PlatoRepository extends CrudRepository<Plato, Integer>{
	
	@Query("SELECT *  FROM Plato plato left join fetch plato.ingredientes ORDERED BY name")
	public Collection<Owner> findByLastName(@Param("lastName") String lastName);
}
