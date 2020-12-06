package org.springframework.samples.petclinic.service;

import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.samples.petclinic.model.Producto;
import org.springframework.samples.petclinic.repository.ProductoRepository;
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
}
