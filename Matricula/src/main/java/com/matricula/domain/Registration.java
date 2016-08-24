/**
 *
 */
package com.matricula.domain;

import java.util.Date;

import javax.persistence.Access;
import javax.persistence.AccessType;
import javax.persistence.Basic;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;

import com.matricula.base.domain.BaseEntity;

/**
 *
 * Representa la matricula de una asignatura para un estudiante
 *
 * @author Alexis
 *
 */

@Entity
@Table(name = "registration")
@Access(AccessType.FIELD)
public class Registration extends BaseEntity {

	private static final long serialVersionUID = -2164441008488976906L;

	// ATRIBUTOS

	@Basic(optional = true)
	@Temporal(TemporalType.TIMESTAMP)
	@Column(name = "registration_date")
	private Date registrationDate;

	@Basic(fetch = FetchType.LAZY, optional = false)
	@ManyToOne
	@JoinColumn(name = "period")
	private Period period;

	@Basic(optional = false, fetch = FetchType.LAZY)
	@ManyToOne
	@JoinColumn(name = "student", nullable = false)
	private Student student;

	@Basic(optional = false)
	@Column(name = "comments", length = 500)
	private String comment;

	@Basic(fetch = FetchType.LAZY)
	@ManyToOne
	@JoinColumn(name = "working_day", nullable = false)
	private WorkingDay workingDay;

	@Basic(fetch = FetchType.LAZY)
	@ManyToOne
	@JoinColumn(name = "course", nullable = false)
	private Course course;

	public Registration() {
		this.period = new Period();
		this.student = new Student();
		this.course = new Course();
		this.workingDay = new WorkingDay();
	}

	public Date getRegistrationDate() {
		return registrationDate;
	}

	public void setRegistrationDate(Date registrationDate) {
		this.registrationDate = registrationDate;
	}

	public Period getPeriod() {
		return period;
	}

	public void setPeriod(Period period) {
		this.period = period;
	}

	public Student getStudent() {
		return student;
	}

	public void setStudent(Student student) {
		this.student = student;
	}

	public String getComment() {
		return comment;
	}

	public void setComment(String comment) {
		this.comment = comment;
	}

	public WorkingDay getWorkingDay() {
		return workingDay;
	}

	public void setWorkingDay(WorkingDay workingDay) {
		this.workingDay = workingDay;
	}

	public Course getCourse() {
		return course;
	}

	public void setCourse(Course course) {
		this.course = course;
	}

}
