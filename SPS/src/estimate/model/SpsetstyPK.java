package estimate.model;

import java.io.Serializable;
import javax.persistence.*;

/**
 * The primary key class for the SPSETSTY database table.
 * 
 */
@Embeddable
public class SpsetstyPK implements Serializable {
	//default serial version id, required for serializable classes.
	private static final long serialVersionUID = 1L;

	@Column(name="APPLICATION_NO")
	private String applicationNo;

	@Column(name="DEPT_ID")
	private String deptId;

	@Column(name="MAT_CD")
	private String matCd;

	@Column(name="STAY_TYPE")
	private String stayType;

    public SpsetstyPK() {
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
	public String getStayType() {
		return this.stayType;
	}
	public void setStayType(String stayType) {
		this.stayType = stayType;
	}

	public boolean equals(Object other) {
		if (this == other) {
			return true;
		}
		if (!(other instanceof SpsetstyPK)) {
			return false;
		}
		SpsetstyPK castOther = (SpsetstyPK)other;
		return 
			this.applicationNo.equals(castOther.applicationNo)
			&& this.deptId.equals(castOther.deptId)
			&& this.matCd.equals(castOther.matCd)
			&& this.stayType.equals(castOther.stayType);

    }
    
	public int hashCode() {
		final int prime = 31;
		int hash = 17;
		hash = hash * prime + this.applicationNo.hashCode();
		hash = hash * prime + this.deptId.hashCode();
		hash = hash * prime + this.matCd.hashCode();
		hash = hash * prime + this.stayType.hashCode();
		
		return hash;
    }
}