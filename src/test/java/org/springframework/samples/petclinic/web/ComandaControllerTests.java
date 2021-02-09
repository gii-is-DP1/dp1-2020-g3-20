package org.springframework.samples.petclinic.web;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.samples.petclinic.configuration.SecurityConfiguration;
import org.springframework.samples.petclinic.model.Camarero;
import org.springframework.samples.petclinic.model.Comanda;
import org.springframework.samples.petclinic.model.Ingrediente;
import org.springframework.samples.petclinic.model.IngredientePedido;
import org.springframework.samples.petclinic.model.Plato;
import org.springframework.samples.petclinic.model.PlatoPedido;
import org.springframework.samples.petclinic.model.Producto;
import org.springframework.samples.petclinic.model.Proveedor;
import org.springframework.samples.petclinic.model.TipoProducto;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.web.servlet.MockMvc;

import static org.hamcrest.Matchers.is;
import static org.mockito.BDDMockito.given;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.FilterType;
import org.springframework.samples.petclinic.service.CamareroService;
import org.springframework.samples.petclinic.service.ComandaService;
import org.springframework.samples.petclinic.service.PlatoPedidoService;
import org.springframework.samples.petclinic.service.PlatoService;
import org.springframework.security.config.annotation.web.WebSecurityConfigurer;
import org.springframework.security.test.context.support.WithMockUser;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

import java.time.LocalDateTime;

@WebMvcTest(controllers=ComandaController.class,
		excludeFilters = @ComponentScan.Filter(type = FilterType.ASSIGNABLE_TYPE, classes = WebSecurityConfigurer.class),
		excludeAutoConfiguration= SecurityConfiguration.class)
public class ComandaControllerTests {
	
	private static final int TEST_COMANDA_ID = 1;
	private static final int TEST_PLATOPEDIDO_ID = 1;
	private static final int TEST_PLATOPEDIDOVACIO_ID = 1;
	private static final String COMANDA_DETAILS = "comanda/comandaDetails";
	private static final String PLATOPEDIDO_EDIT = "/platopedido/comanda/{comandaId}/{ppId}";
	
	@Autowired
	private MockMvc mockMvc;
	
	@Autowired
	private ComandaController comandaController;
	
	@MockBean
	private ComandaService comandaService;

	@MockBean
	private PlatoPedidoService platoPedidoService;
	
	@MockBean
	private CamareroService camareroService;
	
	@MockBean
	private PlatoService platoService;


	private Comanda comanda;
	private Camarero camarero;
	private IngredientePedido ingredientePedido;
	private PlatoPedido platoPedido;
	private Ingrediente ingrediente;
	private PlatoPedido platoPedidoVacio;	
	private Plato plato;
	private Producto producto;
	private TipoProducto tipoProducto;
	private Proveedor proveedor;


	@BeforeEach
	void test() {
		proveedor = new Proveedor();
		proveedor.setId(30);
		proveedor.setName("Acme");
		proveedor.setGmail("acme@gmail.com");
		proveedor.setTelefono("631985162");
		proveedor.setActivo(true);
		
		tipoProducto = new TipoProducto();
		tipoProducto.setId(30);
		tipoProducto.setName("Pasta");
		
		producto = new Producto();
		producto.setId(30);
		producto.setName("Fideos");
		producto.setTipoProducto(tipoProducto);
		producto.setCantMin(1.0);
		producto.setCantAct(1.5);
		producto.setCantMax(2.0);
		producto.setProveedor(proveedor);
		
		plato = new Plato();
		plato.setId(30);
		plato.setName("Sopa");
		plato.setDisponible(true);
		plato.setPrecio(5.0);
		plato.setVersion(0);
		
		ingrediente = new Ingrediente();
		ingrediente.setId(30);
		ingrediente.setCantidadUsualPP(1.0);
		ingrediente.setProducto(producto);
		ingrediente.setPlato(plato);
		
		camarero = new Camarero();
		camarero.setId(30);
		camarero.setName("Maria");
		camarero.setApellido("Zamudio");
		camarero.setGmail("marzam@gmail.com");
		camarero.setTelefono("638246990");
		camarero.setUsuario("trece");
		camarero.setContrasena("12345");
		
		comanda = new Comanda();
		comanda.setId(TEST_COMANDA_ID);
		comanda.setMesa(30);
		comanda.setFechaCreado(LocalDateTime.now());
		comanda.setFechaFinalizado(null);
		comanda.setPrecioTotal(5.0);
		
		platoPedido = new PlatoPedido();
		platoPedido.setId(TEST_PLATOPEDIDO_ID);
		platoPedido.setPlato(plato);
		
		platoPedidoVacio = new PlatoPedido();
		platoPedidoVacio.setId(TEST_PLATOPEDIDOVACIO_ID);
		platoPedidoVacio.setPlato(plato);
		
		ingredientePedido = new IngredientePedido();
		ingredientePedido.setId(30);
		ingredientePedido.setCantidadPedida(5.0);
		ingredientePedido.setIngrediente(ingrediente);
		ingredientePedido.setPp(platoPedido);
		
		given(this.platoPedidoService.findById(TEST_PLATOPEDIDO_ID).get()).willReturn(platoPedido);
//		given(this.proveedorService.findPedidoByProveedorId(7).iterator().next()).willReturn(pedido);
//		given(this.proveedorService.findProveedorbyName("jorge")).willReturn(proveedor);
//		given(this.proveedorService.findPedidoByProveedorId(7).iterator().next()).willReturn(prueba);

	}
	
	//Test Crear Pedido (NEW)
	
	
	//TESTS RELACIONADOS CON LA H19
	//Escenario Positivo 1
	@WithMockUser(value = "spring")
    @Test
    void testAsignarComanda() throws Exception {
		mockMvc.perform(get("listaComandaActual/asignar/{comandaId}/{ppId}",TEST_COMANDA_ID,TEST_PLATOPEDIDO_ID))
				.andExpect(status().isOk())
				.andExpect(view().name(COMANDA_DETAILS));
	}	
	
	//Escenario Negativo 1
	@WithMockUser(value = "spring")
    @Test
    void testAsignarComandaFallida() throws Exception {
		mockMvc.perform(get("/listaComandaActual/asignar/{comandaId}/{ppId}",TEST_COMANDA_ID,TEST_PLATOPEDIDOVACIO_ID))
				.andExpect(model().attribute("message", is("Ha habido un error al guardar, No se han seleccionado ingredientes")))
				.andExpect(view().name(PLATOPEDIDO_EDIT));
	}	
	
	
	

}
