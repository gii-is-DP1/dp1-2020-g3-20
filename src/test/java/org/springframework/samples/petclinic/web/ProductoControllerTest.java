package org.springframework.samples.petclinic.web;

import java.time.LocalDate;


import org.assertj.core.util.Lists;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.mockito.BDDMockito;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.FilterType;
import org.springframework.samples.petclinic.configuration.SecurityConfiguration;

import org.springframework.samples.petclinic.model.Producto;
import org.springframework.samples.petclinic.model.Proveedor;
import org.springframework.samples.petclinic.model.TipoProducto;
import org.springframework.samples.petclinic.service.ProductoService;
import org.springframework.samples.petclinic.service.ProveedorService;
import org.springframework.security.config.annotation.web.WebSecurityConfigurer;
import org.springframework.security.test.context.support.WithMockUser;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import org.springframework.test.web.servlet.result.MockMvcResultMatchers;

@WebMvcTest(controllers = ProductoController.class, excludeFilters = @ComponentScan.Filter(type = FilterType.ASSIGNABLE_TYPE, classes = WebSecurityConfigurer.class), excludeAutoConfiguration = SecurityConfiguration.class)
public class ProductoControllerTest {

	private static final int	TEST_PRODUCTO_ID	= 1;

	//private static final int	TEST_PROVEEDOR_ID	= 1;
	private static final int	TEST_TIPOPRODUCTO	= 1;
	
	@MockBean
	private ProductoService		productoService;
	@MockBean
	private ProveedorService		proveedorService;
	

	@Autowired
	private MockMvc				mockMvc;

	
	private Producto				producto;
	private TipoProducto				tipoproducto;
	private Proveedor				proveedor;
	


	@BeforeEach
	
	void setup() {
		
		  this.producto = new Producto();
		  this.proveedor= Lists.newArrayList(proveedorService.findAll()).get(1);
		  this.tipoproducto=new TipoProducto();
		 
		  //this.proveedor.setId(this.TEST_PROVEEDOR_ID);
			
			
			/*
			 * this.proveedor.setApellido("prvdr1");
			 * this.proveedor.setGmail("adch@gmail.com");
			 * this.proveedor.setTelefono("123456789"); this.proveedor.setId(4);
			 */
			 
		  
		  
		 // this.proveedor.setId(ProductoControllerTest.TEST_PROVEEDOR_ID);
		  
		  //this.tipoproducto.setName("solmillo");
		  this.tipoproducto.setId(this.TEST_TIPOPRODUCTO);
		 
		  
		  this.producto.setId(ProductoControllerTest.TEST_PRODUCTO_ID);
		  this.producto.setCantAct(1.0);
		  this.producto.setCantMax(10.0);
		  this.producto.setCantMin(0.0);
		  //this.producto.setFechaCaducidad(LocalDate.now());		  
		  this.producto.setName("abi");
		  this.producto.setProveedor(proveedor);
		  this.producto.setTipoProducto(this.tipoproducto);
		 
		  BDDMockito.given(this.productoService.buscaProductoPorId(ProductoControllerTest.TEST_PRODUCTO_ID).get()).willReturn(this.producto);
		  BDDMockito.given(this.productoService.encontrarTiposProducto()).willReturn(Lists.newArrayList(this.tipoproducto));
	}
	@WithMockUser(username = "antonio", roles = {
			"MANAGER"
		})

	@Test
	@DisplayName("Vista creacion producto")
	void testInitCreationForm() throws Exception {
		this.mockMvc.perform(MockMvcRequestBuilders.get("/producto/new")).andExpect
		(MockMvcResultMatchers.status().isOk()).andExpect(MockMvcResultMatchers.
				view().name("producto/editProducto"))
			.andExpect(MockMvcResultMatchers.model().attributeExists("producto"));
	}

