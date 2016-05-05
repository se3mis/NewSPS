package application.model;

import java.io.Serializable;
import javax.persistence.*;

/**
 * The primary key class for the APPLICATION_REFERENCE database table.
 * 
 */
@Embeddable
public class ApplicationRefBSPK implements Serializable {
	//default serial version id, required for serializable classes.
	private static final long serialVersionUID = 1L;

	@Column(name="APPLICATION_ID")
	private String applicationId;

	@Column(name="DEPT_ID")
	private String deptId;

	@Column(name="APPLICATION_NO")
	private String applicationNo;
	
    public ApplicationRefBSPK() {
    }
	public String getApplicationId() {
		return this.applicationId;
	}
	public void setApplicationId(String applicationId) {
		this.applicationId = applicationId;
	}
	public String getDeptId() {
		return this.deptId;
	}
	public void setDeptId(String deptId) {
		this.deptId = deptId;
	}

	public String getApplicationNo() {
		return applicationNo;
	}
	public void setApplicationNo(String applicationNo) {
		this.applicationNo = applicationNo;
	}
	public boolean equals(Object other) {
		if (this == other) {
			return true;
		}
		if (!(other instanceof ApplicationRefBSPK)) {
			return false;
		}
		ApplicationRefBSPK castOther = (ApplicationRefBSPK)other;
		return 
			this.applicationId.equals(castOther.applicationId)
			&& this.deptId.equals(castOther.deptId);

    }
    
	public int hashCode() {
		final int prime = 31;
		int hash = 17;
		hash = hash * prime + this.applicationId.hashCode();
		hash = hash * prime + this.deptId.hashCode();
		
		return hash;
    }
	@Override
	public String toString() {
		return "ApplicationReferencePK [applicationId=" + applicationId
				+ ", deptId=" + deptId + "]";
	}
	
	
}