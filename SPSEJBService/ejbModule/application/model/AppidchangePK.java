package application.model;

import java.io.Serializable;
import javax.persistence.*;

/**
 * The primary key class for the APPIDCHANGE database table.
 * 
 */
@Embeddable
public class AppidchangePK implements Serializable {
	//default serial version id, required for serializable classes.
	private static final long serialVersionUID = 1L;

	@Column(name="APPLICATION_ID")
	private String applicationId;

	@Column(name="DEPT_ID")
	private String deptId;

	@Column(name="ID_NO_OLD")
	private String idNoOld;

	@Column(name="ID_NO_NEW")
	private String idNoNew;

    public AppidchangePK() {
    }
	public String getApplicationId() {
		return this.applicationId;
	}
	public void setApplicationId(String applicationId) {
		this.applicationId = applicationId;
	}
	public String getDeptId() {
		return this.deptId;
	}
	public void setDeptId(String deptId) {
		this.deptId = deptId;
	}
	public String getIdNoOld() {
		return this.idNoOld;
	}
	public void setIdNoOld(String idNoOld) {
		this.idNoOld = idNoOld;
	}
	public String getIdNoNew() {
		return this.idNoNew;
	}
	public void setIdNoNew(String idNoNew) {
		this.idNoNew = idNoNew;
	}

	public boolean equals(Object other) {
		if (this == other) {
			return true;
		}
		if (!(other instanceof AppidchangePK)) {
			return false;
		}
		AppidchangePK castOther = (AppidchangePK)other;
		return 
			this.applicationId.equals(castOther.applicationId)
			&& this.deptId.equals(castOther.deptId)
			&& this.idNoOld.equals(castOther.idNoOld)
			&& this.idNoNew.equals(castOther.idNoNew);

    }
    
	public int hashCode() {
		final int prime = 31;
		int hash = 17;
		hash = hash * prime + this.applicationId.hashCode();
		hash = hash * prime + this.deptId.hashCode();
		hash = hash * prime + this.idNoOld.hashCode();
		hash = hash * prime + this.idNoNew.hashCode();
		
		return hash;
    }
}