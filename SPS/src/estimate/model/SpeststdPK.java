package estimate.model;

import java.io.Serializable;
import javax.persistence.*;

/**
 * The primary key class for the SPESTSTD database table.
 * 
 */
@Embeddable
public class SpeststdPK implements Serializable {
	//default serial version id, required for serializable classes.
	private static final long serialVersionUID = 1L;

	@Column(name="ESTIMATE_NO")
	private String estimateNo;

	@Column(name="DEPT_ID")
	private String deptId;

    public SpeststdPK() {
    }
	public String getEstimateNo() {
		return this.estimateNo;
	}
	public void setEstimateNo(String estimateNo) {
		this.estimateNo = estimateNo;
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
		if (!(other instanceof SpeststdPK)) {
			return false;
		}
		SpeststdPK castOther = (SpeststdPK)other;
		return 
			this.estimateNo.equals(castOther.estimateNo)
			&& this.deptId.equals(castOther.deptId);

    }
    
	public int hashCode() {
		final int prime = 31;
		int hash = 17;
		hash = hash * prime + this.estimateNo.hashCode();
		hash = hash * prime + this.deptId.hashCode();
		
		return hash;
    }
	@Override
	public String toString() {
		return "SpeststdPK [estimateNo=" + estimateNo + ", deptId=" + deptId
				+ "]";
	}
	
	
}