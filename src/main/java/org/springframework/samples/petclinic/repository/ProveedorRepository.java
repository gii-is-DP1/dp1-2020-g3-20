package org.springframework.samples.petclinic.repository;

import java.util.List;

import org.springframework.dao.DataAccessException;
import org.springframework.data.repository.CrudRepository;
import org.springframework.samples.petclinic.model.Producto;
import org.springframework.samples.petclinic.model.Proveedor;

public interface ProveedorRepository extends  CrudRepository<Proveedor, Integer>{
	List<Proveedor> findByActivoTrue() throws DataAccessException; 
	Proveedor findByName(String nombre) throws DataAccessException; 
}
