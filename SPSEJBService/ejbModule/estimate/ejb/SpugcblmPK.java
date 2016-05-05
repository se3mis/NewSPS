package estimate.ejb;

import java.io.Serializable;
import javax.persistence.*;

/**
 * The primary key class for the SPUGCBLM database table.
 * 
 */
@Embeddable
public class SpugcblmPK implements Serializable {
	//default serial version id, required for serializable classes.
	private static final long serialVersionUID = 1L;

	private long phase;

	@Column(name="CONNECTION_TYPE")
	private long connectionType;

	@Column(name="CABLE_TYPE")
	private String cableType;

	@Column(name="DEPT_ID")
	private String deptId;

    public SpugcblmPK() {
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
	public String getCableType() {
		return this.cableType;
	}
	public void setCableType(String cableType) {
		this.cableType = cableType;
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
		if (!(other instanceof SpugcblmPK)) {
			return false;
		}
		SpugcblmPK castOther = (SpugcblmPK)other;
		return 
			(this.phase == castOther.phase)
			&& (this.connectionType == castOther.connectionType)
			&& this.cableType.equals(castOther.cableType)
			&& this.deptId.equals(castOther.deptId);

    }
    
	public int hashCode() {
		final int prime = 31;
		int hash = 17;
		hash = hash * prime + ((int) (this.phase ^ (this.phase >>> 32)));
		hash = hash * prime + ((int) (this.connectionType ^ (this.connectionType >>> 32)));
		hash = hash * prime + this.cableType.hashCode();
		hash = hash * prime + this.deptId.hashCode();
		
		return hash;
    }
}