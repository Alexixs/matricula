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
import javax.persistence.Table;

import org.hibernate.validator.constraints.NotEmpty;

import com.matricula.base.domain.BaseEntity;

/**
 * 
 * Representa el periodo para el cual se va a matricular un estudiante
 * 
 * @author Alexis
 *
 */
@Entity
@Table(name = "period")
@Access(AccessType.FIELD)
public class Period extends BaseEntity {

	private static final long serialVersionUID = 3510781477146437463L;

	// ATRIBUTOS
	@Basic(fetch = FetchType.EAGER, optional = false)
	@Column(name = "description", length = 30)
	@NotEmpty
	private String description;

	public String getDescription() {
		return description;
	}

	public void setDescription(String description) {
		this.description = description;
	}

}
