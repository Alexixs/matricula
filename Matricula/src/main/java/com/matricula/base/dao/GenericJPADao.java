package com.matricula.base.dao;

import java.io.Serializable;
import java.util.List;

import javassist.NotFoundException;

import javax.ejb.FinderException;
import javax.persistence.EntityManager;
import javax.persistence.EntityNotFoundException;
import javax.persistence.PersistenceContext;
import javax.persistence.PersistenceException;
import javax.persistence.Query;

import org.apache.commons.lang.StringUtils;
import org.springframework.transaction.annotation.Transactional;

import com.matricula.utilities.impl.ParameterUtilities;

/**
 * @author Alexis
 * @since 2014
 * @version 1.0
 * @see ENGLISH: Provide generic common implementation of genericDao interface
 *      persistence method, extends this abstract class to implement dao for
 *      your specific needs
 *
 *      SPANISH: Provee implementaciones genericas comunes de la interfaz
 *      genericDao para persistir los metodos, Herede esta clase abstracta para
 *      implementar dao para sus necesidades especificas
 */
@Transactional
public class GenericJPADao<ID extends Serializable> implements GenericDao<ID> {

	private EntityManager entityManager;

	/**
	 * @category constructor
	 * @param clase
	 *            de persistencia
	 */
	public GenericJPADao() {
	}

	protected EntityManager getEntityManager() {
		return entityManager;
	}

	/*
	 * Inyeccion de la persistencia para entityManager
	 */
	@PersistenceContext
	public void setEntityManager(EntityManager entityManager) {
		this.entityManager = entityManager;
	}

	/*
	 * ==================================================
	 * 
	 * TRANSACTIONAL METHODS
	 * 
	 * ==================================================
	 */
	/**
	 * Metodo que devuelve el registro que contenga el id
	 *
	 * @category transaccional de solo lectura
	 * @param id
	 * @see com.ejemplo.base.dao.GenericDao#findById()
	 * @return devuelve un registro que tenga el id recibido como parametro
	 * @throws FinderException
	 * @throws NotFoundException
	 * @throws no
	 *             devuelve exceptions
	 *
	 */
	@Transactional(readOnly = true)
	public <T extends Serializable> T findById(final Class<T> entity, ID id)
			throws FinderException, NotFoundException {

		if (entity == null) {
			throw new EntityNotFoundException(
					"Entity no found: la entidad no puede ser nula y no se puede omitir");
		}
		if (id == null) {
			throw new NotFoundException("ID no valid: El id no puede ser nulo");
		}
		try {
			return entityManager.find(entity, id);
		} catch (PersistenceException ex) {
			throw new FinderException(ex.getMessage());
		} finally {
			entityManager.close();
		}
	}

	/**
	 * Metodo que devuelve un unico registro que cumpla las condiciones del
	 * filtro de una entidad
	 * 
	 * @author Edward Alexis Ortiz
	 * @category search
	 * @param filter
	 * @param parameters
	 * @throws Exception
	 */
	@SuppressWarnings("unchecked")
	public <T extends Serializable> T find(Class<T> entity, String filter,
			List<ParameterUtilities<?>> parameters) throws Exception {

		if (entity == null) {
			throw new EntityNotFoundException(
					"Entity no found: la entidad no puede ser nula y no se puede omitir");
		}
		if (StringUtils.isEmpty(filter)) {
			throw new Exception("Filter is empty: El filtro no puede ser nulo");
		}
		try {
			Query query = entityManager.createQuery("select x from "
					+ entity.getSimpleName() + " x where " + filter);

			for (ParameterUtilities<?> parameter : parameters) {
				if (parameter.getPosition() != null) {
					query.setParameter(parameter.getPosition(),
							parameter.getName());
				} else {
					query.setParameter(parameter.getName(),
							parameter.getValue());
				}
			}
			return (T) query.getSingleResult();

		} catch (PersistenceException ex) {
			throw new FinderException(ex.getMessage());
		} finally {
			entityManager.close();
		}
	}

	/**
	 * Metodo que devuelve todos los registros de la tabla que contengan el id
	 *
	 * @category transaccional de solo lectura
	 * @param id
	 * @see com.ejemplo.base.dao.GenericDao#findAll()
	 * @return devuelve todos los registros de la entidad
	 * @throws FinderException
	 * @throws no
	 *             devuelve exceptions
	 */

	@SuppressWarnings("unchecked")
	@Transactional(readOnly = true)
	public <T extends Serializable> List<T> findAll(Class<T> entity)
			throws FinderException {

		if (entity == null) {
			throw new EntityNotFoundException(
					"Entity no found: la entidad no puede ser nula y no se puede omitir");
		}
		try {
			List<T> entityList = entityManager.createQuery(
					"select x from " + entity.getSimpleName() + " x ")
					.getResultList();
			return entityList;
		} catch (PersistenceException ex) {
			throw new FinderException(ex.getMessage());
		} finally {
			entityManager.close();
		}
	}

