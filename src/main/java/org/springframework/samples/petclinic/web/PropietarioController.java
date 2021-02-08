package org.springframework.samples.petclinic.web;

import java.util.Iterator;
import java.util.Optional;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.samples.petclinic.model.Camarero;
import org.springframework.samples.petclinic.model.Propietario;
import org.springframework.samples.petclinic.service.AuthoritiesService;
import org.springframework.samples.petclinic.service.PropietarioService;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

@Controller
@RequestMapping(value = "/propietarios")
public class PropietarioController {

	@Autowired
	private PropietarioService propietarioService;
	@Autowired
	private AuthoritiesService authoritiesService;

	@GetMapping()
	public String listadoPropietarios(ModelMap modelMap) {
		String vista = "propietarios/listaPropietarios";
		if(propietarioService.propietarioCount()==0) {
			modelMap.addAttribute("message", "la lista esta vacia");
			return vista;
		}else {
			Iterable<Propietario> propietarios = propietarioService.listPropietario();
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
			modelMap.addAttribute("propietario", propietario);
			return "propietarios/editPropietario";
		} else if (authoritiesService.findAllUsernames().contains(propietario.getUsuario())) {
			modelMap.addAttribute("message", "Este nombre de usuario ya está en uso");
			return crearPropietario(modelMap);
		} else {
			propietarioService.guardarPropietario(propietario);
			modelMap.addAttribute("message", "successfuly saved");
			vista = listadoPropietarios(modelMap);
		}
		return vista;

	}

	@GetMapping(path = "/delete/{propietarioId}")
	public String borrarPropietario(@PathVariable("propietarioId") final int propietarioId, final ModelMap modelMap) {
		// String vista= "propietarios/listaPropietarios";
		Optional<Propietario> propietario = this.propietarioService.buscaPropietarioPorId(propietarioId);
		if (propietario.isPresent()) {
			propietarioService.delete(propietario.get());
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
		Propietario propietario = propietarioService.buscaPropietarioPorId(propietarioId).get();
		model.addAttribute(propietario);
		return vista;
		/*
		 * }else { return "redirect:/propietarios"; }
		 */
	}

	@PostMapping(value = "/edit")
	public String processUpdatePropietarioForm(@Valid Propietario propietario, BindingResult result, ModelMap modelMap,	@RequestParam(value = "version", required = false) Integer version) {
		if (result.hasErrors()) {
			modelMap.addAttribute("propietario", propietario);
			return "propietarios/editarPropietario";
		} else if (authoritiesService.findAllUsernames().contains(propietario.getUsuario())) {
			modelMap.addAttribute("message", "Este nombre de usuario ya está en uso");
			return initUpdatePropietarioForm(propietario.getId(),modelMap);
		} else if (propietario.getVersion() != version) {
			modelMap.addAttribute("message", "El propietario que intentas editar ya se estaba editando, intenta de nuevo por favor");
			return listadoPropietarios(modelMap);
		} else {
			// propietario.setOwner(propietarioService.findByUsername(SecurityContextHolder.getContext().getAuthentication().getName()));
			this.propietarioService.guardarPropietario(propietario);
			return "redirect:/propietarios";
		}
	}
}
