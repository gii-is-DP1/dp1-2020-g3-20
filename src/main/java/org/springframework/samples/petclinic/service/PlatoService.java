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
	private PlatoRepository camRep;
	
	
	@Transactional
	public int platoCount() {
		return (int) camRep.count();
		
	}

	@Transactional
	public Collection<Plato> platoList() {
		return  (Collection<Plato>) camRep.findAll();
		
	}

	@Transactional
	public Plato guardarPlato(Plato plato) {
		return camRep.save(plato);
		
	}
	
	@Transactional
	public void borrarPlato(Integer id) {
		camRep.deleteById(id);
		
	}
	
	@Transactional
	public Optional<Plato> buscaPlatoPorId(Integer id) {
		return camRep.findById(id);
		
	}
	
	public List<Ingrediente> ingredientePorPlato(Integer id){
		List<Ingrediente> ls= camRep.encontrarIngredientes();
		List<Ingrediente> res= new ArrayList<Ingrediente>();
 		for(Ingrediente l: ls) {
			if(l.getPlato().getId()==id) {
				res.add(l);
			}
		}
 		return res;
	}
	
}
