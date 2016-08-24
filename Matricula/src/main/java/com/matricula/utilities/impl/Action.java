/**
 * 
 */
package com.matricula.utilities.impl;

/**
 * Indica la accion que se va a realizar
 * 
 * @author Alexis
 *
 */
public enum Action {

	EDIT("EDITAR"), CREATE("CREAR"), QUERYING("CONSULTAR"), NOTACTION("NONE");

	private String value;

	Action(String value) {
		this.value = value;
	}

	public String getValue() {
		return value;
	}

	public void setValue(String value) {
		this.value = value;
	}

}
