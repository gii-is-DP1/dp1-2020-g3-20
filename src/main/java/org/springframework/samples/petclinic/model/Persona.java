package org.springframework.samples.petclinic.model;

import javax.persistence.Column;
import javax.persistence.MappedSuperclass;
import javax.validation.constraints.Email;
import javax.validation.constraints.Size;

@MappedSuperclass
public class Persona extends BaseEntity {

    @Size(min = 3, max = 50)
	@Column(name = "name")
	private String name;
    
    @Size(min = 3, max = 50)
	@Column(name = "apellido")
	private String apellido;
    @Email(message="text is not a valid email")
    @Size(min = 3, max = 50)
	@Column(name = "gmail")
	private String gmail;

    @Size(min = 3, max = 50)
	@Column(name = "telefono")
	private String telefono;

    @Size(min = 3, max = 50)
	@Column(name = "contrasena")
	private String contrasena;

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getApellido() {
		return apellido;
	}

	public void setApellido(String apellido) {
		this.apellido = apellido;
	}

	public String getGmail() {
		return gmail;
	}

	public void setGmail(String gmail) {
		this.gmail = gmail;
	}

	public String getTelefono() {
		return telefono;
	}

	public void setTelefono(String telefono) {
		this.telefono = telefono;
	}

	public String getContrasena() {
		return contrasena;
	}

	public void setContrasena(String contrasena) {
		this.contrasena = contrasena;
	}

	@Override
	public String toString() {
		return "Persona [name=" + name + ", apellido=" + apellido + ", gmail=" + gmail + ", telefono=" + telefono
				+ ", contrasena=" + contrasena + "]";
	}




	

}