package offDoc.web;

import java.math.BigDecimal;
import java.util.Date;

public class PayingInVoucher {
private String bankName;
private String bankBranchName ;
private String CEBBranch;
private String costCenterNo;
private String date;
private String referenceNo;
private String jobDescription;
private String depositorName;
private String depositorIDNumber;
private String streetAddress;
private String suburb;
private String city;
private String postalCode;
private String bankCode;
private String branchCode;
private String chequeNo;
private String chequeDate;  
private String amountInWords;
private String preparedBy;
private String cetifiedBy;
private BigDecimal miscellaneousIncome ;
private BigDecimal eletricityDebtors ;
private BigDecimal securityDeposit;
private BigDecimal serviceConnectionOrElectricitySchemes;
private BigDecimal tenderDeposits;
private BigDecimal miscellaneousDeposits;
private BigDecimal cashInTransit;
private BigDecimal forDishonouredCheques;
private BigDecimal subTotal;
private BigDecimal vat;
private BigDecimal nbt;
private BigDecimal grandTotal;
private boolean ifBankLoanIsGranted;
private String loanReference;
private BigDecimal loanAmount;
private String PIVNumber;
private String amountInFigures;



public String getAmountInFigures() {
	return amountInFigures;
}
public void setAmountInFigures(String amountInFigures) {
	this.amountInFigures = amountInFigures;
}
public String getPIVNumber() {
	return PIVNumber;
}
public void setPIVNumber(String pIVNumber) {
	PIVNumber = pIVNumber;
}
 
 
public String getChequeDate() {
	return chequeDate;
}
public void setChequeDate(String chequeDate) {
	this.chequeDate = chequeDate;
}
public String getBankName() {
	return bankName;
}
public void setBankName(String bankName) {
	this.bankName = bankName;
}
 
public String getBankBranchName() {
	return bankBranchName;
}
public void setBankBranchName(String bankBranchName) {
	this.bankBranchName = bankBranchName;
}
public String getCEBBranch() {
	return CEBBranch;
}
public void setCEBBranch(String cEBBranch) {
	CEBBranch = cEBBranch;
}
public String getCostCenterNo() {
	return costCenterNo;
}
public void setCostCenterNo(String costCenterNo) {
	this.costCenterNo = costCenterNo;
}
 
public String getDate() {
	return date;
}
public void setDate(String date) {
	this.date = date;
}
public String getReferenceNo() {
	return referenceNo;
}
public void setReferenceNo(String referenceNo) {
	this.referenceNo = referenceNo;
}
public String getJobDescription() {
	return jobDescription;
}
public void setJobDescription(String jobDescription) {
	this.jobDescription = jobDescription;
}
public String getDepositorName() {
	return depositorName;
}
public void setDepositorName(String depositorName) {
	this.depositorName = depositorName;
}
public String getDepositorIDNumber() {
	return depositorIDNumber;
}
public void setDepositorIDNumber(String depositorIDNumber) {
	this.depositorIDNumber = depositorIDNumber;
}
public String getStreetAddress() {
	return streetAddress;
}
public void setStreetAddress(String streetAddress) {
	this.streetAddress = streetAddress;
}
public String getSuburb() {
	return suburb;
}
public void setSuburb(String suburb) {
	this.suburb = suburb;
}
public String getCity() {
	return city;
}
public void setCity(String city) {
	this.city = city;
}
public String getPostalCode() {
	return postalCode;
}
public void setPostalCode(String postalCode) {
	this.postalCode = postalCode;
}
public String getBankCode() {
	return bankCode;
}
public void setBankCode(String bankCode) {
	this.bankCode = bankCode;
}
public String getBranchCode() {
	return branchCode;
}
public void setBranchCode(String branchCode) {
	this.branchCode = branchCode;
}
public String getChequeNo() {
	return chequeNo;
}
public void setChequeNo(String chequeNo) {
	this.chequeNo = chequeNo;
}
public String getAmountInWords() {
	return amountInWords;
}
public void setAmountInWords(String amountInWords) {
	this.amountInWords = amountInWords;
}
public String getPreparedBy() {
	return preparedBy;
}
public void setPreparedBy(String preparedBy) {
	this.preparedBy = preparedBy;
}
public String getCetifiedBy() {
	return cetifiedBy;
}
public void setCetifiedBy(String cetifiedBy) {
	this.cetifiedBy = cetifiedBy;
}
public BigDecimal getMiscellaneousIncome() {
	return miscellaneousIncome;
}
public void setMiscellaneousIncome(BigDecimal miscellaneousIncome) {
	this.miscellaneousIncome = miscellaneousIncome;
}
public BigDecimal getEletricityDebtors() {
	return eletricityDebtors;
}
public void setEletricityDebtors(BigDecimal eletricityDebtors) {
	this.eletricityDebtors = eletricityDebtors;
}
public BigDecimal getSecurityDeposit() {
	return securityDeposit;
}
public void setSecurityDeposit(BigDecimal securityDeposit) {
	this.securityDeposit = securityDeposit;
}
public BigDecimal getServiceConnectionOrElectricitySchemes() {
	return serviceConnectionOrElectricitySchemes;
}
public void setServiceConnectionOrElectricitySchemes(
		BigDecimal serviceConnectionOrElectricitySchemes) {
	this.serviceConnectionOrElectricitySchemes = serviceConnectionOrElectricitySchemes;
}
public BigDecimal getTenderDeposits() {
	return tenderDeposits;
}
public void setTenderDeposits(BigDecimal tenderDeposits) {
	this.tenderDeposits = tenderDeposits;
}
public BigDecimal getMiscellaneousDeposits() {
	return miscellaneousDeposits;
}
public void setMiscellaneousDeposits(BigDecimal miscellaneousDeposits) {
	this.miscellaneousDeposits = miscellaneousDeposits;
}
public BigDecimal getCashInTransit() {
	return cashInTransit;
}
public void setCashInTransit(BigDecimal cashInTransit) {
	this.cashInTransit = cashInTransit;
}
public BigDecimal getForDishonouredCheques() {
	return forDishonouredCheques;
}
public void setForDishonouredCheques(BigDecimal forDishonouredCheques) {
	this.forDishonouredCheques = forDishonouredCheques;
}
public BigDecimal getSubTotal() {
	return subTotal;
}
public void setSubTotal(BigDecimal subTotal) {
	this.subTotal = subTotal;
}
public BigDecimal getVat() {
	return vat;
}
public void setVat(BigDecimal vat) {
	this.vat = vat;
}

public BigDecimal getNbt() {
	return nbt;
}
public void setNbt(BigDecimal nbt) {
	this.nbt = nbt;
}
 
public BigDecimal getGrandTotal() {
	return grandTotal;
}
public void setGrandTotal(BigDecimal grandTotal) {
	this.grandTotal = grandTotal;
}
public boolean isIfBankLoanIsGranted() {
	return ifBankLoanIsGranted;
}
public void setIfBankLoanIsGranted(boolean ifBankLoanIsGranted) {
	this.ifBankLoanIsGranted = ifBankLoanIsGranted;
}
public String getLoanReference() {
	return loanReference;
}
public void setLoanReference(String loanReference) {
	this.loanReference = loanReference;
}
public BigDecimal getLoanAmount() {
	return loanAmount;
}
public void setLoanAmount(BigDecimal loanAmount) {
	this.loanAmount = loanAmount;
}
 

}
