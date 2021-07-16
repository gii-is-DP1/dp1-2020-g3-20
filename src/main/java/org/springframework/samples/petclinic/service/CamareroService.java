package org.springframework.samples.petclinic.service;

import java.util.Iterator;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataAccessException;
import org.springframework.samples.petclinic.model.Camarero;
import org.springframework.samples.petclinic.model.User;
import org.springframework.samples.petclinic.repository.CamareroRepository;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import lombok.extern.slf4j.Slf4j;

@Slf4j
@Service
public class CamareroService {
	@Autowired
	private UserService userService;
	@Autowired
	private CamareroRepository camareroRepository;
	@Autowired
	private AuthoritiesService authoritiesService;
	
	public CamareroService(UserService userService, CamareroRepository camareroRepository,
			AuthoritiesService authoritiesService) {
		super();
		this.userService = userService;
		this.camareroRepository = camareroRepository;
		this.authoritiesService = authoritiesService;
	}

	@Transactional
	public Iterable<Camarero> findAll() {
		return camareroRepository.findAll();
	}
	
	@Transactional
	public Optional<Camarero> findById(int camareroId) throws DataAccessException {
		return this.camareroRepository.findById(camareroId);
	}

	@Transactional
	public void save(Camarero camarero) {
		User user = authoritiesService.crearUsuario(camarero.getUsuario(), camarero.getContrasena());
		userService.saveUser(user);
		authoritiesService.saveAuthorities(camarero.getUsuario(), "camarero");
		camareroRepository.save(camarero);
		log.info(String.format("Waiter with username %s has been saved", camarero.getUsuario(),
				camarero.getId()));
	}

	@Transactional
	public void deleteById(Integer id) {
		Camarero camarero = camareroRepository.findById(id).get();
		camareroRepository.deleteById(id);
		log.info(String.format("Waiter with username %s has been deleted", camarero.getUsuario()));
	}

	// Se usa para asignar un camarero a una comanda dado su usario
	@Transactional
	public Camarero findByUser(String user) {
		Camarero camarero = new Camarero();
		Iterable<Camarero> aux = camareroRepository.findAll();
		Iterator<Camarero> it = aux.iterator();
		while (it.hasNext()) {
			camarero = it.next();
			if (camarero.getUsuario().equals(user))
				return camarero;
		}
		return camarero;
	}
}