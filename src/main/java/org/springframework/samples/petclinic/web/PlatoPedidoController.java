package org.springframework.samples.petclinic.web;

import java.text.ParseException;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.Collection;
import java.util.HashSet;
import java.util.Iterator;
import java.util.List;
import java.util.Locale;
import java.util.Optional;
import java.util.Set;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.samples.petclinic.model.EstadoPlato;
import org.springframework.samples.petclinic.model.Ingrediente;
import org.springframework.samples.petclinic.model.IngredientePedido;
import org.springframework.samples.petclinic.model.LineaPedido;
import org.springframework.samples.petclinic.model.Pedido;
import org.springframework.samples.petclinic.model.Plato;
import org.springframework.samples.petclinic.model.PlatoPedido;
import org.springframework.samples.petclinic.model.PlatoPedidoDTO;
import org.springframework.samples.petclinic.model.Producto;
import org.springframework.samples.petclinic.repository.PlatoPedidoRepository;
import org.springframework.samples.petclinic.service.IngredientePedidoService;
import org.springframework.samples.petclinic.service.IngredienteService;
import org.springframework.samples.petclinic.service.PlatoPedidoService;
import org.springframework.samples.petclinic.service.ProductoService;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping(value = "/platopedido")
public class PlatoPedidoController {
	
	//Perteneciente a plato pedido
	
	private PlatoPedidoService ppService;
	private IngredientePedidoService ingService;
	private IngredienteService ingredienteService; 
	
	@Autowired
	public void PlatoPedidoService(PlatoPedidoService ppService,IngredientePedidoService ingService,IngredienteService ingredienteService) {
		this.ppService = ppService;
		this.ingService = ingService;
		this.ingredienteService = ingredienteService;
	}
	
	@Autowired
	private PlatoPedidoConverter ppConverter;
	
	//formatters
	@Autowired
	private EstadoPlatoFormatter estadoPlatoFormatter;
	
	@Autowired
	private PlatoFormatter platoFormatter;
	
	@ModelAttribute("estadoplatopedido") 				//Esto pertenece a EstadoPlato
	public Collection<EstadoPlato> poblarEstadosPlato() {
		return this.ppService.encontrarEstadoPlato();
	}
	
	@GetMapping()
	public String listadoPlatosPedido(ModelMap modelMap) {
		String vista= "platosPedido/listaPlatosPedido";
		Iterable<PlatoPedido> pp = ppService.findAll();
		modelMap.addAttribute("platopedido",pp);
		return vista;	
	}
	
		@GetMapping(path="/new")
		public String crearPlatoPedido(ModelMap modelMap) {
			String vista= "platosPedido/newPlatosPedido";
			Collection<String> listaPlatos= this.ppService.encontrarPlatos();
			modelMap.addAttribute("platopedido",new PlatoPedidoDTO());
			modelMap.addAttribute("listaPlatos", listaPlatos);
			return vista;
		}
		
		@PostMapping(path="/save")
		public String guardarPP(@Valid PlatoPedidoDTO ppDTO,BindingResult result,ModelMap modelMap) throws ParseException {
			String vista= "platosPedido/listaPlatosPedido";
			final PlatoPedido ppFinal = ppConverter.convertPPDTOToEntity(ppDTO);
			ppFinal.setEstadoplato(estadoPlatoFormatter.parse(ppDTO.getEstadoplatodto(), Locale.ENGLISH));
			ppFinal.setPlato(platoFormatter.parse(ppDTO.getPlatodto(), Locale.ENGLISH));
			
			//Collection<IngredientePedido> lista = ppService.CrearIngredientesPedidos(ppFinal);
			//ppFinal.setIngredientesPedidos(lista);
			if(result.hasErrors()) {
				modelMap.addAttribute("message", "ha habido un error al guardar"+result.getAllErrors().toString());
				return "platosPedido/newPlatosPedido";
			}else {
				ppService.guardarPP(ppFinal);
				modelMap.addAttribute("message", "successfuly saved");
				vista=listadoPlatosPedido(modelMap);
			}
			return vista; 
		}
		
/*		@PostMapping(path="{comandaId}/saveUsual")
		public String guardarPPUsual(@Valid PlatoPedidoDTO ppDTO,@PathVariable("comandaId") int comandaId,BindingResult result,ModelMap modelMap) throws ParseException {
			String vista= "platosPedido/listaPlatosPedido";
			final PlatoPedido ppFinal = ppConverter.convertPPDTOToEntity(ppDTO);
			ppFinal.setEstadoplato(estadoPlatoFormatter.parse(ppDTO.getEstadoplatodto(), Locale.ENGLISH));
			//ppFinal.setPlato(platoFormatter.parse(ppDTO.getPlatodto(), Locale.ENGLISH));
			//Collection<IngredientePedido> lista = ppService.CrearIngredientesPedidos(ppFinal);
			//ppFinal.setIngredientesPedidos(lista);
			if(result.hasErrors()) {
				modelMap.addAttribute("message", "ha habido un error al guardar"+result.getAllErrors().toString());
				return "platosPedido/newPlatosPedido";
			}else {
				ppService.guardarPP(ppFinal);
				modelMap.addAttribute("message", "successfuly saved");
				vista=listadoPlatosPedido(modelMap);
			}
			return vista; 
		}*/
		
		@GetMapping(path="/delete/{ppId}")
		public String borrarPP(@PathVariable("ppId") int ppId, ModelMap modelMap) {
			String vista= "platosPedido/listaPlatosPedido";
			Optional<PlatoPedido> pp= ppService.buscaPPPorId(ppId);
			if(pp.isPresent()) {
				
				ppService.borrarPP(ppId);
				modelMap.addAttribute("message", "successfuly deleted");
				vista=listadoPlatosPedido(modelMap);
			}else {
				modelMap.addAttribute("message", "not found");
				vista=listadoPlatosPedido(modelMap);
			}
			return vista;
		}
		
