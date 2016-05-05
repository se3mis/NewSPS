package estimate.model;

import java.io.Serializable;
import javax.persistence.*;

/**
 * The primary key class for the SPCNDCTM database table.
 * 
 */
@Embeddable
public class SpcndctmPK implements Serializable {
	//default serial version id, required for serializable classes.
	private static final long serialVersionUID = 1L;

	private long phase;

	@Column(name="CONDUCTOR_TYPE")
	private String conductorType;

    public SpcndctmPK() {
    }
	public long getPhase() {
		return this.phase;
	}
	public void setPhase(long phase) {
		this.phase = phase;
	}
	public String getConductorType() {
		return this.conductorType;
	}
	public void setConductorType(String conductorType) {
		this.conductorType = conductorType;
	}

	public boolean equals(Object other) {
		if (this == other) {
			return true;
		}
		if (!(other instanceof SpcndctmPK)) {
			return false;
		}
		SpcndctmPK castOther = (SpcndctmPK)other;
		return 
			(this.phase == castOther.phase)
			&& this.conductorType.equals(castOther.conductorType);

    }
    
	public int hashCode() {
		final int prime = 31;
		int hash = 17;
		hash = hash * prime + ((int) (this.phase ^ (this.phase >>> 32)));
		hash = hash * prime + this.conductorType.hashCode();
		
		return hash;
    }
}