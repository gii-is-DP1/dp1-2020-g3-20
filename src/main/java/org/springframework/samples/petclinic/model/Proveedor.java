package org.springframework.samples.petclinic.model;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Table;
import javax.validation.constraints.Size;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
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

}
