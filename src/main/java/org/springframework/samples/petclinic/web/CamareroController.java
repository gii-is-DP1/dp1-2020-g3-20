package org.springframework.samples.petclinic.web;



import java.util.Optional;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.samples.petclinic.model.Camarero;
import org.springframework.samples.petclinic.service.CamareroService;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping(value = "/camareros")
public class CamareroController {
	
	@Autowired
	private CamareroService camareroService;
	
	@GetMapping()
	public String listadoCamareros(ModelMap modelMap) {
		String vista= "camareros/listaCamareros";
		Iterable<Camarero> camareros=  camareroService.camareroList();
		modelMap.addAttribute("camareros",camareros);
		return vista;
		
	}
	
	@GetMapping(path="/new")
	public String crearCamarero(ModelMap modelMap) {
		String vista= "camareros/editCamarero";
		modelMap.addAttribute("camarero",new Camarero());
		return vista;
	}
	
	@PostMapping(path="/save")
	public String guardarCamarero(@Valid Camarero camarero,BindingResult result,ModelMap modelMap) {
		String vista= "camareros/listaCamareros";
		if(result.hasErrors()) {
			modelMap.addAttribute("camarero", camarero);
			return "camareros/editCamarero";
		}else {
			camareroService.guardarCamarero(camarero);
			modelMap.addAttribute("message", "successfuly saved");
			vista=listadoCamareros(modelMap);
		}
		return vista;
		
	}
	@GetMapping(path="/delete/{camareroId}")
	public String borrarCamarero(@PathVariable("camareroId") int camareroId, ModelMap modelMap) {
		String vista= "camareros/listaCamareros";
		Optional<Camarero> cam= camareroService.buscaCamareroPorId(camareroId);
		if(cam.isPresent()) {
			camareroService.borrarCamarero(camareroId);
			modelMap.addAttribute("message", "successfuly deleted");
			vista=listadoCamareros(modelMap);
		}else {
			modelMap.addAttribute("message", "not found");
			vista=listadoCamareros(modelMap);
		}
		return vista;
		
	}
	
	
}
