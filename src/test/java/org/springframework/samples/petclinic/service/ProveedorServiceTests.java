package org.springframework.samples.petclinic.service;


import static org.junit.Assert.assertEquals;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.samples.petclinic.model.Proveedor;
import org.springframework.stereotype.Service;



@DataJpaTest(includeFilters = @ComponentScan.Filter(Service.class))
public class ProveedorServiceTests {
	@Autowired
	private ProveedorService provSer;
	
	
	@Test
	public void esIgualProveedorTest() {
		//assertEquals(1,2);
		
//		Proveedor proveedor1 = new Proveedor();
//		proveedor1.setId(89);
//		proveedor1.setName("oransio");
//		proveedor1.setApellido("pepo");
//		proveedor1.setGmail("hola");
//		proveedor1.setTelefono("989898989");
//		Proveedor proveedor2 = new Proveedor();
//		proveedor2.setId(90);
//		proveedor2.setName("oransio");
//		proveedor2.setApellido("pepo");
//		proveedor2.setGmail("hola");
//		proveedor2.setTelefono("989898989");
//		Proveedor proveedor3 = new Proveedor();
//		proveedor3.setId(91);
//		proveedor3.setName("pepe");
//		proveedor3.setApellido("manzana");
//		proveedor3.setGmail("jeje");
//		proveedor3.setTelefono("989898987");
		
		assertEquals(provSer.esIgual("Taburete", "El capo"),true);
		assertEquals(provSer.esIgual("Jose", "Diaz"),false);
		
	}
	@Test
	public void esBuscarProveedor() {
		Proveedor test = provSer.findProveedorbyName("Taburete");
		assertEquals("Taburete", test.getName());
		
	}

}
