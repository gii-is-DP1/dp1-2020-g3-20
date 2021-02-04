package org.springframework.samples.petclinic.service;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;

import org.springframework.dao.DataAccessException;
import org.springframework.samples.petclinic.model.EstadoPlato;
import org.springframework.samples.petclinic.model.Ingrediente;

import org.springframework.samples.petclinic.model.Plato;
import org.springframework.samples.petclinic.repository.PlatoRepository;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
public class PlatoService {

	@Autowired
	private PlatoRepository platoRepository;
	
	@Autowired
	private IngredienteService ingredienteService;
	
	@Transactional
	public int platoCount() {
		return (int) platoRepository.count();
	}

	@Transactional
	public Collection<Plato> platoList() {
		return  (Collection<Plato>) platoRepository.findAll();	
	}

	@Transactional
	public Plato guardarPlato(Plato plato) {
		return platoRepository.save(plato);	
	}
	
	@Transactional
	public void borrarPlato(Integer id) {
		platoRepository.deleteById(id);	
	}
	
	@Transactional
	public Optional<Plato> buscaPlatoPorId(Integer id) {
		return platoRepository.findById(id);	
	}
	
	public List<Ingrediente> ingredientePorPlato(Integer id){
		List<Ingrediente> ls= platoRepository.encontrarIngredientes();
		List<Ingrediente> res= new ArrayList<Ingrediente>();
 		for(Ingrediente l: ls) {
			if(l.getPlato().getId()==id) {
				res.add(l);
			}
		}
 		return res;
	}
	
	public boolean ingEstaRepetido(String nombreIng) {
		List<Ingrediente> ls= platoRepository.encontrarIngredientes();	
		boolean res = false;
 		for(Ingrediente l: ls) {
 			if(l.getProducto().getName().equals(nombreIng)) {
 				res= true;
 			}
 		}
 		return res;
	}
	
	public void borrarIngredientePorPlato(Integer id){
		List<Ingrediente> ls= platoRepository.encontrarIngredientes();	
 		for(Ingrediente l: ls) {
			if(l.getPlato().getId()==id) {
				ingredienteService.borrarIngrediente(l.getId());
			}
		}
	}
	
	//Mostrar platos disponibles para ofrecerlos
	public List<Plato> findAllAvailable() {
		return platoRepository.findAllAvailable();
	}
	
}
