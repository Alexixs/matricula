/**
 * 
 */
package com.matricula.domain;

import javax.persistence.Access;
import javax.persistence.AccessType;
import javax.persistence.Basic;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Table;

import org.hibernate.validator.constraints.NotBlank;

import com.matricula.base.domain.BaseEntity;

/**
 * Representa la jornada que tiene disponible un estudiante
 * 
 * @author Alexis
 *
 */
@Entity
@Table(name = "working_day")
@Access(AccessType.FIELD)
public class WorkingDay extends BaseEntity {

	private static final long serialVersionUID = 5768437323161087698L;

	// ATRIBUTOS
	@Basic(optional = false)
	@Column(name = "description", length = 550)
	@NotBlank
	private String description;

	public String getDescription() {
		return description;
	}

	public void setDescription(String description) {
		this.description = description;
	}

}
