package progressMonitoring.model;
import java.io.Serializable;
import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Embeddable;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;

import javax.persistence.Table;



@Embeddable

@Table(name="PCMILEDATES")
public class PcmiledatesPK implements Serializable {

	private static final long serialVersionUID = 1L;
	
	
	@Column(name="MILE_ID")
	private String mileId;
	
	@Column(name="PROJECT_NO")
	private String projectNumber;
	
	@Column(name="DEPT_ID")
	private String deptId;
	
	@Temporal( TemporalType.DATE)
	@Column (name="ENTER_DATE")
	private Date enterDate;
	
	
	public String getMileId() {
		return mileId;
	}

	public void setMileId(String mileId) {
		this.mileId = mileId;
	}

	public String getProjectNumber() {
		return projectNumber;
	}

	public void setProjectNumber(String projectNumber) {
		this.projectNumber = projectNumber;
	}

	public String getDeptId() {
		return deptId;
	}

	public void setDeptId(String deptId) {
		this.deptId = deptId;
	}

	public Date getEnterDate() {
		return enterDate;
	}

	public void setEnterDate(Date enterDate) {
		this.enterDate = enterDate;
	}

	public static long getSerialversionuid() {
		return serialVersionUID;
	}

	public boolean equals(Object other) {
		if (this == other) {
			return true;
		}
		if (!(other instanceof PcmiledatesPK)) {
			return false;
		}
		PcmiledatesPK castOther = (PcmiledatesPK)other;
		return 
			this.mileId.equals(castOther.mileId)
			&& this.projectNumber.equals(castOther.projectNumber)&&this.deptId.equals(castOther.deptId)&&this.enterDate.equals(castOther.enterDate);

    }
    
	public int hashCode() {
		final int prime = 31;
		int hash = 17;
		hash = hash * prime + this.mileId.hashCode();
		hash = hash * prime + this.projectNumber.hashCode();
		hash = hash * prime + this.deptId.hashCode();
		hash = hash * prime + this.enterDate.hashCode();
		
		return hash;
    }
	@Override
	public String toString() {
		return "pcmiledatesPK [ mileId=" + mileId
				+ ", projectNumber=" + projectNumber + ", deptId"+deptId+",enterDate"+enterDate+ "]";
	}
	
	
	
	
	
	
	
	
	
	
	
	
}
