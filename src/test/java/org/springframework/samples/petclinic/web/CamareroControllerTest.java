
package org.springframework.samples.petclinic.web;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import static org.mockito.BDDMockito.given;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.FilterType;
import org.springframework.samples.petclinic.configuration.SecurityConfiguration;
import org.springframework.samples.petclinic.model.Camarero;
import org.springframework.samples.petclinic.service.CamareroService;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.view;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;

import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.model;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

import org.springframework.security.config.annotation.web.WebSecurityConfigurer;
import org.springframework.security.test.context.support.WithMockUser;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import org.springframework.test.web.servlet.result.MockMvcResultMatchers;

import org.springframework.security.test.web.servlet.request.SecurityMockMvcRequestPostProcessors;


@WebMvcTest(controllers = CamareroController.class, excludeFilters = @ComponentScan.Filter(type = FilterType.ASSIGNABLE_TYPE, classes = WebSecurityConfigurer.class), excludeAutoConfiguration = SecurityConfiguration.class)
public class CamareroControllerTest {

	private static final int TEST_CAMARERO_ID = 1;

	@MockBean
	private CamareroService camareroService;

	@Autowired
	private MockMvc mockMvc;

	@BeforeEach

	void setup() {
		Camarero camarero = new Camarero();
		camarero.setApellido("ffffff");
		camarero.setContrasena("12345");
		camarero.setGmail("xxxxx@gmail.com");
		camarero.setName("aaaass");
		camarero.setTelefono("123456789");
		camarero.setUsuario("jose");
		given(this.camareroService.findCamereroById(TEST_CAMARERO_ID)).willReturn(camarero);

	}

	@WithMockUser(value = "spring")
	@Test
	@DisplayName("Listar Camarero")
	void showCamarerosList() throws Exception {
		mockMvc.perform(get("/camareros")).andExpect(status().isOk()).andExpect(view().name("camareros/listaCamareros"))
				.andExpect(model().attributeExists("camareros"));
	}

	@WithMockUser(value = "spring")
	@Test

	@DisplayName("Vista creacion Camarero")
	void testInitCreationForm() throws Exception {
		this.mockMvc.perform(MockMvcRequestBuilders.get("/camareros/new"))
				.andExpect(MockMvcResultMatchers.status().isOk())
				.andExpect(MockMvcResultMatchers.view().name("camareros/editCamarero"))
				.andExpect(MockMvcResultMatchers.model().attributeExists("camarero"));
	}

	@WithMockUser(value = "spring")
	@Test
	void processCreationFormSuccess() throws Exception {
		mockMvc.perform(MockMvcRequestBuilders.post("/camareros/save").with(SecurityMockMvcRequestPostProcessors.csrf())
				.param("name", "Pepe")
				.param("apellido", "esc")
				.param("telefono", "543972343").param("gmail", "pe2000@gmail.com")
				.param("contrasena", "12345").param("suario", "jose"))
		.andExpect(MockMvcResultMatchers.status().isOk())
		  .andExpect(MockMvcResultMatchers.view().name("/camareros/editCocinero"));
		}

}