		@GetMapping(value = "/edit/{ppId}")
		public String initUpdatePPForm(@PathVariable("ppId") int ppId, ModelMap model) {		
			String vista= "platosPedido/modificarIngredientes";
			Collection<EstadoPlato> collectionEstadosPlato= this.ppService.encontrarEstadoPlato();
			model.addAttribute("estadosPlato", collectionEstadosPlato);
			Collection<String> listaPlatos= this.ppService.encontrarPlatos();
			Collection<String> listaPlatos2 = new ArrayList<String>();
			PlatoPedido pp=  ppService.buscaPPPorId(ppId).get();
			listaPlatos2.add(pp.getPlato().getName());
			model.addAttribute("listaPlatos", listaPlatos2);
			PlatoPedidoDTO platoConvertido = ppConverter.convertEntityToPPDTO(pp);
			platoConvertido.setEstadoplatodto(pp.getEstadoplato().getName());
			model.addAttribute("platopedido", platoConvertido);
			
			// Asignación de ingredientespedidos a plato pedido
			Collection<IngredientePedido> ingredientes = new ArrayList<>();
			ingredientes = ppService.CrearIngredientesPedidos(pp);
			//model.addAttribute("ingredientesPedidos", ingredientes);
			
			model.addAttribute("ingredientespedido", ingredientes);
			return vista;
		}
		@PostMapping(value = "/edit")
		public String processUpdatePPForm(PlatoPedidoDTO ppDTO, BindingResult result,ModelMap modelMap) throws ParseException {
			final PlatoPedido ppFinal = ppConverter.convertPPDTOToEntity(ppDTO);
			ppFinal.setEstadoplato(estadoPlatoFormatter.parse(ppDTO.getEstadoplatodto(), Locale.ENGLISH));
			ppFinal.setPlato(platoFormatter.parse(ppDTO.getPlatodto(), Locale.ENGLISH));
			if(result.hasErrors()) {
				modelMap.addAttribute("platopedido", ppDTO);
				return "platosPedido/editarPlatosPedido";
			}
			else {
			this.ppService.guardarPP(ppFinal);
			modelMap.addAttribute("message", "successfuly saved");
			String vista=listadoPlatosPedido(modelMap);
			return vista;
				}
		}
		
		@PostMapping(value = "/guardarIngredientes")
		public String guardarIngredientes(Collection<IngredientePedido> lista, BindingResult result,ModelMap modelMap) throws ParseException {
			Iterator<IngredientePedido> iterator = lista.iterator();
			while(iterator.hasNext()) {
				IngredientePedido i = iterator.next();
			}
			if(result.hasErrors()) {
				//modelMap.addAttribute("platopedido", ppDTO);
				return "platosPedido/editarPlatosPedido";
			}
			else {
			
			modelMap.addAttribute("message", "successfuly saved");
			String vista=listadoPlatosPedido(modelMap);
			return vista;
				}
		}
		
		@PostMapping(value = "/guardarIngrediente/{ppId}/{ingId}")
		public String guardarIngrediente(@PathVariable("ppId") int ppId,
				@PathVariable("ingId") int ingId,
				@Valid IngredientePedido ingredientePedido,
				BindingResult result,
				ModelMap modelMap) throws ParseException {
			String vista="";
			if(result.hasErrors()) {
				//modelMap.addAttribute("platopedido", ppDTO);
				return "platosPedido/editarPlatosPedido";
			}
			else {
			System.out.println("hola que tal llego aqui"+ingredientePedido);
			ingredientePedido.setIngrediente(ingredienteService.buscaIngPorId(ingId).get());
			ingredientePedido.setPp(ppService.buscaPPPorId(ppId).get());
			ingService.guardarIngredientePedido(ingredientePedido);
			modelMap.addAttribute("message", "successfuly saved");
			vista=initUpdatePPForm(ppId,modelMap);
			
				}
			return vista;
		}
		
		//parte correspondiente a ingrediente pedido
		@GetMapping("/{ppId}")
		public String showIngredientePedido(@PathVariable("ppId") int ppId, ModelMap model) {
			PlatoPedido pp= ppService.buscaPPPorId(ppId).get();
			model.addAttribute("platopedido", pp);
			List<IngredientePedido> ls = ppService.ingredientePedidoPorPlatoPedido(ppId);
			model.addAttribute("ingredientespedido", ls);
			return "platosPedido/ingredientesDePlatoPedido";
			
		}
		
		//Modifica el estado del plato al siguiente
		
		@GetMapping(path="/modificarEstado/{platopedidoID}/{cambiarA}")
		public String Stock(@PathVariable("platopedidoID") Integer ppId,@PathVariable("cambiarA") String estado, ModelMap model) throws ParseException {
			
			Optional<PlatoPedido> pp = ppService.buscaPPPorId(ppId);
			if(pp.isPresent()) {
				PlatoPedido p = pp.get();
				p.setEstadoplato(estadoPlatoFormatter.parse(estado, Locale.ENGLISH));
				
				this.ppService.guardarPP(p);
				model.addAttribute("message", "Se ha cambiado el plato con exito");
				String vista=listadoPlatosPedido(model);
				return vista;
			}
			else {
				model.addAttribute("message", "NO Se ha cambiado el plato con exito");
				String vista=listadoPlatosPedido(model);
				return vista;
				}
		}
}