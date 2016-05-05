package estimate.model;

import java.io.Serializable;
import javax.persistence.*;
import java.math.BigDecimal;
import java.util.Date;


/**
 * The persistent class for the SPESTSTD database table.
 * 
 */
@Entity
public class Speststd implements Serializable {
	private static final long serialVersionUID = 1L;

	@EmbeddedId
	private SpeststdPK id;

	@Column(name="ADDL_DEPOSIT")
	private BigDecimal addlDeposit;

	@Column(name="BOARD_CHARGE")
	private BigDecimal boardCharge;

	@Column(name="BOARD_CHARGE_PRESENT")
	private BigDecimal boardChargePresent;

	@Column(name="CABLE_TYPE")
	private String cableType;

	@Column(name="CAPITAL_COST")
	private BigDecimal capitalCost;

	@Column(name="CONDUCTOR_LENGTH")
	private BigDecimal conductorLength;

	@Column(name="CONDUCTOR_TYPE")
	private String conductorType;

	@Column(name="CONNECTOR_TYPE")
	private String connectorType;

	@Column(name="CONTINGENCY_COST")
	private BigDecimal contingencyCost;

	@Column(name="CONTINGENCY_PRESENT")
	private BigDecimal contingencyPresent;

	@Column(name="CONVERSION_COST")
	private BigDecimal conversionCost;

	@Column(name="CONVERSION_LENGTH")
	private BigDecimal conversionLength;

	@Column(name="DAMAGE_COST")
	private BigDecimal damageCost;

	@Column(name="FIXED_COST")
	private BigDecimal fixedCost;

	@Column(name="IS_LOOP_SERVICE")
	private String isLoopService;

	@Column(name="LABOUR_COST")
	private BigDecimal labourCost;

	private BigDecimal lb;

	@Column(name="LINE_LENGTH")
	private BigDecimal lineLength;

	@Column(name="LOOP_CABLE")
	private String loopCable;

	@Column(name="LOOP_LENGTH")
	private BigDecimal loopLength;

	@Column(name="OTHER_COST")
	private BigDecimal otherCost;

	@Column(name="OTHER_LABOUR_COST")
	private BigDecimal otherLabourCost;

	@Column(name="OTHER_MAT_COST")
	private BigDecimal otherMatCost;

	@Column(name="OVERHEAD_COST")
	private BigDecimal overheadCost;

	@Column(name="POLE_NO")
	private BigDecimal poleNo;

	@Column(name="POLE_SIZE")
	private String poleSize;

	@Column(name="POLE_TYPE")
	private String poleType;

	@Column(name="SECURITY_DEPOSIT")
	private BigDecimal securityDeposit;

	@Column(name="SERVICE_DISTANCE")
	private BigDecimal serviceDistance;

	@Column(name="SERVICE_LENGTH")
	private BigDecimal serviceLength;

	@Column(name="SIN_NO")
	private String sinNo;

	private BigDecimal span;

	@Column(name="STAY_NO")
	private BigDecimal stayNo;

	@Column(name="STRUT_NO")
	private BigDecimal strutNo;

	@Column(name="TEMPORARY_DEPOSIT")
	private BigDecimal temporaryDeposit;

	@Column(name="TOTAL_COST")
	private BigDecimal totalCost;

	@Column(name="TRANSFORM_COLOR")
	private String transformColor;

	@Column(name="TRANSPORT_COST")
	private BigDecimal transportCost;

    @Temporal( TemporalType.DATE)
	@Column(name="UPD_DATE")
	private Date updDate;

	@Column(name="UPD_TIME")
	private String updTime;

	@Column(name="UPD_USER")
	private String updUser;

	@Column(name="VARIABLE_COST")
	private BigDecimal variableCost;

	@Column(name="WIRING_TYPE")
	private String wiringType;

    public Speststd() {
    }

	public SpeststdPK getId() {
		return this.id;
	}

	public void setId(SpeststdPK id) {
		this.id = id;
	}
	
	public BigDecimal getAddlDeposit() {
		return this.addlDeposit;
	}

	public void setAddlDeposit(BigDecimal addlDeposit) {
		this.addlDeposit = addlDeposit;
	}

	public BigDecimal getBoardCharge() {
		return this.boardCharge;
	}

	public void setBoardCharge(BigDecimal boardCharge) {
		this.boardCharge = boardCharge;
	}

	public BigDecimal getBoardChargePresent() {
		return this.boardChargePresent;
	}

