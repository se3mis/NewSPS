package job.model;

import java.io.Serializable;
import javax.persistence.*;

/**
 * The primary key class for the PCPRJBAL database table.
 * 
 */
@Embeddable
public class PcprjbalPK implements Serializable {
	//default serial version id, required for serializable classes.
	private static final long serialVersionUID = 1L;

	@Column(name="ESTIMATE_NO")
	private String estimateNo;

	@Column(name="DEPT_ID")
	private String deptId;

	@Column(name="YR_IND")
	private long yrInd;

	@Column(name="MTH_IND")
	private long mthInd;

	@Column(name="RES_TYPE")
	private String resType;

    public PcprjbalPK() {
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
	public long getYrInd() {
		return this.yrInd;
	}
	public void setYrInd(long yrInd) {
		this.yrInd = yrInd;
	}
	public long getMthInd() {
		return this.mthInd;
	}
	public void setMthInd(long mthInd) {
		this.mthInd = mthInd;
	}
	public String getResType() {
		return this.resType;
	}
	public void setResType(String resType) {
		this.resType = resType;
	}

	public boolean equals(Object other) {
		if (this == other) {
			return true;
		}
		if (!(other instanceof PcprjbalPK)) {
			return false;
		}
		PcprjbalPK castOther = (PcprjbalPK)other;
		return 
			this.estimateNo.equals(castOther.estimateNo)
			&& this.deptId.equals(castOther.deptId)
			&& (this.yrInd == castOther.yrInd)
			&& (this.mthInd == castOther.mthInd)
			&& this.resType.equals(castOther.resType);

    }
    
	public int hashCode() {
		final int prime = 31;
		int hash = 17;
		hash = hash * prime + this.estimateNo.hashCode();
		hash = hash * prime + this.deptId.hashCode();
		hash = hash * prime + ((int) (this.yrInd ^ (this.yrInd >>> 32)));
		hash = hash * prime + ((int) (this.mthInd ^ (this.mthInd >>> 32)));
		hash = hash * prime + this.resType.hashCode();
		
		return hash;
    }
	@Override
	public String toString() {
		return "PcprjbalPK [estimateNo=" + estimateNo + ", deptId=" + deptId
				+ ", yrInd=" + yrInd + ", mthInd=" + mthInd + ", resType="
				+ resType + "]";
	}
	
	
}