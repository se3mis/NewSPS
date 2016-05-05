package estimate.model;

import java.io.Serializable;
import javax.persistence.*;

/**
 * The primary key class for the PCJBTYPM database table.
 * 
 */
@Embeddable
public class PcjbtypmPK implements Serializable {
	//default serial version id, required for serializable classes.
	private static final long serialVersionUID = 1L;

	@Column(name="CAT_CD")
	private String catCd;

	@Column(name="DEPT_ID")
	private String deptId;

    public PcjbtypmPK() {
    }
	public String getCatCd() {
		return this.catCd;
	}
	public void setCatCd(String catCd) {
		this.catCd = catCd;
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
		if (!(other instanceof PcjbtypmPK)) {
			return false;
		}
		PcjbtypmPK castOther = (PcjbtypmPK)other;
		return 
			this.catCd.equals(castOther.catCd)
			&& this.deptId.equals(castOther.deptId);

    }
    
	public int hashCode() {
		final int prime = 31;
		int hash = 17;
		hash = hash * prime + this.catCd.hashCode();
		hash = hash * prime + this.deptId.hashCode();
		
		return hash;
    }
	@Override
	public String toString() {
		return "PcjbtypmPK [catCd=" + catCd + ", deptId=" + deptId + "]";
	}
	
}