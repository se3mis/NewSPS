package fa.model;

import java.io.Serializable;
import javax.persistence.*;

/**
 * The primary key class for the FASCFEAT database table.
 * 
 */
@Embeddable
public class FascfeatPK implements Serializable {
	//default serial version id, required for serializable classes.
	private static final long serialVersionUID = 1L;

	@Column(name="SUB_CAT_CODE")
	private String subCatCode;

	@Column(name="FEATURE_CODE")
	private String featureCode;

    public FascfeatPK() {
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
		if (!(other instanceof FascfeatPK)) {
			return false;
		}
		FascfeatPK castOther = (FascfeatPK)other;
		return 
			this.subCatCode.equals(castOther.subCatCode)
			&& this.featureCode.equals(castOther.featureCode);

    }
    
	public int hashCode() {
		final int prime = 31;
		int hash = 17;
		hash = hash * prime + this.subCatCode.hashCode();
		hash = hash * prime + this.featureCode.hashCode();
		
		return hash;
    }
	@Override
	public String toString() {
		return "FascfeatPK [subCatCode=" + subCatCode + ", featureCode="
				+ featureCode + "]";
	}
}