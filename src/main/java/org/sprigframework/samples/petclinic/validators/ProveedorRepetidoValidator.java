package org.sprigframework.samples.petclinic.validators;

import java.util.Collection;
import java.util.Iterator;

import javax.validation.ConstraintValidator;
import javax.validation.ConstraintValidatorContext;

import org.springframework.samples.petclinic.model.Ingrediente;
import org.springframework.samples.petclinic.model.PlatoPedido;
import org.springframework.samples.petclinic.model.Proveedor;

public class ProveedorRepetidoValidator implements ConstraintValidator<ValidateProveedorRepetido, Proveedor>{

	@Override
	public boolean isValid(Proveedor value, ConstraintValidatorContext context) {
		
		return false;
	}
}
