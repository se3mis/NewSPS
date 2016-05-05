package estimate.model;

import java.io.Serializable;
import java.math.BigDecimal;
import java.util.Date;

import javax.persistence.Column;
import javax.persistence.EmbeddedId;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name="ALLOCATION_SUMMARY")
public class AllocationSummary implements Serializable {
	
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	   
	@Id
	@Column(name="AllocationSummary_ID")
	private String allocationSummaryId;
	
	@Column(name="estimateNo")
	private String estimateNo;
	
	
	@Column(name="constructionReference")
	private String constructionReference;
	
	
	
	@Column(name="lineId")
	private String lineId;
	
	@Column(name="lineSummaryId")
	private String lineSummaryId;
	
	@Column(name="DEPT_ID")
	private String deptId;
	
	@Column(name="ALLOCATED_TO")
	private String allocatedTo;
	
	@Column(name="estimatedate")
	private Date estimateDate;
	
	@Column(name="detailCost")
	private BigDecimal detailCost;
	
	@Column(name="standardCost")
	private BigDecimal standardCost;
	//private String fundId;
	
	@Column(name="fundSource")
	private String fundSource;
	
	@Column(name="DESCRIPTION")
	private String description;
	
	@Column(name="serviceDeponame")
	private String serviceDeponame;
	
	@Column(name="totalLineLength")
	private  BigDecimal totalLineLength;
	
	@Column(name="allocatedLineLength")
	private  BigDecimal allocatedLineLength;
	
	@Column(name="allocatedEstimatedCost")
	private  BigDecimal allocatedEstimatedCost;
	
	@Column(name="totalEstimatedCost")
	private  BigDecimal totalEstimatedCost;
	
	
	

	public String getEstimateNo() {
		return estimateNo;
	}
	public void setEstimateNo(String estimateNo) {
		this.estimateNo = estimateNo;
	}
	
	

	public String getServiceDeponame() {
		return serviceDeponame;
	}



	public void setServiceDeponame(String serviceDeponame) {
		this.serviceDeponame = serviceDeponame;
	}


	
	public String getConstructionReference() {
		return constructionReference;
	}
	public void setConstructionReference(String constructionReference) {
		this.constructionReference = constructionReference;
	}
	public String getDeptId() {
		return deptId;
	}
	public void setDeptId(String deptId) {
		this.deptId = deptId;
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



	public BigDecimal getTotalEstimatedCost() {
		return totalEstimatedCost;
	}



	public void setTotalEstimatedCost(BigDecimal totalEstimatedCost) {
		this.totalEstimatedCost = totalEstimatedCost;
	}



	
	

}
