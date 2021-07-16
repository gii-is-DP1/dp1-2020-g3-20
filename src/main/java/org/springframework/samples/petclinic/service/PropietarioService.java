package org.springframework.samples.petclinic.service;

import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.samples.petclinic.model.Propietario;
import org.springframework.samples.petclinic.model.User;
import org.springframework.samples.petclinic.repository.PropietarioRepository;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import lombok.extern.slf4j.Slf4j;

@Slf4j
@Service
public class PropietarioService {
	@Autowired
	private PropietarioRepository propietarioRepository;
	@Autowired
	private AuthoritiesService authoritiesService;
	@Autowired
	private UserService userService;
	
	public PropietarioService(PropietarioRepository propietarioRepository, AuthoritiesService authoritiesService,
			UserService userService) {
		super();
		this.propietarioRepository = propietarioRepository;
		this.authoritiesService = authoritiesService;
		this.userService = userService;
	}

	@Transactional
	public Iterable<Propietario> findAll() {
		return propietarioRepository.findAll();
	}
	
	@Transactional
	public Optional<Propietario> findById(Integer id) {
		return propietarioRepository.findById(id);
	}

	@Transactional
	public int count() {
		return (int) propietarioRepository.count();
	}

	@Transactional
	public void deleteById(Integer id) {
		Propietario propietario = propietarioRepository.findById(id).get();
		this.propietarioRepository.deleteById(id);
		log.info(String.format("Owner with name %s has been deleted", propietario.getName()));
	}

	@Transactional
	public Propietario save(Propietario propietario) {
		// creating user
		User user = authoritiesService.crearUsuario(propietario.getUsuario(), propietario.getContrasena());
		userService.saveUser(user);
		// creating authorities
		authoritiesService.saveAuthorities(propietario.getUsuario(), "propietario");
		log.info(String.format("Owner with name %s has been saved", propietario.getName()));
		return propietarioRepository.save(propietario);
	}
}
