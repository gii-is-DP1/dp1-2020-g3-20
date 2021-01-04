package org.springframework.samples.petclinic.service;

import static org.junit.Assert.assertEquals;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.samples.petclinic.model.LineaPedido;
import org.springframework.samples.petclinic.model.Pedido;
import org.springframework.samples.petclinic.model.Producto;
import org.springframework.samples.petclinic.model.Proveedor;
import org.springframework.stereotype.Service;


@DataJpaTest(includeFilters = @ComponentScan.Filter(Service.class))
public class LineaPedidoServiceTests {
	@Autowired
	private ProveedorService lineaPedidoService;
	private ProductoService productoService;
	@Test
	public void testCountWithInitialData() {
		int count = lineaPedidoService.lineaPedidoCount();
		assertEquals(count,2);
	}
	
//	@Test
//	public void testCrearLineaPedido() {
//		System.out.println("0000000000000000000000000000000000000000000000000000000000000000000000");
//		Producto producto = productoService.buscaProductoPorId(1).get();
//		System.out.println("1111111111111111111111111111111111111111111111111111111111111111111111111");
//		Proveedor proveedor = lineaPedidoService.findProveedorbyName("taburete");
//		System.out.println("2222222222222222222222222222222222222222222222222222222222222222222222222");
//		Pedido pedido = lineaPedidoService.findPedidoByProveedorId(proveedor.getId()).iterator().next();
//		System.out.println("333333333333333333333333333333333333333333333333333333333333333333333333333333");
//		LineaPedido lp = lineaPedidoService.crearLineaPedido(producto, pedido);
//		System.out.println("999999999999999999999999999999999999999999999999999999999999999999999999999999999");
//		System.out.println(lp);
//	}
}
