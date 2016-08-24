package com.matricula.services;

import java.util.List;

import com.matricula.row.AuxiliaryDataDetail;

/**
 * 
 * @author Edward Alexis Ortiz
 * @since 28/12/2014
 * @category database services
 * 
 *           This interface represents methods to manipulate data from database
 *
 */
public interface UtilitiesDbService {

	List<AuxiliaryDataDetail> findDetalleDatoAuxiliarByGroup(String group)
			throws Exception;

	AuxiliaryDataDetail findDetalleDatoAuxiliarByShortName(
			String shortName) throws Exception;
}
