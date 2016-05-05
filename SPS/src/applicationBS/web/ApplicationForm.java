package applicationBS.web;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.List;
import java.util.Map;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.PersistenceException;
import javax.persistence.RollbackException;
import javax.servlet.http.HttpServletRequest;

import masters.model.Bank;
import masters.model.BankBranch;
import masters.model.BankBranchPK;
import masters.service.BankEjb;
import masters.service.ProvinceDetailsMasterEjb;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.apache.struts2.ServletActionContext;
import org.apache.struts2.interceptor.ParameterAware;
import org.apache.struts2.interceptor.SessionAware;

import piv.model.PivDetail;
import piv.service.PivDetailEjb;
import security.service.SecurityEjb;
import util.common.Constants;
import util.common.EstimateNumberGenerator;
import util.common.Format;
import util.common.PivPrefixType;
import application.model.Applicant;
import application.model.Application;
import application.model.ApplicationPK;
import application.model.ApplicationReference;
import application.model.ApplicationReferencePK;
import application.model.CityMap;
import application.model.WiringLandDetail;
import application.model.WiringLandDetailCon;
import application.model.WiringLandDetailConPK;
import application.model.WiringLandDetailPK;
import application.service.ApplicantEjb;
import application.service.ApplicationEjb;
import application.service.ApplicationReferenceEjb;
import application.service.ApplicationTransactionEjb;
import application.service.WiringLandDetailConEjb;
import application.service.WiringLandDetailEjb;

import com.opensymphony.xwork2.ActionSupport;

import costcenter.model.Gldeptin;
import costcenter.service.CostCenterEjb;
import estimate.model.Spestedy;
import estimate.model.SpestedyPK;
import estimate.service.EstimateEjb;
import estimate.service.EstimateReferenceEjb;
import estimate.service.SpestedyEjb;
import estimate.service.TariffCategoryEjb;
import estimate.service.TariffEjb;

