package applicationBS.web;

import com.opensymphony.xwork2.ActionSupport;

@SuppressWarnings("serial")
public class SaveApplication extends ActionSupport{
	
	SaveApplicationData data = new SaveApplicationData();
	private String applicationNo;
	private String pIV_1_No;
	private String date;
	private String Description;
	private String firstName;
	private String lastName;
	private String nICNo_passportNo;
	private String preferefLanguage;
	private String postalAddress_1;
	private String Ownership;
	private String postalAddress_2;
	private String telephoneNo;
	private String postalAddress_3;
	private String email;
	private String serviceAddress_1;
	private String assessmentNo;
	private String serviceAddress_2;
	private String neighboursAccNo;
	private String serviceAddress_3;
	private String customerType;
	private String phase;
	private String tariffCategory;
	private String connectionType;
	private String pIV_1_Amount;
	private String cSC;
	private String bank;
	private String costCenterNo;
	private String isLoopService;
	private String numberOfBullbs;
	private String numberOfFans;
	private String numberOfPlughs_5A;
	private String numberOfPlughs_15A;
	private String motarsTotal;
	private String weldingPlant;
	private String metalCrusher;
	private String sawMills;
	private String occupy_OwnerCertified;
	private String isGovernmenrPlace;
	private String cEBEmployee;
	private String landDispute;

	

	public String getApplicationNo() {
		return applicationNo;
	}

	public void setApplicationNo(String applicationNo) {
		this.applicationNo = applicationNo;
	}

	public String getpIV_1_No() {
		return pIV_1_No;
	}

	public void setpIV_1_No(String pIV_1No) {
		pIV_1_No = pIV_1No;
	}

	public String getDate() {
		return date;
	}

	public void setDate(String date) {
		this.date = date;
	}

	public String getDescription() {
		return Description;
	}

