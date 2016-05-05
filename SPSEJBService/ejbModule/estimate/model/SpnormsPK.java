package estimate.model;

import java.io.Serializable;
import javax.persistence.*;

/**
 * The primary key class for the SPCRCONV database table.
 * 
 */
@Embeddable
public class SpnormsPK implements Serializable {
	//default serial version id, required for serializable classes.
	private static final long serialVersionUID = 1L;

	@Column(name="LINESECTIONTYPEID")
	private String lineSectionTypeId;

	
    public String getLineSectionTypeId() {
		return this.lineSectionTypeId;
	}

	public void setLineSectionTypeId(String lineSectionTypeId) {
		this.lineSectionTypeId = lineSectionTypeId;
	}

	public SpnormsPK() {
    }
	
	public boolean equals(Object other) {
		if (this == other) {
			return true;
		}
		if (!(other instanceof SpnormsPK)) {
			return false;
		}
		SpnormsPK castOther = (SpnormsPK)other;
		return 
			(this.lineSectionTypeId == castOther.lineSectionTypeId)
			;

    }

	public int hashCode() {
		final int prime = 31;
		int hash = 17;
		hash = hash * prime + this.lineSectionTypeId.hashCode();
		
		return hash;
    }
	@Override
	public String toString() {
		return "NormsPK [lineSectionTypeId=" + lineSectionTypeId + "]";
	}
}