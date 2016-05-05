package progressMonitoring.model;

import java.io.Serializable;

import javax.persistence.Column;
import javax.persistence.Embeddable;
import javax.persistence.Table;


@Embeddable
@Table(name="PCMILESUMARY")
public class PcmilesumaryPK implements Serializable{

	private static final long serialVersionUID = 1L;
	
	@Column (name="DEPT_ID")
	private String deptId;
	
	@Column (name="PROJECT_NO")
	private String projectNo;
	
	@Column (name="MILE_ID")
	private String mileId;

	public String getDeptId() {
		return deptId;
	}

	public void setDeptId(String deptId) {
		this.deptId = deptId;
	}

	public String getProjectNo() {
		return projectNo;
	}

	public void setProjectNo(String projectNo) {
		this.projectNo = projectNo;
	}

	public String getMileId() {
		return mileId;
	}

	public void setMileId(String mileId) {
		this.mileId = mileId;
	}
	
	public boolean equals(Object other) {
		if (this == other) {
			return true;
		}
		if (!(other instanceof PcmilesumaryPK)) {
			return false;
		}
		PcmilesumaryPK castOther = (PcmilesumaryPK)other;
		return 
			this.mileId.equals(castOther.mileId)
			&& this.deptId.equals(castOther.deptId)&&this.projectNo.equals(castOther.projectNo)&&this.mileId.equals(castOther.mileId);

    }
    
	public int hashCode() {
		final int prime = 31;
		int hash = 17;
		hash = hash * prime + this.mileId.hashCode();
		hash = hash * prime + this.projectNo.hashCode();
		hash = hash * prime + this.deptId.hashCode();
		
		return hash;
    }
	@Override
	public String toString() {
		return "pcmiledatesPK [ mileId=" + mileId
				+ ", projectNumber=" + projectNo + ", deptId"+deptId+ "]";
	}
	
}
