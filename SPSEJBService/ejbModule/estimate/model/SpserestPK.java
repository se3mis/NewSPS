package estimate.model;

import java.io.Serializable;
import javax.persistence.*;

/**
 * The primary key class for the SPSEREST database table.
 * 
 */
@Embeddable
public class SpserestPK implements Serializable {
	//default serial version id, required for serializable classes.
	private static final long serialVersionUID = 1L;

	@Column(name="APPLICATION_NO")
	private String applicationNo;

	@Column(name="DEPT_ID")
	private String deptId;

    public SpserestPK() {
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
		if (!(other instanceof SpserestPK)) {
			return false;
		}
		SpserestPK castOther = (SpserestPK)other;
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
}