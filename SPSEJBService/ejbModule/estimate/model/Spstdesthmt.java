package estimate.model;

import java.io.Serializable;
import javax.persistence.*;

import java.math.BigDecimal;
import java.util.Date;


/**
 * The persistent class for the PCESTHTT database table.
 * 
 */
@Entity
public class Spstdesthmt implements Serializable {
	private static final long serialVersionUID = 1L;

	@EmbeddedId
	private SpstdesthmtPK id;

	@Column(name="JOBNAME")
	private String jobName;

	@Column(name="CONTINGENCY")
	private String contigency;

	@Column(name="DESCRIPTION")
	private String description;
	
	
	@Column(name="VATCOST")
	private BigDecimal vatcost;
	
	@Column(name="NBTCOST")
	private BigDecimal nbtcost;
	
	@Column(name="TOTAL_COST")
	private BigDecimal totalCost;
	
	@Column(name="SECDEPOSIT")
	private BigDecimal secDeposit;
	
	
	
	@Column(name="STATUS")
	private Long status; // number
	

	@Column(name="ENTRY_BY")
	private String entryBy;
	
	@Temporal( TemporalType.DATE)	
	@Column(name="ENTRY_DATE")
	private Date entryDate;
	
	
	@Column(name="VALIDATE_BY_EE")
	private String validateByEE;
	
	@Temporal( TemporalType.DATE)	
	@Column(name="VALIDATE_DATE_EE")
	private Date validateDateEE;
	
	@Column(name="VALIDATE_BY_CE")
	private String validateByCE;
	
	@Temporal( TemporalType.DATE)	
	@Column(name="VALIDATE_DATE_CE")
	private Date validateDateCE;
	
	@Column(name="SINNO")
	private String sinNo; // number
	@Column(name="SINNO1")
	private String sinNo1; // number
	public String getSinNo1() {
		return sinNo1;
	}



	public void setSinNo1(String sinNo1) {
		this.sinNo1 = sinNo1;
	}



	@Column(name="SINNO2")
	private String sinNo2; // number
	public String getSinNo2() {
		return sinNo2;
	}



	public void setSinNo2(String sinNo2) {
		this.sinNo2 = sinNo2;
	}



	@Column(name="SINNO3")
	private String sinNo3; // number
	public String getSinNo3() {
		return sinNo3;
	}



	public void setSinNo3(String sinNo3) {
		this.sinNo3 = sinNo3;
	}



	@Column(name="SINNO4")
	private String sinNo4; // number
	
	public String getSinNo4() {
		return sinNo4;
	}



	public void setSinNo4(String sinNo4) {
		this.sinNo4 = sinNo4;
	}



	@Column(name="APPROVED_BY")
	private String approvedBy;
	
	@Temporal( TemporalType.DATE)	
	@Column(name="APPROVED_DATE")
	private Date approvedDate;
	
	
	@Column(name="REJECTED_BY")
	private String rejectedBy;
	
	@Temporal( TemporalType.DATE)	
	@Column(name="REJECTED_DATE")
	private Date rejectedDate;
	
	
	@Column(name="REJ_REASON_EE")
	private String rejReasonEE;
	
	@Column(name="REJ_RESON_CE")
	private String rejReasonCE;
	
	@Column(name="REJ_RESON_PE")
	private String rejReasonPE;
	
	@Column(name="PLANING_BY")
	private String planningBy;
	
	@Temporal( TemporalType.DATE)	
	@Column(name="PLANING_DATE")
	private Date planningDate;
	
	@Column(name="POST_DEPTID")
	private String postDeptId;
	

	@Column(name="REBATE_COST")
	private BigDecimal rebateCost;
	
	
	@Column(name="CEBCOST")
	private BigDecimal cebCost;
	
	
	@Column(name="TOCONPAY")
	private BigDecimal toConpay;
	
	@Column(name="LINE_LENGTH")
	private BigDecimal line_length;
	
	
	public BigDecimal getLine_length() {
		return line_length;
	}



	public void setLine_length(BigDecimal line_length) {
		this.line_length = line_length;
	}



	@Column(name="CON_COST")
	private BigDecimal conCost;

