package estimate.model;

import java.io.Serializable;
import java.math.BigDecimal;
import java.util.Date;

@SuppressWarnings("serial")
public class AllocationSummaryDisplay implements Serializable {
	private String allocationSummaryId;
	private String estimateNo;
	private String constructionRef;
	private String lineId;
	private String lineSummaryId;
	private String costCenter;
	
	private String allocatedTo;
	private Date estimateDate;
	private BigDecimal detailCost;
	private BigDecimal standardCost;
	//private String fundId;
	private String fundSource;
	private String description;
	
	private String serviceDeponame;
	private  BigDecimal totalLineLength;
	private  BigDecimal allocatedLineLength;
	private  BigDecimal remainingLineLength;
	private  BigDecimal allocatedEstimatedCost;
	private  BigDecimal totalEstimatedCost;
	
	
	public AllocationSummaryDisplay() {
		super();
	}
	
	
	
	public AllocationSummaryDisplay(String allocationSummaryId,
			String estimateNo, String lineId, String lineSummaryId, String costCenter, String allocatedTo,
			Date estimateDate, BigDecimal detailCost, BigDecimal standardCost,
			String fundSource, String description,String serviceDeponame,
			BigDecimal totalLineLength, BigDecimal allocatedLineLength, BigDecimal allocatedEstimatedCost, BigDecimal totalEstimatedCost
			) {
		super();
		this.estimateNo = estimateNo;
		this.costCenter = costCenter;
		this.allocationSummaryId = allocationSummaryId;
		this.lineId = lineId;
		this.lineSummaryId=lineSummaryId;
		this.allocatedTo = allocatedTo;
		this.estimateDate = estimateDate;
		this.detailCost = detailCost;
		this.standardCost = standardCost;
		//this.fundId = fundId;
		this.fundSource = fundSource;
		this.description = description;
		this.serviceDeponame = serviceDeponame;
		this.totalLineLength = totalLineLength;
	
		this.allocatedLineLength = allocatedLineLength;
		this.allocatedEstimatedCost = allocatedEstimatedCost;
		this.totalEstimatedCost = totalEstimatedCost;
		
	}



	public AllocationSummaryDisplay(String allocationSummaryId,
			String estimateNo, String lineId, String lineSummaryId, String costCenter,
			Date estimateDate,
			String fundSource, String description,String serviceDeponame,
			BigDecimal totalLineLength, BigDecimal totalEstimatedCost
			) {
		super();
		this.estimateNo = estimateNo;
		this.costCenter = costCenter;
		this.allocationSummaryId = allocationSummaryId;
		this.lineId = lineId;
		this.lineSummaryId=lineSummaryId;
		
		this.estimateDate = estimateDate;
		
		
		this.description = description;
		
		this.totalLineLength = totalLineLength;
	
		this.allocatedLineLength = allocatedLineLength;
		this.allocatedEstimatedCost = allocatedEstimatedCost;
		this.totalEstimatedCost = totalEstimatedCost;
		
	}
	
	public AllocationSummaryDisplay(
			String estimateNo, String lineId, String lineSummaryId, String costCenter,
			Date estimateDate,
			String fundSource, String description,String serviceDeponame,
			BigDecimal totalLineLength, BigDecimal totalEstimatedCost
			) {
		super();
		this.estimateNo = estimateNo;
		this.costCenter = costCenter;
		
		this.lineId = lineId;
		this.lineSummaryId=lineSummaryId;
		
		this.estimateDate = estimateDate;
		
		
		this.description = description;
		
		this.totalLineLength = totalLineLength;
	
	
		this.totalEstimatedCost = totalEstimatedCost;
		
	}
	public AllocationSummaryDisplay(String allocationSummaryId,
			String estimateNo, String lineId, String lineSummaryId, String costCenter, String allocatedTo,
			Date estimateDate,
			String fundSource, String description,String serviceDeponame,
			BigDecimal totalLineLength, BigDecimal allocatedLineLength, BigDecimal allocatedEstimatedCost, BigDecimal totalEstimatedCost
			) {
		super();
		this.estimateNo = estimateNo;
		this.costCenter = costCenter;
		this.allocationSummaryId = allocationSummaryId;
		this.lineId = lineId;
		this.lineSummaryId=lineSummaryId;
		this.allocatedTo = allocatedTo;
		this.estimateDate = estimateDate;
		
		
		this.description = description;
		
		this.totalLineLength = totalLineLength;
	
		this.allocatedLineLength = allocatedLineLength;
		this.allocatedEstimatedCost = allocatedEstimatedCost;
		this.totalEstimatedCost = totalEstimatedCost;
		
	}
	public String getEstimateNo() {
		return estimateNo;
	}
	public void setEstimateNo(String estimateNo) {
		this.estimateNo = estimateNo;
	}
	
	