	public void setBoardChargePresent(BigDecimal boardChargePresent) {
		this.boardChargePresent = boardChargePresent;
	}

	public String getCableType() {
		return this.cableType;
	}

	public void setCableType(String cableType) {
		this.cableType = cableType;
	}

	public BigDecimal getCapitalCost() {
		return this.capitalCost;
	}

	public void setCapitalCost(BigDecimal capitalCost) {
		this.capitalCost = capitalCost;
	}

	public BigDecimal getConductorLength() {
		return this.conductorLength;
	}

	public void setConductorLength(BigDecimal conductorLength) {
		this.conductorLength = conductorLength;
	}

	public String getConductorType() {
		return this.conductorType;
	}

	public void setConductorType(String conductorType) {
		this.conductorType = conductorType;
	}

	public String getConnectorType() {
		return this.connectorType;
	}

	public void setConnectorType(String connectorType) {
		this.connectorType = connectorType;
	}

	public BigDecimal getContingencyCost() {
		return this.contingencyCost;
	}

	public void setContingencyCost(BigDecimal contingencyCost) {
		this.contingencyCost = contingencyCost;
	}

	public BigDecimal getContingencyPresent() {
		return this.contingencyPresent;
	}

	public void setContingencyPresent(BigDecimal contingencyPresent) {
		this.contingencyPresent = contingencyPresent;
	}

	public BigDecimal getConversionCost() {
		return this.conversionCost;
	}

	public void setConversionCost(BigDecimal conversionCost) {
		this.conversionCost = conversionCost;
	}

	public BigDecimal getConversionLength() {
		return this.conversionLength;
	}

	public void setConversionLength(BigDecimal conversionLength) {
		this.conversionLength = conversionLength;
	}

	public BigDecimal getDamageCost() {
		return this.damageCost;
	}

	public void setDamageCost(BigDecimal damageCost) {
		this.damageCost = damageCost;
	}

	public BigDecimal getFixedCost() {
		return this.fixedCost;
	}

	public void setFixedCost(BigDecimal fixedCost) {
		this.fixedCost = fixedCost;
	}

	public String getIsLoopService() {
		return this.isLoopService;
	}

	public void setIsLoopService(String isLoopService) {
		this.isLoopService = isLoopService;
	}

	public BigDecimal getLabourCost() {
		return this.labourCost;
	}

	public void setLabourCost(BigDecimal labourCost) {
		this.labourCost = labourCost;
	}

	public BigDecimal getLb() {
		return this.lb;
	}

	public void setLb(BigDecimal lb) {
		this.lb = lb;
	}

	public BigDecimal getLineLength() {
		return this.lineLength;
	}

	public void setLineLength(BigDecimal lineLength) {
		this.lineLength = lineLength;
	}

	public String getLoopCable() {
		return this.loopCable;
	}

	public void setLoopCable(String loopCable) {
		this.loopCable = loopCable;
	}

	public BigDecimal getLoopLength() {
		return this.loopLength;
	}

	public void setLoopLength(BigDecimal loopLength) {
		this.loopLength = loopLength;
	}

	public BigDecimal getOtherCost() {
		return this.otherCost;
	}

	public void setOtherCost(BigDecimal otherCost) {
		this.otherCost = otherCost;
	}

	public BigDecimal getOtherLabourCost() {
		return this.otherLabourCost;
	}

	public void setOtherLabourCost(BigDecimal otherLabourCost) {
		this.otherLabourCost = otherLabourCost;
	}

	public BigDecimal getOtherMatCost() {
		return this.otherMatCost;
	}

	public void setOtherMatCost(BigDecimal otherMatCost) {
		this.otherMatCost = otherMatCost;
	}

	public BigDecimal getOverheadCost() {
		return this.overheadCost;
	}

	public void setOverheadCost(BigDecimal overheadCost) {
		this.overheadCost = overheadCost;
	}

	public BigDecimal getPoleNo() {
		return this.poleNo;
	}

	public void setPoleNo(BigDecimal poleNo) {
		this.poleNo = poleNo;
	}

	public String getPoleSize() {
		return this.poleSize;
	}

	public void setPoleSize(String poleSize) {
		this.poleSize = poleSize;
	}

	public String getPoleType() {
		return this.poleType;
	}

	public void setPoleType(String poleType) {
		this.poleType = poleType;
	}

	public BigDecimal getSecurityDeposit() {
		return this.securityDeposit;
	}

