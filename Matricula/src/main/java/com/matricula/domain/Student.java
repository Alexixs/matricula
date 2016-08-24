/**
 * 
 */
package com.matricula.domain;

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
import com.matricula.row.AuxiliaryDataDetail;

/**
 * Entidad que representa la informacion de los estudiantes
 * 
 * @author Alexis
 *
 */
@Entity
@Table(name = "students")
@Access(AccessType.FIELD)
public class Student extends BaseEntity {

	private static final long serialVersionUID = -5415233546521347777L;

	// ATRIBUTOS

	@Column(name = "doc_number", nullable = false, length = 150)
	@NotBlank
	private String docNumber;

	@Column(name = "name", nullable = false, length = 150)
	@NotBlank
	private String name;

	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(name = "status")
	private AuxiliaryDataDetail status;

	@Column(name = "comments", nullable = true, length = 550)
	private String comment;

	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(name = "user")
	private UserEntity user;

	@Column(name = "address", length = 150)
	private String address;

	public String getDocNumber() {
		return docNumber;
	}

	public void setDocNumber(String docNumber) {
		this.docNumber = docNumber;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public AuxiliaryDataDetail getStatus() {
		return status;
	}

	public void setStatus(AuxiliaryDataDetail status) {
		this.status = status;
	}

	public String getComment() {
		return comment;
	}

	public void setComment(String comment) {
		this.comment = comment;
	}

	public UserEntity getUser() {
		return user;
	}

	public void setUser(UserEntity user) {
		this.user = user;
	}

	public String getAddress() {
		return address;
	}

	public void setAddress(String address) {
		this.address = address;
	}

}
