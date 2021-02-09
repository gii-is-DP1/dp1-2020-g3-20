package org.springframework.samples.petclinic.web;

import static org.mockito.BDDMockito.given;
import static org.mockito.Mockito.when;
import static org.springframework.security.test.web.servlet.request.SecurityMockMvcRequestPostProcessors.csrf;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.model;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.view;

import static org.hamcrest.Matchers.is;

import java.text.ParseException;
import java.util.ArrayList;
import java.util.Collection;
import java.util.Locale;
import java.util.Optional;

import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.junit.jupiter.MockitoSettings;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.FilterType;
import org.springframework.samples.petclinic.configuration.SecurityConfiguration;
import org.springframework.samples.petclinic.model.Comanda;
import org.springframework.samples.petclinic.model.EstadoPlato;
import org.springframework.samples.petclinic.model.Ingrediente;
import org.springframework.samples.petclinic.model.IngredientePedido;
import org.springframework.samples.petclinic.model.Plato;
import org.springframework.samples.petclinic.model.PlatoPedido;
import org.springframework.samples.petclinic.model.PlatoPedidoDTO;
import org.springframework.samples.petclinic.model.Producto;
import org.springframework.samples.petclinic.model.Proveedor;
import org.springframework.samples.petclinic.service.IngredientePedidoService;
import org.springframework.samples.petclinic.service.IngredienteService;
import org.springframework.samples.petclinic.service.PlatoPedidoService;
import org.springframework.security.config.annotation.web.WebSecurityConfigurer;
import org.springframework.security.test.context.support.WithMockUser;
import org.springframework.test.web.servlet.MockMvc;


@WebMvcTest(value = PlatoPedidoController.class, excludeFilters = @ComponentScan.Filter(type = FilterType.ASSIGNABLE_TYPE,
classes = WebSecurityConfigurer.class),
excludeAutoConfiguration = SecurityConfiguration.class)
public class PlatoPedidoControllerTest {

	
	private static final int TEST_PLATOPEDIDO_ID = 1;
	private static final int TEST_COMANDA_ID = 1;
	
	@Autowired
	private MockMvc mockMvc;

	@Autowired
	private PlatoPedidoController platoPedidoController;
	
	@MockBean
	private PlatoPedidoService platoPedidoService;
	
	@MockBean
	private PlatoPedidoConverter platoPedidoConverter;
	
	@MockBean
	private EstadoPlatoFormatter estadoEstadoPlatoFormatter;
	
	@MockBean
	private PlatoFormatter platoFormatter;
	
	@MockBean
	private IngredientePedidoService ingredientePedidoService;
	
	@MockBean
	private IngredienteService ingredienteService;
	
	private PlatoPedido platoPedido;
	private Plato plato;
	private Comanda comanda;
	private Producto producto1;
	private Ingrediente ingrediente1;
	private Ingrediente ingrediente2;
	
	private EstadoPlato estadoPlato1;
	private EstadoPlato estadoPlato2;
	private EstadoPlato estadoPlato3;
	
	@BeforeEach
	void setup() throws ParseException {
		
		estadoPlato1 = new EstadoPlato();
		estadoPlato1.setName("ENCOLA");
		estadoPlato1.setId(1);
		estadoPlato2 = new EstadoPlato();
		estadoPlato2.setName("ENPROCESO");
		estadoPlato2.setId(1);
		estadoPlato3 = new EstadoPlato();
		estadoPlato3.setName("FINALIZADO");
		estadoPlato3.setId(1);
		
		Collection<EstadoPlato> estadosPlato = new ArrayList<>();
		estadosPlato.add(estadoPlato1);
		estadosPlato.add(estadoPlato2);
		estadosPlato.add(estadoPlato3);
		
		producto1= new Producto();
		producto1.setCantAct(5.0);
		producto1.setCantMax(10.0);
		producto1.setCantMin(3.0);
		producto1.setName("alubias");
		producto1.setProveedor(new Proveedor());
		producto1.setId(1);
		
		plato = new Plato();
		plato.setId(1);
		plato.setDisponible(true);
		plato.setName("potaje");
		plato.setPrecio(2.0);
		
		ingrediente1 = new Ingrediente();
		ingrediente1.setId(1);
		ingrediente1.setProducto(producto1);
		ingrediente1.setPlato(plato);
		ingrediente1.setCantidadUsualPP(2.0);
		
		platoPedido = new PlatoPedido();
		platoPedido.setId(1);
		platoPedido.setPlato(plato);
//		platoPedido.setEstadoplato(estadoPlato1);
		
		Collection<IngredientePedido> platosPedidos = new ArrayList<>();

		given(this.platoPedidoService.findById(TEST_PLATOPEDIDO_ID)).willReturn(Optional.of(platoPedido));
		given(this.platoPedidoService.encontrarEstadoPlato()).willReturn(estadosPlato);
		given(this.platoPedidoService.CrearIngredientesPedidos(platoPedido)).willReturn(platosPedidos);
	}

	@WithMockUser(value = "spring")
	@Test
	void initUpdatePPForm() throws Exception {
		System.out.println(model().toString());
		mockMvc.perform(get("/platopedido/comanda/{comandaId}/{ppId}", TEST_COMANDA_ID, TEST_PLATOPEDIDO_ID))
		.andExpect(status().isOk())
				.andExpect(model().attributeExists("ingredientespedido"))
				.andExpect(view().name("platosPedido/modificarIngredientes"));
	}
	
	@WithMockUser(value = "spring")
	@Test
	void CambioEstadoEnProcesoPositivo() throws Exception {
		
		mockMvc.perform(get("/platopedido/modificarEstado/{platopedidoID}/{cambiarA}",TEST_PLATOPEDIDO_ID,"ENPROCESO"))
			.andExpect(model().attribute("message", is("Se ha cambiado el plato con exito")));
				
	}
	
	@WithMockUser(value = "spring")
	@Test
	void CambioEstadoEnProcesoNegativo() throws Exception {
		when(this.platoPedidoService.findById(TEST_PLATOPEDIDO_ID)).thenReturn(Optional.of(new PlatoPedido()).empty());
		mockMvc.perform(get("/platopedido/modificarEstado/{platopedidoID}/{cambiarA}",TEST_PLATOPEDIDO_ID,"ENPROCESO"))
			.andExpect(model().attribute("message", is("NO Se ha cambiado el plato con exito")));
				
	}

}
