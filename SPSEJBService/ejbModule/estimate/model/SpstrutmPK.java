package estimate.model;

import java.io.Serializable;
import javax.persistence.*;

/**
 * The primary key class for the SPSTRUTM database table.
 * 
 */
@Embeddable
public class SpstrutmPK implements Serializable {
	//default serial version id, required for serializable classes.
	private static final long serialVersionUID = 1L;

	@Column(name="DEPT_ID")
	private String deptId;

	@Column(name="MAT_CD")
	private String matCd;

    public SpstrutmPK() {
    }
	public String getDeptId() {
		return this.deptId;
	}
	public void setDeptId(String deptId) {
		this.deptId = deptId;
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
		if (!(other instanceof SpstrutmPK)) {
			return false;
		}
		SpstrutmPK castOther = (SpstrutmPK)other;
		return 
			this.deptId.equals(castOther.deptId)
			&& this.matCd.equals(castOther.matCd);

    }
    
	public int hashCode() {
		final int prime = 31;
		int hash = 17;
		hash = hash * prime + this.deptId.hashCode();
		hash = hash * prime + this.matCd.hashCode();
		
		return hash;
    }
	@Override
	public String toString() {
		return "SpstrutmPK [deptId=" + deptId + ", matCd=" + matCd + "]";
	}
}