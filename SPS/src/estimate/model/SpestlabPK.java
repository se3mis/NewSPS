package estimate.model;

import java.io.Serializable;
import javax.persistence.*;

/**
 * The primary key class for the SPESTLAB database table.
 * 
 */
@Embeddable
public class SpestlabPK implements Serializable {
	//default serial version id, required for serializable classes.
	private static final long serialVersionUID = 1L;

	@Column(name="ESTIMATE_NO")
	private String estimateNo;

	@Column(name="DEPT_ID")
	private String deptId;

	@Column(name="LABOUR_CODE")
	private String labourCode;

    public SpestlabPK() {
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
	public String getLabourCode() {
		return this.labourCode;
	}
	public void setLabourCode(String labourCode) {
		this.labourCode = labourCode;
	}

	public boolean equals(Object other) {
		if (this == other) {
			return true;
		}
		if (!(other instanceof SpestlabPK)) {
			return false;
		}
		SpestlabPK castOther = (SpestlabPK)other;
		return 
			this.estimateNo.equals(castOther.estimateNo)
			&& this.deptId.equals(castOther.deptId)
			&& this.labourCode.equals(castOther.labourCode);

    }
    
	public int hashCode() {
		final int prime = 31;
		int hash = 17;
		hash = hash * prime + this.estimateNo.hashCode();
		hash = hash * prime + this.deptId.hashCode();
		hash = hash * prime + this.labourCode.hashCode();
		
		return hash;
    }
}