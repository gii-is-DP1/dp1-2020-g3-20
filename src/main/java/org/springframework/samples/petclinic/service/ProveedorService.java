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
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
public class ProveedorService {
	@Autowired
	private ProveedorRepository provRepo;
	private PedidoRepository pediRepo;
	private LineaPedidoRepository linRepo;
	private ProductoRepository productoRepository;

	@Autowired
	public ProveedorService(ProveedorRepository provRepo,
			PedidoRepository pediRepo, LineaPedidoRepository linRepo,
			ProductoRepository productoRepository) {
		this.provRepo = provRepo;
		this.pediRepo = pediRepo;
		this.linRepo = linRepo;
		this.productoRepository = productoRepository;
	}
	
	
	@Transactional
	public int Provercount(){
		return (int) provRepo.count();
	}
	@Transactional
	public Iterable<Proveedor> findAll(){
		return provRepo.findAll();
	}
	
	@Transactional
	public Collection<String> findAllNames(){
		Iterable<Proveedor> aux = provRepo.findAll();
		Iterator<Proveedor> it_aux = aux.iterator();
		Collection<String> res = new ArrayList<String>();
		while(it_aux.hasNext()) {
			res.add(it_aux.next().getName());
		}
		return res;
	}
	
	@Transactional
	public Proveedor findProveedorbyName(String nombre){
		Iterable<Proveedor> aux = provRepo.findAll();
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
	public boolean esIgual(String nombre, String apellido){
		Iterable<Proveedor> lista = provRepo.findAll();
		Iterator<Proveedor> it = lista.iterator();
		boolean aux = false;
	
		while (it.hasNext()) {
			Proveedor p = it.next();
			if (p.getName().equals(nombre) && p.getApellido().equals(apellido)) {
				aux = true;
			}	
		}
		return aux; 
	}


	@Transactional
	public void save(Proveedor proveedor) {
		 provRepo.save(proveedor);
	}
	
	@Transactional
	public void delete(Proveedor proveedor) {
		provRepo.delete(proveedor);
		
	}
	
	@Transactional
	public void borrarProv(Integer id) {
		provRepo.deleteById(id);
		
	}
	
	
	
	@Transactional
	public Optional<Proveedor> provedroporid(Integer id) {
		return provRepo.findById(id);
	}
	
	//PEDIDO
	
	@Transactional
	public void savePedido(Pedido pedido) throws DataAccessException {
		pediRepo.save(pedido);
	}
	
	@Transactional
	public Pedido crearPedido(Proveedor proveedor) throws DataAccessException {
		Pedido pedido = new Pedido();
		pedido.setProveedor(proveedor);
		pedido.setFechaPedido(LocalDate.now());
		pedido.setHaLlegado(Boolean.FALSE);
		pedido.setCosteTotal(8.0);
		return pedido;
	}
	
	@Transactional
	public Iterable<Pedido> findAllPedido(){
		return pediRepo.findAll();
	}
	
	@Transactional
	public Optional<Pedido> pedidoPorId(Integer id) {
		return pediRepo.findById(id);
	} 
	
	public Iterable<Pedido> findPedidoByProveedorId(int proveedorID) {
		
		return pediRepo.findByProveedorId(proveedorID);
	}
	
	//Esto se usa al realizar un pedido pues se necesita la lista de productos con el mismo proveedor
	@Transactional
	public Collection<Producto> encontrarProductoProveedor(Producto producto) throws DataAccessException {
		Collection<Producto> res = new ArrayList<>();
		Proveedor proveedor = producto.getProveedor();
		res = productoRepository.findByProveedor(proveedor);
		return res; 
	}

	
	//LINEA PEDIDO
	
	@Transactional
	public int lineaPedidoCount() {
		return (int) linRepo.count();	
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
	
	public Iterable<LineaPedido> findLineaPedidoByPedidoId(int pedidoID) {
		return linRepo.findByPedidoId(pedidoID);
	}
	
//	@Transactional
//	public LineaPedido crearLineaPedido(Producto producto, Pedido pedido) throws DataAccessException {
//		LineaPedido lp = new LineaPedido();
//		double cantidad = producto.getCantMax()-producto.getCantAct();
//		
//		lp.setCantidad(cantidad);
//		lp.setPrecio(cantidad*2);    //El numero dos se sutituye mas tarde por el precio del producto si lo decidimos meter. 
//		lp.setPedido(pedido);
//		lp.setProducto(producto);
//		return lp;
//	}
	
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
	
