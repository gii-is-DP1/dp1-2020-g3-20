package org.springframework.samples.petclinic.web;

import java.text.ParseException;
import java.util.Collection;
import java.util.Locale;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.samples.petclinic.model.EstadoPlato;
import org.springframework.samples.petclinic.model.Plato;
import org.springframework.samples.petclinic.model.PlatoPedido;
import org.springframework.samples.petclinic.model.PlatoPedidoDTO;
import org.springframework.samples.petclinic.service.PlatoPedidoService;
import org.springframework.samples.petclinic.service.PlatoService;
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
	@Autowired
	private PlatoPedidoService ppService;
	
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
		Iterable<PlatoPedido> pp = ppService.ppList();
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
		public String guardarPP(PlatoPedidoDTO ppDTO,BindingResult result,ModelMap modelMap) throws ParseException {
			String vista= "platosPedido/listaPlatosPedido";
			final PlatoPedido ppFinal = ppConverter.convertPPDTOToEntity(ppDTO);
			ppFinal.setEstadoplato(estadoPlatoFormatter.parse(ppDTO.getEstadoplatodto(), Locale.ENGLISH));
			ppFinal.setPlato(platoFormatter.parse(ppDTO.getPlatodto(), Locale.ENGLISH));
			if(result.hasErrors()) {
				return "platosPedido/newPlatosPedido";
			}else {
				ppService.guardarPP(ppFinal);
				modelMap.addAttribute("message", "successfuly saved");
				vista=listadoPlatosPedido(modelMap);
			}
			return vista; 
		}
		
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
			String vista= "platosPedido/editarPlatosPedido";			
			Collection<EstadoPlato> collectionEstadosPlato= this.ppService.encontrarEstadoPlato();
			model.addAttribute("estadosPlato", collectionEstadosPlato);
			Collection<String> listaPlatos= this.ppService.encontrarPlatos();
			model.addAttribute("listaPlatos", listaPlatos);
			PlatoPedido pp=  ppService.buscaPPPorId(ppId).get();
			PlatoPedidoDTO platoConvertido = ppConverter.convertEntityToPPDTO(pp);
			platoConvertido.setEstadoplatodto(pp.getEstadoplato().getName());
			model.addAttribute("platopedido", platoConvertido);
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
}
