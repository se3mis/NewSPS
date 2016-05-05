package estimate.model;

import java.io.Serializable;
import javax.persistence.*;
import java.math.BigDecimal;


/**
 * The persistent class for the SPRATESM database table.
 * 
 */
@Entity
public class Spratesm implements Serializable {
	private static final long serialVersionUID = 1L;

	@EmbeddedId
	private SpratesmPK id;

	@Column(name="CONVERSION_RATE")
	private BigDecimal conversionRate;

	@Column(name="CONVERSION_RATE_2P")
	private BigDecimal conversionRate2p;

	@Column(name="FIXED_TRANSPORT_AMT")
	private BigDecimal fixedTransportAmt;

	@Column(name="FIXED_TRANSPORT_DISTANCE")
	private BigDecimal fixedTransportDistance;

	@Column(name="LABOUR_RATE")
	private BigDecimal labourRate;

	@Column(name="OVERHEAD_RATE")
	private BigDecimal overheadRate;

	@Column(name="TRANSPORT_RATE")
	private BigDecimal transportRate;

    public Spratesm() {
    }

	public SpratesmPK getId() {
		return this.id;
	}

	public void setId(SpratesmPK id) {
		this.id = id;
	}
	
	public BigDecimal getConversionRate() {
		return this.conversionRate;
	}

	public void setConversionRate(BigDecimal conversionRate) {
		this.conversionRate = conversionRate;
	}

	public BigDecimal getConversionRate2p() {
		return this.conversionRate2p;
	}

	public void setConversionRate2p(BigDecimal conversionRate2p) {
		this.conversionRate2p = conversionRate2p;
	}

	public BigDecimal getFixedTransportAmt() {
		return this.fixedTransportAmt;
	}

	public void setFixedTransportAmt(BigDecimal fixedTransportAmt) {
		this.fixedTransportAmt = fixedTransportAmt;
	}

	public BigDecimal getFixedTransportDistance() {
		return this.fixedTransportDistance;
	}

	public void setFixedTransportDistance(BigDecimal fixedTransportDistance) {
		this.fixedTransportDistance = fixedTransportDistance;
	}

	public BigDecimal getLabourRate() {
		return this.labourRate;
	}

	public void setLabourRate(BigDecimal labourRate) {
		this.labourRate = labourRate;
	}

	public BigDecimal getOverheadRate() {
		return this.overheadRate;
	}

	public void setOverheadRate(BigDecimal overheadRate) {
		this.overheadRate = overheadRate;
	}

	public BigDecimal getTransportRate() {
		return this.transportRate;
	}

	public void setTransportRate(BigDecimal transportRate) {
		this.transportRate = transportRate;
	}

	@Override
	public String toString() {
		return "Spratesm [id=" + id + ", conversionRate=" + conversionRate
				+ ", conversionRate2p=" + conversionRate2p
				+ ", fixedTransportAmt=" + fixedTransportAmt
				+ ", fixedTransportDistance=" + fixedTransportDistance
				+ ", labourRate=" + labourRate + ", overheadRate="
				+ overheadRate + ", transportRate=" + transportRate + "]";
	}
	

}