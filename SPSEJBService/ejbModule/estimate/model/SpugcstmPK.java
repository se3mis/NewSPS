package estimate.model;

import java.io.Serializable;
import javax.persistence.*;

/**
 * The primary key class for the SPUGCSTM database table.
 * 
 */
@Embeddable
public class SpugcstmPK implements Serializable {
	//default serial version id, required for serializable classes.
	private static final long serialVersionUID = 1L;

	private long phase;

	@Column(name="CONNECTION_TYPE")
	private long connectionType;

	@Column(name="LOOP_CABLE")
	private String loopCable;

	private String category;

    public SpugcstmPK() {
    }
	public long getPhase() {
		return this.phase;
	}
	public void setPhase(long phase) {
		this.phase = phase;
	}
	public long getConnectionType() {
		return this.connectionType;
	}
	public void setConnectionType(long connectionType) {
		this.connectionType = connectionType;
	}
	public String getLoopCable() {
		return this.loopCable;
	}
	public void setLoopCable(String loopCable) {
		this.loopCable = loopCable;
	}
	public String getCategory() {
		return this.category;
	}
	public void setCategory(String category) {
		this.category = category;
	}

	public boolean equals(Object other) {
		if (this == other) {
			return true;
		}
		if (!(other instanceof SpugcstmPK)) {
			return false;
		}
		SpugcstmPK castOther = (SpugcstmPK)other;
		return 
			(this.phase == castOther.phase)
			&& (this.connectionType == castOther.connectionType)
			&& this.loopCable.equals(castOther.loopCable)
			&& this.category.equals(castOther.category);

    }
    
	public int hashCode() {
		final int prime = 31;
		int hash = 17;
		hash = hash * prime + ((int) (this.phase ^ (this.phase >>> 32)));
		hash = hash * prime + ((int) (this.connectionType ^ (this.connectionType >>> 32)));
		hash = hash * prime + this.loopCable.hashCode();
		hash = hash * prime + this.category.hashCode();
		
		return hash;
    }
	
	@Override
	public String toString() {
		return "SpugcstmPK [phase=" + phase + ", connectionType="
				+ connectionType + ", loopCable=" + loopCable + ", category="
				+ category + "]";
	}
	
	
}