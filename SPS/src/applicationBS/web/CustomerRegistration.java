package applicationBS.web;

import java.util.ArrayList;
import java.util.Calendar;
import java.util.List;
import java.util.Map;

//import javax.persistence.EntityManagerFactory;
//import javax.persistence.Persistence; 
import javax.persistence.PersistenceException;
import javax.persistence.RollbackException;

import masters.service.ProvinceDetailsMasterEjb;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.apache.struts2.interceptor.ParameterAware;
import org.apache.struts2.interceptor.SessionAware;

import util.common.ApplicationSubType;
import util.common.ApplicationType;
import util.common.Constants;
import util.common.Format;
import util.common.Path;
import util.common.PivPrefixType;
//import application.dao.ApplicantDao;
//import application.model.Applicant;
//import application.model.Applicant;
import application.model.Applicant;
import application.service.ApplicantEjb;
import application.service.ApplicationTransactionEjb;
//import application.service.ApplicantEjb;

import com.opensymphony.xwork2.ActionSupport;

import estimate.service.TariffCategoryEjb;
import estimate.service.TariffEjb;

//import costcenter.service.GldeptmEjb;

//import estimate.model.Pcesthtt;

@SuppressWarnings("serial")
public class CustomerRegistration extends ActionSupport implements
		SessionAware, ParameterAware {
	// common
	private static final Log log = LogFactory.getLog(CustomerRegistration.class);
	private static final String toApplicationNC="New Conn>Application>New Application";
	private static final String toApplicationCR="Cost Recovery>Application>New Application";
	private static final String toApplicationTC="Temp Conn>Application>New Application";
	private static final String newPath="Customer>New Customer";
	private static final String modifyPath="Customer>Modify Customer";
	//	
	private static final String toApplicationBULK="New Conn>Application>New Application";
	private static final String MSG_ERROR="ERROR";
	private static final String MSG_INFO="INFO";
	private static final String MSG_DONE="DONE";
	private static final String MSG_NONE="NONE";
	private Map<String, Object> session;
	private String loggedInUserId;
	private String costCenterNo;
	private String csc;
	private String smcType;
	private String errorMessage;
	private String messageType=MSG_NONE;
	private String info="OFF";
	private String prefix;
	private String ApplicationId;	
	private String  othercity;
	//Hidden
	private String state;
	private String path;
	private String hiddenSecondState;
	private String hid_city;
	private Map <String, String[]> parameters;
	private Format format;
	//Persistence
	//private EntityManagerFactory emf;
	// jsp Fields
	private String date;
	private String idNo;
	private String idType;
	private String fullName; 
	private String  isColombo;
	private String isLoanApp;
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
	private String companyName;
	
	// Database Fields
	private String status;
	private String addUser;
	private String addDate;
	private String addTime;
	private String updUser;
	private String updDate;
	private String updTime;
	private String entitled4Loan;
	private String memberOfSamurdhi;
	private String samurdhiId;
	private String sharePrice;
	private String noOfShares;
	private String loanReference;
	private String loanAmount;
	//Application comman fields
	List<String> listTariffCode;
	List<String> listTariffCatCode;
	private String ownership; 
	private String tariffCategoryCode;
	private String tariffCode;
	private String customerCategory;
	private String customerType;
	private String occupy_OwnerCertified;
	private String phase;
	private String connectionType;
	private String preparedBy;
	private String isGovernmentPlace;
	//Application TC fields 
	private String applicationSubType;
	private String applicationType;
	private String webAppName;
	private String personalCorporate; 
	
	private List<String> areaCodeList = new ArrayList<String>(); 
	  private List<String> districtCodeList = new ArrayList<String>(); 
	    private List<String> electorateCodeList = new ArrayList<String>(); 
	    private List<String> cscCodeList = new ArrayList<String>(); 
	private String areaCode;
	private List<String> foundSourceList;
	private String foundSource;
	//Constructors
	public CustomerRegistration() {
		super();
		//setEmf(Persistence.createEntityManagerFactory("SMC"));
		//setFormat(new Format());
	}
	
	/**
    *
    */
	public void setLoggedData() {
		log.info(getSession());
		setLoggedInUserId(getSessionKey("userName"));
		setCostCenterNo(getSessionKey("costCenterNo"));
        setCsc(getSessionKey("costCenterName"));
        setSmcType(getSessionKey("smcType"));
        //setWebAppName(Path.getWebAppName());
	}
	public String getSessionKey(String key) {
        return getSession().get(key).toString();
    }

	public String execute() {
		setState("newApplicant");
		setLoggedData();
		setPath(modifyPath);
		setIdType("NIC");
		//setError("ON");
		//setErrorMessage("jhgkdjfhgdfhg");
		return SUCCESS;
	}
	
	public String newDirect() {
		setState("newApplicant");
		setLoggedData();
		setPath(newPath);
		setIdType("NIC");
		setPersonalCorporate("PER");
		setCebEmployee("N");
		return SUCCESS;
		
	}
	
	public String modifyDirect() {
		setState("modifyApplicant");
		setLoggedData();
		setPath(modifyPath);
		setPersonalCorporate("PER");
		return SUCCESS;
	}
	
	public String findIdNo() {
		try{
			Applicant applicant=null;
			ApplicantEjb applicantEjb=new ApplicantEjb(getSessionKey("region"));
			setState(getState());
			setHiddenSecondState("findIdNo");
			setLoggedData();
			setPath(getPath());
			System.out.println("findIdNo() "+ getIdNo());			
			applicant=applicantEjb.findById(getIdNo());
			System.out.println("findIdNo()");
			if (applicant != null) {
				setApplicantData(applicant); 
				return SUCCESS;
			} else{
				setMessageType(MSG_INFO);
				setErrorMessage("Applicant Id not found");
				clearApplicantFields("no");
				return ERROR;
			}
			
		} catch (RollbackException e) {
			e.printStackTrace();
			System.out.println("ERROR MESSEGE" + e.getMessage());
			//setErrorMessage(e.getMessage());
			return ERROR;
		} catch (PersistenceException e) {
			e.printStackTrace();
			System.out.println("ERROR MESSEGE" + e.getMessage());
			//setErrorMessage(e.getMessage());
			return ERROR;
		} catch (Exception e) {
			e.printStackTrace();
			System.out.println("ERROR MESSEGE" + e.getMessage());
			//setErrorMessage(e.getMessage());
			return ERROR;
		}finally{
			//getEm().close();
			//getEmf().close();
			//emf=null;
			//getEm().close();
			//em=null;
			//applicant=null;
			//applicantDao=null;
			format=null;
		}

	}
	
	private void setApplicantData(Applicant applicant) throws NumberFormatException{
		log.info("setApplicantData ############### " + applicant.getIdNo());
		setPersonalCorporate(applicant.getPersonalCorporate());
		setIdType(applicant.getIdType());
		setIdNo(applicant.getIdNo());
		setCompanyName(applicant.getCompanyName());
		setCostCenterNo(applicant.getDeptId());
		setFirstName(applicant.getFirstName());
		setLastName(applicant.getLastName());
		setFullName(applicant.getFullName());
		setStreetAddress(applicant.getStreetAddress());
		setCity(applicant.getCity());
		hid_city = applicant.getCity();
		setSuburb(applicant.getSuburb());
		log.info("1");
		if (applicant.getPostalCode() != null)
			setPostalCode(applicant.getPostalCode().toString());
		log.info("2");
		if (applicant.getTelephoneNo() != null)
			setTelephoneNo(applicant.getTelephoneNo().toString());
		log.info("3");
		if (applicant.getMobileNo() != null)
			setMobileNo(applicant.getMobileNo().toString());
		log.info("4");
		setPreferefLanguage(applicant.getPreferredLanguage());
		setEmail(applicant.getEmail());
		setCebEmployee(applicant.getCebEmployee());
		log.info("5");
		//Loan Detail
		//log.info("applicant.getEntitled4Loan() "+applicant.getEntitled4Loan().trim());
		
		/*setEntitled4Loan(applicant.getEntitled4Loan());
		if (applicant.getEntitled4Loan().equals("Y")){
			log.info("N "+applicant.getEntitled4Loan());
		setLoanReference(applicant.getLoanReference());
		if (applicant.getLoanAmount()!= null)
			setLoanAmount(applicant.getLoanAmount().toString());
		setMemberOfSamurdhi(applicant.getMemberOfSamurdhi());
		setSamurdhiId(applicant.getSamurdhiId());
		if (applicant.getSharePrice() != null)
			setSharePrice(applicant.getSharePrice().toString());
		if (applicant.getNoOfShares() != null)
			setNoOfShares(applicant.getNoOfShares().toString());
		
		}*/
	}
	
	private void clearApplicantFields(String withid){
		if (withid.equals("yes"))
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
		//Loan Detail
		setEntitled4Loan(null);
		setLoanReference(null);
		setLoanAmount(null);
		setMemberOfSamurdhi(null);
		setSamurdhiId(null);
		setSharePrice(null);
		setNoOfShares(null);
	}
	
	
	
	private void clearIdNo(){
		setIdNo(null);
	}

	public String save() {
		
		
		try {
			setMessageType(MSG_NONE);
			setErrorMessage(null);
			setState("fromCustomer");
			setFormat(new Format());
			setDate(getFormat().FormatDate());
			//setApplicationId(getPrefix()+"xxxx");
			//setApplicationId(";lmv;dfmv;mf");
			setLoggedData();
			setFormat(new Format());
			//setEmf(Persistence.createEntityManagerFactory("SMC"));
			setStatus("N");	
			/*System.out.println("getters   " + getIdNo() + ", "
					+ getIdType() + ", " + getFirstName() + ", "
					+ getLastName() + ", " + getStreetAddress() + ", "
					+ getSuburb() + ", " + getCity() + ", " + getPostalCode()
					+ ", " + getTelephoneNo() + ", " + getMobileNo() + ", "
					+ getEmail() + ", " + getPreferefLanguage() + ", "
					+ getCebEmployee() + ", " + getStatus() + ", "
					+ getAddUser() + ", " + getAddDate() + ", " + getAddTime()
					+ ", " + getUpdUser() + ", " + getUpdDate() + ", "
					+ getUpdTime());*/
			saveData();
			//default value
			setOwnership("O");
			setOccupy_OwnerCertified("Y");
			setIsGovernmentPlace("N");
			setPhase("1");
			setConnectionType("30");
			setTariffCategoryCode("CP");
			setTariffCode("21");
			//default value
			setDate(getFormat().FormatDate());
			setPreparedBy(getLoggedInUserId());
			setMessageType(MSG_DONE);
			setErrorMessage("DONE:- New Customer Successfully Added." ); 
			setListTariffCode(getTafiffCode());
			setListTariffCatCode(getTafiffCaTCode());
			//setAreaCodeList(getAreaCodeList());
			setIsLoanApp("N");
			//System.out.println("#"+othercity+"#");
			//System.out.println("#"+!othercity.trim().equals("")+"#");
			//System.out.println("#"+othercity.trim()!=""+"#");
			setPath(toApplicationBULK);
			setPrefix(getCostCenterNo()+"/"+PivPrefixType.getAPP_XXX_Type(getSessionKey("smcType"))+"/"+getFormat().getYear()+"/");
			setApplicationId(getPrefix()+"xxxx");
			setApplicationType(getSessionKey("smcType"));
			if(getSessionKey("smcType").equalsIgnoreCase(Constants.RUTAL_ELECTRIFICATION) 
					|| getSessionKey("smcType").equalsIgnoreCase(Constants.PLANNING) 
					|| getSessionKey("smcType").equalsIgnoreCase(Constants.BRANCHTYPE_AREA_UNIT)
					||  getSessionKey("smcType").equalsIgnoreCase(Constants.RUTAL_ELECTRIFICATION_I)  
					||  getSessionKey("smcType").equalsIgnoreCase(Constants.BRANCHTYPE_ENERGY_MANAGEMENT) 
					||  getSessionKey("smcType").equalsIgnoreCase(Constants.BRANCHTYPE_DISTRIBUTION_AND_MAINTENANCE) 
					||  getSessionKey("smcType").equalsIgnoreCase(Constants.BRANCHTYPE_AREA_MAINTENANCE)
					||  getSessionKey("smcType").equalsIgnoreCase(Constants.JOBTYPE_COMMERCIAL_CP)
					||  getSessionKey("smcType").equalsIgnoreCase(Constants.JOBTYPE_PHM)
					||  getSessionKey("smcType").equalsIgnoreCase(Constants.BRANCHTYPE_AC_UNIT)
					||  getSessionKey("smcType").equalsIgnoreCase(Constants.JOBTYPE_CONSTRUCTION)){
				return "saveRE";
			}else{
				return "save";
			}
			
			/*if (costCenterNo.equals("541.00") || costCenterNo.equals("542.00") || costCenterNo.equals("547.00") || costCenterNo.equals("548.00")) {
				if (othercity!=null && !othercity.trim().equals("")  ){
					setCity(othercity);
				}
			}*/
//			if (smcType.equals(ApplicationType.NEW_CONN)){
//				setPath(toApplicationNC);
//				setPrefix(getCostCenterNo()+"/"+PivPrefixType.APP_NEW_CONN+"/"+getFormat().getYear()+"/");
//				setApplicationId(getPrefix()+"xxxx");
//				return "saveNC";
//			}else if (smcType.equals(ApplicationType.TEMP_CONN)){
//				setTariffCategoryCode("GP");
//				setTariffCode("31");
//				setCustomerType("CONS");
//				setCustomerCategory("PRIV");
//				setApplicationSubType(ApplicationSubType.FESTIVAL);
//				setApplicationType(ApplicationType.TEMP_CONN);
//				setPath(toApplicationTC);
//				setPrefix(getCostCenterNo()+"/"+PivPrefixType.APP_CR_JOB+"/"+getFormat().getYear()+"/");
//				setApplicationId(getPrefix()+"xxxx");
//				return "saveTC";
//			}else {
//				setApplicationType(ApplicationType.COST_RECOVERY);
//				setPath(toApplicationCR);
//				setPrefix(getCostCenterNo()+"/"+PivPrefixType.APP_TEMP_CONN+"/"+getFormat().getYear()+"/");
//				setApplicationId(getPrefix()+"xxxx");
//				return "saveCR";
//			}
//			
		} catch (RollbackException e) {
			setMessageType(MSG_ERROR);
			setState("newApplicant");
			e.printStackTrace();
			System.out.println("ERROR MESSEGE" + e.getMessage());
			Throwable lastCause = e;
			while (lastCause.getCause() != null)
			      lastCause = lastCause.getCause();
			 if (lastCause.getMessage().startsWith("ORA-00001"))
				 //throw new CustomerException("The Customer Id already exists.");
			     //System.out.println("ERROR MESSEGE1" + "the Customer Id already exists1111111111111");
			    setErrorMessage("ERROR:- " +"The Customer Id already exists.");
			 else
				 setErrorMessage("ERROR MESSEGE " + e.getMessage()); 
			 return ERROR;
			
		} catch (PersistenceException e) {
			e.printStackTrace();
			setState("newApplicant");
			setMessageType(MSG_ERROR);
			System.out.println("ERROR:- " + e.getMessage());
			Throwable lastCause = e;
			while (lastCause.getCause() != null)
			      lastCause = lastCause.getCause();
			      if (lastCause.getMessage().startsWith("ORA-00001"))
			        //throw new CustomerException("The Customer Id already exists.");
			      //System.out.println("ERROR MESSEGE1" + "the Customer Id already exists1111111111111");
			    	  setErrorMessage("ERROR:- " +"The Customer Id already exists.");
			      else
			    	  setErrorMessage("ERROR:- " + e.getMessage());  
			return ERROR;
		} catch (Exception e) {
			setState("newApplicant");
			setMessageType(MSG_ERROR);
			e.printStackTrace();
			System.out.println("ERROR:- " + e.getMessage());
			Throwable lastCause = e;
			while (lastCause.getCause() != null)
			      lastCause = lastCause.getCause();
			      if (lastCause.getMessage().startsWith("ORA-00001"))
			        //throw new CustomerException("The Customer Id already exists.");
			      //System.out.println("ERROR MESSEGE1" + "the Customer Id already exists1111111111111");
			    	  setErrorMessage("ERROR:- " +"The Customer Id already exists.");
			      else
			    	  setErrorMessage("ERROR:- " + e.getMessage()); 
			return ERROR;
		}finally{
			//getEmf().close();
			//emf=null;
			format=null;
		}
	}
	public String saveNC(){
		
		
		return SUCCESS;
		
	}
	public String modify(){
		try {
			System.out.println("kjdksjfsdkjbfksdjbfdkjvbbv"+getCostCenterNo());
			setMessageType(MSG_NONE);
			setErrorMessage(null);
			setState("modifyApplicant");
			setLoggedData();
			setFormat(new Format());
			setStatus("M");
			updateData();
			setMessageType(MSG_DONE);
			setErrorMessage("DONE:- The Customer details Modified Successfully." ); 
			return "modify";
		} catch (RollbackException e) {
			e.printStackTrace();
			System.out.println("ERROR:- " + e.getMessage());
			Throwable lastCause = e;
			while (lastCause.getCause() != null)
			      lastCause = lastCause.getCause();
			      if (lastCause.getMessage().startsWith("ORA-00001"))
			        //throw new CustomerException("The Customer Id already exists.");
			      System.out.println("ERROR:- " + "The Customer Id already exists1111111111111");
			setMessageType(MSG_ERROR);
			setErrorMessage("ERROR:- RollbackException. Contact MIS IT staff" ); 
			return ERROR;
			
		} catch (PersistenceException e) {
			e.printStackTrace();
			System.out.println("ERROR:- " + e.getMessage());
			setMessageType(MSG_ERROR);
			setErrorMessage("ERROR:- PersistenceException. Contact MIS IT staff" ); 
			return ERROR;
		} catch (Exception e) {
			e.printStackTrace();
			System.out.println("ERROR:- " + e.getMessage());
			setMessageType(MSG_ERROR);
			setErrorMessage("ERROR:- Exception. Contact MIS IT staff" ); 
			return ERROR;
		}finally{
			//getEmf().close();
			//emf=null;
			format=null;
		}
	}

	public String next() {
		try {
		setState("newCustomer");
		setLoggedData();
		setFormat(new Format());
		//setEmf(Persistence.createEntityManagerFactory("SMC"));
		setIdNo(idNo);
		//setC
		saveData();
		setDate(getFormat().FormatDate());
		setListTariffCode(getTafiffCode());
		setListTariffCatCode(getTafiffCaTCode());
		if  (smcType.equals("NC")){
			return "next";
		}else 
			return "nextTC";
		 
		
		} catch (RollbackException e) {
			e.printStackTrace();
			System.out.println("ERROR MESSEGE" + e.getMessage());
			Throwable lastCause = e;
			while (lastCause.getCause() != null)
			      lastCause = lastCause.getCause();
			      if (lastCause.getMessage().startsWith("ORA-00001"))
			        //throw new CustomerException("The Customer Id already exists.");
			      System.out.println("ERROR MESSEGE" + "he Customer Id already exists1111111111111");
			return ERROR;
			
		} catch (PersistenceException e) {
			e.printStackTrace();
			System.out.println("ERROR MESSEGE" + e.getMessage());
			return ERROR;
		} catch (Exception e) {
			e.printStackTrace();
			System.out.println("ERROR MESSEGE" + e.getMessage());
			return ERROR;
		}finally{
			//getEmf().close();
			//emf=null;
			format=null;
		}
	}
	
	
	
	public String close() {
		setLoggedData();
		//getEmf().close();
		//emf=null;
		return "close";
	}
	
	private void saveData() {
		log.info("save");
		//GldeptmEjb gldeptmEjb=new GldeptmEjb(getSessionKey("region"));
		ApplicantEjb applicantEjb=new ApplicantEjb(getSessionKey("region"));
		//ApplicantDao applicantDao = new ApplicantDao(getEmf());
		//Format format = new Format();
		Applicant applicant = new Applicant();
		applicant=createApplicantObject(applicant);
		log.info(applicant);
		
		//Applicant applicant1 =new Applicant("9876543","NIC", "Dileepa", "Waduge", "92", "Dalugama", "Kelaniya",null, null, null, "wadda", "si", "Y", "N", null, null, null, null, null, null);
		//applicantEjb.createApplicant(applicant1);
		//applicantDao.createApplicant(applicant);
		applicantEjb.createApplicant(applicant);
		//applicantEjb.
		//applicantDao=null;
		applicant=null;
		

	}
	
	private void updateData() {
		log.info("updateData");
		//GldeptmEjb gldeptmEjb=new GldeptmEjb(getSessionKey("region"));
		ApplicantEjb applicantEjb=new ApplicantEjb(getSessionKey("region"));
		//ApplicantDao applicantDao = new ApplicantDao(getEmf());
		//Format format = new Format();
		Applicant applicant = new Applicant();
		applicant=createApplicantObject(applicant);
		log.info(applicant);
		//Applicant applicant1 =new Applicant("9876543","NIC", "Dileepa", "Waduge", "92", "Dalugama", "Kelaniya",null, null, null, "wadda", "si", "Y", "N", null, null, null, null, null, null);
		//applicantEjb.createApplicant(applicant1);
		//applicantDao.createApplicant(applicant);
		applicantEjb.updateApplicant(applicant);
		//applicantEjb.
		//applicantDao=null;
		applicant=null;
		

	}
	
	
	
	private Applicant createApplicantObject(Applicant applicant){
		log.info("createApplicantObject");
		Calendar calendar = Calendar.getInstance();
		applicant.setIdNo(getIdNo());
		applicant.setPersonalCorporate(personalCorporate);
		log.info("createApplicantObject");
		log.info(applicant);
		applicant.setIdType(getIdType());
		applicant.setCompanyName(companyName); 
		applicant.setFullName(fullName);
		//applicant.set
		applicant.setDeptId(costCenterNo);
		applicant.setFirstName(getFirstName());
		applicant.setLastName(getLastName());
		applicant.setStreetAddress(getStreetAddress());
		applicant.setSuburb(getSuburb());
		
		if(isColombo==null)
			applicant.setCity(getCity());
		else if(isColombo.equals("Y"))		
			applicant.setCity(getCity());
		else
			applicant.setCity(getOthercity());
		
		//log.info("3333333333333333333333333333333333");
		applicant.setPostalCode(getPostalCode());
		//log.info("44444444444444444444444444444444444");
		applicant.setTelephoneNo(getTelephoneNo());
		//log.info("5555555555555555555555555555555555555");
		applicant.setMobileNo(getMobileNo());
		//log.info("666666666666666666666666666666666666");
		applicant.setEmail(getEmail());
		applicant.setPreferredLanguage(getPreferefLanguage());
		applicant.setCebEmployee(getCebEmployee());
		applicant.setStatus(getStatus());
		applicant.setAddUser(getLoggedInUserId());
		applicant.setAddDate(calendar.getTime());
		applicant.setAddTime(getFormat().FormatTime());
		applicant.setUpdUser(null);
		applicant.setUpdTime(null);
		applicant.setUpdDate(null);
		//applicant.setEntitled4Loan(getEntitled4Loan());
		//applicant.setLoanReference(getLoanReference());
		//if (getLoanAmount().trim().length()!=0){
		//	applicant.setLoanAmount(new BigDecimal(getLoanAmount()));
		//}
		//applicant.setMemberOfSamurdhi(getMemberOfSamurdhi());
		//applicant.setSamurdhiId(getSamurdhiId());
		//if (getSharePrice().trim().length()!=0)
		//applicant.setSharePrice(new BigDecimal(getSharePrice()));
		//else 
		//	applicant.setSharePrice(new BigDecimal(0));
		//if (getNoOfShares().trim().length()!=0)
		//applicant.setNoOfShares(new BigDecimal(getNoOfShares()));
		//else applicant.setNoOfShares(new BigDecimal(0));
		calendar=null;
		return applicant;
		
		
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

	public String getDate() {
		return date;
	}

	public void setDate(String date) {
		this.date = date;
	}
	public String getPath() {
		return path;
	}

	public void setPath(String path) {
		this.path = path;
	}

	/*public EntityManagerFactory getEmf() {
		return emf;
	}


	public void setEmf(EntityManagerFactory emf) {
		this.emf = emf;
	}*/

	
	public Format getFormat() {
		return format;
	}



	public void setFormat(Format format) {
		this.format = format;
	}

	public String getStatus() {
		return status;
	}

	
	public void setStatus(String status) {
		this.status = status;
	}

	public String getAddUser() {
		return addUser;
	}

	public void setAddUser(String addUser) {
		this.addUser = addUser;
	}

	public String getAddDate() {
		return addDate;
	}

	public void setAddDate(String addDate) {
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

	public String getUpdDate() {
		return updDate;
	}

	public void setUpdDate(String updDate) {
		this.updDate = updDate;
	}

	public String getUpdTime() {
		return updTime;
	}

	public void setUpdTime(String updTime) {
		this.updTime = updTime;
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

	public String getIdNo() {
		return idNo;
	}

	public void setIdNo(String idNo) {
		this.idNo = idNo;
	}

	public String getState() {
		return state;
	}
	
	public String getEntitled4Loan() {
		return entitled4Loan;
	}



	public void setEntitled4Loan(String entitled4Loan) {
		this.entitled4Loan = entitled4Loan;
	}



	public String getMemberOfSamurdhi() {
		return memberOfSamurdhi;
	}



	public void setMemberOfSamurdhi(String memberOfSamurdhi) {
		this.memberOfSamurdhi = memberOfSamurdhi;
	}



	public String getSamurdhiId() {
		return samurdhiId;
	}



	public void setSamurdhiId(String samurdhiId) {
		this.samurdhiId = samurdhiId;
	}



	public String getSharePrice() {
		return sharePrice;
	}



	public void setSharePrice(String sharePrice) {
		this.sharePrice = sharePrice;
	}



	public String getNoOfShares() {
		return noOfShares;
	}



	public void setNoOfShares(String noOfShares) {
		this.noOfShares = noOfShares;
	}


	public void setState(String state) {
		this.state = state;
	}

	@Override
	public void setSession(Map<String, Object> session) {
		this.session = session;

	}

	public String getLoggedInUserId() {
		return loggedInUserId;
	}

	public void setLoggedInUserId(String loggedInUserId) {
		this.loggedInUserId = loggedInUserId;
	}

	public Map<String, Object> getSession() {
		return session;

	}
	
	public Map<String, String[]> getParameters() {
		return parameters;
	}
	
	@Override
	public void setParameters(Map<String, String[]> parameters) {
		this.parameters=parameters;
		
		
	}
	
	public String getHiddenSecondState() {
		return hiddenSecondState;
	}

	public void setHiddenSecondState(String hiddenSecondState) {
		this.hiddenSecondState = hiddenSecondState;
	}

	public String getLoanReference() {
		return loanReference;
	}

	public void setLoanReference(String loanReference) {
		this.loanReference = loanReference;
	}

	public String getLoanAmount() {
		return loanAmount;
	}

	public void setLoanAmount(String loanAmount) {
		this.loanAmount = loanAmount;
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
	
	public String getSmcType() {
		return smcType;
	}
	public void setSmcType(String smcType) {
		this.smcType = smcType;
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

	public String getInfo() {
		return info;
	}

	public void setInfo(String info) {
		this.info = info;
	}
	
	public String getPrefix() {
		return prefix;
	}



	public String getHid_city() {
		return hid_city;
	}



	public void setHid_city(String hid_city) {
		this.hid_city = hid_city;
	}



	public void setPrefix(String prefix) {
		this.prefix = prefix;
	}
	
	public String getApplicationId() {
		return ApplicationId;
	}

	public void setApplicationId(String applicationId) {
		ApplicationId = applicationId;
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

	public String getPhase() {
		return phase;
	}

	public void setPhase(String phase) {
		this.phase = phase;
	}

	public String getConnectionType() {
		return connectionType;
	}

	public void setConnectionType(String connectionType) {
		this.connectionType = connectionType;
	}

	public String getOthercity() {
		return othercity;
	}

	public void setOthercity(String othercity) {
		this.othercity = othercity;
	}



	public String getPreparedBy() {
		return preparedBy;
	}

	public void setPreparedBy(String preparedBy) {
		this.preparedBy = preparedBy;
	}
	
	public String getIsGovernmentPlace() {
		return isGovernmentPlace;
	}

	public void setIsGovernmentPlace(String isGovernmentPlace) {
		this.isGovernmentPlace = isGovernmentPlace;
	}
	public String getTariffCategoryCode() {
		return tariffCategoryCode;
	}

	public void setTariffCategoryCode(String tariffCategoryCode) {
		this.tariffCategoryCode = tariffCategoryCode;
	}
	public String getApplicationSubType() {
		return applicationSubType;
	}

	public void setApplicationSubType(String applicationSubType) {
		this.applicationSubType = applicationSubType;
	}

	public String getApplicationType() {
		return applicationType;
	}

	public void setApplicationType(String applicationType) {
		this.applicationType = applicationType;
	}
	
	public String getCompanyName() {
		return companyName;
	}

	public void setCompanyName(String companyName) {
		this.companyName = companyName;
	}
	public String getFullName() {
		return fullName;
	}

	public void setFullName(String fullName) {
		this.fullName = fullName;
	}
	public String getIsLoanApp() {
		return isLoanApp;
	}

	public void setIsLoanApp(String isLoanApp) {
		this.isLoanApp = isLoanApp;
	}
	
	public String getIsColombo() {
		return isColombo;
	}

	public void setIsColombo(String isColombo) {
		this.isColombo = isColombo;
	}
	
	public String getWebAppName() {
		return webAppName;
	}

	public void setWebAppName(String webAppName) {
		this.webAppName = webAppName;
	}
	
	public String getTariffCode() {
		return tariffCode;
	}

	public void setTariffCode(String tariffCode) {
		this.tariffCode = tariffCode;
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
	
	public String getPersonalCorporate() {
		return personalCorporate;
	}

	public void setPersonalCorporate(String personalCorporate) {
		this.personalCorporate = personalCorporate;
	}
	public List<String> getAreaCodeList() {
		areaCodeList.add("--Select--");
		ApplicationTransactionEjb transactionEjb=new ApplicationTransactionEjb(getSessionKey("region"));
		List<String> areaCodes = transactionEjb.findAreaCodeNames(getCostCenterNo(), getSessionKey("region"));
		//List<String> areaCodes =new ArrayList<String>();
		//areaCodes.add("510.00");
		if(areaCodes == null){
			areaCodeList = new ArrayList<String>();
			if(getCostCenterNo() != null){
				areaCodeList.add(getCostCenterNo());
			}
		}else{
			areaCodeList.addAll(areaCodes);
		}
		return areaCodeList;
		
	}

	public void setAreaCodeList(List<String> areaCodeList) {
		this.areaCodeList = areaCodeList;
	}
	public List<String> getFoundSourceList() {
		ApplicationTransactionEjb transactionEjb=new ApplicationTransactionEjb(getSessionKey("region"));
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
	
	public List<String> getDistrictCodeList() {
		ProvinceDetailsMasterEjb provinceDetailsMasterEjb=new ProvinceDetailsMasterEjb();
		districtCodeList = provinceDetailsMasterEjb.getAllCodes("DISTRICT", getCostCenterNo(), getSessionKey("region"));
		if(districtCodeList == null){
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
		if(cscCodeList == null){
			cscCodeList = new ArrayList<String>();
			cscCodeList.add("--Select--");
		}
		
		return cscCodeList;
		
	}

	public void setCscCodeList(List<String> cscCodeList) {
		this.cscCodeList = cscCodeList;
	}
		  
		 
}
