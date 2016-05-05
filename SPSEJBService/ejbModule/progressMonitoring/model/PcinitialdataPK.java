package progressMonitoring.model;

import java.io.Serializable;

import javax.persistence.Column;
import javax.persistence.Embeddable;

import javax.persistence.Table;



@Embeddable

@Table(name="PCINITIALDATA")
public class PcinitialdataPK implements Serializable{

	/**
	 * 
	 */
	
	private static final long serialVersionUID = 1L;

	@Column(name="REF_NO")
	private String refNo;
	
	@Column (name="FILE_NO")
	private String fileNo;
	
	@Column(name="DEPT_ID")
	private String deptId;

	public void setRefNo(String refNo) {
		this.refNo = refNo;
	}

	public String getRefNo() {
		return refNo;
	}

	public void setDeptId(String deptId) {
		this.deptId = deptId;
	}

	public String getDeptId() {
		return deptId;
	}

	public void setFileNo(String fileNo) {
		this.fileNo = fileNo;
	}

	public String getFileNo() {
		return fileNo;
	}
	
	public boolean equals(Object other) {
		if (this == other) {
			return true;
		}
		if (!(other instanceof PcinitialdataPK)) {
			return false;
		}
		PcinitialdataPK castOther = (PcinitialdataPK)other;
		return 
			this.deptId.equals(castOther.deptId)
			&& this.refNo.equals(castOther.refNo)
			&& this.fileNo.equals(castOther.fileNo);

    }
    
	public int hashCode() {
		final int prime = 31;
		int hash = 17;
		hash = hash * prime + this.refNo.hashCode();
		hash = hash * prime + this.deptId.hashCode();
		
		return hash;
    }
	@Override
	public String toString() {
		return "PcesthmtPK [ refNo=" + refNo
				+ ", deptId=" + deptId + "]";
	}
	

}
