package org.sprigframework.samples.petclinic.validators;

import java.lang.annotation.Documented;
import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

import javax.validation.Constraint;
import javax.validation.Payload;

@Documented
@Target(ElementType.TYPE)
@Retention(RetentionPolicy.RUNTIME)
@Constraint(validatedBy = (PlatoPedidoDisponibleValidator.class))
public @interface ValidatePlatoPedidoDisponible {
	String message() default "Faltan ingredientes de este plato, no se puede servir";
	Class<?>[] groups() default {};
	Class<? extends Payload>[] payload() default {};

}
