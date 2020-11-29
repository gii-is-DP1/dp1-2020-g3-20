package org.springframework.samples.petclinic.service;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertThat;
import static org.junit.jupiter.api.Assertions.assertThrows;

import javax.validation.ConstraintViolationException;

import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.dao.InvalidDataAccessApiUsageException;
import org.springframework.samples.petclinic.model.Propietario;
import org.springframework.samples.petclinic.repository.PropietarioRepository;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import static org.assertj.core.api.Assertions.assertThat;
import javax.persistence.EntityManager;

import static org.junit.jupiter.api.Assertions.assertThrows;

import net.bytebuddy.dynamic.DynamicType.Builder.FieldDefinition.Optional;

@DataJpaTest(includeFilters = @ComponentScan.Filter(Service.class))
public class PropietarioServiceTests {
	
	@Autowired
	private PropietarioService proServi;
	
	@Autowired
	protected PropietarioRepository pr;
			
	@Test
	public void testCountWithInitialData() {
		int count= proServi.propietarioCount();
		assertEquals(count,1);
	}
	
	@Test
	@Transactional
	void shouldFindPropietario() {
		java.util.Optional<Propietario> propietario = this.proServi.buscaPropietarioPorId(1);
		Propietario p= propietario.get();
		assertThat(p.getName().equals("Abdel"));
		assertThat(p.getApellido().equals("Ch"));
		assertThat(p.getGmail().equals("Abdch@gmail.com"));
		assertThat(p.getTelefono().equals("602354622"));
		assertThat(p.getContraseña().equals("12345"));

	}
	
	@Test
	@Transactional
	void shouldInsertPropietario() {
		 
		Propietario p = new Propietario();
		p.setName("abdel");
		p.setApellido("Chell");
        p.setGmail("abdel@gmail.com");
		p.setContraseña("1234567");
		p.setTelefono("602343454");
		
		this.pr.save(p);
		assertThat(p.getId()).isNotNull();
		
	}
	
	@Test
	@Transactional
	void shouldNotInsertPropietarioithWrongEmail() {

		Propietario p = new Propietario();
		p.setName("abdel");
		p.setApellido("Ch");
        p.setGmail("abdel");
		p.setContraseña("1234567");
		p.setTelefono("602343454");
		assertThrows(ConstraintViolationException.class, () -> {
			this.pr.save(p);
		});
		
		
	}
	@Test
	@Transactional
	void shouldNotInsertPropietarioWithWrongcontraseña() {

		Propietario p = new Propietario();
		p.setName("abdel");
		p.setApellido("Ch");
        p.setGmail("abdel");
		p.setContraseña("1");
		p.setTelefono("602343454");
		assertThrows(ConstraintViolationException.class, () -> {
			this.pr.save(p);
		});
		
		
	}
	@Test
	@Transactional
	void shouldUpdateNombre() {
		Propietario p = new Propietario();
		String nombre = p.getName();
		String n =nombre +"abi";
		p.setName(n);
		this.pr.save(p);
		assertThat(p.getName()).isEqualTo(n);
	}
	@Test
	@Transactional
	void shouldDeletePropietario() {
		java.util.Optional<Propietario> pro=proServi.buscaPropietarioPorId(1);
		this.proServi.delete(pro.get());
		assertThat(pro.get()).isEqualTo(null);
			
	}
}
