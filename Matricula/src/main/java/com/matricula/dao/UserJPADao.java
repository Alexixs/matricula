package com.matricula.dao;

import java.io.Serializable;
import java.util.List;

import javax.ejb.FinderException;
import javax.persistence.NoResultException;
import javax.persistence.Query;

import org.springframework.util.Assert;

import com.matricula.base.dao.GenericJPADao;
import com.matricula.domain.UserEntity;

/**
 * Implementacion de los metodos de userDao interface
 * 
 * @author Alexis
 * @16/06/2014
 * 
 */
public class UserJPADao extends GenericJPADao<Long> implements UserDao {

	public UserJPADao() {
	}

	/**
	 * Valida de que no exista un usuario con el mismo userName
	 * 
	 * @return verdadero si no existe un usuario
	 * @param userName
	 *            --usuario
	 * @throws Assert.exception
	 */
	public <T extends Serializable> boolean checkAvailableUser(Class<T> entity,
			String userName) {
		// Assert.notNull valida de si el nombre de usuario es nulo, si es nulo
		// lanza una excepcion y no deja continuar el proceso
		Assert.notNull(userName);

		Query query = getEntityManager().createQuery(
				"select count(*) from " + entity.getSimpleName()
						+ " u where u.strUser = :user").setParameter("user",
				userName);

		Long count = (Long) query.getSingleResult();
		return count == 0;
	}

	/**
	 * Metodo que busca a un usuario por el nombre y devuelve el objeto completo
	 * 
	 * @param userName
	 *            --usuario
	 * @return devuelve el objeto entidad
	 * @throws NoResultException
	 *             o Assert exception
	 */
	public <T extends Serializable> UserEntity loadUserByUserName(
			Class<T> entity, String userName) {
		Assert.notNull(userName);

		UserEntity user = null;

		Query query = getEntityManager().createQuery(
				"select u from " + entity.getSimpleName()
						+ " u where u.strUser = :user").setParameter("user",
				userName);

		try {
			user = (UserEntity) query.getSingleResult();
		} catch (NoResultException ex) {

		}

		return user;
	}

	public void delete(UserEntity entity) {
		// TODO Auto-generated method stub

	}

	public void flush() {
		// TODO Auto-generated method stub

	}

	public <T extends Serializable> List<T> findAll(String filter,
			Object parameterValue) throws FinderException {
		// TODO Auto-generated method stub
		return null;
	}

}