@SuppressWarnings("serial")
public class ApplicationForm extends ActionSupport implements SessionAware,
		ParameterAware {
	// Common Fields
	private static final Log log = LogFactory.getLog(ApplicationForm.class);
	private static final String systemType="E";
	private static final String newStatus = "N";
	private static final String modifyStatus = "M";
	private static final String confirmedStatus = "C";
	private static final String allocatedStatus = "A";
	private static final String NEWPATH_PIV="Application>New PIV";
	private static final String newPath="New Connection>Application>New Application";
	private static final String viewPath="New Connection>Application>View Application";
	private static final String printPath="New Connection>Application>Print Application";
	private static final String modifyPath="New Connection>Application>Modify Application";
	private static final String confirmPath="New Connection>Application>Generate App No";
	private static final String CHANGE_SERVICE_ADD_PATH="New Connection>Application>Change Service Address";
	private static final String ALTER_LOAN_APP="New Connection>Application>Alter Loan App";
	
	
	//
	private static final String MSG_ERROR="ERROR";
	private static final String MSG_INFO="INFO";
	private static final String MSG_DONE="DONE";
	private static final String MSG_NONE="NONE";
	private String errorMessage;
	private String messageType=MSG_NONE;
	private Map<String, Object> session;
	private Map <String, String[]> parameters;
	private String loggedInUserId;
	private String path;
	private PathStore pathStore;
	//private String csc;
	// private Map<?, ?> parameters;
	// Persistence
	private EntityManagerFactory emf;
	private EntityManager em;
	private Format format;
	private String amountInWords;

	// ///
	private String applicationIdPrefix;
	// JSP Fields
	private String prefix;
	private String appReferenceNo;
	private String applicationId;
	private String applicationType;
	private String applicationSubType;
	private String duration;
	/*private String durationType;
	private String fromDate; 
	private String toDate; */
	
	private String date;
	private String description;
	private String costCenterNo;
	private String csc;
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
	private String accountNos;
	
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
	private List<String> listTariffCode;
	private List<String> listTariffCatCode;
	
	

	// Hidden Fields
	private String state;
	private String hiddenSecondState;
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
    private String payingBankName;
    private String payingBranchName;
	

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
	//
	private String msgApplicationId;
	//
	
	//
	private String statusMsg;
	//Loan
	private String applicationNo;
	private String appointmentDate;
	private String  appointmentSession; 
	
	private String costCenterArea; 
	
	private List<String> areaCodeList = new ArrayList<String>(); 
	private List<String> applicationTypes; 
	
	private List<String> premisesList;
	private List<String> foundSourceList;
	private String desPremises;
	
	private String demand;
	private String schemaNo;
	private String schemaName;
	private String proposer;
	private String repres1;
	private String repres2;
	private String jobName;
	private String electrate;
	private String devSec;
	private String gsDivision;
	private String agaDivision;
	private String areaName;
	private String capacityOfServices;
	private String meterPoints;
	private String consumerRef;
	private String hiddenIsAleardyhaveElectricity;
	private String foundSource;
	private String premises;
	private String noOfMeterPoints;
	private String isAlreadyHave;
	private String repres2Con;
	private String repres1Con;
	private String applicationFee;
	private String miscellaneousIncome;
    private String electricityDebtors;
    private String securityDeposit;
    private String serConnOrElecSch;
    private String tenderDeposit;
    private String miscellaneousDeposit;
    private String cashInTransit;
    private String forDishonouredCheque;
    private String subTotal;
    private String vat;
    private String grandTotal;
    private String cscname;
    private String district;
    private List<String> districtCodeList = new ArrayList<String>(); 
    private List<String> electorateCodeList = new ArrayList<String>(); 
    private List<String> cscCodeList = new ArrayList<String>();

    private String districtCode;
    private String electorateCode;
	private String cscCode;
	
	private String  branchType;
	
    public String getDistrictCode() {
		return districtCode;
	}

	public void setDistrictCode(String districtCode) {
		this.districtCode = districtCode;
	}

	public String getElectorateCode() {
		return electorateCode;
	}

	public void setElectorateCode(String electorateCode) {
		this.electorateCode = electorateCode;
	}

	public String getCscCode() {
		return cscCode;
	}

	public void setCscCode(String cscCode) {
		this.cscCode = cscCode;
	}


    
	public String getBranchType() {
		return branchType;
	}

	public void setBranchType(String branchType) {
		this.branchType = branchType;
	}

	public String getIsAlreadyHave() {
		return isAlreadyHave;
	}

	public void setIsAlreadyHave(String isAlreadyHave) {
		this.isAlreadyHave = isAlreadyHave;
	}

	public String getJobName() {
		return jobName;
	}

	public void setJobName(String jobName) {
		this.jobName = jobName;
	}

	public String getElectrate() {
		return electrate;
	}

	public void setElectrate(String electrate) {
		this.electrate = electrate;
	}

	public String getDistrict() {
		return district;
	}

	public void setDistrict(String district) {
		this.district = district;
	}

	public String getAmountInWords() {
		return amountInWords;
	}

	public void setAmountInWords(String amountInWords) {
		this.amountInWords = amountInWords;
	}

	public String getCscname() {
		return cscname;
	}

	public void setCscname(String cscname) {
		this.cscname = cscname;
	}

	public String getDevSec() {
		return devSec;
	}

	public void setDevSec(String devSec) {
		this.devSec = devSec;
	}

	public String getGsDivision() {
		return gsDivision;
	}

	public void setGsDivision(String gsDivision) {
		this.gsDivision = gsDivision;
	}

	public String getAgaDivision() {
		return agaDivision;
	}

	public void setAgaDivision(String agaDivision) {
		this.agaDivision = agaDivision;
	}

	

	public String getApplicationFee() {
		return applicationFee;
	}

	public void setApplicationFee(String applicationFee) {
		this.applicationFee = applicationFee;
	}

	public String getMiscellaneousIncome() {
		return miscellaneousIncome;
	}

	public void setMiscellaneousIncome(String miscellaneousIncome) {
		this.miscellaneousIncome = miscellaneousIncome;
	}

	public String getElectricityDebtors() {
		return electricityDebtors;
	}

	public void setElectricityDebtors(String electricityDebtors) {
		this.electricityDebtors = electricityDebtors;
	}

	public String getSecurityDeposit() {
		return securityDeposit;
	}

	public void setSecurityDeposit(String securityDeposit) {
		this.securityDeposit = securityDeposit;
	}

	public String getSerConnOrElecSch() {
		return serConnOrElecSch;
	}

	public void setSerConnOrElecSch(String serConnOrElecSch) {
		this.serConnOrElecSch = serConnOrElecSch;
	}

	public String getTenderDeposit() {
		return tenderDeposit;
	}

	public void setTenderDeposit(String tenderDeposit) {
		this.tenderDeposit = tenderDeposit;
	}

	public String getMiscellaneousDeposit() {
		return miscellaneousDeposit;
	}

	public void setMiscellaneousDeposit(String miscellaneousDeposit) {
		this.miscellaneousDeposit = miscellaneousDeposit;
	}

	public String getCashInTransit() {
		return cashInTransit;
	}

	public void setCashInTransit(String cashInTransit) {
		this.cashInTransit = cashInTransit;
	}

	public String getForDishonouredCheque() {
		return forDishonouredCheque;
	}

	public void setForDishonouredCheque(String forDishonouredCheque) {
		this.forDishonouredCheque = forDishonouredCheque;
	}

	public String getSubTotal() {
		return subTotal;
	}

	public void setSubTotal(String subTotal) {
		this.subTotal = subTotal;
	}

	public String getVat() {
		return vat;
	}

	public void setVat(String vat) {
		this.vat = vat;
	}

	public String getGrandTotal() {
		return grandTotal;
	}

	public void setGrandTotal(String grandTotal) {
		this.grandTotal = grandTotal;
	}

	public String getNoOfMeterPoints() {
		return meterPoints;
	}

	public void setNoOfMeterPoints(String meterPoints) {
		this.meterPoints = meterPoints;
	}

	public String getPremises() {
		return premises;
	}

	public void setPremises(String premises) {
		this.premises = premises;
	}

	public ApplicationForm() {
		super();
		log.info("@@@@@@@@@@@@@@@@@@@&&&&&&&&&&&&&&&&&&&&&&");
		//ApplicationTransactionEjb transactionEjb=new ApplicationTransactionEjb(getSessionKey("region"));
		//areaCodeList = transactionEjb.findAreaCodes(getSessionKey("costCenterNo"), getSessionKey("region"));
		//setListTariffCode(getTafiffCode());
		//setListTariffCatCode(getTafiffCaTCode());
	}

	/**
     *
     */
	public void setLoggedData() {
        //log.info(getSession());
		setLoggedInUserId(getSessionKey("userName"));
        setCostCenterNo(getSessionKey("costCenterNo"));
        setCebBranch(getSessionKey("costCenterName"));
        setCsc(getCebBranch());
        
        
        
    }
 	
 	public String getSessionKey(String key) {
        return getSession().get(key).toString();
    }
 	
 	public Map<String,String> getSessionKeyMap(String key) {
        return (Map<String, String>) getSession().get(key);
    }

	public String getAccountNos() {
		return accountNos;
	}

	public void setAccountNos(String accountNos) {
		this.accountNos = accountNos;
	}

	/**
	 * return success
	 */
	public String execute() {
	
		if(state.equals("print")){
			setLoggedData();
			setListTariffCode(getTafiffCode());
			setListTariffCatCode(getTafiffCaTCode());
			setAreaCodeList(getAreaCodeList());
			setDistrictCodeList(getDistrictCodeList());
			setCscCodeList(getCscCodeList());
			setElectorateCodeList(getElectorateCodeList());
			System.out.println("smctype :" + getSessionKey("smcType"));
			setApplicationType(PivPrefixType.getAPP_XXX_Type(getSessionKey("smcType")));
			//return SUCCESS;
			if(getSessionKey("smcType").equalsIgnoreCase(Constants.RUTAL_ELECTRIFICATION)){
				return "successRE";
			}else{
				return SUCCESS;
			}
		}else{
			setLoggedData();
			setPath(viewPath);
			PathStore pathStore= new PathStore();
			pathStore.setPath(viewPath);
			setState("view");
			setListTariffCode(getTafiffCode());
			setListTariffCatCode(getTafiffCaTCode());
			setAreaCodeList(getAreaCodeList());
			setDistrictCodeList(getDistrictCodeList());
			setCscCodeList(getCscCodeList());
			setElectorateCodeList(getElectorateCodeList());
			setApplicationType(PivPrefixType.getAPP_XXX_Type(getSessionKey("smcType")));
			if (applicationId!=null)
			 setApplicationId(session.get("applicationId").toString());
			//findAppId();
			//return SUCCESS;
			
			return SUCCESS;
			
		}
		//setLoggedData();
		//setPath(viewPath);
		//PathStore pathStore= new PathStore();
		//pathStore.setPath(viewPath);
		//setState("view");
		//setListTariffCode(getTafiffCode());
		//setListTariffCatCode(getTafiffCaTCode());
		
		//if (applicationId!=null)
		 //setApplicationId(session.get("applicationId").toString());
		//findAppId();
		//return SUCCESS;
	}
	
	public String genAppNo() {
		setAreaCodeList(getAreaCodeList());
		setDistrictCodeList(getDistrictCodeList());
		setCscCodeList(getCscCodeList());
		setElectorateCodeList(getElectorateCodeList());
		setListTariffCode(getTafiffCode());
		setListTariffCatCode(getTafiffCaTCode());
		System.out.println("khgjhgh");
		setLoggedData();
		setState("confirmApp");
		setPath(confirmPath); 
		setHiddenSecondState("findAppId");
		setApplicationId(session.get("applicationId").toString());
		session.remove("applicationId");
		findAppId();
		//return SUCCESS;
		
		return SUCCESS;
		
	}
	
	
	//EJB
	//@SuppressWarnings("unchecked")
	public String save() {
		try {
			setListTariffCode(getTafiffCode());
			setListTariffCatCode(getTafiffCaTCode());
			setAreaCodeList(getAreaCodeList());
			setDistrictCodeList(getDistrictCodeList());
			setCscCodeList(getCscCodeList());
			setElectorateCodeList(getElectorateCodeList());
			//CityMap cityMap=new CityMap();
			setBankList(getAllBankList());
			ApplicationTransactionEjb ejb=new ApplicationTransactionEjb(getSessionKey("region"));
			setErrorMessage(null);
			//setLoggedData();
			//setParameterssa(parameters);
			//setAllParas();
			//setCostCenterNo(cityMap.mapArea(costCenterNo, serviceCity));
			System.out.println("getCostCenterNo() "+getCostCenterNo());
			setCostCenterNo(getCostCenterNo());
			setCscname(getCscname());
			setFormat(new Format());
			setDate(getFormat().FormatDate());
			//setReferenceNo(getApplicationId());
			setFullName(getFirstName() + " " + getLastName());
			//String postalCode = getPostalCode() == null ? "" : getPostalCode();
			//setFullAddress(getStreetAddress() + " " + getSuburb() + " "
			//		+ getCity() + " " + postalCode);
			String servicePostalCode = getServicePostalCode() == null ? "" : getServicePostalCode();
			
			setServiceStreetAddress(serviceStreetAddress != null ? serviceStreetAddress : Constants.DEFAULT_STRING1);
			setServiceSuburb(serviceSuburb != null ? serviceSuburb : Constants.DEFAULT_STRING1);
			System.out.println("serviceCity "+serviceCity);
			setServiceCity(serviceCity != null ? serviceCity : Constants.DEFAULT_STRING1);
			setServicePostalCode(servicePostalCode);
			setFullAddress(getServiceStreetAddress() + ";" + getServiceSuburb() + ";"
					+ getServiceCity() + ";" + servicePostalCode);
			System.out.println("getSessionKeyuserName "+getSessionKey("userName"));
			//setPreparedBy(getLoggedInUserId());
			setDescription(getDescription());
			//setCostCenterNo(costCenterNo);
			//setCebBranch(csc);
			
			
			Application application=createApplicationObject();
			//System.out.println("Hii" + application.getDuration());
			WiringLandDetail wiringLandDetail=createWiringLandObject();
			WiringLandDetailCon wiringLandDetailCon=createWiringLandConObject();
			//ejb.save(application, wiringLandDetail);
			String appPrefix= PivPrefixType.getAPP_XXX_Type(getSessionKey("smcType"));
			if(appPrefix.equalsIgnoreCase("XXX")){
				
				   appPrefix = getSessionKey("estimateType");
			}
			System.out.println("Hiiiiiiii  HHHHHHH" +appPrefix );
			//String appId=ejb.save(application, wiringLandDetail,wiringLandDetailCon, getCostCenterNo()+"/"+PivPrefixType.getAPP_XXX_Type(getSessionKey("smcType"))+"/"+getFormat().getYear()+"/");//prefix
			String appId=ejb.save(application, wiringLandDetail,wiringLandDetailCon, getCostCenterNo()+"/"+appPrefix+"/"+getFormat().getYear()+"/");//prefix
			System.out.println("Hiiiiiiii  HHHHHHH");
			setReferenceNo(appId);
			setPivNo("PIV/"+appId);
			
			setDescription("APPLICATION FEE");
			setPivDetails();
			//set application details
			//setApplicationIdPrefix(getCostCenterNo()+"/"+PivPrefixType.getAPP_XXX_Type(getSessionKey("smcType"))+"/"+getFormat().getYear()+"/");
			
			setApplicationId(appId);
			//getEm().getTransaction().commit();
			//PIV data			
			SecurityEjb securityEjb=new SecurityEjb(getSessionKey("region"));
			CostCenterEjb ejbCC=new CostCenterEjb(getSessionKey("region"));
	        Gldeptin gldeptin=ejbCC.findById(securityEjb.getCostCenter(getSessionKey("userName")));
	        System.out.println(gldeptin);
	        System.out.println("Hiiiiiiii  HHHHHHH");
	        if(gldeptin != null){
		        setPayingBankCode(gldeptin.getBankCode());
		        setPayingBranchCode(gldeptin.getBranchCode());
	        }
	        BankEjb bankEjb = new BankEjb(getSessionKey("region"));
	        if(payingBankCode != null && !payingBankCode.equals("")){
		        Bank bank = bankEjb.findBankCode(payingBankCode);
		        setPayingBankName(bank.getBankName());
		 		
		 		BankBranchPK bankBranchPK = new BankBranchPK();
		 		bankBranchPK.setBankCode(payingBankCode);
		 		bankBranchPK.setBranchCode(payingBranchCode);
		 		BankBranch bankBranch = bankEjb.findBankBranchCode(bankBranchPK);
		 		setPayingBranchName(bankBranch.getBranchName());
	        }
	 		setPath(NEWPATH_PIV);
	 		setState("fromApp");
	 		if(getSessionKey("smcType").equalsIgnoreCase(Constants.RUTAL_ELECTRIFICATION) || getSessionKey("smcType").equalsIgnoreCase(Constants.PLANNING)){
	 			
	 			//setMessageType(MSG_DONE);
				//setErrorMessage("Application Details Successfully Saved");
	 		}
	 	
			//setErrorMessage("Application Details Successfully Saved");
	 		setMessageType(MSG_DONE);
			setErrorMessage("DONE:- Application Details Successfully Saved." );
	 		return "save";
	 		
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
		} finally{
			//getEm().close();
			//em=null;
			//getEmf().close();
			//emf=null;
			//format=null;
		}
		

	}
	private void setPivDetails(){
		setMiscellaneousIncome("0.00");
    	setElectricityDebtors("0");
    	setSecurityDeposit("0");
    	setSerConnOrElecSch("0");
    	setMiscellaneousDeposit("0");
    	setForDishonouredCheque("0");
    	setCashInTransit("0");
    	setTenderDeposit("0");
    	setSubTotal("2000.00");
    	setVat("0");
    	setGrandTotal("2000.00");
    	setAmountInWords("Two Thousand Rupees Only");
    	
    	setApplicationFee("2000.00");
	}
	/*public String next() {
		try {
			CityMap cityMap=new CityMap();
			setBankList(getAllBankList());
			ApplicationTransactionEjb ejb=new ApplicationTransactionEjb();
			setErrorMessage(null);
			setLoggedData();
			//setParameterssa(parameters);
			//setAllParas();
			setCostCenterNo(cityMap.mapArea(costCenterNo, serviceCity));
			setFormat(new Format());
			setDate(getFormat().FormatDate());
			//setReferenceNo(getApplicationId());
			setFullName(getFirstName() + " " + getLastName());
			//String postalCode = getPostalCode() == null ? "" : getPostalCode();
			//setFullAddress(getStreetAddress() + " " + getSuburb() + " "
			//		+ getCity() + " " + postalCode);
			String servicePostalCode = getServicePostalCode() == null ? "" : getServicePostalCode();
			setFullAddress(getServiceStreetAddress() + " " + getServiceSuburb() + " "
					+ getServiceCity() + " " + servicePostalCode);
			setServiceCity(serviceCity);
			setPreparedBy(getLoggedInUserId());
			setDescription(getDescription());
			//setEmf(Persistence.createEntityManagerFactory("SMC"));
			//setEm(emf.createEntityManager());
			//getEm().getTransaction().begin();
			
			
			
			// log.info("3333333333333333333");
			//saveApplicationData();
			// log.info("444444444444444444");
			//saveWiringLandDetail();
			//setApplicationId(getNewApplicationId().toString());
			
			Application application=createApplicationObject();
			WiringLandDetail wiringLandDetail=createWiringLandObject();
			//ejb.save(application, wiringLandDetail);
			String appId=ejb.save(application, wiringLandDetail, PivPrefixType.APP_NEW_CONN+"/"+getCostCenterNo()+"/"+getFormat().getYear()+"/");
			setReferenceNo(appId);
			setPivNo(appId);
			//getEm().getTransaction().commit();
			CostCenterEjb ejbCC=new CostCenterEjb();
	        Gldeptin gldeptin=ejbCC.findById(getCostCenterNo());
	        setPayingBankCode(gldeptin.getBankCode());
	        setPayingBranchCode(gldeptin.getBranchCode());
			setState("fromApp");
			return "next";
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
		} finally{
			//getEm().close();
			//em=null;
			//getEmf().close();
			//emf=null;
			//format=null;
		}
		

	}*/
	//EJB
	/*public String save() {
		try {
			CityMap cityMap=new CityMap();
			setErrorMessage(null);
			//setParameters(parameters);
			//setAllParas();
			// log.info("1111111111111111111");
			setLoggedData();
			// log.info("2222222222222222222");
			ApplicationTransactionEjb ejb=new ApplicationTransactionEjb();
			setFormat(new Format());
			setDate(getFormat().FormatDate());
			setCostCenterNo(cityMap.mapArea(costCenterNo, serviceCity));
			log.info("2222222222222222222 "+getCostCenterNo());
			Application application=createApplicationObject();
			WiringLandDetail wiringLandDetail=createWiringLandObject();
			//ejb.save( application, wiringLandDetail);
			
			ejb.save(application, wiringLandDetail, PivPrefixType.APP_NEW_CONN+"/"+getCostCenterNo()+"/"+getFormat().getYear()+"/");
			//setEmf(Persistence.createEntityManagerFactory("SMC"));
			//setEm(emf.createEntityManager());
			//getEm().getTransaction().begin();
			//	saveApplicationData();
			//	saveWiringLandDetail();
			//getEm().getTransaction().commit();
			
			return "save";
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
			//em=null;
			//emf=null;
			format=null;
		}

	}*/
	//EJB
	public String modify() {
		Application application=null;
		ApplicationPK applicationPK=null;
		//ApplicationDao applicationDao=null;
		WiringLandDetail wiringLandDetail=null;
		WiringLandDetailCon wiringLandDetailcon=null;
		WiringLandDetailPK  wiringLandDetailPK=null;
		WiringLandDetailConPK  wiringLandDetailconPK=null;
		//WiringLandDetailDao wiringLandDetailDao=null;
		ApplicationEjb applicationEjb=new ApplicationEjb(getSessionKey("region"));
		WiringLandDetailEjb wiringLandDetailEjb=new WiringLandDetailEjb(getSessionKey("region"));
		WiringLandDetailConEjb wiringLandDetailconEjb=new WiringLandDetailConEjb(getSessionKey("region"));
		ApplicationTransactionEjb transactionEjb=new ApplicationTransactionEjb(getSessionKey("region"));
		try {
			setListTariffCode(getTafiffCode());
			setListTariffCatCode(getTafiffCaTCode());
			//setParameters(parameters);
			setErrorMessage(null);
			//setAllParas();
			setLoggedData();
			setFormat(new Format());
			
			setDate(getFormat().FormatDate());
			
			//setEmf(Persistence.createEntityManagerFactory("SMC"));
			//setEm(emf.createEntityManager());
			//applicationDao=new ApplicationDao();
			wiringLandDetailPK=new WiringLandDetailPK();
			wiringLandDetailPK.setApplicationId(getApplicationId());
			wiringLandDetailPK.setDeptId(getCostCenterNo());
			
			wiringLandDetailconPK=new WiringLandDetailConPK();
			wiringLandDetailconPK.setApplicationId(getApplicationId());
			wiringLandDetailconPK.setDeptId(getCostCenterNo());
			
			//wiringLandDetailDao =new WiringLandDetailDao();
			applicationPK=new ApplicationPK();
			
			applicationPK.setApplicationId(getApplicationId());
			applicationPK.setDeptId(getCostCenterNo());
			//application=applicationDao.findByAppId(applicationPK, getEm());
			application=applicationEjb.findByAppId(applicationPK);
			
			wiringLandDetail=wiringLandDetailEjb.findByAppId(wiringLandDetailPK);
			wiringLandDetailcon=wiringLandDetailconEjb.findByAppId(wiringLandDetailconPK);
			System.out.println("applicationwiringLandDetail" + application+"  "+wiringLandDetail);
			if (application!=null){
				application=updateApplicationModel(application);
				
				wiringLandDetail=updateWiringLandDetailModel(wiringLandDetail);
				
				wiringLandDetailcon=updateWiringLandDetailConModel(wiringLandDetailcon);
				
				transactionEjb.update(application, wiringLandDetail,wiringLandDetailcon);
				
				//getEm().getTransaction().begin();
				//updateApplicationData(application,applicationDao);
				//updateWiringLandDetail(wiringLandDetail,wiringLandDetailDao);
				//getEm().getTransaction().commit();
				setMessageType(MSG_DONE);
				setErrorMessage("DONE:- Successfully Modified");
				
				return "modify";
				
				
			}else{
				setMessageType(MSG_ERROR);
				setErrorMessage("Application id not found");
				return ERROR;
			}
			
		} catch (RollbackException e) {
			e.printStackTrace();
			System.out.println("ERROR MESSEGE" + e.getMessage());
			setMessageType(MSG_ERROR);
			setErrorMessage(e.getMessage());
			return ERROR;
		} catch (PersistenceException e) {
			e.printStackTrace();
			System.out.println("ERROR MESSEGE" + e.getMessage());
			setMessageType(MSG_ERROR);
			setErrorMessage(e.getMessage());
			return ERROR;
		} catch (Exception e) {
			e.printStackTrace();
			System.out.println("ERROR MESSEGE" + e.getMessage());
			setMessageType(MSG_ERROR);
			setErrorMessage(e.getMessage());
			return ERROR;
		}finally{
			//getEm().close();
			//getEmf().close(); 
			//log.info("getEmf() is closed");
			//em=null;
			//emf=null;
			format=null;
			application=null;
			applicationPK=null;
			//applicationDao=null;
			wiringLandDetail=null;
			wiringLandDetailPK=null;
			//wiringLandDetailDao=null;
		}

	}
	//EJB
	public String allocate(){
		Application application=null;
		ApplicationPK applicationPK=null;
		//ApplicationDao applicationDao=null;
		
		try {
			setListTariffCode(getTafiffCode());
			setListTariffCatCode(getTafiffCaTCode());
			ApplicationEjb ejb=new ApplicationEjb(getSessionKey("region"));
			//setParameters(parameters);
			setErrorMessage(null);
			//setAllParas();
			setLoggedData();
			//setState("allocate");
			setFormat(new Format());
			//setEmf(Persistence.createEntityManagerFactory("SMC"));
			//setEm(emf.createEntityManager());
			//applicationDao=new ApplicationDao();
			applicationPK=new ApplicationPK();
			applicationPK.setApplicationId(getApplicationId());
			applicationPK.setDeptId(getCostCenterNo());
			log.info(log.isInfoEnabled() +"  *****************************************");
			application=ejb.findByAppId(applicationPK);
			if (application!=null){
				application=updateAllocationDetail(application);
				ejb.updateApplication(application);
				//getEm().getTransaction().begin();
				//updateAllocationDetail(application,applicationDao);
				//getEm().getTransaction().commit();
				return "allocate";
			}else{
				setErrorMessage("Application id not found");
				return ERROR;
			}
			
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
		} finally{
			//getEmf().close();
			//getEm().close();
			//emf=null;
			//em=null;
			format=null;
			application=null;
			applicationPK=null;
			//applicationDao=null;
		}
	}
	//EJB
	public String issuePiv(){
		try {
			setListTariffCode(getTafiffCode());
			setListTariffCatCode(getTafiffCaTCode());
			setBankList(getAllBankList());
			setLoggedData();
			setParameters(parameters);
			//setAllParas();
			setFormat(new Format());
			setDate(getFormat().FormatDate());
			setReferenceNo(getApplicationId());
			setPivNo(getApplicationId());
			setFullName(getFirstName() + " " + getLastName());
			String postalCode = getPostalCode() == null ? "" : getPostalCode();
			setFullAddress(getStreetAddress() + " " + getSuburb() + " "
					+ getCity() + " " + postalCode);
			setPreparedBy(getLoggedInUserId());
			setDescription(getDescription());
			setConfirmedBy("");
			setState("issuePiv");
			CostCenterEjb ejbCC=new CostCenterEjb(getSessionKey("region"));
	        Gldeptin gldeptin=ejbCC.findById(getCostCenterNo());
	        setPayingBankCode(gldeptin.getBankCode());
	        setPayingBranchCode(gldeptin.getBranchCode());
			return "next";
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
		} finally{
			format=null;
		}
	}
	//EJB
	public String findAppId() {
		clearAllFields();
		Applicant applicant=null;
		//ApplicantDao applicantDao=null;
		Application application=null;
		ApplicationPK applicationPK=null;
		//ApplicationDao applicationDao=null;
		WiringLandDetail wiringLandDetail=null;
		WiringLandDetailPK wiringLandDetailPK=null;
		WiringLandDetailConPK wiringLandDetailconPK=null;
		WiringLandDetailCon wiringLandDetaicon=null;
		//WiringLandDetailDao wiringLandDetailDao=null;
		ApplicationReference applicationReference=null;
		ApplicationReferencePK applicationReferencePK=null;
		//ApplicationReferenceDao applicationReferenceDao=null;
		Spestedy  spestedy=new Spestedy();
		SpestedyPK  spestedyPK=new SpestedyPK();
		
		ApplicantEjb applicantEjb=new ApplicantEjb(getSessionKey("region"));
		ApplicationEjb applicationEjb=new ApplicationEjb(getSessionKey("region"));
		WiringLandDetailEjb wiringLandDetailEjb=new WiringLandDetailEjb(getSessionKey("region"));
		WiringLandDetailConEjb wiringLandDetailconEjb=new WiringLandDetailConEjb(getSessionKey("region"));
		ApplicationReferenceEjb appReferenceEjb=new ApplicationReferenceEjb(getSessionKey("region"));
		SpestedyEjb spestedyEjb=new SpestedyEjb(getSessionKey("region"));
		try{	
			setListTariffCode(getTafiffCode());
			setListTariffCatCode(getTafiffCaTCode());
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
			//
			
			
			
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
					
					wiringLandDetailconPK=new WiringLandDetailConPK();
					wiringLandDetailconPK.setApplicationId(getApplicationId());
					wiringLandDetailconPK.setDeptId(getCostCenterNo());
					
					wiringLandDetaicon=wiringLandDetailconEjb.findByAppId(wiringLandDetailconPK);
					log.info("5");
					if(wiringLandDetaicon != null){
						setWiringLandDetailCon(wiringLandDetaicon);
					}
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
							spestedy=spestedyEjb.getAppointment(getCostCenterNo(), applicationReference.getApplicationNo());
							if (spestedy!=null){
								if (spestedy.getAppointmentDate()!=null){
									appointmentDate=spestedy.getAppointmentDate().toString();
									appointmentSession=spestedy.getTimeSession();
									costCenterArea=getCostCenterNo();
									allocatedTo=spestedy.getAllocatedTo();
								}
								
							}
							
							log.info("7");
							setAppRefDetail(applicationReference);
							visibleButton();
							return SUCCESS;
						}else{
							visibleButton();
						  	return SUCCESS;
						}
						
						
						  	
						  	
					} else{
						clearAllFields();
						setErrorMessage("No Wiring and Land Details found");
						return ERROR;
					}
			
					
					
				} else{
					clearAllFields();
					setErrorMessage("Applicant id not found");
					return ERROR;
				}
			} else{
				clearAllFields();
				setErrorMessage("Application id not found");
				return ERROR;
			}
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
	//EJB
	//@SuppressWarnings("unchecked")
	public String findId() {
		
		Applicant applicant=null;
		ApplicantEjb applicantEjb=new ApplicantEjb(getSessionKey("region"));
		//ApplicantDao applicantDao=null;
		
		try{
			setListTariffCode(getTafiffCode());
			setListTariffCatCode(getTafiffCaTCode());
			//setAreaCodeList(getAreaCodeList());
			//setParameters(parameters);
			//setApplicantIdPara(ActionContext.getContext().getParameters());
			setErrorMessage(null);
			//setApplicantIdPara();s
			//setHiddensParas();
			setLoggedData();
			setFormat(new Format());
			setDate(getFormat().FormatDate());
			//setState("findId");
			setState(getState());
			setHiddenSecondState("findId");
			setPath(getPath());
			//setEmf(Persistence.createEntityManagerFactory("SMC"));
			//setEm(emf.createEntityManager());
			//applicantDao=new ApplicantDao(getEmf());
			//applicant = findById(getIdNo());
			//applicant=applicantDao.findById(getIdNo(),getEm());
			applicant=applicantEjb.findById(getIdNo());
			// log.info("applicant " + applicant);
			if (applicant != null) {
				setApplicantData(applicant); 
				
				return SUCCESS;
				
			} else{
				setMessageType(MSG_INFO);
				setErrorMessage("Customer Id not found");
				clearApplicantFields();
				return ERROR;
			}
				
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
			//applicantDao=null;
			format=null;
		}

	}
	//@SuppressWarnings("unchecked")
	public String fillTheSame() {
		//setParameters(parameters);
		setErrorMessage(null);
		//setApplicantParas();
		//setHiddensParas();
		setListTariffCode(getTafiffCode());
		setListTariffCatCode(getTafiffCaTCode());
		setLoggedData();
		setPath(getPath());
		//setDate(getFormat().FormatDate());
		setState("fillTheSame");
		setServiceStreetAddress(getStreetAddress());
		setServiceSuburb(getSuburb());
		setServiceCity(getCity());
		setServicePostalCode(getPostalCode());
		return SUCCESS;
	}
	//EJB
	//@SuppressWarnings("unchecked")
	public String confirm() {
		
		setMessageType(MSG_NONE);
		setErrorMessage(null);
		CityMap cityMap=new CityMap();
		Application application=null;
		ApplicationPK applicationPK=null;
		//ApplicationDao applicationDao=null;
		//ApplicationReference applicationReference=null;
		//ApplicationReferencePK applicationReferencePk=null;
		//ApplicationReferenceDao applicationReferenceDao=null;
		System.out.println("Hiiii");
		try{
			setListTariffCode(getTafiffCode());
			setListTariffCatCode(getTafiffCaTCode());
			ApplicationEjb applicationEjb=new ApplicationEjb(getSessionKey("region"));
		//ApplicationReferenceEjb appReferenceEjb=new ApplicationReferenceEjb();
		ApplicationTransactionEjb transactionEjb=new ApplicationTransactionEjb(getSessionKey("region"));
		//setParameters(parameters);
		//setAppId();
		setLoggedData();
		setState("confirm");
		//setDate(getFormat().FormatDate());
		setFormat(new Format());
		//setEmf(Persistence.createEntityManagerFactory("SMC"));
		//setEm(emf.createEntityManager());
		//applicationReferencePk=new ApplicationReferencePK();
		//applicationReferencePk.setApplicationId(getApplicationId());
		//applicationReferencePk.setDeptId(getCostCenterNo());
		applicationPK=new ApplicationPK();
		applicationPK.setApplicationId(getApplicationId());
		applicationPK.setDeptId(getCostCenterNo());
		//applicationDao = new ApplicationDao();
		//applicationReferenceDao=new ApplicationReferenceDao();
		application=applicationEjb.findByAppId(applicationPK);
		application=updateConfirmBy(application);
		//setCostCenterNo(cityMap.mapCity(costCenterNo, serviceCity));
		//setApplicationIdPrefix(getCostCenterNo()+"/"+systemType+getApplicationType()+"/"+getFormat().getYear()+"/");
		String estPrefix= PivPrefixType.getEST_XXX_Type(getSessionKey("smcType"));
		System.out.println("Hiiii ; " + estPrefix);
		if(estPrefix.equalsIgnoreCase("XXX")){
			
			   String jobTypeCode = getSessionKey("smcType");
			   
			  /* if(jobTypeCode != null){
				  setBranchType(jobTypeCode);
			   }else{
				   setBranchType(Constants.DEFAULT_ESTIMATE_TYPE);
			   }
			   putSessionKey("smcType", getBranchType());*/
			   if(jobTypeCode != null){
				   estPrefix = jobTypeCode;
			   }else{
				   
			   }
		}
		
		setApplicationIdPrefix(getCostCenterNo()+"/"+estPrefix+"/"+getFormat().getYear()+"/");
		System.out.println("Hiiii ; " + getApplicationIdPrefix());
		
		//comment for standarized
		/*EstimateNumberGenerator  estimateNumber = new EstimateNumberGenerator();
		String AreaCode = null;
		//Map<String,String> codes = getSessionKeyMap("areaCodes");
		ApplicationTransactionEjb transonEjb=new ApplicationTransactionEjb(getSessionKey("region"));
		String areaCode = transonEjb.findAreaCodeIDs(getCostCenterNo(),getAreaName());
		if(areaCode != null && areaCode.length() > 0){
			AreaCode =areaCode.substring(0,3);
		}
		String applicationPrefix = estimateNumber.getCommecialReferenceNumber(getFoundSource(), getCostCenterNo(), AreaCode, getSessionKey("smcType"));
		setApplicationIdPrefix(applicationPrefix);*/
		System.out.println("Hiiii APP ; " + application.getApplicationNo());
		setMsgApplicationId(transactionEjb.update(application, getApplicationIdPrefix()));
		
		System.out.println("Hiiii XXX ; " + getMsgApplicationId());
		
		if(getSessionKey("branchType") != null &&  (getSessionKey("branchType").equalsIgnoreCase(Constants.BRANCHTYPE_CONSTRUCTION) ||
				getSessionKey("branchType").equalsIgnoreCase(Constants.BRANCHTYPE_DISTRIBUTION_AND_MAINTENANCE) ||
				getSessionKey("branchType").equalsIgnoreCase(Constants.BRANCHTYPE_ENERGY_MANAGEMENT) || 
				getSessionKey("branchType").equalsIgnoreCase(Constants.BRANCHTYPE_AREA_UNIT))){
				
			
			EstimateReferenceEjb estimateRefEjb = new EstimateReferenceEjb(); 
			System.out.println("Hiiii2 ; " + getMsgApplicationId());
			estimateRefEjb.checkEstimateNoExist(getMsgApplicationId(), getMsgApplicationId(), costCenterNo, getSessionKey("region"));
			System.out.println("Hiiii2 ; " + getMsgApplicationId());
		}
		
		System.out.println("Hiiii2 ; " + getMsgApplicationId());
		//getEm().getTransaction().begin();
		//	setApplicationIdPrefix(getCostCenterNo()+"/"+systemType+getApplicationType()+"/"+getFormat().getYear()+"/");
		//	String nextApplicationNo=getApplicationIdPrefix()+applicationReferenceDao.getNextApplicationNo(getApplicationIdPrefix(), getEm());
		//	updateConfirmBy(nextApplicationNo,application,applicationPK,applicationDao);
		//	setMsgApplicationId(updateAppRef(nextApplicationNo,applicationReference,applicationReferencePk,applicationReferenceDao));
		//getEm().getTransaction().commit();
		setMessageType(MSG_INFO);
		
		
		setErrorMessage("INFO:- New Application Number is "+getMsgApplicationId());
		
		return "confirm";
		
		} catch (RollbackException e) {
			e.printStackTrace();
			System.out.println("ERROR MESSEGE" + e.getMessage());
			setErrorMessage(e.getMessage());
			setMessageType(MSG_ERROR);
			return ERROR;
		} catch (PersistenceException e) {
			e.printStackTrace();
			System.out.println("ERROR MESSEGE" + e.getMessage());
			setErrorMessage(e.getMessage());
			setMessageType(MSG_ERROR);
			return ERROR;
		} catch (Exception e) {
			e.printStackTrace();
			System.out.println("ERROR MESSEGE" + e.getMessage());
			setErrorMessage(e.getMessage());
			setMessageType(MSG_ERROR);
			return ERROR;
		}finally{
			//getEm().close();
			//em=null;
			//getEmf().close();
			//emf=null;
			//format=null;
			application=null;
			applicationPK=null;
			//applicationDao=null;
			//applicationReference=null;
			//applicationReferencePk=null;
			//applicationReferenceDao=null;
			//applicant
		}
	}
	
