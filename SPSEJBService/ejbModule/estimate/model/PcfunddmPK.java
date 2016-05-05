package estimate.model;

import java.io.Serializable;
import javax.persistence.*;

/**
 * The primary key class for the PCFUNDDM database table.
 * 
 */
@Embeddable
public class PcfunddmPK implements Serializable {
	//default serial version id, required for serializable classes.
	private static final long serialVersionUID = 1L;

	@Column(name="FUND_SOURCE")
	private String fundSource;

	@Column(name="FUND_ID")
	private String fundId;

	@Column(name="DEPT_ID")
	private String deptId;

    public PcfunddmPK() {
    }
	public String getFundSource() {
		return this.fundSource;
	}
	public void setFundSource(String fundSource) {
		this.fundSource = fundSource;
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

	public boolean equals(Object other) {
		if (this == other) {
			return true;
		}
		if (!(other instanceof PcfunddmPK)) {
			return false;
		}
		PcfunddmPK castOther = (PcfunddmPK)other;
		return 
			this.fundSource.equals(castOther.fundSource)
			&& this.fundId.equals(castOther.fundId)
			&& this.deptId.equals(castOther.deptId);

    }
    
	public int hashCode() {
		final int prime = 31;
		int hash = 17;
		hash = hash * prime + this.fundSource.hashCode();
		hash = hash * prime + this.fundId.hashCode();
		hash = hash * prime + this.deptId.hashCode();
		
		return hash;
    }
	@Override
	public String toString() {
		return "PcfunddmPK [fundSource=" + fundSource + ", fundId=" + fundId
				+ ", deptId=" + deptId + "]";
	}
	
	
}