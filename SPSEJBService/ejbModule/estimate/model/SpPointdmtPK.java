package estimate.model;

import java.io.Serializable;
import javax.persistence.*;

/**
 * Author: Dinusha Nirmalie
 * Created: March 01, 2012 11:23:42 AM
 */

/**
 * The persistent class for the SpPegInfo database table.
 * 
 */

@Embeddable
public class SpPointdmtPK implements Serializable {
	//default serial version id, required for serializable classes.
	private static final long serialVersionUID = 1L;

	@Column(name="LINESECTIONTYPEID")
	private String lineSectionTypeId;

	
	@Column(name="RES_CD")
	private String resCd;

	@Column(name="DEPT_ID")
	private String masterId;
	
	public String getLineSectionTypeId() {
		return lineSectionTypeId;
	}

	public void setLineSectionTypeId(String lineSectionTypeId) {
		this.lineSectionTypeId = lineSectionTypeId;
	}

	public String getResCd() {
		return resCd;
	}

	public void setResCd(String resCd) {
		this.resCd = resCd;
	}

	public String getMasterId() {
		return masterId;
	}

	public void setMasterId(String masterId) {
		this.masterId = masterId;
	}

	public SpPointdmtPK() {
    }
	
	public boolean equals(Object other) {
		if (this == other) {
			return true;
		}
		if (!(other instanceof SpPointdmtPK)) {
			return false;
		}
		SpPointdmtPK castOther = (SpPointdmtPK)other;
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
		return "SpPointdmtPK [lineSectionTypeId=" + lineSectionTypeId + ",resCd=" + resCd + "]";
	}
}