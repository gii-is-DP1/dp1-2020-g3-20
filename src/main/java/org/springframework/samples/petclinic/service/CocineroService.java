package org.springframework.samples.petclinic.service;

import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.samples.petclinic.model.Cocinero;
import org.springframework.samples.petclinic.model.User;
import org.springframework.samples.petclinic.repository.CocineroRepository;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import lombok.extern.slf4j.Slf4j;

@Slf4j
@Service
public class CocineroService {
	@Autowired
	private UserService userService;
	@Autowired
	private AuthoritiesService authoritiesService;
	@Autowired
	private CocineroRepository cocineroRepository;
	
	public CocineroService(UserService userService, AuthoritiesService authoritiesService,
			CocineroRepository cocineroRepository) {
		super();
		this.userService = userService;
		this.authoritiesService = authoritiesService;
		this.cocineroRepository = cocineroRepository;
	}

	@Transactional
	public Iterable<Cocinero> findAll() {
		return cocineroRepository.findAll();
	}
	
	@Transactional
	public Optional<Cocinero> findById(Integer id) {
		return cocineroRepository.findById(id);
	}

	@Transactional
	public Cocinero save(Cocinero cocinero) {
		// creating user
		User user = authoritiesService.crearUsuario(cocinero.getUsuario(), cocinero.getContrasena());
		userService.saveUser(user);
		// creating authorities
		authoritiesService.saveAuthorities(cocinero.getUsuario(), "cocinero");
		log.info(String.format("Chef with username %s has been saved", cocinero.getUsuario(),
				cocinero.getId()));
		return cocineroRepository.save(cocinero);
	}

	@Transactional
	public void deleteById(Integer id) {
		Cocinero cocinero = cocineroRepository.findById(id).get();
		cocineroRepository.deleteById(id);
		log.info(String.format("Chef with username %s has been deleted", cocinero.getUsuario()));
	}
}
