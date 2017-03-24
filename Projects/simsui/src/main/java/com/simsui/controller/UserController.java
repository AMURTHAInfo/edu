/**
 * @ (#) UserController.java
 * Project     : SIMS
 * File        : UserController.java
 * Author      : ninganna.c
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

import java.io.IOException;
import java.io.Serializable;
import java.util.List;
import javax.faces.application.FacesMessage;
import javax.faces.context.ExternalContext;
import javax.faces.context.FacesContext;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;
import org.apache.log4j.Logger;
import org.apache.shiro.SecurityUtils;
import org.apache.shiro.authc.AuthenticationException;
import org.apache.shiro.authc.UnknownAccountException;
import org.apache.shiro.authc.UsernamePasswordToken;
import org.apache.shiro.authc.credential.PasswordService;
import org.apache.shiro.mgt.SecurityManager;
import org.apache.shiro.realm.Realm;
import org.apache.shiro.subject.Subject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Component;

import com.simsui.common.FileOperations;
import com.simsui.common.SimsException;
import com.simsui.common.Utility;
import com.simsui.model.UserModel;
import com.simsui.service.UserServiceInterface;

/**
 * This is Controller Class for Users to perform operations like login and logout.
 * 
 * @author ninganna.c
 */
@Component(value = "user")
@Scope(value = "session")
public class UserController extends BaseController implements Serializable {

	private static final Logger LOGGER = Logger.getLogger(UserController.class);
	private static final long serialVersionUID = -8249427316647583842L;
	private String firstName;
	private String lastName;
	private String userType;
	private String email;
	private String status;
	private String message;
	private String password;
	private String loginId;
	private Integer userId;
	private boolean rememberMe;
	private boolean accessSUPERADMIN = false;
	private List<?> listOfIds;
	Subject currentUser;
/*	@Autowired
	Realm realm;*/
	@Autowired
	SecurityManager sm;
	@Autowired
	UserServiceInterface userServiceInterface;
	@Autowired
	private HttpServletRequest request;
	@Autowired
	PasswordService defaultPasswordService;

	/**
	 * @return the message
	 */
	public String getMessage() {
		return message;
	}

	/**
	 * @param message
	 *            the message to set
	 */
	public void setMessage(String message) {
		this.message = message;
	}

	/**
	 * @return the userId
	 */
	public Integer getUserId() {
		return userId;
	}

	/**
	 * @param userId
	 *            the userId to set
	 */
	public void setUserId(Integer userId) {
		this.userId = userId;
	}

	/**
	 * @return the firstName
	 */
	public String getFirstName() {
		return firstName;
	}

	/**
	 * @param firstName
	 *            the firstName to set
	 */
	public void setFirstName(String firstName) {
		this.firstName = firstName;
	}

	/**
	 * @return the lastName
	 */
	public String getLastName() {
		return lastName;
	}

	/**
	 * @param lastName
	 *            the lastName to set
	 */
	public void setLastName(String lastName) {
		this.lastName = lastName;
	}

	/**
	 * @return the userType
	 */
	public String getUserType() {
		return userType;
	}

	/**
	 * @param userType
	 *            the userType to set
	 */
	public void setUserType(String userType) {
		this.userType = userType;
	}

	/**
	 * @return the email
	 */
	public String getEmail() {
		return email;
	}

	/**
	 * @param email
	 *            the email to set
	 */
	public void setEmail(String email) {
		this.email = email;
	}

	/**
	 * @return the loginId
	 */
	public String getLoginId() {
		return loginId;
	}

	/**
	 * @param loginId
	 *            the loginId to set
	 */
	public void setLoginId(String loginId) {
		this.loginId = loginId;
	}

	/**
	 * @return the status
	 */
	public String getStatus() {
		return status;
	}

	/**
	 * @param status
	 *            the status to set
	 */
	public void setStatus(String status) {
		this.status = status;
	}

	/**
	 * @return the password
	 */
	public String getPassword() {
		return password;
	}

	/**
	 * @param password
	 *            the password to set
	 */
	public void setPassword(String password) {
		this.password = password;
	}

	/**
	 * @return the rememberMe
	 */
	public boolean isRememberMe() {
		return rememberMe;
	}

	/**
	 * @param rememberMe
	 *            the rememberMe to set
	 */
	public void setRememberMe(boolean rememberMe) {
		this.rememberMe = rememberMe;
	}

	/**
	 * @return the accessSUPERADMIN
	 */
	public boolean isAccessSUPERADMIN() {
		return accessSUPERADMIN;
	}

	/**
	 * @param accessSUPERADMIN
	 *            the accessSUPERADMIN to set
	 */
	public void setAccessSUPERADMIN(boolean accessSUPERADMIN) {
		this.accessSUPERADMIN = accessSUPERADMIN;
	}

