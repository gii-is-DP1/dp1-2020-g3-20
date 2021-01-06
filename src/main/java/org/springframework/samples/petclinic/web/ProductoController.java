package org.springframework.samples.petclinic.web;

import java.text.ParseException;
import java.util.Collection;
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
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

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
	
	@ModelAttribute("tipoproducto") 				//Esto pertenece a TipoProducto
	public Collection<TipoProducto> poblarTiposProducto() {
		return this.productoService.encontrarTiposProducto();
	}
	
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
		Collection<TipoProducto> collectionTipoProducto = this.productoService.encontrarTiposProducto();
		Collection<String> collectionProveedor = this.proveedorService.findAllNames();
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
			
		if(result.hasErrors()) {
			modelMap.addAttribute("producto", producto);
			return "producto/editProducto";
		}else {
			productoService.guardarProducto(productoFinal);
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
	
	@GetMapping(value = "/edit/{productoId}")
	public String initUpdateProductoForm(@PathVariable("productoId") int productoId, ModelMap model) {		
		String vista= "producto/editarProducto";	
		
		Collection<TipoProducto> collectionTipoProducto = this.productoService.encontrarTiposProducto();
		model.addAttribute("listaTipos", collectionTipoProducto);
		Producto producto =  productoService.buscaProductoPorId(productoId).get();
		ProductoDTO productoConvertido = productoConverter.convertEntityToProductoDTO(producto);
		Collection<String> collectionProveedor = this.proveedorService.findAllNames();
		productoConvertido.setTipoproductodto(producto.getTipoProducto().getName());
		
		model.addAttribute("listaProveedores", collectionProveedor);
		model.addAttribute("producto", productoConvertido);
		return vista;
		}
	
	@PostMapping(value = "/edit")
	public String processUpdateProductoForm(ProductoDTO producto, BindingResult result,ModelMap modelMap) throws ParseException {
		final Producto productoFinal = productoConverter.convertProductoDTOToEntity(producto);
		productoFinal.setTipoProducto(tipoProductoFormatter.parse(producto.getTipoproductodto(), Locale.ENGLISH));
		if(result.hasErrors()) {
			modelMap.addAttribute("producto", producto);
			return "producto/editarProducto";
		}else {
			this.productoService.guardarProducto(productoFinal);
			modelMap.addAttribute("message", "successfuly saved");
			return "redirect:/producto";
		}
	}		
		
	@GetMapping(path="/savePedido/{productoId}")
	public String recargarStock(@PathVariable("productoId") int productoId, ModelMap modelMap) {
		String vista= "producto/listaProducto";
		Optional<Producto> prod= productoService.buscaProductoPorId(productoId);
		if(prod.isPresent()) {
			Producto p = prod.get();
			String proveedor = p.getProveedor();
			Pedido pedido = proveedorService.crearPedido(proveedor);
			LineaPedido lp = proveedorService.crearLineaPedido(p, pedido);
			proveedorService.savePedido(pedido);
			proveedorService.saveLineaPedido(lp);
			modelMap.addAttribute("message", "Se ha creado el pedido correctamente");
			vista = listadoProducto(modelMap);
		}else {
			modelMap.addAttribute("message", "not found");
			vista=listadoProducto(modelMap);
		}
		return vista;
	}
}