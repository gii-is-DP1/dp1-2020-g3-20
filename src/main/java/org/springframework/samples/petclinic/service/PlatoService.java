package org.springframework.samples.petclinic.service;

import java.util.ArrayList;
import java.util.Collection;
import java.util.Iterator;
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

import lombok.extern.slf4j.Slf4j;
@Slf4j
@Service
public class PlatoService {

	@Autowired
	private PlatoRepository platoRepository;
	
	@Autowired
	private IngredienteService ingredienteService;
	
	public PlatoService(PlatoRepository platoRepository, IngredienteService ingredienteService) {
		super();
		this.platoRepository = platoRepository;
		this.ingredienteService = ingredienteService;
	}

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
		log.info(String.format("Plate with name %s has been saved", plato.getName()));
		return platoRepository.save(plato);	
	}
	
	@Transactional
	public void borrarPlato(Integer id) {
		Plato plato = platoRepository.findById(id).get();
		platoRepository.deleteById(id);	
		log.info(String.format("Plate with name %s has been saved", plato.getName()));
		
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
	

	public boolean ingEstaRepetido(String nombreIng, int platoId) {
		List<Ingrediente> ls= platoRepository.encontrarIngredientes();	

		boolean res = false;
 		for(Ingrediente l: ls) {
 			if(l.getProducto().getName().equals(nombreIng) && l.getPlato().getId().equals(platoId)) {
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
	
	//Mostrar platos disponibles para ofrecerlos (elimina platos de los que falan ingredientes)
	public List<Plato> findAllAvailable() {
		Boolean falta = false;
		List<Plato> res = platoRepository.findAllAvailable();
		Iterator<Plato> iterator = res.iterator();
		while (iterator.hasNext()) {
			falta=false;
			Plato plato = iterator.next();
			Iterator<Ingrediente> listaIngredientes = plato.getIngredientes().iterator();
			while (listaIngredientes.hasNext()) {
				Ingrediente ingrediente = listaIngredientes.next();
				if(ingrediente.getCantidadUsualPP() > ingrediente.getProducto().getCantAct()) {
					falta = true;
				}
			}
			if(falta) {
				res.remove(plato);
			}
			
		}
		return res;
	}
	
}
