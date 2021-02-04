package org.springframework.samples.petclinic.validators;

import java.text.ParseException;
import java.util.Collection;
import java.util.Iterator;
import java.util.Locale;

import javax.validation.ConstraintValidator;
import javax.validation.ConstraintValidatorContext;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.samples.petclinic.model.Ingrediente;
import org.springframework.samples.petclinic.model.IngredientePedido;
import org.springframework.samples.petclinic.model.PlatoPedido;
import org.springframework.samples.petclinic.web.EstadoPlatoFormatter;

public class PlatoPedidoDisponibleValidator implements ConstraintValidator<ValidatePlatoPedidoDisponible, PlatoPedido>{

	@Autowired
	private EstadoPlatoFormatter estadoPlatoFormatter;
	@Override
	public boolean isValid(PlatoPedido platoPedido, ConstraintValidatorContext context) {
		Boolean res = false;
		Collection<IngredientePedido> lista = platoPedido.getIngredientesPedidos();
		Iterator<IngredientePedido> iterator = null;
		try {
			iterator = lista.iterator();
			while (iterator.hasNext()) {
			    IngredientePedido ing = iterator.next();
			    if((ing.getIngrediente().getProducto().getCantAct() >= ing.getCantidadPedida())||(platoPedido.getEstadoplato().getId().equals(3))||(platoPedido.getEstadoplato().getId().equals(1))) {
			    	res = true;
			    }
			}
		} catch (Exception e) {
			System.out.println(e.toString());
		}
		
		
		return res;
		
		
	}
}
