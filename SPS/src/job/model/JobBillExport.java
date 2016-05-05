package job.model;

import java.io.Serializable;
import java.math.BigDecimal;

@SuppressWarnings("serial")
public class JobBillExport  implements Serializable{
	String applicationType;
	String tableSerial;
	String piv2;
	String areaCode;
	String depo;
	String sinNo;
	String jobYear;
	String serial;
	String lastName;
	String firstName;
	String address1;
	String address2;
	String address3;
	String payMode;
	String payDate;
	String tariffCode;
	String kva;
	String connectionType;
	String phase;
	String customerType;
	String securityDeposit;
	String piv2Amount;
	String packNo;
	String readerCode;
	String walkSequence;
	String noOfMeters;
	String dateConnected;
	String meterNo1;
	String meterType1;
	String noOfDigit1;
	String reading1;
	String meterNo2;
	String meterType2;
	String noOfDigit2;
	String reading2;
	String meterNo3;
	String meterType3;
	String noOfDigit3;
	String reading3;
	String updateFlag;
	String avgConsumption;
	String loanTotal1;
	String loanAmount1;
	String loanDate1;
	String loanCode1;
	String loanInstalment1;
	String noOfInstalment1;
	String interestRate1;
	String interestAmount1;
	String loanTotal2;
	String loanAmount2;
	String loanDate2;
	String loanCode2;
	String loanInstalment2;
	String noOfInstalment2;
	String interestRate2;
	String interestAmount2; 
	String oldAccount;
	String dateExported;
	String nicNo;
	String customerCode;
	String billPay;
	String telephone1;
	String telephone2;
	String email;
	
	public JobBillExport()
	{
		applicationType = " ";//1
		tableSerial = "0";//1
		piv2 = "        ";//8
		areaCode = "  ";//2
		depo = " ";//1
		sinNo = "   ";//3
		jobYear = "  ";//2
		serial = "     ";//5
		lastName = "               ";//15
		firstName = "                ";//15
		address1 = "                                ";//30
		address2 = "                         ";//25
		address3 = "                    ";//20
		payMode = " ";//1
		payDate = "        ";//8
		tariffCode = "  ";//2
		kva = "   ";//3
		connectionType = "   ";//3
		phase = " ";//1
		customerType = "  ";//2
		securityDeposit = "        ";//8
		piv2Amount = "        ";//8
		packNo = "  ";//2
		readerCode = "  ";//2
		walkSequence = "     ";//5
		noOfMeters = " ";//1
		dateConnected = "        ";//8
		meterNo1 = "        ";//8
		meterType1 = " ";//1
		noOfDigit1 = "  ";//2
		reading1 = "        ";//8
		meterNo2 = "        ";//8
		meterType2 = " ";//1
		noOfDigit2 = "  ";//2
		reading2 = "        ";//8
		meterNo3 = "        ";//8
		meterType3 = " ";//1
		noOfDigit3 = "  ";//2
		reading3 = "        ";//8
		updateFlag = "W";//1
		avgConsumption = "     ";//5
		loanTotal1 = "        ";//8
		loanAmount1 = "        ";//8
		loanDate1 = "        ";//8
		loanCode1 = " ";//1
		loanInstalment1 = "       ";//7
		noOfInstalment1 = "  ";//2
		interestRate1 = "     ";//5
		interestAmount1 = "       ";//7
		loanTotal2 = "        ";//8
		loanAmount2 = "        ";//8
		loanDate2 = "        ";//8
		loanCode2 = " ";//1
		loanInstalment2 = "       ";//7
		noOfInstalment2 = "  ";//2
		interestRate2 = "     ";//5
		interestAmount2 = "       "; //7
		oldAccount = "          ";//10
		dateExported = "          ";//10
		nicNo = "          ";//10
		customerCode = "    ";//4
		billPay = " ";//1
		telephone1 = "          ";//10
		telephone2 = "          ";//10
		email = "                                ";//30
	}
	
