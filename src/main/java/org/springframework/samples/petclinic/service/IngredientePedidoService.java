package org.springframework.samples.petclinic.service;


import java.util.Collection;
import java.util.Optional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataAccessException;
import org.springframework.samples.petclinic.model.Ingrediente;
import org.springframework.samples.petclinic.model.IngredientePedido;
import org.springframework.samples.petclinic.repository.IngredientePedidoRepository;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
public class IngredientePedidoService {

	private IngredientePedidoRepository ingPedidoRepo;
	
	@Autowired
	public IngredientePedidoService(IngredientePedidoRepository ingPedidoRepo) {
		this.ingPedidoRepo = ingPedidoRepo;
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
