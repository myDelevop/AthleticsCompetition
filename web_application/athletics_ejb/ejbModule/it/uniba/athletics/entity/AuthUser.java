package it.uniba.athletics.entity;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.JoinTable;
import javax.persistence.ManyToMany;
import javax.persistence.NamedQuery;
import javax.persistence.OneToOne;
import javax.persistence.Table;
import javax.persistence.Transient;

import org.apache.log4j.Logger;
import org.modelmapper.ModelMapper;

import it.uniba.athletics.common.dto.AuthUserDTO;
import it.uniba.athletics.common.exception.BackEndException;
import it.uniba.athletics.util.Constants;


/**
 * The persistent class for the AUTH_USER database table.
 * 
 */
@Entity
@Table(name="AUTH_USER")
@NamedQuery(name="AuthUser.findAll", query="SELECT a FROM AuthUser a")
public class AuthUser implements Serializable {
	private static final long serialVersionUID = 1L;

	static final Logger LOGGER = Logger.getLogger(AuthUser.class);

	@Id
	@GeneratedValue(strategy=GenerationType.AUTO)
	private long iduser;

	private String email;

	private String name;

	private String surname;

	@Column(name="TAX_CODE")
	private String taxCode;

	private String username;

	//bi-directional one-to-one association to AuthPsw
	@OneToOne(mappedBy="authUser")
	private AuthPsw authPsw;

	//bi-directional many-to-many association to AuthGroup
	@ManyToMany
	@JoinTable(
		name="AUTH_USER_GROUP"
		, joinColumns={
			@JoinColumn(name="IDUSER")
			}
		, inverseJoinColumns={
			@JoinColumn(name="IDGROUP")
			}
		)
	private List<AuthGroup> authGroups;
	
	@Transient
	private boolean founded;

	@Transient
	private boolean isLogged;

	public AuthUser() {}

	public AuthUser(String username) {
		this.username = username;
	}
	
	public AuthUserDTO convertToDTO() throws BackEndException {
		LOGGER.info("converting AuthUser entity in DTO");

		AuthUserDTO dto = new ModelMapper().map(this, AuthUserDTO.class);
		try {
			dto.setAuthPsw(this.authPsw.getPsw());
		} catch(NullPointerException e) {
			// se è Null non deve andare in errore
		} catch(Exception e) {
			LOGGER.error("Error in AuthUser convertToDTO: " + Constants.ERR_CONVERSION, e);
			throw new BackEndException(Constants.ERR_CONVERSION);			
		}
		
		return dto;
	}

	public long getIduser() {
		return this.iduser;
	}

	public void setIduser(long iduser) {
		this.iduser = iduser;
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

	public AuthPsw getAuthPsw() {
		return this.authPsw;
	}

	public void setAuthPsw(AuthPsw authPsw) {
		this.authPsw = authPsw;
	}

	public List<AuthGroup> getAuthGroups() {
		return this.authGroups;
	}

	public void setAuthGroups(List<AuthGroup> authGroups) {
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
		return "\nENTITY:\nidUser -> " + this.iduser + "\n"
			.concat("email -> " + this.email + "\n")
			.concat("name -> " + this.name + "\n")
			.concat("surname -> " + this.surname + "\n")
			.concat("taxCode -> " + this.taxCode + "\n")
			.concat("username -> " + this.username);
	}
	
	
	public static void main(String[] args) {
		List<AuthUser> users = new ArrayList<AuthUser>();
		AuthUser u = new AuthUser();
		u.setIduser(3L);
		u.setName("ROCCO");
		u.setUsername("ROCL");
		users.add(u);
		u.setIduser(4L);
		u.setName("CALIANDRO");
		u.setUsername("ROCK");
		users.add(u);
		
		System.out.println(users.toString());
	}

}