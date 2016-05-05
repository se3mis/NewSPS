package fa.model;

import java.io.Serializable;
import javax.persistence.*;

/**
 * The primary key class for the ARBATCHM database table.
 * 
 */
@Embeddable
public class ArbatchmPK implements Serializable {
	//default serial version id, required for serializable classes.
	private static final long serialVersionUID = 1L;

	@Column(name="DOC_PF")
	private String docPf;

	@Column(name="DEPT_ID")
	private String deptId;

	@Column(name="BATCH_ID")
	private String batchId;

    public ArbatchmPK() {
    }
	public String getDocPf() {
		return this.docPf;
	}
	public void setDocPf(String docPf) {
		this.docPf = docPf;
	}
	public String getDeptId() {
		return this.deptId;
	}
	public void setDeptId(String deptId) {
		this.deptId = deptId;
	}
	public String getBatchId() {
		return this.batchId;
	}
	public void setBatchId(String batchId) {
		this.batchId = batchId;
	}

	public boolean equals(Object other) {
		if (this == other) {
			return true;
		}
		if (!(other instanceof ArbatchmPK)) {
			return false;
		}
		ArbatchmPK castOther = (ArbatchmPK)other;
		return 
			this.docPf.equals(castOther.docPf)
			&& this.deptId.equals(castOther.deptId)
			&& this.batchId.equals(castOther.batchId);

    }
    
	public int hashCode() {
		final int prime = 31;
		int hash = 17;
		hash = hash * prime + this.docPf.hashCode();
		hash = hash * prime + this.deptId.hashCode();
		hash = hash * prime + this.batchId.hashCode();
		
		return hash;
    }
	@Override
	public String toString() {
		return "ArbatchmPK [docPf=" + docPf + ", deptId=" + deptId
				+ ", batchId=" + batchId + "]";
	}
}