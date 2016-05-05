package fa.model;

import java.io.Serializable;
import javax.persistence.*;

/**
 * The primary key class for the FAPLNTEQ database table.
 * 
 */
@Embeddable
public class FaplnteqPK implements Serializable {
	//default serial version id, required for serializable classes.
	private static final long serialVersionUID = 1L;

	@Column(name="DEPT_ID")
	private String deptId;

	@Column(name="PROJECT_NO")
	private String projectNo;

	@Column(name="CAT_CODE")
	private String catCode;

	@Column(name="FEATURE_CODE")
	private String featureCode;

    public FaplnteqPK() {
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
	public String getCatCode() {
		return this.catCode;
	}
	public void setCatCode(String catCode) {
		this.catCode = catCode;
	}
	public String getFeatureCode() {
		return this.featureCode;
	}
	public void setFeatureCode(String featureCode) {
		this.featureCode = featureCode;
	}

	public boolean equals(Object other) {
		if (this == other) {
			return true;
		}
		if (!(other instanceof FaplnteqPK)) {
			return false;
		}
		FaplnteqPK castOther = (FaplnteqPK)other;
		return 
			this.deptId.equals(castOther.deptId)
			&& this.projectNo.equals(castOther.projectNo)
			&& this.catCode.equals(castOther.catCode)
			&& this.featureCode.equals(castOther.featureCode);

    }
    
	public int hashCode() {
		final int prime = 31;
		int hash = 17;
		hash = hash * prime + this.deptId.hashCode();
		hash = hash * prime + this.projectNo.hashCode();
		hash = hash * prime + this.catCode.hashCode();
		hash = hash * prime + this.featureCode.hashCode();
		
		return hash;
    }
	@Override
	public String toString() {
		return "FaplnteqPK [deptId=" + deptId + ", projectNo=" + projectNo
				+ ", catCode=" + catCode + ", featureCode=" + featureCode + "]";
	}
}