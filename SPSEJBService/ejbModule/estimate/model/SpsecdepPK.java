package estimate.model;

import java.io.Serializable;
import javax.persistence.*;

/**
 * The primary key class for the SPSECDEP database table.
 * 
 */
@Embeddable
public class SpsecdepPK implements Serializable {
	//default serial version id, required for serializable classes.
	private static final long serialVersionUID = 1L;

	private long phase;

	@Column(name="CONNECTION_TYPE")
	private long connectionType;

	@Column(name="DEPT_ID")
	private String deptId;

	@Column(name="TARIFF_CAT_CODE")
	private String tariffCatCode;

    public SpsecdepPK() {
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
	public String getDeptId() {
		return this.deptId;
	}
	public void setDeptId(String deptId) {
		this.deptId = deptId;
	}
	public String getTariffCatCode() {
		return this.tariffCatCode;
	}
	public void setTariffCatCode(String tariffCatCode) {
		this.tariffCatCode = tariffCatCode;
	}

	public boolean equals(Object other) {
		if (this == other) {
			return true;
		}
		if (!(other instanceof SpsecdepPK)) {
			return false;
		}
		SpsecdepPK castOther = (SpsecdepPK)other;
		return 
			(this.phase == castOther.phase)
			&& (this.connectionType == castOther.connectionType)
			&& this.deptId.equals(castOther.deptId)
			&& this.tariffCatCode.equals(castOther.tariffCatCode);

    }
    
	public int hashCode() {
		final int prime = 31;
		int hash = 17;
		hash = hash * prime + ((int) (this.phase ^ (this.phase >>> 32)));
		hash = hash * prime + ((int) (this.connectionType ^ (this.connectionType >>> 32)));
		hash = hash * prime + this.deptId.hashCode();
		hash = hash * prime + this.tariffCatCode.hashCode();
		
		return hash;
    }
}