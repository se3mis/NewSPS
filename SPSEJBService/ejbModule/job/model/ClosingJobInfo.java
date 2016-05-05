package job.model;

import java.io.Serializable;
import java.math.BigDecimal;

@SuppressWarnings("serial")
public class ClosingJobInfo implements Serializable {
	
	@Override
	public String toString() {
		return "ClosingJobInfo [projectNo=" + projectNo + ", estimateNo="
				+ estimateNo + ", deptId=" + deptId + ", fundSource="
				+ fundSource + ", catCd=" + catCd + ", description="
				+ description + ", stdCost=" + stdCost + ", commitedCost="
				+ commitedCost + ", status=" + status + "]";
	}
	private String projectNo ;
	private String estimateNo;
	private String deptId;
	private String fundSource;
	private String catCd;
	private String  description;
	private BigDecimal stdCost ;
	private BigDecimal commitedCost;
	private BigDecimal  status;
	
	public ClosingJobInfo() {
		super();
	}
	
	
	public ClosingJobInfo(String projectNo, String estimateNo, String deptId,
			String fundSource, String catCd, String description,
			BigDecimal stdCost, BigDecimal status) {
		super();
		this.projectNo = projectNo;
		this.estimateNo = estimateNo;
		this.deptId = deptId;
		this.fundSource = fundSource;
		this.catCd = catCd;
		this.description = description;
		this.stdCost = stdCost;
		this.status = status;
	}


	
	public BigDecimal getCommitedCost() {
		return commitedCost;
	}
	public void setCommitedCost(BigDecimal commitedCost) {
		this.commitedCost = commitedCost;
	}
	public String getProjectNo() {
		return projectNo;
	}
	public void setProjectNo(String projectNo) {
		this.projectNo = projectNo;
	}
	public String getDescription() {
		return description;
	}
	public void setDescription(String description) {
		this.description = description;
	}
	public BigDecimal getStdCost() {
		return stdCost;
	}
	public void setStdCost(BigDecimal stdCost) {
		this.stdCost = stdCost;
	}
	public String getFundSource() {
		return fundSource;
	}
	public void setFundSource(String fundSource) {
		this.fundSource = fundSource;
	}
	public String getCatCd() {
		return catCd;
	}
	public void setCatCd(String catCd) {
		this.catCd = catCd;
	}
	public BigDecimal getStatus() {
		return status;
	}
	public void setStatus(BigDecimal status) {
		this.status = status;
	}
	public String getDeptId() {
		return deptId;
	}
	public void setDeptId(String deptId) {
		this.deptId = deptId;
	}
	public String getEstimateNo() {
		return estimateNo;
	}
	public void setEstimateNo(String estimateNo) {
		this.estimateNo = estimateNo;
	}
	


}
