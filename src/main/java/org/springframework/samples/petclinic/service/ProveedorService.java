package org.springframework.samples.petclinic.service;


import java.time.LocalDate;
import java.util.ArrayList;
import java.util.Collection;
import java.util.Iterator;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataAccessException;
import org.springframework.samples.petclinic.model.Comanda;
import org.springframework.samples.petclinic.model.LineaPedido;
import org.springframework.samples.petclinic.model.Pedido;
import org.springframework.samples.petclinic.model.Producto;
import org.springframework.samples.petclinic.model.Proveedor;
import org.springframework.samples.petclinic.repository.LineaPedidoRepository;
import org.springframework.samples.petclinic.repository.PedidoRepository;
import org.springframework.samples.petclinic.repository.ProductoRepository;
import org.springframework.samples.petclinic.repository.ProveedorRepository;
import org.springframework.samples.petclinic.service.exceptions.DuplicatedPedidoException;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
public class ProveedorService {
	@Autowired
	private ProveedorRepository proveedorRepository;
	private PedidoRepository pedidoRepository;
	private LineaPedidoRepository lineaPedidoRepository;
	private ProductoRepository productoRepository;

	@Autowired
	public ProveedorService(ProveedorRepository proveedorRepository,
			PedidoRepository pedidoRepository, LineaPedidoRepository lineaPedidoRepository,
			ProductoRepository productoRepository) {
		this.proveedorRepository = proveedorRepository;
		this.pedidoRepository = pedidoRepository;
		this.lineaPedidoRepository = lineaPedidoRepository;
		this.productoRepository = productoRepository;
	}
	
	
	@Transactional
	public int Provercount(){
		return (int) proveedorRepository.count();
	}
	@Transactional
	public Iterable<Proveedor> findAll(){
		return proveedorRepository.findAll();
	}
	
	@Transactional
	public Collection<String> findAllNames(){
		Iterable<Proveedor> aux = proveedorRepository.findAll();
		Iterator<Proveedor> it_aux = aux.iterator();
		Collection<String> res = new ArrayList<String>();
		while(it_aux.hasNext()) {
			res.add(it_aux.next().getName());
		}
		return res;
	}
	
	@Transactional
	public Proveedor findProveedorbyName(String nombre){
		Iterable<Proveedor> aux = proveedorRepository.findAll();
		Iterator<Proveedor> it_aux = aux.iterator();
		while(it_aux.hasNext()) {
			Proveedor proveedor = it_aux.next();
			
			String name = proveedor.getName();
			if (name.equals(nombre)) {
				return proveedor;
			}
		}
		return null;
	}
	
	
	@Transactional
	public boolean esIgual(String nombre){
		Iterable<Proveedor> lista = proveedorRepository.findAll();
		Iterator<Proveedor> it = lista.iterator();
		boolean aux = false;
	
		while (it.hasNext()) {
			Proveedor p = it.next();
			if (p.getName().equals(nombre)){
				aux = true;
			}	
		}
		return aux; 
	}


	@Transactional
	public void save(Proveedor proveedor) {
		proveedorRepository.save(proveedor);
	}
	
	@Transactional
	public void delete(Proveedor proveedor) {
		proveedorRepository.delete(proveedor);
		
	}
	
	@Transactional
	public void borrarProv(Integer id) {
		proveedorRepository.deleteById(id);
		
	}
	
	
	
	@Transactional
	public Optional<Proveedor> provedroporid(Integer id) {
		return proveedorRepository.findById(id);
	}
	
	//PEDIDO
	
	
	@Transactional
	public int Pedidocount(){
		return (int) pedidoRepository.count();
	}

	@Transactional(rollbackFor = DuplicatedPedidoException.class)
	public void savePedido(Pedido pedido) throws DataAccessException, DuplicatedPedidoException {
		Iterable<Pedido> lista = pedidoRepository.findAll();
		Iterator<Pedido> it = lista.iterator();
       	Boolean Hayrepetido = false;
       	while(it.hasNext()) {
       		Pedido p = it.next();
			if (p.getProveedor()==pedido.getProveedor()&& p.getHaLlegado()==false) {
				      Hayrepetido = true;
		    }		
       	}
       	if (Hayrepetido)  {    
       		throw new DuplicatedPedidoException();
       	}else {
       		pedidoRepository.save(pedido);
       	}
	}

	
	@Transactional
	public Iterable<Pedido> findAllPedido(){
		return pedidoRepository.findAll();
	}
	
	@Transactional
	public Optional<Pedido> pedidoPorId(Integer id) {
		return pedidoRepository.findById(id);
	} 
	
	public Iterable<Pedido> findPedidoByProveedorId(int proveedorID) {
		return pedidoRepository.findByProveedorId(proveedorID);
	}
	
	//Esto se usa al realizar un pedido pues se necesita la lista de productos con el mismo proveedor
	@Transactional
	public Collection<Producto> encontrarProductoProveedor(Producto producto) throws DataAccessException {
		Collection<Producto> res = new ArrayList<>();
		Proveedor proveedor = producto.getProveedor();
		res = productoRepository.findByProveedor(proveedor);
		return res; 
	}
	
	//Esto es para encontrar los pedidos por un dia de la semana 
	@Transactional
	public Collection<Pedido> encontrarPedidoDia(String dia) throws DataAccessException {
		LocalDate actualDate =LocalDate.parse(dia);
		Collection<Pedido> res = new ArrayList<>();
		Iterable<Pedido> aux = pedidoRepository.findAll();
		Iterator<Pedido> it_aux = aux.iterator();
		while (it_aux.hasNext()) {
			Pedido pedido = it_aux.next();
			if (pedido.getFechaPedido().equals(actualDate)) {
				res.add(pedido);
			}	
		}
		return res; 
	}
	
	

	
	//LINEA PEDIDO
	
	@Transactional
	public int lineaPedidoCount() {
		return (int) lineaPedidoRepository.count();	
	}
	
	@Transactional
	public void saveLineaPedido(LineaPedido lineaPedido) throws DataAccessException {
		lineaPedidoRepository.save(lineaPedido);
	}
	
	@Transactional
	public void borrarLineaPedido(Integer id) {
		lineaPedidoRepository.deleteById(id);
	}
	
	
	@Transactional
	public Iterable<LineaPedido> findAllLineaPedido(){
		return lineaPedidoRepository.findAll();
	}
	
	@Transactional
	public Optional<LineaPedido> buscaLineaPedido(Integer id) {
		return lineaPedidoRepository.findById(id);
	}
	
	public Iterable<LineaPedido> findLineaPedidoByPedidoId(int pedidoID) {
		return lineaPedidoRepository.findByPedidoId(pedidoID);
	}
	public Iterable<LineaPedido> findLineaPedidoByProductoId(int productoID) {
		return lineaPedidoRepository.findByProductoId(productoID);
	}
	
	
	@Transactional
	public LineaPedido anadirLineaPedido(Producto producto, Pedido pedido) throws DataAccessException {
		LineaPedido res = new LineaPedido();
		Integer cantidad = (int) (producto.getCantMax()-producto.getCantAct());
		res.setProducto(producto);
		res.setCantidad(cantidad);
		res.setPedido(pedido);
		return res;
	}
	
}
	
