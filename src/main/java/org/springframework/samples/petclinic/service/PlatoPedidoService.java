package org.springframework.samples.petclinic.service;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataAccessException;
import org.springframework.samples.petclinic.model.EstadoPlato;
import org.springframework.samples.petclinic.model.Ingrediente;
import org.springframework.samples.petclinic.model.IngredientePedido;
import org.springframework.samples.petclinic.model.PlatoPedido;
import org.springframework.samples.petclinic.model.PlatoPedidoDTO;
import org.springframework.samples.petclinic.repository.IngredientePedidoRepository;
import org.springframework.samples.petclinic.repository.PlatoPedidoRepository;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
public class PlatoPedidoService {
	private PlatoPedidoRepository ppRepo;
	
	private IngredientePedidoRepository ingPedidoRep;
	
	
	@Autowired
	public PlatoPedidoService(PlatoPedidoRepository ppRepo) {
		this.ppRepo = ppRepo;
	}
	
	
	@Transactional
	public int productoCount() {
		return (int) ppRepo.count();	
	}

	@Transactional
	public Iterable<PlatoPedido> ppList() {
		return ppRepo.findAll();
		
	}
	@Transactional
	public PlatoPedido guardarPP(PlatoPedido pp) {
		return ppRepo.save(pp);
		
	}
	
	@Transactional
	public void borrarPP(Integer id) {
		ppRepo.deleteById(id);
		
	}
	
	@Transactional
	public Optional<PlatoPedido> buscaPPPorId(Integer id) {
		return ppRepo.findById(id);
		
	}
	
	//Esto pertenece a la clase EstadoPlato
	@Transactional(readOnly = true)
	public Collection<EstadoPlato> encontrarEstadoPlato() throws DataAccessException {
		return ppRepo.encontrarEstadosPlato();
	}
	
	//Esto pertenece a la clase Plato
	@Transactional(readOnly = true)
	public Collection<String> encontrarPlatos() throws DataAccessException {
		return ppRepo.encontrarPlatos();
	}
	
//	//Esto pertenece a la clase Comanda
//	@Transactional(readOnly = true)
//	public Collection<IngredientePedido> encontrarIngredientePedido() throws DataAccessException {
//		return ppRepo.encontrarIngredientePedido();
//	}
		
	
//	//Esto pertenece a la clase IngredientePedido
	public List<IngredientePedido> ingredientePedidoPorPlatoPedido(Integer id){
		Collection<IngredientePedido> ls= ppRepo.encontrarIngredientesPedido();
		List<IngredientePedido> res= new ArrayList<IngredientePedido>();
 		for(IngredientePedido l: ls) {
			if(l.getPp().getId()==id) {
				res.add(l);
			}
		}
 		return res;
	}
	
}
