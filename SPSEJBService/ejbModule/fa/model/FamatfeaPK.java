package fa.model;

import java.io.Serializable;
import javax.persistence.*;

/**
 * The primary key class for the FAMATFEA database table.
 * 
 */
@Embeddable
public class FamatfeaPK implements Serializable {
	//default serial version id, required for serializable classes.
	private static final long serialVersionUID = 1L;

	@Column(name="MAT_CD")
	private String matCd;

	@Column(name="SUB_CAT_CODE")
	private String subCatCode;

	@Column(name="FEATURE_CODE")
	private String featureCode;

    public FamatfeaPK() {
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
	public String getFeatureCode() {
		return this.featureCode;
	}
	public void setFeatureCode(String featureCode) {
		this.featureCode = featureCode;
	}

	public boolean equals(Object other) {
		if (this == other) {
			return true;
		}
		if (!(other instanceof FamatfeaPK)) {
			return false;
		}
		FamatfeaPK castOther = (FamatfeaPK)other;
		return 
			this.matCd.equals(castOther.matCd)
			&& this.subCatCode.equals(castOther.subCatCode)
			&& this.featureCode.equals(castOther.featureCode);

    }
    
	public int hashCode() {
		final int prime = 31;
		int hash = 17;
		hash = hash * prime + this.matCd.hashCode();
		hash = hash * prime + this.subCatCode.hashCode();
		hash = hash * prime + this.featureCode.hashCode();
		
		return hash;
    }
	@Override
	public String toString() {
		return "FamatfeaPK [matCd=" + matCd + ", subCatCode=" + subCatCode
				+ ", featureCode=" + featureCode + "]";
	}
}