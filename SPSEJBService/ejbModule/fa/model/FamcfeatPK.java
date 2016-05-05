package fa.model;

import java.io.Serializable;
import javax.persistence.*;

/**
 * The primary key class for the FAMCFEAT database table.
 * 
 */
@Embeddable
public class FamcfeatPK implements Serializable {
	//default serial version id, required for serializable classes.
	private static final long serialVersionUID = 1L;

	@Column(name="CAT_CODE")
	private String catCode;

	@Column(name="FEATURE_CODE")
	private String featureCode;

    public FamcfeatPK() {
    }
	public String getCatCode() {
		return this.catCode;
	}
	public void setCatCode(String catCode) {
		this.catCode = catCode;
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
		if (!(other instanceof FamcfeatPK)) {
			return false;
		}
		FamcfeatPK castOther = (FamcfeatPK)other;
		return 
			this.catCode.equals(castOther.catCode)
			&& this.featureCode.equals(castOther.featureCode);

    }
    
	public int hashCode() {
		final int prime = 31;
		int hash = 17;
		hash = hash * prime + this.catCode.hashCode();
		hash = hash * prime + this.featureCode.hashCode();
		
		return hash;
    }
	@Override
	public String toString() {
		return "FamcfeatPK [catCode=" + catCode + ", featureCode="
				+ featureCode + "]";
	}
}