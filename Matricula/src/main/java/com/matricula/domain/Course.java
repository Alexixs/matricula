/**
 * 
 */
package com.matricula.domain;

import javax.persistence.Access;
import javax.persistence.AccessType;
import javax.persistence.Basic;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

import org.hibernate.validator.constraints.NotBlank;

import com.matricula.base.domain.BaseEntity;

/**
 * Representa los programas a los cuales se puede matricular un estudiante
 * 
 * @author Alexis
 *
 */
@Entity
@Table(name = "course")
@Access(AccessType.FIELD)
public class Course extends BaseEntity {

	private static final long serialVersionUID = 1923689941805640354L;

	// ATRIBUTOS

	@Basic(optional = false, fetch = FetchType.LAZY)
	@ManyToOne
	@JoinColumn(name = "faculty")
	private Faculty faculty;

	@Basic(optional = false)
	@Column(name = "name", length = 30)
	@NotBlank
	private String name;

	public Faculty getFaculty() {
		return faculty;
	}

	public void setFaculty(Faculty faculty) {
		this.faculty = faculty;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

}
