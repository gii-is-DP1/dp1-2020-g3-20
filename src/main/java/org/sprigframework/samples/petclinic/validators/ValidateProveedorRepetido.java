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
@Constraint(validatedBy = (ProveedorRepetidoValidator.class))
public @interface ValidateProveedorRepetido {
	String message() default "Proveedor añadido con la misma información";
	Class<?>[] groups() default {};
	Class<? extends Payload>[] payload() default {};
}
