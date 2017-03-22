/**
 * @ (#) BaseController.java
 * Project     : SIMSUI
 * File        : BaseController.java
 * Author      : Ninganna.c
 * Company     : 
 * Date Created: 20/Mar/2017
 *
 * ========================================================================================================================
 *  No | Modified date |      Modified by     |  Reason
 * ========================================================================================================================
 *  1.   
 * ========================================================================================================================
 */
package com.simsui.controller;

import java.io.Serializable;
import java.util.Map;
import java.util.MissingResourceException;
import java.util.ResourceBundle;
import javax.faces.application.FacesMessage;
import javax.faces.context.FacesContext;
import javax.servlet.ServletContext;
import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Value;

import com.simsui.common.Utility;
import com.simsui.model.UserModel;

/**
 * BaseController class contains common methods that can be used in all Spring
 * Controller class All UI controller class need to extend this class.
 * 
 * @author Ninganna.c
 *
 */
public class BaseController implements Serializable {

	/** The Constant serialVersionUID. */
	private static final long serialVersionUID = 6288618563087026619L;

	/** The Constant LOGGER. */
	private static final Logger LOGGER = Logger.getLogger(BaseController.class);

	/** The resource bundle. */
	private ResourceBundle resourceBundle;

	/** The error message. */
	private String errorMessage;

	@Value("${APPLICATION_URL}")
	private String URL_PATH;

	/**
	 * Gets the error message.
	 *
	 * @return the error message
	 */
	public String getErrorMessage() {
		return errorMessage;
	}

	/**
	 * @return the uRL_PATH
	 */
	public String getURL_PATH() {
		return URL_PATH;
	}

	/**
	 * @param uRL_PATH
	 *            the uRL_PATH to set
	 */
	public void setURL_PATH(String uRL_PATH) {
		URL_PATH = uRL_PATH;
	}

	/**
	 * Method to set the error message.
	 *
	 * @param errorMessage
	 *            the new error message
	 */
	public void setErrorMessage(String errorMessage) {
		this.errorMessage = errorMessage;
		switch (errorMessage) {
		case "SAVE_ERROR":
			FacesContext.getCurrentInstance().addMessage(null, new FacesMessage(FacesMessage.SEVERITY_ERROR,
					getMessage("sims.exception.save_error"), "Error in saveOrUpdate"));
			break;
		case "EMAIL_EXISTS":
			FacesContext.getCurrentInstance().addMessage(null, new FacesMessage(FacesMessage.SEVERITY_ERROR,
					getMessage("sims.email.exists"), "Duplictae Email"));
		case "ARCHIVE_FAIL":
			FacesContext.getCurrentInstance().addMessage(null, new FacesMessage(FacesMessage.SEVERITY_ERROR,
					getMessage("sims.archive.fail"), "Archive Fails"));
		case "ADD_ASSOCIATION_FAIL":
			FacesContext.getCurrentInstance().addMessage(null, new FacesMessage(FacesMessage.SEVERITY_ERROR,
					getMessage("sims.addAssociation.fail"), "Add Association Fails"));
		default:
			break;
		}
	}

	/**
	 * Method to get the real path.
	 *
	 * @return the real path
	 */
	public String getRealPath() {
		ServletContext ctx = (ServletContext) FacesContext.getCurrentInstance().getExternalContext().getContext();
		return ctx.getRealPath("/");
	}

	/**
	 * Method to get the message.
	 *
	 * @param key
	 *            the key
	 * @return the message
	 */
	public String getMessage(String key) {
		String sMessage = "";
		FacesContext context = FacesContext.getCurrentInstance();
		resourceBundle = context.getApplication().getResourceBundle(context, "msg");
		try {
			sMessage = resourceBundle.getString(key);
		} catch (MissingResourceException e) {
			sMessage = "??" + key + "??";
			LOGGER.info("getMessage", e);
		}
		return sMessage;
	}

	/**
	 * Method to get the logged in user first name.
	 *
	 * @return the logged in user first name
	 */
	public String getLoggedInUserFirstName() {
		return (String) getValue("getFirstName");
	}

	/**
	 * Method to get the logged in user id.
	 *
	 * @return the logged in user id
	 */
	public String getLoggedInUserId() {
		return (String) getValue("getLoginId");
	}

	/**
	 * Method to get the logged in user seq id.
	 *
	 * @return the logged in user seq id
	 */
	public Integer getLoggedInUserSeqId() {
		return (Integer) getValue("getUserId");
	}

	/**
	 * Method to get the logged in user type.
	 *
	 * @return the logged in user type
	 */
	public String getLoggedInUserType() {
		return (String) getValue("getUserType");
	}

	/**
	 * Method to get the value of user.
	 *
	 * @param sMethod
	 *            the s method
	 * @return the value
	 */
	private Object getValue(String sMethod) {
		UserModel user = (UserModel) getSession("SIMS_USER");
		return Utility.getMethodValue(user, sMethod);
	}

	/**
	 * Method to get the parameter.
	 *
	 * @param sParam
	 *            the s param
	 * @return the parameter
	 */
	public Object getParameter(String sParam) {
		Map<String, String> params = FacesContext.getCurrentInstance().getExternalContext().getRequestParameterMap();
		return params.get(sParam);
	}

	/**
	 * Method to Set the session object.
	 *
	 * @param sessionName
	 *            the session name
	 * @param sessionObject
	 *            the session object
	 */
	public void setSession(final String sessionName, final Object sessionObject) {
		FacesContext.getCurrentInstance().getExternalContext().getSessionMap().put(sessionName, sessionObject);
	}

	/**
	 * Method to get the session object.
	 *
	 * @param sessionName
	 *            the session name
	 * @return the session
	 */
	public Object getSession(final String sessionName) {
		return FacesContext.getCurrentInstance().getExternalContext().getSessionMap().get(sessionName);
	}

	/**
	 * Method to Clear session object.
	 */
	public void clear() {
		LOGGER.info("Clearing Session Object");
		FacesContext.getCurrentInstance().getExternalContext().getSessionMap().clear();
	}
}
