package org.springframework.samples.petclinic.service;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataAccessException;
import org.springframework.samples.petclinic.model.EstadoPlato;
import org.springframework.samples.petclinic.model.IngredientePedido;
import org.springframework.samples.petclinic.model.PlatoPedido;
import org.springframework.samples.petclinic.repository.PlatoPedidoRepository;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
public class PlatoPedidoService {
	private PlatoPedidoRepository platoPedidoRepository;
		
	
	@Autowired
	public PlatoPedidoService(PlatoPedidoRepository platoPedidoRepository) {
		this.platoPedidoRepository = platoPedidoRepository;
	}
	
	@Transactional
	public int productoCount() {
		return (int) platoPedidoRepository.count();	
	}

	@Transactional
	public Iterable<PlatoPedido> findAll() {
		return platoPedidoRepository.findAll();
		
	}
	@Transactional
	public PlatoPedido guardarPP(PlatoPedido pp) {
		return platoPedidoRepository.save(pp);
		
	}
	
	@Transactional
	public void borrarPP(Integer id) {
		platoPedidoRepository.deleteById(id);
		
	}
	
	@Transactional
	public Optional<PlatoPedido> buscaPPPorId(Integer id) {
		return platoPedidoRepository.findById(id);
		
	}
	
	//Esto pertenece a la clase EstadoPlato
	@Transactional(readOnly = true)
	public Collection<EstadoPlato> encontrarEstadoPlato() throws DataAccessException {
		return platoPedidoRepository.encontrarEstadosPlato();
	}
	
	//Esto pertenece a la clase Plato
	@Transactional(readOnly = true)
	public Collection<String> encontrarPlatos() throws DataAccessException {
		return platoPedidoRepository.encontrarPlatos();
	}
	
//	//Esto pertenece a la clase Comanda
//	@Transactional(readOnly = true)
//	public Collection<IngredientePedido> encontrarIngredientePedido() throws DataAccessException {
//		return ppRepo.encontrarIngredientePedido();
//	}
		
	
//	//Esto pertenece a la clase IngredientePedido
	public List<IngredientePedido> ingredientePedidoPorPlatoPedido(Integer id){
		Collection<IngredientePedido> ls= platoPedidoRepository.encontrarIngredientesPedido();
		List<IngredientePedido> res= new ArrayList<IngredientePedido>();
 		for(IngredientePedido l: ls) {
			if(l.getPp().getId()==id) {
				res.add(l);
			}
		}
 		return res;
	}
	
}
