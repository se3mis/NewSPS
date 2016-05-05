package application.web;

import java.util.Date;
import java.util.List;
import java.util.Map;

import javax.persistence.PersistenceException;
import javax.persistence.RollbackException;

import masters.model.Bank;
import masters.model.BankBranch;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.apache.struts2.interceptor.SessionAware;

import application.model.Applicant;
import application.model.Application;
import application.model.ApplicationPK;
import application.model.ApplicationReference;
import application.model.ApplicationReferencePK;
import application.model.WiringLandDetail;
import application.model.WiringLandDetailPK;
import application.service.ApplicantEjb;
import application.service.ApplicationEjb;
import application.service.ApplicationReferenceEjb;
import application.service.WiringLandDetailEjb;

import com.opensymphony.xwork2.ActionSupport;

import estimate.service.TariffCategoryEjb;
import estimate.service.TariffEjb;

@SuppressWarnings("serial")
public class GenerateDirect extends ActionSupport implements SessionAware {
	// Common Fields
	private static final Log log = LogFactory.getLog(GenerateDirect.class);
	private static final String MSG_ERROR="ERROR";
	private static final String MSG_INFO="INFO";
	private static final String MSG_DONE="DONE";
	private static final String MSG_NONE="NONE";
	private static final String CONFIRMPATH="New Connection>Application>Generate App No";	
	private Map<String, Object> session;
	//JSP
	private String loggedInUserId;
	private String path;
	private String costCenterNo;
	private String csc;
	private String errorMessage;
	private String messageType=MSG_NONE;

	
	private List<String> listTariffCode;
	private List<String> listTariffCatCode;
	// Hidden Fields
	private String state;
	private String hiddenSecondState;
	private String hiddenPath;
	
	// ///
	private String applicationIdPrefix;
	
	// JSP Fields
	private String prefix;
	

	private String appReferenceNo;
	private String applicationId;
	private String applicationType;
	private String applicationSubType;
	private String duration;
	private String durationType;
	private String fromDate; 
	private String toDate; 
	
	private String date;
	private String description;
	// Customer Fields
	private String idType;
	private String idNo;
	private String firstName;
	private String lastName;
	private String streetAddress;
	private String suburb;
	private String city;
	private String postalCode;
	private String telephoneNo;
	private String mobileNo;
	private String email;
	private String preferefLanguage;
	private String cebEmployee;
	private String isLoanApp;
	// Wiring N LAnd Fields
	private String serviceStreetAddress;
	private String serviceSuburb;
	private String serviceCity;
	private String servicePostalCode;
	private String ownership;
	private String occupy_OwnerCertified;
	private String isGovernmentPlace;
	private String landDispute;
	private String assessmentNo;
	private String neighboursAccNo;
	private String noOfBulbs;
	private String noOfFans;
	private String noOfPlughs_5A;
	private String noOfPlughs_15A;
	private String motorsTotal;
	private String weldingPlant;
	private String metalCrusher;
	private String sawMills;
	private String phase;
	private String tariffCode;
	private String tariffCategoryCode;
	private String connectionType;
	private String customerCategory;
	private String customerType;
	private String preparedBy;
	private String confirmedBy;
	private String allocatedTo;
	private String isLoopService;
	
	
	

	// Hidden Fields
	private String hiddenApplicationType;
	private String hiddenOwnership;
	private String hiddenOccupy_OwnerCertified;
	private String hiddenIsGovernmentPlace;
	private String hiddenLandDispute;
	private String hiddenPhase;
	private String hiddenConnectionType;
	private String hiddenIsLoopService;
	private String status;
	// PIV Fields
	private String pivNo;
	private String fullName;
	private String fullAddress;
	private String referenceNo;
	private String cebBranch;
	private List<Bank> bankList; 
	private List<BankBranch> branchList; 
	private String payingBankCode;
    private String payingBranchCode;
	//Other Fields
	private Date submitDate;
	private Date confirmDate;
	private String confirmTime;
	private String allocatedBy;
	private Date allocatedDate;
	private String allocatedTime;
	private String addUser;
    private Date addDate;
	private String addTime;
	private String updUser;
	private Date updDate;
	private String updTime;
	private String newApplicationId;
	
