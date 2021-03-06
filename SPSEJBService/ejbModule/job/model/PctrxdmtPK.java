package job.model;

import java.io.Serializable;
import javax.persistence.*;

/**
 * The primary key class for the PCTRXDMT database table.
 * 
 */
@Embeddable
public class PctrxdmtPK implements Serializable {
	//default serial version id, required for serializable classes.
	private static final long serialVersionUID = 1L;

	@Column(name="DOC_NO")
	private String docNo;

	@Column(name="DOC_PF")
	private String docPf;

	@Column(name="DEPT_ID")
	private String deptId;

	@Column(name="SEQ_NO")
	private long seqNo;

    public PctrxdmtPK() {
    }
	public String getDocNo() {
		return this.docNo;
	}
	public void setDocNo(String docNo) {
		this.docNo = docNo;
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
	public long getSeqNo() {
		return this.seqNo;
	}
	public void setSeqNo(long seqNo) {
		this.seqNo = seqNo;
	}

	public boolean equals(Object other) {
		if (this == other) {
			return true;
		}
		if (!(other instanceof PctrxdmtPK)) {
			return false;
		}
		PctrxdmtPK castOther = (PctrxdmtPK)other;
		return 
			this.docNo.equals(castOther.docNo)
			&& this.docPf.equals(castOther.docPf)
			&& this.deptId.equals(castOther.deptId)
			&& (this.seqNo == castOther.seqNo);

    }
    
	public int hashCode() {
		final int prime = 31;
		int hash = 17;
		hash = hash * prime + this.docNo.hashCode();
		hash = hash * prime + this.docPf.hashCode();
		hash = hash * prime + this.deptId.hashCode();
		hash = hash * prime + ((int) (this.seqNo ^ (this.seqNo >>> 32)));
		
		return hash;
    }
	@Override
	public String toString() {
		return "PctrxdmtPK [docNo=" + docNo + ", docPf=" + docPf + ", deptId="
				+ deptId + ", seqNo=" + seqNo + "]";
	}
	
	
}