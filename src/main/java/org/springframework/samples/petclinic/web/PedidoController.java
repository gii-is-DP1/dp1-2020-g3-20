package org.springframework.samples.petclinic.web;

import java.time.LocalDate;
import java.util.Iterator;
import java.util.Optional;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.samples.petclinic.model.LineaPedido;
import org.springframework.samples.petclinic.model.Pedido;
import org.springframework.samples.petclinic.model.Producto;
import org.springframework.samples.petclinic.service.ProveedorService;
import org.springframework.samples.petclinic.service.exceptions.DuplicatedPedidoException;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
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
	
	
//	@PostMapping(path="/save")
//	public String guardarPedido(@Valid Pedido pedido,BindingResult result,ModelMap modelMap) {
//		String view= "pedidos/listaPedidos";
//		pedido.setFechaPedido(LocalDate.now());
//		pedido.setHaLlegado(Boolean.FALSE);
//		if(result.hasErrors()) {
//			modelMap.addAttribute("pedido", pedido);
//			return "pedidos/editPedido";
//		}else {
//			proveedorService.savePedido(pedido);
//			modelMap.addAttribute("message", "pedido successfuly saved");
//			view=listadoDePedidos(modelMap);
//		}
//		return view	;
//	}
	@PostMapping(path="/save")
	public String guardarPedido(@Valid Pedido pedido,BindingResult result,ModelMap modelMap) {
		String view= "pedidos/listaPedidos";
		pedido.setFechaPedido(LocalDate.now());
		pedido.setHaLlegado(Boolean.FALSE);
		if(result.hasErrors()) {
			modelMap.addAttribute("pedido", pedido);
			return "pedidos/editPedido";
		}else {
			 try {                    
				proveedorService.savePedido(pedido);
				modelMap.addAttribute("message", "Guardado Correctamente");
				view=listadoDePedidos(modelMap);              
             } catch (DuplicatedPedidoException ex) {
                 result.rejectValue("proveedor", "duplicate", "already exists");
                 view= "pedidos/listaPedidos";
             }
		}
		return view	;
	}
	

	@GetMapping(path="/terminarPedido/{pedidoID}")
	public String recargarStock(@PathVariable("pedidoID") int pedidoID, ModelMap modelMap) {
		String view= "pedidos/listaPedidos";
		Optional<Pedido> pedi = proveedorService.pedidoPorId(pedidoID);
		Iterable<LineaPedido> lineaPedi = proveedorService.findLineaPedidoByPedidoId(pedidoID);
		Iterator<LineaPedido> lp_it = lineaPedi.iterator();
		if(pedi.isPresent()) {
			Pedido p = pedi.get();
			if (p.getHaLlegado().equals(Boolean.FALSE)) {
				//Modificacion de producto
				while (lp_it.hasNext()) {
					LineaPedido lp = lp_it.next();
					Producto prod = lp.getProducto();
					prod.setCantAct(prod.getCantAct()+lp.getCantidad());
				}
		
				//Modificacion de pedido
				p.setHaLlegado(Boolean.TRUE);
				p.setFechaEntrega(LocalDate.now());
				modelMap.addAttribute("message", "Se ha finalizado el pedido correctamente");
				view = listadoDePedidos(modelMap);
			} else {
				modelMap.addAttribute("message", "El pedido ya se ha finalizado");
				view = listadoDePedidos(modelMap);
			}
			
		}else {
			modelMap.addAttribute("message", "not found");
			view = listadoDePedidos(modelMap);
		}
		return view;
	}
}
