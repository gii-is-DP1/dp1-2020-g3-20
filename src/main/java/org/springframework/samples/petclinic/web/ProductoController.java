package org.springframework.samples.petclinic.web;

import java.text.ParseException;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.Collection;
import java.util.Iterator;
import java.util.List;
import java.util.Locale;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.samples.petclinic.model.LineaPedido;
import org.springframework.samples.petclinic.model.Pedido;
import org.springframework.samples.petclinic.model.Producto;
import org.springframework.samples.petclinic.model.ProductoDTO;
import org.springframework.samples.petclinic.model.TipoProducto;
import org.springframework.samples.petclinic.service.ProductoService;
import org.springframework.samples.petclinic.service.ProveedorService;
import org.springframework.samples.petclinic.service.exceptions.DuplicatedPedidoException;
import org.springframework.samples.petclinic.service.exceptions.PedidoPendienteException;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

@Controller
@RequestMapping(value = "/producto")
public class ProductoController {
	@Autowired
	private ProductoService productoService;
	
	@Autowired 
	private ProveedorService proveedorService;
	
	@Autowired
	private ProductoConverter productoConverter;
	
	@Autowired
	private TipoProductoFormatter tipoProductoFormatter;
	
	@Autowired
	private ProveedorFormatter proveedorFormatter;
	
	@ModelAttribute("tipoproducto") 				//Esto pertenece a TipoProducto
	public Collection<TipoProducto> poblarTiposProducto() {
		return this.productoService.encontrarTiposProducto();
	}
	
	@GetMapping()
	public String listadoProducto(ModelMap modelMap) {
		String vista= "producto/listaProducto";
		Iterable<Producto> producto = productoService.productoList();
		Iterator<Producto> it_producto = producto.iterator();
		
		if (!(it_producto.hasNext())) {
			modelMap.addAttribute("message", "No hay productos, los necesitas para poder cocinar, añade uno nuevo");
		}
		modelMap.addAttribute("producto",producto);
		return vista;	
	}
	
	@GetMapping(path="/notificaciones")
	public String productos_que_faltan(ModelMap modelMap) {
		String vista= "producto/notificaciones";
		Collection<Producto> lista = new ArrayList<Producto>();
		Iterable<Producto> producto = productoService.productoList();
		Iterator<Producto> iter = producto.iterator();
		while(iter.hasNext()) {
			Producto product = iter.next();
			if(product.getCantMin() > product.getCantAct()) {
				lista.add(product);
			}
		}
		Iterable<Producto> res = lista;
		modelMap.addAttribute("producto",res);
		return vista;	
	}
	
	@GetMapping(path="/new")
	public String crearProducto(ModelMap modelMap) {
		String vista= "producto/editProducto";
		Collection<TipoProducto> collectionTipoProducto = this.productoService.encontrarTiposProducto();
		List<String> collectionProveedor = this.proveedorService.findActivosName();
		modelMap.addAttribute("producto",new ProductoDTO());
		modelMap.addAttribute("listaProveedores", collectionProveedor);
		modelMap.addAttribute("listaTipos", collectionTipoProducto);
		return vista;
	}
		
	@PostMapping(path="/save")
	public String guardarProducto(ProductoDTO producto,BindingResult result,ModelMap modelMap) throws ParseException {
		String vista= "producto/listaProducto";
		final Producto productoFinal = productoConverter.convertProductoDTOToEntity(producto);
		productoFinal.setTipoProducto(tipoProductoFormatter.parse(producto.getTipoproductodto(), Locale.ENGLISH));
		productoFinal.setProveedor(proveedorFormatter.parse(producto.getProveedor(), Locale.ENGLISH));
			
		if(result.hasErrors()) {
			modelMap.addAttribute("producto", producto);
			return "producto/editProducto";
		}else {
			productoService.guardarProducto(productoFinal);
			modelMap.addAttribute("message", "Guardado Correctamente");
			vista=listadoProducto(modelMap);
		}
		return vista; 
	}
		
