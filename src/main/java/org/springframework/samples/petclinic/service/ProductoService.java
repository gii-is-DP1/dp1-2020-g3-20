package org.springframework.samples.petclinic.service;

import java.util.Collection;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataAccessException;
import org.springframework.samples.petclinic.model.LineaPedido;
import org.springframework.samples.petclinic.model.Producto;
import org.springframework.samples.petclinic.model.TipoProducto;
import org.springframework.samples.petclinic.repository.LineaPedidoRepository;
import org.springframework.samples.petclinic.repository.ProductoRepository;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
public class ProductoService {
	
	private ProductoRepository productoRepo;
	//Hemos añadido lineaPedido 
	private LineaPedidoRepository linRepo;
	
	//Hemos añadido esto
	@Autowired
	public ProductoService(ProductoRepository productoRepo,
			LineaPedidoRepository linRepo) {
		this.productoRepo = productoRepo;
		this.linRepo = linRepo;
	}
	
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
	@Transactional(readOnly = true)
	public Collection<TipoProducto> encontrarTiposProducto() throws DataAccessException {
		return productoRepo.encontrarTiposProducto();
	}
	
	
	@Transactional
	public void saveLineaPedido(LineaPedido lineaPedido) throws DataAccessException {
		linRepo.save(lineaPedido);
	}
	
	@Transactional
	public void borrarLineaPedido(Integer id) {
		linRepo.deleteById(id);
	}
	
	
	@Transactional
	public Iterable<LineaPedido> findAllLineaPedido(){
		return linRepo.findAll();
	}
	
	@Transactional
	public Optional<LineaPedido> buscaLineaPedido(Integer id) {
		return linRepo.findById(id);
	}
}
