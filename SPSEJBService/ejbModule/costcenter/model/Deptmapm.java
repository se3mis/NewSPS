package costcenter.model;

import java.io.Serializable;
import javax.persistence.*;


/**
 * The persistent class for the DEPTMAPM database table.
 * 
 */
@Entity
public class Deptmapm implements Serializable {
	private static final long serialVersionUID = 1L;

	@Id
	@Column(name="DEPT_ID")
	private String deptId;

	@Column(name="MAP_DEPT")
	private String mapDept;

    public Deptmapm() {
    }

	public String getDeptId() {
		return this.deptId;
	}

	public void setDeptId(String deptId) {
		this.deptId = deptId;
	}

	public String getMapDept() {
		return this.mapDept;
	}

	public void setMapDept(String mapDept) {
		this.mapDept = mapDept;
	}

}