package org.springframework.samples.petclinic.web;

import java.text.ParseException;

import java.util.Collection;
import java.util.List;
import java.util.Locale;
import java.util.Optional;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.repository.query.Param;
import org.springframework.samples.petclinic.model.Ingrediente;
import org.springframework.samples.petclinic.model.IngredienteAUX;
import org.springframework.samples.petclinic.model.Owner;
import org.springframework.samples.petclinic.model.Pet;
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
import org.springframework.web.servlet.ModelAndView;

@Controller
@RequestMapping(value = "/platos")
public class PlatoController {
	
	@Autowired
	private PlatoFormatter platoFormatter;

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
			modelMap.addAttribute("plato", plato);
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
	@PostMapping(value = "/edit")
	public String processUpdatePlatoForm(@Valid Plato plato,BindingResult result,ModelMap modelMap) {
		String vista= "platos/editarPlatos";
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
		IngredienteAUX ing=new IngredienteAUX();
		
		
		modelMap.addAttribute("plato", plato);
		modelMap.addAttribute("ingredienteaux",ing);
		modelMap.addAttribute("listaProductos", listaProd);
		return vista;
	}
	
	public Ingrediente ConversorAUXToIngrediente(IngredienteAUX aux) {
		Integer id=aux.getId();
		Double cantidad=aux.getCantidadUsualPP();
		Producto prod=aux.getProducto();
		Ingrediente res= new Ingrediente();
		res.setId(id);
		res.setCantidadUsualPP(cantidad);
		
		res.setProducto(prod);
		return res;
	}
	
	@PostMapping(path="/ingSave")
	public String processCreationForm(@Valid IngredienteAUX ingrediente,BindingResult result, ModelMap model) throws ParseException {	
		
		if (result.hasErrors()) {
			model.put("ingrediente", ingrediente);
			return "platos/newIngredientes";
		}
		else {
               Ingrediente res= ConversorAUXToIngrediente(ingrediente);
               res.setPlato(platoFormatter.parse(ingrediente.getPlatoaux(),Locale.ENGLISH));
               this.ingService.guardarIngrediente(res);
                    
         return showPlato(res.getPlato().getId(),model);
		}
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
