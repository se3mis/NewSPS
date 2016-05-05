package job.model;

import java.io.Serializable;
import javax.persistence.*;
import java.math.BigDecimal;
import java.util.Date;


/**
 * The persistent class for the SPESTCNT database table.
 * 
 */
@Entity
public class Spestcnt implements Serializable {
	private static final long serialVersionUID = 1L;

	@EmbeddedId
	private SpestcntPK id;

	@Column(name="BOND_AMOUNT")
	private BigDecimal bondAmount;

	@Column(name="BOND_NO")
	private String bondNo;

	private String code;

	@Column(name="CONTRACTOR_ADDRESS")
	private String contractorAddress;

	@Column(name="CONTRACTOR_NAME")
	private String contractorName;

    @Temporal( TemporalType.DATE)
	@Column(name="END_DATE")
	private Date endDate;

	@Column(name="JOB_IN_HAND")
	private BigDecimal jobInHand;

	private String nbt;

	@Column(name="PERFORMANCE_AMOUNT")
	private BigDecimal performanceAmount;

	@Column(name="SP_ADD_SPAN")
	private BigDecimal spAddSpan;

    @Temporal( TemporalType.DATE)
	@Column(name="START_DATE")
	private Date startDate;

	private String status;

	@Column(name="TENDER_AMOUNT")
	private BigDecimal tenderAmount;

	@Column(name="TOTAL_AMOUNT")
	private BigDecimal totalAmount;

	@Column(name="TP_ADD_SPAN")
	private BigDecimal tpAddSpan;

	private String vat;

    public Spestcnt() {
    }

	public SpestcntPK getId() {
		return this.id;
	}

	public void setId(SpestcntPK id) {
		this.id = id;
	}
	
	public BigDecimal getBondAmount() {
		return this.bondAmount;
	}

	public void setBondAmount(BigDecimal bondAmount) {
		this.bondAmount = bondAmount;
	}

	public String getBondNo() {
		return this.bondNo;
	}

	public void setBondNo(String bondNo) {
		this.bondNo = bondNo;
	}

	public String getCode() {
		return this.code;
	}

	public void setCode(String code) {
		this.code = code;
	}

	public String getContractorAddress() {
		return this.contractorAddress;
	}

	public void setContractorAddress(String contractorAddress) {
		this.contractorAddress = contractorAddress;
	}

	public String getContractorName() {
		return this.contractorName;
	}

	public void setContractorName(String contractorName) {
		this.contractorName = contractorName;
	}

	public Date getEndDate() {
		return this.endDate;
	}

	public void setEndDate(Date endDate) {
		this.endDate = endDate;
	}

	public BigDecimal getJobInHand() {
		return this.jobInHand;
	}

	public void setJobInHand(BigDecimal jobInHand) {
		this.jobInHand = jobInHand;
	}

	public String getNbt() {
		return this.nbt;
	}

	public void setNbt(String nbt) {
		this.nbt = nbt;
	}

	public BigDecimal getPerformanceAmount() {
		return this.performanceAmount;
	}

	public void setPerformanceAmount(BigDecimal performanceAmount) {
		this.performanceAmount = performanceAmount;
	}

	public BigDecimal getSpAddSpan() {
		return this.spAddSpan;
	}

	public void setSpAddSpan(BigDecimal spAddSpan) {
		this.spAddSpan = spAddSpan;
	}

	public Date getStartDate() {
		return this.startDate;
	}

	public void setStartDate(Date startDate) {
		this.startDate = startDate;
	}

	public String getStatus() {
		return this.status;
	}

	public void setStatus(String status) {
		this.status = status;
	}

	public BigDecimal getTenderAmount() {
		return this.tenderAmount;
	}

	public void setTenderAmount(BigDecimal tenderAmount) {
		this.tenderAmount = tenderAmount;
	}

	public BigDecimal getTotalAmount() {
		return this.totalAmount;
	}

	public void setTotalAmount(BigDecimal totalAmount) {
		this.totalAmount = totalAmount;
	}

	public BigDecimal getTpAddSpan() {
		return this.tpAddSpan;
	}

	public void setTpAddSpan(BigDecimal tpAddSpan) {
		this.tpAddSpan = tpAddSpan;
	}

	public String getVat() {
		return this.vat;
	}

	public void setVat(String vat) {
		this.vat = vat;
	}

}