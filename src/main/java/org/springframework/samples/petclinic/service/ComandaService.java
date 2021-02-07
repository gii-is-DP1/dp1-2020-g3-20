package org.springframework.samples.petclinic.service;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.Collection;
import java.util.Iterator;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataAccessException;
import org.springframework.samples.petclinic.model.Comanda;
import org.springframework.samples.petclinic.repository.ComandaRepository;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
public class ComandaService {
	
	@Autowired
	private ComandaRepository comandaRepository;
	
	public ComandaService(ComandaRepository comandaRepository) {
		super();
		this.comandaRepository = comandaRepository;
	}

	@Transactional
	public int comandaCount() {
		return (int) comandaRepository.count();	
	}

	@Transactional
	public Iterable<Comanda> findAll() {
		return comandaRepository.findAll();
	}

	@Transactional
	public Comanda guardarComanda(Comanda comanda) {
		return comandaRepository.save(comanda);
	}
	
	@Transactional
	public Optional<Comanda> findById(Integer id) {
		return comandaRepository.findById(id);
	}
	
	@Transactional
	public Collection<Comanda> encontrarComandaDia(String dia) throws DataAccessException {
		LocalDate actualDate =LocalDate.parse(dia);
		Collection<Comanda> res = new ArrayList<>();
		Iterable<Comanda> aux = comandaRepository.findAll();
		Iterator<Comanda> it_aux = aux.iterator();
		while (it_aux.hasNext()) {
			Comanda comanda = it_aux.next();
			if (comanda.getFechaCreado().toLocalDate().equals(actualDate)) {
				res.add(comanda);
			}	
		}
		return res; 
	}
	
	@Transactional
	public Collection<Comanda> encontrarComandaActual() throws DataAccessException {
		Collection<Comanda> res = new ArrayList<>();
		Iterable<Comanda> aux = comandaRepository.findAll();
		Iterator<Comanda> it_aux = aux.iterator();
		while (it_aux.hasNext()) {
			Comanda comAux = it_aux.next();
			if (comAux.getFechaFinalizado()==null) {
				res.add(comAux);
			}	
		}
		return res; 
	}
	
	public Integer findLastId() throws DataAccessException{
		return comandaRepository.findLastId();
	}
}