	public String getServiceDeponame() {
		return serviceDeponame;
	}



	


	public String getConstructionRef() {
		return constructionRef;
	}



	public void setConstructionRef(String constructionRef) {
		this.constructionRef = constructionRef;
	}



	public void setServiceDeponame(String serviceDeponame) {
		this.serviceDeponame = serviceDeponame;
	}



	public String getAllocationSummaryId() {
		return allocationSummaryId;
	}



	public void setAllocationSummaryId(String allocationSummaryId) {
		this.allocationSummaryId = allocationSummaryId;
	}



	public String getLineId() {
		return lineId;
	}



	public void setLineId(String lineId) {
		this.lineId = lineId;
	}



	public String getLineSummaryId() {
		return lineSummaryId;
	}



	public void setLineSummaryId(String lineSummaryId) {
		this.lineSummaryId = lineSummaryId;
	}



	public String getAllocatedTo() {
		return allocatedTo;
	}



	public void setAllocatedTo(String allocatedTo) {
		this.allocatedTo = allocatedTo;
	}



	public BigDecimal getTotalLineLength() {
		return totalLineLength;
	}



	public void setTotalLineLength(BigDecimal totalLineLength) {
		this.totalLineLength = totalLineLength;
	}



	public BigDecimal getAllocatedLineLength() {
		return allocatedLineLength;
	}



	public void setAllocatedLineLength(BigDecimal allocatedLineLength) {
		this.allocatedLineLength = allocatedLineLength;
	}



	public BigDecimal getAllocatedEstimatedCost() {
		return allocatedEstimatedCost;
	}



	public void setAllocatedEstimatedCost(BigDecimal allocatedEstimatedCost) {
		this.allocatedEstimatedCost = allocatedEstimatedCost;
	}



	public BigDecimal getRemainingLineLength() {
		return remainingLineLength;
	}



	public void setRemainingLineLength(BigDecimal remainingLineLength) {
		this.remainingLineLength = remainingLineLength;
	}



	public BigDecimal getTotalEstimatedCost() {
		return totalEstimatedCost;
	}



	public void setTotalEstimatedCost(BigDecimal totalEstimatedCost) {
		this.totalEstimatedCost = totalEstimatedCost;
	}



	public String getCostCenter() {
		return costCenter;
	}
	public void setCostCenter(String costCenter) {
		this.costCenter = costCenter;
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
	
	


	@Override
	public String toString() {
		return "EstimateDisplay [estimateNo=" + estimateNo + ", costCenter="
				+ costCenter + ", allocationSummaryId=" + allocationSummaryId
				+ ", lineId=" + lineId + ", enterBy="
				+ allocatedTo + ", estimateDate=" + estimateDate + ", detailCost="
				+ detailCost + ", standardCost=" + standardCost + ",  fundSource=" + fundSource + ", description="
				+ description + ", lineSummaryId=" + lineSummaryId
				+ ", totalLineLength=" + totalLineLength + ", allocatedLineLength="
				+ allocatedLineLength + "]";
	}
	
	
	
	

}
