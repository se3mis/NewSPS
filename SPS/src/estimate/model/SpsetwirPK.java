package estimate.model;

import java.io.Serializable;
import javax.persistence.*;

/**
 * The primary key class for the SPSETWIR database table.
 * 
 */
@Embeddable
public class SpsetwirPK implements Serializable {
	//default serial version id, required for serializable classes.
	private static final long serialVersionUID = 1L;

	@Column(name="APPLICATION_NO")
	private String applicationNo;

	@Column(name="DEPT_ID")
	private String deptId;

	@Column(name="MAT_CD")
	private String matCd;

    public SpsetwirPK() {
    }
	public String getApplicationNo() {
		return this.applicationNo;
	}
	public void setApplicationNo(String applicationNo) {
		this.applicationNo = applicationNo;
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
		if (!(other instanceof SpsetwirPK)) {
			return false;
		}
		SpsetwirPK castOther = (SpsetwirPK)other;
		return 
			this.applicationNo.equals(castOther.applicationNo)
			&& this.deptId.equals(castOther.deptId)
			&& this.matCd.equals(castOther.matCd);

    }
    
	public int hashCode() {
		final int prime = 31;
		int hash = 17;
		hash = hash * prime + this.applicationNo.hashCode();
		hash = hash * prime + this.deptId.hashCode();
		hash = hash * prime + this.matCd.hashCode();
		
		return hash;
    }
}