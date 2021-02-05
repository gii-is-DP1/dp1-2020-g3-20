package org.springframework.samples.petclinic.service;

import static org.hamcrest.CoreMatchers.nullValue;

import java.util.ArrayList;
import java.util.Collection;
import java.util.HashSet;
import java.util.Iterator;
import java.util.List;
import java.util.Optional;
import java.util.Set;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataAccessException;
import org.springframework.samples.petclinic.model.EstadoPlato;
import org.springframework.samples.petclinic.model.Ingrediente;
import org.springframework.samples.petclinic.model.IngredientePedido;
import org.springframework.samples.petclinic.model.PlatoPedido;
import org.springframework.samples.petclinic.model.PlatoPedidoDTO;
import org.springframework.samples.petclinic.model.Producto;
import org.springframework.samples.petclinic.repository.IngredientePedidoRepository;
import org.springframework.samples.petclinic.repository.PlatoPedidoRepository;
import org.springframework.samples.petclinic.service.IngredientePedidoService;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
public class PlatoPedidoService {
	private PlatoPedidoRepository ppRepo;
	
	private IngredientePedidoRepository ingPedidoRep;
	private IngredientePedidoService ingService;
	private ProductoService prodService;
	
	
	@Autowired
	public PlatoPedidoService(IngredientePedidoService ingService, PlatoPedidoRepository ppRepo, ProductoService prodService) {
		this.ppRepo = ppRepo;
		this.ingService = ingService;
		this.prodService = prodService;
	}
	
	@Transactional
	public int productoCount() {
		return (int) ppRepo.count();	
	}

	@Transactional
	public Iterable<PlatoPedido> findAll() {
		return ppRepo.findAll();
		
	}
	@Transactional
	public PlatoPedido guardarPP(PlatoPedido pp) {
//		pp.getPlato().getIngredientes();
		if((pp.getEstadoplato().getId().equals(1)) & (pp.getComanda() != null)) {
			Iterator<IngredientePedido> ipl = pp.getIngredientesPedidos().iterator();
			while(ipl.hasNext()) {
				IngredientePedido ip = ipl.next();
				Double cantidad = ip.getCantidadPedida();
				Producto prod = ip.getIngrediente().getProducto();
				prod.setCantAct(prod.getCantAct()-cantidad);
				prodService.guardarProducto(prod);
			}
		}
		return ppRepo.save(pp);
		
	}
	
	@Transactional
	public Collection<IngredientePedido> CrearIngredientesPedidos(PlatoPedido pp) {
		Collection<Ingrediente> ingList = pp.getPlato().getIngredientes();
		Collection<IngredientePedido> res = new ArrayList<>();
		Iterator<Ingrediente> iterator = ingList.iterator();
		while (iterator.hasNext()) {
			Ingrediente i = iterator.next();
			IngredientePedido ingp = ingService.crearIngredientePedidoPorIngrediente(i);
			ingp.setPp(pp);
			res.add(ingp);
		}
		return res;
		
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
 			if(l.getPp()!=null) {
			if(l.getPp().getId()==id) {
				res.add(l);
			}}
		}
 		return res;
	}
	
}
