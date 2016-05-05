package estimate.model;

import java.io.Serializable;
import javax.persistence.*;

/**
 * The primary key class for the SPLBSTRT database table.
 * 
 */
@Embeddable
public class SplbstrtPK implements Serializable {
	//default serial version id, required for serializable classes.
	private static final long serialVersionUID = 1L;

	@Column(name="LABOUR_CODE")
	private String labourCode;

	@Column(name="DEPT_ID")
	private String deptId;

    public SplbstrtPK() {
    }
	public String getLabourCode() {
		return this.labourCode;
	}
	public void setLabourCode(String labourCode) {
		this.labourCode = labourCode;
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
		if (!(other instanceof SplbstrtPK)) {
			return false;
		}
		SplbstrtPK castOther = (SplbstrtPK)other;
		return 
			this.labourCode.equals(castOther.labourCode)
			&& this.deptId.equals(castOther.deptId);

    }
    
	public int hashCode() {
		final int prime = 31;
		int hash = 17;
		hash = hash * prime + this.labourCode.hashCode();
		hash = hash * prime + this.deptId.hashCode();
		
		return hash;
    }
	@Override
	public String toString() {
		return "SplbstrtPK [labourCode=" + labourCode + ", deptId=" + deptId
				+ "]";
	}
	
	
}