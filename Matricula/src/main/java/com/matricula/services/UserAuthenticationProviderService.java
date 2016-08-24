/**
 * 
 */
package com.matricula.services;

import com.matricula.domain.UserEntity;

/**
 * Provee servicios de autenticacion del usuario logueado
 * 
 * @author Alexis
 * @26/06/2014
 * 
 */
public interface UserAuthenticationProviderService {
	/**
	 * Proceso de autenticacion del usuario
	 * 
	 * @param user
	 * @return true
	 */
	boolean processUserAuthentication(UserEntity user);
}
