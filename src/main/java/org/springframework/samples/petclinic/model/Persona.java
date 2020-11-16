package org.springframework.samples.petclinic.model;

import javax.persistence.Column;
import javax.persistence.MappedSuperclass;
import javax.validation.constraints.Size;

@MappedSuperclass
public class Persona extends BaseEntity {

    @Size(min = 3, max = 50)
	@Column(name = "name")
	private String name;
    
    @Size(min = 3, max = 50)
	@Column(name = "apellido")
	private String apellido;
    
    @Size(min = 3, max = 50)
	@Column(name = "gmail")
	private String gmail;

    @Size(min = 3, max = 50)
	@Column(name = "telefono")
	private String telefono;

    @Size(min = 3, max = 50)
	@Column(name = "contraseña")
	private String contraseña;

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

	public String getContraseña() {
		return contraseña;
	}

	public void setContraseña(String contraseña) {
		this.contraseña = contraseña;
	}

	@Override
	public String toString() {
		return "Persona [name=" + name + ", apellido=" + apellido + ", gmail=" + gmail + ", telefono=" + telefono
				+ ", contraseña=" + contraseña + "]";
	}




	

}