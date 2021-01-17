package org.springframework.samples.petclinic.web;



import java.util.Optional;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.samples.petclinic.model.Menu;
import org.springframework.samples.petclinic.service.MenuService;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping(value = "/menus")
public class MenuController {
	
	@Autowired
	private MenuService menuService;
	
	@GetMapping()
	public String listadoMenus(ModelMap modelMap) {
		String vista= "menus/listaMenus";
		Iterable<Menu> menus=  menuService.menuList();
		modelMap.addAttribute("menus",menus);
		return vista;
		
	}
	
	@GetMapping(path="/new")
	public String crearMenu(ModelMap modelMap) {
		String vista= "menus/editMenu";
		modelMap.addAttribute("menu",new Menu());
		return vista;
	}
	
	@PostMapping(path="/save")
	public String guardarMenu(@Valid Menu menu,BindingResult result,ModelMap modelMap) {
		String vista= "menus/listaMenus";
		if(result.hasErrors()) {
			modelMap.addAttribute("menu", menu);
			return "menus/editMenu";
		}else {
			menuService.guardarMenu(menu);
			modelMap.addAttribute("message", "Guardado Correctamente");
			vista=listadoMenus(modelMap);
		}
		return vista;
		
	}
	@GetMapping(path="/delete/{menuId}")
	public String borrarMenu(@PathVariable("menuId") int menuId, ModelMap modelMap) {
		String vista= "menus/listaMenus";
		Optional<Menu> cam= menuService.buscaMenuPorId(menuId);
		if(cam.isPresent()) {
			menuService.borrarMenu(menuId);
			modelMap.addAttribute("message", "Borrado Correctamente");
		}else {
			modelMap.addAttribute("message", "Menu no Encontrado");
			vista=listadoMenus(modelMap);
		}
		return vista;
		
	}
	
	
}
