package org.springframework.samples.petclinic.model;

import java.time.LocalDate;

import javax.persistence.Entity;

import org.springframework.format.annotation.DateTimeFormat;

@Entity
public class Event extends NamedEntity {
	
	private String description;
	       
	@DateTimeFormat(pattern = "yyyy/MM/dd")
	private LocalDate start;
	
	@DateTimeFormat(pattern = "yyyy/MM/dd")
	private LocalDate end;
}
