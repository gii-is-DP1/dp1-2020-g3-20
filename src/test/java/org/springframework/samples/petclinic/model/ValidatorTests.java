package org.springframework.samples.petclinic.model;

import static org.assertj.core.api.Assertions.assertThat;

import java.util.Locale;
import java.util.Set;

import javax.validation.ConstraintViolation;
import javax.validation.Validator;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.context.i18n.LocaleContextHolder;
import org.springframework.validation.beanvalidation.LocalValidatorFactoryBean;

/**
 * @author Michael Isvy Simple test to make sure that Bean Validation is working (useful
 * when upgrading to a new version of Hibernate Validator/ Bean Validation)
 */
class ValidatorTests {

	private Validator createValidator() {
		LocalValidatorFactoryBean localValidatorFactoryBean = new LocalValidatorFactoryBean();
		localValidatorFactoryBean.afterPropertiesSet();
		return localValidatorFactoryBean;
	}

	@Test
	void shouldNotValidateWhenFirstNameEmpty() {

		LocaleContextHolder.setLocale(Locale.ENGLISH);
		Persona persona = new Persona();
		persona.setName("");
		persona.setApellido("smith");

		Validator validator = createValidator();
		Set<ConstraintViolation<Persona>> constraintViolations = validator.validate(persona);

		assertThat(constraintViolations.size()).isEqualTo(1);
		ConstraintViolation<Persona> violation = constraintViolations.iterator().next();
		assertThat(violation.getPropertyPath().toString()).isEqualTo("name");
		assertThat(violation.getMessage()).isEqualTo("must not be empty");
	}
	
	// TESTS PARA Propietario -----------------------------------------------------------------------
	
		@Test
		@DisplayName("Validar una Propietario Correcto")
		void shouldNotValidateReviewWhenValorationIncorrect() {

			LocaleContextHolder.setLocale(Locale.ENGLISH);
			Propietario propietario = new Propietario();
			propietario.setName("Jose");;
			propietario.setApellido("Alves");
			propietario.setTelefono("600234321");
			propietario.setGmail("abdche@gmail.com");
			propietario.setContrasena("12345678");

			Validator validator = createValidator();
			Set<ConstraintViolation<Propietario>> constraintViolations = validator.validate(propietario);

			assertThat(constraintViolations.size()).isEqualTo(0);
				
			}
		@Test
		@DisplayName("Validar un propietario incorrecto")
		void shouldNotValidateWhenAllFieldsIncorrect() {

			Propietario propietario = new Propietario();
			propietario.setName("");
			propietario.setApellido("");
			propietario.setTelefono("");
			propietario.setGmail("");
			propietario.setContrasena("");

			Validator validator = createValidator();
			Set<ConstraintViolation<Propietario>> constraintViolations = validator.validate(propietario);
			assertThat(constraintViolations.size()).isEqualTo(5);
			for (ConstraintViolation<Propietario> d : constraintViolations) {
				if (d.getPropertyPath().toString().equals("name")) {
					assertThat(d.getMessage()).isEqualTo("must not be empty");
				}
				if (d.getPropertyPath().toString().equals("apellido")) {
					assertThat(d.getMessage()).isEqualTo("size must be between 3 and 50");
				}
				if (d.getPropertyPath().toString().equals("gmail")) {
					assertThat(d.getMessage()).isEqualTo("size must be between 3 and 50");
				}
				if (d.getPropertyPath().toString().equals("telefono")) {
					assertThat(d.getMessage()).isEqualTo("size must be between 3 and 50");
				}
				if (d.getPropertyPath().toString().equals("contraseña")) {
					assertThat(d.getMessage()).isEqualTo("size must be between 3 and 50");
				}

			}

		}
		// TESTS PARA Platos -----------------------------------------------------------------------
		@Test
		@DisplayName("Validar un plato sin precio")
		void ValidacionPrecioNoVacio() {
			
			Plato plato= new Plato();
			
			plato.setPrecio("");
			Validator validator = createValidator();
			Set<ConstraintViolation<Plato>> constraintViolations = validator.validate(plato);
			for (ConstraintViolation<Plato> d : constraintViolations) {
				if (d.getPropertyPath().toString().equals("precio")) {
					assertThat(d.getMessage()).isEqualTo("no puede estar vacío");
				}
			}
		}
		
}
