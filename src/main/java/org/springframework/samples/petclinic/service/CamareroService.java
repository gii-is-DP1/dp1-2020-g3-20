package org.springframework.samples.petclinic.service;

import java.util.Iterator;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataAccessException;
import org.springframework.samples.petclinic.model.Camarero;
import org.springframework.samples.petclinic.model.User;
import org.springframework.samples.petclinic.repository.CamareroRepository;
import org.springframework.samples.petclinic.service.exceptions.DuplicatedPedidoException;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
public class CamareroService {
	@Autowired
	private UserService userService;
	
	@Autowired
	private CamareroRepository camareroRepository;
	
	@Autowired
	private AuthoritiesService authoritiesService;
	
	@Transactional
	public int camareroCount() {
		return (int) camareroRepository.count();
		
	}
	

	@Transactional
	public Iterable<Camarero> camareroList() {
		return camareroRepository.findAll();
		
	}

	@Transactional(rollbackFor = DuplicatedPedidoException.class)
	public void guardarCamarero(Camarero camarero) throws DuplicatedPedidoException {
		//creating user
		Iterable<Camarero> lista = camareroRepository.findAll();
		Iterator<Camarero> it = lista.iterator();
       	Boolean Hayrepetido = false;
       	while(it.hasNext()) {
       		Camarero c = it.next();
			if (c.getUsuario()==camarero.getUsuario()) {
				Hayrepetido = true;
			}	
       	if (Hayrepetido)  {    
       	throw new DuplicatedPedidoException();
    }
       	else {
		User user=authoritiesService.crearUsuario(camarero.getUsuario(), camarero.getContrasena());
		userService.saveUser(user);
		//creating authorities
		authoritiesService.saveAuthorities(camarero.getUsuario(), "camarero");
		camareroRepository.save(camarero);
		 }}
       	
	}
	
	@Transactional
	public void borrarCamarero(Integer id) {
		camareroRepository.deleteById(id);
		
	}
	
	@Transactional
	public Optional<Camarero> buscaCamareroPorId(Integer id) {
		return camareroRepository.findById(id);
	}
	
	//Se usa para asignar un camarero a una comanda dado su usario
	@Transactional
	public Camarero buscaCamareroPorUser(String user) {
		Camarero camarero = new Camarero();
		Iterable<Camarero> aux = camareroRepository.findAll();
		Iterator<Camarero> it = aux.iterator();
		while(it.hasNext()) {
			camarero = it.next();
			if(camarero.getUsuario().equals(user))
				return camarero;
		}
		return camarero;
	}
	@Transactional
	public Camarero findCamereroById(int camareroId) throws DataAccessException {
		return this.camareroRepository.findCamareroById(camareroId);
	}
}