	/*
	 * @WithMockUser(value = "spring")
	 * 
	 * @Test
	 * 
	 * @DisplayName("Vista lista reseña") void testList() throws Exception {
	 * this.mockMvc.perform(MockMvcRequestBuilders.get("/reviews")).andExpect(
	 * MockMvcResultMatchers.status().isOk()).andExpect(MockMvcResultMatchers.view()
	 * .name("/reviews/listadoReviews")); }
	 * 
	 * @WithMockUser(username = "owner5", roles = { "OWNER" })
	 * 
	 * @DisplayName("Creacion reseña fallida por reserva doble")
	 * 
	 * @Test void testProcessCreationFormFailureDouble() throws Exception {
	 * this.mockMvc.perform(MockMvcRequestBuilders.post("/reviews/save/")
	 * .with(SecurityMockMvcRequestPostProcessors.csrf()) .param("description",
	 * "description review test") .param("valoracion", "4"))
	 * .andExpect(MockMvcResultMatchers.status().isOk())
	 * .andExpect(MockMvcResultMatchers.view().name("reviews/editReview"));
	 * 
	 * }
	 * 
	 * 
	 * @WithMockUser(username = "owner6", roles = { "OWNER" })
	 * 
	 * @DisplayName("Creacion reseña exitosa")
	 * 
	 * @Test void testProcessCreationFormSuccess() throws Exception {
	 * this.mockMvc.perform(MockMvcRequestBuilders.post("/reviews/save/")
	 * .with(SecurityMockMvcRequestPostProcessors.csrf()) .param("description",
	 * "description review test") .param("valoracion", "4"))
	 * .andExpect(MockMvcResultMatchers.status().isOk())
	 * .andExpect(MockMvcResultMatchers.view().name("/reviews/listadoReviews"));
	 * 
	 * }
	 * 
	 * 
	 * @WithMockUser(username = "owner6", roles = { "OWNER" })
	 * 
	 * @DisplayName("Creacion reseña fallida por parametros")
	 * 
	 * @Test void testProcessCreationFormHasErrors() throws Exception {
	 * this.mockMvc.perform(MockMvcRequestBuilders.post("/reviews/save/")
	 * .with(SecurityMockMvcRequestPostProcessors.csrf()) .param("description",
	 * "description review test").param("valoracion", "0"))
	 * .andExpect(MockMvcResultMatchers.status().isOk())
	 * .andExpect(MockMvcResultMatchers.view().name("reviews/editReview")); }
	 * 
	 * @WithMockUser(username = "owner5", roles = { "OWNER" })
	 * 
	 * @DisplayName("Actualizar reseña con errores")
	 * 
	 * @Test void testProcessUpdateFormHasErrors() throws Exception {
	 * this.mockMvc.perform(MockMvcRequestBuilders.post("/reviews/edit")
	 * .with(SecurityMockMvcRequestPostProcessors.csrf()) .param("description",
	 * "description review test").param("valoracion", "0"))
	 * .andExpect(MockMvcResultMatchers.status().isOk())
	 * .andExpect(MockMvcResultMatchers.view().name("reviews/editReview")); }
	 * 
	 * @WithMockUser(username = "owner5", roles = { "OWNER" })
	 * 
	 * @DisplayName("Actualizar reseña exitosamente")
	 * 
	 * @Test void testProcessUpdateFormSuccess() throws Exception {
	 * this.mockMvc.perform(MockMvcRequestBuilders.post("/reviews/edit")
	 * .with(SecurityMockMvcRequestPostProcessors.csrf()) .param("description",
	 * "description review test").param("valoracion", "1"))
	 * .andExpect(MockMvcResultMatchers.status().is3xxRedirection())
	 * .andExpect(MockMvcResultMatchers.view().name("redirect:/reviews")); }
	 * 
	 * 
	 * @WithMockUser(username = "owner6", roles = { "OWNER" })
	 * 
	 * @DisplayName("Borrar reseña fallida")
	 * 
	 * @Test void testProcessDeleteHasError() throws Exception {
	 * this.mockMvc.perform(MockMvcRequestBuilders.post("/reviews/delete/"+this.
	 * TEST_REVIEW_ID) .with(SecurityMockMvcRequestPostProcessors.csrf()) )
	 * .andExpect(MockMvcResultMatchers.status().isOk())
	 * .andExpect(MockMvcResultMatchers.view().name("exception")); }
	 * 
	 * @WithMockUser(username = "owner5", roles = { "OWNER" })
	 * 
	 * @DisplayName("Borrado de reseña exitoso")
	 * 
	 * @Test void testProcessDeleteSuccess() throws Exception {
	 * this.mockMvc.perform(MockMvcRequestBuilders.get("/reviews/delete/"+this.
	 * TEST_REVIEW_ID) .with(SecurityMockMvcRequestPostProcessors.csrf()) )
	 * .andExpect(MockMvcResultMatchers.status().is3xxRedirection())
	 * .andExpect(MockMvcResultMatchers.view().name("redirect:/reviews")); }
	 */
	
	



}
