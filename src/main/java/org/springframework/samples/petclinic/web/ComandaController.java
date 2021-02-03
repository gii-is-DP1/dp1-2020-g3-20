package org.springframework.samples.petclinic.web;

import java.text.ParseException;
import java.time.LocalDate;
import java.util.Collection;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.samples.petclinic.model.Comanda;
import org.springframework.samples.petclinic.model.LineaPedido;
import org.springframework.samples.petclinic.service.ComandaService;
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
	
	//Vista de Camarero para la lista actual de Comandas sin finalizar
	@GetMapping()
	public String listadoComandaActual(ModelMap modelMap) {
		String vista= "comanda/listaComandaActual";
		Collection<Comanda> comanda = comandaService.encontrarComandaActual();
		modelMap.addAttribute("comanda",comanda);
		return vista;	
	}
	
	
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
		String vista= "comanda/listaComandaTotal/dia";
		Collection<Comanda> comanda = comandaService.encontrarComandaDia(date);
		modelMap.addAttribute("comanda",comanda);
		return vista;	
	}
	
	@GetMapping(path="/new")
	public String crearComanda(ModelMap modelMap) {
		String vista= "comanda/editComanda";
		modelMap.addAttribute("comanda",new Comanda());
		return vista;
	}
	
	@PostMapping(path="/save")
	public String guardarComanda(Comanda comanda,BindingResult result,ModelMap modelMap) throws ParseException {
		String vista= "comanda/listaComanda";			
		if(result.hasErrors()) {
			modelMap.addAttribute("comanda", comanda);
			return "comanda/editComanda";
		}else {
			comandaService.guardarComanda(comanda);
			modelMap.addAttribute("message", "Guardado Correctamente");
			vista=listadoComandaActual(modelMap);
		}
		return vista; 
	}

	@GetMapping(value = "/edit/{comandaId}")
	public String initUpdateComandaForm(@PathVariable("comandaId") int comandaId, ModelMap model) {		
		String vista= "comanda/editarComanda";	
		return vista;
		}
	
	@PostMapping(value = "/edit")
	public String processUpdateProductoForm(Comanda comanda, BindingResult result,ModelMap modelMap) throws ParseException {
		if(result.hasErrors()) {
			modelMap.addAttribute("comanda", comanda);
			return "producto/editarComanda";
		}else {
			this.comandaService.guardarComanda(comanda);
			modelMap.addAttribute("message", "Guardado Correctamente");
			return "redirect:/comanda";
		}
	}		
}