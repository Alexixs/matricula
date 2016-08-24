package com.matricula.utilities.impl;

import java.util.ArrayList;
import java.util.List;

import org.apache.commons.lang.StringUtils;

import com.matricula.base.domain.BeanServices;
import com.matricula.domain.AuxiliaryData;
import com.matricula.row.AuxiliaryDataDetail;
import com.matricula.services.UtilitiesDbService;

/**
 * 
 * @author Edward Alexis Ortiz
 * @since 28/12/2014
 * @category Database utilities
 * @version 1.0
 *
 *          This class presents some methods to facilitate the manipulation of
 *          database data
 */
public class UtilitiesDbServiceImpl extends BeanServices implements UtilitiesDbService {

	private static final long serialVersionUID = -5608887199759463581L;

	/**
	 * This method recovers auxiliary data from database with the specified
	 * group
	 * 
	 * @param group
	 * @exception Exception
	 * @throws Exception
	 * @return List<DetalleDatoAuxiliarEntity>
	 */
	public List<AuxiliaryDataDetail> findDetalleDatoAuxiliarByGroup(String group) throws Exception {

		super.setParameters(new ArrayList<ParameterUtilities<?>>());

		if (StringUtils.isEmpty(group)) {
			throw new Exception("Group no valid: El nombre del grupo no puede ser nulo");
		}
		super.getParameters().add(new ParameterUtilities<String>("group", group));

		AuxiliaryData datoAuxiliar = super.serviceDao.find(AuxiliaryData.class, "shortName = :group",
				super.getParameters());

		super.getParameters().clear();

		if (datoAuxiliar != null) {
			super.getParameters().add(new ParameterUtilities<Long>("groupId", datoAuxiliar.getId()));
			List<AuxiliaryDataDetail> resultSet = super.serviceDao.findAll(AuxiliaryDataDetail.class,
					"datoAuxiliar.id = :groupId", super.getParameters());
			return resultSet;
		}
		return null;
	}

	/**
	 * Obtiene el registro que contenga el shortname recibido como parametro
	 * 
	 * @param shortName
	 * @return DetalleDatoAuxiliarEntity
	 * @throws Exception
	 * 
	 */
	public AuxiliaryDataDetail findDetalleDatoAuxiliarByShortName(String shortName) throws Exception {

		super.setParameters(new ArrayList<ParameterUtilities<?>>());

		if (StringUtils.isEmpty(shortName)) {
			throw new Exception("ShortName no valid: El nombre corto no puede ser nulo");
		}
		StringBuilder filter = new StringBuilder("shortName = :shortName");

		super.getParameters().add(new ParameterUtilities<String>("shortName", shortName));

		AuxiliaryDataDetail detalleDatoAuxiliarEntity = super.serviceDao.find(AuxiliaryDataDetail.class,
				filter.toString(), super.getParameters());

		return detalleDatoAuxiliarEntity;
	}
}
