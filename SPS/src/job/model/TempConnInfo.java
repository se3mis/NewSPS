package job.model;

import java.io.Serializable;
import java.math.BigDecimal;
import java.util.Date;

@SuppressWarnings("serial")
public class TempConnInfo implements Serializable{
	//PCESTHMT
	private String projectNo;
	private String estimateNo;
	private Date prjAssDt;
	//WiringAndLand
	private String serviceStreetAddress;
	private String serviceSuburb;
	private String serviceCity;
	private BigDecimal servicePostalCode;
	private BigDecimal connectionType;
	private BigDecimal phase;
	//APPLICANT
	private String idNo;
	private String firstName;
	private String lastName;
	//private BigDecimal telephoneNo;
	
	//Applications
	private Date submitDate;
	private Date fromDate;
	private BigDecimal durationInDays;
	
	//SPESTSTD
	private BigDecimal totalCost;
	private BigDecimal securityDeposit;
	private BigDecimal addlDeposit;
	private BigDecimal damageCost;
	//PIV_DETAILS
	private String pivNo;
	private Date pivDate;
	private BigDecimal pivAmount;
	//SPESTCND
	private String contractorId;
	
	
	


	public TempConnInfo() {
		super();
	}
	
	public TempConnInfo(String projectNo, String estimateNo, Date prjAssDt,
			String serviceStreetAddress, String serviceSuburb,
			String serviceCity, BigDecimal servicePostalCode,
			BigDecimal connectionType, BigDecimal phase, String idNo,
			String firstName, String lastName, Date submitDate, Date fromDate,
			BigDecimal durationInDays, BigDecimal totalCost,
			BigDecimal securityDeposit, BigDecimal addlDeposit,
			BigDecimal damageCost, String pivNo, Date pivDate,
			BigDecimal pivAmount, String contractorId) {
		super();
		this.projectNo = projectNo;
		this.estimateNo = estimateNo;
		this.prjAssDt = prjAssDt;
		this.serviceStreetAddress = serviceStreetAddress;
		this.serviceSuburb = serviceSuburb;
		this.serviceCity = serviceCity;
		this.servicePostalCode = servicePostalCode;
		this.connectionType = connectionType;
		this.phase = phase;
		this.idNo = idNo;
		this.firstName = firstName;
		this.lastName = lastName;
		this.submitDate = submitDate;
		this.fromDate = fromDate;
		this.durationInDays = durationInDays;
		this.totalCost = totalCost;
		this.securityDeposit = securityDeposit;
		this.addlDeposit = addlDeposit;
		this.damageCost = damageCost;
		this.pivNo = pivNo;
		this.pivDate = pivDate;
		this.pivAmount = pivAmount;
		this.contractorId = contractorId;
	}

	

