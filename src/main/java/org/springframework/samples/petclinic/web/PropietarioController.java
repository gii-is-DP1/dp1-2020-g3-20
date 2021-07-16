package org.springframework.samples.petclinic.web;

import java.util.Optional;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.samples.petclinic.model.Propietario;
import org.springframework.samples.petclinic.service.AuthoritiesService;
import org.springframework.samples.petclinic.service.PropietarioService;
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
@RequestMapping(value = "/propietarios")
public class PropietarioController {
	@Autowired
	private PropietarioService propietarioService;
	@Autowired
	private AuthoritiesService authoritiesService;
	
	public PropietarioController(PropietarioService propietarioService, AuthoritiesService authoritiesService) {
		super();
		this.propietarioService = propietarioService;
		this.authoritiesService = authoritiesService;
	}

	@GetMapping()
	public String listadoPropietarios(ModelMap modelMap) {
		String vista = "propietarios/listaPropietarios";
		if(propietarioService.count()==0) {
			modelMap.addAttribute("message", "la lista esta vacia");
			return vista;
		}else {
			Iterable<Propietario> propietarios = propietarioService.findAll();
			modelMap.addAttribute("propietarios", propietarios);
			return vista;
		}
	}

	@GetMapping(path = "/new")
	public String crearPropietario(ModelMap modelMap) {
		String vista = "propietarios/editPropietario";
		modelMap.addAttribute("propietario", new Propietario());
		return vista;
	}

	@PostMapping(path = "/save")
	public String guardarPropietario(@Valid Propietario propietario, BindingResult result, ModelMap modelMap) {
		String vista = "propietarios/listaPropietarios";
		if (result.hasErrors()) {
			log.info(String.format("Owner with name %s wasn't able to be created", propietario.getName()));
			modelMap.addAttribute("propietario", propietario);
			return "propietarios/editPropietario";
		} else if (authoritiesService.findAllUsernames().contains(propietario.getUsuario())) {
			modelMap.addAttribute("message", "Este nombre de usuario ya está en uso");
			return crearPropietario(modelMap);
		} else {
			propietarioService.save(propietario);
			modelMap.addAttribute("message", "successfuly saved");
			vista = listadoPropietarios(modelMap);
		}
		return vista;

	}

	@GetMapping(path = "/delete/{propietarioId}")
	public String borrarPropietario(@PathVariable("propietarioId") final int propietarioId, final ModelMap modelMap) {
		// String vista= "propietarios/listaPropietarios";
		Optional<Propietario> propietario = this.propietarioService.findById(propietarioId);
		if (propietario.isPresent()) {
			propietarioService.deleteById(propietario.get().getId());
			modelMap.addAttribute("message", "successfuly deleted");
		} else {
			modelMap.addAttribute("message", "not found");
			// vista=listadoPropietarios(modelMap);
		}
		return "redirect:/propietarios";

	}

	@GetMapping(value = "/edit/{propietarioId}")
	public String initUpdatePropietarioForm(@PathVariable("propietarioId") int propietarioId, ModelMap model) {
		String vista = "propietarios/editarPropietario";

		// if(username.equals(propietarioService.buscaPropietarioPorId(propietarioId).get().getName()))
		// {
		Propietario propietario = propietarioService.findById(propietarioId).get();
		model.addAttribute(propietario);
		return vista;
		/*
		 * }else { return "redirect:/propietarios"; }
		 */
	}

	@PostMapping(value = "/edit")
	public String processUpdatePropietarioForm(@Valid Propietario propietario, BindingResult result, ModelMap modelMap) {
		if (result.hasErrors()) {
			modelMap.addAttribute("propietario", propietario);
			return "propietarios/editarPropietario";
		} else if (authoritiesService.findAllUsernames().contains(propietario.getUsuario()) 
				&& !propietarioService.findById(propietario.getId()).get().getUsuario().equals(propietario.getUsuario())) {
			modelMap.addAttribute("message", "Este nombre de usuario ya está en uso");
			return initUpdatePropietarioForm(propietario.getId(),modelMap);
		}else {
			this.propietarioService.save(propietario);
			return "redirect:/propietarios";
		}
	}
}
