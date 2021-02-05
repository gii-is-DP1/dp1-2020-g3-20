package org.springframework.samples.petclinic.service;

import static org.junit.Assert.assertEquals;

import java.util.Collection;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.samples.petclinic.model.TipoProducto;
import org.springframework.stereotype.Service;

@DataJpaTest(includeFilters = @ComponentScan.Filter(Service.class))
class ProductoServiceTests {
	@Autowired
	private ProductoService productoService;
			
	@Test
	public void testCountWithInitialData() {
		Collection<TipoProducto> count= productoService.encontrarTiposProducto();
		assertEquals(count.size(),6);
	}
}
