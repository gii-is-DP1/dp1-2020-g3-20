package org.springframework.samples.petclinic.web;

import java.util.Optional;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.samples.petclinic.model.Diagnose;
import org.springframework.samples.petclinic.model.Owner;
import org.springframework.samples.petclinic.model.Pet;
import org.springframework.samples.petclinic.model.Visit;
import org.springframework.samples.petclinic.service.DiagnoseService;
import org.springframework.samples.petclinic.service.DiseaseService;
import org.springframework.samples.petclinic.service.PetService;
import org.springframework.samples.petclinic.service.VetService;
import org.springframework.samples.petclinic.service.VisitService;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

@Controller
@RequestMapping()
public class DiagnoseController {

	@Autowired
	VisitService visitService;
	
	@Autowired
	DiseaseService diseaseService;
	
	@Autowired
	DiagnoseService diagnoseService;
	
	@Autowired
	VetService vetService;
	
	@Autowired
	PetService petService;
	
	@GetMapping(path = "/visits/{visitId}/diagnoses/new")
	public String createDiagnose(@PathVariable("visitId")int visitId,ModelMap model)
	{
		Optional<Visit> visit=visitService.findById(visitId);
		if(visit.isPresent()) {
			Diagnose diagnose=new Diagnose();
			diagnose.setVisit(visit.get());
			model.addAttribute("visit",visit.get());
			model.addAttribute("diseases",diseaseService.findAll());
			model.addAttribute("vets",vetService.findVets());
			model.addAttribute("diagnose",diagnose);
			return "diagnoses/CreateOrUpdateDiagnoseForm";
		}else {
			model.addAttribute("message","Visit with id "+visitId+" not found!");
			model.addAttribute("messageType","warning");
			return "redirect:/welcome";
		}		
	}
	
	@PostMapping(path = "/visits/{visitId}/diagnoses/new")
	public String createDiagnose(@PathVariable("visitId")int visitId, @RequestParam("vet") int vetId,@RequestParam("disease") int diseaseId, @RequestParam("description") String description, ModelMap model) {
		Optional<Visit> visit=visitService.findById(visitId);
		if(visit.isPresent()) {
			Diagnose diagnose=new Diagnose();
			diagnose.setDescription(description);
			diagnose.setDisease(diseaseService.findById(diseaseId).get());
			diagnose.setVet(vetService.findById(vetId).get());
			diagnose.setVisit(visit.get());
			diagnoseService.save(diagnose);
			Pet pet=visit.get().getPet();
			Owner owner=pet.getOwner();			
			return "redirect:/owners/"+owner.getId()+"/pets/"+pet.getId()+"/history";
		}else {
			return "redirect:/";
		}
	}
	
	@GetMapping(value="/owners/{ownerId}/pets/{petId}/history")
    public String getPetClinicHistory(@PathVariable("petId") int petId, ModelMap model) {
    	model.addAttribute("diagnoses",diagnoseService.findByPetId(petId));
    	model.addAttribute("pet",petService.findPetById(petId));
    	return "pets/History";
    }
}
