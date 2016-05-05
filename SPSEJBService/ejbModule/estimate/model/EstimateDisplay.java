package estimate.model;

import java.io.Serializable;
import java.math.BigDecimal;
import java.util.Date;

@SuppressWarnings("serial")
public class EstimateDisplay implements Serializable {
	private String estimateNo;
	private String costCenter;
	private String applicationType;
	private String applicationSubType;
	private String enterBy;
	private Date estimateDate;
	private BigDecimal detailCost;
	private BigDecimal standardCost;
	//private String fundId;
	private String fundSource;
	private String description;
	private String rejectedBy;
	private Date rejectedDate;
	private String rejectedReason;
	private String proposer;
	private String serviceDeponame;
	private String schemeName;
	private String schemeNo;
	private String electorate;
	private String representative1;
	private String representativeContact1;
	private String pivNo;
	
	
	public EstimateDisplay() {
		super();
	}
	
	
	
	public EstimateDisplay(String estimateNo, String costCenter,
			String applicationType, String applicationSubType, String enterBy,
			Date estimateDate, BigDecimal detailCost, BigDecimal standardCost,
			String fundSource, String description,
			String rejectedBy, Date rejectedDate, String rejectedReason, String proposer, String serviceDeponame, String schemeName,String schemeNo
			,String electorate,String representative1,String representativeContact1) {
		super();
		this.estimateNo = estimateNo;
		this.costCenter = costCenter;
		this.applicationType = applicationType;
		this.applicationSubType = applicationSubType;
		this.enterBy = enterBy;
		this.estimateDate = estimateDate;
		this.detailCost = detailCost;
		this.standardCost = standardCost;
		//this.fundId = fundId;
		this.fundSource = fundSource;
		this.description = description;
		this.rejectedBy = rejectedBy;
		this.rejectedDate = rejectedDate;
		this.rejectedReason = rejectedReason;
		this.serviceDeponame = serviceDeponame;
		this.proposer = proposer;
		this.schemeName = schemeName;
		this.schemeNo = schemeNo;
		this.electorate = electorate;
		this.representativeContact1 = representativeContact1;
		this.representative1 = representative1;
		
		
	}



	public EstimateDisplay(String estimateNo, String costCenter,
			String applicationType, String applicationSubType,
			Date estimateDate, BigDecimal detailCost, BigDecimal standardCost,
			String fundId, String fundSource, String description,
			String rejectedBy, Date rejectedDate, String rejectedReason, String proposer, String schemeName,String schemeNo	,String electorate,String representative1,String representativeContact1) {
		super();
		this.estimateNo = estimateNo;
		this.costCenter = costCenter;
		this.applicationType = applicationType;
		this.applicationSubType = applicationSubType;
		this.estimateDate = estimateDate;
		this.detailCost = detailCost;
		this.standardCost = standardCost;
		//this.fundId = fundId;
		this.fundSource = fundSource;
		this.description = description;
		this.rejectedBy = rejectedBy;
		this.rejectedDate = rejectedDate;
		this.rejectedReason = rejectedReason;
		this.serviceDeponame = serviceDeponame;
		this.proposer = proposer;
		this.schemeName = schemeName;
		this.schemeNo = schemeNo;
		this.electorate = electorate;
		this.representativeContact1 = representativeContact1;
		this.representative1 = representative1;
		
	}
	
	public EstimateDisplay(String estimateNo, String costCenter,
			String applicationType, String applicationSubType, String enterBy,
			Date estimateDate, BigDecimal detailCost, BigDecimal standardCost,
			String fundSource, String description,
			String rejectedBy, Date rejectedDate, String rejectedReason, String proposer, String serviceDeponame, String schemeName,String schemeNo
			,String electorate,String representative1,String representativeContact1,String pivNo) {
		super();
		this.estimateNo = estimateNo;
		this.costCenter = costCenter;
		this.applicationType = applicationType;
		this.applicationSubType = applicationSubType;
		this.enterBy = enterBy;
		this.estimateDate = estimateDate;
		this.detailCost = detailCost;
		this.standardCost = standardCost;
		//this.fundId = fundId;
		this.fundSource = fundSource;
		this.description = description;
		this.rejectedBy = rejectedBy;
		this.rejectedDate = rejectedDate;
		this.rejectedReason = rejectedReason;
		this.serviceDeponame = serviceDeponame;
		this.proposer = proposer;
		this.schemeName = schemeName;
		this.schemeNo = schemeNo;
		this.electorate = electorate;
		this.representativeContact1 = representativeContact1;
		this.representative1 = representative1;
		this.pivNo = pivNo;
		
	}

	
	

