package org.springframework.samples.petclinic.repository;

import org.springframework.data.repository.CrudRepository;
import org.springframework.samples.petclinic.model.Menu;


public interface MenuRepository extends CrudRepository<Menu, Integer>{

}