	public String execute(){
		setState("GenerateApplicationNo");
		setLoggedData();
		setPath(CONFIRMPATH);
		setHiddenPath(getPath());//to get back
		setListTariffCode(getTafiffCode());
		setListTariffCatCode(getTafiffCaTCode());
		return SUCCESS;
	}
	
	public String findAppId() {
		Applicant applicant=null;
		//ApplicantDao applicantDao=null;
		Application application=null;
		ApplicationPK applicationPK=null;
		//ApplicationDao applicationDao=null;
		WiringLandDetail wiringLandDetail=null;
		WiringLandDetailPK wiringLandDetailPK=null;
		//WiringLandDetailDao wiringLandDetailDao=null;
		ApplicationReference applicationReference=null;
		ApplicationReferencePK applicationReferencePK=null;
		//ApplicationReferenceDao applicationReferenceDao=null;
		ApplicantEjb applicantEjb=new ApplicantEjb(getSessionKey("region"));
		ApplicationEjb applicationEjb=new ApplicationEjb(getSessionKey("region"));
		WiringLandDetailEjb wiringLandDetailEjb=new WiringLandDetailEjb(getSessionKey("region"));
		ApplicationReferenceEjb appReferenceEjb=new ApplicationReferenceEjb(getSessionKey("region"));
		try{			
			//setParameters(parameters);
			//setApplicationIdPara();
			//setHiddensParas();
			setErrorMessage(null);
			setLoggedData();
			System.out.println("5555555555555555555555555555555"+getPath());
			setPath(getPath());
			//setState("findAppId");
			setState(getState());
			setHiddenSecondState("findAppId");
			log.info("setState  to " + "findAppId");
			//setEmf(Persistence.createEntityManagerFactory("SMC"));
			//setEm(emf.createEntityManager());
			applicationPK=new ApplicationPK();
			applicationPK.setApplicationId(getApplicationId());
			applicationPK.setDeptId(getCostCenterNo());
			//applicationDao=new ApplicationDao(getEmf());
			//applicantDao=new ApplicantDao(getEmf());
			//wiringLandDetailDao=new WiringLandDetailDao(getEmf());
			applicationReferencePK =new ApplicationReferencePK();
			//applicationReferenceDao=new ApplicationReferenceDao(getEmf());
			//application=applicationDao.findByAppId(applicationPK,getEm());
			// log.info("applicant " + applicant);
			application=applicationEjb.findByAppId(applicationPK);
			log.info("1");
			if (application != null) {
				log.info("2");
				setApplicationData(application);
				//applicant = findById(application.getIdNo());
				//applicant=applicantDao.findById(application.getIdNo(),getEm());
				applicant=applicantEjb.findById(application.getIdNo());
				if (applicant != null) { 
					log.info("3");
					setApplicantData(applicant);
					log.info("4");
					//wiringLandDetail = findWiringLandDetailByAppId(longApplicationNo);
					wiringLandDetailPK=new WiringLandDetailPK();
					wiringLandDetailPK.setApplicationId(getApplicationId());
					wiringLandDetailPK.setDeptId(getCostCenterNo());
					//wiringLandDetail=wiringLandDetailDao.findByAppId(wiringLandDetailPK,getEm());
					wiringLandDetail=wiringLandDetailEjb.findByAppId(wiringLandDetailPK);
					log.info("5");
					if (wiringLandDetail != null) {
						setWiringLandDetail(wiringLandDetail);
						//applicationReference=findAppRefDetailByAppId(longApplicationNo);
						applicationReferencePK.setApplicationId(getApplicationId());
						applicationReferencePK.setDeptId(getCostCenterNo());
						//applicationReference=applicationReferenceDao.findByAppId(applicationReferencePK,getEm());
						//log.info("before");
						//log.info(applicationReferencePK);
						applicationReference=appReferenceEjb.findByAppId(applicationReferencePK);
						//log.info(applicationReference);
						//log.info("After");
						log.info("6");
						if(applicationReference!=null){
							log.info("7");
							setAppRefDetail(applicationReference);
							return SUCCESS;
						}else
						  return SUCCESS;
					} else
						clearAllFields();
						setErrorMessage("No Wiring and Land Details found");
						return ERROR;
				} else
					clearAllFields();
					setErrorMessage("Applicant id not found");
					return ERROR;
			} else
				clearAllFields();
				setErrorMessage("Application id not found");
				return ERROR;
		} catch (RollbackException e) {
			e.printStackTrace();
			System.out.println("ERROR MESSEGE" + e.getMessage());
			setErrorMessage(e.getMessage());
			return ERROR;
		} catch (PersistenceException e) {
			e.printStackTrace();
			System.out.println("ERROR MESSEGE" + e.getMessage());
			setErrorMessage(e.getMessage());
			return ERROR;
		} catch (Exception e) {
			e.printStackTrace();
			System.out.println("ERROR MESSEGE" + e.getMessage());
			setErrorMessage(e.getMessage());
			return ERROR;
		}finally{
			//getEm().close();
			//getEmf().close(); 
			//emf=null;
			//getEm().close();
			//em=null;
			applicant=null;
			application=null;
			applicationPK=null;
			wiringLandDetail=null;
			wiringLandDetailPK=null;
			applicationReference=null;
			applicationReferencePK=null;
			
		}
	}
	
