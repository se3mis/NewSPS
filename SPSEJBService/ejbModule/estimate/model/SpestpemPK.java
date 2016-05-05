package estimate.model;

import java.io.Serializable;
import javax.persistence.*;

/**
 * The primary key class for the SPESTPEM database table.
 * 
 */
@Embeddable
public class SpestpemPK implements Serializable {
	//default serial version id, required for serializable classes.
	private static final long serialVersionUID = 1L;

	@Column(name="APPLICATION_NO")
	private String applicationNo;

	@Column(name="DEPT_ID")
	private String deptId;

	@Column(name="PERMISSION_TYPE")
	private String permissionType;

    public SpestpemPK() {
    }
	public String getApplicationNo() {
		return this.applicationNo;
	}
	public void setApplicationNo(String applicationNo) {
		this.applicationNo = applicationNo;
	}
	public String getDeptId() {
		return this.deptId;
	}
	public void setDeptId(String deptId) {
		this.deptId = deptId;
	}
	public String getPermissionType() {
		return this.permissionType;
	}
	public void setPermissionType(String permissionType) {
		this.permissionType = permissionType;
	}

	public boolean equals(Object other) {
		if (this == other) {
			return true;
		}
		if (!(other instanceof SpestpemPK)) {
			return false;
		}
		SpestpemPK castOther = (SpestpemPK)other;
		return 
			this.applicationNo.equals(castOther.applicationNo)
			&& this.deptId.equals(castOther.deptId)
			&& this.permissionType.equals(castOther.permissionType);

    }
    
	public int hashCode() {
		final int prime = 31;
		int hash = 17;
		hash = hash * prime + this.applicationNo.hashCode();
		hash = hash * prime + this.deptId.hashCode();
		hash = hash * prime + this.permissionType.hashCode();
		
		return hash;
    }
	@Override
	public String toString() {
		return "SpestpemPK [applicationNo=" + applicationNo + ", deptId="
				+ deptId + ", permissionType=" + permissionType + "]";
	}
	
	
}