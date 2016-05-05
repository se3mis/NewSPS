package masters.model;

import java.io.Serializable;
import javax.persistence.*;

/**
 * The primary key class for the BANK_BRANCH database table.
 * 
 */
@Embeddable
public class ProvinceDetailMasterPK implements Serializable {
	//default serial version id, required for serializable classes.
	private static final long serialVersionUID = 1L;
	
	@Column(name="DEPT_ID")
	private String deptId;

	@Column(name="CODE_TYPE")
	private String codeType;
	
	@Column(name="CODE")
	private String code;

   
	public String getDeptId() {
		return deptId;
	}

	public void setDeptId(String deptId) {
		this.deptId = deptId;
	}

	public String getCodeType() {
		return codeType;
	}

	public void setCodeType(String codeType) {
		this.codeType = codeType;
	}

	public String getCode() {
		return code;
	}

	public void setCode(String code) {
		this.code = code;
	}
	
    public ProvinceDetailMasterPK() {
    }
	
	public boolean equals(Object other) {
		if (this == other) {
			return true;
		}
		if (!(other instanceof ProvinceDetailMasterPK)) {
			return false;
		}
		ProvinceDetailMasterPK castOther = (ProvinceDetailMasterPK)other;
		return 
			this.deptId.equals(castOther.deptId)
			&& this.codeType.equals(castOther.codeType)
			&& this.code.equals(castOther.code)
			;

    }
    
	public int hashCode() {
		final int prime = 31;
		int hash = 17;
		hash = hash * prime + this.deptId.hashCode();
		hash = hash * prime + this.codeType.hashCode();
		hash = hash * prime + this.code.hashCode();
		return hash;
    }
	@Override
	public String toString() {
		return "DistrictPK [deptId=" + deptId + ", codeType="
				+ codeType + "]";
	}
}