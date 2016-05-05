package estimate.model;

import java.io.Serializable;
import javax.persistence.*;

/**
 * The primary key class for the APPLICATION_REFERENCE database table.
 * 
 */
@Embeddable
public class EstimateReferencePK implements Serializable {
	//default serial version id, required for serializable classes.
	private static final long serialVersionUID = 1L;

	@Column(name="SESTIMATE_NO")
	private String standardEstimateNo;

	@Column(name="DEPT_ID")
	private String deptId;
	
	@Column(name="WESTIMATE_NO")
	private String workEstimateNo;

    public EstimateReferencePK() {
    }
    public String getWorkEstimateNo() {
		return workEstimateNo;
	}

	public void setWorkEstimateNo(String workEstimateNo) {
		this.workEstimateNo = workEstimateNo;
	}
	public String getDeptId() {
		return this.deptId;
	}
	public void setDeptId(String deptId) {
		this.deptId = deptId;
	}

	public String getStandardEstimateNo() {
		return standardEstimateNo;
	}

	public void setStandardEstimateNo(String standardEstimateNo) {
		this.standardEstimateNo = standardEstimateNo;
	}

	
	public boolean equals(Object other) {
		if (this == other) {
			return true;
		}
		if (!(other instanceof EstimateReferencePK)) {
			return false;
		}
		EstimateReferencePK castOther = (EstimateReferencePK)other;
		return 
			this.standardEstimateNo.equals(castOther.standardEstimateNo)&&
			this.workEstimateNo.equals(castOther.workEstimateNo)
			&& this.deptId.equals(castOther.deptId);

    }
    
	public int hashCode() {
		final int prime = 31;
		int hash = 17;
		//hash = hash * prime + this.workEstimateNo.hashCode();
		hash = hash * prime + this.standardEstimateNo.hashCode();
		hash = hash * prime + this.workEstimateNo.hashCode();
		hash = hash * prime + this.deptId.hashCode();
		
		return hash;
    }
	@Override
	public String toString() {
		return "EstimateReferencePK [standardEstimateNo=" + standardEstimateNo+
	
				", deptId=" + deptId + "]";
	}
	
	
}