package org.sprigframework.samples.petclinic.validators;

import java.util.Collection;
import java.util.Iterator;

import javax.validation.ConstraintValidator;
import javax.validation.ConstraintValidatorContext;

import org.springframework.samples.petclinic.model.Ingrediente;
import org.springframework.samples.petclinic.model.PlatoPedido;

public class PlatoPedidoDisponibleValidator implements ConstraintValidator<ValidatePlatoPedidoDisponible, PlatoPedido>{

	@Override
	public boolean isValid(PlatoPedido platoPedido, ConstraintValidatorContext context) {
		Boolean res = false;
		Collection<Ingrediente> lista = platoPedido.getPlato().getIngredientes();
		Iterator<Ingrediente> iterator = lista.iterator();
		while (iterator.hasNext()) {
		    Ingrediente ing = iterator.next();
		    if(ing.getProducto().getCantAct() >= ing.getProducto().getCantMin()) {
		    	res = true;
		    }
		}
		return res;
		
		
	}
}
