/**
 * 
 */
package com.matricula.domain;

import javax.persistence.Access;
import javax.persistence.AccessType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Table;

import org.hibernate.validator.constraints.NotBlank;
import org.hibernate.validator.constraints.NotEmpty;

import com.matricula.base.domain.BaseEntity;

/**
 * Define el grupo para los detalles en detalleDatoAuxiliarEntity
 * 
 * @author Alexis
 *
 */
@Entity
@Table(name = "auxiliary_data")
@Access(AccessType.FIELD)
public class AuxiliaryData extends BaseEntity {

	private static final long serialVersionUID = -7368763351908986345L;

	// ATRIBUTOS

	@Column(name = "name", nullable = false, length = 150)
	@NotBlank
	@NotEmpty
	private String name;

	@Column(name = "short_name", nullable = false, length = 40)
	@NotBlank
	@NotEmpty
	private String shortName;

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getShortName() {
		return shortName;
	}

	public void setShortName(String shortName) {
		this.shortName = shortName;
	}

}
