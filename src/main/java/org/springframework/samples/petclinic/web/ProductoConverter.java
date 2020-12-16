package org.springframework.samples.petclinic.web;

import org.springframework.beans.BeanUtils;
import org.springframework.samples.petclinic.model.Producto;
import org.springframework.samples.petclinic.model.ProductoDTO;
import org.springframework.stereotype.Component;

@Component
public class ProductoConverter {
	public Producto covertDTOToEntity(ProductoDTO producto) {
		Producto aux = new Producto();
		BeanUtils.copyProperties(producto, aux);     //Obviar ids de relaciones en un futuro o establecer en null la relacion
		return aux;	
	}
	
}
