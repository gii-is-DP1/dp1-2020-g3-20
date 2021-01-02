package org.springframework.samples.petclinic.web;

import java.text.ParseException;
import java.util.Collection;
import java.util.Locale;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.format.Formatter;
import org.springframework.samples.petclinic.model.EstadoPlato;
import org.springframework.samples.petclinic.service.PlatoPedidoService;
import org.springframework.stereotype.Component;

@Component
public class EstadoPlatoFormatter implements Formatter<EstadoPlato>{
	private final PlatoPedidoService ppService;
	
	@Autowired
	public EstadoPlatoFormatter(PlatoPedidoService ppedidoService) {
		this.ppService = ppedidoService;
	}

	@Override
	public String print(EstadoPlato estadoPlato, Locale locale) {
		return estadoPlato.getName();
	}

	@Override
	public EstadoPlato parse(String text, Locale locale) throws ParseException {
		Collection<EstadoPlato> encuentraEstadoPlato = this.ppService.encontrarEstadoPlato();
		for (EstadoPlato tipo : encuentraEstadoPlato) {
			if (tipo.getName().equals(text)) {
				return tipo;
			}
		}
		throw new ParseException("tipo no encontrado: " + text, 0);
	}
}
