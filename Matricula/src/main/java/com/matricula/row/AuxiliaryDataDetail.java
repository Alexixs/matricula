/**
 * 
 */
package com.matricula.row;

import javax.persistence.Access;
import javax.persistence.AccessType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

import org.hibernate.validator.constraints.NotBlank;

import com.matricula.base.domain.BaseEntity;
import com.matricula.domain.AuxiliaryData;

import lombok.Data;
import lombok.EqualsAndHashCode;

/**
 * 
 * Clase detalle que servira de apoyo para aplicaciones que necesiten el uso de
 * constantes
 * 
 * @author Alexis
 *
 */
@Entity
@Table(name = "auxiliary_data_detail")
@EqualsAndHashCode(callSuper = false)
@Access(AccessType.FIELD)
public @Data class AuxiliaryDataDetail extends BaseEntity {

	private static final long serialVersionUID = -2121680203023275148L;

	// ATRIBUTOS
	@Column(name = "name", length = 150, nullable = false)
	@NotBlank
	private String name;

	@Column(name = "short_name", length = 40, nullable = false)
	private String shortName;

	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(name = "auxiliary_data", nullable = false)
	private AuxiliaryData datoAuxiliar;

	public AuxiliaryDataDetail() {
		this.datoAuxiliar = new AuxiliaryData();
	}

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

	public AuxiliaryData getDatoAuxiliar() {
		return datoAuxiliar;
	}

	public void setDatoAuxiliar(AuxiliaryData datoAuxiliar) {
		this.datoAuxiliar = datoAuxiliar;
	}

}
