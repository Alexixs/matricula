/**
 *
 */
package com.matricula.services.impl;

import java.io.Serializable;
import java.util.List;

import javax.ejb.FinderException;

import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;

import com.matricula.base.dao.GenericJPADao;
import com.matricula.services.UserService;

/**
 * Clase para manipular los metodos de servicios para el usuario e implementa a
 * UserSerivice
 *
 * @author Alexis
 * @16/06/2014
 *
 */
public class UserServiceImpl extends GenericJPADao<Long> implements
		UserService, UserDetailsService {

	public UserServiceImpl() {
	}

	public UserDetails loadUserByUsername(String username)
			throws UsernameNotFoundException {
		throw new UnsupportedOperationException("Not supported yet.");
	}

	public <T extends Serializable> List<T> findAll(String filter,
			Object parameterValue) throws FinderException {
		// TODO Auto-generated method stub
		return null;
	}

}
