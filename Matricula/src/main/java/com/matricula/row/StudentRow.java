package com.matricula.row;

import com.matricula.domain.Student;

public class StudentRow {

	private Student student;
	private Long id;

	/*
	 * CONSTRUCTORS
	 */

	// This empty constructor is required
	public StudentRow() {

	}

	public StudentRow(Student student, Long id) {
		this.setStudent(student);
		this.id = id;
	}

	/*
	 * GETTERS AND SETTERS
	 */

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public Student getStudent() {
		return student;
	}

	public void setStudent(Student student) {
		this.student = student;
	}
}
