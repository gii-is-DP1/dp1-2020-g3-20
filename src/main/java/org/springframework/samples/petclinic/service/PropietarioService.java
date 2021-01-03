package org.springframework.samples.petclinic.service;

import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.samples.petclinic.model.Camarero;
import org.springframework.samples.petclinic.model.Propietario;
import org.springframework.samples.petclinic.model.User;
import org.springframework.samples.petclinic.repository.PropietarioRepository;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
public class PropietarioService {
	@Autowired
	private PropietarioRepository propRepo;
	
	@Autowired
	private AuthoritiesService authoritiesService;
	
	@Autowired
	private UserService userService;
	
	@Transactional
	public int propietarioCount() {
		return (int) propRepo.count();
		
	}
	@Transactional
	public Iterable<Propietario> listPropietario() {
		return propRepo.findAll();
		
	}
	@Transactional
	public void delete(final Propietario propietario) {
		this.propRepo.delete(propietario);
	}
	@Transactional
	public Propietario guardarPropietario(Propietario propietario) {
		//creating user
				User user=authoritiesService.crearUsuario(propietario.getUsuario(), propietario.getContrasena());
				userService.saveUser(user);
				//creating authorities
				authoritiesService.saveAuthorities(propietario.getUsuario(), "propietario");
		return propRepo.save(propietario);
	}
	
	@Transactional
	public Optional<Propietario> buscaPropietarioPorId(Integer id) {
		return propRepo.findById(id);
		
	}

}