public String confirmCC() {
		
		setMessageType(MSG_NONE);
		setErrorMessage(null);
		CityMap cityMap=new CityMap();
		Application application=null;
		ApplicationPK applicationPK=null;
		//ApplicationDao applicationDao=null;
		//ApplicationReference applicationReference=null;
		//ApplicationReferencePK applicationReferencePk=null;
		//ApplicationReferenceDao applicationReferenceDao=null;
		
		try{
			setListTariffCode(getTafiffCode());
			setListTariffCatCode(getTafiffCaTCode());
			ApplicationEjb applicationEjb=new ApplicationEjb(getSessionKey("region"));
		//ApplicationReferenceEjb appReferenceEjb=new ApplicationReferenceEjb();
		ApplicationTransactionEjb transactionEjb=new ApplicationTransactionEjb(getSessionKey("region"));
		//setParameters(parameters);
		//setAppId();
		//setLoggedData();
		setState("confirm");
		//setDate(getFormat().FormatDate());
		setFormat(new Format());
		//setEmf(Persistence.createEntityManagerFactory("SMC"));
		//setEm(emf.createEntityManager());
		//applicationReferencePk=new ApplicationReferencePK();
		//applicationReferencePk.setApplicationId(getApplicationId());
		//applicationReferencePk.setDeptId(getCostCenterNo());
		applicationPK=new ApplicationPK();
		applicationPK.setApplicationId(getApplicationId());
		applicationPK.setDeptId(getCostCenterNo());
		//applicationDao = new ApplicationDao();
		//applicationReferenceDao=new ApplicationReferenceDao();
		application=applicationEjb.findByAppId(applicationPK);
		application=updateConfirmBy(application);
		//setCostCenterNo(cityMap.mapCity(costCenterNo, serviceCity));
		//setApplicationIdPrefix(getCostCenterNo()+"/"+PivPrefixType.getEST_XXX_Type(getSessionKey("smcType"))+"/"+getFormat().getYear()+"/");
		
		EstimateNumberGenerator  estimateNumber = new EstimateNumberGenerator();
		//String applicationPrefix = estimateNumber.getCommecialReferenceNumber(getFoundSource(), getCostCenterNo(), areaName, getSessionKey("smcType"));
		setApplicationIdPrefix(getCostCenterNo()+"/"+PivPrefixType.getEST_XXX_Type(getSessionKey("smcType"))+"/"+getFormat().getYear()+"/");
		
		
		setMsgApplicationId(transactionEjb.update(application, getApplicationIdPrefix()));
		////////////////
		
		getSession().put("applicationNo", getMsgApplicationId());
		getSession().put("ccn", getCostCenterNo());
		//setApplicationNumber(getMsgApplicationId());
		
		//setStatusMsg("INFO:- New Application Number is "+getMsgApplicationId()+". Please select the Application No");
		setMessageType(MSG_INFO);
		setErrorMessage("INFO:- New Application Number is "+getMsgApplicationId());
		
		
		return "confirmCC";
		
	
		} catch (RollbackException e) {
			e.printStackTrace();
			System.out.println("ERROR MESSEGE" + e.getMessage());
			setErrorMessage(e.getMessage());
			setMessageType(MSG_ERROR);
			return ERROR;
		} catch (PersistenceException e) {
			e.printStackTrace();
			System.out.println("ERROR MESSEGE" + e.getMessage());
			setErrorMessage(e.getMessage());
			setMessageType(MSG_ERROR);
			return ERROR;
		} catch (Exception e) {
			e.printStackTrace();
			System.out.println("ERROR MESSEGE" + e.getMessage());
			setErrorMessage(e.getMessage());
			setMessageType(MSG_ERROR);
			return ERROR;
		}finally{
			//getEm().close();
			//em=null;
			//getEmf().close();
			//emf=null;
			//format=null;
			application=null;
			applicationPK=null;
			//applicationDao=null;
			//applicationReference=null;
			//applicationReferencePk=null;
			//applicationReferenceDao=null;
			//applicant
		}
	}
	
	/*private void getApplicationNextNo(ApplicationDao applicationDao) {
		setApplicationIdPrefix(getCostCenterNo()+"/"+systemType+getApplicationType()+"/"+getFormat().getYear()+"/");
		//String newApplicationId=setApplicationIdFormatPrefix(getCostCenterNo(), systemType, getApplicationType(), getFormat().getYear())+applicationDao.getNextID(getEm());
		String newApplicationId=getApplicationIdPrefix()+applicationDao.getNextAppId(getApplicationIdPrefix(), em);
		
		
		
	}*/

