package org.springframework.samples.petclinic.web;

import java.util.Iterator;
import java.util.Optional;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.samples.petclinic.model.Camarero;
import org.springframework.samples.petclinic.service.AuthoritiesService;
import org.springframework.samples.petclinic.service.CamareroService;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import lombok.extern.slf4j.Slf4j;
@Slf4j
@Controller
@RequestMapping(value = "/camareros")
public class CamareroController {
	@Autowired
	private CamareroService camareroService;
	@Autowired
	private AuthoritiesService authoritiesService;
	
	public CamareroController(CamareroService camareroService, AuthoritiesService authoritiesService) {
		super();
		this.camareroService = camareroService;
		this.authoritiesService = authoritiesService;
	}

	@GetMapping()
	public String listadoCamareros(ModelMap modelMap) {
		String vista= "camareros/listaCamareros";
		Iterable<Camarero> camareros=  camareroService.findAll();
		Iterator<Camarero> it_camareros = camareros.iterator();
		
		if (!(it_camareros.hasNext())) {
			modelMap.addAttribute("message", "No hay camareros, contrata a alguien y crea su Ficha de Empleado");
		}
		
		modelMap.addAttribute("camareros",camareros);
		return vista;
		
	}
	
	@GetMapping(path="/new")
	public String crearCamarero(ModelMap modelMap) {
		String vista= "camareros/editCamarero";
		modelMap.addAttribute("camarero",new Camarero());
		modelMap.addAttribute("usernames", authoritiesService.findAllUsernames());
		return vista;
	}
	
	@PostMapping(path="/save")
	public String save(@Valid Camarero camarero,BindingResult result,ModelMap modelMap) {
		String vista= "camareros/listaCamareros";
		if(result.hasErrors()) {
			log.info(String.format("Waiter with name %s wasn't able to be created", camarero.getName(), camarero.getId()));
			modelMap.addAttribute("camarero", camarero);
			return "camareros/editCamarero";
		}else if(authoritiesService.findAllUsernames().contains(camarero.getUsuario())){
			modelMap.addAttribute("message", "Este nombre de usuario ya está en uso");
			vista=crearCamarero(modelMap);
		}else {
			camareroService.save(camarero);
			modelMap.addAttribute("message", "Guardado correctamente");
			vista=listadoCamareros(modelMap);
		}
		return vista;
	}
	
	@GetMapping(path="/delete/{camareroId}")
	public String borrarCamarero(@PathVariable("camareroId") int camareroId, ModelMap modelMap) {
		String vista= "camareros/listaCamareros";
		Optional<Camarero> cam= camareroService.findById(camareroId);
		if(cam.isPresent()) {
			camareroService.deleteById(camareroId);
			modelMap.addAttribute("message", "Borrado correctamente");
			vista=listadoCamareros(modelMap);
		}else {
			modelMap.addAttribute("message", "Camarero no encontrado");
			vista=listadoCamareros(modelMap);
		}
		return vista;
	}
	
	@GetMapping(value = "/edit/{camareroId}")
	public String initUpdateCamareroForm(@PathVariable("camareroId") int camareroId, ModelMap model) {
		String vista= "camareros/editarCamareros";
		
			Camarero cam =  camareroService.findById(camareroId).get();
			model.addAttribute(cam);
			return vista;
	}
	@PostMapping(value = "/edit")
	public String processUpdateCamareroForm(@Valid Camarero camarero, BindingResult result,ModelMap modelMap) {
		if(result.hasErrors()) {
			log.info(String.format("Waiter with name %s and ID %d wasn't able to be updated", camarero.getName(), camarero.getId()));
			modelMap.addAttribute("camarero", camarero);
			return "camareros/editarCamareros";
		}else if(authoritiesService.findAllUsernames().contains(camarero.getUsuario())
				&& !camareroService.findById(camarero.getId()).get().getUsuario().equals(camarero.getUsuario())){
			log.info(String.format("Try to update a waiter with same user name as someone", camarero.getName(), camarero.getId()));
			modelMap.addAttribute("message", "Este nombre de usuario ya está en uso");
			return initUpdateCamareroForm(camarero.getId(),modelMap);
		}else {
			camareroService.save(camarero);
		return "redirect:/camareros";
		}
	}
}
