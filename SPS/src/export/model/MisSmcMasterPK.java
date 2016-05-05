package export.model;

import java.io.Serializable;
import javax.persistence.*;

/**
 * The primary key class for the MIS_SMC_MASTER database table.
 * 
 */
@Embeddable
public class MisSmcMasterPK implements Serializable {
	//default serial version id, required for serializable classes.
	private static final long serialVersionUID = 1L;

	@Column(name="SMC_TYPE")
	private String smcType;

	@Column(name="AREA_CODE")
	private String areaCode;

	@Column(name="CRNT_DEPOT")
	private String crntDepot;

	@Column(name="YEAR_INSTLD")
	private String yearInstld;

	@Column(name="JOB_SERL")
	private long jobSerl;

    public MisSmcMasterPK() {
    }
	public String getSmcType() {
		return this.smcType;
	}
	public void setSmcType(String smcType) {
		this.smcType = smcType;
	}
	public String getAreaCode() {
		return this.areaCode;
	}
	public void setAreaCode(String areaCode) {
		this.areaCode = areaCode;
	}
	public String getCrntDepot() {
		return this.crntDepot;
	}
	public void setCrntDepot(String crntDepot) {
		this.crntDepot = crntDepot;
	}
	public String getYearInstld() {
		return this.yearInstld;
	}
	public void setYearInstld(String yearInstld) {
		this.yearInstld = yearInstld;
	}
	public long getJobSerl() {
		return this.jobSerl;
	}
	public void setJobSerl(long jobSerl) {
		this.jobSerl = jobSerl;
	}

	public boolean equals(Object other) {
		if (this == other) {
			return true;
		}
		if (!(other instanceof MisSmcMasterPK)) {
			return false;
		}
		MisSmcMasterPK castOther = (MisSmcMasterPK)other;
		return 
			this.smcType.equals(castOther.smcType)
			&& this.areaCode.equals(castOther.areaCode)
			&& this.crntDepot.equals(castOther.crntDepot)
			&& this.yearInstld.equals(castOther.yearInstld)
			&& (this.jobSerl == castOther.jobSerl);

    }
    
	public int hashCode() {
		final int prime = 31;
		int hash = 17;
		hash = hash * prime + this.smcType.hashCode();
		hash = hash * prime + this.areaCode.hashCode();
		hash = hash * prime + this.crntDepot.hashCode();
		hash = hash * prime + this.yearInstld.hashCode();
		hash = hash * prime + ((int) (this.jobSerl ^ (this.jobSerl >>> 32)));
		
		return hash;
    }
	@Override
	public String toString() {
		return "MisSmcMasterPK [smcType=" + smcType + ", areaCode=" + areaCode
				+ ", crntDepot=" + crntDepot + ", yearInstld=" + yearInstld
				+ ", jobSerl=" + jobSerl + "]";
	}
}