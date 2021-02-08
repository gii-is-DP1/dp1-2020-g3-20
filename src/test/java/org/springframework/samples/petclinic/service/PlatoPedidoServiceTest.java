package org.springframework.samples.petclinic.service;

import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.Assert.assertThat;

import java.util.Collection;
import java.util.Iterator;

import javax.transaction.Transactional;

import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.samples.petclinic.model.Ingrediente;
import org.springframework.samples.petclinic.model.IngredientePedido;
import org.springframework.samples.petclinic.model.Plato;
import org.springframework.samples.petclinic.model.PlatoPedido;
import org.springframework.samples.petclinic.model.Producto;
import org.springframework.stereotype.Service;


@DataJpaTest(includeFilters = @ComponentScan.Filter(Service.class))
public class PlatoPedidoServiceTest {

	@Autowired
	protected PlatoPedidoService platoPedidoService;
	@Autowired
	protected PlatoService platoService;
	@Autowired
	protected IngredienteService ingredienteService;
	@Autowired
	protected IngredientePedidoService ingredientePedidoService;

	
/*	@BeforeEach
	@Transactional
	void initAll() {
		Producto producto = new Producto();
		
		Plato plato = new Plato();
		plato.setDisponible(true);
		plato.setName("curry");
		plato.setPrecio(3.0);
		platoService.guardarPlato(plato);
		
		PlatoPedido platoPedido = new PlatoPedido();
		platoPedido.setPlato(plato);
		platoPedido.setId(100);
		platoPedidoService.guardarPP(platoPedido);
		
		Ingrediente ingrediente1 = new Ingrediente();
		ingrediente1.setCantidadUsualPP(2.0);
		ingrediente1.setPlato(plato);
		ingredienteService.guardarIngrediente(ingrediente1);
		
		Ingrediente ingrediente2 = new Ingrediente();
		ingrediente2.setCantidadUsualPP(3.0);
		ingrediente2.setPlato(plato);
		ingredienteService.guardarIngrediente(ingrediente2);
		
		IngredientePedido ingredientePedido1 = new IngredientePedido();
		ingredientePedido1.setPp(platoPedido);
		ingredientePedido1.setCantidadPedida(2.0);
		ingredientePedido1.setIngrediente(ingrediente1);
		ingredientePedidoService.guardarIngredientePedido(ingredientePedido1);
		}*/
	
	@Test
	@Transactional
	void CrearIngredientesPedidos() {
		
		PlatoPedido platoPedido =  platoPedidoService.findById(1).get();
		Collection<IngredientePedido> res = platoPedidoService.CrearIngredientesPedidos(platoPedido);
		Integer size = res.size();
		assertThat(size).isEqualTo(0);
		
	}
	@Test
	@Transactional
	void ingredientePedidoPorPlatoPedido() {
		
		Collection<IngredientePedido> res = platoPedidoService.ingredientePedidoPorPlatoPedido(1);
		Integer size = res.size();
		assertThat(size).isEqualTo(5);
		
	}
}
