package org.springframework.samples.petclinic.model;


import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Table;
import javax.validation.constraints.Size;

import lombok.Data;


@Data
@Entity
@Table(name = "proveedor")
public class Proveedor extends NamedEntity{

    @Size(min = 3, max = 50)
	@Column(name = "apellido")
	private String apellido;
    
    @Size(min = 3, max = 50)
	@Column(name = "gmail")
	private String gmail;
    
    @Size(min = 3, max = 50)
	@Column(name = "telefono")
	private String telefono;
	

	public String getapellido() {
		return this.apellido;
	}

	public void setapellido(String apellido) {
		this.apellido = apellido;
	}
	public String getgmail() {
		return this.gmail;
	}

	public void setgmail(String gmail) {
		this.gmail = gmail;
	}
	public String getTelefono() {
		return telefono;
	}

	public void setTelefono(String telefono) {
		this.telefono = telefono;
	}
	
	@Override
	public String toString() {
		return "Proveedor [gmail=" + gmail + ", telefono=" + telefono + "]";
	}
	

}
