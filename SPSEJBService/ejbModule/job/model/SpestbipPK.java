package job.model;

import java.io.Serializable;
import javax.persistence.*;

/**
 * The primary key class for the SPESTBIP database table.
 * 
 */
@Embeddable
public class SpestbipPK implements Serializable {
	//default serial version id, required for serializable classes.
	private static final long serialVersionUID = 1L;

	@Column(name="DEPT_ID")
	private String deptId;

	@Column(name="PROJECT_NO")
	private String projectNo;

	@Column(name="BILL_SETTLEMENT_NO")
	private String billSettlementNo;

    public SpestbipPK() {
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
	public String getBillSettlementNo() {
		return this.billSettlementNo;
	}
	public void setBillSettlementNo(String billSettlementNo) {
		this.billSettlementNo = billSettlementNo;
	}

	public boolean equals(Object other) {
		if (this == other) {
			return true;
		}
		if (!(other instanceof SpestbipPK)) {
			return false;
		}
		SpestbipPK castOther = (SpestbipPK)other;
		return 
			this.deptId.equals(castOther.deptId)
			&& this.projectNo.equals(castOther.projectNo)
			&& this.billSettlementNo.equals(castOther.billSettlementNo);

    }
    
	public int hashCode() {
		final int prime = 31;
		int hash = 17;
		hash = hash * prime + this.deptId.hashCode();
		hash = hash * prime + this.projectNo.hashCode();
		hash = hash * prime + this.billSettlementNo.hashCode();
		
		return hash;
    }
	@Override
	public String toString() {
		return "SpestbipPK [deptId=" + deptId + ", projectNo=" + projectNo
				+ ", billSettlementNo=" + billSettlementNo + "]";
	}
	
	
}