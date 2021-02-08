package org.springframework.samples.petclinic.web;

import java.security.Principal;
import java.text.ParseException;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.Collection;
import java.util.Iterator;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.samples.petclinic.model.Comanda;
import org.springframework.samples.petclinic.model.Plato;
import org.springframework.samples.petclinic.model.PlatoPedido;
import org.springframework.samples.petclinic.model.PlatoPedidoDTO;
import org.springframework.samples.petclinic.service.CamareroService;
import org.springframework.samples.petclinic.service.ComandaService;
import org.springframework.samples.petclinic.service.PlatoPedidoService;
import org.springframework.samples.petclinic.service.PlatoService;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping(value = "/comanda")
public class ComandaController {
	
	@Autowired
	private ComandaService comandaService;

	@Autowired
	private PlatoPedidoService platoPedidoService;
	
	@Autowired
	private CamareroService camareroService;
	
	@Autowired
	private PlatoService platoService;
	
	//Vista de Propietario para la lista total de Comandas
	@GetMapping(path="/listaComandaTotal")
	public String listadoComandaTotal(ModelMap modelMap) {
		String vista= "comanda/listaComandaTotal";
		Iterable<Comanda> comanda = comandaService.findAll();
		modelMap.addAttribute("comanda",comanda);
		return vista;	
	}
	
	//Vista de Propietario para la lista de Comandas de UN DIA concreto
	@GetMapping(path="/listaComandaTotal/dia")
	public String listadoComandaDia(String date, ModelMap modelMap) {
		String vista= "comanda/listaComandaTotal";
		Collection<Comanda> comanda = comandaService.encontrarComandaDia(date);
		modelMap.addAttribute("comanda",comanda);
		return vista;	
	}
	
	//Vista de Camarero para la lista actual de Comandas sin finalizar
	@GetMapping(path="/listaComandaActual")
	public String listadoComandaActual(ModelMap modelMap) {
		String vista= "comanda/listaComandaActual";
		Collection<Comanda> comanda = comandaService.encontrarComandaActual();
		modelMap.addAttribute("comanda",comanda);
		return vista;	
	}
	
	//Vista de Camarero para la lista actual de Comandas sin finalizar
	@GetMapping(path="/listaComandaActual/finalizarComanda/{comandaID}")
	public String finalizarComanda(@PathVariable("comandaID") int comandaID, ModelMap modelMap) {
		String vista= "comanda/listaComandaActual";
		Optional<Comanda> comanda = comandaService.findById(comandaID);
		if(comanda.isPresent()) {
			Comanda res = comanda.get();
			Iterator<PlatoPedido> listaPlatosPedidos = res.getPlatosPedidos().iterator();
			while(listaPlatosPedidos.hasNext()) {
				PlatoPedido platoPedido = listaPlatosPedidos.next();
				if(!(platoPedido.getEstadoplato().getId()==3)) {
					modelMap.addAttribute("message", "Esta comanda aún tiene platos por finalizar");
					vista = listadoComandaActual(modelMap);
					return vista;
				}
			}
			if(res.getFechaFinalizado()==null) {
				res.setFechaFinalizado(LocalDateTime.now());
				modelMap.addAttribute("message", "La comanda se ha finalizado correctamente");
				vista = listadoComandaActual(modelMap);
			}else {
				modelMap.addAttribute("message", "La comanda ya está finalizada");
				vista = listadoComandaActual(modelMap);
			}
		}else {
			modelMap.addAttribute("message", "La comanda pedida no existe");
			vista = listadoComandaActual(modelMap);
		}
		return vista;
	}
	
	//Vista de Camarero para la lista de platos de una comanda
	@GetMapping(path="/listaComandaActual/{comandaID}")
	public String infoComanda(@PathVariable("comandaID") int comandaID, ModelMap modelMap) {
		String vista= "comanda/comandaDetails";		
		
		Optional<Comanda> optAux = comandaService.findById(comandaID);
		Comanda comanda = optAux.get();
		Iterable<Plato> listaPlatos = platoService.findAllAvailable();
		Iterable<PlatoPedido> allPP = platoPedidoService.findAll();
		Iterator<PlatoPedido> it = allPP.iterator();
		Collection<PlatoPedido> platosEC = new ArrayList<>();
		while(it.hasNext()) {
			PlatoPedido ppAux = it.next();
			if(ppAux.getComanda()!=null) {
				if(ppAux.getComanda().getId()==comandaID)
					platosEC.add(ppAux);
			}
		}
		
		modelMap.addAttribute("platopedido",new PlatoPedidoDTO());
		modelMap.addAttribute("platop",platosEC);
		modelMap.addAttribute("comanda",comanda);
		modelMap.addAttribute("listaPlatos",listaPlatos);
		return vista;
	}
	
	@GetMapping(path="/listaComandaActual/new")
	public String crearComanda(int mesa,ModelMap modelMap,Principal user) {
		Comanda comanda = new Comanda();		
		comanda.setMesa(mesa);
		comanda.setFechaCreado(LocalDateTime.now());
		comanda.setPrecioTotal(0.0);
		comanda.setCamarero(camareroService.buscaCamareroPorUser(user.getName()));
		comandaService.guardarComanda(comanda);
		int comandaId = comandaService.findLastId();
		modelMap.addAttribute("comanda", comanda);
		String vista=infoComanda(comandaId,modelMap);
		return vista;
	}	
	
	
	@PostMapping(path="/listaComandaActual/asignar/{comandaId}/{ppId}")
	public String asignarComanda(@PathVariable("comandaId") int comandaId, @PathVariable("ppId") int ppId, ModelMap modelMap) throws ParseException {
		PlatoPedido plato = platoPedidoService.findById(ppId).get();
		Comanda comanda = comandaService.findById(comandaId).get();
		plato.setComanda(comanda);
		comanda.setPrecioTotal(comanda.getPrecioTotal()+plato.getPlato().getPrecio());
		platoPedidoService.guardarPP(plato);
		comanda.setPrecioTotal(comanda.getPrecioTotal()+plato.getPlato().getPrecio());
		String vista= infoComanda(comandaId,modelMap);
		return vista; 
	}
}