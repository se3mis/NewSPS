package estimate.model;

import java.io.Serializable;
import javax.persistence.*;
import java.math.BigDecimal;


/**
 * The persistent class for the SPSEREST database table.
 * 
 */
@Entity
public class Spserest implements Serializable {
	private static final long serialVersionUID = 1L;

	@EmbeddedId
	private SpserestPK id;

	@Column(name="BARECON_DESCR")
	private String bareconDescr;

	@Column(name="BARECON_LENGTH")
	private BigDecimal bareconLength;

	@Column(name="BARECON_TYPE")
	private String bareconType;

	@Column(name="CABLE_TYPE")
	private String cableType;

	@Column(name="CONVERSION_LENGTH")
	private BigDecimal conversionLength;

	@Column(name="CONVERSION_LENGTH_2P")
	private BigDecimal conversionLength2p;

	@Column(name="CONVERSION1_DESCR")
	private String conversion1Descr;

	@Column(name="CONVERSION1_QTY")
	private BigDecimal conversion1Qty;

	@Column(name="CONVERSION2_DESCR")
	private String conversion2Descr;

	@Column(name="CONVERSION2_QTY")
	private BigDecimal conversion2Qty;

	@Column(name="DISTANCE_FROM_SS")
	private BigDecimal distanceFromSs;

	@Column(name="DISTANCE_TO_SP")
	private BigDecimal distanceToSp;

	@Column(name="FEEDER_CONTROL_TYPE")
	private String feederControlType;

	@Column(name="IS_SERVICE_CONVERSION")
	private String isServiceConversion;

	@Column(name="LOOP_CABLE")
	private String loopCable;

	@Column(name="NO_OF_SPANS")
	private BigDecimal noOfSpans;

	private String phase;

	private String poleno;

	@Column(name="SERVICE_LENGTH")
	private String serviceLength;

	@Column(name="SERVICE_WIRE_DESCR")
	private String serviceWireDescr;

	@Column(name="SERVICE_WIRE_TYPE")
	private String serviceWireType;

	private String sin;

	@Column(name="SPANS_DESCR")
	private String spansDescr;

	private String substation;

	@Column(name="TOTAL_LENGTH")
	private String totalLength;

	@Column(name="TRANSFORMER_CAPACITY")
	private String transformerCapacity;

	@Column(name="TRANSFORMER_LOAD")
	private String transformerLoad;

	@Column(name="TRANSFORMER_PEAK_LOAD")
	private String transformerPeakLoad;

	@Column(name="WIRING_TYPE")
	private String wiringType;

    public Spserest() {
    }

	public SpserestPK getId() {
		return this.id;
	}

	public void setId(SpserestPK id) {
		this.id = id;
	}
	
	public String getBareconDescr() {
		return this.bareconDescr;
	}

	public void setBareconDescr(String bareconDescr) {
		this.bareconDescr = bareconDescr;
	}

	public BigDecimal getBareconLength() {
		return this.bareconLength;
	}

	public void setBareconLength(BigDecimal bareconLength) {
		this.bareconLength = bareconLength;
	}

	public String getBareconType() {
		return this.bareconType;
	}

	public void setBareconType(String bareconType) {
		this.bareconType = bareconType;
	}

	public String getCableType() {
		return this.cableType;
	}

	public void setCableType(String cableType) {
		this.cableType = cableType;
	}

	public BigDecimal getConversionLength() {
		return this.conversionLength;
	}

	public void setConversionLength(BigDecimal conversionLength) {
		this.conversionLength = conversionLength;
	}

	public BigDecimal getConversionLength2p() {
		return this.conversionLength2p;
	}

	public void setConversionLength2p(BigDecimal conversionLength2p) {
		this.conversionLength2p = conversionLength2p;
	}

	public String getConversion1Descr() {
		return this.conversion1Descr;
	}

	public void setConversion1Descr(String conversion1Descr) {
		this.conversion1Descr = conversion1Descr;
	}

	public BigDecimal getConversion1Qty() {
		return this.conversion1Qty;
	}

	public void setConversion1Qty(BigDecimal conversion1Qty) {
		this.conversion1Qty = conversion1Qty;
	}

	public String getConversion2Descr() {
		return this.conversion2Descr;
	}

	public void setConversion2Descr(String conversion2Descr) {
		this.conversion2Descr = conversion2Descr;
	}

	public BigDecimal getConversion2Qty() {
		return this.conversion2Qty;
	}

	public void setConversion2Qty(BigDecimal conversion2Qty) {
		this.conversion2Qty = conversion2Qty;
	}

	public BigDecimal getDistanceFromSs() {
		return this.distanceFromSs;
	}

