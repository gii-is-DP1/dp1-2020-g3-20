package org.springframework.samples.petclinic.repository;

import org.springframework.data.repository.CrudRepository;
import org.springframework.samples.petclinic.model.Producto;

public interface ProductoRepository extends CrudRepository<Producto, Integer>{

}
