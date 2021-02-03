package org.springframework.samples.petclinic.web;

import java.text.ParseException;
import java.util.Collection;
import java.util.Locale;

import org.springframework.format.Formatter;
import org.springframework.samples.petclinic.model.Producto;
import org.springframework.samples.petclinic.service.ProductoService;
import org.springframework.stereotype.Component;

@Component
public class ProductoFormatter implements Formatter<Producto>{
	
	private ProductoService prodService;

	public ProductoFormatter(ProductoService prodService) {
		this.prodService = prodService;
	}

	@Override
	public String print(Producto producto, Locale locale) {
		return producto.getName();
	}

	@Override
	public Producto parse(String text, Locale locale) throws ParseException {
		Iterable<Producto> encuentraProducto = this.prodService.productoList();
		for (Producto p : encuentraProducto) {
			if (p.getName().equals(text)) {
				return p;
			}
		}
		throw new ParseException("tipo no encontrado: " + text, 0);
	}

}
