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
		Empleado empleado = new Empleado();
		empleado.setName("");
		empleado.setApellido("smith");

		Validator validator = createValidator();
		Set<ConstraintViolation<Empleado>> constraintViolations = validator.validate(empleado);

		assertThat(constraintViolations.size()).isEqualTo(1);
		ConstraintViolation<Empleado> violation = constraintViolations.iterator().next();
		assertThat(violation.getPropertyPath().toString()).isEqualTo("name");
		assertThat(violation.getMessage()).isEqualTo("must not be empty");
	}
	
	// TESTS PARA Propietario -----------------------------------------------------------------------
	
		@Test
		@DisplayName("Validar una Propietario Correcto")
		void shouldNotValidateReviewWhenValorationIncorrect() {

			LocaleContextHolder.setLocale(Locale.ENGLISH);
			Propietario propietario = new Propietario();
			propietario.setName("Jose");
			propietario.setApellido("Alves");
			propietario.setTelefono("600234321");
			propietario.setGmail("abdche@gmail.com");
			propietario.setUsuario("Manma");
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
				if (d.getPropertyPath().toString().equals("contrasena")) {
					assertThat(d.getMessage()).isEqualTo("size must be between 3 and 50");
				}

			}

		}
		// TESTS PARA Proveedor -----------------------------------------------------------------------
		
		@Test
		@DisplayName("Validar una Proveedor Correcto")
		void shouldNotValidateProveedorReviewWhenValorationIncorrect() {

			LocaleContextHolder.setLocale(Locale.ENGLISH);
			Proveedor Proveedor = new Proveedor();
			Proveedor.setName("juan");
			Proveedor.setTelefono("600234321");
			Proveedor.setGmail("dbfche@gmail.com");

			Validator validator = createValidator();
			Set<ConstraintViolation<Proveedor>> constraintViolations = validator.validate(Proveedor);

			assertThat(constraintViolations.size()).isEqualTo(0);
				
			}
		@Test
		@DisplayName("Validar un Proveedor incorrecto")
		void shouldNotValidateProveedorWhenAllFieldsIncorrect() {

			Proveedor Proveedor = new Proveedor();
			Proveedor.setName("");
			Proveedor.setTelefono("");
			Proveedor.setGmail("");

			Validator validator = createValidator();
			Set<ConstraintViolation<Proveedor>> constraintViolations = validator.validate(Proveedor);
			assertThat(constraintViolations.size()).isEqualTo(4);
			for (ConstraintViolation<Proveedor> d : constraintViolations) {
				if (d.getPropertyPath().toString().equals("name")) {
					assertThat(d.getMessage()).isEqualTo("size must be between 3 and 50");
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


			}

		}
		// TESTS PARA menu -----------------------------------------------------------------------
		@Test
		@DisplayName("Validar una Menú Correcto")
		void shouldNotValidatemenuReviewWhenValorationIncorrect() {

			LocaleContextHolder.setLocale(Locale.ENGLISH);
			Menu Menu = new Menu();
			Menu.setName("Julian");;


			Validator validator = createValidator();
			Set<ConstraintViolation<Menu>> constraintViolations = validator.validate(Menu);

			assertThat(constraintViolations.size()).isEqualTo(0);
				
			}
		@Test
		@DisplayName("Validar un Menú incorrecto")
		void shouldNotValidatemenuWhenAllFieldsIncorrect() {

			Menu Menu = new Menu();
			Menu.setName("");


			Validator validator = createValidator();
			Set<ConstraintViolation<Menu>> constraintViolations = validator.validate(Menu);
			assertThat(constraintViolations.size()).isEqualTo(1);
			for (ConstraintViolation<Menu> d : constraintViolations) {
				if (d.getPropertyPath().toString().equals("name")) {
					assertThat(d.getMessage()).isEqualTo("size must be between 3 and 50");
				}



			}

		}
		// TESTS PARA Producto -----------------------------------------------------------------------
		@Test
		@DisplayName("Validar una Producto Correcto")
		void shouldNotValidateProductoReviewWhenValorationIncorrect() {

			LocaleContextHolder.setLocale(Locale.ENGLISH);
			Producto Producto = new Producto();
			TipoProducto TestTP = new TipoProducto() ;
			Producto.setName("Misco");
			Producto.setTipoProducto(TestTP);
			Producto.setCantMin(1.0);
			Producto.setCantAct(2.0);
			Producto.setCantMax(5.0);

			Validator validator = createValidator();
			Set<ConstraintViolation<Producto>> constraintViolations = validator.validate(Producto);

			assertThat(constraintViolations.size()).isEqualTo(0);
				
			}
		@Test
		@DisplayName("Validar un Producto incorrecto")
		void shouldNotValidateProductoWhenAllFieldsIncorrect() {

			Proveedor Proveedor = new Proveedor();
			Proveedor.setName("");


			Validator validator = createValidator();
			Set<ConstraintViolation<Proveedor>> constraintViolations = validator.validate(Proveedor);
			assertThat(constraintViolations.size()).isEqualTo(1);
			for (ConstraintViolation<Proveedor> d : constraintViolations) {
				if (d.getPropertyPath().toString().equals("name")) {
					assertThat(d.getMessage()).isEqualTo("size must be between 3 and 50");
				}



			}

		}
//		// TESTS PARA Platos -----------------------------------------------------------------------
//        @Test
//        @DisplayName("Validar un plato sin precio")
//        void ValidacionPrecioNoVacio() {
//
//            Plato plato= new Plato();
//
//            plato.setPrecio("");
//            Validator validator = createValidator();
//            Set<ConstraintViolation<Plato>> constraintViolations = validator.validate(plato);
//            for (ConstraintViolation<Plato> d : constraintViolations) {
//                if (d.getPropertyPath().toString().equals("precio")) {
//                    assertThat(d.getMessage()).isEqualTo("no puede estar vacío");
//                }
//            }
//        }
//
//		
}
