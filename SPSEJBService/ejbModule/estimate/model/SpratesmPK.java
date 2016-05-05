package estimate.model;

import java.io.Serializable;
import javax.persistence.*;

/**
 * The primary key class for the SPRATESM database table.
 * 
 */
@Embeddable
public class SpratesmPK implements Serializable {
	//default serial version id, required for serializable classes.
	private static final long serialVersionUID = 1L;

	@Column(name="APPLICATION_TYPE")
	private String applicationType;

	@Column(name="DEPT_ID")
	private String deptId;

    public SpratesmPK() {
    }
	public String getApplicationType() {
		return this.applicationType;
	}
	public void setApplicationType(String applicationType) {
		this.applicationType = applicationType;
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
		if (!(other instanceof SpratesmPK)) {
			return false;
		}
		SpratesmPK castOther = (SpratesmPK)other;
		return 
			this.applicationType.equals(castOther.applicationType)
			&& this.deptId.equals(castOther.deptId);

    }
    
	public int hashCode() {
		final int prime = 31;
		int hash = 17;
		hash = hash * prime + this.applicationType.hashCode();
		hash = hash * prime + this.deptId.hashCode();
		
		return hash;
    }
	@Override
	public String toString() {
		return "SpratesmPK [applicationType=" + applicationType + ", deptId="
				+ deptId + "]";
	}
	
}