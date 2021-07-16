package org.springframework.samples.petclinic.service;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataAccessException;
import org.springframework.samples.petclinic.model.Manager;
import org.springframework.samples.petclinic.model.User;
import org.springframework.samples.petclinic.repository.ManagerRepository;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.validation.BeanPropertyBindingResult;
import org.springframework.validation.BindingResult;
import org.springframework.validation.FieldError;

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
	public int managerCount() {
		return (int) managerRepository.count();
	}

	@Transactional
	public Iterable<Manager> managerList() {
		return managerRepository.findAll();
	}

	@Transactional
	public Manager guardarManager(Manager manager) {
		User user = authoritiesService.crearUsuario(manager.getUsuario(), manager.getContrasena());
		// creating user
		if(manager.getId()!=null) {	
			String antiguo = this.managerRepository.findById(manager.getId()).get().getUsuario();
			if(!user.getUsername().equals(antiguo)) {
				userService.deleteUser(userService.findUser(antiguo).get());
			}
		}	
		userService.saveUser(user);
		// creating authorities
		authoritiesService.saveAuthorities(manager.getUsuario(), "manager");
		log.info(String.format("Manager with username %s been save correctly", manager.getUsuario(),
				manager.getId()));
		return managerRepository.save(manager);
	}

	@Transactional
	public void borrarManager(Integer id) {
		Manager manager = managerRepository.findById(id).get();
		this.userService.deleteUser(this.userService.findUser(manager.getUsuario()).get());
		managerRepository.deleteById(id);
		log.info(String.format("Manager with username %s has been deleted", manager.getUsuario(),
				manager.getId()));
	}

	@Transactional
	public Optional<Manager> buscaManagerPorId(Integer id) {
		return managerRepository.findById(id);
	}
	
	@Transactional
	public Boolean managerConMismoUsuario(Manager manager) throws DataAccessException {
		Boolean res=false;
		Integer managerId= manager.getId();
		Manager comp=this.managerRepository.findById(managerId).get();
		if(comp.getUsuario().equals(manager.getUsuario())) {
			res=true;
		}
		return res;
	}
	
	@Transactional
	public BindingResult erroresSinMismoUser(Manager manager,BindingResult result) throws DataAccessException {
		List<FieldError> errorsToKeep = result.getFieldErrors().stream()
                .filter(fer -> !fer.getField().equals("usuario"))
                .collect(Collectors.toList());
		
		 result = new BeanPropertyBindingResult(manager, "manager");

	        for (FieldError fieldError : errorsToKeep) {
	            result.addError(fieldError);
	        }
			return result;
	}

}
