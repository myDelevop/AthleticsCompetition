package it.uniba.athletics.entity;

import java.io.Serializable;
import javax.persistence.*;


/**
 * The persistent class for the AUTH_PSW database table.
 * 
 */
@Entity
@Table(name="AUTH_PSW")
@NamedQuery(name="AuthPsw.findAll", query="SELECT a FROM AuthPsw a")
public class AuthPsw implements Serializable {
	private static final long serialVersionUID = 1L;

	@Id
	@GeneratedValue(strategy=GenerationType.AUTO)
	private long idUser;

	private String psw;

	//bi-directional one-to-one association to AuthUser
	@OneToOne
	@JoinColumn(name="IDUSER")
	private AuthUser authUser;

	public AuthPsw() {
	}

	public long getIduser() {
		return this.idUser;
	}

	public void setIduser(long iduser) {
		this.idUser = iduser;
	}

	public String getPsw() {
		return this.psw;
	}

	public void setPsw(String psw) {
		this.psw = psw;
	}

	public AuthUser getAuthUser() {
		return this.authUser;
	}

	public void setAuthUser(AuthUser authUser) {
		this.authUser = authUser;
	}

}