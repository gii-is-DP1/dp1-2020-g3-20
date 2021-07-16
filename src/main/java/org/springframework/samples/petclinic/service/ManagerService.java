package org.springframework.samples.petclinic.service;

import java.util.Optional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.samples.petclinic.model.Manager;
import org.springframework.samples.petclinic.model.User;
import org.springframework.samples.petclinic.repository.ManagerRepository;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import lombok.extern.slf4j.Slf4j;

@Slf4j
@Service
public class ManagerService {
	@Autowired
	private AuthoritiesService authoritiesService;
	@Autowired
	private UserService userService;
	@Autowired
	private ManagerRepository managerRepository;
	
	public ManagerService(AuthoritiesService authoritiesService, UserService userService,
			ManagerRepository managerRepository) {
		super();
		this.authoritiesService = authoritiesService;
		this.userService = userService;
		this.managerRepository = managerRepository;
	}

	@Transactional
	public Iterable<Manager> findAll() {
		return managerRepository.findAll();
	}
	
	@Transactional
	public Optional<Manager> findById(Integer id) {
		return managerRepository.findById(id);
	}

	@Transactional
	public Manager save(Manager manager) {
		// creating user
		User user = authoritiesService.crearUsuario(manager.getUsuario(), manager.getContrasena());
		userService.saveUser(user);
		// creating authorities
		authoritiesService.saveAuthorities(manager.getUsuario(), "manager");
		log.info(String.format("Manager with username %s been save correctly", manager.getUsuario(),
				manager.getId()));
		return managerRepository.save(manager);
	}

	@Transactional
	public void deleteById(Integer id) {
		Manager manager = managerRepository.findById(id).get();
		managerRepository.deleteById(id);
		log.info(String.format("Manager with username %s has been deleted", manager.getUsuario()));
	}
}
