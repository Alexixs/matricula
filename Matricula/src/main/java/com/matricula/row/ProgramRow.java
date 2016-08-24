package com.matricula.row;

import com.matricula.domain.Course;

public class ProgramRow {

	private Course programaEntity;

	/*
	 * CONSTRUCTORS
	 */

	// This empty constructor is required
	public ProgramRow() {

	}

	public ProgramRow(Course programaEntity) {
		this.programaEntity = programaEntity;
	}

	/*
	 * GETTERS AND SETTERS
	 */
	public Course getProgramaEntity() {
		return programaEntity;
	}

	public void setProgramaEntity(Course programaEntity) {
		this.programaEntity = programaEntity;
	}
}
