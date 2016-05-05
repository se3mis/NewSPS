package masters.model;

import java.io.Serializable;
import javax.persistence.*;

/**
 * The primary key class for the BANK_BRANCH database table.
 * 
 */
@Embeddable
public class ProvinceBranchJobTypePK implements Serializable {
	//default serial version id, required for serializable classes.
	private static final long serialVersionUID = 1L;
	
	@Column(name="DEPT_ID")
	private String deptId;

	@Column(name="BRANCH_CODE")
	private String branchCode;
	
	@Column(name="JOBTYPECODE")
	private String jobTypeCode;
	
   
	public String getDeptId() {
		return deptId;
	}

	public void setDeptId(String deptId) {
		this.deptId = deptId;
	}

	
    public String getBranchCode() {
		return branchCode;
	}

	public void setBranchCode(String branchCode) {
		this.branchCode = branchCode;
	}

	public String getJobTypeCode() {
		return jobTypeCode;
	}

	public void setJobTypeCode(String jobTypeCode) {
		this.jobTypeCode = jobTypeCode;
	}

	public ProvinceBranchJobTypePK() {
    }
	
	public boolean equals(Object other) {
		if (this == other) {
			return true;
		}
		if (!(other instanceof ProvinceBranchJobTypePK)) {
			return false;
		}
		ProvinceBranchJobTypePK castOther = (ProvinceBranchJobTypePK)other;
		return 
			this.deptId.equals(castOther.deptId)
			&& this.branchCode.equals(castOther.branchCode)
			&& this.jobTypeCode.equals(castOther.jobTypeCode)
			;

    }
    
	public int hashCode() {
		final int prime = 31;
		int hash = 17;
		hash = hash * prime + this.deptId.hashCode();
		hash = hash * prime + this.branchCode.hashCode();
		hash = hash * prime + this.jobTypeCode.hashCode();
		return hash;
    }
	@Override
	public String toString() {
		return "ProvinceBranchJobTypePK [deptId=" + deptId + ", branchCode="
				+ branchCode +",jobTypeCode="+jobTypeCode+ "]";
	}
}