public String	modifyDirect(){
	HttpServletRequest request = ServletActionContext.getRequest();
	setListTariffCode(getTafiffCode());
	setListTariffCatCode(getTafiffCaTCode());
	setLoggedData();
	setFormat(new Format());
	String AppNumber = request.getParameter("applicationId");
	if(AppNumber !=null){
		setApplicationId(AppNumber);
	}else{
		 setPrefix(getCostCenterNo()+"/"+PivPrefixType.getAPP_XXX_Type(getSessionKey("smcType"))+"/"+getFormat().getYear()+"/");
		 setApplicationId(getPrefix()+"xxxx");
	}
   
	setState("modifyApplication");
	setPath(modifyPath);
	
	return SUCCESS;
	
}

public String	confirmDirect(){
	setListTariffCode(getTafiffCode());
	setListTariffCatCode(getTafiffCaTCode());
	setLoggedData();
	setFormat(new Format());
    setPrefix(getCostCenterNo()+"/"+PivPrefixType.getAPP_XXX_Type(getSessionKey("smcType"))+"/"+getFormat().getYear()+"/");
	setApplicationId(getPrefix()+"xxxx");
	setState("confirmApp");
	setPath(confirmPath); 
	
	return SUCCESS;
	
}

public String	newDirect(){
	/*SpugcstmEjb ejb =new SpugcstmEjb() ;
	Spugcstm spugcstm=new Spugcstm();
	SpugcstmPK id= new SpugcstmPK();
	id.setPhase(1);
	id.setConnectionType(30);
	id.setLoopCable("abc");
	id.setCategory("AD");
	spugcstm.setId(id);
	spugcstm.setUpdatedBy("Dileepa");
	ejb.createSpugcstm(spugcstm);
	*/
	setListTariffCode(getTafiffCode());
	setListTariffCatCode(getTafiffCaTCode());
	//setAreaCodeList(getAreaCodeList());
	setLoggedData();
	setFormat(new Format());
	System.out.println("ssss : "+ getSessionKey("smcType"));
	   
	System.out.println("ssss : "+ PivPrefixType.getAPP_XXX_Type(getSessionKey("smcType")));
    setPrefix(getCostCenterNo()+"/"+PivPrefixType.getAPP_XXX_Type(getSessionKey("smcType"))+"/"+getFormat().getYear()+"/");
	setApplicationId(getPrefix()+"xxxx");
	setState("newApplication");
	setPath(newPath);
	//Radio
	setOwnership("O");
	setOccupy_OwnerCertified("Y");
	setIsGovernmentPlace("N");
	setIsAlreadyHave("N");
	//setLandDispute(hiddenLandDispute)
	setPhase("1");
	setConnectionType("30");
	setTariffCategoryCode("CP");
	setTariffCode("21");
	setIsLoanApp("N");
	//setIsLoopService(hiddenIsLoopService)
	//Radio
	setDate(getFormat().FormatDate());
	setPreparedBy(getLoggedInUserId());
	setApplicationType(getSessionKey("smcType"));
	//if(getSessionKey("smcType").equalsIgnoreCase(Constants.RUTAL_ELECTRIFICATION)){
		//return "successRE";
	//}else{
		return SUCCESS;
	//}
}

public String	viewDirect(){
	setListTariffCode(getTafiffCode());
	setListTariffCatCode(getTafiffCaTCode());
	setAreaCodeList(getAreaCodeList());
	setDistrictCodeList(getDistrictCodeList());
	setCscCodeList(getCscCodeList());
	setElectorateCodeList(getElectorateCodeList());
	setLoggedData();
	setFormat(new Format());
    setPrefix(getCostCenterNo()+"/"+PivPrefixType.getAPP_XXX_Type(getSessionKey("smcType"))+"/"+getFormat().getYear()+"/");
	setApplicationId(getPrefix()+"xxxx");
	setState("view");
	setPath(viewPath);
	setDate(getFormat().FormatDate());
	setPreparedBy(getLoggedInUserId());
	
	return SUCCESS;
	
}
	
public String	printDirect(){
	setListTariffCode(getTafiffCode());
	setListTariffCatCode(getTafiffCaTCode());
	setAreaCodeList(getAreaCodeList());
	setDistrictCodeList(getDistrictCodeList());
	setCscCodeList(getCscCodeList());
	setElectorateCodeList(getElectorateCodeList());
	setLoggedData();
	setFormat(new Format());
    setPrefix(getCostCenterNo()+"/"+PivPrefixType.getAPP_XXX_Type(getSessionKey("smcType"))+"/"+getFormat().getYear()+"/");
    setApplicationId(getPrefix()+"xxxx");
    setState("print");
	setPath(printPath);
	setDate(getFormat().FormatDate());
	setPreparedBy(getLoggedInUserId());

	return SUCCESS;
	
}

public String printApplication(){
	if(getSessionKey("smcType").equalsIgnoreCase(Constants.RUTAL_ELECTRIFICATION)){
		return "successRE";
	}else{
		return SUCCESS;
	}
}



public String	changeServiceAddDirect(){
	setListTariffCode(getTafiffCode());
	setListTariffCatCode(getTafiffCaTCode());
	setLoggedData();
	setFormat(new Format());
    setPrefix(getCostCenterNo()+"/"+PivPrefixType.getAPP_XXX_Type(getSessionKey("smcType"))+"/"+getFormat().getYear()+"/");
    setApplicationId(getPrefix()+"xxxx");
    setState("changeServiceAddDirect");
	setPath(CHANGE_SERVICE_ADD_PATH);
	setDate(getFormat().FormatDate());
	setPreparedBy(getLoggedInUserId());
	return SUCCESS;
}

public String	alterLoanAppDirect(){
	setListTariffCode(getTafiffCode());
	setListTariffCatCode(getTafiffCaTCode());
	setLoggedData();
	setFormat(new Format());
    setPrefix(getCostCenterNo()+"/"+PivPrefixType.getAPP_XXX_Type(getSessionKey("smcType"))+"/"+getFormat().getYear()+"/");
    setApplicationId(getPrefix()+"xxxx");
    setState("alterLoanAppDirect");
	setPath(ALTER_LOAN_APP);
	setDate(getFormat().FormatDate());
	setPreparedBy(getLoggedInUserId());
	return SUCCESS;
}



