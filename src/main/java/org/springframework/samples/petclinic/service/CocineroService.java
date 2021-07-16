package org.springframework.samples.petclinic.service;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataAccessException;
import org.springframework.samples.petclinic.model.Cocinero;
import org.springframework.samples.petclinic.model.User;
import org.springframework.samples.petclinic.repository.CocineroRepository;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.validation.BeanPropertyBindingResult;
import org.springframework.validation.BindingResult;
import org.springframework.validation.FieldError;

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
	public int cocineroCount() {
		return (int) cocineroRepository.count();
	}

	@Transactional
	public Iterable<Cocinero> findAll() {
		return cocineroRepository.findAll();

	}

	@Transactional
	public Cocinero guardarCocinero(Cocinero cocinero) {
		// creating user
		User user = authoritiesService.crearUsuario(cocinero.getUsuario(), cocinero.getContrasena());
		if(cocinero.getId()!=null) {	
			String antiguo = this.cocineroRepository.findById(cocinero.getId()).get().getUsuario();
			if(!user.getUsername().equals(antiguo)) {
				userService.deleteUser(userService.findUser(antiguo).get());
			}
		}
		userService.saveUser(user);
		// creating authorities
		authoritiesService.saveAuthorities(cocinero.getUsuario(), "cocinero");
		log.info(String.format("Chef with username %s has been saved", cocinero.getUsuario(),
				cocinero.getId()));
		return cocineroRepository.save(cocinero);

	}

	@Transactional
	public void borrarCocinero(Integer id) {
		Cocinero cocinero = cocineroRepository.findCocineroById(id);
		this.userService.deleteUser(this.userService.findUser(cocinero.getUsuario()).get());
		cocineroRepository.deleteById(id);
		log.info(String.format("Chef with username %s has been deleted", cocinero.getUsuario(),
				cocinero.getId()));
	}

	@Transactional
	public Optional<Cocinero> findById(Integer id) {
		return cocineroRepository.findById(id);
	}

	@Transactional
	public Cocinero findCamereroById(int cocineroId) throws DataAccessException {
		return this.cocineroRepository.findCocineroById(cocineroId);
	}
	
	@Transactional
	public Boolean cocineroConMismoUsuario(Cocinero cocinero ) throws DataAccessException {
		Boolean res=false;
		Integer cocineroId= cocinero.getId();
		Cocinero comp=this.cocineroRepository.findCocineroById(cocineroId);
		if(comp.getUsuario().equals(cocinero.getUsuario())) {
			res=true;
		}
		return res;
	}
	
	@Transactional
	public BindingResult erroresSinMismoUser(Cocinero cocinero,BindingResult result) throws DataAccessException {
		List<FieldError> errorsToKeep = result.getFieldErrors().stream()
                .filter(fer -> !fer.getField().equals("usuario"))
                .collect(Collectors.toList());
		
		 result = new BeanPropertyBindingResult(cocinero, "cocinero");

	        for (FieldError fieldError : errorsToKeep) {
	            result.addError(fieldError);
	        }
			return result;
	}
}
