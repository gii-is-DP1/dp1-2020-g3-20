package org.springframework.samples.petclinic.service;



import static org.junit.Assert.assertEquals;

import java.util.Optional;

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
	
	
	@Test
	public void esCrearPedidoTest() {
		Proveedor prov = new Proveedor();
		prov.setApellido("prueba2");
		prov.setName("prueba");
		prov.setGmail("ffff@gmail");
		prov.setTelefono("655666666");
		Pedido test = provSer.crearPedido(prov);
		assertEquals("prueba", test.getProveedor().getName());
		assertEquals("655666666", test.getProveedor().getTelefono());
		
	}
	

	@Test
	public void findPedidoByProveedorId() {
		Iterable<Pedido> test = this.provSer.findPedidoByProveedorId(1);
		assertEquals("1", test.iterator().next().getId().toString());
		assertEquals(false, test.iterator().next().getHaLlegado());
		assertEquals("Taburete", test.iterator().next().getProveedor().getName());
		
	}
	@Test
	public void testprueba() {
		Optional<Pedido> test = this.provSer.pedidoPorId(1);
		assertEquals("1", test.get().getId().toString());
		assertEquals(false, test.get().getHaLlegado());
		assertEquals("Taburete", test.get().getProveedor().getName());

		
	}
	
	
}
