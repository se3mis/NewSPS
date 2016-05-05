package job.model;

import java.io.Serializable;
import javax.persistence.*;
import java.math.BigDecimal;
import java.util.Date;


/**
 * The persistent class for the SPJOBTCD database table.
 * 
 */
@Entity
public class Spjobtcd implements Serializable {
	private static final long serialVersionUID = 1L;

	@EmbeddedId
	private SpjobtcdPK id;

	@Column(name="CHARGED_AMOUNT")
	private BigDecimal chargedAmount;

	@Column(name="FINAL_READING1")
	private BigDecimal finalReading1;

	@Column(name="FINAL_READING2")
	private BigDecimal finalReading2;

	@Column(name="FINAL_READING3")
	private BigDecimal finalReading3;

	@Column(name="INITIAL_READING1")
	private BigDecimal initialReading1;

	@Column(name="INITIAL_READING2")
	private BigDecimal initialReading2;

	@Column(name="INITIAL_READING3")
	private BigDecimal initialReading3;

    @Temporal( TemporalType.DATE)
	@Column(name="MATERIAL_NOTICE_DATE")
	private Date materialNoticeDate;

	@Column(name="MATERIAL_NOTICE_NUMBER")
	private String materialNoticeNumber;

	@Column(name="METER_NUMBER1")
	private String meterNumber1;

	@Column(name="METER_NUMBER2")
	private String meterNumber2;

	@Column(name="METER_NUMBER3")
	private String meterNumber3;

    @Temporal( TemporalType.DATE)
	@Column(name="SERVICE_CONNECTED_DATE")
	private Date serviceConnectedDate;

	@Column(name="SERVICE_CONNECTED_LINESMAN")
	private String serviceConnectedLinesman;

    @Temporal( TemporalType.DATE)
	@Column(name="SERVICE_DISCONNECTED_DATE")
	private Date serviceDisconnectedDate;

	@Column(name="SERVICE_DISCONNECTED_LINESMAN")
	private String serviceDisconnectedLinesman;

    @Temporal( TemporalType.DATE)
	@Column(name="SUBMIT_DATE")
	private Date submitDate;

	@Column(name="UNITS_USED")
	private BigDecimal unitsUsed;

    public Spjobtcd() {
    }

	public SpjobtcdPK getId() {
		return this.id;
	}

	public void setId(SpjobtcdPK id) {
		this.id = id;
	}
	
	public BigDecimal getChargedAmount() {
		return this.chargedAmount;
	}

	public void setChargedAmount(BigDecimal chargedAmount) {
		this.chargedAmount = chargedAmount;
	}

	public BigDecimal getFinalReading1() {
		return this.finalReading1;
	}

	public void setFinalReading1(BigDecimal finalReading1) {
		this.finalReading1 = finalReading1;
	}

	public BigDecimal getFinalReading2() {
		return this.finalReading2;
	}

	public void setFinalReading2(BigDecimal finalReading2) {
		this.finalReading2 = finalReading2;
	}

	public BigDecimal getFinalReading3() {
		return this.finalReading3;
	}

	public void setFinalReading3(BigDecimal finalReading3) {
		this.finalReading3 = finalReading3;
	}

	public BigDecimal getInitialReading1() {
		return this.initialReading1;
	}

	public void setInitialReading1(BigDecimal initialReading1) {
		this.initialReading1 = initialReading1;
	}

	public BigDecimal getInitialReading2() {
		return this.initialReading2;
	}

	public void setInitialReading2(BigDecimal initialReading2) {
		this.initialReading2 = initialReading2;
	}

	public BigDecimal getInitialReading3() {
		return this.initialReading3;
	}

	public void setInitialReading3(BigDecimal initialReading3) {
		this.initialReading3 = initialReading3;
	}

	public Date getMaterialNoticeDate() {
		return this.materialNoticeDate;
	}

	public void setMaterialNoticeDate(Date materialNoticeDate) {
		this.materialNoticeDate = materialNoticeDate;
	}

	public String getMaterialNoticeNumber() {
		return this.materialNoticeNumber;
	}

	public void setMaterialNoticeNumber(String materialNoticeNumber) {
		this.materialNoticeNumber = materialNoticeNumber;
	}

	public String getMeterNumber1() {
		return this.meterNumber1;
	}

	public void setMeterNumber1(String meterNumber1) {
		this.meterNumber1 = meterNumber1;
	}

	public String getMeterNumber2() {
		return this.meterNumber2;
	}

	public void setMeterNumber2(String meterNumber2) {
		this.meterNumber2 = meterNumber2;
	}

	public String getMeterNumber3() {
		return this.meterNumber3;
	}

	public void setMeterNumber3(String meterNumber3) {
		this.meterNumber3 = meterNumber3;
	}

	public Date getServiceConnectedDate() {
		return this.serviceConnectedDate;
	}

	public void setServiceConnectedDate(Date serviceConnectedDate) {
		this.serviceConnectedDate = serviceConnectedDate;
	}

	public String getServiceConnectedLinesman() {
		return this.serviceConnectedLinesman;
	}

	public void setServiceConnectedLinesman(String serviceConnectedLinesman) {
		this.serviceConnectedLinesman = serviceConnectedLinesman;
	}

	public Date getServiceDisconnectedDate() {
		return this.serviceDisconnectedDate;
	}

	public void setServiceDisconnectedDate(Date serviceDisconnectedDate) {
		this.serviceDisconnectedDate = serviceDisconnectedDate;
	}

	public String getServiceDisconnectedLinesman() {
		return this.serviceDisconnectedLinesman;
	}

	public void setServiceDisconnectedLinesman(String serviceDisconnectedLinesman) {
		this.serviceDisconnectedLinesman = serviceDisconnectedLinesman;
	}

	public Date getSubmitDate() {
		return this.submitDate;
	}

	public void setSubmitDate(Date submitDate) {
		this.submitDate = submitDate;
	}

	public BigDecimal getUnitsUsed() {
		return this.unitsUsed;
	}

	public void setUnitsUsed(BigDecimal unitsUsed) {
		this.unitsUsed = unitsUsed;
	}

}