	private void setApplicantData(Applicant applicant) throws NumberFormatException{
		log.info("setApplicantData ############### " + applicant.getIdNo());
		setIdType(applicant.getIdType());
		setIdNo(applicant.getIdNo());
		setFirstName(applicant.getFirstName());
		setLastName(applicant.getLastName());
		setStreetAddress(applicant.getStreetAddress());
		setCity(applicant.getCity());
		setSuburb(applicant.getSuburb());
		if (applicant.getPostalCode() != null)
			setPostalCode(applicant.getPostalCode().toString());
		if (applicant.getTelephoneNo() != null)
			setTelephoneNo(applicant.getTelephoneNo().toString());
		if (applicant.getMobileNo() != null)
			setMobileNo(applicant.getMobileNo().toString());
		setPreferefLanguage(applicant.getPreferredLanguage());
		setEmail(applicant.getEmail());
		setCebEmployee(applicant.getCebEmployee());

	}
	
	private void setApplicationData(Application application) throws NumberFormatException {
		log.info("setApplicationData#################### " + application.getId().getApplicationId());
		setApplicationId(application.getId().getApplicationId());
		setApplicationType(application.getApplicationType());
		setApplicationSubType(application.getApplicationSubType());
		//Duration
		setDurationType(application.getDurationType());
		if (application.getDuration()!=null)
			setDuration(application.getDuration().toString());
		if (application.getFromDate()!=null)
			setFromDate(application.getFromDate().toString());
		if (application.getToDate()!=null)
			setToDate(application.getToDate().toString());
		//Duration
		setIsLoanApp(application.getIsLoanApp());
		setDate(application.getSubmitDate().toString());
		setCostCenterNo(application.getId().getDeptId());
		setCsc(getCsc());
		setPreparedBy(application.getPreparedBy());
		setConfirmedBy(application.getConfirmedBy());
		setDescription(application.getDescription());
		setAllocatedTo(application.getAllocatedTo());
		setStatus(application.getStatus());
		log.info("485y34805y03485y0834 " + application.getConfirmedBy());
		// setIdNumber(application.getIdNo());
		// Applicant applicant = findById(application.getIdNo());
		// if (applicant!=null){
		// setApplicantData(applicant);

		// }

	}
	
