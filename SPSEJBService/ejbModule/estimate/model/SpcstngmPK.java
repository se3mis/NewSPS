package estimate.model;

import java.io.Serializable;
import javax.persistence.*;

/**
 * The primary key class for the SPCSTNGM database table.
 * 
 */
@Embeddable
public class SpcstngmPK implements Serializable {
	//default serial version id, required for serializable classes.
	private static final long serialVersionUID = 1L;

	private long phase;

	@Column(name="CONNECTION_TYPE")
	private long connectionType;

	@Column(name="TARIFF_CAT_CODE")
	private String tariffCatCode;

	@Column(name="DEPT_ID")
	private String deptId;

	@Column(name="FROM_LENGTH")
	private long fromLength;

	@Column(name="TO_LENGTH")
	private long toLength;

    public SpcstngmPK() {
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
	public String getTariffCatCode() {
		return this.tariffCatCode;
	}
	public void setTariffCatCode(String tariffCatCode) {
		this.tariffCatCode = tariffCatCode;
	}
	public String getDeptId() {
		return this.deptId;
	}
	public void setDeptId(String deptId) {
		this.deptId = deptId;
	}
	public long getFromLength() {
		return this.fromLength;
	}
	public void setFromLength(long fromLength) {
		this.fromLength = fromLength;
	}
	public long getToLength() {
		return this.toLength;
	}
	public void setToLength(long toLength) {
		this.toLength = toLength;
	}

	public boolean equals(Object other) {
		if (this == other) {
			return true;
		}
		if (!(other instanceof SpcstngmPK)) {
			return false;
		}
		SpcstngmPK castOther = (SpcstngmPK)other;
		return 
			(this.phase == castOther.phase)
			&& (this.connectionType == castOther.connectionType)
			&& this.tariffCatCode.equals(castOther.tariffCatCode)
			&& this.deptId.equals(castOther.deptId)
			&& (this.fromLength == castOther.fromLength)
			&& (this.toLength == castOther.toLength);

    }
    
	public int hashCode() {
		final int prime = 31;
		int hash = 17;
		hash = hash * prime + ((int) (this.phase ^ (this.phase >>> 32)));
		hash = hash * prime + ((int) (this.connectionType ^ (this.connectionType >>> 32)));
		hash = hash * prime + this.tariffCatCode.hashCode();
		hash = hash * prime + this.deptId.hashCode();
		hash = hash * prime + ((int) (this.fromLength ^ (this.fromLength >>> 32)));
		hash = hash * prime + ((int) (this.toLength ^ (this.toLength >>> 32)));
		
		return hash;
    }
	@Override
	public String toString() {
		return "SpcstngmPK [phase=" + phase + ", connectionType="
				+ connectionType + ", tariffCatCode=" + tariffCatCode
				+ ", deptId=" + deptId + ", fromLength=" + fromLength
				+ ", toLength=" + toLength + "]";
	}
	
}