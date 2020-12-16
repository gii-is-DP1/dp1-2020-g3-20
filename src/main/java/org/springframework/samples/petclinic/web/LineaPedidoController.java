package org.springframework.samples.petclinic.web;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.samples.petclinic.model.LineaPedido;
import org.springframework.samples.petclinic.service.ProductoService;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping("/lineaPedido")
public class LineaPedidoController {
	
	@Autowired
	private ProductoService productoService;
	
	@GetMapping()
	public String listadoDeLineaPedido(ModelMap modelMap) {
		String vista="lineaPedido/listaLineaPedido";
		Iterable<LineaPedido> lineaPedido= productoService.findAllLineaPedido();
		modelMap.addAttribute("lineaPedido", lineaPedido);
		return vista;
	}
	
	
	@GetMapping(path="/new")
	public String crearLineaPedido(ModelMap modelMap) {
		String view= "lineaPedido/editLineaPedido";
		modelMap.addAttribute("lineaPedido",new LineaPedido());
		return view;
	}
	
	
	@PostMapping(path="/save")
	public String guardarLineaPedido(@Valid LineaPedido lineaPedido,BindingResult result,ModelMap modelMap) {
		String view= "lineaPedido/listaLineaPedido";
		if(result.hasErrors()) {
			modelMap.addAttribute("lineaPedido", lineaPedido);
			return "lineaPedido/editLineaPedido";
		}else {
			productoService.saveLineaPedido(lineaPedido);
			modelMap.addAttribute("message", "lineaPedido successfuly saved");
			view=listadoDeLineaPedido(modelMap);
		}
		return view	;
	}
}

