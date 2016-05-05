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
import masters.model.Bank;
import masters.model.BankBranch;
import masters.model.BankBranchPK;
import masters.service.BankEjb;
import org.apache.struts2.interceptor.ParameterAware;
import org.apache.struts2.interceptor.SessionAware;

//import application.dao.ApplicantDao;
//import application.dao.ApplicationDao;
//import application.dao.ApplicationReferenceDao;
//import application.dao.WiringLandDetailDao;
import application.model.Applicant;
import application.model.Application;
import application.model.ApplicationPK;
import application.model.ApplicationReference;
import application.model.ApplicationReferencePK;
import application.model.CityMap;
import application.model.WiringLandDetail;
import application.model.WiringLandDetailPK;
import application.service.ApplicantEjb;
import application.service.ApplicationEjb;
import application.service.ApplicationReferenceEjb;
import application.service.ApplicationTransactionEjb;
import application.service.WiringLandDetailEjb;

import com.opensymphony.xwork2.ActionSupport;

import costcenter.model.Gldeptin;
import costcenter.service.CostCenterEjb;

//import estimate.model.Tariff;
//import estimate.model.TariffCategory;
import estimate.model.FundSource;
import estimate.service.EstimateEjb;
import estimate.service.TariffCategoryEjb;
import estimate.service.TariffEjb;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import util.common.Format;
import util.common.PivPrefixType;

