package estimate.model;

import java.io.Serializable;
import javax.persistence.*;
import java.math.BigDecimal;


/**
 * The persistent class for the SPCNDMAT database table.
 * 
 */
@Entity
public class Spcndmat implements Serializable {
	private static final long serialVersionUID = 1L;

	@EmbeddedId
	private SpcndmatPK id;

	@Column(name="MEASUREMENT_FACTOR")
	private BigDecimal measurementFactor;

    public Spcndmat() {
    }

	public SpcndmatPK getId() {
		return this.id;
	}

	public void setId(SpcndmatPK id) {
		this.id = id;
	}
	
	public BigDecimal getMeasurementFactor() {
		return this.measurementFactor;
	}

	public void setMeasurementFactor(BigDecimal measurementFactor) {
		this.measurementFactor = measurementFactor;
	}

	@Override
	public String toString() {
		return "Spcndmat [id=" + id + ", measurementFactor="
				+ measurementFactor + "]";
	}

}