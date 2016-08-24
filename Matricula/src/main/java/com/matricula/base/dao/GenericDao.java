/**
 *
 */
package com.matricula.base.dao;

import com.matricula.utilities.impl.ParameterUtilities;

import java.io.Serializable;
import java.util.List;

import javassist.NotFoundException;

import javax.ejb.FinderException;

/**
 * Interfaz encargarda de gestionar los procesos de acceso a datos de los
 * objetos (dao) o tambien conocidos como procesos para la realizacion de un
 * crud. Pero no solamente contiene metodos para lo mencionado anteriormente si
 * no que contiene otros para facilitar la obtencion de informacion de la base
 * de datos como lo es el findById, el findAll y otros que podrian ir aqui
 *
 * @author Alexis
 * @since 02/07/2014
 * @category DAO
 * @version 1.0
 * @see GenericJPADao
 */
public interface GenericDao<ID extends Serializable> {

	/**
	 * Metodo que se encarga de persistir la entidad, es decir de guardarla en
	 * la base de datos
	 *
	 * @category Persistencia
	 * @param entity
	 * @return entity
	 *
	 */
	<T extends Serializable> void save(final T entity);

	/**
	 * Metodo que se encarga de actualizar la entidad
	 *
	 * @category Merged
	 * @param entity
	 * @return entity
	 *
	 */
	<T extends Serializable> void update(final T entity);

	/**
	 * Metodo que se encarga de borrar la entidad
	 *
	 * @category Remove
	 * @param entity
	 * @throws NotFoundException 
	 * @throws FinderException 
	 *
	 */
	<T extends Serializable> void delete(Class<T> entity, ID id) throws FinderException, NotFoundException;

	/**
	 * Metodo que se encarga de buscar un registro que contenga el id recibido
	 * como parametro
	 *
	 * @param id
	 * @return objeto con la informacion correspondiente al registro con el id
	 *         recibido
	 * @throws FinderException
	 * @throws NotFoundException
	 */
	<T extends Serializable> T findById(Class<T> entity, ID id)
			throws FinderException, NotFoundException;

	/**
	 * Metodo que se encarga de devolver los registros de una entidad
	 *
	 * @return todos los registros de la entidad
	 * @throws FinderException
	 */
	<T extends Serializable> List<T> findAll(Class<T> entity)
			throws FinderException;

	/**
	 * Metodo que se encarga de devolver los registros de una entidad con las
	 * condiciones del filtro
	 *
	 * @param filter
	 * @param parameterValue
	 * @return todos los registros de la entidad
	 * @throws FinderException
	 * @throws Exception
	 */
	<T extends Serializable> List<T> findAll(Class<T> entity, String filter,
			Object parameterValue) throws FinderException, Exception;

	/**
	 * Metodo que recibe un filtro y una lista de parametros para devolver una
	 * lista de registros
	 *
	 * @param filter
	 * @param parameters
	 * @return
	 */
	<T extends Serializable> List<T> findAll(Class<T> entity, String filter,
			List<ParameterUtilities<?>> parameters) throws Exception;

	/**
	 * Metodo que devuelve la entidad que cumpla con las condiciones del filtro
	 * 
	 * @param filter
	 * @param list
	 *            of parameters
	 * @return entity
	 * @throws FinderException
	 * @throws Exception
	 * 
	 */
	<T extends Serializable> T find(Class<T> entity, String filter,
			List<ParameterUtilities<?>> parameters) throws FinderException,
			Exception;
}
