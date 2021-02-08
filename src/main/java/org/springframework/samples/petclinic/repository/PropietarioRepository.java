package org.springframework.samples.petclinic.repository;

import org.springframework.data.repository.CrudRepository;
import org.springframework.samples.petclinic.model.Propietario;

public interface PropietarioRepository extends CrudRepository<Propietario, Integer>{ 

}
