/**
 * 
 */
package com.matricula.base.domain;

import java.io.Serializable;

import javax.persistence.Access;
import javax.persistence.AccessType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.MappedSuperclass;

/**
 * Clase que genera los id de la entidad
 * 
 * @author Alexis
 *
 */
@MappedSuperclass
@Access(AccessType.FIELD)
public class BaseEntity implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = -5452064233861832882L;

	/**
	 * Generador del id
	 * 
	 * @category autogenerador
	 * @return id
	 */
	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	private Long id;

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}
}