	public void setDescription(String description) {
		Description = description;
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

	public String getnICNo_passportNo() {
		return nICNo_passportNo;
	}

	public void setnICNo_passportNo(String nICNoPassportNo) {
		nICNo_passportNo = nICNoPassportNo;
	}

	public String getPreferefLanguage() {
		return preferefLanguage;
	}

	public void setPreferefLanguage(String preferefLanguage) {
		this.preferefLanguage = preferefLanguage;
	}

	public String getPostalAddress_1() {
		return postalAddress_1;
	}

	public void setPostalAddress_1(String postalAddress_1) {
		this.postalAddress_1 = postalAddress_1;
	}

	public String getOwnership() {
		return Ownership;
	}

	public void setOwnership(String ownership) {
		Ownership = ownership;
	}

	public String getPostalAddress_2() {
		return postalAddress_2;
	}

	public void setPostalAddress_2(String postalAddress_2) {
		this.postalAddress_2 = postalAddress_2;
	}

	public String getTelephoneNo() {
		return telephoneNo;
	}

	public void setTelephoneNo(String telephoneNo) {
		this.telephoneNo = telephoneNo;
	}

	public String getPostalAddress_3() {
		return postalAddress_3;
	}

	public void setPostalAddress_3(String postalAddress_3) {
		this.postalAddress_3 = postalAddress_3;
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public String getServiceAddress_1() {
		return serviceAddress_1;
	}

	public void setServiceAddress_1(String serviceAddress_1) {
		this.serviceAddress_1 = serviceAddress_1;
	}

	public String getAssessmentNo() {
		return assessmentNo;
	}

	public void setAssessmentNo(String assessmentNo) {
		this.assessmentNo = assessmentNo;
	}

	public String getServiceAddress_2() {
		return serviceAddress_2;
	}

	public void setServiceAddress_2(String serviceAddress_2) {
		this.serviceAddress_2 = serviceAddress_2;
	}

	public String getNeighboursAccNo() {
		return neighboursAccNo;
	}

	public void setNeighboursAccNo(String neighboursAccNo) {
		this.neighboursAccNo = neighboursAccNo;
	}

	public String getServiceAddress_3() {
		return serviceAddress_3;
	}

	public void setServiceAddress_3(String serviceAddress_3) {
		this.serviceAddress_3 = serviceAddress_3;
	}

	public String getCustomerType() {
		return customerType;
	}

	public void setCustomerType(String customerType) {
		this.customerType = customerType;
	}

	public String getPhase() {
		return phase;
	}

	public void setPhase(String phase) {
		this.phase = phase;
	}

	public String getTariffCategory() {
		return tariffCategory;
	}

	public void setTariffCategory(String tariffCategory) {
		this.tariffCategory = tariffCategory;
	}

	public String getConnectionType() {
		return connectionType;
	}

	public void setConnectionType(String connectionType) {
		this.connectionType = connectionType;
	}

	public String getpIV_1_Amount() {
		return pIV_1_Amount;
	}

	public void setpIV_1_Amount(String pIV_1Amount) {
		pIV_1_Amount = pIV_1Amount;
	}

	public String getcSC() {
		return cSC;
	}

	public void setcSC(String cSC) {
		this.cSC = cSC;
	}

	public String getBank() {
		return bank;
	}

	public void setBank(String bank) {
		this.bank = bank;
	}

	public String getCostCenterNo() {
		return costCenterNo;
	}

	public void setCostCenterNo(String costCenterNo) {
		this.costCenterNo = costCenterNo;
	}

	public String getIsLoopService() {
		return isLoopService;
	}

	public void setIsLoopService(String isLoopService) {
		this.isLoopService = isLoopService;
	}

	public String getNumberOfBullbs() {
		return numberOfBullbs;
	}

	public void setNumberOfBullbs(String numberOfBullbs) {
		this.numberOfBullbs = numberOfBullbs;
	}

	public String getNumberOfFans() {
		return numberOfFans;
	}

	public void setNumberOfFans(String numberOfFans) {
		this.numberOfFans = numberOfFans;
	}

	public String getNumberOfPlughs_5A() {
		return numberOfPlughs_5A;
	}

	public void setNumberOfPlughs_5A(String numberOfPlughs_5A) {
		this.numberOfPlughs_5A = numberOfPlughs_5A;
	}

	public String getNumberOfPlughs_15A() {
		return numberOfPlughs_15A;
	}

	public void setNumberOfPlughs_15A(String numberOfPlughs_15A) {
		this.numberOfPlughs_15A = numberOfPlughs_15A;
	}

	public String getMotarsTotal() {
		return motarsTotal;
	}

	public void setMotarsTotal(String motarsTotal) {
		this.motarsTotal = motarsTotal;
	}

	public String getWeldingPlant() {
		return weldingPlant;
	}

	public void setWeldingPlant(String weldingPlant) {
		this.weldingPlant = weldingPlant;
	}

	public String getMetalCrusher() {
		return metalCrusher;
	}

	public void setMetalCrusher(String metalCrusher) {
		this.metalCrusher = metalCrusher;
	}

	public String getSawMills() {
		return sawMills;
	}

	public void setSawMills(String sawMills) {
		this.sawMills = sawMills;
	}

	public String getOccupy_OwnerCertified() {
		return occupy_OwnerCertified;
	}

	public void setOccupy_OwnerCertified(String occupyOwnerCertified) {
		occupy_OwnerCertified = occupyOwnerCertified;
	}

	public String getIsGovernmenrPlace() {
		return isGovernmenrPlace;
	}

	public void setIsGovernmenrPlace(String isGovernmenrPlace) {
		this.isGovernmenrPlace = isGovernmenrPlace;
	}

	public String getcEBEmployee() {
		return cEBEmployee;
	}

	public void setcEBEmployee(String cEBEmployee) {
		this.cEBEmployee = cEBEmployee;
	}

	public String getLandDispute() {
		return landDispute;
	}

	public void setLandDispute(String landDispute) {
		this.landDispute = landDispute;
	}
		
	public String execute() {
			System.out.println(getApplicationNo());
						
		return "success";
	}

}
