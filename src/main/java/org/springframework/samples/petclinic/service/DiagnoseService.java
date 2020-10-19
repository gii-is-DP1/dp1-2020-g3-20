package org.springframework.samples.petclinic.service;

import java.util.Collection;
import java.util.HashMap;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.samples.petclinic.model.Diagnose;
import org.springframework.samples.petclinic.model.Pet;
import org.springframework.samples.petclinic.model.Visit;
import org.springframework.samples.petclinic.repository.DiagnoseRepository;
import org.springframework.stereotype.Service;

@Service
public class DiagnoseService {
	@Autowired
	private DiagnoseRepository repo;
	
	
	public Collection<Diagnose> findAll(){
		return repo.findAll();
	}
	
	public Collection<Diagnose> findByPetId(int petId){
		return repo.findByPetId(petId);
	}
	
	public Map<Visit,Diagnose> findByPets(Collection<Pet> pets){
		Map<Visit,Diagnose> diagnoses=new HashMap<Visit, Diagnose>();		
		for(Pet pet:pets) {
			for(Diagnose diagnose:findByPetId(pet.getId()))
				diagnoses.put(diagnose.getVisit(), diagnose);
		}
		return diagnoses;
	}
	
	public void save(Diagnose diagnose) {
		repo.save(diagnose);
	}

}
