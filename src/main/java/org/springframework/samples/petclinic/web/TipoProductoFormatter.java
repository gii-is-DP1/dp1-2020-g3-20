package org.springframework.samples.petclinic.web;

import java.text.ParseException;
import java.util.Collection;
import java.util.Locale;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.format.Formatter;
import org.springframework.samples.petclinic.model.TipoProducto;
import org.springframework.samples.petclinic.service.ProductoService;
import org.springframework.stereotype.Component;

@Component
public class TipoProductoFormatter implements Formatter<TipoProducto>{
	
	private final ProductoService productoService;
	
	@Autowired
	public TipoProductoFormatter(ProductoService prodService) {
		this.productoService = prodService;
	}

	@Override
	public String print(TipoProducto tipoProducto, Locale locale) {
		return tipoProducto.getName();
	}

	@Override
	public TipoProducto parse(String text, Locale locale) throws ParseException {
		Collection<TipoProducto> encuentraTipoProducto = this.productoService.encontrarTiposProducto();
		for (TipoProducto tipo : encuentraTipoProducto) {
			if (tipo.getName().equals(text)) {
				return tipo;
			}
		}
		throw new ParseException("tipo no encontrado: " + text, 0);
	}
}