	@GetMapping(path="/delete/{productoId}")
	public String borrarProducto(@PathVariable("productoId") int productoId, ModelMap modelMap)  {
		String vista= "producto/listaProducto";
		Optional<Producto> prod= productoService.buscaProductoPorId(productoId);
		if(prod.isPresent()) {
			try {
				productoService.borrarProducto(productoId);
				modelMap.addAttribute("message", "Borrado Correctamente");
				vista=listadoProducto(modelMap);
			}catch (PedidoPendienteException ex) {
				modelMap.addAttribute("message", "No se puede borrar porque hay un pedido pendiente con ese producto");
				vista=listadoProducto(modelMap);
			}
		}
		else {
			modelMap.addAttribute("message", "Producto no encontrado");
			vista=listadoProducto(modelMap);
		}
		return vista;
	}

	@GetMapping(value = "/edit/{productoId}")
	public String initUpdateProductoForm(@PathVariable("productoId") int productoId, ModelMap model) {		
		String vista= "producto/editarProducto";	
		Collection<TipoProducto> collectionTipoProducto = this.productoService.encontrarTiposProducto();
		Producto producto =  productoService.buscaProductoPorId(productoId).get();
		ProductoDTO productoConvertido = productoConverter.convertEntityToProductoDTO(producto);
		Collection<String> collectionProveedor = this.proveedorService.findAllNames();
		productoConvertido.setTipoproductodto(producto.getTipoProducto().getName());
		model.addAttribute("listaTipos", collectionTipoProducto);
		model.addAttribute("listaProveedores", collectionProveedor);
		model.addAttribute("producto", productoConvertido);
		return vista;
		}
	
	@PostMapping(value = "/edit")
	public String processUpdateProductoForm(ProductoDTO producto, BindingResult result,ModelMap modelMap, @RequestParam(value="version", required=false) Integer version) throws ParseException {
		final Producto productoFinal = productoConverter.convertProductoDTOToEntity(producto);
		productoFinal.setTipoProducto(tipoProductoFormatter.parse(producto.getTipoproductodto(), Locale.ENGLISH));
		productoFinal.setProveedor(proveedorFormatter.parse(producto.getProveedor(), Locale.ENGLISH));
		if(result.hasErrors()) {
			modelMap.addAttribute("producto", producto);
			return "producto/editarProducto";
		}else if(productoFinal.getVersion()!=productoService.buscaProductoPorId(productoFinal.getId()).get().getVersion()){
			modelMap.addAttribute("message", "El producto que intentas editar ya se estaba editando, intenta de nuevo por favor");
			return listadoProducto(modelMap);
		}else {
			productoFinal.setVersion(productoFinal.getVersion()+1);
			this.productoService.guardarProducto(productoFinal);
			modelMap.addAttribute("message", "Guardado Correctamente");
			return "redirect:/producto";
		}
	}		
		

	@GetMapping(path="/savePedido/{productoId}")
	public String recargarStock(@PathVariable("productoId") int productoId, ModelMap modelMap) {
		String vista= "producto/listaProducto";
		Optional<Producto> prodOpt= productoService.buscaProductoPorId(productoId);
		if(prodOpt.isPresent()) {
			try {
			Producto producto = prodOpt.get();
			Collection<Producto> listaProducto = proveedorService.encontrarProductoProveedor(producto);
			Pedido pedido = new Pedido();
			pedido.setProveedor(producto.getProveedor());
			pedido.setFechaPedido(LocalDate.now());
			pedido.setHaLlegado(Boolean.FALSE);
			proveedorService.savePedido(pedido);
			LineaPedido lineaPedido = new LineaPedido();
			for(Producto p : listaProducto) {
				lineaPedido = proveedorService.anadirLineaPedido(p, pedido);
				proveedorService.saveLineaPedido(lineaPedido);
			}
			modelMap.addAttribute("message", "Se ha creado el pedido correctamente");
			vista = listadoProducto(modelMap);
			}catch(DuplicatedPedidoException ex){
				modelMap.addAttribute("message", "Ya hay un pedido pendiente a ese proveedor ");
				vista=listadoProducto(modelMap);
			}
		}else {
			modelMap.addAttribute("message", "Producto no encontrado");
			vista=listadoProducto(modelMap);
		}
		return vista;
	}
}