/**
 * 
 */
package com.matricula.services.impl;

import org.springframework.security.authentication.AuthenticationManager;

import com.matricula.domain.UserEntity;
import com.matricula.services.UserAuthenticationProviderService;

/**
 * 
 * Implementacion de los procesos de autenticacion del usuario
 * 
 * @author Alexis
 * @26/06/2014
 * 
 */
public class UserAuthenticationProviderServiceImpl implements
		UserAuthenticationProviderService {

	// ATRIBUTOS
	private AuthenticationManager authenticationManager;

	/*
	 * ==================================================
	 * 
	 * METODOS PUBLICOS
	 * 
	 * ==================================================
	 */
	/**
	 * Implementacion del proceso de autenticacion del usuario
	 * 
	 * @param user
	 * @category validacion
	 * @throws authentication
	 *             exception si el usuario no existe
	 * @return true or false
	 */
	public boolean processUserAuthentication(UserEntity user) {
		return false;
	}

	/*
	 * ==================================================
	 * 
	 * GETTERS AND SETTERS
	 * 
	 * ==================================================
	 */
	public AuthenticationManager getAuthenticationManager() {
		return authenticationManager;
	}

	public void setAuthenticationManager(
			AuthenticationManager authenticationManager) {
		this.authenticationManager = authenticationManager;
	}

}
