package com.matricula.row;

import com.matricula.domain.Registration;

public class RegistrationRow {

	private Registration matriculaEntity;
	private Long id;

	/*
	 * CONSTRUCTORS
	 */

	// This empty constructor is required
	public RegistrationRow() {

	}

	public RegistrationRow(Registration matriculaEntity, Long id) {
		this.matriculaEntity = matriculaEntity;
		this.id = id;
	}

	/*
	 * GETTERS AND SETTER
	 */

	public Registration getMatriculaEntity() {
		return matriculaEntity;
	}

	public void setMatriculaEntity(Registration matriculaEntity) {
		this.matriculaEntity = matriculaEntity;
	}

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

}
