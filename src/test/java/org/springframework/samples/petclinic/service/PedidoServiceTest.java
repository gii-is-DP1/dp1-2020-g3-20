package org.springframework.samples.petclinic.service;




import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.Assert.assertEquals;
import java.util.logging.Level;
import java.util.logging.Logger;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.Collection;
import java.util.Iterator;
import java.util.List;
import java.util.Optional;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.samples.petclinic.model.Pedido;
import org.springframework.samples.petclinic.model.Producto;
import org.springframework.samples.petclinic.service.exceptions.DuplicatedPedidoException;
import org.springframework.stereotype.Service;



@DataJpaTest(includeFilters = @ComponentScan.Filter(Service.class))

public class PedidoServiceTest {

	@Autowired
	private ProveedorService proveedorService;
	
	@Autowired
	private ProductoService productoService;
	
	//FindPedidoByProveedorID
	@Test
	public void findPedidoByProveedorId() {
		Iterable<Pedido> test = this.proveedorService.findPedidoByProveedorId(1);
		Iterator<Pedido> it_test = test.iterator();
		List<Pedido> aux = new ArrayList<Pedido>();
		while (it_test.hasNext()) {
			Pedido pedido = it_test.next();
			aux.add(pedido);
			assertThat(pedido.getId()).isNotNull();
		}
		
		int a = aux.get(0).getId();
		assertEquals(1, a);
		assertEquals(false, aux.get(0).getHaLlegado());
		assertEquals("Makro", aux.get(0).getProveedor().getName());

		
	}
	
	//SavePedido
	
	@Test
	public void guardarPedido() {
		int found = proveedorService.Pedidocount();
		
		Pedido pedido = new Pedido();
		pedido.setFechaPedido(LocalDate.now());
		pedido.setHaLlegado(false);
		pedido.setProveedor(proveedorService.provedroporid(3).get());
		
		try {
			proveedorService.savePedido(pedido);
		} catch (DuplicatedPedidoException ex) {
			Logger.getLogger(PedidoServiceTest.class.getName()).log(Level.SEVERE, null, ex);
		}
      
		int size = proveedorService.Pedidocount();
		assertEquals(found+1, size);	
	}
	
	
	@Test
	public void falloGuardarPedidoRepetido() {
		int found = proveedorService.Pedidocount();
		
		Pedido pedido = new Pedido();
		pedido.setFechaPedido(LocalDate.now());
		pedido.setHaLlegado(false);
		pedido.setProveedor(proveedorService.provedroporid(1).get());
		
		try {
			proveedorService.savePedido(pedido);
		} catch (DuplicatedPedidoException ex) {
			Logger.getLogger(PedidoServiceTest.class.getName()).log(Level.SEVERE, null, ex);
		}
      
		int size = proveedorService.Pedidocount();
		assertEquals(found, size);
	}
	
	//EncontrarProductoProveedor
	
	@Test
	public void listaDeProductosDeProveedor() {
		Producto producto = productoService.buscaProductoPorId(1).get();
		Collection<Producto> aux = proveedorService.encontrarProductoProveedor(producto);
		int size = aux.size();
		assertEquals(size, 7);
	}
	
	
	@Test
	public void testprueba() {
		Optional<Pedido> test = this.proveedorService.pedidoPorId(1);
		assertEquals("1", test.get().getId().toString());
		assertEquals(false, test.get().getHaLlegado());
		assertEquals("Makro", test.get().getProveedor().getName());		
	}
	
	
}