	public void setDistanceFromSs(BigDecimal distanceFromSs) {
		this.distanceFromSs = distanceFromSs;
	}

	public BigDecimal getDistanceToSp() {
		return this.distanceToSp;
	}

	public void setDistanceToSp(BigDecimal distanceToSp) {
		this.distanceToSp = distanceToSp;
	}

	public String getFeederControlType() {
		return this.feederControlType;
	}

	public void setFeederControlType(String feederControlType) {
		this.feederControlType = feederControlType;
	}

	public String getIsServiceConversion() {
		return this.isServiceConversion;
	}

	public void setIsServiceConversion(String isServiceConversion) {
		this.isServiceConversion = isServiceConversion;
	}

	public String getLoopCable() {
		return this.loopCable;
	}

	public void setLoopCable(String loopCable) {
		this.loopCable = loopCable;
	}

	public BigDecimal getNoOfSpans() {
		return this.noOfSpans;
	}

	public void setNoOfSpans(BigDecimal noOfSpans) {
		this.noOfSpans = noOfSpans;
	}

	public String getPhase() {
		return this.phase;
	}

	public void setPhase(String phase) {
		this.phase = phase;
	}

	public String getPoleno() {
		return this.poleno;
	}

	public void setPoleno(String poleno) {
		this.poleno = poleno;
	}

	public String getServiceLength() {
		return this.serviceLength;
	}

	public void setServiceLength(String serviceLength) {
		this.serviceLength = serviceLength;
	}

	public String getServiceWireDescr() {
		return this.serviceWireDescr;
	}

	public void setServiceWireDescr(String serviceWireDescr) {
		this.serviceWireDescr = serviceWireDescr;
	}

	public String getServiceWireType() {
		return this.serviceWireType;
	}

	public void setServiceWireType(String serviceWireType) {
		this.serviceWireType = serviceWireType;
	}

	public String getSin() {
		return this.sin;
	}

	public void setSin(String sin) {
		this.sin = sin;
	}

	public String getSpansDescr() {
		return this.spansDescr;
	}

	public void setSpansDescr(String spansDescr) {
		this.spansDescr = spansDescr;
	}

	public String getSubstation() {
		return this.substation;
	}

	public void setSubstation(String substation) {
		this.substation = substation;
	}

	public String getTotalLength() {
		return this.totalLength;
	}

	public void setTotalLength(String totalLength) {
		this.totalLength = totalLength;
	}

	public String getTransformerCapacity() {
		return this.transformerCapacity;
	}

	public void setTransformerCapacity(String transformerCapacity) {
		this.transformerCapacity = transformerCapacity;
	}

	public String getTransformerLoad() {
		return this.transformerLoad;
	}

	public void setTransformerLoad(String transformerLoad) {
		this.transformerLoad = transformerLoad;
	}

	public String getTransformerPeakLoad() {
		return this.transformerPeakLoad;
	}

	public void setTransformerPeakLoad(String transformerPeakLoad) {
		this.transformerPeakLoad = transformerPeakLoad;
	}

	public String getWiringType() {
		return this.wiringType;
	}

	public void setWiringType(String wiringType) {
		this.wiringType = wiringType;
	}

	@Override
	public String toString() {
		return "Spserest [id=" + id + ", bareconDescr=" + bareconDescr
				+ ", bareconLength=" + bareconLength + ", bareconType="
				+ bareconType + ", cableType=" + cableType
				+ ", conversionLength=" + conversionLength
				+ ", conversionLength2p=" + conversionLength2p
				+ ", conversion1Descr=" + conversion1Descr
				+ ", conversion1Qty=" + conversion1Qty + ", conversion2Descr="
				+ conversion2Descr + ", conversion2Qty=" + conversion2Qty
				+ ", distanceFromSs=" + distanceFromSs + ", distanceToSp="
				+ distanceToSp + ", feederControlType=" + feederControlType
				+ ", isServiceConversion=" + isServiceConversion
				+ ", loopCable=" + loopCable + ", noOfSpans=" + noOfSpans
				+ ", phase=" + phase + ", poleno=" + poleno
				+ ", serviceLength=" + serviceLength + ", serviceWireDescr="
				+ serviceWireDescr + ", serviceWireType=" + serviceWireType
				+ ", sin=" + sin + ", spansDescr=" + spansDescr
				+ ", substation=" + substation + ", totalLength=" + totalLength
				+ ", transformerCapacity=" + transformerCapacity
				+ ", transformerLoad=" + transformerLoad
				+ ", transformerPeakLoad=" + transformerPeakLoad
				+ ", wiringType=" + wiringType + "]";
	}
	
	

}