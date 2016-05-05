package fa.model;

import java.io.Serializable;
import javax.persistence.*;

/**
 * The primary key class for the FAMATCAT database table.
 * 
 */
@Embeddable
public class FamatcatPK implements Serializable {
	//default serial version id, required for serializable classes.
	private static final long serialVersionUID = 1L;

	@Column(name="MAT_CD")
	private String matCd;

	@Column(name="SUB_CAT_CODE")
	private String subCatCode;

    public FamatcatPK() {
    }
	public String getMatCd() {
		return this.matCd;
	}
	public void setMatCd(String matCd) {
		this.matCd = matCd;
	}
	public String getSubCatCode() {
		return this.subCatCode;
	}
	public void setSubCatCode(String subCatCode) {
		this.subCatCode = subCatCode;
	}

	public boolean equals(Object other) {
		if (this == other) {
			return true;
		}
		if (!(other instanceof FamatcatPK)) {
			return false;
		}
		FamatcatPK castOther = (FamatcatPK)other;
		return 
			this.matCd.equals(castOther.matCd)
			&& this.subCatCode.equals(castOther.subCatCode);

    }
    
	public int hashCode() {
		final int prime = 31;
		int hash = 17;
		hash = hash * prime + this.matCd.hashCode();
		hash = hash * prime + this.subCatCode.hashCode();
		
		return hash;
    }
	@Override
	public String toString() {
		return "FamatcatPK [matCd=" + matCd + ", subCatCode=" + subCatCode
				+ "]";
	}
}