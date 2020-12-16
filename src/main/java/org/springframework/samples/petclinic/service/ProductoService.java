package org.springframework.samples.petclinic.service;

import java.util.Collection;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataAccessException;
import org.springframework.samples.petclinic.model.Producto;
import org.springframework.samples.petclinic.model.TipoProducto;
import org.springframework.samples.petclinic.repository.PetRepository;
import org.springframework.samples.petclinic.repository.ProductoRepository;
import org.springframework.samples.petclinic.repository.VisitRepository;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
public class ProductoService {

	@Autowired
	private ProductoRepository productoRepo;
	
	@Transactional
	public int productoCount() {
		return (int) productoRepo.count();	
	}

	@Transactional
	public Iterable<Producto> productoList() {
		return productoRepo.findAll();
		
	}

	@Transactional
	public Producto guardarProducto(Producto producto) {
		return productoRepo.save(producto);
		
	}
	
	@Transactional
	public void borrarProducto(Integer id) {
		productoRepo.deleteById(id);
		
	}
	
	@Transactional
	public Optional<Producto> buscaProductoPorId(Integer id) {
		return productoRepo.findById(id);
		
	}
	
	//Esto pertenece a la clase TipoProducto
	@Autowired
	public ProductoService(ProductoRepository productoRepo) {
		this.productoRepo = productoRepo;
	}
	
	@Transactional(readOnly = true)
	public Collection<TipoProducto> encontrarTiposProducto() throws DataAccessException {
		return productoRepo.encontrarTiposProducto();
	}
}
