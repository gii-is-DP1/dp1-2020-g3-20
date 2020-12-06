package org.springframework.samples.petclinic.web;

import java.util.Optional;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.samples.petclinic.model.Producto;
import org.springframework.samples.petclinic.service.ProductoService;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping(value = "/producto")
public class ProductoController {
	@Autowired
	private ProductoService productoService;
	
	@GetMapping()
	public String listadoProducto(ModelMap modelMap) {
		String vista= "producto/listaProducto";
		Iterable<Producto> producto = productoService.productoList();
		modelMap.addAttribute("producto",producto);
		return vista;	
	}
	
		@GetMapping(path="/new")
		public String crearProducto(ModelMap modelMap) {
			String vista= "producto/editProducto";
			modelMap.addAttribute("producto",new Producto());
			return vista;
		}
		
		@PostMapping(path="/save")
		public String guardarProducto(@Valid Producto producto,BindingResult result,ModelMap modelMap) {
			String vista= "producto/listaProducto";
			if(result.hasErrors()) {
				modelMap.addAttribute("producto", producto);
				return "producto/editProducto";
			}else {
				productoService.guardarProducto(producto);
				modelMap.addAttribute("message", "successfuly saved");
				vista=listadoProducto(modelMap);
			}
			return vista;
			
		}
		@GetMapping(path="/delete/{productoId}")
		public String borrarProducto(@PathVariable("productoId") int productoId, ModelMap modelMap) {
			String vista= "producto/listaProducto";
			Optional<Producto> prod= productoService.buscaProductoPorId(productoId);
			if(prod.isPresent()) {
				productoService.borrarProducto(productoId);
				modelMap.addAttribute("message", "successfuly deleted");
			}else {
				modelMap.addAttribute("message", "not found");
				vista=listadoProducto(modelMap);
			}
			return vista;
		}
}
