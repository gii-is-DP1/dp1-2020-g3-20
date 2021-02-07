package org.springframework.samples.petclinic.service;

import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.samples.petclinic.model.Propietario;
import org.springframework.samples.petclinic.model.User;
import org.springframework.samples.petclinic.repository.PropietarioRepository;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

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
	public int propietarioCount() {
		return (int) propietarioRepository.count();
		
	}
	@Transactional
	public Iterable<Propietario> listPropietario() {
		return propietarioRepository.findAll();
		
	}
	@Transactional
	public void delete(final Propietario propietario) {
		this.propietarioRepository.delete(propietario);
	}
	@Transactional
	public Propietario guardarPropietario(Propietario propietario) {
		//creating user
				User user=authoritiesService.crearUsuario(propietario.getUsuario(), propietario.getContrasena());
				userService.saveUser(user);
				//creating authorities
				authoritiesService.saveAuthorities(propietario.getUsuario(), "propietario");
		return propietarioRepository.save(propietario);
	}
	
	@Transactional
	public Optional<Propietario> buscaPropietarioPorId(Integer id) {
		return propietarioRepository.findById(id);
		
	}

}
