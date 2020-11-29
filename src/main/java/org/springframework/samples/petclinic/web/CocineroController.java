package org.springframework.samples.petclinic.web;

import java.util.Optional;
import javax.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.samples.petclinic.model.Cocinero;
import org.springframework.samples.petclinic.model.Manager;
import org.springframework.samples.petclinic.service.CocineroService;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping(value = "/cocinero")
public class CocineroController {
	
	@Autowired
	private CocineroService cocineroService;
	
	@GetMapping()
	public String listadoCocinero(ModelMap modelMap) {
		String vista= "cocinero/listaCocinero";
		Iterable<Cocinero> cocinero=  cocineroService.findAll();
		modelMap.addAttribute("cocinero",cocinero);
		return vista;
		
	}
	
	@GetMapping(path="/new")
	public String crearCocinero(ModelMap modelMap) {
		String vista= "cocinero/editCocinero";
		modelMap.addAttribute("cocinero",new Cocinero());
		return vista;
	}
	
	@PostMapping(path="/save")
	public String guardarCocinero(@Valid Cocinero cocinero,BindingResult result,ModelMap modelMap) {
		String vista= "cocinero/listaCocinero";
		if(result.hasErrors()) {
			modelMap.addAttribute("cocinero", cocinero);
			return "cocinero/editCocinero";
		}else {
			cocineroService.guardarCocinero(cocinero);
			modelMap.addAttribute("message", "successfuly saved");
			vista=listadoCocinero(modelMap);
		}
		return vista;
		
	}
	@GetMapping(path="/delete/{cocineroId}")
	public String borrarCocinero(@PathVariable("cocineroId") int cocineroId, ModelMap modelMap) {
		String vista= "cocinero/listaCocinero";
		Optional<Cocinero> cam= cocineroService.buscaCocineroPorId(cocineroId);
		if(cam.isPresent()) {
			cocineroService.borrarCocinero(cocineroId);
			modelMap.addAttribute("message", "successfuly deleted");
		}else {
			modelMap.addAttribute("message", "not found");
			vista=listadoCocinero(modelMap);
		}
		return vista;
	}
	
	@GetMapping(value = "/edit/{cocineroId}")
	public String initUpdateCocineroForm(@PathVariable("cocineroId") int cocineroId, ModelMap model) {
		String vista= "cocinero/editarCocinero";
		
			Cocinero cocinero =  cocineroService.buscaCocineroPorId(cocineroId).get();
			model.addAttribute(cocinero);
			return vista;
	}
	@PostMapping(value = "/edit")
	public String processUpdateCocineroForm(@Valid Cocinero cocinero, BindingResult result,ModelMap modelMap) {
		
		String vista= "cocinero/editarCocinero";
		
		if(result.hasErrors()) {
			modelMap.addAttribute("cocinero", cocinero);
		
			return vista;
		}
		else {
		this.cocineroService.guardarCocinero(cocinero);
			return "redirect:/cocinero";
	}
		
	}
	
	
	
	
	
	
}
