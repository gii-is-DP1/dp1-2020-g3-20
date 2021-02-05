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
	private CocineroRepository cocineroRepo;
	
	@Transactional
	public int cocineroCount() {
		return (int) cocineroRepo.count();
	}
	
	@Transactional
	public Iterable<Cocinero> findAll() {
		return cocineroRepo.findAll();
		
	}

	@Transactional
	public Cocinero guardarCocinero(Cocinero cocinero) {
		//creating user
				User user=authoritiesService.crearUsuario(cocinero.getUsuario(), cocinero.getContrasena());
				userService.saveUser(user);
				//creating authorities
				authoritiesService.saveAuthorities(cocinero.getUsuario(), "cocinero");
		return cocineroRepo.save(cocinero);
		
	}
	
	@Transactional
	public void borrarCocinero(Integer id) {
		cocineroRepo.deleteById(id);
		
	}
	
	@Transactional
	public Optional<Cocinero> buscaCocineroPorId(Integer id) {
		return cocineroRepo.findById(id);
		
	}
}
