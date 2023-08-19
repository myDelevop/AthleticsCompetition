package it.uniba.athletics.common.dto;

import java.io.Serializable;
import java.util.List;


public class AuthUserDTO implements Serializable {

	private static final long serialVersionUID = 1L;
	
	private long idUser;
	private String email;
	private String name;
	private String surname;
	private String taxCode;
	private String username;
	private String authPsw;
	private List<AuthGroupDTO> authGroups;
	private boolean isLogged;
	private boolean founded;
	
	public AuthUserDTO() {}
	
	public AuthUserDTO(long idUser, String email, String name, String surname, String taxCode, String username,
			String authPsw, List<AuthGroupDTO> authGroups) {
		super();
		this.idUser = idUser;
		this.email = email;
		this.name = name;
		this.surname = surname;
		this.taxCode = taxCode;
		this.username = username;
		this.authPsw = authPsw;
		this.authGroups = authGroups;
	}

	/* public AuthUser convertToEntity() {
		return new ModelMapper().map(this, AuthUser.class);
	} */

	public long getIdUser() {
		return this.idUser;
	}

	public void setIdUser(long idUser) {
		this.idUser = idUser;
	}

	public String getEmail() {
		return this.email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public String getName() {
		return this.name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getSurname() {
		return this.surname;
	}

	public void setSurname(String surname) {
		this.surname = surname;
	}

	public String getTaxCode() {
		return this.taxCode;
	}

	public void setTaxCode(String taxCode) {
		this.taxCode = taxCode;
	}

	public String getUsername() {
		return this.username;
	}

	public void setUsername(String username) {
		this.username = username;
	}

	public String getAuthPsw() {
		return this.authPsw;
	}

	public void setAuthPsw(String authPsw) {
		this.authPsw = authPsw;
	}

	public List<AuthGroupDTO> getAuthGroups() {
		return this.authGroups;
	}

	public void setAuthGroups(List<AuthGroupDTO> authGroups) {
		this.authGroups = authGroups;
	}
	
	public boolean isFounded() {
		return founded;
	}

	public void setFounded(boolean founded) {
		this.founded = founded;
	}
	
	public boolean isLogged() {
		return isLogged;
	}

	public void setLogged(boolean isLogged) {
		this.isLogged = isLogged;
	}

	
	@Override
	public String toString() {
		return "\nDTO\nidUser -> " + this.idUser + "\n"
			.concat("email -> " + this.email + "\n")
			.concat("name -> " + this.name + "\n")
			.concat("surname -> " + this.surname + "\n")
			.concat("taxCode -> " + this.taxCode + "\n")
			.concat("username -> " + this.username);
	}


}