	/**
	 * Metodo que devuelve todos los registros de la tabla que contengan el id
	 *
	 * @category transaccional de solo lectura
	 * @param filter
	 * @param parameterValue
	 * @return devuelve todos los registros que tenga las condiciones del filtro
	 * @throws Exception
	 * @throws no
	 *             devuelve exceptions
	 */
	@SuppressWarnings("unchecked")
	@Transactional(readOnly = true)
	public <T extends Serializable> List<T> findAll(Class<T> entity,
			String filter, Object parameterValue) throws Exception {

		if (entity == null) {
			throw new EntityNotFoundException(
					"Entity no found: la entidad no puede ser nula y no se puede omitir");
		}

		if (StringUtils.isEmpty(filter)) {
			throw new Exception("Filter is empty: El filtro no puede ser nulo");
		}
		if (parameterValue == null) {
			throw new NotFoundException(
					"Parameter no valid: Los parametros no pueden ser nulos");
		}
		try {
			List<T> entityList = entityManager
					.createQuery(
							"select x from " + entity.getSimpleName()
									+ " x where " + filter)
					.setParameter(1, parameterValue).getResultList();
			return entityList;
		} catch (PersistenceException ex) {
			throw new FinderException(ex.getMessage());
		} finally {
			entityManager.close();
		}
	}

	/**
	 * Metodo que retorna los registros de una tabla con la condicion del filtro
	 *
	 * @param filter
	 * @param parameters
	 * @return List<T>
	 * @throws Exception
	 */
	@SuppressWarnings("unchecked")
	@Transactional(readOnly = true)
	public <T extends Serializable> List<T> findAll(Class<T> entity,
			String filter, List<ParameterUtilities<?>> parameters)
			throws Exception {

		if (entity == null) {
			throw new EntityNotFoundException(
					"Entity no found: la entidad no puede ser nula y no se puede omitir");
		}

		if (StringUtils.isEmpty(filter)) {
			throw new Exception("Filter is empty: El filtro no puede ser nulo");
		}
		if (parameters == null || parameters.size() == 0) {
			throw new NotFoundException(
					"Parameter no valid: Los parametros no pueden ser nulos");
		}
		try {
			Query query = getEntityManager().createQuery(
					"select x from " + entity.getSimpleName() + " x where "
							+ filter);
			for (ParameterUtilities<?> parameter : parameters) {
				if (parameter.getPosition() != null) {
					query.setParameter(parameter.getPosition(),
							parameter.getName());
				} else {
					query.setParameter(parameter.getName(),
							parameter.getValue());
				}
			}
			return query.getResultList();
		} catch (PersistenceException ex) {
			throw new FinderException(ex.getMessage());
		} finally {
			entityManager.close();
		}
	}

	/**
	 * Metodo que se encarga de guardar la entidad
	 *
	 * @category transaccional de persistencia de guardado
	 * @param entidad
	 * @see com.ejemplo.base.dao.GenericDao#save()
	 * @throws no
	 *             devuelve exceptions
	 */
	public <T extends Serializable> void save(final T entity) {

		if (entity == null) {
			throw new EntityNotFoundException(
					"Entity no found: la entidad no puede ser nula y no se puede omitir");
		}
		try {
			entityManager.persist(entity);
		} catch (PersistenceException ex) {
			throw new PersistenceException(ex.getMessage());
		} finally {
			entityManager.close();
		}
	}

	/**
	 * Metodo que se encarga de actualizar la entidad
	 *
	 * @category transaccional de persistencia de actualizado
	 * @param entidad
	 * @see com.ejemplo.base.dao.GenericDao#save()
	 * @throws no
	 *             devuelve exceptions
	 * @return devuelve la entidad actualizada
	 */
	public <T extends Serializable> void update(final T entity) {

		if (entity == null) {
			throw new EntityNotFoundException(
					"Entity no found: la entidad no puede ser nula y no se puede omitir");
		}

		try {
			entityManager.merge(entity);
		} catch (PersistenceException ex) {
			throw new PersistenceException(ex.getMessage());
		} finally {
			entityManager.close();
		}
	}

	/**
	 * Metodo encargado de eliminar una entidad
	 *
	 * @category transactional de persistencia de borrado
	 * @param entidad
	 * @throws FinderException
	 * @throws NotFoundException
	 *
	 */
	public <T extends Serializable> void delete(Class<T> entity, ID id)
			throws FinderException, NotFoundException {

		if (entity == null) {
			throw new EntityNotFoundException(
					"Entity no found: la entidad no puede ser nula y no se puede omitir");
		}

		T foundEntity = findById(entity, id);

		if (foundEntity == null) {
			throw new FinderException(
					"Zero results: No se encontró registro para la entidad "
							+ entity.getSimpleName() + " y el id " + id);
		}

		try {
			entityManager.remove(foundEntity);
		} catch (PersistenceException ex) {
			throw new PersistenceException(
					"Delete no commited: El borrado no se completo con éxito");
		} finally {
			entityManager.close();
		}
	}
}
