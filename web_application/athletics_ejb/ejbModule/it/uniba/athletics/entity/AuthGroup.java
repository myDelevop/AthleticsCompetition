package it.uniba.athletics.entity;

import java.io.Serializable;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.NamedQuery;
import javax.persistence.Table;

import org.apache.log4j.Logger;
import org.modelmapper.ModelMapper;

import it.uniba.athletics.common.dto.AuthGroupDTO;
import it.uniba.athletics.common.exception.BackEndException;
import it.uniba.athletics.util.Constants;


/**
 * The persistent class for the AUTH_GROUP database table.
 * 
 */
@Entity
@Table(name="AUTH_GROUP")
@NamedQuery(name="AuthGroup.findAll", query="SELECT a FROM AuthGroup a")
public class AuthGroup implements Serializable {
	private static final long serialVersionUID = 1L;

	static final Logger LOGGER = Logger.getLogger(AuthGroup.class);

	@Id
	@GeneratedValue(strategy=GenerationType.AUTO)
	private long idGroup;

	private String groupName;
	
	private String groupDescription;

	/*bi-directional many-to-many association to AuthUser
	@ManyToMany(mappedBy="authGroups")
	private List<AuthUser> authUsers;*/

	public AuthGroup() {}
	
	public AuthGroupDTO convertToDTO() throws BackEndException {
		LOGGER.info("converting AuthGroup entity in DTO");
		try {
			return new ModelMapper().map(this, AuthGroupDTO.class);			
		} catch(Exception e) {
			LOGGER.error("Error in AuthGroup convertToDTO: " + Constants.ERR_CONVERSION, e);
			throw new BackEndException(Constants.ERR_CONVERSION);			
		}
	}

	public long getIdGroup() {
		return this.idGroup;
	}

	public void setIdGroup(long idGroup) {
		this.idGroup = idGroup;
	}

	public String getGroupName() {
		return this.groupName;
	}

	public void setGroupName(String groupName) {
		this.groupName = groupName;
	}

	public String getGroupDescription() {
		return this.groupDescription;
	}

	public void setGroupDescription(String groupDescription) {
		this.groupDescription= groupDescription;
	}

	@Override
	public String toString() {
		return "\nENTITY\nidGroup -> " + this.idGroup + "\n"
			.concat("groupName -> " + this.groupName + "\n")
			.concat("groupDescription -> " + this.groupDescription);
	}

}