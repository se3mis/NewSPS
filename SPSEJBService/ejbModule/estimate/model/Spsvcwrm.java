package estimate.model;

import java.io.Serializable;
import javax.persistence.*;
import java.math.BigDecimal;


/**
 * The persistent class for the SPSVCWRM database table.
 * 
 */
@Entity
public class Spsvcwrm implements Serializable {
	private static final long serialVersionUID = 1L;

	@EmbeddedId
	private SpsvcwrmPK id;

	@Column(name="EXTRA_QTY")
	private BigDecimal extraQty;

	@Column(name="MEASUREMENT_FACTOR")
	private BigDecimal measurementFactor;

    public Spsvcwrm() {
    }

	public SpsvcwrmPK getId() {
		return this.id;
	}

	public void setId(SpsvcwrmPK id) {
		this.id = id;
	}
	
	public BigDecimal getExtraQty() {
		return this.extraQty;
	}

	public void setExtraQty(BigDecimal extraQty) {
		this.extraQty = extraQty;
	}

	public BigDecimal getMeasurementFactor() {
		return this.measurementFactor;
	}

	public void setMeasurementFactor(BigDecimal measurementFactor) {
		this.measurementFactor = measurementFactor;
	}

	@Override
	public String toString() {
		return "Spsvcwrm [id=" + id + ", extraQty=" + extraQty
				+ ", measurementFactor=" + measurementFactor + "]";
	}

}