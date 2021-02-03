package org.springframework.samples.petclinic.web;

import java.text.ParseException;
import java.util.Collection;
import java.util.Locale;
import java.util.Optional;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.samples.petclinic.model.Ingrediente;
import org.springframework.samples.petclinic.model.Producto;
import org.springframework.samples.petclinic.service.IngredienteService;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping(value = "/ingrediente")
public class IngredienteController {
	
	@Autowired
	private IngredienteService ingService;
	
	@Autowired
	private ProductoFormatter productoFormatter;
	

	@GetMapping()
	public String listadoIngrediente(ModelMap modelMap) {
		String vista= "ingrediente/listaIngredientes";
		Iterable<Ingrediente> inglist = ingService.ingList();
		modelMap.addAttribute("ingrediente",inglist);
		return vista;	
	}
	
	@GetMapping(path="/new")
	public String crearIngrediente(ModelMap modelMap) {
		String vista= "ingrediente/newIngrediente";
		Collection<Producto> listaProd= this.ingService.encontrarProductos();
		modelMap.addAttribute("ingrediente",new Ingrediente());
		modelMap.addAttribute("listaProductos", listaProd);
		return vista;
	}
	
	@PostMapping(path="/save")
	public String guardarIngrediente(@Valid Ingrediente ing,BindingResult result,ModelMap modelMap) throws ParseException {
		String vista= "ingrediente/listaIngredientes";
		final Ingrediente ingFinal = ing;
		ingFinal.setProducto(productoFormatter.parse(ing.getProducto().getName(), Locale.ENGLISH));
		if(result.hasErrors()) {
			return "platosPedido/newPlatosPedido";
		}else {
			ingService.guardarIngrediente(ingFinal);
			modelMap.addAttribute("message", "Guardado Correctamente");
			vista=listadoIngrediente(modelMap);
		}
		return vista; 
	}

	@GetMapping(path="/delete/{ingId}")
	public String borrarIngrediente(@PathVariable("ingId") int ingId, ModelMap modelMap) {
		String vista= "ingrediente/listaIngredientes";
		Optional<Ingrediente> pp= ingService.buscaIngPorId(ingId);
		if(pp.isPresent()) {
			ingService.borrarIngrediente(ingId);
			modelMap.addAttribute("message", "Borrado Correctamente");
			vista=listadoIngrediente(modelMap);
		}else {
			modelMap.addAttribute("message", "Ingrediente no encontrado");
			vista=listadoIngrediente(modelMap);
		}
		return vista;
	}
//	
//	@GetMapping(value = "/edit/{ingId}")
//	public String initUpdatePPForm(@PathVariable("ingId") int ingId, ModelMap model) {		
//		String vista= "ingrediente/editarIngrediente";			
//		Collection<Producto> listaProd= this.ingService.encontrarProductos();
//		model.addAttribute("listaProd", listaProd);
//		Ingrediente ing=  ingService.buscaIngPorId(ingId).get();
//		PlatoPedidoDTO platoConvertido = ppConverter.convertEntityToPPDTO(pp);
//		platoConvertido.setEstadoplatodto(pp.getEstadoplato().getName());
//		model.addAttribute("platopedido", platoConvertido);
//		return vista;
//	}
//	

}
