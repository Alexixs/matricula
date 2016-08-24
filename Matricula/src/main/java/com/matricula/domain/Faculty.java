package com.matricula.domain;

import javax.persistence.Access;
import javax.persistence.AccessType;
import javax.persistence.Basic;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.Table;

import org.hibernate.validator.constraints.NotBlank;

import com.matricula.base.domain.BaseEntity;

/**
 * Representa la facultad a la que esta asociada un programa
 * 
 * @author Alexis
 *
 */
@Entity
@Table(name = "faculty")
@Access(AccessType.FIELD)
public class Faculty extends BaseEntity {

	private static final long serialVersionUID = 7003073316521891347L;

	// ATRIBUTOS
	@Basic(fetch = FetchType.EAGER, optional = false)
	@Column(name = "name", length = 150)
	@NotBlank
	private String name;

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

}
