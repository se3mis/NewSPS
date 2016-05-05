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
public class SpPegInfoPK implements Serializable {
	//default serial version id, required for serializable classes.
	private static final long serialVersionUID = 1L;

	@Column(name="ID")
	private String pegItemId;

	@Column(name="PARENT_ID")
	private String parent_id;
	
    
	@Column(name="DEPT_ID")
	private String masterId;
	

	public String getPegItemId() {
		return pegItemId;
	}

	public void setPegItemId(String pegItemId) {
		this.pegItemId = pegItemId;
	}

	public String getParent_id() {
		return parent_id;
	}

	public void setParent_id(String parent_id) {
		this.parent_id = parent_id;
	}

	public String getMasterId() {
		return masterId;
	}

	public void setMasterId(String masterId) {
		this.masterId = masterId;
	}

	public SpPegInfoPK() {
    }
	
	public boolean equals(Object other) {
		if (this == other) {
			return true;
		}
		if (!(other instanceof SpPegInfoPK)) {
			return false;
		}
		SpPegInfoPK castOther = (SpPegInfoPK)other;
		return 
			(this.pegItemId.equals(castOther.pegItemId))&&(this.parent_id.equals(castOther.parent_id))
			;

    }

	public int hashCode() {
		final int prime = 31;
		int hash = 17;
		hash = hash * prime + this.pegItemId.hashCode();
		hash = hash * prime + this.parent_id.hashCode();
		return hash;
    }
	@Override
	public String toString() {
		return "SpPegInfoPK [lineSectionTypeId=" + pegItemId + "parenId=" + parent_id + "]";
	}
}