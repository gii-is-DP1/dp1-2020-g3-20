package org.springframework.samples.petclinic.service;

import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.Assert.assertEquals;

import java.util.List;

import org.assertj.core.util.Lists;
import org.junit.jupiter.api.Test;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;

import org.springframework.context.annotation.ComponentScan;
import org.springframework.samples.petclinic.model.Camarero;
import org.springframework.samples.petclinic.service.exceptions.DuplicatedPedidoException;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;



@DataJpaTest(includeFilters = @ComponentScan.Filter(Service.class))
class CamareroServiceTests {
	@Autowired
	private CamareroService camSer;
			
	@Test
	public void testCountWithInitialData() {
		int count= camSer.camareroCount();
		assertEquals(count,2);
	}
	
	
	  @Test
	  void shouldDeleteCamarero() {
	  Iterable<Camarero> it= camSer.camareroList();
	  List<Camarero> ls=Lists.newArrayList(it);
	  int foundBefore = ls.size();
	  
	  Camarero cm = this.camSer.findById(1).get();
	  this.camSer.borrarCamarero(cm.getId());	  
		
		 List<Camarero> ls2=Lists.newArrayList(camSer.camareroList());
		 
	  int foundAfter = ls2.size();
	  assertThat(foundBefore).isEqualTo(foundAfter+1);
	  
	  }
	  
	  @Test
	  @Transactional
		public void shouldInsertCamarero() throws DuplicatedPedidoException {
		  Iterable<Camarero> it= camSer.camareroList();
		  List<Camarero> ls=Lists.newArrayList(it);
		  int before = ls.size();
			
	        Camarero cam= new Camarero();
	        		
	        
	       cam.setUsuario("suario1");  
	       cam.setApellido("Avr");
	       cam.setContrasena("12345");
	       cam.setGmail("AS@gmail.com");
	       cam.setTelefono("123456789");
	   
	                
			this.camSer.guardarCamarero(cam);
			assertThat(cam.getId().longValue()).isNotEqualTo(0);

			int after = Lists.newArrayList(camSer.camareroList()).size();
			assertThat(before+1).isEqualTo(after);
	 

}
}
