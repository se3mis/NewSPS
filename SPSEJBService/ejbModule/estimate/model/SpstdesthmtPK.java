package estimate.model;

import java.io.Serializable;
import javax.persistence.*;

/**
 * The primary key class for the SPSTAYMT database table.
 * 
 */
@Embeddable
public class SpstdesthmtPK implements Serializable {
	//default serial version id, required for serializable classes.
	private static final long serialVersionUID = 1L;

	@Column(name="APP_NO")
	private String applicationNo;

	@Column(name="STD_NO")
	private String stdNo;
	
	@Column(name="DEPT_ID")
	private String deptId;
	
    public String getDeptId() {
		return deptId;
	}


	public void setDeptId(String deptId) {
		this.deptId = deptId;
	}


	public SpstdesthmtPK() {
    }
	

	public String getApplicationNo() {
		return applicationNo;
	}


	public void setApplicationNo(String applicationNo) {
		this.applicationNo = applicationNo;
	}


	public String getStdNo() {
		return stdNo;
	}


	public void setStdNo(String stdNo) {
		this.stdNo = stdNo;
	}


	public boolean equals(Object other) {
		if (this == other) {
			return true;
		}
		if (!(other instanceof SpstdesthmtPK)) {
			return false;
		}
		SpstdesthmtPK castOther = (SpstdesthmtPK)other;
		return 
			this.applicationNo.equals(castOther.applicationNo)
			&& this.stdNo.equals(castOther.stdNo) ;

    }
    
	public int hashCode() {
		final int prime = 31;
		int hash = 17;
		hash = hash * prime + this.applicationNo.hashCode();
		hash = hash * prime + this.stdNo.hashCode();
		
		return hash;
    }
	@Override
	public String toString() {
		return "SpstdesthmtPK [applicationNo=" + applicationNo + ", stdNo=" + stdNo + ", deptId=" + deptId +"]";
	}
}