@SuppressWarnings("serial")
public class ApplicationFormBS extends ActionSupport implements SessionAware,
		ParameterAware {
	
	// Common Fields
	private static final Log log = LogFactory.getLog(ApplicationFormBS.class);
	private static final String systemType="E";
	private static final String newStatus = "N";
	private static final String modifyStatus = "M";
	private static final String confirmedStatus = "C";
	private static final String allocatedStatus = "A";
	private static final String newPath="New Connection>Application>New Application";
	private static final String viewPath="New Connection>Application>View Application";
	private static final String modifyPath="New Connection>Application>Modify Application";
	private static final String confirmPath="New Connection>Application>Generate App No";	
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
	private List<String> listTariffCode;
	private List<String> listTariffCatCode;
	//Gayani
	private List<FundSource> fundSourceList = new ArrayList();
	private List<String> fundSourceListTmp = new ArrayList();
	private String fundSourceID;
	private String areacode;
	private String location;
	private String conrefno;
	private String representative1;
	private String representative2;
	private String proposer;
	private String schemeno;
	private String schemename;
	private String gsdivision;
	private String agadivision;
	private String electorate;
	private String premises;
	private String sinnumber;
	private String demand;
	private String devsionalSector;
	
	
	
    //End Gayani
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
	private String testP;
	private BigDecimal NoMePoint;
	
	
	
	public String getTestP() {
		return testP;
	}

	public void setTestP(String testP) {
		this.testP = testP;
	}

	public ApplicationFormBS() {
		super();
		log.info("@@@@@@@@@@@@@@@@@@@&&&&&&&&&&&&&&&&&&&&&&");
		//setListTariffCode(getTafiffCode());
		//setListTariffCatCode(getTafiffCaTCode());
	}

	/**
     *
     */
	public void setLoggedData() {
        log.info(getSession());
		setLoggedInUserId(getSessionKey("userName"));
        setCostCenterNo(getSessionKey("costCenterNo"));
        setCebBranch(getSessionKey("costCenterName"));
        setCsc(getCebBranch());
        System.out.println("##########################################getFundSource" + getSessionKey("costCenterNo") + getFundSource() );
        //System.out.println("##########################################khgjhgh" + getFundSource());
        //
        EstimateEjb estimateEjb = new EstimateEjb(getSessionKey("region"));
    	fundSourceList = estimateEjb.getFundSource(costCenterNo);
        System.out.println("########## 1 "+fundSourceList);
        
        
    }
 	
 	public String getSessionKey(String key) {
        return getSession().get(key).toString();
    }
 	
 	

	/**
	 * return success
	 */
	public String execute() {
		System.out.println("##########################################khgjhgh"  + getTestP());
		setLoggedData();
		setPath(viewPath);
		PathStore pathStore= new PathStore();
		pathStore.setPath(viewPath);
		setState("view");
		setListTariffCode(getTafiffCode());
		setListTariffCatCode(getTafiffCaTCode());
		
		if (applicationId!=null)
		 setApplicationId(session.get("applicationId").toString());
		//findAppId();
		System.out.println("########## 2 "+fundSourceList);
		fundSourceListTmp.add("ADB");
		fundSourceListTmp.add("CEB");
		fundSourceListTmp.add("DCB");
		return SUCCESS;
	}
	
	public String genAppNo() {
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
		return SUCCESS;
	}
	
	
	//EJB
	//@SuppressWarnings("unchecked")
	public String save() {
		try {
			setListTariffCode(getTafiffCode());
			setListTariffCatCode(getTafiffCaTCode());
			CityMap cityMap=new CityMap();
			setBankList(getAllBankList());
			ApplicationTransactionEjb ejb=new ApplicationTransactionEjb(getSessionKey("region"));
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
			setFullAddress(getServiceStreetAddress() + ";" + getServiceSuburb() + ";"
					+ getServiceCity() + ";" + servicePostalCode);
			setServiceStreetAddress(serviceStreetAddress);
			setServiceSuburb(serviceSuburb);
			setServiceCity(serviceCity);
			setServicePostalCode(servicePostalCode);
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
			String appId=ejb.save(application, wiringLandDetail, getCostCenterNo()+"/"+PivPrefixType.APP_NEW_BCONN+"/"+getFormat().getYear()+"/");//prefix
			setReferenceNo(appId);
			setPivNo("PIV/"+appId);
			//getEm().getTransaction().commit();
			CostCenterEjb ejbCC=new CostCenterEjb(getSessionKey("region"));
	        Gldeptin gldeptin=ejbCC.findById(getCostCenterNo());
	        setPayingBankCode(gldeptin.getBankCode());
	        setPayingBranchCode(gldeptin.getBranchCode());
	        BankEjb bankEjb = new BankEjb(getSessionKey("region"));
	        Bank bank = bankEjb.findBankCode(payingBankCode);
	        setPayingBankName(bank.getBankName());
	 		
	 		BankBranchPK bankBranchPK = new BankBranchPK();
	 		bankBranchPK.setBankCode(payingBankCode);
	 		bankBranchPK.setBranchCode(payingBranchCode);
	 		BankBranch bankBranch = bankEjb.findBankBranchCode(bankBranchPK);
	 		setPayingBranchName(bankBranch.getBranchName());
	 		
			setState("fromApp");
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
		WiringLandDetailPK  wiringLandDetailPK=null;
		//WiringLandDetailDao wiringLandDetailDao=null;
		ApplicationEjb applicationEjb=new ApplicationEjb(getSessionKey("region"));
		WiringLandDetailEjb wiringLandDetailEjb=new WiringLandDetailEjb(getSessionKey("region"));
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
			//wiringLandDetailDao =new WiringLandDetailDao();
			applicationPK=new ApplicationPK();
			applicationPK.setApplicationId(getApplicationId());
			applicationPK.setDeptId(getCostCenterNo());
			//application=applicationDao.findByAppId(applicationPK, getEm());
			try{
			application=applicationEjb.findByAppId(applicationPK);
			wiringLandDetail=wiringLandDetailEjb.findByAppId(wiringLandDetailPK);
			}catch(Exception e){
				System.out.println("Error : " );
			}
			
			System.out.println("applicationwiringLandDetail" + application+"  "+wiringLandDetail);
			if (application!=null){
				application=updateApplicationModel(application);
				wiringLandDetail=updateWiringLandDetailModel(wiringLandDetail);
				transactionEjb.update(application, wiringLandDetail);
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
			try{
			application=ejb.findByAppId(applicationPK);
			}catch(Exception e){
				System.out.println("Error : ");
			}
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
		
		System.out.println("##########################################khgjhgh"  + getTestP());
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
			try{
			application=applicationEjb.findByAppId(applicationPK);
			}catch(Exception e){
				System.out.println("Error : ");
			}
			log.info("1");
			if (application != null) {
				log.info("2");
				setApplicationData(application);
				//applicant = findById(application.getIdNo());
				//applicant=applicantDao.findById(application.getIdNo(),getEm());
				//try{
					applicant=applicantEjb.findById(application.getIdNo());
				//}catch(Exception e){
					//System.out.println("Error : " + e.getMessage());
				//}
				if (applicant != null) { 
					log.info("3");
					setApplicantData(applicant);
					log.info("4");
					//wiringLandDetail = findWiringLandDetailByAppId(longApplicationNo);
					wiringLandDetailPK=new WiringLandDetailPK();
					System.out.println("App : " + getApplicationId() + getCostCenterNo());
					wiringLandDetailPK.setApplicationId(getApplicationId().trim());
					wiringLandDetailPK.setDeptId(getCostCenterNo().trim());
					//wiringLandDetail=wiringLandDetailDao.findByAppId(wiringLandDetailPK,getEm());
					System.out.println("Error : 1 " + wiringLandDetailPK);
					try{
						wiringLandDetail=wiringLandDetailEjb.findByAppId(wiringLandDetailPK);
					}catch(Exception e){
						e.printStackTrace();
						System.out.println("Error : " + e.getMessage());
					}
					log.info("5 : " + wiringLandDetail );
					if (wiringLandDetail != null) {
						setWiringLandDetail(wiringLandDetail);
						//applicationReference=findAppRefDetailByAppId(longApplicationNo);
						applicationReferencePK.setApplicationId(getApplicationId());
						applicationReferencePK.setDeptId(getCostCenterNo());
						//applicationReference=applicationReferenceDao.findByAppId(applicationReferencePK,getEm());
						//log.info("before");
						//log.info(applicationReferencePK);
						try{
							applicationReference=appReferenceEjb.findByAppId(applicationReferencePK);
						}catch(Exception e){
							System.out.println("Error : ");
						}
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
	//EJB
	//@SuppressWarnings("unchecked")
	public String findId() {
		
		System.out.println("Hiiiii FindID 1" + getIdNo());
		System.out.println("Hiiiii session" + getSessionKey("region"));
		Applicant applicant=null;
		ApplicantEjb applicantEjb=new ApplicantEjb(getSessionKey("region"));
		
		//ApplicantDao applicantDao=null;
		
		try{
			setListTariffCode(getTafiffCode());
			System.out.println("Hiiiii FindID 2");
			setListTariffCatCode(getTafiffCaTCode());
			System.out.println("Hiiiii FindID 3");
			//setParameters(parameters);
			//setApplicantIdPara(ActionContext.getContext().getParameters());
			setErrorMessage(null);
			System.out.println("Hiiiii FindID 4");
			//setApplicantIdPara();
			//setHiddensParas();
			setLoggedData();
			System.out.println("Hiiiii FindID 5");
			setFormat(new Format());
			setDate(getFormat().FormatDate());
			//setState("findId");
			setState(getState());
			System.out.println("Hiiiii FindID 6");
			setHiddenSecondState("findId");
			System.out.println("Hiiiii FindID 7");
			setPath(getPath());
			System.out.println("Hiiiii FindID 8");
			//setEmf(Persistence.createEntityManagerFactory("SMC"));
			//setEm(emf.createEntityManager());
			//applicantDao=new ApplicantDao(getEmf());
			//applicant = findById(getIdNo());
			//applicant=applicantDao.findById(getIdNo(),getEm());
			System.out.println("Hiiiii FindID 8" + "Hiii : " + getIdNo() );
			//try{
			applicant = applicantEjb.findById(getIdNo());
			//}catch(Exception e){
				//System.out.println("Hiiiii FindID 9 " + e.getMessage());
			//}
			System.out.println("Hiiiii FindID 9 " + applicant);
			// log.info("applicant " + applicant);
			if (applicant != null) {
				setApplicantData(applicant); 
				System.out.println("Hiiiii FindID 10");
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
		System.out.println("fillTheSame 1");
		setErrorMessage(null);
		//setApplicantParas();
		//setHiddensParas();
		System.out.println("fillTheSame 2");
		setListTariffCode(getTafiffCode());
		System.out.println("fillTheSame 3");
		setListTariffCatCode(getTafiffCaTCode());
		System.out.println("fillTheSame 4");
		setLoggedData();
		System.out.println("fillTheSame 5");
		setPath(getPath());
		System.out.println("fillTheSame 6");
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
		System.out.println("fillTheSame-Confirm 1");
		setMessageType(MSG_NONE);
		setErrorMessage(null);
		CityMap cityMap=new CityMap();
		Application application=null;
		ApplicationPK applicationPK=null;
		//ApplicationDao applicationDao=null;
		//ApplicationReference applicationReference=null;
		//ApplicationReferencePK applicationReferencePk=null;
		//ApplicationReferenceDao applicationReferenceDao=null;
		System.out.println("fillTheSame-Confirm 2");
		try{
			setListTariffCode(getTafiffCode());
			setListTariffCatCode(getTafiffCaTCode());
			System.out.println("fillTheSame-Confirm 3");
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
		try{
			
			application=applicationEjb.findByAppId(applicationPK);
		}catch(Exception e){
		   System.out.println("Error : ");	
		}
		application=updateConfirmBy(application);
		setCostCenterNo(cityMap.mapCity(costCenterNo, serviceCity));
		setApplicationIdPrefix(getCostCenterNo()+"/"+systemType+getApplicationType()+"/"+getFormat().getYear()+"/");
		setMsgApplicationId(transactionEjb.update(application, getApplicationIdPrefix()));
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
	
	/*private void getApplicationNextNo(ApplicationDao applicationDao) {
		setApplicationIdPrefix(getCostCenterNo()+"/"+systemType+getApplicationType()+"/"+getFormat().getYear()+"/");
		//String newApplicationId=setApplicationIdFormatPrefix(getCostCenterNo(), systemType, getApplicationType(), getFormat().getYear())+applicationDao.getNextID(getEm());
		String newApplicationId=getApplicationIdPrefix()+applicationDao.getNextAppId(getApplicationIdPrefix(), em);
		
		
		
	}*/

public String	modifyDirect(){
	setListTariffCode(getTafiffCode());
	setListTariffCatCode(getTafiffCaTCode());
	setLoggedData();
	setFormat(new Format());
    setPrefix(getCostCenterNo()+"/"+PivPrefixType.APP_NEW_BCONN+"/"+getFormat().getYear()+"/");
	setApplicationId(getPrefix()+"xxxx");
	setState("modifyApplication");
	setPath(modifyPath);
	return SUCCESS;
}

public String	confirmDirect(){
	setListTariffCode(getTafiffCode());
	setListTariffCatCode(getTafiffCaTCode());
	setLoggedData();
	setFormat(new Format());
    setPrefix(getCostCenterNo()+"/"+PivPrefixType.APP_NEW_BCONN+"/"+getFormat().getYear()+"/");
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
	System.out.println("@@@@@@@@@@ 1"+fundSourceList);
	setLoggedData();
	System.out.println("@@@@@@@@@@ 2"+fundSourceList);
	setFormat(new Format());
    setPrefix(getCostCenterNo()+"/"+PivPrefixType.APP_NEW_BCONN+"/"+getFormat().getYear()+"/");
	setApplicationId(getPrefix()+"xxxx");
	setState("newApplication");
	setPath(newPath);
	//Radio
	setOwnership("O");
	setOccupy_OwnerCertified("Y");
	setIsGovernmentPlace("N");
	//setLandDispute(hiddenLandDispute)
	setPhase("1");
	setConnectionType("30");
	setTariffCategoryCode("DP");
	setIsLoanApp("N");
	//setIsLoopService(hiddenIsLoopService)
	//Radio
	setDate(getFormat().FormatDate());
	setPreparedBy(getLoggedInUserId());
	System.out.println("@@@@@@@@@@ 3"+fundSourceList);
	return SUCCESS;
}

public String	viewDirect(){
	setListTariffCode(getTafiffCode());
	setListTariffCatCode(getTafiffCaTCode());
	setLoggedData();
	setFormat(new Format());
    setPrefix(getCostCenterNo()+"/"+PivPrefixType.APP_NEW_BCONN+"/"+getFormat().getYear()+"/");
	setApplicationId(getPrefix()+"xxxx");
	setState("view");
	setPath(viewPath);
	setDate(getFormat().FormatDate());
	setPreparedBy(getLoggedInUserId());
	return SUCCESS;
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
		setPreparedBy(getLoggedInUserId());
		setAddUser(getLoggedInUserId());
		setAddDate(calendar.getTime());
		setAddTime(getFormat().FormatTime());
		Application application=new Application();
		log.info("3");
		application.setId(id);
		application.setApplicationNo(null);
		application.setApplicationType(getApplicationType());
		application.setApplicationSubType(applicationSubType);
		
		//log.info("4");
		//Duration
		if (!applicationSubType.equals("PM")){
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
		
		}
		//Duration
		application.setIsLoanApp(isLoanApp);
		application.setSubmitDate(getSubmitDate());
		System.out.println("Id NO : " + getIdNo());
		application.setIdNo(getIdNo());
		application.setPreparedBy(getLoggedInUserId());
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
		
		//BigDecimal bdServicePostalCode = 
			//getServicePostalCode().trim().length() == 0 ? new BigDecimal(0)
				//: new BigDecimal(getServicePostalCode());
		
		//BigDecimal bdNoOfBulbs = getNoOfBulbs().trim().length() == 0 ? new BigDecimal(0)
		//: new BigDecimal(getNoOfBulbs());
		
		//BigDecimal bdNoOfFans = getNoOfFans().trim().length() == 0 ? new BigDecimal(0)
		//		: new BigDecimal(getNoOfFans());
		//BigDecimal bdNoOfPlughs_5A = getNoOfPlughs_5A().trim().length() == 0 ? new BigDecimal(0)
		//		: new BigDecimal(getNoOfPlughs_5A());
		log.info("111");
		//BigDecimal bdNoOfPlughs_15A = getNoOfPlughs_15A().trim().length() == 0 ? new BigDecimal(0)
				//: new BigDecimal(getNoOfPlughs_15A());
		log.info("444");
		//BigDecimal bdMotorsTotal = getMotorsTotal().trim().length() == 0 ? new BigDecimal(0)
			//	: new BigDecimal(getMotorsTotal());
		log.info("2222");
		//BigDecimal bdWeldingPlant = getWeldingPlant().trim().length() == 0 ? new BigDecimal(0)
				//: new BigDecimal(getWeldingPlant());
		//BigDecimal bdMetalCrusher = getMetalCrusher().trim().length() == 0 ? new BigDecimal(0)
				//: new BigDecimal(getMetalCrusher());
		log.info("3333");
		//BigDecimal bdSawMills = getSawMills().trim().length() == 0 ? new BigDecimal(0)
				//: new BigDecimal(getSawMills());
		// BigDecimal bdTariffCode = getNoOfPlughs_5A().trim().length()==0? new
		// BigDecimal(0):new BigDecimal(getTariffCode());
		log.info("2");
		
		//showConvertedData();
		WiringLandDetail wiringLandDetail=new WiringLandDetail();
		wiringLandDetail.setId(id);
		wiringLandDetail.setServiceStreetAddress(getServiceStreetAddress());
		wiringLandDetail.setServiceSuburb(getServiceSuburb());
		wiringLandDetail.setServiceCity( getServiceCity()); 
		if (servicePostalCode!=null){
			//wiringLandDetail.setServicePostalCode(bdServicePostalCode);
		}else wiringLandDetail.setServicePostalCode(new BigDecimal(0));
		
		wiringLandDetail.setOwnership(getOwnership());
		wiringLandDetail.setOccupyOwnerCertified(getOccupy_OwnerCertified());
		wiringLandDetail.setIsGovernmentPlace(getIsGovernmentPlace());
		//wiringLandDetail.setLandDispute(getLandDispute());
		wiringLandDetail.setAssessmentNo(getAssessmentNo());
		wiringLandDetail.setNeighboursAccNo(getNeighboursAccNo());
		//System.out.println("noOfBulbs" + noOfBulbs);
		//if (noOfBulbs!=null){//gayani
			//wiringLandDetail.setNoOfBulbs( new BigDecimal(noOfBulbs));
		//}else wiringLandDetail.setNoOfBulbs( new BigDecimal(0));
		if (noOfFans!=null){
			//wiringLandDetail.setNoOfFans(bdNoOfFans);
		}else wiringLandDetail.setNoOfFans(new BigDecimal(0));
		if (noOfPlughs_5A!=null){
			//wiringLandDetail.setNoOfPlugs5a(bdNoOfPlughs_5A);
		}else wiringLandDetail.setNoOfPlugs5a(new BigDecimal(0));
		if (noOfPlughs_15A!=null){
			//wiringLandDetail.setNoOfPlugs15a(bdNoOfPlughs_15A);
		}else 	wiringLandDetail.setNoOfPlugs15a(new BigDecimal(0));
		log.info("3");
		if (motorsTotal!=null){
			//wiringLandDetail.setMotorTotal(bdMotorsTotal);
		}else	wiringLandDetail.setMotorTotal(new BigDecimal(0));
		if (weldingPlant!=null){
			//wiringLandDetail.setWeldingPlant(bdWeldingPlant);
		}else wiringLandDetail.setWeldingPlant(new BigDecimal(0));	
		
		if (metalCrusher!=null){
			//wiringLandDetail.setMetalCrusher(bdMetalCrusher);
		}else wiringLandDetail.setMetalCrusher(new BigDecimal(0));	
		if (sawMills!=null){
			//wiringLandDetail.setSawMills(bdSawMills);
		}else 	wiringLandDetail.setSawMills(new BigDecimal(0));
		
		wiringLandDetail.setPhase(new BigDecimal(getPhase()));
		wiringLandDetail.setTariffCode(getTariffCode());
		wiringLandDetail.setTariffCatCode(getTariffCategoryCode());
		wiringLandDetail.setConnectionType(new BigDecimal(getConnectionType()));
		wiringLandDetail.setCustomerCategory(getCustomerCategory());
		log.info("4");
		log.info(getCustomerType());
		wiringLandDetail.setCustomerType(getCustomerType());
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

	
	
	private Application updateApplicationModel(Application application) throws NumberFormatException{
		Calendar calendar = Calendar.getInstance();
		application.setIsLoanApp(isLoanApp);
		application.setApplicationType(getApplicationType());
		application.setApplicationSubType(applicationSubType);
		application.setApplicationSubType(applicationSubType);
		application.setFromDate(format.StrToDate(fromDate));
		application.setToDate(format.StrToDate(toDate));
		application.setIsLoanApp(isLoanApp);
		application.setConfirmedBy(getConfirmedBy());
		application.setStatus(getStatus());
		application.setDescription(getDescription());
		application.setUpdUser(getLoggedInUserId());
		application.setUpdDate(calendar.getTime());
		application.setUpdTime(getFormat().FormatTime());
		return application;
		

	}

	
	
private WiringLandDetail updateWiringLandDetailModel(WiringLandDetail wiringLandDetail) throws NumberFormatException{
		

		wiringLandDetail.setServiceStreetAddress(getServiceStreetAddress());
		wiringLandDetail.setServiceCity(getServiceCity());
		wiringLandDetail.setServiceSuburb(getServiceSuburb());
		wiringLandDetail.setServicePostalCode(new BigDecimal(
				getServicePostalCode())); 
		wiringLandDetail.setAssessmentNo(getAssessmentNo());
		wiringLandDetail.setNeighboursAccNo(getNeighboursAccNo());
		//System.out.println("ee : " + getNoOfBulbs());
		wiringLandDetail.setNoOfBulbs(new BigDecimal(getNoOfBulbs()));
		wiringLandDetail.setNoOfFans(new BigDecimal(getNoOfFans()));
		wiringLandDetail.setNoOfPlugs5a(new BigDecimal(getNoOfPlughs_5A()));
		wiringLandDetail.setNoOfPlugs15a(new BigDecimal(getNoOfPlughs_15A()));
		wiringLandDetail.setMotorTotal(new BigDecimal(getMotorsTotal()));
		wiringLandDetail.setWeldingPlant(new BigDecimal(getWeldingPlant()));
		wiringLandDetail.setMetalCrusher(new BigDecimal(getMetalCrusher()));
		wiringLandDetail.setSawMills(new BigDecimal(getSawMills()));
		wiringLandDetail.setTariffCode(getTariffCode());
		wiringLandDetail.setTariffCatCode(getTariffCategoryCode());
		wiringLandDetail.setCustomerCategory(getCustomerCategory());
		wiringLandDetail.setCustomerType(getCustomerType());
		wiringLandDetail.setOwnership(getOwnership());
		wiringLandDetail.setOccupyOwnerCertified(getOccupy_OwnerCertified());
		wiringLandDetail.setIsGovernmentPlace(getIsGovernmentPlace());
		//wiringLandDetail.setLandDispute(getLandDispute());
		wiringLandDetail.setPhase(new BigDecimal(getPhase()));
		wiringLandDetail.setConnectionType(new BigDecimal(getConnectionType()));
		//wiringLandDetail.setIsLoopService(getIsLoopService());
		return wiringLandDetail;
		
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
		log.info("setApplicantData ############### " + applicant.getIdType());
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
	
	private void showGetterData() {
		log.info(getApplicationId() + ", " + getServiceStreetAddress() + ", "
				+ getServiceSuburb() + ", " + getServiceCity() + ", "
				+ getServicePostalCode() + ", " + getOwnership() + ", "
				+ getOccupy_OwnerCertified() + ", " + getIsGovernmentPlace()
				+ ", " + getLandDispute() + ", " + getAssessmentNo() + ", "
				+ getNeighboursAccNo() + ", " + getNoOfBulbs() + ", "
				+ getNoOfFans() + ", " + getNoOfPlughs_5A() + ", "
				+ getNoOfPlughs_15A() + ", " + getMotorsTotal() + ", "
				+ getWeldingPlant() + ", " + getMetalCrusher() + ", "
				+ getSawMills() + ", " + getPhase() + ", " + getTariffCode()
				+ ", " + getConnectionType() + ", " + getCustomerCategory()
				+ ", " + getIsLoopService());
	}

	private void showConvertedData() {
		//log.info("getApplicationId  " + Long.parseLong(getApplicationId()));
		if (getServicePostalCode().trim().length() != 0)
			log.info("getPostalCode  " + new BigDecimal(getServicePostalCode()));
		System.out.println((getNoOfBulbs() == ""));
		System.out.println((getNoOfBulbs().trim().length() == 0));
		System.out.println("bhfj" + getNoOfBulbs());
		if (getNoOfBulbs().trim().length() != 0)
			log.info("getNoOfBulbs  " + new BigDecimal(getNoOfBulbs()));
		if (getNoOfFans().trim().length() != 0)
			log.info("getNoOfFans  " + new BigDecimal(getNoOfFans()));
		if (getNoOfPlughs_5A().trim().length() != 0)
			log.info("getNoOfPlughs_5A  " + new BigDecimal(getNoOfPlughs_5A()));
		if (getNoOfPlughs_15A().trim().length() != 0)
			log.info("getNoOfPlughs_15A  "
					+ new BigDecimal(getNoOfPlughs_15A()));
		if (getMotorsTotal().trim().length() != 0)
			log.info("getMotorsTotal  " + new BigDecimal(getMotorsTotal()));
		if (getWeldingPlant().trim().length() != 0)
			log.info("getWeldingPlant  " + new BigDecimal(getWeldingPlant()));
		if (getMetalCrusher().trim().length() != 0)
			log.info("getMetalCrusher  " + new BigDecimal(getMetalCrusher()));
		if (getSawMills().trim().length() != 0)
			log.info("getSawMills  " + new BigDecimal(getSawMills()));
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
		System.out.println("clear id no");
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
	
	private List<FundSource> getFundSource(){
		EstimateEjb estimateEjb = new EstimateEjb(getSessionKey("region"));
		List<FundSource> list = estimateEjb.getFundSource(costCenterNo);
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

	private void setFundSourceList(List<FundSource> fundSourceList) {
		this.fundSourceList = fundSourceList;
	}

	private List<FundSource> getFundSourceList() {
		return fundSourceList;
	}

	private void setFundSourceID(String fundSourceID) {
		this.fundSourceID = fundSourceID;
	}

	private String getFundSourceID() {
		return fundSourceID;
	}

	public List<String> getFundSourceListTmp() {
		return fundSourceListTmp;
	}

	public void setFundSourceListTmp(List<String> fundSourceListTmp) {
		this.fundSourceListTmp = fundSourceListTmp;
	}

	private void setAreacode(String areacode) {
		this.areacode = areacode;
	}

	private String getAreacode() {
		return areacode;
	}

	private void setLocation(String location) {
		this.location = location;
	}

	private String getLocation() {
		return location;
	}

	private void setConrefno(String conrefno) {
		this.conrefno = conrefno;
	}

	private String getConrefno() {
		return conrefno;
	}

	private void setRepresentative1(String representative1) {
		this.representative1 = representative1;
	}

	private String getRepresentative1() {
		return representative1;
	}

	private void setRepresentative2(String representative2) {
		this.representative2 = representative2;
	}

	private String getRepresentative2() {
		return representative2;
	}

	private void setProposer(String proposer) {
		this.proposer = proposer;
	}

	private String getProposer() {
		return proposer;
	}

	private void setSchemeno(String schemeno) {
		this.schemeno = schemeno;
	}

	private String getSchemeno() {
		return schemeno;
	}

	private void setSchemename(String schemename) {
		this.schemename = schemename;
	}

	private String getSchemename() {
		return schemename;
	}

	private void setGsdivision(String gsdivision) {
		this.gsdivision = gsdivision;
	}

	private String getGsdivision() {
		return gsdivision;
	}

	private void setAgadivision(String agadivision) {
		this.agadivision = agadivision;
	}

	private String getAgadivision() {
		return agadivision;
	}

	private void setElectorate(String electorate) {
		this.electorate = electorate;
	}

	private String getElectorate() {
		return electorate;
	}

	private void setPremises(String premises) {
		this.premises = premises;
	}

	private String getPremises() {
		return premises;
	}

	private void setSinnumber(String sinnumber) {
		this.sinnumber = sinnumber;
	}

	private String getSinnumber() {
		return sinnumber;
	}

	private void setDemand(String demand) {
		this.demand = demand;
	}

	private String getDemand() {
		return demand;
	}

	private void setDevsionalSector(String devsionalSector) {
		this.devsionalSector = devsionalSector;
	}

	private String getDevsionalSector() {
		return devsionalSector;
	}

	private void setNoMePoint(BigDecimal noMePoint) {
		NoMePoint = noMePoint;
	}

	private BigDecimal getNoMePoint() {
		return NoMePoint;
	}
	
	
	  
	 
	
	

}
