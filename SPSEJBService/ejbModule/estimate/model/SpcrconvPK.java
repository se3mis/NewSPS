package estimate.model;

import java.io.Serializable;
import javax.persistence.*;

/**
 * The primary key class for the SPCRCONV database table.
 * 
 */
@Embeddable
public class SpcrconvPK implements Serializable {
	//default serial version id, required for serializable classes.
	private static final long serialVersionUID = 1L;

	@Column(name="FROM_PHASE")
	private long fromPhase;

	@Column(name="TO_PHASE")
	private long toPhase;

	@Column(name="FROM_CONNECTION_TYPE")
	private long fromConnectionType;

	@Column(name="TO_CONNECTION_TYPE")
	private long toConnectionType;

    public SpcrconvPK() {
    }
	public long getFromPhase() {
		return this.fromPhase;
	}
	public void setFromPhase(long fromPhase) {
		this.fromPhase = fromPhase;
	}
	public long getToPhase() {
		return this.toPhase;
	}
	public void setToPhase(long toPhase) {
		this.toPhase = toPhase;
	}
	public long getFromConnectionType() {
		return this.fromConnectionType;
	}
	public void setFromConnectionType(long fromConnectionType) {
		this.fromConnectionType = fromConnectionType;
	}
	public long getToConnectionType() {
		return this.toConnectionType;
	}
	public void setToConnectionType(long toConnectionType) {
		this.toConnectionType = toConnectionType;
	}

	public boolean equals(Object other) {
		if (this == other) {
			return true;
		}
		if (!(other instanceof SpcrconvPK)) {
			return false;
		}
		SpcrconvPK castOther = (SpcrconvPK)other;
		return 
			(this.fromPhase == castOther.fromPhase)
			&& (this.toPhase == castOther.toPhase)
			&& (this.fromConnectionType == castOther.fromConnectionType)
			&& (this.toConnectionType == castOther.toConnectionType);

    }
    
	public int hashCode() {
		final int prime = 31;
		int hash = 17;
		hash = hash * prime + ((int) (this.fromPhase ^ (this.fromPhase >>> 32)));
		hash = hash * prime + ((int) (this.toPhase ^ (this.toPhase >>> 32)));
		hash = hash * prime + ((int) (this.fromConnectionType ^ (this.fromConnectionType >>> 32)));
		hash = hash * prime + ((int) (this.toConnectionType ^ (this.toConnectionType >>> 32)));
		
		return hash;
    }
	@Override
	public String toString() {
		return "SpcrconvPK [fromPhase=" + fromPhase + ", toPhase=" + toPhase
				+ ", fromConnectionType=" + fromConnectionType
				+ ", toConnectionType=" + toConnectionType + "]";
	}
}