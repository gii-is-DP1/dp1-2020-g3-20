package org.springframework.samples.petclinic.model;

import java.time.LocalDate;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

import org.springframework.format.annotation.DateTimeFormat;

import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@EqualsAndHashCode(callSuper = true,doNotUseGetters = true)
@Entity
@Table(name = "producto")
public class Producto extends NamedEntity{
	
	@ManyToOne
	@JoinColumn(name = "tipo_producto")
	private TipoProducto tipoProducto;
    
	@Column(name = "fecha_caducidad")
	@DateTimeFormat(pattern = "yyyy/MM/dd")
	private LocalDate fechaCaducidad;

	@Column(name = "cantidad_minima")
	private double cantMin;

	@Column(name = "cantidad_actual")
	private double cantAct;
	
	@Column(name = "cantidad_maxima")
	private double cantMax;
	
	@Column(name = "proveedor")
	private String proveedor;
	
	
	public TipoProducto getTipoProducto() {
		return tipoProducto;
	}

	public void setTipoProducto(TipoProducto tipoProducto) {
		this.tipoProducto = tipoProducto;
	}

	public LocalDate getFechaCaducidad() {
		return fechaCaducidad;
	}

	public void setFechaCaducidad(LocalDate fechaCaducidad) {
		this.fechaCaducidad = fechaCaducidad;
	}

	public double getCantMin() {
		return cantMin;
	}

	public void setCantMin(double cantMin) {
		this.cantMin = cantMin;
	}

	public double getCantAct() {
		return cantAct;
	}

	public void setCantAct(double cantAct) {
		this.cantAct = cantAct;
	}

	public double getCantMax() {
		return cantMax;
	}

	public void setCantMax(double cantMax) {
		this.cantMax = cantMax;
	}
	
	public String getProveedor() {
		return proveedor;
	}

	public void setProveedor(String proveedor) {
		this.proveedor = proveedor;
	}
}
