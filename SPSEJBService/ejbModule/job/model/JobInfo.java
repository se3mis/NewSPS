package job.model;

import java.io.Serializable;
import java.math.BigDecimal;
import java.util.Date;
import java.util.Map;

import javax.persistence.Column;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;

@SuppressWarnings("serial")
public class JobInfo implements Serializable {
	private String estimateNo;
	
	private String projectNo;
	
	private String commercialRef;
	
	private String fullName;
	
	private BigDecimal constructionCost;
	
	private String fundId;
	
	private Date estimateDate;
	
	private String decrp;
	

	private String devSec;
	
	private BigDecimal stdCost;
	
	
	

	private String areaCode;
	
	private String electorate;
	
	
	private String district;
	
	private String csc;
	
	private String conBy;
	
	private String estBy;
	
	private String supBy;
	
	
	private String estNo;
	


	private String jobName ;
	
	
	
	public BigDecimal getConstructionCost() {
		return constructionCost;
	}


	public void setConstructionCost(BigDecimal constructionCost) {
		this.constructionCost = constructionCost;
	}


	public void setStdCost(BigDecimal stdCost) {
		this.stdCost = stdCost;
	}


	public JobInfo() {
		super();
	}

	
	@SuppressWarnings("rawtypes")
	public JobInfo(Map map) {
		super();
		
		this.estimateNo = map.values().toArray()[0].toString();
		this.projectNo = map.values().toArray()[1].toString();
		this.commercialRef = map.values().toArray()[2].toString();
		
		this.fullName = map.values().toArray()[3].toString();
		this.constructionCost = new BigDecimal(map.values().toArray()[4].toString());
		this.fundId = map.values().toArray()[5].toString();
		
		this.estimateDate = new Date(map.values().toArray()[6].toString());
		this.decrp = map.values().toArray()[7].toString();
		this.devSec = map.values().toArray()[8].toString();
		this.stdCost = new BigDecimal(map.values().toArray()[9].toString());
		this.areaCode = map.values().toArray()[10].toString();
		this.electorate = map.values().toArray()[11].toString();
		this.district = map.values().toArray()[12].toString();
		this.csc = map.values().toArray()[13].toString();
		this.conBy = map.values().toArray()[14].toString();
		this.estBy = map.values().toArray()[15].toString();
		this.supBy = map.values().toArray()[16].toString();
		this.estNo = map.values().toArray()[17].toString();
		
		this.jobName = map.values().toArray()[18].toString();
		
		
	}
	
	public String getEstimateNo() {
		return estimateNo;
	}
	public void setEstimateNo(String estimateNo) {
		this.estimateNo = estimateNo;
	}
	public String getCommercialRef() {
		return commercialRef;
	}
	public void setCommercialRef(String commercialRef) {
		this.commercialRef = commercialRef;
	}
	public String getFundId() {
		return fundId;
	}
	public void setFundId(String fundId) {
		this.fundId = fundId;
	}
	public String getDecrp() {
		return decrp;
	}
	public void setDecrp(String decrp) {
		this.decrp = decrp;
	}
	public String getDevSec() {
		return devSec;
	}
	
	public Date getEstimateDate() {
		return estimateDate;
	}
	public void setEstimateDate(Date estimateDate) {
		this.estimateDate = estimateDate;
	}
	public void setDevSec(String devSec) {
		this.devSec = devSec;
	}
	
	
	public BigDecimal getStdCost() {
		return stdCost;
	}


	public String getAreaCode() {
		return areaCode;
	}
	public void setAreaCode(String areaCode) {
		this.areaCode = areaCode;
	}
	public String getElectorate() {
		return electorate;
	}
	public void setElectorate(String electorate) {
		this.electorate = electorate;
	}
	public String getDistrict() {
		return district;
	}
	public void setDistrict(String district) {
		this.district = district;
	}
	public String getCsc() {
		return csc;
	}
	public void setCsc(String csc) {
		this.csc = csc;
	}
	public String getConBy() {
		return conBy;
	}
	public void setConBy(String conBy) {
		this.conBy = conBy;
	}
	public String getEstBy() {
		return estBy;
	}
	public void setEstBy(String estBy) {
		this.estBy = estBy;
	}
	public String getSupBy() {
		return supBy;
	}
	public void setSupBy(String supBy) {
		this.supBy = supBy;
	}
	public String getEstNo() {
		return estNo;
	}
	public void setEstNo(String estNo) {
		this.estNo = estNo;
	}
	
	public String getJobName() {
		return jobName;
	}
	public void setJobName(String jobName) {
		this.jobName = jobName;
	}
	public String getProjectNo() {
		return projectNo;
	}
	public void setProjectNo(String projectNo) {
		this.projectNo = projectNo;
	}
	public String getFullName() {
		return fullName;
	}
	public void setFullName(String fullName) {
		this.fullName = fullName;
	}
	
	@Override
	public String toString() {
		return "JobInfo [projectNo=" + projectNo + ", fullName=" + fullName
				+ ", Amount=" + constructionCost + "]";
	}
	
	

}
