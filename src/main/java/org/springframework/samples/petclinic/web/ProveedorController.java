package org.springframework.samples.petclinic.web;

import java.util.Optional;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.samples.petclinic.model.Proveedor;
import org.springframework.samples.petclinic.service.ProveedorService;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping("/proveedor")
public class ProveedorController {
	
	@Autowired
	private ProveedorService proveedorService;
	@GetMapping()
	public String listadoDeProveedores(ModelMap modelMap) {
		String vista="proveedor/listadoDeProveedores";
		Iterable<Proveedor> proveedor=proveedorService.findAll();
		modelMap.addAttribute("proveedor", proveedor);
		return vista;
	}
	@GetMapping(path="/new")
	public String crearProveedor(ModelMap modelMap) {
		String view= "proveedor/editProveedor";
		modelMap.addAttribute("proveedor",new Proveedor());
		return view;
	}
	@PostMapping(path="/save")
	public String guardarProveedor(@Valid Proveedor proveedor,BindingResult result,ModelMap modelMap) {
		String view= "proveedor/listadoDeProveedores";
		if(result.hasErrors()) {
			modelMap.addAttribute("proveedor", proveedor);
			return "proveedor/editProveedor";
		}else {
			proveedorService.save(proveedor);
			modelMap.addAttribute("message", "proveedor successfuly saved");
			view=listadoDeProveedores(modelMap);
		}
		return view	;
		
	}
	@GetMapping(path="/delete/{proveedorid}")
	public String borrarProveedor(@PathVariable("proveedorid") int proveedorid, ModelMap modelMap) {
		String view= "proveedor/listadoDeProveedores";
		Optional<Proveedor> proveedor= proveedorService.provedroporid(proveedorid);
		if(proveedor.isPresent()) {
			proveedorService.delete(proveedor.get());
			modelMap.addAttribute("message", "proveedor successfuly deleted");
			view=listadoDeProveedores(modelMap);
		}else {
			modelMap.addAttribute("message", " proveedor not found");
			view=listadoDeProveedores(modelMap);
		}
		return view;
		
	}
	@GetMapping(value = "/edit/{proveedorId}")
	public String initUpdateProveedorForm(@PathVariable("proveedorId") int proveedorId, ModelMap model) {
		String vista= "proveedor/editarProveedor";
		String username = SecurityContextHolder.getContext().getAuthentication().getName();
		
		Proveedor proveedor =  proveedorService.provedroporid(proveedorId).get();
			model.addAttribute(proveedor);
			return vista;
	}
	@PostMapping(value = "/edit")
	public String processUpdateProveedorForm(@Valid Proveedor proveedor, BindingResult result,ModelMap modelMap) {
		
		String vista= "proveedor/editarProveedor";
		
		if(result.hasErrors()) {
			modelMap.addAttribute("proveedor", proveedor);
		
			return vista;
		}
		else {
		this.proveedorService.save(proveedor);
			return "redirect:/proveedor";
		
	}
	
}
	}
