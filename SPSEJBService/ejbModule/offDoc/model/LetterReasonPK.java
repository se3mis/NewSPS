package offDoc.model;

import java.io.Serializable;
import javax.persistence.*;

/**
 * The primary key class for the LETTER_REASONS database table.
 * 
 */
@Embeddable
public class LetterReasonPK implements Serializable {
	//default serial version id, required for serializable classes.
	private static final long serialVersionUID = 1L;

	@Column(name="DEPT_ID")
	private String deptId;

	@Column(name="LETTER_TYPE")
	private String letterType;

	@Column(name="REASON_CODE")
	private String reasonCode;

    public LetterReasonPK() {
    }
	public String getDeptId() {
		return this.deptId;
	}
	public void setDeptId(String deptId) {
		this.deptId = deptId;
	}
	public String getLetterType() {
		return this.letterType;
	}
	public void setLetterType(String letterType) {
		this.letterType = letterType;
	}
	public String getReasonCode() {
		return this.reasonCode;
	}
	public void setReasonCode(String reasonCode) {
		this.reasonCode = reasonCode;
	}

	public boolean equals(Object other) {
		if (this == other) {
			return true;
		}
		if (!(other instanceof LetterReasonPK)) {
			return false;
		}
		LetterReasonPK castOther = (LetterReasonPK)other;
		return 
			this.deptId.equals(castOther.deptId)
			&& this.letterType.equals(castOther.letterType)
			&& this.reasonCode.equals(castOther.reasonCode);

    }
    
	public int hashCode() {
		final int prime = 31;
		int hash = 17;
		hash = hash * prime + this.deptId.hashCode();
		hash = hash * prime + this.letterType.hashCode();
		hash = hash * prime + this.reasonCode.hashCode();
		
		return hash;
    }
	@Override
	public String toString() {
		return "LetterReasonPK [deptId=" + deptId + ", letterType="
				+ letterType + ", reasonCode=" + reasonCode + "]";
	}
	
}