package estimate.model;

import java.io.Serializable;
import java.math.BigDecimal;
import java.util.Date;

@SuppressWarnings("serial")
public class ApplicationDisplay implements Serializable {
	private String estimateNo;
	private String costCenter;
	private String applicationType;
	private String applicationSubType;
	private String area;
	private String proposer;
	private String schemeName;
	private String schemeNo;
	
	
	public ApplicationDisplay() {
		super();
	}
	
	
	
	public ApplicationDisplay(String estimateNo, String costCenter,
			String applicationType, String applicationSubType,String area,String proposer, String schemeName,String schemeNo) {
		super();
		this.estimateNo = estimateNo;
		this.costCenter = costCenter;
		this.applicationType = applicationType;
		this.applicationSubType = applicationSubType;
		this.area = area;
		this.proposer = proposer;
		this.schemeName = schemeName;
		this.schemeNo = schemeNo;
		
	}



	/*public ApplicationDisplay(String estimateNo, String costCenter,
			String applicationType, String applicationSubType,
			String allocatedTo,String allocatedBy, String proposer, String schemeName,String schemeNo) {
		super();
		this.estimateNo = estimateNo;
		this.costCenter = costCenter;
		this.applicationType = applicationType;
		this.applicationSubType = applicationSubType;
		this.allocatedTo = allocatedTo;
		this.allocatedBy = allocatedBy;
		this.proposer = proposer;
		this.schemeName = schemeName;
		this.schemeNo = schemeNo;
	}*/

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
	
	@Override
	public String toString() {
		return "EstimateDisplay [estimateNo=" + estimateNo + ", costCenter="
				+ costCenter + ", applicationType=" + applicationType
				+ ", applicationSubType=" + applicationSubType +"]";
	}
	
	
	
	

}
