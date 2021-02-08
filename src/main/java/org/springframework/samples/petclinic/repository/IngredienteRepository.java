package org.springframework.samples.petclinic.repository;

import java.util.List;

import org.springframework.dao.DataAccessException;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.samples.petclinic.model.Ingrediente;
import org.springframework.samples.petclinic.model.Producto;

public interface IngredienteRepository extends CrudRepository<Ingrediente, Integer> {

	@Query("SELECT pr FROM Producto pr ORDER BY pr.id")
	List<Producto> encontrarProductos() throws DataAccessException;
}

