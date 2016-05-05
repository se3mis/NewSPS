package job.model;

import java.io.Serializable;
import javax.persistence.*;

/**
 * The primary key class for the SPODRCRD database table.
 * 
 */
@Embeddable
public class SpodrcrdPK implements Serializable {
	//default serial version id, required for serializable classes.
	private static final long serialVersionUID = 1L;

	@Column(name="PROJECT_NO")
	private String projectNo;

	@Column(name="DEPT_ID")
	private String deptId;

    public SpodrcrdPK() {
    }
	public String getProjectNo() {
		return this.projectNo;
	}
	public void setProjectNo(String projectNo) {
		this.projectNo = projectNo;
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
		if (!(other instanceof SpodrcrdPK)) {
			return false;
		}
		SpodrcrdPK castOther = (SpodrcrdPK)other;
		return 
			this.projectNo.equals(castOther.projectNo)
			&& this.deptId.equals(castOther.deptId);

    }
    
	public int hashCode() {
		final int prime = 31;
		int hash = 17;
		hash = hash * prime + this.projectNo.hashCode();
		hash = hash * prime + this.deptId.hashCode();
		
		return hash;
    }
}