package estimate.model;

import java.io.Serializable;
import javax.persistence.*;

/**
 * The primary key class for the SPPOLEMT database table.
 * 
 */
@Embeddable
public class SppolemtPK implements Serializable {
	//default serial version id, required for serializable classes.
	private static final long serialVersionUID = 1L;

	@Column(name="DEPT_ID")
	private String deptId;

	private long phase;

	@Column(name="POLE_TYPE")
	private String poleType;

	@Column(name="FROM_CONDUCTOR")
	private String fromConductor;

	@Column(name="TO_CONDUCTOR")
	private String toConductor;

	@Column(name="MAT_CD")
	private String matCd;

    public SppolemtPK() {
    }
	public String getDeptId() {
		return this.deptId;
	}
	public void setDeptId(String deptId) {
		this.deptId = deptId;
	}
	public long getPhase() {
		return this.phase;
	}
	public void setPhase(long phase) {
		this.phase = phase;
	}
	public String getPoleType() {
		return this.poleType;
	}
	public void setPoleType(String poleType) {
		this.poleType = poleType;
	}
	public String getFromConductor() {
		return this.fromConductor;
	}
	public void setFromConductor(String fromConductor) {
		this.fromConductor = fromConductor;
	}
	public String getToConductor() {
		return this.toConductor;
	}
	public void setToConductor(String toConductor) {
		this.toConductor = toConductor;
	}
	public String getMatCd() {
		return this.matCd;
	}
	public void setMatCd(String matCd) {
		this.matCd = matCd;
	}

	public boolean equals(Object other) {
		if (this == other) {
			return true;
		}
		if (!(other instanceof SppolemtPK)) {
			return false;
		}
		SppolemtPK castOther = (SppolemtPK)other;
		return 
			this.deptId.equals(castOther.deptId)
			&& (this.phase == castOther.phase)
			&& this.poleType.equals(castOther.poleType)
			&& this.fromConductor.equals(castOther.fromConductor)
			&& this.toConductor.equals(castOther.toConductor)
			&& this.matCd.equals(castOther.matCd);

    }
    
	public int hashCode() {
		final int prime = 31;
		int hash = 17;
		hash = hash * prime + this.deptId.hashCode();
		hash = hash * prime + ((int) (this.phase ^ (this.phase >>> 32)));
		hash = hash * prime + this.poleType.hashCode();
		hash = hash * prime + this.fromConductor.hashCode();
		hash = hash * prime + this.toConductor.hashCode();
		hash = hash * prime + this.matCd.hashCode();
		
		return hash;
    }
	@Override
	public String toString() {
		return "SppolemtPK [deptId=" + deptId + ", phase=" + phase
				+ ", poleType=" + poleType + ", fromConductor=" + fromConductor
				+ ", toConductor=" + toConductor + ", matCd=" + matCd + "]";
	}
}