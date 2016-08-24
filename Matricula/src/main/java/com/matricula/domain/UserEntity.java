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
import javax.persistence.OneToOne;
import javax.persistence.Table;
import javax.validation.constraints.NotNull;

import org.hibernate.annotations.OnDelete;
import org.springframework.security.authentication.encoding.Md5PasswordEncoder;

import com.matricula.base.domain.BaseEntity;
import com.matricula.row.AuxiliaryDataDetail;

/**
 * Representa la entidad del usuario
 * 
 * @author Alexis
 * @since 29/06/2014
 * @version 2
 * @see Access define si se va acceder al valor que contiene el atributo o al
 *      valor que contiene la propiedad de ese atributo
 */
@Entity
@Table(name = "users")
@Access(AccessType.FIELD)
public class UserEntity extends BaseEntity {

	private static final long serialVersionUID = 3817245997428223070L;

	// ATRIBUTOS

	@Column(name = "user_login", nullable = false, length = 50)
	@NotNull
	private String strUsuario;

	@Column(name = "user_password", length = 150)
	@Basic(fetch = FetchType.EAGER, optional = false)
	private String strContrasena;

	// Relacion unidireccional
	@OneToOne(fetch = FetchType.LAZY)
	@JoinColumn(name = "rol", nullable = false)
	private AuxiliaryDataDetail perfil;

	public UserEntity() {
		this.perfil = new AuxiliaryDataDetail();
	}
	/*
	 * =================================================
	 * 
	 * GETTERS AND SETTERS
	 * 
	 * 
	 * =================================================
	 */

	/**
	 * @see FetchType define el tipo de lectura que se hara al momento de la
	 *      creacion del objeto, estas pueden ser temprana(eager) o
	 *      demorada(lazy), el atributo optional indica si un campo puede ser
	 *      nulo o no
	 * 
	 * @see Column define la columna de la base de datos junto con sus
	 *      restricciones
	 * 
	 * @see OnDelete especifica la forma en que se va a realizar el borrado
	 */

	public String getStrUsuario() {
		return strUsuario;
	}

	public void setStrUsuario(String strUsuario) {
		this.strUsuario = strUsuario;
	}

	public String getStrContrasena() {
		return strContrasena;
	}

	public void setStrContrasena(String strContrasena) {
		Md5PasswordEncoder encriptar = new Md5PasswordEncoder();
		this.strContrasena = encriptar.encodePassword(strContrasena, null);
	}

	/**
	 * Perfil del usuario
	 * 
	 * @return perfil
	 */
	public AuxiliaryDataDetail getPerfil() {
		return perfil;
	}

	public void setPerfil(AuxiliaryDataDetail perfil) {
		this.perfil = perfil;
	}
}