	private void setWiringLandDetail(WiringLandDetail wiringLandDetail) throws NumberFormatException{
		// wiringLandDetail.getS
		//log.info("setWiringLandDetail##################### "+wiringLandDetail.getId().getApplicationId());
		setServiceStreetAddress(wiringLandDetail.getServiceStreetAddress());
		setServiceCity(wiringLandDetail.getServiceCity());
		setServiceSuburb(wiringLandDetail.getServiceSuburb());
		if (wiringLandDetail.getServicePostalCode() != null)
			setServicePostalCode(wiringLandDetail.getServicePostalCode()
					.toString());

		setAssessmentNo(wiringLandDetail.getAssessmentNo());
		setNeighboursAccNo(wiringLandDetail.getNeighboursAccNo());
		//log.info("wiringLandDetail.getNoOfBulbs() "	+ wiringLandDetail.getNoOfBulbs());
		if (wiringLandDetail.getNoOfBulbs() != null) {
			setNoOfBulbs(wiringLandDetail.getNoOfBulbs().toString());
		} else
			setNoOfBulbs("0");
		if (wiringLandDetail.getNoOfFans() != null)
			setNoOfFans(wiringLandDetail.getNoOfFans().toString());
		else
			setNoOfFans("0");
		if (wiringLandDetail.getNoOfPlugs5a() != null)
			setNoOfPlughs_5A(wiringLandDetail.getNoOfPlugs5a().toString());
		else
			setNoOfPlughs_5A("0");
		if (wiringLandDetail.getNoOfPlugs15a() != null)
			setNoOfPlughs_15A(wiringLandDetail.getNoOfPlugs15a().toString());
		else
			setNoOfPlughs_15A("0");
		if (wiringLandDetail.getMotorTotal() != null)
			setMotorsTotal(wiringLandDetail.getMotorTotal().toString());
		else
			setMotorsTotal("0");
		//log.info("wiringLandDetail.getWeldingPlant() " + wiringLandDetail.getWeldingPlant());
		if (wiringLandDetail.getWeldingPlant() != null)
			setWeldingPlant(wiringLandDetail.getWeldingPlant().toString());
		else
			setWeldingPlant("0");
		if (wiringLandDetail.getMetalCrusher() != null)
			setMetalCrusher(wiringLandDetail.getMetalCrusher().toString());
		else
			setMetalCrusher("0");
		if (wiringLandDetail.getSawMills() != null)
			setSawMills(wiringLandDetail.getSawMills().toString());
		else
			setSawMills("0");

		log.info("wiringLandDetail.getTariffCode() " + wiringLandDetail.getTariffCode());
		//log.info("finish() " + "finish");
		if (wiringLandDetail.getTariffCode() != null)
			setTariffCode(wiringLandDetail.getTariffCode().toString());
		else
			setTariffCode("0");
		log.info("wiringLandDetail.getTariffCatCode() " + wiringLandDetail.getTariffCatCode());
		if (wiringLandDetail.getTariffCatCode() != null)
			setTariffCategoryCode(wiringLandDetail.getTariffCatCode().toString());
		else
			setTariffCode("0");
		setCustomerCategory(wiringLandDetail.getCustomerCategory());
		setCustomerType(wiringLandDetail.getCustomerType());
		/*// Hidden
		setHiddenOwnership(wiringLandDetail.getOwnership());
		setHiddenOccupy_OwnerCertified(wiringLandDetail
				.getOccupyOwnerCertified());
		setHiddenIsGovernmentPlace(wiringLandDetail.getIsGovernmentPlace());
		setHiddenLandDispute(wiringLandDetail.getLandDispute());
		if (wiringLandDetail.getPhase() != null)
			setHiddenPhase(wiringLandDetail.getPhase().toString());
		if (wiringLandDetail.getConnectionType() != null)
			setHiddenConnectionType(wiringLandDetail.getConnectionType()
					.toString());
		//setHiddenIsLoopService(wiringLandDetail.getIsLoopService());
		*/
		// Radio buttons
		setOwnership(wiringLandDetail.getOwnership());
		setOccupy_OwnerCertified(wiringLandDetail
				.getOccupyOwnerCertified());
		setIsGovernmentPlace(wiringLandDetail.getIsGovernmentPlace());
		//setLandDispute(wiringLandDetail.getLandDispute());
		if (wiringLandDetail.getPhase() != null)
			setPhase(wiringLandDetail.getPhase().toString());
		if (wiringLandDetail.getConnectionType() != null)
			setConnectionType(wiringLandDetail.getConnectionType()
					.toString());
		//setIsLoopService(wiringLandDetail.getIsLoopService());
	}
	
