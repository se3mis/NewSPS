package estimate.model;

import java.io.Serializable;
import javax.persistence.*;

/**
 * Author: Dinusha Nirmalie
 * Created: March 01, 2012 11:23:42 AM
 */

/**
 * The persistent class for the SpPegInfo database table.
 * 
 */

@Embeddable
public class CostCalculationMasterPK implements Serializable {
	//default serial version id, required for serializable classes.
	private static final long serialVersionUID = 1L;

	@Column(name="DEPT_ID")
	private String deptId;

	@Column(name="ESTIMATE_TYPE")
	private String estimateType;
	
	@Column(name="ENTRY_CD")
	private String entryResCode;
	

	public String getDeptId() {
		return deptId;
	}

	public void setDeptId(String deptId) {
		this.deptId = deptId;
	}

	public String getEstimateType() {
		return estimateType;
	}

	public void setEstimateType(String estimateType) {
		this.estimateType = estimateType;
	}

	public String getEntryResCode() {
		return entryResCode;
	}

	public void setEntryResCode(String entryResCode) {
		this.entryResCode = entryResCode;
	}

	public CostCalculationMasterPK() {
    }
	
	public boolean equals(Object other) {
		if (this == other) {
			return true;
		}
		if (!(other instanceof CostCalculationMasterPK)) {
			return false;
		}
		CostCalculationMasterPK castOther = (CostCalculationMasterPK)other;
		return 
			(this.estimateType.equals(castOther.estimateType))&&(this.deptId.equals(castOther.deptId))&&(this.entryResCode.equals(castOther.entryResCode))
			;

    }

	public int hashCode() {
		final int prime = 31;
		int hash = 17;
		hash = hash * prime + this.deptId.hashCode();
		hash = hash * prime + this.estimateType.hashCode();
		hash = hash * prime + this.entryResCode.hashCode();
		return hash;
    }
	@Override
	public String toString() {
		return "SpPegInfoPK [deptId=" + deptId + "estimateType=" + estimateType + "entryResCode=" + entryResCode+ "]";
	}
}