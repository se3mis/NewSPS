package rebate.model;

import java.io.Serializable;
import javax.persistence.*;

/**
 * The primary key class for the SPREBATE database table.
 * 
 */
@Embeddable
public class SprebatePK implements Serializable {
	//default serial version id, required for serializable classes.
	private static final long serialVersionUID = 1L;

	@Column(name="ESTIMATE_NO")
	private String estimateNo;

	@Column(name="DEPT_ID")
	private String deptId;

	@Column(name="RES_CD")
	private String resCd;

	public SprebatePK() {
	}
	public String getEstimateNo() {
		return this.estimateNo;
	}
	public void setEstimateNo(String estimateNo) {
		this.estimateNo = estimateNo;
	}
	public String getDeptId() {
		return this.deptId;
	}
	public void setDeptId(String deptId) {
		this.deptId = deptId;
	}
	public String getResCd() {
		return this.resCd;
	}
	public void setResCd(String resCd) {
		this.resCd = resCd;
	}

	public boolean equals(Object other) {
		if (this == other) {
			return true;
		}
		if (!(other instanceof SprebatePK)) {
			return false;
		}
		SprebatePK castOther = (SprebatePK)other;
		return 
			this.estimateNo.equals(castOther.estimateNo)
			&& this.deptId.equals(castOther.deptId)
			&& this.resCd.equals(castOther.resCd);
	}

	public int hashCode() {
		final int prime = 31;
		int hash = 17;
		hash = hash * prime + this.estimateNo.hashCode();
		hash = hash * prime + this.deptId.hashCode();
		hash = hash * prime + this.resCd.hashCode();
		
		return hash;
	}
}