	public String getApplicationType() {
		return applicationType;
	}
	public void setApplicationType(String applicationType) {
		this.applicationType = applicationType;
	}
	public String getTableSerial() {
		return tableSerial;
	}
	public void setTableSerial(String tableSerial) {
		this.tableSerial = tableSerial;
	}
	public String getPiv2() {
		return piv2;
	}
	public void setPiv2(String piv2) {
		this.piv2 = piv2;
	}
	public String getAreaCode() {
		return areaCode;
	}
	public void setAreaCode(String areaCode) {
		this.areaCode = areaCode;
	}
	public String getDepo() {
		return depo;
	}
	public void setDepo(String depo) {
		this.depo = depo;
	}
	public String getSinNo() {
		return sinNo;
	}
	public void setSinNo(String sinNo) {
		this.sinNo = sinNo;
	}
	public String getJobYear() {
		return jobYear;
	}
	public void setJobYear(String jobYear) {
		this.jobYear = jobYear;
	}
	public String getSerial() {
		return serial;
	}
	public void setSerial(String serial) {
		this.serial = serial;
	}
	public String getLastName() {
		return lastName;
	}
	public void setLastName(String lastName) {
		this.lastName = lastName;
	}
	public String getFirstName() {
		return firstName;
	}
	public void setFirstName(String firstName) {
		this.firstName = firstName;
	}
	public String getAddress1() {
		return address1;
	}
	public void setAddress1(String address1) {
		this.address1 = address1;
	}
	public String getAddress2() {
		return address2;
	}
	public void setAddress2(String address2) {
		this.address2 = address2;
	}
	public String getAddress3() {
		return address3;
	}
	public void setAddress3(String address3) {
		this.address3 = address3;
	}
	public String getPayMode() {
		return payMode;
	}
	public void setPayMode(String payMode) {
		this.payMode = payMode;
	}
	public String getPayDate() {
		return payDate;
	}
	public void setPayDate(String payDate) {
		this.payDate = payDate;
	}
	public String getTariffCode() {
		return tariffCode;
	}
	public void setTariffCode(String tariffCode) {
		this.tariffCode = tariffCode;
	}
	public String getKva() {
		return kva;
	}
	public void setKva(String kva) {
		this.kva = kva;
	}
	public String getConnectionType() {
		return connectionType;
	}
	public void setConnectionType(String connectionType) {
		this.connectionType = connectionType;
	}
	public String getPhase() {
		return phase;
	}
	public void setPhase(String phase) {
		this.phase = phase;
	}
	public String getCustomerType() {
		return customerType;
	}
	public void setCustomerType(String customerType) {
		this.customerType = customerType;
	}
	public String getSecurityDeposit() {
		return securityDeposit;
	}
	public void setSecurityDeposit(String securityDeposit) {
		this.securityDeposit = securityDeposit;
	}
	public String getPiv2Amount() {
		return piv2Amount;
	}
	public void setPiv2Amount(String piv2Amount) {
		this.piv2Amount = piv2Amount;
	}
	public String getPackNo() {
		return packNo;
	}
	public void setPackNo(String packNo) {
		this.packNo = packNo;
	}
	public String getReaderCode() {
		return readerCode;
	}
	public void setReaderCode(String readerCode) {
		this.readerCode = readerCode;
	}
	public String getWalkSequence() {
		return walkSequence;
	}
	public void setWalkSequence(String walkSequence) {
		this.walkSequence = walkSequence;
	}
	public String getNoOfMeters() {
		return noOfMeters;
	}
	public void setNoOfMeters(String noOfMeters) {
		this.noOfMeters = noOfMeters;
	}
	public String getDateConnected() {
		return dateConnected;
	}
	public void setDateConnected(String dateConnected) {
		this.dateConnected = dateConnected;
	}
	public String getMeterNo1() {
		return meterNo1;
	}
	public void setMeterNo1(String meterNo1) {
		this.meterNo1 = meterNo1;
	}
	public String getMeterType1() {
		return meterType1;
	}
	public void setMeterType1(String meterType1) {
		this.meterType1 = meterType1;
	}
	public String getNoOfDigit1() {
		return noOfDigit1;
	}
	public void setNoOfDigit1(String noOfDigit1) {
		this.noOfDigit1 = noOfDigit1;
	}
	public String getReading1() {
		return reading1;
	}
	public void setReading1(String reading1) {
		this.reading1 = reading1;
	}
	public String getMeterNo2() {
		return meterNo2;
	}
	public void setMeterNo2(String meterNo2) {
		this.meterNo2 = meterNo2;
	}
	public String getMeterType2() {
		return meterType2;
	}
	public void setMeterType2(String meterType2) {
		this.meterType2 = meterType2;
	}
	public String getNoOfDigit2() {
		return noOfDigit2;
	}
	public void setNoOfDigit2(String noOfDigit2) {
		this.noOfDigit2 = noOfDigit2;
	}
	public String getReading2() {
		return reading2;
	}
	public void setReading2(String reading2) {
		this.reading2 = reading2;
	}
	public String getMeterNo3() {
		return meterNo3;
	}
	public void setMeterNo3(String meterNo3) {
		this.meterNo3 = meterNo3;
	}
	public String getMeterType3() {
		return meterType3;
	}
	public void setMeterType3(String meterType3) {
		this.meterType3 = meterType3;
	}
	public String getNoOfDigit3() {
		return noOfDigit3;
	}
	public void setNoOfDigit3(String noOfDigit3) {
		this.noOfDigit3 = noOfDigit3;
	}
	public String getReading3() {
		return reading3;
	}
	public void setReading3(String reading3) {
		this.reading3 = reading3;
	}
	public String getUpdateFlag() {
		return updateFlag;
	}
	public void setUpdateFlag(String updateFlag) {
		this.updateFlag = updateFlag;
	}
	public String getAvgConsumption() {
		return avgConsumption;
	}
	public void setAvgConsumption(String avgConsumption) {
		this.avgConsumption = avgConsumption;
	}
	public String getLoanTotal1() {
		return loanTotal1;
	}
	public void setLoanTotal1(String loanTotal1) {
		this.loanTotal1 = loanTotal1;
	}
	public String getLoanAmount1() {
		return loanAmount1;
	}
	public void setLoanAmount1(String loanAmount1) {
		this.loanAmount1 = loanAmount1;
	}
	public String getLoanDate1() {
		return loanDate1;
	}
	public void setLoanDate1(String loanDate1) {
		this.loanDate1 = loanDate1;
	}
	public String getLoanCode1() {
		return loanCode1;
	}
	public void setLoanCode1(String loanCode1) {
		this.loanCode1 = loanCode1;
	}
	public String getLoanInstalment1() {
		return loanInstalment1;
	}
	public void setLoanInstalment1(String loanInstalment1) {
		this.loanInstalment1 = loanInstalment1;
	}
	public String getNoOfInstalment1() {
		return noOfInstalment1;
	}
	public void setNoOfInstalment1(String noOfInstalment1) {
		this.noOfInstalment1 = noOfInstalment1;
	}
	public String getInterestRate1() {
		return interestRate1;
	}
	public void setInterestRate1(String interestRate1) {
		this.interestRate1 = interestRate1;
	}
	public String getInterestAmount1() {
		return interestAmount1;
	}
	public void setInterestAmount1(String interestAmount1) {
		this.interestAmount1 = interestAmount1;
	}
	public String getLoanTotal2() {
		return loanTotal2;
	}
	public void setLoanTotal2(String loanTotal2) {
		this.loanTotal2 = loanTotal2;
	}
	public String getLoanAmount2() {
		return loanAmount2;
	}
	public void setLoanAmount2(String loanAmount2) {
		this.loanAmount2 = loanAmount2;
	}
	public String getLoanDate2() {
		return loanDate2;
	}
	public void setLoanDate2(String loanDate2) {
		this.loanDate2 = loanDate2;
	}
	public String getLoanCode2() {
		return loanCode2;
	}
	public void setLoanCode2(String loanCode2) {
		this.loanCode2 = loanCode2;
	}
	public String getLoanInstalment2() {
		return loanInstalment2;
	}
	public void setLoanInstalment2(String loanInstalment2) {
		this.loanInstalment2 = loanInstalment2;
	}
	public String getNoOfInstalment2() {
		return noOfInstalment2;
	}
	public void setNoOfInstalment2(String noOfInstalment2) {
		this.noOfInstalment2 = noOfInstalment2;
	}
	public String getInterestRate2() {
		return interestRate2;
	}
	public void setInterestRate2(String interestRate2) {
		this.interestRate2 = interestRate2;
	}
	public String getInterestAmount2() {
		return interestAmount2;
	}
	public void setInterestAmount2(String interestAmount2) {
		this.interestAmount2 = interestAmount2;
	}
	public String getOldAccount() {
		return oldAccount;
	}
	public void setOldAccount(String oldAccount) {
		this.oldAccount = oldAccount;
	}
	public String getDateExported() {
		return dateExported;
	}
	public void setDateExported(String dateExported) {
		this.dateExported = dateExported;
	}
	public String getNicNo() {
		return nicNo;
	}
	public void setNicNo(String nicNo) {
		this.nicNo = nicNo;
	}
	public String getCustomerCode() {
		return customerCode;
	}
	public void setCustomerCode(String customerCode) {
		this.customerCode = customerCode;
	}
	public String getBillPay() {
		return billPay;
	}
	public void setBillPay(String billPay) {
		this.billPay = billPay;
	}
	public String getTelephone1() {
		return telephone1;
	}
	public void setTelephone1(String telephone1) {
		this.telephone1 = telephone1;
	}
	public String getTelephone2() {
		return telephone2;
	}
	public void setTelephone2(String telephone2) {
		this.telephone2 = telephone2;
	}
	public String getEmail() {
		return email;
	}
	public void setEmail(String email) {
		this.email = email;
	}
	
	
	
	
	
	
	
	
}