	@Column(name="PROJECTTYPE")
	private String projectType;
	
	@Column(name="ASSIGNEDTO")
	private String assignTo;
	
	@Column(name="COMMENT_PE")
	private String com_pe;
	
	@Column(name="COMMENT_EE")
	private String com_ee;
	
	@Column(name="COMMENT_CE")
	private String com_ce;
	@Column(name="COMMENT_PCE")
	private String com_pce;
	@Column(name="COMMENT_DGM")
	private String com_dgm;
	
	@Column(name="COMMENT_ES")
	private String com_es;
	
	public String getCom_es() {
		return com_es;
	}



	public void setCom_es(String com_es) {
		this.com_es = com_es;
	}



	@Column(name="VAT")
	private String vat;
	
	
    public Spstdesthmt() {
    }

	

	public SpstdesthmtPK getId() {
		return id;
	}



	public void setId(SpstdesthmtPK id) {
		this.id = id;
	}
	
	public String getCom_dgm() {
		return com_dgm;
	}



	public void setCom_dgm(String com_dgm) {
		this.com_dgm = com_dgm;
	}
	
	public String getCom_pce() {
		return com_pce;
	}



	public void setCom_pce(String com_pce) {
		this.com_pce = com_pce;
	}

	public String getCom_pe() {
		return com_pe;
	}



	public void setCom_pe(String com_pe) {
		this.com_pe = com_pe;
	}
	
	public String getCom_ee() {
		return com_ee;
	}



	public void setCom_ee(String com_ee) {
		this.com_ee = com_ee;
	}
	
	public String getCom_ce() {
		return com_ce;
	}



	public void setCom_ce(String com_ce) {
		this.com_ce = com_ce;
	}
	
	

	public String getJobName() {
		return jobName;
	}



	public void setJobName(String jobName) {
		this.jobName = jobName;
	}



	public String getContigency() {
		return contigency;
	}



	public String getAssignTo() {
		return assignTo;
	}



	public void setAssignTo(String assignTo) {
		this.assignTo = assignTo;
	}



	public void setContigency(String contigency) {
		this.contigency = contigency;
	}



	public String getDescription() {
		return description;
	}



	public void setDescription(String description) {
		this.description = description;
	}



	public BigDecimal getTotalCost() {
		return totalCost;
	}



	public void setTotalCost(BigDecimal totalCost) {
		this.totalCost = totalCost;
	}



	public String getSinNo() {
		return sinNo;
	}



	public void setSinNo(String sinNo) {
		this.sinNo = sinNo;
	}




	public String getEntryBy() {
		return entryBy;
	}



	public void setEntryBy(String entryBy) {
		this.entryBy = entryBy;
	}



	public BigDecimal getSecDeposit() {
		return secDeposit;
	}



	public void setSecDeposit(BigDecimal secDeposit) {
		this.secDeposit = secDeposit;
	}



	public Long getStatus() {
		return status;
	}



	public void setStatus(Long status) {
		this.status = status;
	}



	public Date getEntryDate() {
		return entryDate;
	}



	public void setEntryDate(Date entryDate) {
		this.entryDate = entryDate;
	}


	public String getApprovedBy() {
		return approvedBy;
	}



	public void setApprovedBy(String approvedBy) {
		this.approvedBy = approvedBy;
	}



	public Date getApprovedDate() {
		return approvedDate;
	}



	public void setApprovedDate(Date approvedDate) {
		this.approvedDate = approvedDate;
	}



	public String getRejectedBy() {
		return rejectedBy;
	}



	public void setRejectedBy(String rejectedBy) {
		this.rejectedBy = rejectedBy;
	}



	public Date getRejectedDate() {
		return rejectedDate;
	}



	public void setRejectedDate(Date rejectedDate) {
		this.rejectedDate = rejectedDate;
	}



	public String getRejReasonCE() {
		return rejReasonCE;
	}



	public void setRejReasonCE(String rejReasonCE) {
		this.rejReasonCE = rejReasonCE;
	}



	public String getPlanningBy() {
		return planningBy;
	}



	public void setPlanningBy(String planningBy) {
		this.planningBy = planningBy;
	}



	public Date getPlanningDate() {
		return planningDate;
	}



