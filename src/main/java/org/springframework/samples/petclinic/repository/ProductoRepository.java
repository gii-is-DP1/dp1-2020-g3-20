package org.springframework.samples.petclinic.repository;

import java.util.List;

import org.springframework.dao.DataAccessException;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.samples.petclinic.model.Producto;
import org.springframework.samples.petclinic.model.Proveedor;
import org.springframework.samples.petclinic.model.TipoProducto;

public interface ProductoRepository extends CrudRepository<Producto, Integer>{
	
	@Query("SELECT tp FROM TipoProducto tp ORDER BY tp.id")
	List<TipoProducto> encontrarTiposProducto() throws DataAccessException;
	
	List<Producto> findByProveedor(Proveedor proveedor) throws DataAccessException;
}
