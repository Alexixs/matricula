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
 * 
 * Representa las asignaturas que puede elegir un estudiante cuando vaya a
 * realizar su matricula
 * 
 * @author Alexis
 *
 */
@Entity
@Table(name = "subject")
@Access(AccessType.FIELD)
public class Subject extends BaseEntity {

	private static final long serialVersionUID = -5095245497951085903L;

	// ATRIBUTOS
	@Basic(fetch = FetchType.LAZY, optional = false)
	@ManyToOne
	@JoinColumn(name = "course")
	private Course course;

	@Basic(fetch = FetchType.EAGER, optional = false)
	@Column(name = "name", length = 50)
	@NotBlank
	private String name;

	public Course getCourse() {
		return course;
	}

	public void setCourse(Course course) {
		this.course = course;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}


}
