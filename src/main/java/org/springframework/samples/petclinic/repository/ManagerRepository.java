package org.springframework.samples.petclinic.repository;

import org.springframework.data.repository.CrudRepository;
import org.springframework.samples.petclinic.model.Manager;

public interface ManagerRepository extends CrudRepository<Manager, Integer> {

}
