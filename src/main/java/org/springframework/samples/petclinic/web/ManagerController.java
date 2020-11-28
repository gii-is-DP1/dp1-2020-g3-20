package org.springframework.samples.petclinic.web;

import java.util.Optional;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.samples.petclinic.model.Manager;
import org.springframework.samples.petclinic.service.ManagerService;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;


@Controller
@RequestMapping(value = "/managers")
public class ManagerController {
	
	@Autowired
	private ManagerService managerService;
	
	@GetMapping()
	public String listadoManagers(ModelMap modelMap) {
		String vista = "managers/listaManagers";
		Iterable<Manager> managers = managerService.managerList();
		modelMap.addAttribute("managers", managers);
		return vista;
	}
	
	@GetMapping(path="/new")
	public String crearManager(ModelMap modelMap) {
		String vista= "managers/editManager";
		modelMap.addAttribute("manager",new Manager());
		return vista;
	}
	
	@PostMapping(path="/save")
	public String guardarManager(@Valid Manager manager, BindingResult result, ModelMap modelMap) {
		String vista= "managers/listaManager";
		if(result.hasErrors()) {
			modelMap.addAttribute("manager", manager);
			return "managers/editManager";
		}else {
			managerService.guardarManager(manager);
			modelMap.addAttribute("message", "successfuly saved");
			vista=listadoManagers(modelMap);
		}
		return vista;
	}
	
	@GetMapping(path="/delete/{managerId}")
	public String borrarManager(@PathVariable("managerId") int managerId, ModelMap modelMap) {
		String vista= "managers/listaManagers";
		Optional<Manager> man= managerService.buscaManagerPorId(managerId);
		if(man.isPresent()) {
			managerService.borrarManager(managerId);
			modelMap.addAttribute("message", "successfuly deleted");
			vista=listadoManagers(modelMap);
		}else {
			modelMap.addAttribute("message", "not found");
			vista=listadoManagers(modelMap);
		}
		return vista;	
	}

}