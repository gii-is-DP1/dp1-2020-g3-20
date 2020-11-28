package org.springframework.samples.petclinic.repository;

import org.springframework.data.repository.CrudRepository;

import org.springframework.samples.petclinic.model.Proveedor;
import org.springframework.samples.petclinic.model.User;

public interface ProveedorRepository extends  CrudRepository<Proveedor, Integer>{
	
	
}
