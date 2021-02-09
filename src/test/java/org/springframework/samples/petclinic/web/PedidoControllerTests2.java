package org.springframework.samples.petclinic.web;

import org.assertj.core.util.Lists;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.samples.petclinic.configuration.SecurityConfiguration;
import org.springframework.samples.petclinic.model.Owner;
import org.springframework.samples.petclinic.model.Pedido;
import org.springframework.samples.petclinic.model.Proveedor;
import org.springframework.samples.petclinic.service.VetService;
import org.springframework.test.context.junit.jupiter.web.SpringJUnitWebConfig;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;

import static org.hamcrest.Matchers.hasProperty;
import static org.hamcrest.Matchers.is;
import static org.mockito.BDDMockito.given;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.FilterType;
import org.springframework.samples.petclinic.service.AuthoritiesService;
import org.springframework.samples.petclinic.service.OwnerService;
import org.springframework.samples.petclinic.service.ProveedorService;
import org.springframework.samples.petclinic.service.UserService;
import org.springframework.security.config.annotation.web.WebSecurityConfigurer;
import org.springframework.security.test.context.support.WithMockUser;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

import java.time.LocalDate;
import java.util.Optional;

import static org.springframework.security.test.web.servlet.request.SecurityMockMvcRequestPostProcessors.csrf;

/**
 * Test class for {@link PedidoController}
 *
 * @author Victor y tabares
 */

@WebMvcTest(controllers=PedidoController.class,
		excludeFilters = @ComponentScan.Filter(type = FilterType.ASSIGNABLE_TYPE, classes = WebSecurityConfigurer.class),
		excludeAutoConfiguration= SecurityConfiguration.class)
class PedidoControllerTests2 {
	
// toca revisarlo no funciona aun 
	private static final int TEST_PEDIDO_ID = 1;

	@Autowired
	private PedidoController pedidoController;

	@MockBean
	private ProveedorService proveedorService;

	@Autowired
	private MockMvc mockMvc;
	
	private Pedido pedido;
	private Proveedor proveedor;

	@BeforeEach
	void test() {
		proveedor = new Proveedor();
		proveedor.setId(7);
		proveedor.setName("jorge");
		proveedor.setGmail("jorge@gmail.com");
		proveedor.setTelefono("678678678");
		
		pedido = new Pedido();
		pedido.setId(TEST_PEDIDO_ID);
		pedido.setProveedor(proveedor);
		pedido.setHaLlegado(false);
		pedido.setFechaEntrega(null);
		pedido.setFechaPedido(LocalDate.now());
		
		given(this.proveedorService.pedidoPorId(TEST_PEDIDO_ID)).willReturn(Optional.of(pedido));
//		given(this.proveedorService.findPedidoByProveedorId(7).iterator().next()).willReturn(pedido);
//		given(this.proveedorService.findProveedorbyName("jorge")).willReturn(proveedor);
//		given(this.proveedorService.findPedidoByProveedorId(7).iterator().next()).willReturn(prueba);

	}
	
	//Test Crear Pedido (NEW)
	
	@WithMockUser(value = "spring")
    @Test
    void testPedidoNew() throws Exception {
		mockMvc.perform(get("/pedidos/new")).andExpect(status().isOk())
				.andExpect(model().attributeExists("pedido"))
				.andExpect(view().name("pedidos/editPedido"));
	}	
	
	
	// Test Guardar pedido (SAVE)
	
	@WithMockUser(value = "spring")
    @Test
    void testSavePedidoSuccess() throws Exception {
		mockMvc.perform(post("/pedidos/save").param("name", "pepito")
				.with(csrf()))
				.andExpect(view().name("pedidos/listaPedidos"));
	}

	@WithMockUser(value = "spring")
	@Test
	void testSavePedidoFail() throws Exception {
		mockMvc.perform(post("/pedidos/save").param("name", "pepito")
				.with(csrf())
				.param("fechaEntrega", "13 del 10 de 2020")
				.param("haLlegado", "Si"))
				.andExpect(model().attributeHasErrors("pedido"))
				.andExpect(model().attributeHasFieldErrors("pedido", "haLlegado"))
				.andExpect(model().attributeHasFieldErrors("pedido", "fechaEntrega"))
				.andExpect(view().name("pedidos/editPedido"));
	}
	
	
	// Test RecargarStock	
	
	@WithMockUser(value = "spring")
    @Test
    void testRecargarStock() throws Exception {
		mockMvc.perform(get("/pedidos/terminarPedido/{pedidoID}", TEST_PEDIDO_ID)).andExpect(status().isOk())
				.andExpect(model().attributeExists("pedido"))
				.andExpect(model().attribute("pedido", hasProperty("haLlegado", is(false))))
				.andExpect(model().attribute("pedido", hasProperty("fechaEntrega", is(null))))
				.andExpect(view().name("pedidos/listaPedidos"));
		System.out.println("0000000000000000000000000000000000000000000000000000");
		System.out.println(proveedorService.pedidoPorId(TEST_PEDIDO_ID).get().getHaLlegado());
	}	

	
	
	
	
	



}
