package org.springframework.samples.petclinic.model;

import javax.persistence.Column;
import javax.persistence.MappedSuperclass;
import javax.validation.constraints.Email;
import javax.validation.constraints.Size;

import org.springframework.data.annotation.Version;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@MappedSuperclass
public class Empleado extends NamedEntity {

    @Size(min = 3, max = 50)
	@Column(name = "apellido")
	private String apellido;
    
    @Email(message="text is not a valid email")
    @Size(min = 3, max = 50)
	@Column(name = "gmail")
	private String gmail;

    @Size(min = 9, max = 12)
	@Column(name = "telefono")
	private String telefono;
    
    @Size(min = 3, max = 50)
	@Column(name = "usuario")
	private String usuario;
    
    @Size(min = 3, max = 50)
	@Column(name = "contrasena")
	private String contrasena;
    
	@Version
	private Integer version;
	
	public Integer getVersion() {
		return this.version;
	}
    
}