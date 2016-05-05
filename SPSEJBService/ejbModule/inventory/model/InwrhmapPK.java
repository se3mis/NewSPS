package inventory.model;

import java.io.Serializable;
import javax.persistence.*;

/**
 * The primary key class for the INWRHMAP database table.
 * 
 */
@Embeddable
public class InwrhmapPK implements Serializable {
	//default serial version id, required for serializable classes.
	private static final long serialVersionUID = 1L;

	@Column(name="DEPT_ID")
	private String deptId;

	@Column(name="WRH_DEPT")
	private String wrhDept;

    public InwrhmapPK() {
    }
	public String getDeptId() {
		return this.deptId;
	}
	public void setDeptId(String deptId) {
		this.deptId = deptId;
	}
	public String getWrhDept() {
		return this.wrhDept;
	}
	public void setWrhDept(String wrhDept) {
		this.wrhDept = wrhDept;
	}

	public boolean equals(Object other) {
		if (this == other) {
			return true;
		}
		if (!(other instanceof InwrhmapPK)) {
			return false;
		}
		InwrhmapPK castOther = (InwrhmapPK)other;
		return 
			this.deptId.equals(castOther.deptId)
			&& this.wrhDept.equals(castOther.wrhDept);

    }
    
	public int hashCode() {
		final int prime = 31;
		int hash = 17;
		hash = hash * prime + this.deptId.hashCode();
		hash = hash * prime + this.wrhDept.hashCode();
		
		return hash;
    }
	@Override
	public String toString() {
		return "InwrhmapPK [deptId=" + deptId + ", wrhDept=" + wrhDept + "]";
	}
	
}