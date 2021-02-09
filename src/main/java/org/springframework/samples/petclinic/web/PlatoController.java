package org.springframework.samples.petclinic.web;

import java.text.ParseException;

import java.util.Collection;
import java.util.List;
import java.util.Optional;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.samples.petclinic.model.Ingrediente;
import org.springframework.samples.petclinic.model.Plato;
import org.springframework.samples.petclinic.model.Producto;
import org.springframework.samples.petclinic.service.IngredienteService;
import org.springframework.samples.petclinic.service.PlatoService;

import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.WebDataBinder;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.InitBinder;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import lombok.extern.slf4j.Slf4j;
@Slf4j
@Controller
@RequestMapping(value = "/platos")
public class PlatoController {

	@Autowired
	private PlatoService platoService;
	
	@Autowired
	private IngredienteService ingService;
	
	
	@InitBinder
	public void setAllowedFields(WebDataBinder dataBinder) {
		dataBinder.setDisallowedFields("id");
	}
	
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
			log.info(String.format("Plate with name %s wasn't able to be created", plato.getName()));
      modelMap.addAttribute("message", "el plato que estas intentanco crear es erroneo");
		  modelMap.addAttribute("platos", plato);
			return "platos/editPlatos";
		}else {
			platoService.guardarPlato(plato);
			modelMap.addAttribute("message", "Guardado Correctamente");
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
			modelMap.addAttribute("message", "Borrado Correctamente");
			vista=listadoPlatos(modelMap);
		}else {
			modelMap.addAttribute("message", "Plato no encontrado");
			vista=listadoPlatos(modelMap);
		}
		return vista;
	}
	

	@GetMapping(value = "{platoId}/edit")
	public String initUpdatePlatoForm(@PathVariable("platoId") int platoId, ModelMap model) {
		String vista= "platos/editarPlatos";
		
			Plato plato =  platoService.buscaPlatoPorId(platoId).get();
			model.addAttribute(plato);
			return vista;
	}
	@PostMapping(value = "/edit/{platoId}")

	public String processUpdatePlatoForm(@Valid Plato plato,BindingResult result,@PathVariable("platoId") int platoId,ModelMap modelMap) {
		String vista= "platos/editarPlatos";
		plato.setId(platoId);
		if(result.hasErrors()) {
			modelMap.addAttribute("plato", plato);
			return vista;
		}else {
			this.platoService.guardarPlato(plato);
			return "redirect:/platos";
		}	
	}
	
	@GetMapping("/{platoId}")
	public String showPlato(@PathVariable("platoId") int platoId, ModelMap model) {
		Plato plato= platoService.buscaPlatoPorId(platoId).get();
		model.addAttribute("plato", plato);
		List<Ingrediente> ls= platoService.ingredientePorPlato(platoId);
		model.addAttribute("ingredientes", ls);
		return "platos/platosDetails";
		
	}

	@GetMapping(path="/{platoId}/ingrediente/new")
	public String crearIngrediente(ModelMap modelMap, @PathVariable("platoId") int platoId) {
		String vista= "platos/newIngredientes";
		Collection<Producto> listaProd= ingService.encontrarProductos();
		Plato plato=  platoService.buscaPlatoPorId(platoId).get();
		Ingrediente ing=new Ingrediente();
		
		
		modelMap.addAttribute("plato", plato);
		modelMap.addAttribute("ingrediente",ing);
		modelMap.addAttribute("listaProductos", listaProd);
		return vista;
	}

	
	@PostMapping(path="/ingSave/{platoId}")
	public String processCreationForm(@Valid Ingrediente ingrediente,BindingResult result,@PathVariable("platoId") int platoId, ModelMap model) throws ParseException {	
		Plato pl= this.platoService.buscaPlatoPorId(platoId).get();
		ingrediente.setPlato(pl);
		if (result.hasErrors()) {
			model.put("ingrediente", ingrediente);
			return "platos/newIngredientes";
		}else {
            if(this.platoService.ingEstaRepetido(ingrediente.getProducto().getName(), platoId)) {
            	model.put("message", "el ingrediente esta repetido");
            	return "redirect:/platos/"+platoId;
            }else {
//              res.setPlato(platoFormatter.parse(ingrediente.getPlatoaux(),Locale.ENGLISH));
    			this.ingService.guardarIngrediente(ingrediente);                
    			return "redirect:/platos/"+platoId;
            }
			
		}
	}
	
	@GetMapping(path="/deleteIng/{ingId}")
	public String borrarIngredienteDePlato(@PathVariable("ingId") int ingId, ModelMap modelMap) {
		Optional<Ingrediente> ing= ingService.buscaIngPorId(ingId);
		Integer platoId= ing.get().getPlato().getId();
		if(ing.isPresent()) {
			ingService.borrarIngrediente(ingId);
			modelMap.addAttribute("message", "Borrado Correctamente");
		}else {
			modelMap.addAttribute("message", "Ingrediente no encontrado");
		}
		return "redirect:/platos/"+platoId;
	}
	
	
//	@PostMapping(path="/ingSave")
//	public String guardarIngrediente(@Valid Ingrediente ing,@Param("platoId") int platoId,BindingResult result,ModelMap modelMap) throws ParseException {
//	
//		String vista= "platos/platosDetails";
//		final Ingrediente ingFinal = ing;
//		
//		ingFinal.setProducto(productoFormatter.parse(ing.getProducto().getName(), Locale.ENGLISH));
//		if(result.hasErrors()) {
//			return "platos/newIngredientes";
//		}else {
//			ingService.guardarIngrediente(ingFinal);
//			modelMap.addAttribute("message", "successfuly saved");
//			vista=showPlato(ing.getPlato().getId(),modelMap);
//		}
//		return vista; 
//	}


	
}
