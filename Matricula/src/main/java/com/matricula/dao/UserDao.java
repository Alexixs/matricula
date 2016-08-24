/**
 * 
 */
package com.matricula.dao;

import java.io.Serializable;

import com.matricula.base.dao.GenericDao;
import com.matricula.domain.UserEntity;

/**
 * Interfaz de acceso a los datos del objeto para trabajar con la entidad de
 * usuario con operaciones a la base de datos
 * 
 * @author Alexis
 * @16/06/2014
 * 
 */
public interface UserDao extends GenericDao<Long> {

	/**
	 * 
	 * @param userName
	 * @return true si existe un usuario con el parametro userName
	 */
	<T extends Serializable> boolean checkAvailableUser(Class<T> entity,
			String userName);

	/**
	 * 
	 * @param userName
	 * @return devuelve la entidad UserEntity
	 */
	<T extends Serializable> UserEntity loadUserByUserName(Class<T> entity,
			String userName);

}
