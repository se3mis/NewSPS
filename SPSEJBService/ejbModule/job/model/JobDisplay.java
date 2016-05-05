package job.model;



import java.io.Serializable;
import java.math.BigDecimal;
import java.util.Date;

@SuppressWarnings("serial")
public class JobDisplay implements Serializable {
	private String estimateNo;
	private String costCenter;
	private String applicationType;
	private String applicationSubType;
	private Date estimateDate;
	private BigDecimal detailCost;
	private BigDecimal standardCost;
	private String fundId;
	private String fundSource;
	private String description;
	private String rejectedBy;
	private Date rejectedDate;
	private String rejectedReason;

	
	public JobDisplay() {
		super();
	}
	
	public JobDisplay(String estimateNo, String costCenter,
			String applicationType, String applicationSubType,
			Date estimateDate, BigDecimal detailCost, BigDecimal standardCost,
			String fundId, String fundSource, String description,
			String rejectedBy, Date rejectedDate, String rejectedReason) {
		super();
		this.estimateNo = estimateNo;
		this.costCenter = costCenter;
		this.applicationType = applicationType;
		this.applicationSubType = applicationSubType;
		this.estimateDate = estimateDate;
		this.detailCost = detailCost;
		this.standardCost = standardCost;
		this.fundId = fundId;
		this.fundSource = fundSource;
		this.description = description;
		this.rejectedBy = rejectedBy;
		this.rejectedDate = rejectedDate;
		this.rejectedReason = rejectedReason;
	}

	public String getEstimateNo() {
		return estimateNo;
	}
	public void setEstimateNo(String estimateNo) {
		this.estimateNo = estimateNo;
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
	
	public String getFundId() {
		return fundId;
	}

	public void setFundId(String fundId) {
		this.fundId = fundId;
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
				+ costCenter + ", applicationType=" + applicationType
				+ ", applicationSubType=" + applicationSubType
				+ ", estimateDate=" + estimateDate + ", detailCost="
				+ detailCost + ", standardCost=" + standardCost + ", fundId="
				+ fundId + ", fundSource=" + fundSource + ", description="
				+ description + ", rejectedBy=" + rejectedBy
				+ ", rejectedDate=" + rejectedDate + ", rejectedReason="
				+ rejectedReason + "]";
	}
	
	
	
	

}
