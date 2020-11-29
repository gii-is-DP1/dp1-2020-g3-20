package org.springframework.samples.petclinic.service;

import static org.junit.Assert.assertEquals;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.stereotype.Service;

@DataJpaTest(includeFilters = @ComponentScan.Filter(Service.class))
class CamareroServiceTests {
	@Autowired
	private CamareroService camSer;
			
	@Test
	public void testCountWithInitialData() {
		int count= camSer.camareroCount();
		assertEquals(count,1);
	}

}
