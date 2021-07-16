package org.springframework.samples.petclinic.service;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataAccessException;
import org.springframework.samples.petclinic.model.Propietario;
import org.springframework.samples.petclinic.model.User;
import org.springframework.samples.petclinic.repository.PropietarioRepository;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.validation.BeanPropertyBindingResult;
import org.springframework.validation.BindingResult;
import org.springframework.validation.FieldError;

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
	public int propietarioCount() {
		return (int) propietarioRepository.count();

	}

	@Transactional
	public Iterable<Propietario> listPropietario() {
		return propietarioRepository.findAll();

	}

	@Transactional
	public void delete(final Propietario propietario) {
		this.userService.deleteUser(this.userService.findUser(propietario.getUsuario()).get());
		log.info(String.format("Owner with name %s has been deleted", propietario.getName()));
		this.propietarioRepository.delete(propietario);
	}

	@Transactional
	public Propietario guardarPropietario(Propietario propietario) {
		// creating user
		User user = authoritiesService.crearUsuario(propietario.getUsuario(), propietario.getContrasena());
		userService.saveUser(user);
		// creating authorities
		authoritiesService.saveAuthorities(propietario.getUsuario(), "propietario");
		log.info(String.format("Owner with name %s has been saved", propietario.getName()));
		return propietarioRepository.save(propietario);
	}

	@Transactional
	public Optional<Propietario> buscaPropietarioPorId(Integer id) {
		return propietarioRepository.findById(id);

	}
	
	@Transactional
	public Boolean propietarioConMismoUsuario(Propietario propietario) throws DataAccessException {
		Boolean res=false;
		Integer propietarioId= propietario.getId();
		Propietario comp=this.propietarioRepository.findById(propietarioId).get();
		if(comp.getUsuario().equals(propietario.getUsuario())) {
			res=true;
		}
		return res;
		
	}
	
	@Transactional
	public BindingResult erroresSinMismoUser(Propietario propietario,BindingResult result) throws DataAccessException {
		List<FieldError> errorsToKeep = result.getFieldErrors().stream()
                .filter(fer -> !fer.getField().equals("usuario"))
                .collect(Collectors.toList());
		
		 result = new BeanPropertyBindingResult(propietario, "propietario");

	        for (FieldError fieldError : errorsToKeep) {
	            result.addError(fieldError);
	        }
			return result;
	}

}
