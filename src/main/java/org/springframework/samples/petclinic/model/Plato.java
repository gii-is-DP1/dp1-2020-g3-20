package org.springframework.samples.petclinic.model;



import java.util.ArrayList;
import java.util.Collections;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.OneToMany;
import javax.persistence.Table;

import org.springframework.beans.support.MutableSortDefinition;
import org.springframework.beans.support.PropertyComparator;

import lombok.Data;
import lombok.Getter;
import lombok.Setter;

@Data
@Entity
@Table(name = "platos")
public class Plato extends NamedEntity{
	 
	
	@Column(name = "precio")
	private Double precio;
	
	@Column(name = "disponible")
	private Boolean disponible;
	
	

	
}
