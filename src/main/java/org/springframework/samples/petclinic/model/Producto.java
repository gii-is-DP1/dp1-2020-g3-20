package org.springframework.samples.petclinic.model;

import java.time.LocalDate;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Table;

import org.springframework.format.annotation.DateTimeFormat;

import lombok.Data;

@Entity
@Table(name = "producto")
public class Producto extends NamedEntity{
	@Column(name = "tipo_producto")
	private E_Producto eProducto;
    
	@Column(name = "fecha_caducidad")
	@DateTimeFormat(pattern = "yyyy/MM/dd")
	private LocalDate fechaCaducidad;

	@Column(name = "cantidad_minima")
	private int cantMin;

	@Column(name = "cantidad_actual")
	private int cantAct;
	
	@Column(name = "cantidad_maxima")
	private int cantMax;
	
	
	public E_Producto geteProducto() {
		return eProducto;
	}

	public void seteProducto(E_Producto eProducto) {
		this.eProducto = eProducto;
	}

	public LocalDate getFechaCaducidad() {
		return fechaCaducidad;
	}

	public void setFechaCaducidad(LocalDate fechaCaducidad) {
		this.fechaCaducidad = fechaCaducidad;
	}

	public int getCantMin() {
		return cantMin;
	}

	public void setCantMin(int cantMin) {
		this.cantMin = cantMin;
	}

	public int getCantAct() {
		return cantAct;
	}

	public void setCantAct(int cantAct) {
		this.cantAct = cantAct;
	}

	public int getCantMax() {
		return cantMax;
	}

	public void setCantMax(int cantMax) {
		this.cantMax = cantMax;
	}
	


}