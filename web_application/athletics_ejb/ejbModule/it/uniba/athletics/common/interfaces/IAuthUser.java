package it.uniba.athletics.common.interfaces;

import java.util.List;

import javax.ejb.Remote;

import it.uniba.athletics.common.dto.AuthUserDTO;
import it.uniba.athletics.common.exception.BackEndException;

@Remote
public interface IAuthUser {

	public AuthUserDTO getById(Long id) throws BackEndException;
	public List<AuthUserDTO> getAllUsers() throws BackEndException;
	public AuthUserDTO login(String username, String psw) throws BackEndException;
}