	public String getServiceStreetAddress() {
		return serviceStreetAddress;
	}
	public void setServiceStreetAddress(String serviceStreetAddress) {
		this.serviceStreetAddress = serviceStreetAddress;
	}
	public String getServiceSuburb() {
		return serviceSuburb;
	}
	public void setServiceSuburb(String serviceSuburb) {
		this.serviceSuburb = serviceSuburb;
	}
	public String getServiceCity() {
		return serviceCity;
	}
	public void setServiceCity(String serviceCity) {
		this.serviceCity = serviceCity;
	}
	public BigDecimal getServicePostalCode() {
		return servicePostalCode;
	}
	public void setServicePostalCode(BigDecimal servicePostalCode) {
		this.servicePostalCode = servicePostalCode;
	}
	public Date getSubmitDate() {
		return submitDate;
	}
	public void setSubmitDate(Date submitDate) {
		this.submitDate = submitDate;
	}
	public Date getFromDate() {
		return fromDate;
	}
	public void setFromDate(Date fromDate) {
		this.fromDate = fromDate;
	}
	public BigDecimal getDurationInDays() {
		return durationInDays;
	}
	public void setDurationInDays(BigDecimal durationInDays) {
		this.durationInDays = durationInDays;
	}
	public Date getPrjAssDt() {
		return prjAssDt;
	}
	public void setPrjAssDt(Date prjAssDt) {
		this.prjAssDt = prjAssDt;
	}
	public BigDecimal getTotalCost() {
		return totalCost;
	}
	public void setTotalCost(BigDecimal totalCost) {
		this.totalCost = totalCost;
	}
	public BigDecimal getSecurityDeposit() {
		return securityDeposit;
	}
	public void setSecurityDeposit(BigDecimal securityDeposit) {
		this.securityDeposit = securityDeposit;
	}
	public BigDecimal getAddlDeposit() {
		return addlDeposit;
	}
	public void setAddlDeposit(BigDecimal addlDeposit) {
		this.addlDeposit = addlDeposit;
	}
	public String getPivNo() {
		return pivNo;
	}
	public void setPivNo(String pivNo) {
		this.pivNo = pivNo;
	}
	public Date getPivDate() {
		return pivDate;
	}
	public void setPivDate(Date pivDate) {
		this.pivDate = pivDate;
	}
	public BigDecimal getPivAmount() {
		return pivAmount;
	}
	public void setPivAmount(BigDecimal pivAmount) {
		this.pivAmount = pivAmount;
	}
	public String getProjectNo() {
		return projectNo;
	}
	public void setProjectNo(String projectNo) {
		this.projectNo = projectNo;
	}
	
	public String getEstimateNo() {
		return estimateNo;
	}


	public void setEstimateNo(String estimateNo) {
		this.estimateNo = estimateNo;
	}
	
	public BigDecimal getConnectionType() {
		return connectionType;
	}

	public void setConnectionType(BigDecimal connectionType) {
		this.connectionType = connectionType;
	}

	public BigDecimal getPhase() {
		return phase;
	}

	public void setPhase(BigDecimal phase) {
		this.phase = phase;
	}
	

	public String getIdNo() {
		return idNo;
	}

	public void setIdNo(String idNo) {
		this.idNo = idNo;
	}

	public String getFirstName() {
		return firstName;
	}

	public void setFirstName(String firstName) {
		this.firstName = firstName;
	}

	public String getLastName() {
		return lastName;
	}

	public void setLastName(String lastName) {
		this.lastName = lastName;
	}
	
	public String getContractorId() {
		return contractorId;
	}

	public void setContractorId(String contractorId) {
		this.contractorId = contractorId;
	}
	
	public BigDecimal getDamageCost() {
		return damageCost;
	}

	public void setDamageCost(BigDecimal damageCost) {
		this.damageCost = damageCost;
	}


	//public BigDecimal getMobileNo() {
	//	return mobileNo;
	//}

	//public void setMobileNo(BigDecimal mobileNo) {
	//	this.mobileNo = mobileNo;
	//}

	//public BigDecimal getTelephoneNo() {
	//	return telephoneNo;
	//}

	//public void setTelephoneNo(BigDecimal telephoneNo) {
	//	this.telephoneNo = telephoneNo;
	//}

	@Override
	public String toString() {
		return "TempConnInfo [projectNo=" + projectNo + ", estimateNo="
				+ estimateNo + ", prjAssDt=" + prjAssDt
				+ ", serviceStreetAddress=" + serviceStreetAddress
				+ ", serviceSuburb=" + serviceSuburb + ", serviceCity="
				+ serviceCity + ", servicePostalCode=" + servicePostalCode
				+ ", connectionType=" + connectionType + ", phase=" + phase
				+ ", idNo=" + idNo + ", firstName=" + firstName + ", lastName="
				+ lastName + ", submitDate=" + submitDate + ", fromDate="
				+ fromDate + ", durationInDays=" + durationInDays
				+ ", totalCost=" + totalCost + ", securityDeposit="
				+ securityDeposit + ", addlDeposit=" + addlDeposit
				+ ", damageCost=" + damageCost + ", pivNo=" + pivNo
				+ ", pivDate=" + pivDate + ", pivAmount=" + pivAmount
				+ ", contractorId=" + contractorId + "]";
	}
	
	


}