public String	alterLoanApp(){
	try{
		ApplicationPK  id=new ApplicationPK();
		id.setApplicationId(getApplicationId());
		id.setDeptId(getCostCenterNo());
		ApplicationEjb  applicationEjb=new ApplicationEjb(getSessionKey("region"));
		Application application= applicationEjb.findByAppId(id);
		PivDetailEjb pivDetailEjb=new PivDetailEjb(getSessionKey("region"));
		PivDetail pivDetail =null;
		System.out.println("appReferenceNo "+appReferenceNo+"#");
		pivDetail=pivDetailEjb.findByReferenceNo(appReferenceNo, "EST");
		System.out.println("pivDetail "+pivDetail+"#");
		if (pivDetail==null){
			application.setIsLoanApp(isLoanApp);
			applicationEjb.updateApplication(application);
			setListTariffCode(getTafiffCode());
			setListTariffCatCode(getTafiffCaTCode());
			if (isLoanApp.equals("Y")){
				setMessageType(MSG_DONE);
				setErrorMessage("Successfully Changed");
				setApplicationNo(appReferenceNo);
				getSession().put("applicationNo", appReferenceNo);
				return "loan";
				//return SUCCESS;
			}else{
				setMessageType(MSG_DONE);
				setErrorMessage("Successfully Changed");
				return SUCCESS;
			}
		}else{
			setListTariffCode(getTafiffCode());
			setListTariffCatCode(getTafiffCaTCode());
			setMessageType(MSG_ERROR);
			setErrorMessage("PIV 2 has issued for this application");
			return ERROR;
		}
	}catch (Exception e) {
		setListTariffCode(getTafiffCode());
		setListTariffCatCode(getTafiffCaTCode());
		setMessageType(MSG_ERROR);
		setErrorMessage("ERROR:- "+e);
		return ERROR;
	}
}


public String changeServiceAdd(){
	try{
		WiringLandDetailPK  id=new WiringLandDetailPK();
		id.setApplicationId(getApplicationId());
		id.setDeptId(getCostCenterNo());
		WiringLandDetailEjb  wiringLandDetailEjb=new WiringLandDetailEjb(getSessionKey("region"));
		WiringLandDetail wiringLandDetail= wiringLandDetailEjb.findByAppId(id);
		wiringLandDetail.setServiceStreetAddress(serviceStreetAddress);
		wiringLandDetail.setServiceSuburb(serviceSuburb);
		wiringLandDetail.setServiceCity(serviceCity);
		wiringLandDetail.setServicePostalCode(new BigDecimal(servicePostalCode));
		wiringLandDetail.setNeighboursAccNo(accountNos);
		wiringLandDetail.setTariffCode(tariffCode);
		wiringLandDetailEjb.updateWiringLandDetail(wiringLandDetail);
		setListTariffCode(getTafiffCode());
		setListTariffCatCode(getTafiffCaTCode());
		setMessageType(MSG_DONE);
		setErrorMessage("Successfully Changed");
		setState("changeServiceAddDirect");
		return SUCCESS;
		
	}catch (Exception e) {
		setListTariffCode(getTafiffCode());
		setListTariffCatCode(getTafiffCaTCode());
		setMessageType(MSG_ERROR);
		setErrorMessage("ERROR:- "+e);
		return ERROR;
	}
}

	
	private List<Bank> getAllBankList(){
		   BankEjb ejb=new BankEjb(getSessionKey("region"));
		   return ejb.getAllBanks();
	}
	
private Application updateConfirmBy(Application application) throws NumberFormatException {
		
		
		log.info("updateConfirmBy");
		//log.info(nextApplicationNo);
		//log.info(applicationPK);
		log.info(getLoggedInUserId());
		Calendar calendar = Calendar.getInstance();
		//application = applicationDao.findByAppId(applicationPK, getEm());
		//application.setApplicationNo(nextApplicationNo);
		application.setConfirmedBy(getLoggedInUserId());
		application.setStatus(confirmedStatus);
		application.setConfirmedDate(calendar.getTime());
		application.setConfirmedTime(getFormat().FormatTime());
		//applicationDao.updateApplicantNoCommit(application, getEm());
		//log.info(applicationDao.getAll(getEm()));
		//log.info(applicationDao);
		//applicationDao=null;
		//application=null;
		//calendar=null;
		return application;
	}
	
	/*private String updateAppRef(String nextApplicationNo,ApplicationReference applicationReference,ApplicationReferencePK applicationReferencePK,ApplicationReferenceDao applicationReferenceDao) throws NumberFormatException{
		log.info("updateAppRef");
		log.info(applicationReferencePK);
		log.info("newApplicationNo "+nextApplicationNo);
		//applicationReferencePK.setApplicationId(getApplicationId());
		//applicationReferencePK.setDeptId(getCostCenterNo());
		applicationReference=new ApplicationReference(applicationReferencePK,nextApplicationNo,null);
		applicationReferenceDao=new ApplicationReferenceDao();
		applicationReferenceDao.createApplicationReferenceNoCommit(applicationReference, getEm());
		return nextApplicationNo;
		
		//applicationReference=null;
		//applicationReferenceDao=null;
	}*/
	
	

	public String close() {
		setLoggedData();
		//getEm().close(); 
		//getEmf().close(); 
		return "close";
	}
	

	/*
	 * private void saveApplicationData(){ ApplicationDao applicationDao= new
	 * ApplicationDao(emf); Calendar calendar = Calendar.getInstance();
	 * Application application=new
	 * Application(Long.parseLong(getApplicationId()), getApplicationType(),
	 * calendar.getTime(), getCostCenterNo(), getIdNumber(),
	 * getLoggedInUserId(), getApprovedBy(), getStatus(), getLoggedInUserId(),
	 * calendar.getTime(), format.FormatTime(), null, null, null,
	 * getDescription()); applicationDao.createApplication(application); }
	 */

	
	
	private Application createApplicationObject ()throws NumberFormatException {
		log.info("3333333333333333333333333333333333333333333333");
		setStatus(newStatus);
		ApplicationPK id=new ApplicationPK();
		id.setApplicationId(getApplicationId());
		id.setDeptId(getCostCenterNo());
		Calendar calendar = Calendar.getInstance();
		setSubmitDate(calendar.getTime());
		setConfirmDate(null);
		setConfirmTime(null);
		setAllocatedBy(null);
		setAllocatedTo(null);
		setAllocatedDate(null);
		setAllocatedTime(null);
		setUpdUser(null);
		setUpdDate(null);
		setUpdTime(null);
		setPreparedBy(getSessionKey("userName"));
		setLoggedInUserId(getSessionKey("userName"));
		setAddUser(getLoggedInUserId());
		setAddDate(calendar.getTime());
		setAddTime(getFormat().FormatTime());
		Application application=new Application();
		log.info("3");
		application.setId(id);
		application.setApplicationNo(null);
		//application.setApplicationType(getApplicationType());
		
		application.setApplicationType(getSessionKey("smcType"));
		application.setApplicationSubType(applicationSubType);
		//log.info("4");
		log.info("33333333333333333333333333 " + getDuration() );
		//if(getDuration() != null){
			//application.setDuration(new BigDecimal(getDuration()));
		//}
		//application.setDurationInDays(new BigDecimal(duration));
		//Duration
	/*	if (!applicationSubType.equals("PM")){
			application.setDurationType(durationType);
			//log.info("5");
			//System.out.println(getDuration().length());
			System.out.println(duration.length());
			if (duration.length()!=0)
				application.setDuration(new BigDecimal(duration));
			System.out.println(duration);
			//log.info("6");
			if (durationType.equals("D")){
				application.setDurationInDays(new BigDecimal(duration));
				System.out.println(new BigDecimal(duration));
			}else if (durationType.equals("M")) {
				application.setDurationInDays(new BigDecimal(Integer.parseInt(duration)*30));
				System.out.println(new BigDecimal(Integer.parseInt(duration)*30));
			}else if (durationType.equals("Y")){
				application.setDurationInDays(new BigDecimal(Integer.parseInt(duration)*365));
				System.out.println(new BigDecimal(Integer.parseInt(duration)*365));
			}
			//log.info("7");
			//if (duration!=null)
			application.setFromDate(format.StrToDate(fromDate));
			application.setToDate(format.StrToDate(toDate));
			//log.info("8");
		
		}*/
		//Duration
		application.setIsLoanApp(isLoanApp);
		application.setSubmitDate(getSubmitDate());
		application.setIdNo(getIdNo());
		application.setPreparedBy(getPreparedBy());
		application.setConfirmedBy(getConfirmedBy());
		application.setConfirmedDate(null);
		application.setConfirmedTime(null);
		application.setAllocatedBy(null);
		application.setAllocatedTo(null);
		application.setAllocatedDate(null);
		application.setAllocatedTime(null);
		application.setStatus(getStatus());
		application.setAddUser(getLoggedInUserId());
		application.setAddDate(calendar.getTime());
		application.setAddTime(getFormat().FormatTime());
		application.setUpdUser(null);
		application.setUpdDate(null);
		application.setUpdTime(null);
		application.setDescription (getDescription());
		
		/*Application application = new Application(id, null, getApplicationType(), getSubmitDate(), getIdNo(),
		getPreparedBy(), getConfirmedBy(),getConfirmDate(),getConfirmTime(), getAllocatedBy(),getAllocatedTo(),getAllocatedDate(),getAllocatedTime(), getStatus(),
		getAddUser(), getAddDate(), getAddTime(),
		getUpdUser(), getUpdDate(), getUpdTime(), getDescription());
		 */
		
		/*Application application = new Application(newApplicationId, getApplicationType(), getSubmitDate(), getCostCenterNo(), getIdNo(),
				getPreparedBy(), getApprovedBy(),getConfirmDate(),getConfirmTime(), getAllocatedBy(),getAllocatedTo(),getAllocatedDate(),getAllocatedTime(), getStatus(),
				getAddUser(), getAddDate(), getAddTime(),
				getUpdUser(), getUpdDate(), getUpdTime(), getDescription());*/
		/*Application application = new Application(getApplicationType(), getSubmitDate(), getCostCenterNo(), getIdNo(),
				getPreparedBy(), getApprovedBy(),getConfirmDate(),getConfirmTime(), getAllocatedBy(),getAllocatedTo(),getAllocatedDate(),getAllocatedTime(), getStatus(),
				getAddUser(), getAddDate(), getAddTime(),
				getUpdUser(), getUpdDate(), getUpdTime(), getDescription());*/
				
		return application;
		
	}

	/*private void saveWiringLandDetail() throws NumberFormatException {
		
		showGetterData();
		WiringLandDetailPK  wiringLandDetailPK=new WiringLandDetailPK();
		wiringLandDetailPK.setApplicationId(getApplicationId());
		wiringLandDetailPK.setDeptId(getCostCenterNo());
		
		BigDecimal bdServicePostalCode = 
			getServicePostalCode().trim().length() == 0 ? new BigDecimal(0)
				: new BigDecimal(getServicePostalCode());
		BigDecimal bdNoOfBulbs = getNoOfBulbs().trim().length() == 0 ? new BigDecimal(0)
				: new BigDecimal(getNoOfBulbs());
		BigDecimal bdNoOfFans = getNoOfFans().trim().length() == 0 ? new BigDecimal(0)
				: new BigDecimal(getNoOfFans());
		BigDecimal bdNoOfPlughs_5A = getNoOfPlughs_5A().trim().length() == 0 ? new BigDecimal(0)
				: new BigDecimal(getNoOfPlughs_5A());
		BigDecimal bdNoOfPlughs_15A = getNoOfPlughs_5A().trim().length() == 0 ? new BigDecimal(0)
				: new BigDecimal(getNoOfPlughs_15A());
		BigDecimal bdMotorsTotal = getNoOfPlughs_5A().trim().length() == 0 ? new BigDecimal(0)
				: new BigDecimal(getMotorsTotal());
		BigDecimal bdWeldingPlant = getNoOfPlughs_5A().trim().length() == 0 ? new BigDecimal(0)
				: new BigDecimal(getWeldingPlant());
		BigDecimal bdMetalCrusher = getNoOfPlughs_5A().trim().length() == 0 ? new BigDecimal(0)
				: new BigDecimal(getMetalCrusher());
		BigDecimal bdSawMills = getNoOfPlughs_5A().trim().length() == 0 ? new BigDecimal(0)
				: new BigDecimal(getSawMills());
		// BigDecimal bdTariffCode = getNoOfPlughs_5A().trim().length()==0? new
		// BigDecimal(0):new BigDecimal(getTariffCode());
		showConvertedData();
		/*WiringLandDetail wiringLandDetail = new WiringLandDetail(wiringLandDetailPK, getServiceStreetAddress(),
				getServiceSuburb(), getServiceCity(), bdServicePostalCode,
				getOwnership(), getOccupy_OwnerCertified(),
				getIsGovernmentPlace(), getLandDispute(), getAssessmentNo(),
				getNeighboursAccNo(), bdNoOfBulbs, bdNoOfFans, bdNoOfPlughs_5A,
				bdNoOfPlughs_15A, bdMotorsTotal, bdWeldingPlant,
				bdMetalCrusher, bdSawMills, new BigDecimal(getPhase()),
				new BigDecimal(getTariffCode()), new BigDecimal(
						getConnectionType()), getCustomerCategory(),
				getIsLoopService());
				*/
		/*WiringLandDetail wiringLandDetail = new WiringLandDetail(newApplicationId, getServiceStreetAddress(),
				getServiceSuburb(), getServiceCity(), bdServicePostalCode,
				getOwnership(), getOccupy_OwnerCertified(),
				getIsGovernmentPlace(), getLandDispute(), getAssessmentNo(),
				getNeighboursAccNo(), bdNoOfBulbs, bdNoOfFans, bdNoOfPlughs_5A,
				bdNoOfPlughs_15A, bdMotorsTotal, bdWeldingPlant,
				bdMetalCrusher, bdSawMills, new BigDecimal(getPhase()),
				new BigDecimal(getTariffCode()), new BigDecimal(
						getConnectionType()), getCustomerCategory(),
				getIsLoopService());*/
		
		
		/*WiringLandDetailDao wiringLandDetailDao = new WiringLandDetailDao();
		wiringLandDetailDao.createWiringLandDetailNocommint(wiringLandDetail,
				getEm());
		wiringLandDetailDao=null;
		wiringLandDetailPK=null;
		wiringLandDetail=null;
		
	}*/
	