	private void clearAllFields(){
		
		setIdNo(null);
		clearApplicantFields();
		setAppReferenceNo(null);
		//setApplicationId(null);
		setApplicationType(null);
		setDate(null);
		setDescription(null);
		//setCostCenterNo(null);
		setCsc(null);
		setServiceStreetAddress(null);
		setServiceSuburb(null);
		setServiceCity(null);
		setServicePostalCode(null);
		//setOwnership(null);
		//setOccupy_OwnerCertified(null);
		//setIsGovernmentPlace(null);
		//setLandDispute(null);
		setAssessmentNo(null);
		setNeighboursAccNo(null);
		setNoOfBulbs(null);
		setNoOfFans(null);
		setNoOfPlughs_5A(null);
		setNoOfPlughs_15A(null);
		setMotorsTotal(null);
		setWeldingPlant(null);
		setMetalCrusher(null);
		setSawMills(null);
		setPhase(null);
		setTariffCode(null);
		setTariffCategoryCode(null);
		setConnectionType(null);
		setCustomerCategory(null);
		setCustomerType(null);
		setPreparedBy(null);
		setConfirmedBy(null);
		setIsLoopService(null);
		//setState(null);
		setAllocatedTo(null);
		//setPath(null);
		setStatus(null);
}
	
	private void clearApplicantFields(){
		clearIdNo();
		setIdType(null);
		setFirstName(null);
		setLastName(null);
		setStreetAddress(null);
		setSuburb(null);
		setCity(null);
		setPostalCode(null);
		setTelephoneNo(null);
		setMobileNo(null);
		setEmail(null);
		setPreferefLanguage(null);
		setCebEmployee(null);
	}
	
	private void clearIdNo(){
		setIdNo(null);
	}
	
	
	public void setLoggedData() {
        //setSession(ActionContext.getContext().getSession());
        log.info(getSession());
        setLoggedInUserId(getSessionKey("userName"));
        setCostCenterNo(getSessionKey("costCenterNo"));
        setCsc(getSessionKey("costCenterName"));
        //log.info(getSession());
    }
 	
 	public String getSessionKey(String key) {
        return getSession().get(key).toString();
    }
	
 	private List<String> getTafiffCaTCode(){
		TariffCategoryEjb ejb=new TariffCategoryEjb(getSessionKey("region"));
		List<String> list=ejb.getTariffCategoryList();
		return list;
		
	}
	
	private List<String> getTafiffCode(){
		TariffEjb ejb=new TariffEjb(getSessionKey("region"));
		List<String> list=ejb.getTariffCodeList();
		return list;
		
	}

	
	@Override
	public void setSession(Map<String, Object> session) {
		this.session=session;
		
	}
	
	public Map<String, Object> getSession() {
		return session;
	}
	public String getCostCenterNo() {
		return costCenterNo;
	}
	public void setCostCenterNo(String costCenterNo) {
		this.costCenterNo = costCenterNo;
	}
	
	public String getLoggedInUserId() {
		return loggedInUserId;
	}

	public void setLoggedInUserId(String loggedInUserId) {
		this.loggedInUserId = loggedInUserId;
	}
	public String getState() {
		return state;
	}
	public void setState(String state) {
		this.state = state;
	}
	public String getHiddenSecondState() {
		return hiddenSecondState;
	}
	public void setHiddenSecondState(String hiddenSecondState) {
		this.hiddenSecondState = hiddenSecondState;
	}
	public String getPath() {
		return path;
	}


	public void setPath(String path) {
		this.path = path;
	}
	public String getHiddenPath() {
		return hiddenPath;
	}


	public void setHiddenPath(String hiddenPath) {
		this.hiddenPath = hiddenPath;
	}
	
	public List<String> getListTariffCode() {
		return listTariffCode;
	}

	public void setListTariffCode(List<String> listTariffCode) {
		this.listTariffCode = listTariffCode;
	}

	public List<String> getListTariffCatCode() {
		return listTariffCatCode;
	}

