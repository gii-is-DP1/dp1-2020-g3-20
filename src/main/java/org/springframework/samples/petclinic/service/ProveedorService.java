package org.springframework.samples.petclinic.service;


import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataAccessException;
import org.springframework.samples.petclinic.model.Pedido;
import org.springframework.samples.petclinic.model.Proveedor;
import org.springframework.samples.petclinic.repository.PedidoRepository;
import org.springframework.samples.petclinic.repository.ProveedorRepository;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
public class ProveedorService {
	
	private ProveedorRepository provRepo;
	private PedidoRepository pediRepo;

	@Autowired
	public ProveedorService(ProveedorRepository provRepo,
			PedidoRepository pediRepo) {
		this.provRepo = provRepo;
		this.pediRepo = pediRepo;
	}
	
	
	@Transactional
	public int Provercount(){
		return (int) provRepo.count();
	}
	@Transactional
	public Iterable<Proveedor> findAll(){
		return provRepo.findAll();
	}

	@Transactional
	public void save(Proveedor proveedor) {
		 provRepo.save(proveedor);
	}
	
	@Transactional
	public void delete(Proveedor proveedor) {
		provRepo.delete(proveedor);
		
	}
	
	@Transactional
	public Optional<Proveedor> provedroporid(Integer id) {
		return provRepo.findById(id);
		
	}
	
	@Transactional
	public void savePedido(Pedido pedido) throws DataAccessException {
		pediRepo.save(pedido);
	}
	
	@Transactional
	public Iterable<Pedido> findAllPedido(){
		return pediRepo.findAll();
	}
	
	public Iterable<Pedido> findPedidoByProveedorId(int proveedorID) {
		
		return pediRepo.findByProveedorId(proveedorID);
	}
}
	
