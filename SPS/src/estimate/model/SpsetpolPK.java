package estimate.model;

import java.io.Serializable;
import javax.persistence.*;

/**
 * The primary key class for the SPSETPOL database table.
 * 
 */
@Embeddable
public class SpsetpolPK implements Serializable {
	//default serial version id, required for serializable classes.
	private static final long serialVersionUID = 1L;

	@Column(name="APPLICATION_NO")
	private String applicationNo;

	@Column(name="DEPT_ID")
	private String deptId;

	@Column(name="POINT_TYPE")
	private String pointType;

	@Column(name="POLE_TYPE")
	private String poleType;

	@Column(name="FROM_CONDUCTOR")
	private String fromConductor;

	@Column(name="TO_CONDUCTOR")
	private String toConductor;

	@Column(name="MAT_CD")
	private String matCd;

    public SpsetpolPK() {
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
	public String getPointType() {
		return this.pointType;
	}
	public void setPointType(String pointType) {
		this.pointType = pointType;
	}
	public String getPoleType() {
		return this.poleType;
	}
	public void setPoleType(String poleType) {
		this.poleType = poleType;
	}
	public String getFromConductor() {
		return this.fromConductor;
	}
	public void setFromConductor(String fromConductor) {
		this.fromConductor = fromConductor;
	}
	public String getToConductor() {
		return this.toConductor;
	}
	public void setToConductor(String toConductor) {
		this.toConductor = toConductor;
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
		if (!(other instanceof SpsetpolPK)) {
			return false;
		}
		SpsetpolPK castOther = (SpsetpolPK)other;
		return 
			this.applicationNo.equals(castOther.applicationNo)
			&& this.deptId.equals(castOther.deptId)
			&& this.pointType.equals(castOther.pointType)
			&& this.poleType.equals(castOther.poleType)
			&& this.fromConductor.equals(castOther.fromConductor)
			&& this.toConductor.equals(castOther.toConductor)
			&& this.matCd.equals(castOther.matCd);

    }
    
	public int hashCode() {
		final int prime = 31;
		int hash = 17;
		hash = hash * prime + this.applicationNo.hashCode();
		hash = hash * prime + this.deptId.hashCode();
		hash = hash * prime + this.pointType.hashCode();
		hash = hash * prime + this.poleType.hashCode();
		hash = hash * prime + this.fromConductor.hashCode();
		hash = hash * prime + this.toConductor.hashCode();
		hash = hash * prime + this.matCd.hashCode();
		
		return hash;
    }
}