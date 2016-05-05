package job.model;

import java.io.Serializable;
import javax.persistence.*;

/**
 * The primary key class for the SPESTCND database table.
 * 
 */
@Embeddable
public class SpestcndPK implements Serializable {
	//default serial version id, required for serializable classes.
	private static final long serialVersionUID = 1L;

	@Column(name="CONTRACTOR_ID")
	private String contractorId;

	@Column(name="PROJECT_NO")
	private String projectNo;

	@Column(name="DEPT_ID")
	private String deptId;

    public SpestcndPK() {
    }
	public String getContractorId() {
		return this.contractorId;
	}
	public void setContractorId(String contractorId) {
		this.contractorId = contractorId;
	}
	public String getProjectNo() {
		return this.projectNo;
	}
	public void setProjectNo(String projectNo) {
		this.projectNo = projectNo;
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
		if (!(other instanceof SpestcndPK)) {
			return false;
		}
		SpestcndPK castOther = (SpestcndPK)other;
		return 
			this.contractorId.equals(castOther.contractorId)
			&& this.projectNo.equals(castOther.projectNo)
			&& this.deptId.equals(castOther.deptId);

    }
    
	public int hashCode() {
		final int prime = 31;
		int hash = 17;
		hash = hash * prime + this.contractorId.hashCode();
		hash = hash * prime + this.projectNo.hashCode();
		hash = hash * prime + this.deptId.hashCode();
		
		return hash;
    }
	@Override
	public String toString() {
		return "SpestcndPK [contractorId=" + contractorId + ", projectNo="
				+ projectNo + ", deptId=" + deptId + "]";
	}
}