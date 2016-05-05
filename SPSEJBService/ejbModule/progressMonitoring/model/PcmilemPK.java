package progressMonitoring.model;

import java.io.Serializable;


import javax.persistence.Embeddable;
import javax.persistence.Column;
import javax.persistence.Table;



@Embeddable
@Table(name = "PCMILEM")
public class PcmilemPK implements Serializable {
	private static final long serialVersionUID = 1L;

	@Column(name = "MILE_ID")
	private String mileId;

	@Column(name = "DEPT_ID")
	private String deptId;

	public String getMileId() {
		return mileId;
	}

	public void setMileId(String mileId) {
		this.mileId = mileId;
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
		if (!(other instanceof PcmilemPK)) {
			return false;
		}
		PcmilemPK castOther = (PcmilemPK) other;
		return this.mileId.equals(castOther.mileId)
				&& this.deptId.equals(castOther.deptId);

	}

	public int hashCode() {
		final int prime = 31;
		int hash = 17;
		hash = hash * prime + this.mileId.hashCode();
		hash = hash * prime + this.deptId.hashCode();
		return hash;
	}

	@Override
	public String toString() {
		return "pcmiledatesPK [ mileId=" + mileId + ", deptId" + deptId + "]";
	}
}
