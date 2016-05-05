package job.model;

import java.io.Serializable;
import javax.persistence.*;

/**
 * The primary key class for the SPESTJAD database table.
 * 
 */
@Embeddable
public class SpestjadPK implements Serializable {
	//default serial version id, required for serializable classes.
	private static final long serialVersionUID = 1L;

	@Column(name="DEPT_ID")
	private String deptId;

	@Column(name="JOB_AMOUNT_DETAIL_ID")
	private String jobAmountDetailId;

    public SpestjadPK() {
    }
	public String getDeptId() {
		return this.deptId;
	}
	public void setDeptId(String deptId) {
		this.deptId = deptId;
	}
	public String getJobAmountDetailId() {
		return this.jobAmountDetailId;
	}
	public void setJobAmountDetailId(String jobAmountDetailId) {
		this.jobAmountDetailId = jobAmountDetailId;
	}

	public boolean equals(Object other) {
		if (this == other) {
			return true;
		}
		if (!(other instanceof SpestjadPK)) {
			return false;
		}
		SpestjadPK castOther = (SpestjadPK)other;
		return 
			this.deptId.equals(castOther.deptId)
			&& this.jobAmountDetailId.equals(castOther.jobAmountDetailId);

    }
    
	public int hashCode() {
		final int prime = 31;
		int hash = 17;
		hash = hash * prime + this.deptId.hashCode();
		hash = hash * prime + this.jobAmountDetailId.hashCode();
		
		return hash;
    }
	@Override
	public String toString() {
		return "SpestjadPK [deptId=" + deptId + ", jobAmountDetailId="
				+ jobAmountDetailId + "]";
	}
	
	
}