private WiringLandDetail createWiringLandObject() throws NumberFormatException {
		
		showGetterData();
		WiringLandDetailPK  id=new WiringLandDetailPK();
		id.setApplicationId(getApplicationId());
		id.setDeptId(getCostCenterNo());
		log.info("1");
		
		BigDecimal bdServicePostalCode = 
			getServicePostalCode().trim().length() == 0 ? new BigDecimal(0)
				: new BigDecimal(getServicePostalCode());
		//if (getNoOfBulbs()==null ) {
		//	BigDecimal bdNoOfBulbs = getNoOfBulbs().trim().length() == 0 ? new BigDecimal(0)
		//		: new BigDecimal(getNoOfBulbs());
		//}else bdNoOfBulbs=new BigDecimal(0);
		
		log.info("2");
		
		//showConvertedData();
		WiringLandDetail wiringLandDetail=new WiringLandDetail();
		wiringLandDetail.setId(id);
		wiringLandDetail.setServiceStreetAddress(getServiceStreetAddress() == null ?  Constants.DEFAULT_STRING1 : getServiceStreetAddress());
		wiringLandDetail.setServiceSuburb(getServiceSuburb()  != null ? getServiceSuburb() : Constants.DEFAULT_STRING1);
		wiringLandDetail.setServiceCity( getServiceCity()  != null ? getServiceCity() : Constants.DEFAULT_STRING1); 
		if (servicePostalCode!=null){
			wiringLandDetail.setServicePostalCode(bdServicePostalCode);
		}else wiringLandDetail.setServicePostalCode(new BigDecimal(0));
		
		wiringLandDetail.setOwnership(getOwnership() != null ?  getOwnership() : Constants.DEFAULT_OWNERSHIP);
		wiringLandDetail.setOccupyOwnerCertified(getOccupy_OwnerCertified() != null ?  getOccupy_OwnerCertified() : Constants.DEFAULT_OWNER_CERTIFIED );
		wiringLandDetail.setIsGovernmentPlace(getIsGovernmentPlace() != null ? getIsGovernmentPlace(): Constants.DEFAULT_IS_GOV_PLACE );
		//wiringLandDetail.setLandDispute(getLandDispute());
		wiringLandDetail.setAssessmentNo(getAssessmentNo());
		
		if(getPhase() != null){
			wiringLandDetail.setPhase(new BigDecimal(getPhase()));
		}else{
			wiringLandDetail.setPhase(new BigDecimal(0));
		}
		
		wiringLandDetail.setTariffCode(getTariffCode() != null ?  getTariffCode() : Constants.DEFAULT_TARIFF_CODE);
		wiringLandDetail.setTariffCatCode(getTariffCategoryCode() != null ?  getTariffCategoryCode() : Constants.DEFAULT_STRING1);
		if(getConnectionType() != null){
			wiringLandDetail.setConnectionType(new BigDecimal(getConnectionType()));
		}else{
			wiringLandDetail.setConnectionType(new BigDecimal(0));
		}
	
		wiringLandDetail.setCustomerCategory(getCustomerCategory() != null ?  getCustomerCategory() : Constants.DEFAULT_STRING1);
		log.info("4");
		log.info(getCustomerType());
		wiringLandDetail.setCustomerType(getCustomerType() != null ?  getCustomerType() : Constants.DEFAULT_STRING1);
		if(getDemand() != null){
			if (getDemand().equalsIgnoreCase(Constants.DEFAULT_STRING)){
				setDemand("0");
			}else wiringLandDetail.setDemand(Long.parseLong(getDemand()));
		}else{
			setDemand("0");
		}
		//wiringLandDetail.setIsLoopService(getIsLoopService());
		
		/*WiringLandDetail wiringLandDetail = new WiringLandDetail(newApplicationId, getServiceStreetAddress(),
				getServiceSuburb(), getServiceCity(), bdServicePostalCode,
				getOwnership(), getOccupy_OwnerCertified(),
				getIsGovernmentPlace(), getLandDispute(), getAssessmentNo(),
				getNeighboursAccNo(), bdNoOfBulbs, bdNoOfFans, bdNoOfPlughs_5A,
				bdNoOfPlughs_15A, bdMotorsTotal, bdWeldingPlant,
				bdMetalCrusher, bdSawMills, new BigDecimal(getPhase()),
				new BigDecimal(getTariffCode()), new BigDecimal(
						getConnectionType()), getCustomerCategory(),
				getIsLoopService());*/
		return wiringLandDetail;
				
	}

private WiringLandDetailCon createWiringLandConObject() throws NumberFormatException {
	
	showGetterData();
	WiringLandDetailConPK  id=new WiringLandDetailConPK();
	id.setApplicationId(getApplicationId());
	id.setDeptId(getCostCenterNo());
	log.info("1");
	
	BigDecimal bdServicePostalCode = 
		getServicePostalCode().trim().length() == 0 ? new BigDecimal(0)
			: new BigDecimal(getServicePostalCode());
	//if (getNoOfBulbs()==null ) {
	//	BigDecimal bdNoOfBulbs = getNoOfBulbs().trim().length() == 0 ? new BigDecimal(0)
	//		: new BigDecimal(getNoOfBulbs());
	//}else bdNoOfBulbs=new BigDecimal(0);
	
	log.info("2");
	
	//showConvertedData();
	WiringLandDetailCon wiringLandDetail=new WiringLandDetailCon();
	
	wiringLandDetail.setId(id);
	wiringLandDetail.setAgaDivision(getAgaDivision() != null && getAgaDivision().trim().length() == 0 ? Constants.DEFAULT_STRING: getAgaDivision());
	wiringLandDetail.setAreaCode(getAreaName());
	if(getCapacityOfServices() == null){
		setCapacityOfServices("0");
	}
	wiringLandDetail.setCapacityofService(getCapacityOfServices().trim().length() == 0 ? new BigDecimal(0): new BigDecimal(getCapacityOfServices()));
	/*if (servicePostalCode!=null){
		wiringLandDetail.setServicePostalCode(getS);
	}else wiringLandDetail.setServicePostalCode(new BigDecimal(0));*/
	wiringLandDetail.setAcountNos(getAccountNos());
	wiringLandDetail.setConsumerRefNo(getConsumerRef());
	wiringLandDetail.setDesOfPremises(getPremises());
	wiringLandDetail.setDevSec(getDevSec());
	//wiringLandDetail.setLandDispute(getLandDispute());
	wiringLandDetail.setElectorate(getElectrate().trim().length() == 0 ? Constants.DEFAULT_STRING: getElectrate());
	wiringLandDetail.setFundSource(getFoundSource());
	
	wiringLandDetail.setGsDivision(getGsDivision().trim().length() == 0 ? Constants.DEFAULT_STRING: getGsDivision());
	wiringLandDetail.setIsElectricityhave(getIsAlreadyHave());
	if(getNoOfMeterPoints() == null){
		setNoOfMeterPoints("0");
	}
	wiringLandDetail.setNoOfMeterPoints(getNoOfMeterPoints().trim().length() == 0 ? new BigDecimal(0): new BigDecimal(getNoOfMeterPoints()));
	wiringLandDetail.setRepresentative(getRepres1().trim().length() == 0 ? Constants.DEFAULT_STRING: getRepres1());
	//wiringLandDetail.setSchemeName(getSchemaName().trim().length() == 0 ? Constants.DEFAULT_STRING: getSchemaName());
	log.info("4");
	log.info(getCustomerType());
	//wiringLandDetail.setSchemeNo(getSchemaNo().trim().length() == 0 ? Constants.DEFAULT_STRING: getSchemaNo());
	if(getRepres2()!= null){
		wiringLandDetail.setRepresentative2(getRepres2().trim().length() == 0 ? Constants.DEFAULT_STRING: getRepres2());
	}
	if(getRepres1Con()!= null){
		wiringLandDetail.setRepContact1(getRepres1Con().trim().length() == 0 ? Constants.DEFAULT_STRING: getRepres1Con());
	}
	if(getRepres1Con()!= null){
		wiringLandDetail.setRepContact2(getRepres2Con().trim().length() == 0 ? Constants.DEFAULT_STRING: getRepres2Con());
	}
	if(getCscname()!= null){
		wiringLandDetail.setServiceDepoName(getCscname().trim().length() == 0 ? Constants.DEFAULT_STRING: getCscname());
	}
	if(getDistrict()!= null){
		wiringLandDetail.setDistrict(getDistrict().trim().length() == 0 ? Constants.DEFAULT_STRING: getDistrict());
	}
	wiringLandDetail.setSchemeName(getSchemaName() != null && getSchemaName().trim().length() == 0 ? Constants.DEFAULT_STRING: getSchemaName());	
	wiringLandDetail.setSchemeNo(getSchemaNo() != null &&  getSchemaNo().trim().length() == 0 ? Constants.DEFAULT_STRING: getSchemaNo());
	//wiringLandDetail.setIsLoopService(getIsLoopService());
	
	/*WiringLandDetail wiringLandDetail = new WiringLandDetail(newApplicationId, getServiceStreetAddress(),
			getServiceSuburb(), getServiceCity(), bdServicePostalCode,
			getOwnership(), getOccupy_OwnerCertified(),
			getIsGovernmentPlace(), getLandDispute(), getAssessmentNo(),
			getNeighboursAccNo(), bdNoOfBulbs, bdNoOfFans, bdNoOfPlughs_5A,
			bdNoOfPlughs_15A, bdMotorsTotal, bdWeldingPlant,
			bdMetalCrusher, bdSawMills, new BigDecimal(getPhase()),
			new BigDecimal(getTariffCode()), new BigDecimal(osa
					getConnectionType()), getCustomerCategory(),
			getIsLoopService());*/
	return wiringLandDetail;
			
}
	
	private Application updateApplicationModel(Application application) throws NumberFormatException{
		Calendar calendar = Calendar.getInstance();
		application.setIsLoanApp(isLoanApp);
		//application.setApplicationType(getApplicationType());
		application.setApplicationType(getSessionKey("smcType"));
		//application.setApplicationSubType(applicationSubType);
		application.setApplicationSubType(applicationSubType);
		/*if(fromDate != null){
			application.setFromDate(format.StrToDate(fromDate));
		}
		if(toDate != null){
			application.setToDate(format.StrToDate(toDate));
		}*/
	
		
		application.setConfirmedBy(getConfirmedBy());
		application.setStatus(getStatus());
		application.setDescription(getDescription());
		application.setUpdUser(getLoggedInUserId());
		application.setUpdDate(calendar.getTime());
		application.setUpdTime(getFormat().FormatTime());
		//Duration
		/*if (!applicationSubType.equals("PM")){
			application.setDurationType(durationType);
			//log.info("5");
			//System.out.println(getDuration().length());
			System.out.println(duration.length());
			if (duration.length()!=0)
				application.setDuration(new BigDecimal(duration));
			System.out.println(duration);
			//log.info("6");
			if (durationType.equals("D")){
				application.setDurationInDays(new BigDecimal(duration));
				System.out.println(new BigDecimal(duration));
			}else if (durationType.equals("M")) {
				application.setDurationInDays(new BigDecimal(Integer.parseInt(duration)*30));
				System.out.println(new BigDecimal(Integer.parseInt(duration)*30));
			}else if (durationType.equals("Y")){
				application.setDurationInDays(new BigDecimal(Integer.parseInt(duration)*365));
				System.out.println(new BigDecimal(Integer.parseInt(duration)*365));
			}
			//log.info("7");
			//if (duration!=null)
			application.setFromDate(format.StrToDate(fromDate));
			application.setToDate(format.StrToDate(toDate));
			//log.info("8");
		
		}*/
		return application;
		

	}

	
	
