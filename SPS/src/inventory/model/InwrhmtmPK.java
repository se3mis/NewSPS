package inventory.model;

import java.io.Serializable;
import javax.persistence.*;

/**
 * The primary key class for the INWRHMTM database table.
 * 
 */
@Embeddable
public class InwrhmtmPK implements Serializable {
	//default serial version id, required for serializable classes.
	private static final long serialVersionUID = 1L;

	@Column(name="DEPT_ID")
	private String deptId;

	@Column(name="WRH_CD")
	private String wrhCd;

	@Column(name="MAT_CD")
	private String matCd;

	@Column(name="GRADE_CD")
	private String gradeCd;

    public InwrhmtmPK() {
    }
	public String getDeptId() {
		return this.deptId;
	}
	public void setDeptId(String deptId) {
		this.deptId = deptId;
	}
	public String getWrhCd() {
		return this.wrhCd;
	}
	public void setWrhCd(String wrhCd) {
		this.wrhCd = wrhCd;
	}
	public String getMatCd() {
		return this.matCd;
	}
	public void setMatCd(String matCd) {
		this.matCd = matCd;
	}
	public String getGradeCd() {
		return this.gradeCd;
	}
	public void setGradeCd(String gradeCd) {
		this.gradeCd = gradeCd;
	}

	public boolean equals(Object other) {
		if (this == other) {
			return true;
		}
		if (!(other instanceof InwrhmtmPK)) {
			return false;
		}
		InwrhmtmPK castOther = (InwrhmtmPK)other;
		return 
			this.deptId.equals(castOther.deptId)
			&& this.wrhCd.equals(castOther.wrhCd)
			&& this.matCd.equals(castOther.matCd)
			&& this.gradeCd.equals(castOther.gradeCd);

    }
    
	public int hashCode() {
		final int prime = 31;
		int hash = 17;
		hash = hash * prime + this.deptId.hashCode();
		hash = hash * prime + this.wrhCd.hashCode();
		hash = hash * prime + this.matCd.hashCode();
		hash = hash * prime + this.gradeCd.hashCode();
		
		return hash;
    }
	@Override
	public String toString() {
		return "InwrhmtmPK [deptId=" + deptId + ", wrhCd=" + wrhCd + ", matCd="
				+ matCd + ", gradeCd=" + gradeCd + "]";
	}
	
}