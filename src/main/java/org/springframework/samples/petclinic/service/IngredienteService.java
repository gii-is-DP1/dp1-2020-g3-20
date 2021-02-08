package org.springframework.samples.petclinic.service;

import java.util.Collection;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataAccessException;
import org.springframework.samples.petclinic.model.Ingrediente;
import org.springframework.samples.petclinic.model.Producto;
import org.springframework.samples.petclinic.repository.IngredienteRepository;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import lombok.extern.slf4j.Slf4j;
@Slf4j
@Service
public class IngredienteService {
	
	private IngredienteRepository ingredienteRepository;
	
	@Autowired
	public IngredienteService(IngredienteRepository ingRepo) {
		this.ingredienteRepository = ingRepo;
	}
	
	@Transactional
	public int ingCount() {
		return (int) ingredienteRepository.count();	
	}

	@Transactional
	public Iterable<Ingrediente> ingList() {
		return ingredienteRepository.findAll();
		
	}
	@Transactional
	public Ingrediente guardarIngrediente(Ingrediente ing) {
		log.info(String.format("Ingredient with name %s and amount %f has been saved", ing.getProducto().getName(), ing.getCantidadUsualPP()));
		return ingredienteRepository.save(ing);
		
	}
	
	@Transactional
	public void borrarIngrediente(Integer id) {
		Ingrediente ing = ingredienteRepository.findById(id).get();
		ingredienteRepository.deleteById(id);
		log.info(String.format("Ingredient with name %s and amount %f has been deleted", ing.getProducto().getName(), ing.getCantidadUsualPP()));
		
		
	}
	
	@Transactional
	public Optional<Ingrediente> buscaIngPorId(Integer id) {
		return ingredienteRepository.findById(id);
		
	}
	
	//Esto pertenece a la clase Producto
	@Transactional(readOnly = true)
	public Collection<Producto> encontrarProductos() throws DataAccessException {
		return ingredienteRepository.encontrarProductos();
	}
	

		
}
