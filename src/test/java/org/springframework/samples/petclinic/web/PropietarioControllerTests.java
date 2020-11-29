/*package org.springframework.samples.petclinic.web;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.BDDMockito;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;

import org.springframework.test.web.servlet.MockMvc;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.security.test.web.servlet.request.SecurityMockMvcRequestPostProcessors.csrf;

import org.springframework.samples.petclinic.configuration.SecurityConfiguration;
import org.springframework.samples.petclinic.model.Propietario;

import org.springframework.samples.petclinic.service.PropietarioService;
import org.springframework.security.config.annotation.web.WebSecurityConfigurer;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.FilterType;


@WebMvcTest(controllers=PropietarioController.class, 
excludeFilters = @ComponentScan.Filter(type = FilterType.ASSIGNABLE_TYPE))
public class PropietarioControllerTests {
	
	@MockBean
    private PropietarioService propietarioService;
    private Propietario propietario;
	
   

    @Autowired
    private MockMvc mockMvc;

    @BeforeEach
    void setup() {
    	this.propietario = new Propietario();
    	propietario.setName("anna");
    	propietario.setApellido("Alvarez");
    	propietario.setTelefono("621213243");
    	propietario.setContraseña("1234567");
    	propietario.setGmail("Anna@gmail.com");
    	
    	
    	BDDMockito.given(this.propietarioService.buscaPropietarioPorId(1).get()).willReturn(this.propietario);
      
    }
  
	
	  @Test void testInitCreationForm() throws Exception {
	  
	  mockMvc.perform(get("/propietarios/new")).andExpect(status().isOk()).
	  andExpect(model().attributeExists("propietario"))
	  .andExpect(view().name("propietarios/editPropietario")); }
	 
    
    @Test
	void testProcessCreationFormSuccess() throws Exception {
		
		mockMvc.perform(post("/propietarios/save")
							.with(csrf())
							
							.param("name", "anna")
							.param("apellido", "Alvarez")
							.param("gmail", "Anna@gmail.com")
		                    .param("telefono", "621213243")
		                    .param("contraseña", "1234567")
		                    
		                    
		                    )
		                  
				.andExpect(status().isOk())
				.andExpect(model().attributeHasNoErrors("propietario"))
				.andExpect(view().name("propietarios/listaPropietarios"));
	}*/

//}
