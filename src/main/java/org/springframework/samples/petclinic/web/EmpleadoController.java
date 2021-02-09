package org.springframework.samples.petclinic.web;

import java.util.Map;



import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import java.util.ArrayList;
import java.util.List;

@Controller
@RequestMapping(value = "/empleados")
public class EmpleadoController {
	
	
	  @GetMapping()
	  public String empleadoVista(ModelMap modelMap) {	   
		  String vista= "empleados/empleados";
		  
	    return vista;
	  }
}
