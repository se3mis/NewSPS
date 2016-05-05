package estimate.model;

import java.io.Serializable;
import javax.persistence.*;

/**
 * The primary key class for the PCFUNDDM database table.
 * 
 */
@Embeddable
public class PegschdmtPK implements Serializable {
	//default serial version id, required for serializable classes.
	private static final long serialVersionUID = 1L;

	@Column(name="ESTIMATENO")
	private String estimateNo;

	@Column(name="NODEID")
	private String nodeId;

	@Column(name="DEPT_ID")
	private String deptId;



	public String getEstimateNo() {
		return estimateNo;
	}

	public void setEstimateNo(String estimateNo) {
		this.estimateNo = estimateNo;
	}

	public String getNodeId() {
		return nodeId;
	}

	public void setNodeId(String nodeId) {
		this.nodeId = nodeId;
	}

	public String getDeptId() {
		return deptId;
	}

	public void setDeptId(String deptId) {
		this.deptId = deptId;
	}

	public boolean equals(Object other) {
		if (this == other) {
			return true;
		}
		if (!(other instanceof PegschdmtPK)) {
			return false;
		}
		PegschdmtPK castOther = (PegschdmtPK)other;
		return 
			this.estimateNo.equals(castOther.estimateNo)
			&& this.nodeId.equals(castOther.nodeId)
			&& this.deptId.equals(castOther.deptId);

    }
    
	public int hashCode() {
		final int prime = 31;
		int hash = 17;
		hash = hash * prime + this.estimateNo.hashCode();
		hash = hash * prime + this.nodeId.hashCode();
		hash = hash * prime + this.deptId.hashCode();
		
		return hash;
    }
	@Override
	public String toString() {
		return "PegschdmtPK [estimateNo=" + estimateNo + ", nodeId=" + nodeId
				+ ", deptId=" + deptId + "]";
	}
	
	
}