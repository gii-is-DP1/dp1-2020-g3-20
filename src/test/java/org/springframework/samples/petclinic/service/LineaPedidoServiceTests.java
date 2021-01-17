package org.springframework.samples.petclinic.service;

import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.Assert.assertEquals;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.samples.petclinic.model.LineaPedido;
import org.springframework.samples.petclinic.model.Pedido;
import org.springframework.samples.petclinic.model.Producto;
import org.springframework.stereotype.Service;




@DataJpaTest(includeFilters = @ComponentScan.Filter(Service.class))
public class LineaPedidoServiceTests {
	@Autowired
	private ProveedorService proveedorService;
	
	@Autowired
	private ProductoService productoService;
	
	//Contar cuantas lineas de pedido hay.
	@Test
	public void testCountWithInitialData() {
		int count = proveedorService.lineaPedidoCount();
		assertEquals(count,2);
	}
	
	
	//FindLineaPEdidoByProductoID
	@Test
	public void esBuscarLineaPedidoconProductoId() {
		Iterable<LineaPedido> test = proveedorService.findLineaPedidoByProductoId(1);
		Iterator<LineaPedido> it_test = test.iterator();
		int i = 0;
		while(it_test.hasNext()) {
			LineaPedido lineaPedido = it_test.next();
			int productoID = lineaPedido.getProducto().getId();
			assertEquals(1,productoID);
			assertThat(lineaPedido.getPedido()).isNotNull();
			assertThat(lineaPedido.getProducto()).isNotNull();
			i++;
		}
		assertEquals(i,2);
	}
	
	//AñadirLineaPedido
	@Test
	public void añadirLineaPedidoAPedido() {

		Producto producto = productoService.buscaProductoPorId(5).get();
		Pedido pedido1 = proveedorService.pedidoPorId(2).get();
		int found = pedido1.getLineasPedidas().size();
		
		LineaPedido lineapedido = proveedorService.anadirLineaPedido(producto, pedido1);
		proveedorService.saveLineaPedido(lineapedido);
		assertThat(lineapedido.getId()).isNotNull();
		
		double cantidad = producto.getCantMax()-producto.getCantAct();
		assertThat(lineapedido.getCantidad()).isEqualTo(cantidad);
		
		Iterable<LineaPedido> test = proveedorService.findLineaPedidoByProductoId(1);
		Iterator<LineaPedido> it_test = test.iterator();
		List<LineaPedido> aux = new ArrayList<LineaPedido>();
		while (it_test.hasNext()) {
			aux.add(it_test.next());
		}
		assertEquals(aux.size(), found+1);
	}
	
	
}
