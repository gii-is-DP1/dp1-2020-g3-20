package org.springframework.samples.petclinic.service;

import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.samples.petclinic.model.Manager;
import org.springframework.samples.petclinic.model.User;
import org.springframework.samples.petclinic.repository.ManagerRepository;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
public class ManagerService {
	
	@Autowired
	private AuthoritiesService authoritiesService;
	
	@Autowired 
	private UserService userService;
	
	@Autowired
	private ManagerRepository managerRepository;
	
	@Transactional
	public int managerCount() {
		return (int) managerRepository.count();
	}
	
	@Transactional
	public Iterable<Manager> managerList(){
		return managerRepository.findAll();
	}

	@Transactional
	public Manager guardarManager (Manager manager) {
		//creating user
				User user=authoritiesService.crearUsuario(manager.getUsuario(), manager.getContrasena());
				userService.saveUser(user);
				//creating authorities
				authoritiesService.saveAuthorities(manager.getUsuario(), "manager");
		return managerRepository.save(manager);
	}
	
	@Transactional
	public void borrarManager(Integer id) {
		managerRepository.deleteById(id);
	}
	
	@Transactional
	public Optional<Manager> buscaManagerPorId (Integer id){
		return managerRepository.findById(id);
	}
	
}
