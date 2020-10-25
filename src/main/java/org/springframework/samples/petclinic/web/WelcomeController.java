package org.springframework.samples.petclinic.web;

import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.samples.petclinic.repository.UserRepository;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.samples.petclinic.model.Person;
import org.springframework.samples.petclinic.model.User;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

import java.util.ArrayList;
import java.util.List;

@Controller
public class WelcomeController {
	
	
	  @GetMapping({"/","/welcome"})
	  public String welcome(Map<String, Object> model) {	    
		  List<Person> people= new ArrayList<Person>();
		  Person person1= new Person();
		  Person person2= new Person();
		  Person person3= new Person();
		  Person person4= new Person();
		  Person person5= new Person();
		  person1.setFirstName("Alexander");
		  person1.setLastName(" Sanchez Hossdorf");
		  person2.setFirstName("Jose Manuel");
		  person2.setLastName(" Tabares Rodriguez");
		  person3.setFirstName("Horacio");
		  person3.setLastName(" Garcia Lerco");
		  person4.setFirstName("Victor");
		  person4.setLastName(" Monteseirin Puig");
		  person5.setFirstName("Fernando");
		  person5.setLastName(" Angulo Moruno");
		  people.add(person1);
		  people.add(person2);
		  people.add(person3);
		  people.add(person4);
		  people.add(person5);
		  model.put("people", people);
		  model.put("title", "ProyectoDP");
		  model.put("group", "Cursores");
		  
	    return "welcome";
	  }
}
