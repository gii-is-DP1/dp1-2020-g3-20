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

@Service
public class ProductoService {
	
	@Autowired
	private ProductoRepository productoRepo;
	private LineaPedidoRepository lineaPedidoRepository;
	
	@Autowired
	public ProductoService(LineaPedidoRepository lineaPedidoRepository,ProductoRepository productoRepository) {
		this.lineaPedidoRepository = lineaPedidoRepository;
		this.productoRepo = productoRepository;
	}
	
	@Transactional
	public int productoCount() {
		return (int) productoRepo.count();	
	}

	@Transactional
	public Collection<Producto> productoList() {
		return (Collection<Producto>) productoRepo.findAll();
	}

	@Transactional
	public Producto guardarProducto(Producto producto) {
		return productoRepo.save(producto);
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
   		productoRepo.deleteById(id);
		}
	}

	
	@Transactional
	public Optional<Producto> buscaProductoPorId(Integer id) {
		return productoRepo.findById(id);
	}
	public Iterable<LineaPedido> findLineaPedidoByProductoId(int productoID) {
		return lineaPedidoRepository.findByProductoId(productoID);
	}
	//Esto pertenece a la clase TipoProducto
	@Transactional(readOnly = true)
	public Collection<TipoProducto> encontrarTiposProducto() throws DataAccessException {
		return productoRepo.encontrarTiposProducto();
	}
	
}
