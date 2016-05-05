package consumer.model;

import java.io.Serializable;
import javax.persistence.*;

/**
 * The primary key class for the CONSUMER database table.
 * 
 */
@Embeddable
public class ConsumerPK implements Serializable {
	//default serial version id, required for serializable classes.
	private static final long serialVersionUID = 1L;

	@Column(name="APPLICATION_REF_NO")
	private String applicationRefNo;

	@Column(name="DEPT_ID")
	private String deptId;

    public ConsumerPK() {
    }
	public String getApplicationRefNo() {
		return this.applicationRefNo;
	}
	public void setApplicationRefNo(String applicationRefNo) {
		this.applicationRefNo = applicationRefNo;
	}
	public String getDeptId() {
		return this.deptId;
	}
	public void setDeptId(String deptId) {
		this.deptId = deptId;
	}

	public boolean equals(Object other) {
		if (this == other) {
			return true;
		}
		if (!(other instanceof ConsumerPK)) {
			return false;
		}
		ConsumerPK castOther = (ConsumerPK)other;
		return 
			this.applicationRefNo.equals(castOther.applicationRefNo)
			&& this.deptId.equals(castOther.deptId);

    }
    
	public int hashCode() {
		final int prime = 31;
		int hash = 17;
		hash = hash * prime + this.applicationRefNo.hashCode();
		hash = hash * prime + this.deptId.hashCode();
		
		return hash;
    }
	@Override
	public String toString() {
		return "ConsumerPK [applicationRefNo=" + applicationRefNo + ", deptId="
				+ deptId + "]";
	}
}