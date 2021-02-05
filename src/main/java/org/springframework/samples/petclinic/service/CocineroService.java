package org.springframework.samples.petclinic.service;

import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.samples.petclinic.model.Cocinero;
import org.springframework.samples.petclinic.model.User;
import org.springframework.samples.petclinic.repository.CocineroRepository;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
public class CocineroService {
	@Autowired
	private UserService userService;
	
	@Autowired
	private AuthoritiesService authoritiesService;
	
	@Autowired
	private CocineroRepository cocineroRepository;
	
	@Transactional
	public int cocineroCount() {
		return (int) cocineroRepository.count();
	}
	
	@Transactional
	public Iterable<Cocinero> findAll() {
		return cocineroRepository.findAll();
		
	}

	@Transactional
	public Cocinero guardarCocinero(Cocinero cocinero) {
		//creating user
				User user=authoritiesService.crearUsuario(cocinero.getUsuario(), cocinero.getContrasena());
				userService.saveUser(user);
				//creating authorities
				authoritiesService.saveAuthorities(cocinero.getUsuario(), "cocinero");
		return cocineroRepository.save(cocinero);
		
	}
	
	@Transactional
	public void borrarCocinero(Integer id) {
		cocineroRepository.deleteById(id);
		
	}
	
	@Transactional
	public Optional<Cocinero> findById(Integer id) {
		return cocineroRepository.findById(id);
		
	}
}