	public void setSecurityDeposit(BigDecimal securityDeposit) {
		this.securityDeposit = securityDeposit;
	}

	public BigDecimal getServiceDistance() {
		return this.serviceDistance;
	}

	public void setServiceDistance(BigDecimal serviceDistance) {
		this.serviceDistance = serviceDistance;
	}

	public BigDecimal getServiceLength() {
		return this.serviceLength;
	}

	public void setServiceLength(BigDecimal serviceLength) {
		this.serviceLength = serviceLength;
	}

	public String getSinNo() {
		return this.sinNo;
	}

	public void setSinNo(String sinNo) {
		this.sinNo = sinNo;
	}

	public BigDecimal getSpan() {
		return this.span;
	}

	public void setSpan(BigDecimal span) {
		this.span = span;
	}

	public BigDecimal getStayNo() {
		return this.stayNo;
	}

	public void setStayNo(BigDecimal stayNo) {
		this.stayNo = stayNo;
	}

	public BigDecimal getStrutNo() {
		return this.strutNo;
	}

	public void setStrutNo(BigDecimal strutNo) {
		this.strutNo = strutNo;
	}

	public BigDecimal getTemporaryDeposit() {
		return this.temporaryDeposit;
	}

	public void setTemporaryDeposit(BigDecimal temporaryDeposit) {
		this.temporaryDeposit = temporaryDeposit;
	}

	public BigDecimal getTotalCost() {
		return this.totalCost;
	}

	public void setTotalCost(BigDecimal totalCost) {
		this.totalCost = totalCost;
	}

	public String getTransformColor() {
		return this.transformColor;
	}

	public void setTransformColor(String transformColor) {
		this.transformColor = transformColor;
	}

	public BigDecimal getTransportCost() {
		return this.transportCost;
	}

	public void setTransportCost(BigDecimal transportCost) {
		this.transportCost = transportCost;
	}

	public Date getUpdDate() {
		return this.updDate;
	}

	public void setUpdDate(Date updDate) {
		this.updDate = updDate;
	}

	public String getUpdTime() {
		return this.updTime;
	}

	public void setUpdTime(String updTime) {
		this.updTime = updTime;
	}

	public String getUpdUser() {
		return this.updUser;
	}

	public void setUpdUser(String updUser) {
		this.updUser = updUser;
	}

	public BigDecimal getVariableCost() {
		return this.variableCost;
	}

	public void setVariableCost(BigDecimal variableCost) {
		this.variableCost = variableCost;
	}

	public String getWiringType() {
		return this.wiringType;
	}

	public void setWiringType(String wiringType) {
		this.wiringType = wiringType;
	}

	@Override
	public String toString() {
		return "Speststd [id=" + id + ", addlDeposit=" + addlDeposit
				+ ", boardCharge=" + boardCharge + ", boardChargePresent="
				+ boardChargePresent + ", cableType=" + cableType
				+ ", capitalCost=" + capitalCost + ", conductorLength="
				+ conductorLength + ", conductorType=" + conductorType
				+ ", connectorType=" + connectorType + ", contingencyCost="
				+ contingencyCost + ", contingencyPresent="
				+ contingencyPresent + ", conversionCost=" + conversionCost
				+ ", conversionLength=" + conversionLength + ", damageCost="
				+ damageCost + ", fixedCost=" + fixedCost + ", isLoopService="
				+ isLoopService + ", labourCost=" + labourCost + ", lb=" + lb
				+ ", lineLength=" + lineLength + ", loopCable=" + loopCable
				+ ", loopLength=" + loopLength + ", otherCost=" + otherCost
				+ ", otherLabourCost=" + otherLabourCost + ", otherMatCost="
				+ otherMatCost + ", overheadCost=" + overheadCost + ", poleNo="
				+ poleNo + ", poleSize=" + poleSize + ", poleType=" + poleType
				+ ", securityDeposit=" + securityDeposit + ", serviceDistance="
				+ serviceDistance + ", serviceLength=" + serviceLength
				+ ", sinNo=" + sinNo + ", span=" + span + ", stayNo=" + stayNo
				+ ", strutNo=" + strutNo + ", temporaryDeposit="
				+ temporaryDeposit + ", totalCost=" + totalCost
				+ ", transformColor=" + transformColor + ", transportCost="
				+ transportCost + ", updDate=" + updDate + ", updTime="
				+ updTime + ", updUser=" + updUser + ", variableCost="
				+ variableCost + ", wiringType=" + wiringType + "]";
	}
	
	

}