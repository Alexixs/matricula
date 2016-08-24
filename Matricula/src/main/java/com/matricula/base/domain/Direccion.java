/**
 * 
 */
package com.matricula.base.domain;

import javax.persistence.Access;
import javax.persistence.AccessType;
import javax.persistence.Column;
import javax.persistence.Embeddable;

/**
 * Clase insertable. Es una clase que no posee tabla en la base de datos pero
 * Permite que otras clases tipo entidades(que si tienen tabla) la inserten para
 * usarla como atributo
 * 
 * @author Alexis
 *
 */
@Embeddable
@Access(AccessType.PROPERTY)
public class Direccion {

	private String strCodigoPostal;
	private String strCalle;
	private String strCarrera;
	private Integer intInterior;

	/*
	 * ====================================================
	 * 
	 * GETTERS AND SETTERS
	 * 
	 * ====================================================
	 */
	@Column(name = "codigoPostal")
	public String getStrCodigoPostal() {
		return strCodigoPostal;
	}

	public void setStrCodigoPostal(String strCodigoPostal) {
		this.strCodigoPostal = strCodigoPostal;
	}

	@Column(name = "calle")
	public String getStrCalle() {
		return strCalle;
	}

	public void setStrCalle(String strCalle) {
		this.strCalle = strCalle;
	}

	@Column(name = "carrera")
	public String getStrCarrera() {
		return strCarrera;
	}

	public void setStrCarrera(String strCarrera) {
		this.strCarrera = strCarrera;
	}

	@Column(name = "interior")
	public Integer getIntInterior() {
		return intInterior;
	}

	public void setIntInterior(Integer intInterior) {
		this.intInterior = intInterior;
	}
}
