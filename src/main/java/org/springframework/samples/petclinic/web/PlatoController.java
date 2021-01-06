package org.springframework.samples.petclinic.web;

import java.util.Optional;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.samples.petclinic.model.Plato;
import org.springframework.samples.petclinic.service.PlatoService;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping(value = "/platos")
public class PlatoController {
	

	@Autowired
	private PlatoService platoService;
	
	@GetMapping()
	public String listadoPlatos(ModelMap modelMap) {
		String vista= "platos/listaPlatos";
		Iterable<Plato> platos=  platoService.platoList();
		modelMap.addAttribute("platos",platos);
		return vista;
		
	}
	
	@GetMapping(path="/new")
	public String crearPlato(ModelMap modelMap) {
		String vista= "platos/editPlatos";
		modelMap.addAttribute("platos",new Plato());
		return vista;
	}
	
	@PostMapping(path="/save")
	public String guardarPlato(@Valid Plato plato,BindingResult result,ModelMap modelMap) {
		String vista= "platos/listaPlatos";
		if(result.hasErrors()) {
			modelMap.addAttribute("plato", plato);
			return "platos/editPlatos";
		}else {
			platoService.guardarPlato(plato);
			modelMap.addAttribute("message", "successfuly saved");
			vista=listadoPlatos(modelMap);
		}
		return vista;
		
	}
	@GetMapping(path="/delete/{platoId}")
	public String borrarPlato(@PathVariable("platoId") int platoId, ModelMap modelMap) {
		String vista= "platos/listaPlatos";
		Optional<Plato> cam= platoService.buscaPlatoPorId(platoId);
		if(cam.isPresent()) {
			platoService.borrarPlato(platoId);
			modelMap.addAttribute("message", "successfuly deleted");
			vista=listadoPlatos(modelMap);
		}else {
			modelMap.addAttribute("message", "not found");
			vista=listadoPlatos(modelMap);
		}
		return vista;
	}
	

	@GetMapping(value = "/edit/{platoId}")
	public String initUpdatePlatoForm(@PathVariable("platoId") int platoId, ModelMap model) {
		String vista= "platos/editarPlatos";
		
			Plato plato =  platoService.buscaPlatoPorId(platoId).get();
			model.addAttribute(plato);
			return vista;
	}
	@PostMapping(value = "/edit")
	public String processUpdatePlatoForm(@Valid Plato plato, BindingResult result,ModelMap modelMap) {
		
		String vista= "platos/editarPlatos";
		
		if(result.hasErrors()) {
			modelMap.addAttribute("plato", plato);
		
			return vista;
		}else {
		this.platoService.guardarPlato(plato);
			return "redirect:/platos";
		}
	}
}
