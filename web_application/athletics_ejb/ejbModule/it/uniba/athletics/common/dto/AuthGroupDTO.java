package it.uniba.athletics.common.dto;

import java.io.Serializable;
import java.util.List;


public class AuthGroupDTO implements Serializable {
	
	private static final long serialVersionUID = 1L;

	private long idGroup;
	private String groupName;
	private String groupDescription;
//	private List<AuthUserDTO> authUsers;

	public AuthGroupDTO () {}
	
	public AuthGroupDTO(long idGroup, String groupName, List<AuthUserDTO> authUsers) {
		super();
		this.idGroup = idGroup;
		this.groupName = groupName;
//		this.authUsers = authUsers;
	}
	
	/* public AuthGroup convertToEntity() {
		return new ModelMapper().map(this, AuthGroup.class);
	} */

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
		return groupDescription;
	}

	public void setGroupDescription(String groupDescription) {
		this.groupDescription = groupDescription;
	}

	@Override
	public String toString() {
		return "\nDTO\nidGroup -> " + this.idGroup + "\n"
			.concat("groupName -> " + this.groupName + "\n")
			.concat("groupDescription -> " + this.groupDescription);
	}

}