package application.model;

import java.io.Serializable;
import javax.persistence.*;

/**
 * The primary key class for the SPAPPLON database table.
 * 
 */
@Embeddable
public class SpapplonPK implements Serializable {
	//default serial version id, required for serializable classes.
	private static final long serialVersionUID = 1L;

	@Column(name="APPLICATION_NO")
	private String applicationNo;

	@Column(name="DEPT_ID")
	private String deptId;

    public SpapplonPK() {
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

	public boolean equals(Object other) {
		if (this == other) {
			return true;
		}
		if (!(other instanceof SpapplonPK)) {
			return false;
		}
		SpapplonPK castOther = (SpapplonPK)other;
		return 
			this.applicationNo.equals(castOther.applicationNo)
			&& this.deptId.equals(castOther.deptId);

    }
    
	public int hashCode() {
		final int prime = 31;
		int hash = 17;
		hash = hash * prime + this.applicationNo.hashCode();
		hash = hash * prime + this.deptId.hashCode();
		
		return hash;
    }
	@Override
	public String toString() {
		return "SpapplonPK [applicationNo=" + applicationNo + ", deptId="
				+ deptId + "]";
	}
}