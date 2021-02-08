package org.springframework.samples.petclinic.service;

import java.util.Collection;
import java.util.Iterator;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataAccessException;
import org.springframework.samples.petclinic.model.LineaPedido;
import org.springframework.samples.petclinic.model.Pedido;
import org.springframework.samples.petclinic.model.Producto;
import org.springframework.samples.petclinic.model.TipoProducto;
import org.springframework.samples.petclinic.repository.LineaPedidoRepository;
import org.springframework.samples.petclinic.repository.PedidoRepository;
import org.springframework.samples.petclinic.repository.ProductoRepository;
import org.springframework.samples.petclinic.repository.ProveedorRepository;
import org.springframework.samples.petclinic.service.exceptions.DuplicatedPedidoException;
import org.springframework.samples.petclinic.service.exceptions.PedidoPendienteException;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import lombok.extern.slf4j.Slf4j;
@Slf4j
@Service
public class ProductoService {
	
	@Autowired
	private ProductoRepository productoRepository;
	@Autowired
	private LineaPedidoRepository lineaPedidoRepository;
	
	@Autowired
	public ProductoService(LineaPedidoRepository lineaPedidoRepository,ProductoRepository productoRepository) {
		this.lineaPedidoRepository = lineaPedidoRepository;
		this.productoRepository = productoRepository;
	}
	
	@Transactional
	public int productoCount() {
		return (int) productoRepository.count();	
	}

	@Transactional
	public Iterable<Producto> productoList() {
		return productoRepository.findAll();
	}

	@Transactional
	public Producto guardarProducto(Producto producto) {
		log.info(String.format("Product with name %s has been saved", producto.getName()));
		return productoRepository.save(producto);
	}
	
	@Transactional(rollbackFor = PedidoPendienteException.class)
	public void borrarProducto(Integer id) throws DataAccessException, PedidoPendienteException {
		Iterable<LineaPedido> LineasPedido =findLineaPedidoByProductoId(id);
		Iterator<LineaPedido> it = LineasPedido.iterator();
		Boolean HaypedidoPendiente = false;
		while(it.hasNext()) {
			LineaPedido p = it.next();
		    if (p.getPedido().getHaLlegado()==false) {
		    	HaypedidoPendiente = true;
	    	}		
		}if (HaypedidoPendiente)  {    
			throw new PedidoPendienteException();
		}else {
		Producto producto = productoRepository.findById(id).get();
		productoRepository.deleteById(id);
		log.info(String.format("Product with name %s has been deleted", producto.getName()));
		
		}
	}

	
	@Transactional
	public Optional<Producto> buscaProductoPorId(Integer id) {
		return productoRepository.findById(id);
	}
	
	public Iterable<LineaPedido> findLineaPedidoByProductoId(int productoID) {
		return lineaPedidoRepository.findByProductoId(productoID);
	}
	//Esto pertenece a la clase TipoProducto
	@Transactional(readOnly = true)
	public Collection<TipoProducto> encontrarTiposProducto() throws DataAccessException {
		return productoRepository.encontrarTiposProducto();
	}
	
}
