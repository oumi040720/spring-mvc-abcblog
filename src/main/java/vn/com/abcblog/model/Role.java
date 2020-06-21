package vn.com.abcblog.model;

import java.io.Serializable;

public class Role extends Abstract implements Serializable {

	private static final long serialVersionUID = -6776614958409084951L;

	private String roleName;

	private String roleCode;

	public String getRoleName() {
		return roleName;
	}

	public void setRoleName(String roleName) {
		this.roleName = roleName;
	}

	public String getRoleCode() {
		return roleCode;
	}

	public void setRoleCode(String roleCode) {
		this.roleCode = roleCode;
	}

	public static long getSerialversionuid() {
		return serialVersionUID;
	}

}
