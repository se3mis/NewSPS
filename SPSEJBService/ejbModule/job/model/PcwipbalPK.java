package job.model;

import java.io.Serializable;
import javax.persistence.*;

/**
 * The primary key class for the PCWIPBAL database table.
 * 
 */
@Embeddable
public class PcwipbalPK implements Serializable {
	//default serial version id, required for serializable classes.
	private static final long serialVersionUID = 1L;

	@Column(name="FUND_ID")
	private String fundId;

	@Column(name="DEPT_ID")
	private String deptId;

	@Column(name="LOG_YR")
	private long logYr;

	@Column(name="LOG_MTH")
	private long logMth;

    public PcwipbalPK() {
    }
	public String getFundId() {
		return this.fundId;
	}
	public void setFundId(String fundId) {
		this.fundId = fundId;
	}
	public String getDeptId() {
		return this.deptId;
	}
	public void setDeptId(String deptId) {
		this.deptId = deptId;
	}
	public long getLogYr() {
		return this.logYr;
	}
	public void setLogYr(long logYr) {
		this.logYr = logYr;
	}
	public long getLogMth() {
		return this.logMth;
	}
	public void setLogMth(long logMth) {
		this.logMth = logMth;
	}

	public boolean equals(Object other) {
		if (this == other) {
			return true;
		}
		if (!(other instanceof PcwipbalPK)) {
			return false;
		}
		PcwipbalPK castOther = (PcwipbalPK)other;
		return 
			this.fundId.equals(castOther.fundId)
			&& this.deptId.equals(castOther.deptId)
			&& (this.logYr == castOther.logYr)
			&& (this.logMth == castOther.logMth);

    }
    
	public int hashCode() {
		final int prime = 31;
		int hash = 17;
		hash = hash * prime + this.fundId.hashCode();
		hash = hash * prime + this.deptId.hashCode();
		hash = hash * prime + ((int) (this.logYr ^ (this.logYr >>> 32)));
		hash = hash * prime + ((int) (this.logMth ^ (this.logMth >>> 32)));
		
		return hash;
    }
}