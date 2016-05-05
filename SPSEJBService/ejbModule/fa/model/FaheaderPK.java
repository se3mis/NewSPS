package fa.model;

import java.io.Serializable;
import javax.persistence.*;

/**
 * The primary key class for the FAHEADER database table.
 * 
 */
@Embeddable
public class FaheaderPK implements Serializable {
	//default serial version id, required for serializable classes.
	private static final long serialVersionUID = 1L;

	@Column(name="DEPT_ID")
	private String deptId;

	@Column(name="PROJECT_NO")
	private String projectNo;

	@Column(name="FA_CAT_CODE")
	private String faCatCode;

    public FaheaderPK() {
    }
	public String getDeptId() {
		return this.deptId;
	}
	public void setDeptId(String deptId) {
		this.deptId = deptId;
	}
	public String getProjectNo() {
		return this.projectNo;
	}
	public void setProjectNo(String projectNo) {
		this.projectNo = projectNo;
	}
	public String getFaCatCode() {
		return this.faCatCode;
	}
	public void setFaCatCode(String faCatCode) {
		this.faCatCode = faCatCode;
	}

	public boolean equals(Object other) {
		if (this == other) {
			return true;
		}
		if (!(other instanceof FaheaderPK)) {
			return false;
		}
		FaheaderPK castOther = (FaheaderPK)other;
		return 
			this.deptId.equals(castOther.deptId)
			&& this.projectNo.equals(castOther.projectNo)
			&& this.faCatCode.equals(castOther.faCatCode);

    }
    
	public int hashCode() {
		final int prime = 31;
		int hash = 17;
		hash = hash * prime + this.deptId.hashCode();
		hash = hash * prime + this.projectNo.hashCode();
		hash = hash * prime + this.faCatCode.hashCode();
		
		return hash;
    }
	@Override
	public String toString() {
		return "FaheaderPK [deptId=" + deptId + ", projectNo=" + projectNo
				+ ", faCatCode=" + faCatCode + "]";
	}
}