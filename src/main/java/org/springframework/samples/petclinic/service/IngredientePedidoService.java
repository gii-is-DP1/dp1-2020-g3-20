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

import lombok.extern.slf4j.Slf4j;
@Slf4j
@Service
public class IngredientePedidoService {

	private IngredientePedidoRepository ingredientePedidoRepository;
	private ProductoService productoService;

	@Autowired
	public IngredientePedidoService(IngredientePedidoRepository ingredientePedidoRepository, ProductoService prodService) {
		this.ingredientePedidoRepository = ingredientePedidoRepository;
		this.productoService = prodService;
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
		Double cantidad = ing.getCantidadPedida();
		Producto prod = ing.getIngrediente().getProducto();
		prod.setCantAct(prod.getCantAct()-cantidad);
		productoService.guardarProducto(prod);
		return ingredientePedidoRepository.save(ing);
		
	}
	
	@Transactional
	public IngredientePedido crearIngredientePedidoPorIngrediente(Ingrediente i) {
		IngredientePedido ip = new IngredientePedido();
		ip.setCantidadPedida(i.getCantidadUsualPP());
		ip.setIngrediente(i);
		log.info(String.format("IngredientOrder with ingredient %s and amount %f has been saved", ip.getIngrediente().getProducto().getName(), ip.getCantidadPedida()));
		return ip;
	}
	
	@Transactional
	public void borrarIngredientePedido(Integer id) {
		IngredientePedido ip = ingredientePedidoRepository.findById(id).get();
		ingredientePedidoRepository.deleteById(id);
		log.info(String.format("IngredientOrder with ingredient %s and amount %f has been delete", ip.getIngrediente().getProducto().getName(), ip.getCantidadPedida()));	
		
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
