package it.uniba.athletics.service;

import java.util.ArrayList;
import java.util.List;

import javax.ejb.EJB;
import javax.ejb.Stateless;

import org.apache.log4j.Logger;

import it.uniba.athletics.common.dto.AuthUserDTO;
import it.uniba.athletics.common.exception.BackEndException;
import it.uniba.athletics.common.interfaces.IAuthUser;
import it.uniba.athletics.dao.AuthUserDao;
import it.uniba.athletics.entity.AuthUser;

@Stateless
public class AuthUserEjb implements IAuthUser {
    static final Logger LOGGER = Logger.getLogger(AuthUserEjb.class);
	
	@EJB
	private AuthUserDao userEjb;
	
	public AuthUserDTO getById(Long id) throws BackEndException {
		LOGGER.info("Calling EJB Mehtod getByID...");
		AuthUserDTO userDTO = null;
		AuthUser user = userEjb.getById(id);
		if(user != null)
			userDTO = user.convertToDTO();
		return userDTO;
	}

	@Override
	public AuthUserDTO login(String username, String psw) throws BackEndException {
		LOGGER.info("Calling EJB Mehtod login...");
		AuthUserDTO userDTO = null;
		AuthUser user = userEjb.login(username, psw);
		if(user != null)
			userDTO = user.convertToDTO();
		return userDTO;
	}

	@Override
	public List<AuthUserDTO> getAllUsers() throws BackEndException {
		LOGGER.info("Calling EJB Mehtod getAllUsers...");
		List<AuthUser> entities = userEjb.getAllUsers();
		List<AuthUserDTO> users = new ArrayList<AuthUserDTO>();
		
		for(AuthUser au: entities) {
			LOGGER.debug("Converting entity" + au.getUsername() + " in DTO");
			users.add(au.convertToDTO());
		}
		LOGGER.info("Retourned " + users.size() + "users from EJB");		
		return users;
	}

}
