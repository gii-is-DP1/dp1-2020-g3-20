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

import static org.springframework.security.test.web.servlet.request.SecurityMockMvcRequestPostProcessors.csrf;

/**
 * Test class for {@link PedidoController}
 *
 * @author Victor y tabares
 */

@WebMvcTest(controllers=ProveedorController.class,
		excludeFilters = @ComponentScan.Filter(type = FilterType.ASSIGNABLE_TYPE, classes = WebSecurityConfigurer.class),
		excludeAutoConfiguration= SecurityConfiguration.class)
class ProveedorControllerTests {
	private static final int TEST_PROVEEDOR_ID = 1;



	@Autowired
	private ProveedorController ProveedorController;

	@MockBean
	private ProveedorService proveedorService;

	@Autowired
	private MockMvc mockMvc;


	private Proveedor jorge;

	@BeforeEach
	void setup() {
		jorge = new Proveedor();
		jorge.setId(TEST_PROVEEDOR_ID);
		jorge.setName("jorge");
		jorge.setApellido("monteperrito");
		jorge.setGmail("patata@gmail.com");
		jorge.setTelefono("954333333");
//		prueba = new Pedido();
//		prueba.setId(TEST_PEDIDO_ID);
//		prueba.setProveedor(proveedor);
//		prueba.setHaLlegado(true);
//		prueba.setFechaEntrega(LocalDate.now());
//		prueba.setFechaPedido(LocalDate.now());
//		prueba.setCosteTotal(60.55);
		given(this.proveedorService.findProveedorbyName("jorge")).willReturn(jorge);
//		given(this.proveedorService.findPedidoByProveedorId(7).iterator().next()).willReturn(prueba);


}
	@WithMockUser(value = "spring")
    @Test
    void testInitCreationForm() throws Exception {
	mockMvc.perform(get("/proveedor/new")).andExpect(status().isOk()).andExpect(model().attributeExists("proveedor"))
			.andExpect(view().name("proveedor/editProveedor"));
}
//    @WithMockUser(value = "spring")
//    @Test
//    void testEditproveedor() throws Exception {
//    	mockMvc.perform(get("/proveedor/edit/{proveedorId}",TEST_PROVEEDOR_ID)).andExpect(status().isOk())
//   			.andExpect(model().attributeExists("proveedor"))
//   			.andExpect(model().attribute("proveedor", hasProperty("apellido", is("monteperrito"))))
//   			.andExpect(model().attribute("proveedor", hasProperty("name", is("jorge"))))
//   			.andExpect(model().attribute("proveedor", hasProperty("gmail", is("patata@gmail.com"))))
//    		.andExpect(model().attribute("proveedor", hasProperty("telefono", is("954333333"))))
//    		.andExpect(view().name("proveedor/editarProveedor"));
//}
//


}