	public void setPlanningDate(Date planningDate) {
		this.planningDate = planningDate;
	}



	public String getPostDeptId() {
		return postDeptId;
	}



	public void setPostDeptId(String postDeptId) {
		this.postDeptId = postDeptId;
	}



	public BigDecimal getRebateCost() {
		return rebateCost;
	}



	public void setRebateCost(BigDecimal rebateCost) {
		this.rebateCost = rebateCost;
	}



	public BigDecimal getCebCost() {
		return cebCost;
	}



	public void setCebCost(BigDecimal cebCost) {
		this.cebCost = cebCost;
	}



	public BigDecimal getToConpay() {
		return toConpay;
	}



	public void setToConpay(BigDecimal toConpay) {
		this.toConpay = toConpay;
	}



	public String getVat() {
		return vat;
	}



	public void setVat(String vat) {
		this.vat = vat;
	}



	public BigDecimal getConCost() {
		return conCost;
	}



	public void setVatcost(BigDecimal vatCost) {
		this.vatcost = vatCost;
	}
	
	public BigDecimal getVatcost() {
		return vatcost;
	}
	
	public void setNbtcost(BigDecimal nbtCost) {
		this.nbtcost = nbtCost;
	}
	
	public BigDecimal getNbtcost() {
		return nbtcost;
	}




	public void setConCost(BigDecimal conCost) {
		this.conCost = conCost;
	}



	public String getProjectType() {
		return projectType;
	}



	public void setProjectType(String projectType) {
		this.projectType = projectType;
	}



	public String getValidateByEE() {
		return validateByEE;
	}



	public void setValidateByEE(String validateByEE) {
		this.validateByEE = validateByEE;
	}



	public Date getValidateDateEE() {
		return validateDateEE;
	}



	public void setValidateDateEE(Date validateDateEE) {
		this.validateDateEE = validateDateEE;
	}



	public String getValidateByCE() {
		return validateByCE;
	}



	public void setValidateByCE(String validateByCE) {
		this.validateByCE = validateByCE;
	}



	public Date getValidateDateCE() {
		return validateDateCE;
	}



	public void setValidateDateCE(Date validateDateCE) {
		this.validateDateCE = validateDateCE;
	}



	public String getRejReasonEE() {
		return rejReasonEE;
	}



	public void setRejReasonEE(String rejReasonEE) {
		this.rejReasonEE = rejReasonEE;
	}



	public String getRejReasonPE() {
		return rejReasonPE;
	}



	public void setRejReasonPE(String rejReasonPE) {
		this.rejReasonPE = rejReasonPE;
	}



	@Override
	public String toString() {
		StringBuffer buf = new StringBuffer();
		buf.append("SpstaymtPK [id=").append( id );
		buf.append(", jobName=").append( jobName);
		buf.append(", contigency=").append( contigency);
		buf.append(", description=").append( description);
		buf.append(", vatCost=").append( vatcost);
		buf.append(", nbtCost=").append( nbtcost);
		buf.append(", totalCost=").append( totalCost);
		buf.append(", secDeposit=").append( secDeposit);
		//buf.append(", deptId=").append( deptId);
		buf.append(", status=").append( status);
		buf.append(", entryDate=").append( entryDate);
		buf.append(", validateDate=").append( validateDateEE);
		buf.append(", approvedBy=").append( approvedBy);
		buf.append(", approvedDate=").append( approvedDate);
		buf.append(", rejectedBy=").append( rejectedBy);
		buf.append(", rejectedDate=").append( rejectedDate);
		buf.append(", rejReasonCE=").append( rejReasonCE);
		buf.append(", rejReason=").append( rejReasonEE);
		buf.append(", planningBy=").append( planningBy);
		buf.append(", planningDate=").append( planningDate);
		buf.append(", postDeptId=").append( postDeptId);
		buf.append(", rebateCost=").append( rebateCost);
		buf.append(", cebCost=").append( cebCost);
		buf.append(", conCost=").append( conCost);
		buf.append(", projectType=").append( projectType).append("]");
		buf.append(", line_length=").append( line_length).append("]");
		
		return buf.toString();
	}
	
	

}