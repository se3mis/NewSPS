package estimate.model;

import java.io.Serializable;
import javax.persistence.*;
import java.math.BigDecimal;


/**
 * The persistent class for the SPRECONN database table.
 * 
 */
@Entity
public class Spreconn implements Serializable {
	private static final long serialVersionUID = 1L;

	@Id
	@Column(name="DISCONNECT_DURATION")
	private long disconnectDuration;

	private BigDecimal rate;

    public Spreconn() {
    }

	public long getDisconnectDuration() {
		return this.disconnectDuration;
	}

	public void setDisconnectDuration(long disconnectDuration) {
		this.disconnectDuration = disconnectDuration;
	}

	public BigDecimal getRate() {
		return this.rate;
	}

	public void setRate(BigDecimal rate) {
		this.rate = rate;
	}

}