package com.matricula.base.domain;

import java.io.Serializable;
import java.util.List;

import org.apache.commons.lang.StringUtils;

import com.matricula.base.dao.GenericDao;
import com.matricula.services.UtilitiesDbService;
import com.matricula.utilities.impl.Action;
import com.matricula.utilities.impl.ParameterUtilities;

public class ActionBean implements Serializable {


	private static final long serialVersionUID = -4163811093498814471L;
	/**
	 * Variable que almacena la accion que se va a realizar en pantalla
	 */
	protected String action;
	/**
	 * Variable que almacena los mensajes que se presentarán en pantalla
	 */
	protected String dialogMessage;

	/**
	 * Lista de parametros
	 */
	protected List<ParameterUtilities<?>> parameters;

	protected GenericDao<Serializable> serviceDao;

	protected UtilitiesDbService utilitiesDbService;

	/********************************************************
	 * GETTERS AND SETTERS
	 * 
	 *********************************************************/

	public String getAction() {
		return action;
	}

	public void setAction(String action) {
		this.action = action;
	}

	public String getDialogMessage() {
		return dialogMessage;
	}

	public void setDialogMessage(String dialogMessage) {
		this.dialogMessage = dialogMessage;
	}

	public List<ParameterUtilities<?>> getParameters() {
		return parameters;
	}

	public void setParameters(List<ParameterUtilities<?>> parameters) {
		this.parameters = parameters;
	}

	public GenericDao<Serializable> getServiceDao() {
		return serviceDao;
	}

	public void setServiceDao(GenericDao<Serializable> serviceDao) {
		this.serviceDao = serviceDao;
	}

	public UtilitiesDbService getUtilitiesDbService() {
		return utilitiesDbService;
	}

	public void setUtilitiesDbService(UtilitiesDbService utilitiesDbService) {
		this.utilitiesDbService = utilitiesDbService;
	}

	public boolean isCreating() {
		return StringUtils.equals(this.action, Action.CREATE.getValue());
	}

	public void setCreating() {
		this.action = Action.CREATE.getValue();
	}

	public boolean isEditing() {
		return StringUtils.equals(this.action, Action.EDIT.getValue());
	}

	public void setEditing() {
		this.action = Action.EDIT.getValue();
	}

	public boolean isQuerying() {
		return StringUtils.equals(this.action, Action.QUERYING.getValue());
	}

	public void setQuerying() {
		this.action = Action.QUERYING.getValue();
	}

	public boolean isNotAction() {
		return StringUtils.equals(this.action, Action.NOTACTION.getValue());
	}

	public void setNotAction() {
		this.action = Action.NOTACTION.getValue();
	}

}
