package org.springframework.samples.petclinic.service;

import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.samples.petclinic.model.Menu;
import org.springframework.samples.petclinic.repository.MenuRepository;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
public class MenuService {

	@Autowired
	private MenuRepository menRep;
	
	@Transactional
	public int menuCount() {
		return (int) menRep.count();
		
	}
	

	@Transactional
	public Iterable<Menu> menuList() {
		return menRep.findAll();
		
	}

	@Transactional
	public Menu guardarMenu(Menu menu) {
		return menRep.save(menu);
		
	}
	
	@Transactional
	public void borrarMenu(Integer id) {
		menRep.deleteById(id);
		
	}
	
	@Transactional
	public Optional<Menu> buscaMenuPorId(Integer id) {
		return menRep.findById(id);
		
	}
}
