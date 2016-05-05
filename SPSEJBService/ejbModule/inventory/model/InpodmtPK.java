package inventory.model;

import java.io.Serializable;
import javax.persistence.*;

/**
 * The primary key class for the INPODMT database table.
 * 
 */
@Embeddable
public class InpodmtPK implements Serializable {
	//default serial version id, required for serializable classes.
	private static final long serialVersionUID = 1L;

	@Column(name="MAT_CD")
	private String matCd;

	@Column(name="BATCH_ID")
	private String batchId;

	@Column(name="DEPT_ID")
	private String deptId;

	@Column(name="UNIT_PRICE")
	private long unitPrice;

	public InpodmtPK() {
	}
	public String getMatCd() {
		return this.matCd;
	}
	public void setMatCd(String matCd) {
		this.matCd = matCd;
	}
	public String getBatchId() {
		return this.batchId;
	}
	public void setBatchId(String batchId) {
		this.batchId = batchId;
	}
	public String getDeptId() {
		return this.deptId;
	}
	public void setDeptId(String deptId) {
		this.deptId = deptId;
	}
	public long getUnitPrice() {
		return this.unitPrice;
	}
	public void setUnitPrice(long unitPrice) {
		this.unitPrice = unitPrice;
	}

	public boolean equals(Object other) {
		if (this == other) {
			return true;
		}
		if (!(other instanceof InpodmtPK)) {
			return false;
		}
		InpodmtPK castOther = (InpodmtPK)other;
		return 
			this.matCd.equals(castOther.matCd)
			&& this.batchId.equals(castOther.batchId)
			&& this.deptId.equals(castOther.deptId)
			&& (this.unitPrice == castOther.unitPrice);
	}

	public int hashCode() {
		final int prime = 31;
		int hash = 17;
		hash = hash * prime + this.matCd.hashCode();
		hash = hash * prime + this.batchId.hashCode();
		hash = hash * prime + this.deptId.hashCode();
		hash = hash * prime + ((int) (this.unitPrice ^ (this.unitPrice >>> 32)));
		
		return hash;
	}
}