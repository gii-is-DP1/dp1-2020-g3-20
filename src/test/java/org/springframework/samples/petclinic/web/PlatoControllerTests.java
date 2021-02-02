package org.springframework.samples.petclinic.web;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;

import org.springframework.test.web.servlet.MockMvc;



import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.FilterType;
import org.springframework.samples.petclinic.configuration.SecurityConfiguration;
import org.springframework.samples.petclinic.model.Plato;
import org.springframework.samples.petclinic.model.Proveedor;
import org.springframework.samples.petclinic.service.AuthoritiesService;
import org.springframework.samples.petclinic.service.IngredienteService;
import org.springframework.samples.petclinic.service.OwnerService;
import org.springframework.samples.petclinic.service.PlatoService;
import org.springframework.samples.petclinic.service.ProveedorService;
import org.springframework.samples.petclinic.service.UserService;
import org.springframework.security.config.annotation.web.WebSecurityConfigurer;
import org.springframework.security.test.context.support.WithMockUser;

import static org.hamcrest.Matchers.hasProperty;
import static org.hamcrest.Matchers.is;
import static org.mockito.BDDMockito.given;
import static org.springframework.security.test.web.servlet.request.SecurityMockMvcRequestPostProcessors.csrf;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

import java.util.Optional;


/**
 * Test class for {@link OwnerController}
 *
 * @author Colin But
 */

@WebMvcTest(controllers=PlatoController.class,
excludeFilters = @ComponentScan.Filter(type = FilterType.ASSIGNABLE_TYPE, classes = WebSecurityConfigurer.class),
excludeAutoConfiguration= SecurityConfiguration.class)
class PlatoControllerTests {
private static final int TEST_PLATO_ID = 1;

@MockBean
private PlatoService platoService;

@MockBean
private IngredienteService ingService;

@MockBean
private PlatoFormatter platoFormatter;

@Autowired
private MockMvc mockMvc;



private Plato plato;

@BeforeEach
void setup() {
	plato = new Plato();
	plato.setId(TEST_PLATO_ID);
	plato.setName("jorge");
	plato.setPrecio(4.);
	plato.setDisponible(true);
	plato.setIngredientes(null);
	
	given(this.platoService.buscaPlatoPorId(TEST_PLATO_ID)).willReturn(Optional.of(plato));
//	given(this.proveedorService.provedroporid(TEST_PROVEEDOR_ID)).willReturn(Optional.of(jorge));


}


@WithMockUser(value = "spring")
@Test
void testPlatoNew() throws Exception {
mockMvc.perform(get("/platos/new")).andExpect(status().isOk()).andExpect(model().attributeExists("platos"))
	.andExpect(view().name("platos/editPlatos"));
}


}

