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

@Service
public class IngredienteService {
	
	private IngredienteRepository ingRepo;
	
	@Autowired
	public IngredienteService(IngredienteRepository ingRepo) {
		this.ingRepo = ingRepo;
	}
	
	
	@Transactional
	public int ingCount() {
		return (int) ingRepo.count();	
	}

	@Transactional
	public Iterable<Ingrediente> ingList() {
		return ingRepo.findAll();
		
	}
	@Transactional
	public Ingrediente guardarIngrediente(Ingrediente ing) {
		return ingRepo.save(ing);
		
	}
	
	@Transactional
	public void borrarIngrediente(Integer id) {
		ingRepo.deleteById(id);
		
	}
	
	@Transactional
	public Optional<Ingrediente> buscaIngPorId(Integer id) {
		return ingRepo.findById(id);
		
	}
	
	//Esto pertenece a la clase Producto
	@Transactional(readOnly = true)
	public Collection<Producto> encontrarProductos() throws DataAccessException {
		return ingRepo.encontrarProductos();
	}
	

		
}
