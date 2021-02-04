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

	private IngredientePedidoRepository ingredientePedidoRepository;
	
	@Autowired
	public IngredientePedidoService(IngredientePedidoRepository ingredientePedidoRepository) {
		this.ingredientePedidoRepository = ingredientePedidoRepository;
	}
	
	
	@Transactional
	public int ingPedidoCount() {
		return (int) ingredientePedidoRepository.count();	
	}

	@Transactional
	public Iterable<IngredientePedido> ingPedidoList() {
		return ingredientePedidoRepository.findAll();
		
	}
	@Transactional
	public IngredientePedido guardarIngredientePedido(IngredientePedido ing) {
		return ingredientePedidoRepository.save(ing);
		
	}
	
	@Transactional
	public void borrarIngredientePedido(Integer id) {
		ingredientePedidoRepository.deleteById(id);
		
	}
	
	@Transactional
	public Optional<IngredientePedido> buscaIngPedidoPorId(Integer id) {
		return ingredientePedidoRepository.findById(id);
		
	}
	
	//Esto pertenece a la clase Ingrediente
	@Transactional(readOnly = true)
	public Collection<Ingrediente> encontrarIngredientes() throws DataAccessException {
		return ingredientePedidoRepository.encontrarIngredientes();
	}
	
	@Transactional(readOnly = true)
	public Ingrediente ingredienteAsociado(Integer ingrediente_pedido_id) throws DataAccessException{
		return ingredientePedidoRepository.ingredienteAsociado(ingrediente_pedido_id);
	}

		
}