private WiringLandDetail updateWiringLandDetailModel(WiringLandDetail wiringLandDetail) throws NumberFormatException{
		
	    
		wiringLandDetail.setServiceStreetAddress(getServiceStreetAddress() == null ? Constants.DEFAULT_STRING1 : getServiceStreetAddress());
		
		wiringLandDetail.setServiceCity(getServiceCity()  == null ? Constants.DEFAULT_STRING1 : getServiceCity());
		
		wiringLandDetail.setServiceSuburb(getServiceSuburb() == null ? Constants.DEFAULT_STRING1 : getServiceSuburb());
		//System.out.println("hii 13" + getServicePostalCode() );
		//if(getServicePostalCode() != null)
			//wiringLandDetail.setServicePostalCode(new BigDecimal(getServicePostalCode())); 
		
		wiringLandDetail.setAssessmentNo(getAssessmentNo());
		
		wiringLandDetail.setTariffCode(getTariffCode()  == null ? Constants.DEFAULT_TARIFF_CODE : getTariffCode());
		
		wiringLandDetail.setTariffCatCode(getTariffCategoryCode()  == null ? Constants.DEFAULT_STRING1: getTariffCategoryCode());
		
		wiringLandDetail.setCustomerCategory(getCustomerCategory()  == null ? Constants.DEFAULT_STRING1: getCustomerCategory());
		
		wiringLandDetail.setCustomerType(getCustomerType() == null ? Constants.DEFAULT_STRING1: getCustomerType());
		
		wiringLandDetail.setOwnership(getOwnership() == null ? Constants.DEFAULT_OWNERSHIP: getOwnership());
		
		wiringLandDetail.setOccupyOwnerCertified(getOccupy_OwnerCertified() == null ? Constants.DEFAULT_OWNER_CERTIFIED: getOccupy_OwnerCertified());
		
		wiringLandDetail.setIsGovernmentPlace(getIsGovernmentPlace() == null ? Constants.DEFAULT_IS_GOV_PLACE: getIsGovernmentPlace());
		//wiringLandDetail.setLandDispute(getLandDispute());
		wiringLandDetail.setPhase(getPhase() == null ? new BigDecimal(0) : new BigDecimal(getPhase()));
		wiringLandDetail.setConnectionType(getConnectionType() == null ? new BigDecimal(0) : new BigDecimal(getConnectionType()));
		
		if(getDemand() != null){
			if (getDemand().equalsIgnoreCase(Constants.DEFAULT_STRING)){
				setDemand("0");
			}else wiringLandDetail.setDemand(Long.parseLong(getDemand()));
		}else{
			setDemand("0");
		}
		
		
		return wiringLandDetail;
		
	}

