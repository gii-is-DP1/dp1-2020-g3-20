package org.springframework.samples.petclinic.service;


import java.util.Collection;
import java.util.Optional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataAccessException;
import org.springframework.samples.petclinic.model.Ingrediente;
import org.springframework.samples.petclinic.model.IngredientePedido;
import org.springframework.samples.petclinic.model.Producto;
import org.springframework.samples.petclinic.repository.IngredientePedidoRepository;
import org.springframework.samples.petclinic.repository.PlatoPedidoRepository;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
public class IngredientePedidoService {

	private IngredientePedidoRepository ingPedidoRepo;
	private ProductoService prodService;
	
	
	@Autowired
	public IngredientePedidoService(IngredientePedidoRepository ingPedidoRepo, ProductoService prodService) {
		this.ingPedidoRepo = ingPedidoRepo;
		this.prodService = prodService;
	}
	
	@Transactional
	public int ingPedidoCount() {
		return (int) ingPedidoRepo.count();	
	}

	@Transactional
	public Iterable<IngredientePedido> ingPedidoList() {
		return ingPedidoRepo.findAll();
		
	}
	@Transactional
	public IngredientePedido guardarIngredientePedido(IngredientePedido ing) {
		Double cantidad = ing.getCantidadPedida();
		Producto prod = ing.getIngrediente().getProducto();
		prod.setCantAct(prod.getCantAct()-cantidad);
		prodService.guardarProducto(prod);
		return ingPedidoRepo.save(ing);
		
	}
	
	@Transactional
	public IngredientePedido crearIngredientePedidoPorIngrediente(Ingrediente i) {
		IngredientePedido ip = new IngredientePedido();
		ip.setCantidadPedida(i.getCantidadUsualPP());
		ip.setIngrediente(i);
		return ip;
	}
	
	@Transactional
	public void borrarIngredientePedido(Integer id) {
		ingPedidoRepo.deleteById(id);
		
	}
	
	@Transactional
	public Optional<IngredientePedido> buscaIngPedidoPorId(Integer id) {
		return ingPedidoRepo.findById(id);
		
	}
	
	//Esto pertenece a la clase Ingrediente
	@Transactional(readOnly = true)
	public Collection<Ingrediente> encontrarIngredientes() throws DataAccessException {
		return ingPedidoRepo.encontrarIngredientes();
	}
	
	@Transactional(readOnly = true)
	public Ingrediente ingredienteAsociado(Integer ingrediente_pedido_id) throws DataAccessException{
		return ingPedidoRepo.ingredienteAsociado(ingrediente_pedido_id);
	}

		
}
