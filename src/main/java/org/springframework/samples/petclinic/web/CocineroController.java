package org.springframework.samples.petclinic.web;

import java.util.Optional;
import javax.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.samples.petclinic.model.Cocinero;
import org.springframework.samples.petclinic.service.CocineroService;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping(value = "/cocineros")
public class CocineroController {
	
	@Autowired
	private CocineroService cocineroService;
	
	@GetMapping()
	public String listadoCocineros(ModelMap modelMap) {
		String vista= "cocineros/listaCocineros";
		Iterable<Cocinero> cocineros=  cocineroService.findAll();
		modelMap.addAttribute("cocineros",cocineros);
		return vista;
		
	}
	
	@GetMapping(path="/new")
	public String crearCocinero(ModelMap modelMap) {
		String vista= "cocineros/editCocinero";
		modelMap.addAttribute("cocinero",new Cocinero());
		return vista;
	}
	
	@PostMapping(path="/save")
	public String guardarCocinero(@Valid Cocinero cocinero,BindingResult result,ModelMap modelMap) {
		String vista= "cocineros/listaCocinero";
		if(result.hasErrors()) {
			modelMap.addAttribute("cocinero", cocinero);
			return "cocineros/editCocinero";
		}else {
			cocineroService.guardarCocinero(cocinero);
			modelMap.addAttribute("message", "successfuly saved");
			vista=listadoCocineros(modelMap);
		}
		return vista;
		
	}
	@GetMapping(path="/delete/{cocineroId}")
	public String borrarCocinero(@PathVariable("cocineroId") int cocineroId, ModelMap modelMap) {
		String vista= "cocineros/listaCocineros";
		Optional<Cocinero> cam= cocineroService.buscaCocineroPorId(cocineroId);
		if(cam.isPresent()) {
			cocineroService.borrarCocinero(cocineroId);
			modelMap.addAttribute("message", "successfuly deleted");
		}else {
			modelMap.addAttribute("message", "not found");
			vista=listadoCocineros(modelMap);
		}
		return vista;
		
	}	
}