	public String getEstimateNo() {
		return estimateNo;
	}
	public void setEstimateNo(String estimateNo) {
		this.estimateNo = estimateNo;
	}
	
	public String getProposer() {
		return proposer;
	}



	public String getSchemeNo() {
		return schemeNo;
	}



	public void setSchemeNo(String schemeNo) {
		this.schemeNo = schemeNo;
	}



	public void setProposer(String proposer) {
		this.proposer = proposer;
	}



	public String getSchemeName() {
		return schemeName;
	}



	public void setSchemeName(String schemeName) {
		this.schemeName = schemeName;
	}



	public String getServiceDeponame() {
		return serviceDeponame;
	}



	public void setServiceDeponame(String serviceDeponame) {
		this.serviceDeponame = serviceDeponame;
	}



	public String getCostCenter() {
		return costCenter;
	}
	public void setCostCenter(String costCenter) {
		this.costCenter = costCenter;
	}
	public String getApplicationType() {
		return applicationType;
	}
	public void setApplicationType(String applicationType) {
		this.applicationType = applicationType;
	}
	public String getApplicationSubType() {
		return applicationSubType;
	}
	public void setApplicationSubType(String applicationSubType) {
		this.applicationSubType = applicationSubType;
	}
	public Date getEstimateDate() {
		return estimateDate;
	}
	public void setEstimateDate(Date estimateDate) {
		this.estimateDate = estimateDate;
	}
	public BigDecimal getDetailCost() {
		return detailCost;
	}
	public void setDetailCost(BigDecimal detailCost) {
		this.detailCost = detailCost;
	}
	public String getDescription() {
		return description;
	}
	public void setDescription(String description) {
		this.description = description;
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
	public String getRejectedReason() {
		return rejectedReason;
	}
	public void setRejectedReason(String rejectedReason) {
		this.rejectedReason = rejectedReason;
	}
	public BigDecimal getStandardCost() {
		return standardCost;
	}
	public void setStandardCost(BigDecimal standardCost) {
		this.standardCost = standardCost;
	}
	
	
	public String getFundSource() {
		return fundSource;
	}

	public void setFundSource(String fundSource) {
		this.fundSource = fundSource;
	}
	
	public String getEnterBy() {
		return enterBy;
	}

	public void setEnterBy(String enterBy) {
		this.enterBy = enterBy;
	}

	public String getElectorate() {
		return electorate;
	}



	public void setElectorate(String electorate) {
		this.electorate = electorate;
	}



	public String getRepresentative1() {
		return representative1;
	}



	public void setRepresentative1(String representative1) {
		this.representative1 = representative1;
	}



	public String getRepresentativeContact1() {
		return representativeContact1;
	}



	public void setRepresentativeContact1(String representativeContact1) {
		this.representativeContact1 = representativeContact1;
	}

	public String getPivNo() {
		return pivNo;
	}



	public void setPivNo(String pivNo) {
		this.pivNo = pivNo;
	}


	@Override
	public String toString() {
		return "EstimateDisplay [estimateNo=" + estimateNo + ", costCenter="
				+ costCenter + ", applicationType=" + applicationType
				+ ", applicationSubType=" + applicationSubType + ", enterBy="
				+ enterBy + ", estimateDate=" + estimateDate + ", detailCost="
				+ detailCost + ", standardCost=" + standardCost + ",  fundSource=" + fundSource + ", description="
				+ description + ", rejectedBy=" + rejectedBy
				+ ", rejectedDate=" + rejectedDate + ", rejectedReason="
				+ rejectedReason + " , pivNo="
				+ pivNo + " ]";
	}
	

	
	
	
	

}
