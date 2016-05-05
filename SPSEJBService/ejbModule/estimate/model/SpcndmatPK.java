package estimate.model;

import java.io.Serializable;
import javax.persistence.*;

/**
 * The primary key class for the SPCNDMAT database table.
 * 
 */
@Embeddable
public class SpcndmatPK implements Serializable {
	//default serial version id, required for serializable classes.
	private static final long serialVersionUID = 1L;

	@Column(name="DEPT_ID")
	private String deptId;

	private long phase;

	@Column(name="CONNECTION_TYPE")
	private long connectionType;

	@Column(name="WIRING_TYPE")
	private String wiringType;

	@Column(name="CONDUCTOR_TYPE")
	private String conductorType;

	@Column(name="MAT_CD")
	private String matCd;

    public SpcndmatPK() {
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
	public long getConnectionType() {
		return this.connectionType;
	}
	public void setConnectionType(long connectionType) {
		this.connectionType = connectionType;
	}
	public String getWiringType() {
		return this.wiringType;
	}
	public void setWiringType(String wiringType) {
		this.wiringType = wiringType;
	}
	public String getConductorType() {
		return this.conductorType;
	}
	public void setConductorType(String conductorType) {
		this.conductorType = conductorType;
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
		if (!(other instanceof SpcndmatPK)) {
			return false;
		}
		SpcndmatPK castOther = (SpcndmatPK)other;
		return 
			this.deptId.equals(castOther.deptId)
			&& (this.phase == castOther.phase)
			&& (this.connectionType == castOther.connectionType)
			&& this.wiringType.equals(castOther.wiringType)
			&& this.conductorType.equals(castOther.conductorType)
			&& this.matCd.equals(castOther.matCd);

    }
    
	public int hashCode() {
		final int prime = 31;
		int hash = 17;
		hash = hash * prime + this.deptId.hashCode();
		hash = hash * prime + ((int) (this.phase ^ (this.phase >>> 32)));
		hash = hash * prime + ((int) (this.connectionType ^ (this.connectionType >>> 32)));
		hash = hash * prime + this.wiringType.hashCode();
		hash = hash * prime + this.conductorType.hashCode();
		hash = hash * prime + this.matCd.hashCode();
		
		return hash;
    }
	@Override
	public String toString() {
		return "SpcndmatPK [deptId=" + deptId + ", phase=" + phase
				+ ", connectionType=" + connectionType + ", wiringType="
				+ wiringType + ", conductorType=" + conductorType + ", matCd="
				+ matCd + "]";
	}
}