	/**
	 * @return the listOfIds
	 */
	public List<?> getListOfIds() {
		return listOfIds;
	}

	/**
	 * @param listOfIds
	 *            the listOfIds to set
	 */
	public void setListOfIds(List<?> listOfIds) {
		this.listOfIds = listOfIds;
	}

	/**
	 * @return the userServiceInterface
	 */
	public UserServiceInterface getUserServiceInterface() {
		return userServiceInterface;
	}

	/**
	 * @param userServiceInterface
	 *            the userServiceInterface to set
	 */
	public void setUserServiceInterface(UserServiceInterface userServiceInterface) {
		this.userServiceInterface = userServiceInterface;
	}

	/**
	 * @return the request
	 */
	public HttpServletRequest getRequest() {
		return request;
	}

	/**
	 * @param request
	 *            the request to set
	 */
	public void setRequest(HttpServletRequest request) {
		this.request = request;
	}

	/**
	 * @return the currentUser
	 */
	public Subject getCurrentUser() {
		return currentUser;
	}

	/**
	 * @param currentUser
	 *            the currentUser to set
	 */
	public void setCurrentUser(Subject currentUser) {
		this.currentUser = currentUser;
	}

	/**
	 * @return the sm
	 */
	public SecurityManager getSm() {
		return sm;
	}

	/**
	 * @param sm
	 *            the sm to set
	 */
	public void setSm(SecurityManager sm) {
		this.sm = sm;
	}

	/**
	 * @return the defaultPasswordService
	 */
	public PasswordService getDefaultPasswordService() {
		return defaultPasswordService;
	}

	/**
	 * @param defaultPasswordService
	 *            the defaultPasswordService to set
	 */
	public void setDefaultPasswordService(PasswordService defaultPasswordService) {
		this.defaultPasswordService = defaultPasswordService;
	}

	
	/**
	 * Method to control Login process.
	 *
	 * @return the string
	 * @throws AuthenticationException the authentication exception
	 */
	public String login() throws AuthenticationException {
		LOGGER.info("In UserController : Login");
		System.out.println(loginId+"here in with "+ password);
		UserModel sessionUser = null;
		UsernamePasswordToken token = new UsernamePasswordToken(loginId, password.toCharArray());
		token.setRememberMe(rememberMe);
		SecurityUtils.setSecurityManager(sm);
		currentUser = SecurityUtils.getSubject();
		try {
			sessionUser = userServiceInterface.getUser(loginId, password);
			System.out.println(sessionUser.getFirstName()+sessionUser.getServiceStaus());
			setSession("ONLINETEST_USER", sessionUser);
			HttpSession session = request.getSession();
		} catch (SimsException e) {
			LOGGER.error("Error in login call getUser OR getAllFeaturePermissions", e);
			setErrorMessage("GENERIC_ERROR");
		} catch (UnknownAccountException uae) {
			FacesContext.getCurrentInstance().addMessage(null, new FacesMessage(FacesMessage.SEVERITY_ERROR,
					getMessage("myfinancecommunity.login.unknownaccountexception"), "Unknown Account!"));
			LOGGER.info("Unknown Account!", uae);
		} catch (AuthenticationException ae) {
		}
		if (sessionUser != null) {
			/*this.firstName = "Welcome " + this.getLoggedInUserFirstName();
			if (sessionUser.getUserType().equals("SuperAdmin")) {
				accessSUPERADMIN = true;
			}*/
			return "homepage";
		} else {
			message = "LoginId or password do not match.";
			return "homepage";
		}

	}

	
	/**
	 * Method to control Forgot password.
	 *
	 * @throws SimsException the online test exception
	 */
	public void forgotPassword() throws SimsException {/*
		UserModel user = new User();
		user = userServiceInterface.getUserByEmail(email);
		if (user.getEmail().equals(this.email)) {
			mailServiceImpl.sendmail();
			message = "Check your mail to reset password";
		} else {
			message = "Email id is not registered.";
		}

	*/}

	
	/**
	 *Method for Login id validation.
	 *
	 * @throws SimsException 
	 */
	public void loginIdValidation() throws SimsException {
		try {
			listOfIds = userServiceInterface.getUserByloginId();
		} catch (SimsException e) {
			LOGGER.error("Error in getting loginIds OR getUserByloginId", e);
			setErrorMessage("GENERIC_ERROR");
		}

	}

	/**
	 * This is used for logout functionality and also clear session values
	 */
	public void logout() {
		LOGGER.info("In LoginController : logout");
		String fallbackUrl = null;
		try {
			fallbackUrl = getURL_PATH() + "/login.xhtml";
			ExternalContext externalContext = FacesContext.getCurrentInstance().getExternalContext();
			externalContext.invalidateSession();
			externalContext.redirect(fallbackUrl);
		} catch (IOException e) {
			LOGGER.error("Error in logout call redirect", e);
		}
	}

}