	public void setListTariffCatCode(List<String> listTariffCatCode) {
		this.listTariffCatCode = listTariffCatCode;
	}
	public String getCsc() {
		return csc;
	}
	public void setCsc(String csc) {
		this.csc = csc;
	}
	
	public String getApplicationIdPrefix() {
		return applicationIdPrefix;
	}

	public void setApplicationIdPrefix(String applicationIdPrefix) {
		this.applicationIdPrefix = applicationIdPrefix;
	}

	public String getPrefix() {
		return prefix;
	}

	public void setPrefix(String prefix) {
		this.prefix = prefix;
	}

	public String getAppReferenceNo() {
		return appReferenceNo;
	}

	public void setAppReferenceNo(String appReferenceNo) {
		this.appReferenceNo = appReferenceNo;
	}

	public String getApplicationId() {
		return applicationId;
	}

	public void setApplicationId(String applicationId) {
		this.applicationId = applicationId;
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

	public String getDuration() {
		return duration;
	}

	public void setDuration(String duration) {
		this.duration = duration;
	}

	public String getDurationType() {
		return durationType;
	}

	public void setDurationType(String durationType) {
		this.durationType = durationType;
	}

	public String getFromDate() {
		return fromDate;
	}

	public void setFromDate(String fromDate) {
		this.fromDate = fromDate;
	}

	public String getToDate() {
		return toDate;
	}

	public void setToDate(String toDate) {
		this.toDate = toDate;
	}

	public String getDate() {
		return date;
	}

	public void setDate(String date) {
		this.date = date;
	}

	public String getDescription() {
		return description;
	}

	public void setDescription(String description) {
		this.description = description;
	}

	public String getIdType() {
		return idType;
	}

	public void setIdType(String idType) {
		this.idType = idType;
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

	public String getTelephoneNo() {
		return telephoneNo;
	}

	public void setTelephoneNo(String telephoneNo) {
		this.telephoneNo = telephoneNo;
	}

	public String getMobileNo() {
		return mobileNo;
	}

	public void setMobileNo(String mobileNo) {
		this.mobileNo = mobileNo;
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public String getPreferefLanguage() {
		return preferefLanguage;
	}

	public void setPreferefLanguage(String preferefLanguage) {
		this.preferefLanguage = preferefLanguage;
	}

	public String getCebEmployee() {
		return cebEmployee;
	}

	public void setCebEmployee(String cebEmployee) {
		this.cebEmployee = cebEmployee;
	}

	public String getIsLoanApp() {
		return isLoanApp;
	}

	public void setIsLoanApp(String isLoanApp) {
		this.isLoanApp = isLoanApp;
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

	public String getServicePostalCode() {
		return servicePostalCode;
	}

	public void setServicePostalCode(String servicePostalCode) {
		this.servicePostalCode = servicePostalCode;
	}

	public String getOwnership() {
		return ownership;
	}

	public void setOwnership(String ownership) {
		this.ownership = ownership;
	}

	public String getOccupy_OwnerCertified() {
		return occupy_OwnerCertified;
	}

	public void setOccupy_OwnerCertified(String occupy_OwnerCertified) {
		this.occupy_OwnerCertified = occupy_OwnerCertified;
	}

	public String getIsGovernmentPlace() {
		return isGovernmentPlace;
	}

	public void setIsGovernmentPlace(String isGovernmentPlace) {
		this.isGovernmentPlace = isGovernmentPlace;
	}

	public String getLandDispute() {
		return landDispute;
	}

	public void setLandDispute(String landDispute) {
		this.landDispute = landDispute;
	}

	public String getAssessmentNo() {
		return assessmentNo;
	}

	public void setAssessmentNo(String assessmentNo) {
		this.assessmentNo = assessmentNo;
	}

	public String getNeighboursAccNo() {
		return neighboursAccNo;
	}

	public void setNeighboursAccNo(String neighboursAccNo) {
		this.neighboursAccNo = neighboursAccNo;
	}

	public String getNoOfBulbs() {
		return noOfBulbs;
	}

	public void setNoOfBulbs(String noOfBulbs) {
		this.noOfBulbs = noOfBulbs;
	}

	public String getNoOfFans() {
		return noOfFans;
	}

	public void setNoOfFans(String noOfFans) {
		this.noOfFans = noOfFans;
	}

	public String getNoOfPlughs_5A() {
		return noOfPlughs_5A;
	}

	public void setNoOfPlughs_5A(String noOfPlughs_5A) {
		this.noOfPlughs_5A = noOfPlughs_5A;
	}

	public String getNoOfPlughs_15A() {
		return noOfPlughs_15A;
	}

	public void setNoOfPlughs_15A(String noOfPlughs_15A) {
		this.noOfPlughs_15A = noOfPlughs_15A;
	}

	public String getMotorsTotal() {
		return motorsTotal;
	}

	public void setMotorsTotal(String motorsTotal) {
		this.motorsTotal = motorsTotal;
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

	public String getPhase() {
		return phase;
	}

	public void setPhase(String phase) {
		this.phase = phase;
	}

	public String getTariffCode() {
		return tariffCode;
	}

	public void setTariffCode(String tariffCode) {
		this.tariffCode = tariffCode;
	}

	public String getTariffCategoryCode() {
		return tariffCategoryCode;
	}

	public void setTariffCategoryCode(String tariffCategoryCode) {
		this.tariffCategoryCode = tariffCategoryCode;
	}

	public String getConnectionType() {
		return connectionType;
	}

	public void setConnectionType(String connectionType) {
		this.connectionType = connectionType;
	}

	public String getCustomerCategory() {
		return customerCategory;
	}

	public void setCustomerCategory(String customerCategory) {
		this.customerCategory = customerCategory;
	}

	public String getCustomerType() {
		return customerType;
	}

	public void setCustomerType(String customerType) {
		this.customerType = customerType;
	}

	public String getPreparedBy() {
		return preparedBy;
	}

	public void setPreparedBy(String preparedBy) {
		this.preparedBy = preparedBy;
	}

	public String getConfirmedBy() {
		return confirmedBy;
	}

	public void setConfirmedBy(String confirmedBy) {
		this.confirmedBy = confirmedBy;
	}

	public String getAllocatedTo() {
		return allocatedTo;
	}

	public void setAllocatedTo(String allocatedTo) {
		this.allocatedTo = allocatedTo;
	}

	public String getIsLoopService() {
		return isLoopService;
	}

	public void setIsLoopService(String isLoopService) {
		this.isLoopService = isLoopService;
	}

	public String getHiddenApplicationType() {
		return hiddenApplicationType;
	}

	public void setHiddenApplicationType(String hiddenApplicationType) {
		this.hiddenApplicationType = hiddenApplicationType;
	}

	public String getHiddenOwnership() {
		return hiddenOwnership;
	}

	public void setHiddenOwnership(String hiddenOwnership) {
		this.hiddenOwnership = hiddenOwnership;
	}

	public String getHiddenOccupy_OwnerCertified() {
		return hiddenOccupy_OwnerCertified;
	}

	public void setHiddenOccupy_OwnerCertified(String hiddenOccupy_OwnerCertified) {
		this.hiddenOccupy_OwnerCertified = hiddenOccupy_OwnerCertified;
	}

	public String getHiddenIsGovernmentPlace() {
		return hiddenIsGovernmentPlace;
	}

	public void setHiddenIsGovernmentPlace(String hiddenIsGovernmentPlace) {
		this.hiddenIsGovernmentPlace = hiddenIsGovernmentPlace;
	}

	public String getHiddenLandDispute() {
		return hiddenLandDispute;
	}

	public void setHiddenLandDispute(String hiddenLandDispute) {
		this.hiddenLandDispute = hiddenLandDispute;
	}

	public String getHiddenPhase() {
		return hiddenPhase;
	}

	public void setHiddenPhase(String hiddenPhase) {
		this.hiddenPhase = hiddenPhase;
	}

	public String getHiddenConnectionType() {
		return hiddenConnectionType;
	}

	public void setHiddenConnectionType(String hiddenConnectionType) {
		this.hiddenConnectionType = hiddenConnectionType;
	}

	public String getHiddenIsLoopService() {
		return hiddenIsLoopService;
	}

	public void setHiddenIsLoopService(String hiddenIsLoopService) {
		this.hiddenIsLoopService = hiddenIsLoopService;
	}

	public String getStatus() {
		return status;
	}

	public void setStatus(String status) {
		this.status = status;
	}

	public String getPivNo() {
		return pivNo;
	}

	public void setPivNo(String pivNo) {
		this.pivNo = pivNo;
	}

	public String getFullName() {
		return fullName;
	}

	public void setFullName(String fullName) {
		this.fullName = fullName;
	}

	public String getFullAddress() {
		return fullAddress;
	}

	public void setFullAddress(String fullAddress) {
		this.fullAddress = fullAddress;
	}

	public String getReferenceNo() {
		return referenceNo;
	}

	public void setReferenceNo(String referenceNo) {
		this.referenceNo = referenceNo;
	}

	public String getCebBranch() {
		return cebBranch;
	}

	public void setCebBranch(String cebBranch) {
		this.cebBranch = cebBranch;
	}

	public List<Bank> getBankList() {
		return bankList;
	}

	public void setBankList(List<Bank> bankList) {
		this.bankList = bankList;
	}

	public List<BankBranch> getBranchList() {
		return branchList;
	}

	public void setBranchList(List<BankBranch> branchList) {
		this.branchList = branchList;
	}

	public String getPayingBankCode() {
		return payingBankCode;
	}

	public void setPayingBankCode(String payingBankCode) {
		this.payingBankCode = payingBankCode;
	}

	public String getPayingBranchCode() {
		return payingBranchCode;
	}

	public void setPayingBranchCode(String payingBranchCode) {
		this.payingBranchCode = payingBranchCode;
	}

	public Date getSubmitDate() {
		return submitDate;
	}

	public void setSubmitDate(Date submitDate) {
		this.submitDate = submitDate;
	}

	public Date getConfirmDate() {
		return confirmDate;
	}

	public void setConfirmDate(Date confirmDate) {
		this.confirmDate = confirmDate;
	}

	public String getConfirmTime() {
		return confirmTime;
	}

	public void setConfirmTime(String confirmTime) {
		this.confirmTime = confirmTime;
	}

	public String getAllocatedBy() {
		return allocatedBy;
	}

	public void setAllocatedBy(String allocatedBy) {
		this.allocatedBy = allocatedBy;
	}

	public Date getAllocatedDate() {
		return allocatedDate;
	}

	public void setAllocatedDate(Date allocatedDate) {
		this.allocatedDate = allocatedDate;
	}

	public String getAllocatedTime() {
		return allocatedTime;
	}

	public void setAllocatedTime(String allocatedTime) {
		this.allocatedTime = allocatedTime;
	}

	public String getAddUser() {
		return addUser;
	}

	public void setAddUser(String addUser) {
		this.addUser = addUser;
	}

	public Date getAddDate() {
		return addDate;
	}

	public void setAddDate(Date addDate) {
		this.addDate = addDate;
	}

	public String getAddTime() {
		return addTime;
	}

	public void setAddTime(String addTime) {
		this.addTime = addTime;
	}

	public String getUpdUser() {
		return updUser;
	}

	public void setUpdUser(String updUser) {
		this.updUser = updUser;
	}

	public Date getUpdDate() {
		return updDate;
	}

	public void setUpdDate(Date updDate) {
		this.updDate = updDate;
	}

	public String getUpdTime() {
		return updTime;
	}

	public void setUpdTime(String updTime) {
		this.updTime = updTime;
	}

	public String getNewApplicationId() {
		return newApplicationId;
	}

	public void setNewApplicationId(String newApplicationId) {
		this.newApplicationId = newApplicationId;
	}
	
	public String getErrorMessage() {
		return errorMessage;
	}

	public void setErrorMessage(String errorMessage) {
		this.errorMessage = errorMessage;
	}

	public String getMessageType() {
		return messageType;
	}

	public void setMessageType(String messageType) {
		this.messageType = messageType;
	}
	private void setAppRefDetail(ApplicationReference applicationReference){
		setAppReferenceNo(applicationReference.getApplicationNo());
	}

}
