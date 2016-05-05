package job.model;

import java.io.Serializable;
import javax.persistence.*;

/**
 * The primary key class for the PCWIPFU database table.
 * 
 */
@Embeddable
public class PcwipfuPK implements Serializable {
	//default serial version id, required for serializable classes.
	private static final long serialVersionUID = 1L;

	@Column(name="FUND_ID")
	private String fundId;

	@Column(name="DEPT_ID")
	private String deptId;

	@Column(name="RES_CD")
	private String resCd;

	@Column(name="RES_CAT")
	private long resCat;

	@Column(name="LOG_YR")
	private long logYr;

	@Column(name="LOG_MTH")
	private long logMth;

    public PcwipfuPK() {
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
	public String getResCd() {
		return this.resCd;
	}
	public void setResCd(String resCd) {
		this.resCd = resCd;
	}
	public long getResCat() {
		return this.resCat;
	}
	public void setResCat(long resCat) {
		this.resCat = resCat;
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
		if (!(other instanceof PcwipfuPK)) {
			return false;
		}
		PcwipfuPK castOther = (PcwipfuPK)other;
		return 
			this.fundId.equals(castOther.fundId)
			&& this.deptId.equals(castOther.deptId)
			&& this.resCd.equals(castOther.resCd)
			&& (this.resCat == castOther.resCat)
			&& (this.logYr == castOther.logYr)
			&& (this.logMth == castOther.logMth);

    }
    
	public int hashCode() {
		final int prime = 31;
		int hash = 17;
		hash = hash * prime + this.fundId.hashCode();
		hash = hash * prime + this.deptId.hashCode();
		hash = hash * prime + this.resCd.hashCode();
		hash = hash * prime + ((int) (this.resCat ^ (this.resCat >>> 32)));
		hash = hash * prime + ((int) (this.logYr ^ (this.logYr >>> 32)));
		hash = hash * prime + ((int) (this.logMth ^ (this.logMth >>> 32)));
		
		return hash;
    }
}