private WiringLandDetailCon updateWiringLandDetailConModel(WiringLandDetailCon wiringLandDetailcon) throws NumberFormatException{
	

	wiringLandDetailcon.setAgaDivision(getAgaDivision().trim().length() == 0 ? Constants.DEFAULT_STRING: getAgaDivision());
	wiringLandDetailcon.setAreaCode(getAreaName());
	if(getCapacityOfServices() != null ){
		wiringLandDetailcon.setCapacityofService(getCapacityOfServices().trim().length() == 0 ? new BigDecimal(0): new BigDecimal(getCapacityOfServices()));	
	}else{
		wiringLandDetailcon.setCapacityofService(new BigDecimal(0));	
	}
	
	wiringLandDetailcon.setConsumerRefNo(getConsumerRef());
	wiringLandDetailcon.setDesOfPremises(getPremises());
	wiringLandDetailcon.setDevSec(getDevSec());	
	wiringLandDetailcon.setElectorate(getElectrate().trim().length() == 0 ? Constants.DEFAULT_STRING: getElectrate());
	wiringLandDetailcon.setFundSource(getFoundSource());	
	wiringLandDetailcon.setGsDivision(getGsDivision().trim().length() == 0 ? Constants.DEFAULT_STRING: getGsDivision());
	wiringLandDetailcon.setIsElectricityhave(getIsAlreadyHave());
	wiringLandDetailcon.setAcountNos(getAccountNos());
	
	if(getNoOfMeterPoints() != null){
		wiringLandDetailcon.setNoOfMeterPoints(getNoOfMeterPoints().trim().length() == 0 ? new BigDecimal(0): new BigDecimal(getNoOfMeterPoints()));
	}else{
		wiringLandDetailcon.setNoOfMeterPoints(new BigDecimal(0));
	}
	
	wiringLandDetailcon.setRepresentative(getRepres1().trim().length() == 0 ? Constants.DEFAULT_STRING: getRepres1());
	wiringLandDetailcon.setSchemeName(getSchemaName() != null && getSchemaName().trim().length() == 0 ? Constants.DEFAULT_STRING: getSchemaName());	
	wiringLandDetailcon.setSchemeNo(getSchemaNo() != null && getSchemaNo().trim().length() == 0 ? Constants.DEFAULT_STRING: getSchemaNo());
	if(getRepres2() != null){
		wiringLandDetailcon.setRepresentative2(getRepres2().trim().length() == 0 ? Constants.DEFAULT_STRING: getRepres2());
	}
	if(getRepres1Con() != null){
		wiringLandDetailcon.setRepContact1(getRepres1Con().trim().length() == 0 ? Constants.DEFAULT_STRING: getRepres1Con());
	}
	if(getRepres2Con() != null){
		wiringLandDetailcon.setRepContact2(getRepres2Con().trim().length() == 0 ? Constants.DEFAULT_STRING: getRepres2Con());
	}
	if(getCscname() != null){
		wiringLandDetailcon.setServiceDepoName(getCscname().trim().length() == 0 ? Constants.DEFAULT_STRING: getCscname());
	}
	if(getDistrict()!= null){
		wiringLandDetailcon.setDistrict(getDistrict().trim().length() == 0 ? Constants.DEFAULT_STRING: getDistrict());
	}
	return wiringLandDetailcon;
	
}

	/*private Applicant findById(String id_no) {
		log.info("findById " + id_no);
		ApplicantDao applicantDao = new ApplicantDao(getEmf());
		// log.info("found " + applicantDao.findById(id));
		return applicantDao.findById(id_no);
	}*/

	

	/*private Application findByAppId(Long application_Id) {
		log.info("findAppId " + application_Id);
		ApplicationDao applicationDao = new ApplicationDao(getEmf());
		return applicationDao.findByAppId(application_Id);
	}*/

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
		/*setDurationType(application.getDurationType());
		if (application.getDuration()!=null)
			setDuration(application.getDuration().toString());
		if (application.getFromDate()!=null)
			setFromDate(application.getFromDate().toString());
		if (application.getToDate()!=null)
			setToDate(application.getToDate().toString());*/
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
		//if(application.getDuration() != null){
			//String xxx = application.getDuration().toString();
			
		//}
		//String dStr = "";
		//if(application.getDuration()!= null){
			//dStr = application.getDuration().toString();
		//}
		//setDuration(dStr);
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
		
		//log.info("wiringLandDetail.getNoOfBulbs() "	+ wiringLandDetail.getNoOfBulbs());
		

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
		setDemand((wiringLandDetail.getDemand() != null ? wiringLandDetail.getDemand().toString() : Constants.DEFAULT_STRING));
		
	
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
	
	private void setWiringLandDetailCon(WiringLandDetailCon wiringLandDetailcon) throws NumberFormatException{
		setAgaDivision(wiringLandDetailcon.getAgaDivision());
		setAreaName((wiringLandDetailcon.getAreaCode()));
		setCapacityOfServices((wiringLandDetailcon.getCapacityofService() == null ? Constants.DEFAULT_STRING : wiringLandDetailcon.getCapacityofService().toString()));
		setConsumerRef(wiringLandDetailcon.getConsumerRefNo() == null ? Constants.DEFAULT_STRING: wiringLandDetailcon.getConsumerRefNo());
		setDesPremises(wiringLandDetailcon.getDesOfPremises() == null ? Constants.DEFAULT_STRING: wiringLandDetailcon.getDesOfPremises());
		setDevSec(wiringLandDetailcon.getDevSec() == null ? Constants.DEFAULT_STRING: wiringLandDetailcon.getDevSec());
		setElectrate(wiringLandDetailcon.getElectorate() == null ? Constants.DEFAULT_STRING: wiringLandDetailcon.getElectorate());
		setFoundSource(wiringLandDetailcon.getFundSource() == null ? Constants.DEFAULT_STRING: wiringLandDetailcon.getFundSource());
		setGsDivision(wiringLandDetailcon.getGsDivision() == null ? Constants.DEFAULT_STRING: wiringLandDetailcon.getGsDivision());
		setIsAlreadyHave(wiringLandDetailcon.getIsElectricityhave());
		//setHiddenIsAleardyhaveElectricity(wiringLandDetailcon.getIsElectricityhave());
		setNoOfMeterPoints((wiringLandDetailcon.getNoOfMeterPoints() == null ? Constants.DEFAULT_STRING: wiringLandDetailcon.getNoOfMeterPoints().toString()));
		setRepres1((wiringLandDetailcon.getRepresentative() == null ? Constants.DEFAULT_STRING: wiringLandDetailcon.getRepresentative()));
		setSchemaName((wiringLandDetailcon.getSchemeName() == null ? Constants.DEFAULT_STRING: wiringLandDetailcon.getSchemeName()));
		setSchemaNo((wiringLandDetailcon.getSchemeNo() == null ? Constants.DEFAULT_STRING: wiringLandDetailcon.getSchemeNo().toString()));
		setRepres2((wiringLandDetailcon.getRepresentative2() == null ? Constants.DEFAULT_STRING: wiringLandDetailcon.getRepresentative2()));
		setRepres1Con((wiringLandDetailcon.getRepContact1() == null ? Constants.DEFAULT_STRING: wiringLandDetailcon.getRepContact1()));
		setRepres2Con((wiringLandDetailcon.getRepContact2() == null ? Constants.DEFAULT_STRING: wiringLandDetailcon.getRepContact2()));
		setCscname((wiringLandDetailcon.getServiceDepoName() == null ? Constants.DEFAULT_STRING: wiringLandDetailcon.getServiceDepoName()));
		setAccountNos(wiringLandDetailcon.getAcountNos());
		setDistrict((wiringLandDetailcon.getDistrict() == null ? Constants.DEFAULT_STRING: wiringLandDetailcon.getDistrict()));
		//setDistrictCode((wiringLandDetailcon.get == null ? Constants.DEFAULT_STRING: wiringLandDetailcon.getDistrict()));
		//setElectorateCode((wiringLandDetailcon.getDistrict() == null ? Constants.DEFAULT_STRING: wiringLandDetailcon.getDistrict()));
		//setCscCode((wiringLandDetailcon.getDistrict() == null ? Constants.DEFAULT_STRING: wiringLandDetailcon.getDistrict()));
	}
	/*private WiringLandDetail findWiringLandDetailByAppId(Long application_Id) {
		WiringLandDetailDao wiringLandDetailDao = new WiringLandDetailDao(
				getEmf());
		return wiringLandDetailDao.findByAppId(application_Id);
	}*/
	
	private void setAppRefDetail(ApplicationReference applicationReference){
		setAppReferenceNo(applicationReference.getApplicationNo());
	}
	
	/*private ApplicationReference findAppRefDetailByAppId(Long application_Id){
		ApplicationReferenceDao applicationReferenceDao=new ApplicationReferenceDao(getEmf());
		return applicationReferenceDao.findByAppId(application_Id);
		 
	}*/
	
	
	
	private Application updateAllocationDetail(Application application){
		Calendar calendar = Calendar.getInstance();
		setAllocatedBy(getLoggedInUserId());
		setAllocatedDate(calendar.getTime());
		setAllocatedTime(getFormat().FormatTime());
		setStatus(allocatedStatus);
		application.setAllocatedBy(getAllocatedBy());
		application.setAllocatedTo(getAllocatedTo());
		application.setAllocatedDate(getAllocatedDate());
		application.setAllocatedTime(getAllocatedTime());
		application.setStatus(getStatus());
		//calendar=null;
		//applicationDao=null;
		//application=null;
		return application;
	
	}
	/*@SuppressWarnings("unchecked")
	public void testDali() {
		EntityManagerFactory emf = Persistence
				.createEntityManagerFactory("SMC");
		EntityManager em = emf.createEntityManager();
		// listPcesthtt = em.createNativeQuery("select * fromPcesthtt",
		// Pcesthtt.class).getResultList();
		listPcesthtt2 = em.createQuery(
				"select i from Pcesthtt  i where i.id.estimateNo=:esNo")
				.setParameter("esNo", "43243232            ").getResultList();
		System.out.println("listPcesthtt2" + listPcesthtt2);
		for (Pcesthtt pcesthtt : listPcesthtt2) {
			txt = txt + pcesthtt.getId().getEstimateNo() + " "
					+ pcesthtt.getId().getDeptId() + " "
					+ pcesthtt.getId().getRevNo() + " " + pcesthtt.getStdCost();

		}
	}*/


	

	public String getIdNo() {
		return idNo;
	}
	
	public List<String> getAreaCodeList() {
		//areaCodeList.add("--Select--");
		if(areaCodeList != null ){
			//areaCodeList.add("--Select--");
			ApplicationTransactionEjb transactionEjb=new ApplicationTransactionEjb(getSessionKey("region"));
			List<String> areaCodes = transactionEjb.findAreaCodeNames(getCostCenterNo(), getSessionKey("region"));
			//List<String> areaCodes =new ArrayList<String>();
			/*Map<String,String> areaCodeId = transactionEjb.findAreaCodeIDs(getCostCenterNo(), getSessionKey("region"));
			if(areaCodeId != null && areaCodeId.size() > 0){
				putSessionKey("areaCodes", areaCodeId);
			}*/
			if(areaCodes == null ){
				areaCodeList = new ArrayList<String>();
				if(getCostCenterNo() != null){
					//areaCodeList.add("--Select--");
					areaCodeList.add(getCostCenterNo());
					
				}
			}else{
				//areaCodeList.add("--Select--");
				areaCodeList.addAll(areaCodes);
				
			}
		}
		
		return areaCodeList;
		
	}

	public void setAreaCodeList(List<String> areaCodeList) {
		this.areaCodeList = areaCodeList;
	}

	public List<String> getApplicationTypes() {
		
		
		
		return applicationTypes;
	}

	public void setApplicationTypes(List<String> applicationTypes) {
		this.applicationTypes = applicationTypes;
		
	}
	  private void putSessionKey(String key, String value ){
		   getSession().put(key, value);
	   
	   }
	public List<String> getPremisesList() {
		premisesList = new ArrayList<String>();
		premisesList.add("Industry            ");
		premisesList.add("Land Sales          ");
		premisesList.add("Hotel               ");
		premisesList.add("Shop                ");
		premisesList.add("Office Complex      ");
		premisesList.add("Shopping Complex    ");
		premisesList.add("Apartment           ");
		premisesList.add("Other               ");
		
		return premisesList;
	}

	public void setPremisesList(List<String> premisesList) {
		this.premisesList = premisesList;
	}

	public void setIdNo(String idNo) {
		this.idNo = idNo;

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
		//this.applicationType = applicationType;
		this.applicationType = applicationType;
		
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

	public String getCostCenterNo() {
		return costCenterNo;
	}

	public void setCostCenterNo(String costCenterNo) {
		this.costCenterNo = costCenterNo;
	}

	public String getCsc() {
		return csc;
	}

	public void setCsc(String csc) {
		this.csc = csc;
	}

	public String getIdType() {
		return idType;
	}

	public void setIdType(String idType) {
		this.idType = idType;
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

	public void setOccupy_OwnerCertified(String occupyOwnerCertified) {
		occupy_OwnerCertified = occupyOwnerCertified;
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

	
	public String getStatus() {
		return status;
	}

	public void setStatus(String status) {
		this.status = status;
	}

	public String getIsLoopService() {
		return isLoopService;
	}

	public void setIsLoopService(String isLoopService) {
		this.isLoopService = isLoopService;
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

	//other getter and seters
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

	public String getAllocatedTo() {
		return allocatedTo;
	}

	public void setAllocatedTo(String allocatedTo) {
		this.allocatedTo = allocatedTo;
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
	/**
	 * @return the session
	 */
	public Map<String, Object> getSession() {
		return session;

	}

	@Override
	public void setSession(Map<String, Object> session) {
		this.session = session;
	}

	/**
	 * @return the user
	 */
	public String getLoggedInUserId() {

		return loggedInUserId;
	}

	/**
	 * @param user
	 *            the user to set
	 */
	public void setLoggedInUserId(String loggedInUserId) {
		this.loggedInUserId = loggedInUserId;
	}

	public Format getFormat() {
		return format;
	}

	public void setFormat(Format format) {
		this.format = format;
	}

	public EntityManagerFactory getEmf() {
		return emf;
	}

	public void setEmf(EntityManagerFactory emf) {
		this.emf = emf;
	}

	public EntityManager getEm() {
		return em;
	}

	public void setEm(EntityManager em) {
		this.em = em;
	}

	// Hidden
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

	public void setHiddenOccupy_OwnerCertified(String hiddenOccupyOwnerCertified) {
		hiddenOccupy_OwnerCertified = hiddenOccupyOwnerCertified;
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

	public String getDesPremises() {
		return desPremises;
	}

	public void setDesPremises(String desPremises) {
		this.desPremises = desPremises;
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
	
	public String getAppReferenceNo() {
		return appReferenceNo;
	}

	public void setAppReferenceNo(String appReferenceNo) {
		this.appReferenceNo = appReferenceNo;
	}
	//Other getters and setters
	public Date getSubmitDate() {
		return submitDate;
	}

	public void setSubmitDate(Date submitDate) {
		this.submitDate = submitDate;
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
	public String getPath() {
		return path;
	}

	public void setPath(String path) {
		this.path = path;
	}

	public PathStore getPathStore() {
		return pathStore;
	}

	public void setPathStore(PathStore pathStore) {
		this.pathStore = pathStore;
	}
	
	public String getMsgApplicationId() {
		return msgApplicationId;
	}

	public void setMsgApplicationId(String msgApplicationId) {
		this.msgApplicationId = msgApplicationId;
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
	
	//private String setApplicationIdFormatPrefix(String costCenter,String systemType, String applicationType, String year){
	//	return costCenter+"/"+systemType+applicationType+"/"+year+"/";
	//}
	
	public String getCebBranch() {
		return cebBranch;
	}

	public void setCebBranch(String cebBranch) {
		this.cebBranch = cebBranch;
	}
	
	public String getApplicationIdPrefix() {
		return applicationIdPrefix;
	}

	public void setApplicationIdPrefix(String applicationIdPrefix) {
		this.applicationIdPrefix = applicationIdPrefix;
	}
	
	public String getPivNo() {
		return pivNo;
	}

	public void setPivNo(String pivNo) {
		this.pivNo = pivNo;
	}
	
	public String getCustomerType() {
		return customerType;
	}

	public void setCustomerType(String customerType) {
		this.customerType = customerType;
	}
	
	public String getTariffCategoryCode() {
		return tariffCategoryCode;
	}

	public void setTariffCategoryCode(String tariffCategoryCode) {
		this.tariffCategoryCode = tariffCategoryCode;
	}
	
	

	public String getRepres2Con() {
		return repres2Con;
	}

	public void setRepres2Con(String repres2Con) {
		this.repres2Con = repres2Con;
	}

	public String getRepres1Con() {
		return repres1Con;
	}

	public void setRepres1Con(String repres1Con) {
		this.repres1Con = repres1Con;
	}

	private void showGetterData() {
		log.info(getApplicationId() + ", " + getServiceStreetAddress() + ", "
				+ getServiceSuburb() + ", " + getServiceCity() + ", "
				+ getServicePostalCode() + ", " + getOwnership() + ", "
				+ getOccupy_OwnerCertified() + ", " + getIsGovernmentPlace()
				+ ", " + getLandDispute() + ", " + getAssessmentNo() + ", "
				+ getAccountNos() + ", " + getPhase() + ", " + getTariffCode()
				+ ", " + getConnectionType() + ", " + getCustomerCategory()
				+ ", " + getIsLoopService());
	}

	private void showConvertedData() {
		//log.info("getApplicationId  " + Long.parseLong(getApplicationId()));
		if (getServicePostalCode().trim().length() != 0)
			log.info("getPostalCode  " + new BigDecimal(getServicePostalCode()));

		if (getPhase().trim().length() != 0)
			log.info("getPhase  " + new BigDecimal(getPhase()));
		if (getTariffCode().trim().length() != 0)
			log.info("getTariffCode  " + new BigDecimal(getTariffCode()));
		if (getConnectionType().trim().length() != 0)
			log.info("getConnectionType  "
					+ new BigDecimal(getConnectionType()));
		log.info("end");
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
			setAccountNos(null);
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
			setAppointmentDate(null);
			setAppointmentSession(null); 
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
	
	public Map <String, String[]> getParameters() {
		return parameters;
	}
		

	@Override
	public void setParameters(Map<String, String[]> parameters) {
		this.parameters=parameters;
		
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
	
	public String getPrefix() {
		return prefix;
	}

	public void setPrefix(String prefix) {
		this.prefix = prefix;
	}
	
	public String getApplicationSubType() {
		return applicationSubType;
	}

	public void setApplicationSubType(String applicationSubType) {
		this.applicationSubType = applicationSubType;
	}
	
	/*public String getFromDate() {
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
	}*/
	
	public String getDuration() {
		return duration;
	}

	public void setDuration(String duration) {
		this.duration = duration;
	}
	
	public String getMessageType() {
		return messageType;
	}

	public void setMessageType(String messageType) {
		this.messageType = messageType;
	}
	
	public String getIsLoanApp() {
		return isLoanApp;
	}

	public void setIsLoanApp(String isLoanApp) {
		this.isLoanApp = isLoanApp;
	}
	
	public String getPayingBankName() {
		return payingBankName;
	}

	public void setPayingBankName(String payingBankName) {
		this.payingBankName = payingBankName;
	}

	public String getPayingBranchName() {
		return payingBranchName;
	}

	public void setPayingBranchName(String payingBranchName) {
		this.payingBranchName = payingBranchName;
	}
	
	public String getStatusMsg() {
		return statusMsg;
	}

	public void setStatusMsg(String statusMsg) {
		this.statusMsg = statusMsg;
	}
	
	public String getApplicationNo() {
		return applicationNo;
	}

	public void setApplicationNo(String applicationNo) {
		this.applicationNo = applicationNo;
	}

private void visibleButton(){
	if (state.equals("changeServiceAddDirect")){
		setState("changeServiceAdd");
	}else if(state.equals("alterLoanAppDirect")){
		setState("alterLoanApp");
	}
}

public String getAppointmentDate() {
	return appointmentDate;
}

public void setAppointmentDate(String appointmentDate) {
	this.appointmentDate = appointmentDate;
}

public String getAppointmentSession() {
	return appointmentSession;
}

public void setAppointmentSession(String appointmentSession) {
	this.appointmentSession = appointmentSession;
}

public String getCostCenterArea() {
	return costCenterArea;
}

public void setCostCenterArea(String costCenterArea) {
	this.costCenterArea = costCenterArea;
}

public String getDemand() {
	return demand;
}

public void setDemand(String demand) {
	this.demand = demand;
}

public String getSchemaNo() {
	return schemaNo;
}

public void setSchemaNo(String schemaNo) {
	this.schemaNo = schemaNo;
}

public String getSchemaName() {
	return schemaName;
}

public void setSchemaName(String schemaName) {
	this.schemaName = schemaName;
}

public String getProposer() {
	return proposer;
}

public void setProposer(String proposer) {
	this.proposer = proposer;
}

public String getRepres1() {
	return repres1;
}

public void setRepres1(String repres1) {
	this.repres1 = repres1;
}

public String getRepres2() {
	return repres2;
}

public void setRepres2(String repres2) {
	this.repres2 = repres2;
}

public List<String> getFoundSourceList() {
	ApplicationTransactionEjb transactionEjb=new ApplicationTransactionEjb(getSessionKey("region"));
	//foundSourceList.add("--select--");
	foundSourceList = transactionEjb.getApplicationTypes(getCostCenterNo(), getSessionKey("region"));
	
	if(foundSourceList == null){
		foundSourceList = new ArrayList<String>();
		foundSourceList.add(getCostCenterNo());
	}
	
	return foundSourceList;
}

public void setFoundSourceList(List<String> foundSourceList) {
	this.foundSourceList = foundSourceList;
}



public String getAreaName() {
	return areaName;
}

public void setAreaName(String areaName) {
	this.areaName = areaName;
}

public String getCapacityOfServices() {
	return capacityOfServices;
}

public void setCapacityOfServices(String capacityOfServices) {
	this.capacityOfServices = capacityOfServices;
}

public String getMeterPoints() {
	return meterPoints;
}

public void setMeterPoints(String meterPoints) {
	this.meterPoints = meterPoints;
}

public String getConsumerRef() {
	return consumerRef;
}

public void setConsumerRef(String consumerRef) {
	this.consumerRef = consumerRef;
}

public String getHiddenIsAleardyhaveElectricity() {
	return hiddenIsAleardyhaveElectricity;
}

public void setHiddenIsAleardyhaveElectricity(
		String hiddenIsAleardyhaveElectricity) {
	this.hiddenIsAleardyhaveElectricity = hiddenIsAleardyhaveElectricity;
}

public String getFoundSource() {
	return foundSource;
}

public void setFoundSource(String foundSource) {
	this.foundSource = foundSource;
}

	
private void putSessionKey(String key, Map value ){
	   getSession().put(key, value);

}

public List<String> getDistrictCodeList() {
	ProvinceDetailsMasterEjb provinceDetailsMasterEjb=new ProvinceDetailsMasterEjb();
	districtCodeList = provinceDetailsMasterEjb.getAllCodes("DISTRICT", getCostCenterNo(), getSessionKey("region"));
	if(districtCodeList == null ){
		districtCodeList = new ArrayList<String>();
		districtCodeList.add("--Select--");
	}
	
	return districtCodeList;
	
}

public void setDistrictCodeList(List<String> districtCodeList) {
	this.districtCodeList = districtCodeList;
}

public List<String> getElectorateCodeList() {
	ProvinceDetailsMasterEjb provinceDetailsMasterEjb=new ProvinceDetailsMasterEjb();
	electorateCodeList = provinceDetailsMasterEjb.getAllCodes("ELECTORATE", getCostCenterNo(), getSessionKey("region"));
	if(electorateCodeList == null){
		electorateCodeList = new ArrayList<String>();
		electorateCodeList.add("--Select--");
	}
	
	return electorateCodeList;
	
}

public void setElectorateCodeList(List<String> electorateCodeList) {
	this.electorateCodeList = electorateCodeList;
}

public List<String> getCscCodeList() {
	ProvinceDetailsMasterEjb provinceDetailsMasterEjb=new ProvinceDetailsMasterEjb();
	cscCodeList = provinceDetailsMasterEjb.getAllCodes("CSC", getCostCenterNo(), getSessionKey("region"));
	if(cscCodeList == null ){
		cscCodeList = new ArrayList<String>();
		cscCodeList.add("--Select--");
	}
	
	return cscCodeList;
	
}

public void setCscCodeList(List<String> cscCodeList) {
	this.cscCodeList = cscCodeList;
}
	  
	 
	
	

}
