package org.springframework.samples.petclinic.web;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.samples.petclinic.model.Pedido;
import org.springframework.samples.petclinic.model.Pet;
import org.springframework.samples.petclinic.model.Proveedor;
import org.springframework.samples.petclinic.model.Visit;
import org.springframework.samples.petclinic.service.ProveedorService;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping("/pedidos")
public class PedidoController {
	
	@Autowired
	private ProveedorService proveedorService;
	
	@GetMapping()
	public String listadoDePedidos(ModelMap modelMap) {
		String vista="pedidos/listaPedidos";
		Iterable<Pedido> pedido= proveedorService.findAllPedido();
		modelMap.addAttribute("pedido", pedido);
		return vista;
	}
	
	@GetMapping(path="/porProveedor")
	public String listadoDePedidosPorProveedor(Integer proveedorID, ModelMap modelMap) {
		String vista="pedidos/listaPedidos";
		Iterable<Pedido> pedido= proveedorService.findPedidoByProveedorId(proveedorID);
		modelMap.addAttribute("pedido", pedido);
		return vista;
	}
	
	
	@GetMapping(path="/new")
	public String crearPedido(ModelMap modelMap) {
		String view= "pedidos/editPedido";
		modelMap.addAttribute("pedido",new Pedido());
		return view;
	}
	
	
	@PostMapping(path="/save")
	public String guardarPedido(@Valid Pedido pedido,BindingResult result,ModelMap modelMap) {
		String view= "pedidos/listaPedidos";
		if(result.hasErrors()) {
			modelMap.addAttribute("pedido", pedido);
			return "pedidos/editPedido";
		}else {
			proveedorService.savePedido(pedido);
			modelMap.addAttribute("message", "pedido successfuly saved");
			view=listadoDePedidos(modelMap);
		}
		return view	;
	}
}
