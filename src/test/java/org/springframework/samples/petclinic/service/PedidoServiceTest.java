package org.springframework.samples.petclinic.service;



import static org.junit.Assert.assertEquals;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.samples.petclinic.model.Pedido;
import org.springframework.samples.petclinic.model.Proveedor;
import org.springframework.stereotype.Service;



@DataJpaTest(includeFilters = @ComponentScan.Filter(Service.class))

public class PedidoServiceTest {

	@Autowired
	private ProveedorService provSer;
	
	
//	@Test
//	public void esCrearPedidoTest() {
//		Pedido test = provSer.crearPedido("taburete");
//		assertEquals("taburete", test.getProveedor().getName());
//		
//	}
	
	@Test
	public void esBuscarProveedor() {
		Proveedor test = provSer.findProveedorbyName("taburete");
		assertEquals("taburete", test.getName());
		
	}
	
}
