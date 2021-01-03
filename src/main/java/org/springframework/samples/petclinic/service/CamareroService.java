package org.springframework.samples.petclinic.service;

import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.samples.petclinic.model.Camarero;
import org.springframework.samples.petclinic.model.User;
import org.springframework.samples.petclinic.repository.CamareroRepository;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
public class CamareroService {
	@Autowired
	private UserService userService;
	
	@Autowired
	private CamareroRepository camRep;
	
	@Autowired
	private AuthoritiesService authoritiesService;
	
	@Transactional
	public int camareroCount() {
		return (int) camRep.count();
		
	}
	

	@Transactional
	public Iterable<Camarero> camareroList() {
		return camRep.findAll();
		
	}

	@Transactional
	public Camarero guardarCamarero(Camarero camarero) {
		//creating user
		User user=authoritiesService.crearUsuario(camarero.getUsuario(), camarero.getContrasena());
		userService.saveUser(user);
		//creating authorities
		authoritiesService.saveAuthorities(camarero.getUsuario(), "camarero");
		return camRep.save(camarero);
		
	}
	
	@Transactional
	public void borrarCamarero(Integer id) {
		camRep.deleteById(id);
		
	}
	
	@Transactional
	public Optional<Camarero> buscaCamareroPorId(Integer id) {
		return camRep.findById(id);
		
	}
}
