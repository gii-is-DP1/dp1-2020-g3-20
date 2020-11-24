package org.springframework.samples.petclinic.web;

import java.util.Optional;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.samples.petclinic.model.Camarero;
import org.springframework.samples.petclinic.model.Propietario;
import org.springframework.samples.petclinic.service.CamareroService;
import org.springframework.samples.petclinic.service.PropietarioService;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping(value = "/propietarios")
public class PropietarioController {
	
	@Autowired
	private PropietarioService propietarioService;
	

	@GetMapping()
	public String listadoPropietarios(ModelMap modelMap) {
		String vista= "propietarios/listaPropietarios";
		Iterable<Propietario> propietarios=  propietarioService.listPropietario();
		modelMap.addAttribute("propietarios",propietarios);
		return vista;
		
	}
	
	@GetMapping(path="/new")
	public String crearPropietario(ModelMap modelMap) {
		String vista= "propietarios/editPropietario";
		modelMap.addAttribute("propietario",new Propietario());
		return vista;
	}
	@PostMapping(path="/save")
	public String guardarPropietario(@Valid Propietario propietario,BindingResult result,ModelMap modelMap) {
		String vista= "propietarios/listaPropietarios";
		if(result.hasErrors()) {
			modelMap.addAttribute("propietario", propietario);
			return "camareros/editCamarero";
		}else {
			propietarioService.guardarPropietario(propietario);
			modelMap.addAttribute("message", "successfuly saved");
			vista=listadoPropietarios(modelMap);
		}
		return vista;
		
	}
	
	@GetMapping(path="/delete/{propietarioId}")
	public String borrarPropietario(@PathVariable("propietarioId") final int propietarioId, final ModelMap modelMap) {
		//String vista= "propietarios/listaPropietarios";
		Optional<Propietario> propie= this.propietarioService.buscaPropietarioPorId(propietarioId);
		if(propie.isPresent()) {
			propietarioService.delete(propie.get());
			modelMap.addAttribute("message", "successfuly deleted");
		}else {
			modelMap.addAttribute("message", "not found");
			//vista=listadoPropietarios(modelMap);
		}
		return "redirect:/propietarios";
		
	}

}
