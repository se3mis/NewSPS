package pivBS.web;

import java.math.BigDecimal;
import java.text.NumberFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.Iterator;
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
import offDoc.web.PayingInVoucher;
import offDoc.web.PrintPiv;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.apache.struts2.ServletActionContext;
import org.apache.struts2.interceptor.ParameterAware;
import org.apache.struts2.interceptor.SessionAware;

import piv.dao.PivDetailDao;
import piv.model.PivDetail;
import piv.model.PivDetailPK;
import piv.service.PivDetailEjb;
import piv.service.PivEjb;
import piv.service.PivTransactionEjb;
import security.service.SecurityEjb;
import util.common.AppStatus;
import util.common.ApplicationType;
import util.common.Constants;
import util.common.Format;
import util.common.NumberToWordsConverter;
import util.common.PIVStatus;
import util.common.PivPrefixType;
import util.common.ReferenceType;
import util.common.StandardEstimateStatus;
import util.common.UserRole;
import application.model.Applicant;
import application.model.Application;
import application.model.ApplicationPK;
import application.model.ApplicationReference;
import application.model.CityMap;
import application.model.WiringLandDetail;
import application.model.WiringLandDetailPK;
import application.service.ApplicantEjb;
import application.service.ApplicationEjb;
import application.service.ApplicationReferenceEjb;
import application.service.WiringLandDetailEjb;

import com.opensymphony.xwork2.ActionSupport;

import costcenter.model.Gldeptin;
import costcenter.service.CostCenterEjb;
import estimate.model.ApplicationDisplay;
import estimate.model.EstimateDisplay;
import estimate.model.Spestedy;
import estimate.model.Spstdesthmt;
import estimate.model.SpstdesthmtPK;
import estimate.service.EstimateEjb;
import estimate.service.SpestedyEjb;
import estimate.service.SpstdesthmtEjb;

@SuppressWarnings("serial")
public class PivIIForm extends ActionSupport implements SessionAware, ParameterAware{
	private static final Log log = LogFactory.getLog(PivIIForm.class);
	private static final String VIEWPATH="Estimate>View PIV";
	private static final String MODIFYPATH="Estimate>Modify PIV";
	private static final String PRINTPATH="Estimate>Print PIV";
	private static final String NEWPATH="Estimate>New PIV";
	private static final String CONFIRMPATH="Estimate>Confirm PIV";
	private static final String ISSUEREINSPPATH="Estimate>Issue PIV";
	private static final String CONFIRMREINSPPATH="Estimate>Confirm  PIV";
	private static final String ISSUEREINSPAFTERESTIMATEDPATH="Estimate>Issue PIV After Estimated";
	private static final String NEWSTATUS=PIVStatus.NEW;
	private static final String CONFIRMSTATUS=PIVStatus.CONFIRMED;
	private static final String PAIDSTATUS=PIVStatus.PAID;
	private static final String APPCONFIRMSTATUS=AppStatus.CONFIRMED;
	private static final String MSG_ERROR="ERROR";
	private static final String MSG_INFO="INFO";
	private static final String MSG_DONE="DONE";
	private static final String MSG_NONE="NONE";
	private String messageType=MSG_NONE;
	private Map<String, Object> session;
	private Map <String, String[]> parameters;
    private String loggedInUserId;
    private String path;
    private String serviceStreetAddress;
	private String serviceSuburb;
	private String serviceCity;
	private String servicePostalCode;
	private String pivSubType;
	private String hdnChequeBranchCode;
	// Persistence
	private EntityManagerFactory emf;
	private EntityManager em;
	//Date
	private Format format;
    
    
    
	//JSP Fields
    private String pivNo;
    private String pivReceiptNo;
    private String pivSeqNo;
    private String payingBankCode;
    private String payingBranchCode;
    private String payingBankName;
    private String payingBranchName;
    private String cebBranch;
    private String costCenterNo;
    private String date;
    private String referenceNo;
    private String idNo;
    private String fullName;
    private String fullAddress;
    private String chequeBankCode;
    private String chequeBranchCode;
    private String paymentMode;
    private String chequeNo;
    private String amountInWords;
    private String preparedBy;
    private String certifiedBy;
    private String description;
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
    private String nbt;
    private String grandTotal;
    private String applicationFee;
    private String loanReference;
    private String loanAmount;
    //Hidden Fields
    private String hiddenPaymentMode;
    private String state;
    private String hiddenSecondState;
    //Other Fields
    private String status;
    private String confirmedBy;
    private Date confirmedDate;
    private String confirmedTime;
    private String updUser;
    private Date updDate;
    private String updTime;
   // private String  chequeDate;
   // private String  payDate;
    //
    private String payDate; 
   	private String chequeDate; 
    private String errorMessage;
	private List<Bank> bankList; 
	private List<BankBranch> branchList;
	private List<String> applicationIdList;
	private List<String> applicationNoList;
	private List<String> tariffCategoryCode;
	private String applicationId; 

	

	private List<String> tariffCode;
	//
	private String firstName;
	private String lastName;
	private String streetAddress;
	private String suburb;
	private String city;
	private String postalCode;
	private String region;
	
	

	/**
    *
    */
    public PivIIForm() {
		super();
		//setBankList(getAllBankList());
		setFormat(new Format());
		//setCostCenterNo(getSessionKey("costCenterNo"));
		//setPivNo("APP/"+getCostCenterNo()+"/"+getFormat().getYear()+"/xxxx");
		//CostCenterEjb ejbCC=new CostCenterEjb(getSessionKey("region"));
        //Gldeptin gldeptin=ejbCC.findById(getCostCenterNo());
        //setPayingBankCode(gldeptin.getBankCode());
        //(gldeptin.getBranchCode());
		//setEmf(Persistence.createEntityManagerFactory("SMC"));
		//setFormat(new Format());
		// setDate(getFormat().FormatDate());

	} 
    
   public void setLoggedData() {
       log.info( getSession());
       CostCenterEjb ejb=new CostCenterEjb(getSessionKey("region"));
       System.out.println(getSession());
       setLoggedInUserId(getSessionKey("userName"));
       setCostCenterNo(getSessionKey("costCenterNo"));
       //System.out.println(" getCostCenterNo() "+getCostCenterNo());
       //PIV data			
       SecurityEjb securityEjb=new SecurityEjb(getSessionKey("region"));
	   Gldeptin gldeptin=ejb.findById(securityEjb.getCostCenter(getSessionKey("userName")));
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
       //System.out.println(" gldeptin() "+gldeptin);
       //System.out.println(gldeptin.getBankCode()+" "+gldeptin.getBranchCode());
       setCebBranch(getSessionKey("costCenterName"));
       setRegion(getSessionKey("region"));
   }
   
   private List<Bank> getAllBankList(){
	   BankEjb ejb=new BankEjb(getSessionKey("region"));
	   return ejb.getAllBanks();
   }
   
   public String getSessionKey(String key) {
       return getSession().get(key).toString();
   }
    
    

	public String execute(){
    	log.info("$$$$$$$$$$$$$$$$$$$$$ execute()");
		setLoggedData();
		//ApplicationEjb ejb=new ApplicationEjb(getSessionKey("region"));
		//setPivNo("PIV/"+getCostCenterNo()+"/"+PivPrefixType.APP_NEW_CONN+"/"+getFormat().getYear()+"/xxxx");
    	//setState("view");
    	//setPath(VIEWPATH);
		System.out.println("x;kvjsdlkglxdkvlxcnv"+state);
    	setCostCenterNo(getSessionKey("costCenterNo"));
    	if (state.equals("new")){
    		//setState("new1");
    		applicationIdList=new ArrayList<String>();
	    	//applicationIdList=ejb.getApplicationIdList(costCenterNo);
	    	//List<String> list=new ArrayList<String>();
	    	//list.add(getApplicationIdList());
    		applicationIdList.add(referenceNo);
    		System.out.println(applicationIdList);
    		setApplicationIdList(applicationIdList);
    		return findByReferenceNo();
    	}else if (state.equals("issueReInsp")){	
    		applicationNoList=new ArrayList<String>();
    		applicationNoList.add(referenceNo);
    		setApplicationNoList(applicationNoList);
    		return findByReferenceNo();
    	}else if (state.equals("afterestimated")){	
    		applicationNoList=new ArrayList<String>();
    		applicationNoList.add(referenceNo);
    		setApplicationNoList(applicationNoList);
    		return findByReferenceNo();	
    		
    	}else {
    		System.out.println("2222222222222222222222");
    		return ERROR;
    	}
    	
    }
	
	//EJB
	public String  saveAfter(){
		//setBankList(getAllBankList());
		return save();
	}
	
	public String save(){
		System.out.println("start save" + state);
		HttpServletRequest request = ServletActionContext.getRequest();
		log.info("start save() 0");
		PivDetailEjb ejb;
		PivTransactionEjb  pivTransactionEjb=new PivTransactionEjb(getSessionKey("region"));
		try{
			setBankList(getAllBankList());
			CityMap cityMap=new CityMap();
			//setParameters(parameters);
			setLoggedData();
			setFormat(new Format());
			//Calendar calendar =Calendar.getInstance();
						
			log.info("serviceCity "+serviceCity);
			setCostCenterNo(cityMap.mapArea(costCenterNo, serviceCity));
			log.info("start save()");
			ejb=new PivDetailEjb(getSessionKey("region"));
			log.info("start save()1"+state);
			PivDetail pivDetail=null;
			String newId=null;
			if (state.equals("issueReInsp") || state.equals("afterestimatedDone")){	
				pivDetail=CreatePivDetailModelREINSP();
				log.info(pivDetail);
				newId =ejb.createPivAutoId(pivDetail, "PIV/"+getCostCenterNo()+"/"+ReferenceType.ESTIMATE+"/"+getFormat().getYear()+"/");
				setPivNo(newId);
			}else{
				if (paymentMode == null  ){
    				setErrorMessage("Payment mode must be filled out.");
	    			applicationIdList=new ArrayList<String>();
	    	    	//applicationIdList=ejb.getApplicationIdList(costCenterNo);
	    	    	//List<String> list=new ArrayList<String>();
	    	    	//list.add(getApplicationIdList());
	        		applicationIdList.add(referenceNo);
	    			setFullName(getFullName());
	    			setFullAddress(getFullAddress());
	    			setHiddenSecondState("findPivNo");
	    			setState("confirm");
	    			return ERROR;
    			}
				pivDetail=CreatePivDetailModel();
				System.out.println("finished save" + pivDetail.getReferenceType());
				log.info(pivDetail);
				ejb.createPiv(pivDetail);
				
				//
				
				SpstdesthmtPK spstdesthmtPK = new SpstdesthmtPK();
				spstdesthmtPK.setDeptId(costCenterNo);
				spstdesthmtPK.setStdNo(getReferenceNo());
				spstdesthmtPK.setApplicationNo(getReferenceNo());
				SpstdesthmtEjb spstdesthmtEjb = new SpstdesthmtEjb(getSessionKey("region"));
				Spstdesthmt spstdesthmt = spstdesthmtEjb.findById(spstdesthmtPK, getSessionKey("region"));
				if(spstdesthmt != null){
					if(getPaymentMode() != null && getPaymentMode().equalsIgnoreCase(Constants.PAYMENT_MODE_CHEQUE)){
						spstdesthmt.setStatus(StandardEstimateStatus.PIVII_TO_BE_CONFIRMED_BY_ACCOUNT_REV.getKey());
					} else{
						spstdesthmt.setStatus(StandardEstimateStatus.PIVII_TO_BE_CONFIRMED_BY_DEO.getKey());
					}
					pivTransactionEjb.confirmPIV2(pivDetail, spstdesthmt);
				}else{
					pivTransactionEjb.confirmPIV2(pivDetail, null);
				}
				setPivNo(pivDetail.getId().getPivNo());///////Don't know why
				setFullAddress(getFullAddress());
				
				//request.getSession().setAttribute("bankCode", pivDetail.getChequeBankCode());
				//request.getSession().setAttribute("branchCode", pivDetail.getChequeBranchCode());
				
				//log.info("else " +pivNo);
				//log.info("else " +getPivNo());
				//log.info("else " +idNo);
				//log.info("else " +getIdNo());
			}
			log.info("start save()2");
			setState("save");
			log.info(state);
			//ejb.createPiv(pivDetail);
			//ejb.createPivAutoId(pivDetail, "APP/"+getCostCenterNo()+"/"+getFormat().getYear()+"/");
			//print(pivDetail);
			setMessageType(MSG_DONE);
			setErrorMessage("PIV Details successfully saved");
			return "save";
		} catch (RollbackException e) {
			e.printStackTrace();
			System.out.println("ERROR MESSEGE" + e.getMessage());
			setErrorMessage(e.getMessage());
			setFullName(getFullName());
			setFullAddress(getFullAddress());
			return ERROR;
		} catch (PersistenceException e) {
			e.printStackTrace();
			System.out.println("ERROR MESSEGE" + e.getMessage());
			setErrorMessage(e.getMessage());
			setFullName(getFullName());
			setFullAddress(getFullAddress());
			return ERROR;
		} catch (Exception e) {
			e.printStackTrace();
			System.out.println("ERROR MESSEGE" + e.getMessage());
			setErrorMessage(e.getMessage());
			setFullName(getFullName());
			setFullAddress(getFullAddress());
			applicationIdList=new ArrayList<String>();
	    	//applicationIdList=ejb.getApplicationIdList(costCenterNo);
	    	//List<String> list=new ArrayList<String>();
	    	//list.add(getApplicationIdList());
    		applicationIdList.add(referenceNo);
    		System.out.println(applicationIdList);
    		setApplicationIdList(applicationIdList);
			return ERROR;
		}finally{
			////getEm().close();
			//getEmf().close();
			//emf=null;
			//em=null;
			format=null;
		}
    }
	
	
	public String  modify(){
		HttpServletRequest request = ServletActionContext.getRequest();
		//String sessionKey= (String) request.getSession().getAttribute("region");
		pivSubType=request.getParameter("pivSubType");
		//String costCen=request.getParameter("costCen");
		if(pivSubType == null){
			pivSubType = Constants.PIV_SUB_TYPE_TOTAL;
		}
		
		setLoggedData();
		PivDetailPK pivDetailPK=null;
		PivDetail pivDetail=null;
		PivDetailEjb pivDetailEjb=null;
		pivDetailPK=new PivDetailPK();
		pivDetail=new PivDetail();
		Calendar calendar=Calendar.getInstance();
		try{
			setBankList(getAllBankList());
			pivDetailEjb=new PivDetailEjb(getSessionKey("region"));
			pivDetailPK.setPivNo(getPivNo());
	    	pivDetailPK.setDeptId(getCostCenterNo());
	    	pivDetail=pivDetailEjb.findById(pivDetailPK);
	    	if (pivDetail!=null){
	    		pivDetail.setPivReceiptNo(pivReceiptNo);
	    		pivDetail.setUpdUser(loggedInUserId);
	    		pivDetail.setUpdDate(calendar.getTime());
	    		pivDetail.setUpdTime(getFormat().FormatTime());
	    		if(getChequeBankCode() != null){
	    			pivDetail.setChequeBankCode(getChequeBankCode());
	    		}
	    		if(getChequeBranchCode() != null){
	    			pivDetail.setChequeBranchCode(getChequeBranchCode());
	    		}
	    		if(getChequeNo() != null){
	    			pivDetail.setChequeNo(new BigDecimal(getChequeNo()));
	    		}
	    		
	    		pivDetailEjb.updatePivDetail(pivDetail);
	    		setMessageType(MSG_DONE);
	    		setErrorMessage("Successfully Changed");
	    		state="modifyDirect";
	    		return SUCCESS;
	    		
	    	}else{
	    		setMessageType(MSG_ERROR);
	    		setErrorMessage("Piv No not found.");
	    		return ERROR;
	    	}
			
		}catch (Exception e) {
			return ERROR;
		}	
	}
	
	public String confirm(){
		HttpServletRequest request = ServletActionContext.getRequest();
		//String sessionKey= (String) request.getSession().getAttribute("region");
		pivSubType=(String) request.getSession().getAttribute("pivSubType");
		//String costCen=request.getParameter("costCen");
		if(pivSubType == null){
			pivSubType = Constants.PIV_SUB_TYPE_TOTAL;
		}
		
		PivDetailPK pivDetailPK=null;
		PivDetail pivDetail=null;
		//PivDetailDao pivDetailDao=null;
		Application application=null;
		ApplicationPK applicationPK=null;
		//ApplicationDao applicationDao=null;
		log.info("4444444444444444444444444444444444444444444444444");
		ApplicationEjb applicationEjb;
		PivDetailEjb pivDetailEjb;
		PivTransactionEjb pivTransactionEjb;
				
		try{
			setBankList(getAllBankList());
			setBranchList(getBranchList());
			applicationEjb=new ApplicationEjb(getSessionKey("region"));
			pivDetailEjb=new PivDetailEjb(getSessionKey("region"));
			pivTransactionEjb=new PivTransactionEjb(getSessionKey("region"));
			String loggedUserLevel = getSessionKey("userRole");
			//setParameters(parameters);
			setLoggedData();
			setFormat(new Format());
			//setEmf(Persistence.createEntityManagerFactory("SMC"));
			//setEm(emf.createEntityManager());
//			applicationPK=new ApplicationPK();
//			applicationPK.setApplicationId(getReferenceNo());
//			applicationPK.setDeptId(getCostCenterNo());
			pivDetailPK=new PivDetailPK();
			pivDetail=new PivDetail();
			//pivDetailDao=new PivDetailDao();
			String commCostcenter = null;
			if(loggedUserLevel.equals(UserRole.ACCOUNT_REVENUE)){
				commCostcenter = costCenterNo.substring(0, 3).concat(".00");
					
			}else{
				commCostcenter = costCenterNo;
			}
			
			pivDetailPK.setPivNo(getPivNo());
	    	pivDetailPK.setDeptId(commCostcenter);
	    	//pivDetail=pivDetailDao.findById(pivDetailPK,getEm());
	    	pivDetail=pivDetailEjb.findById(pivDetailPK);
	    	System.out.println("pivDetail MESSEGE" + pivDetail);
	    	//applicationDao=new ApplicationDao();
	    	//application=applicationDao.findByAppId(applicationPK, getEm());
	    	/*if (costCenterNo.equals("541.00") || costCenterNo.equals("542.00") || costCenterNo.equals("547.00") || costCenterNo.equals("548.00")) {
	    		application=applicationEjb.findByApplicationId(pivDetail.getReferenceNo());
	    	}else{
	    		application=applicationEjb.findByAppId(applicationPK);
	    	}*/
	    	SpstdesthmtPK spstdesthmtPK = new SpstdesthmtPK();
			spstdesthmtPK.setDeptId(commCostcenter);
			spstdesthmtPK.setStdNo(getReferenceNo());
			spstdesthmtPK.setApplicationNo(getReferenceNo());
			SpstdesthmtEjb spstdesthmtEjb = new SpstdesthmtEjb(getSessionKey("region"));
			Spstdesthmt spstdesthmt = spstdesthmtEjb.findById(spstdesthmtPK, getSessionKey("region"));
			
			//ApplicationReferenceEjb appReferenceEjb=new ApplicationReferenceEjb(getSessionKey("region"));
			//ApplicationReference applicationReference = appReferenceEjb.findByApplicationNo(pivDetail.getReferenceNo());
			
	    	//System.out.println("application MESSEGE" + application);
	    	if (pivDetail!=null){
	    		//if(spstdesthmt!=null){
	    			System.out.println("pivDetail.getPaidDate()" + getPayDate()+" "+ (getPayDate().length()<=8) );
	    			/*if (pivDetail.getPaymentMode() == null || pivDetail.getPaymentMode().length()==0 ){
	    				setErrorMessage("Payment mode must be filled out.");
		    			applicationIdList=new ArrayList<String>();
		    	    	//applicationIdList=ejb.getApplicationIdList(costCenterNo);
		    	    	//List<String> list=new ArrayList<String>();
		    	    	//list.add(getApplicationIdList());
		        		applicationIdList.add(referenceNo);
		    			setFullName(getFullName());
		    			setFullAddress(getFullAddress());
		    			setHiddenSecondState("findPivNo");
		    			setState("confirm");
		    			return ERROR;
	    			}*/
	    			if (getPayDate().length()>9 ){
		    			pivDetail=createPivDetailModelC(pivDetail);
		    			System.out.println("$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$");
		    			if(pivSubType.equalsIgnoreCase(Constants.PIV_SUB_TYPE_SEC_DEPOSIT)){
		    				//spstdesthmt.setStatus();
		    				pivTransactionEjb.confirmPIV2(pivDetail, null);
		    			}
		    			
		    			if(spstdesthmt!=null){
			    			if(pivSubType.equalsIgnoreCase(Constants.PIV_SUB_TYPE_TOTAL)){
			    				spstdesthmt.setStatus(StandardEstimateStatus.PIVII_CONFIRMATION.getKey());
			    				pivTransactionEjb.confirmPIV2(pivDetail, spstdesthmt);
			    			}else if(pivSubType.equalsIgnoreCase(Constants.PIV_SUB_TYPE_STESTMATE)){
			    				spstdesthmt.setStatus(StandardEstimateStatus.PIVII_CONFIRMATION.getKey());
			    				pivTransactionEjb.confirmPIV2(pivDetail, spstdesthmt);
			    			}
		    			}
		    			applicationIdList=new ArrayList<String>();
		    	    	//applicationIdList=ejb.getApplicationIdList(costCenterNo);
		    	    	//List<String> list=new ArrayList<String>();
		    	    	//list.add(getApplicationIdList());
		        		applicationIdList.add(referenceNo);
		    		
		    			System.out.println("#############################################################");
		    			//getEm().getTransaction().begin();
		    			//updatePivDetail(pivDetail,pivDetailDao);
		    			//updateApplicationStatus(application, applicationDao);
		    			//getEm().getTransaction().commit();
		    			if (state.equals("confirm")) {
		    				//TariffCategoryEjb ejb1=new TariffCategoryEjb(getSessionKey("region"));
		    				//tariffCategoryCode=ejb1.getTariffCategoryList();
		    				//setTariffCategoryCode(tariffCategoryCode);
		    				//TariffEjb ejb2=new TariffEjb(getSessionKey("region"));
		    				//tariffCode=ejb2.getTariffCodeList();
		    				//setTariffCode(tariffCode);
		    				session.put("estimateNo", referenceNo);
		    				//applicationId=pivNo;
		    				setMessageType(MSG_INFO);
		    				setErrorMessage("INFO:- Direct to Construction and Maintenance" );
		    				/*request.getSession().setAttribute("bankCode", pivDetail.getBankCode());
		    				request.getSession().setAttribute("branchCode", pivDetail.getBranchCode());*/
		    				if(pivSubType.equalsIgnoreCase(Constants.PIV_SUB_TYPE_SEC_DEPOSIT)){
		    					return "directSchedule";
		    				}if(loggedUserLevel.equals(UserRole.ACCOUNT_REVENUE)){
		    					return "directSchedule";
		    				}
		    				return "sendForCM";
		    			}else 
		    				return "directSchedule";
		    		}else {
		    			setErrorMessage("Pay Date must be filled out.");
		    			applicationIdList=new ArrayList<String>();
		    	    	//applicationIdList=ejb.getApplicationIdList(costCenterNo);
		    	    	//List<String> list=new ArrayList<String>();
		    	    	//list.add(getApplicationIdList());
		        		applicationIdList.add(referenceNo);
		    			setFullName(getFullName());
		    			setFullAddress(getFullAddress());
		    			setHiddenSecondState("findPivNo");
		    			setState("confirm");
		    			return ERROR;
		    		}
	    			
	    		/*}else{
	    			//getEm().getTransaction().rollback();
	    			setErrorMessage("Application Id not Found");
	    			setFullName(getFullName());
	    			setFullAddress(getFullAddress());
		    		return ERROR;
	    		}*/
	    	    		
	    	}else {
	    		//getEm().getTransaction().rollback();
	    		setErrorMessage("PIV not Found");
	    		setFullName(getFullName());
				setFullAddress(getFullAddress());
				applicationIdList=new ArrayList<String>();
		    	//applicationIdList=ejb.getApplicationIdList(costCenterNo);
		    	//List<String> list=new ArrayList<String>();
		    	//list.add(getApplicationIdList());
	    		applicationIdList.add(referenceNo);
	    		return ERROR;
	    	}
		} catch (RollbackException e) {
			e.printStackTrace();
			System.out.println("ERROR MESSEGE" + e.getMessage());
			setErrorMessage(e.getMessage());
			setFullName(getFullName());
			setFullAddress(getFullAddress());
			applicationIdList=new ArrayList<String>();
	    	//applicationIdList=ejb.getApplicationIdList(costCenterNo);
	    	//List<String> list=new ArrayList<String>();
	    	//list.add(getApplicationIdList());
    		applicationIdList.add(referenceNo);
			return ERROR;
		} catch (PersistenceException e) {
			e.printStackTrace();
			System.out.println("ERROR MESSEGE" + e.getMessage());
			setErrorMessage(e.getMessage());
			setFullName(getFullName());
			setFullAddress(getFullAddress());
			applicationIdList=new ArrayList<String>();
	    	//applicationIdList=ejb.getApplicationIdList(costCenterNo);
	    	//List<String> list=new ArrayList<String>();
	    	//list.add(getApplicationIdList());
    		applicationIdList.add(referenceNo);
			return ERROR;
		} catch (Exception e) {
			e.printStackTrace();
			System.out.println("ERROR MESSEGE" + e.getMessage());
			setErrorMessage(e.getMessage());
			setFullName(getFullName());
			setFullAddress(getFullAddress());
			applicationIdList=new ArrayList<String>();
	    	//applicationIdList=ejb.getApplicationIdList(costCenterNo);
	    	//List<String> list=new ArrayList<String>();
	    	//list.add(getApplicationIdList());
    		applicationIdList.add(referenceNo);
			return ERROR;
		}finally{
			//getEm().close();
			//getEmf().close(); 
			//log.info("getEmf() is closed");
			//em=null;
			//emf=null;
			format=null;
			pivDetailPK=null;
			pivDetail=null;
			//pivDetailDao=null;
			application=null;
			//applicationDao=null;
		}
	}
	
	public String confirmReInsp(){
		PivDetailPK pivDetailPK=null;
		PivDetail pivDetail=null;
		//PivDetailDao pivDetailDao=null;
		Application application=null;
		ApplicationPK applicationPK=null;
		//ApplicationDao applicationDao=null;
		log.info("4444444444444444444444444444444444444444444444444");
		//ApplicationEjb applicationEjb;
		PivDetailEjb pivDetailEjb;
		PivTransactionEjb pivTransactionEjb;
				
		try{
			setBankList(getAllBankList());
			//applicationEjb=new ApplicationEjb(getSessionKey("region"));
			pivDetailEjb=new PivDetailEjb(getSessionKey("region"));
			//pivTransactionEjb=new PivTransactionEjb(getSessionKey("region"));
			PivEjb pivEjb=new PivEjb(getSessionKey("region"));
			//setParameters(parameters);
			setLoggedData();
			setFormat(new Format());
			//setEmf(Persistence.createEntityManagerFactory("SMC"));
			//setEm(emf.createEntityManager());
			//applicationPK=new ApplicationPK();
			//applicationPK.setApplicationId(getReferenceNo());
			//applicationPK.setDeptId(getCostCenterNo());
			pivDetailPK=new PivDetailPK();
			pivDetail=new PivDetail();
			//pivDetailDao=new PivDetailDao();
			pivDetailPK.setPivNo(getPivNo());
	    	pivDetailPK.setDeptId(getCostCenterNo());
	    	//pivDetail=pivDetailDao.findById(pivDetailPK,getEm());
	    	pivDetail=pivDetailEjb.findById(pivDetailPK);
	    	//System.out.println("pivDetail MESSEGE" + pivDetail);
	    	//applicationDao=new ApplicationDao();
	    	//application=applicationDao.findByAppId(applicationPK, getEm());
	    	//application=applicationEjb.findByAppId(applicationPK);
	    	//System.out.println("application MESSEGE" + application);
	    	if (pivDetail!=null){
	    		//if(application!=null){
	    			System.out.println("pivDetail.getPaidDate()" + getPayDate()+" "+ (getPayDate().length()<=8) );
	    			if (getPayDate().length()>9){
		    			pivDetail=createPivDetailModelC(pivDetail);
		    			System.out.println("$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$");
		    			//application.setStatus(PAIDSTATUS);
		    			System.out.println("state"+state);
		    			SpestedyEjb spestedyEjb=new SpestedyEjb(getSessionKey("region"));
		    			Spestedy spestedy= spestedyEjb.getAppointment(costCenterNo, referenceNo);
		    			if (spestedy!=null){
		    				if (spestedy.getStatus().equals("V") || spestedy.getStatus().equals("E") ){
		    					pivDetail.setStatus(PIVStatus.CONFIRMED);
			    				pivEjb.updatePivDetail(pivDetail);
			    				return SUCCESS;
		    				}else{
		    					pivEjb.confirmReInspectionPiv(pivDetail);
		    					return "directSchedule";
		    				}
		    			}else{
		    				return ERROR;
		    			}
		    			
		    			//if (state.equals("afterestimatedDone")){
		    			//	System.out.println("afterestimatedDone");
		    			//	pivDetail.setStatus(PIVStatus.CONFIRMED);
		    			//	pivEjb.updatePivDetail(pivDetail);
		    			//}else{
		    			//	pivEjb.confirmReInspectionPiv(pivDetail);
		    			//}
		    			
		    			
		    			//System.out.println("#############################################################");
		    			//getEm().getTransaction().begin();
		    			//updatePivDetail(pivDetail,pivDetailDao);
		    			//updateApplicationStatus(application, applicationDao);
		    			//getEm().getTransaction().commit();
		    			//return "directSchedule";
		    			//return "confirm";
		    		}else {
		    			setErrorMessage("Pay Date must be filled out.");
		    			setFullName(getFullName());
		    			setFullAddress(getFullAddress());
		    			setHiddenSecondState("findPivNo");
		    			setState("confirm");
		    			return ERROR;
		    		}
	    			
	    	//	}else{
	    	//		//getEm().getTransaction().rollback();
	    	//		setErrorMessage("Application Id not Found");
	    	//		setFullName(getFullName());
	    	//		setFullAddress(getFullAddress());
		    //		return ERROR;
	    	//	}
	    	    		
	    	}else {
	    		//getEm().getTransaction().rollback();
	    		setErrorMessage("PIV not Found");
	    		setFullName(getFullName());
				setFullAddress(getFullAddress());
	    		return ERROR;
	    	}
		} catch (RollbackException e) {
			e.printStackTrace();
			System.out.println("ERROR MESSEGE" + e.getMessage());
			setErrorMessage(e.getMessage());
			setFullName(getFullName());
			setFullAddress(getFullAddress());
			return ERROR;
		} catch (PersistenceException e) {
			e.printStackTrace();
			System.out.println("ERROR MESSEGE" + e.getMessage());
			setErrorMessage(e.getMessage());
			setFullName(getFullName());
			setFullAddress(getFullAddress());
			return ERROR;
		} catch (Exception e) {
			e.printStackTrace();
			System.out.println("ERROR MESSEGE" + e.getMessage());
			setErrorMessage(e.getMessage());
			setFullName(getFullName());
			setFullAddress(getFullAddress());
			return ERROR;
		}finally{
			//getEm().close();
			//getEmf().close(); 
			//log.info("getEmf() is closed");
			//em=null;
			//emf=null;
			format=null;
			pivDetailPK=null;
			pivDetail=null;
			//pivDetailDao=null;
			//application=null;
			//applicationDao=null;
		}
	}
	
	
	
	public String newDirect(){
		try{
			HttpServletRequest request = ServletActionContext.getRequest();
			//String sessionKey= (String) request.getSession().getAttribute("region");
			pivSubType=request.getParameter("pivSubType");
			//String costCen=request.getParameter("costCen");
			if(pivSubType == null){
				pivSubType = Constants.PIV_SUB_TYPE_TOTAL;
			}
			request.getSession().setAttribute("pivSubType", pivSubType);
			setDescription("STANDARD ESTIMATION PAYMENT");
			setBankList(getAllBankList());
			//System.out.println("1");
			setLoggedData();
			//System.out.println("2");
			ApplicationEjb ejb=new ApplicationEjb(getSessionKey("region"));
			//System.out.println("3");
			setFormat(new Format());
			//System.out.println("4");
			setPivNo("PIV/"+getCostCenterNo()+"/"+PivPrefixType.getEST_XXX_Type(getSessionKey("smcType"))+"/"+getFormat().getYear()+"/xxxx");
			//System.out.println("5");
			setBankList(getAllBankList());
			//System.out.println("6");
			setState("new");
			//System.out.println("7");
			setPath(NEWPATH);
	    	applicationIdList=new ArrayList<String>();
	    	//System.out.println("8");
	    	//applicationIdList=ejb.getApplicationIdList(costCenterNo, getSessionKey("smcType"));
	    	
	    	EstimateEjb estimateEjb = new EstimateEjb(getSessionKey("region"));
			List<Long> status = new ArrayList<Long>();
			status.add(StandardEstimateStatus.APPROVED_ESTIMATES.getKey());
			/*status.add(StandardEstimateStatus.PIVII_TO_BE_CONFIRMED_BY_DEO.getKey());
			status.add(StandardEstimateStatus.PIVII_TO_BE_CONFIRMED_BY_ACCOUNT_REV.getKey());
			status.add(StandardEstimateStatus.PIVII_FOR_SECDEPOSIT.getKey());
			status.add(StandardEstimateStatus.PIVII_CONFIRMATION.getKey());
			status.add(StandardEstimateStatus.JOB_ALLOCATED.getKey());*/
			//status.add(StandardEstimateStatus.APPLICATION_MODIFIED.getKey());
			//applicationIdList = estimateEjb.loadStandEstmatenos(getCostCenterNo(),status,getRegion()); 
			List<EstimateDisplay> tesmpList = null;
			tesmpList   =  estimateEjb.getApprovedEstimateList(getCostCenterNo(),StandardEstimateStatus.APPROVED_ESTIMATES.getKey(), getSessionKey("smcType"), null,getSessionKey("region"));
			
			if(tesmpList != null){
				Iterator<EstimateDisplay> usit = tesmpList.iterator();
				
				if(applicationIdList == null){
					applicationIdList = new ArrayList<String>();
				}
				while (usit.hasNext()) {        	 
					EstimateDisplay esDis=  usit.next();
			        String name = esDis.getEstimateNo();
			        applicationIdList.add(name);
				} 
			}
			
			
			
			
			if(applicationIdList == null){
				applicationIdList = new ArrayList<String>();
			}
			
			
	    	//System.out.println("9");
	    	setApplicationIdList(applicationIdList);
	    	//System.out.println("10");
	    	return SUCCESS;
		}catch (Exception e) {
			return ERROR;
		}
	}
	public String newDirectSDeposit(){
		try{
			
			System.out.println("newDirectSDeposit " + state);
			HttpServletRequest request = ServletActionContext.getRequest();
			//String sessionKey= (String) request.getSession().getAttribute("region");
			pivSubType=request.getParameter("pivSubType");
			//String costCen=request.getParameter("costCen");
			if(pivSubType == null){
				pivSubType = Constants.PIV_SUB_TYPE_TOTAL;
			}
			System.out.println("pivSubType " + pivSubType);
			request.getSession().setAttribute("pivSubType", pivSubType);
			setDescription("STANDARD ESTIMATION PAYMENT");
			setBankList(getAllBankList());
			//System.out.println("1");
			setLoggedData();
			//System.out.println("2");
			ApplicationEjb ejb=new ApplicationEjb(getSessionKey("region"));
			//System.out.println("3");
			setFormat(new Format());
			//System.out.println("4");
			setPivNo("PIV/"+getCostCenterNo()+"/"+PivPrefixType.getEST_XXX_Type(getSessionKey("smcType"))+"/"+getFormat().getYear()+"/xxxx");
			//System.out.println("5");
			setBankList(getAllBankList());
			//System.out.println("6");
			setState("new");//gayani 03122013
			//System.out.println("7");
			setPath(NEWPATH);
	    	applicationIdList=new ArrayList<String>();
	    	//System.out.println("8");
	    	//applicationIdList=ejb.getApplicationIdList(costCenterNo, getSessionKey("smcType"));
	    	ApplicationEjb appEjb=new ApplicationEjb(getSessionKey("region"));
	    	EstimateEjb estimateEjb = new EstimateEjb(getSessionKey("region"));
			List<Long> status = new ArrayList<Long>();
			status.add(StandardEstimateStatus.APPROVED_ESTIMATES.getKey());
			status.add(StandardEstimateStatus.PIVII_TO_BE_CONFIRMED_BY_DEO.getKey());
			status.add(StandardEstimateStatus.PIVII_TO_BE_CONFIRMED_BY_ACCOUNT_REV.getKey());
			status.add(StandardEstimateStatus.PIVII_FOR_SECDEPOSIT.getKey());
			status.add(StandardEstimateStatus.PIVII_CONFIRMATION.getKey());
			status.add(StandardEstimateStatus.SEND_FOR_CONSTRUCTION_MAINTENANCE.getKey());
			status.add(StandardEstimateStatus.JOB_ALLOCATED.getKey());
			//status.add(StandardEstimateStatus.APPLICATION_MODIFIED.getKey());
			//gayani20140731
			//applicationIdList = estimateEjb.loadStandEstmatenos(getCostCenterNo(),status,getRegion());
			System.out.println("7" + status.toString());
			List<EstimateDisplay> list = new ArrayList<EstimateDisplay>();
			list = estimateEjb.getStdEstimateList(getCostCenterNo(),status, getSessionKey("smcType"), null,getSessionKey("region"));
			if(list != null){
				Iterator<EstimateDisplay> usit = list.iterator();
				
				if(applicationIdList == null){
					applicationIdList = new ArrayList<String>();
				}
				while (usit.hasNext()) {        	 
					EstimateDisplay esDis=  usit.next();
			        String name = esDis.getEstimateNo();
			        applicationIdList.add(name);
				} 
			}
			
			
			if(getSessionKey("smcType").equalsIgnoreCase(Constants.JOBTYPE_COMMERCIAL_CP)){
				List<ApplicationDisplay> appListCPJobs = appEjb.getToBeAllocatedApplicationList(getCostCenterNo(), AppStatus.ALLOCATED,Constants.JOBTYPE_COMMERCIAL_CP);
				 
				if(applicationIdList == null){
					applicationIdList = new ArrayList<String>();
				}
				if(appListCPJobs != null){
					for(ApplicationDisplay dis : appListCPJobs){
						applicationIdList.add(dis.getEstimateNo());
					}
					
				}
			}
			
	    	//System.out.println("9");
	    	setApplicationIdList(applicationIdList);
	    	//System.out.println("10");
	    	
	    	return SUCCESS;
		}catch (Exception e) {
			return ERROR;
		}
	}
	public String confirmDirect(){
		try{
			HttpServletRequest request = ServletActionContext.getRequest();
			
			//String sessionKey= (String) request.getSession().getAttribute("region");
			pivSubType=request.getParameter("pivSubType");
			String loggedUserLevel = getSessionKey("userRole");
			//String costCen=request.getParameter("costCen");
			if(pivSubType == null){
				pivSubType = Constants.PIV_SUB_TYPE_TOTAL;
			}
			request.getSession().setAttribute("pivSubType", pivSubType);
			setBankList(getAllBankList());
			setLoggedData();
			setCostCenterNo(getSessionKey("costCenterNo"));
			ApplicationEjb ejb=new ApplicationEjb(getSessionKey("region"));
			setFormat(new Format());
			String commCostcenter = null;
			if(loggedUserLevel.equals(UserRole.ACCOUNT_REVENUE)){
				commCostcenter = costCenterNo.substring(0, 3).concat(".00");
					
			}else{
				commCostcenter = costCenterNo;
			}
			
			String PIVNumber = request.getParameter("pivNo");
			if(PIVNumber != null){
				setPivNo(PIVNumber);
			}else{
				setPivNo("PIV/"+commCostcenter+"/"+PivPrefixType.getEST_XXX_Type(getSessionKey("smcType"))+"/"+getFormat().getYear()+"/xxxx");
				
			}
			
			
			setBankList(getAllBankList());
			setState("confirm");
	    	setPath(CONFIRMPATH);
	    	applicationIdList=new ArrayList<String>();
	    	//applicationIdList=ejb.getApplicationIdList(costCenterNo, getSessionKey("smcType"));
	    	EstimateEjb estimateEjb = new EstimateEjb(getSessionKey("region"));
			List<Long> status = new ArrayList<Long>();
			if(getSessionKey("userRole") != null && getSessionKey("userRole").equalsIgnoreCase(UserRole.ACCOUNT_REVENUE)){
				status.add(StandardEstimateStatus.PIVII_TO_BE_CONFIRMED_BY_ACCOUNT_REV.getKey());
			}else if(getSessionKey("userRole") != null && getSessionKey("userRole").equalsIgnoreCase(UserRole.DATA_ENTRY_OPERATOR)){
				status.add(StandardEstimateStatus.PIVII_TO_BE_CONFIRMED_BY_DEO.getKey());
			}else{
				status.add(StandardEstimateStatus.APPROVED_ESTIMATES.getKey());
			}
			//status.add(StandardEstimateStatus.APPLICATION_MODIFIED.getKey());
			applicationIdList = estimateEjb.loadStandEstmatenos(commCostcenter,status,getRegion()); 
			if(applicationIdList == null){
				applicationIdList = new ArrayList<String>();
			}
	    	setApplicationIdList(applicationIdList);
			return SUCCESS;
		}catch (Exception e) {
			return ERROR;
		}
	}
	
	public String viewDirect(){
		try{
			HttpServletRequest request = ServletActionContext.getRequest();
			//String sessionKey= (String) request.getSession().getAttribute("region");
			pivSubType=request.getParameter("pivSubType");
			//String costCen=request.getParameter("costCen");
			if(pivSubType == null){
				pivSubType = Constants.PIV_SUB_TYPE_TOTAL;
			}
			request.getSession().setAttribute("pivSubType", pivSubType);
			setBankList(getAllBankList());
			setLoggedData();
			setCostCenterNo(getSessionKey("costCenterNo"));
			ApplicationEjb ejb=new ApplicationEjb(getSessionKey("region"));
			setFormat(new Format());
			setPivNo("PIV/"+getCostCenterNo()+"/"+PivPrefixType.getEST_XXX_Type(getSessionKey("smcType"))+"/"+getFormat().getYear()+"/xxxx");
			setBankList(getAllBankList());
			setState("view");
	    	setPath(VIEWPATH);
	    	applicationIdList=new ArrayList<String>();
	    	//applicationIdList=ejb.getApplicationIdList(costCenterNo, getSessionKey("smcType"));
	    	EstimateEjb estimateEjb = new EstimateEjb(getSessionKey("region"));
			List<Long> status = new ArrayList<Long>();
			status.add(StandardEstimateStatus.APPROVED_ESTIMATES.getKey());
			//status.add(StandardEstimateStatus.APPLICATION_MODIFIED.getKey());
			applicationIdList = estimateEjb.loadStandEstmatenos(getCostCenterNo(),status,getRegion()); 
			if(applicationIdList == null){
				applicationIdList = new ArrayList<String>();
			}
	    	setApplicationIdList(applicationIdList);
			return SUCCESS;
		}catch (Exception e) {
			return ERROR;
		}
	}
	public String modifyDirect(){
		try{
			HttpServletRequest request = ServletActionContext.getRequest();
			//String sessionKey= (String) request.getSession().getAttribute("region");
			pivSubType=request.getParameter("pivSubType");
			//String costCen=request.getParameter("costCen");
			if(pivSubType == null){
				pivSubType = Constants.PIV_SUB_TYPE_TOTAL;
			}
			request.getSession().setAttribute("pivSubType", pivSubType);
			setBankList(getAllBankList());
			setLoggedData();
			setCostCenterNo(getSessionKey("costCenterNo"));
			ApplicationEjb ejb=new ApplicationEjb(getSessionKey("region"));
			setFormat(new Format());
			setPivNo("PIV/"+getCostCenterNo()+"/"+PivPrefixType.getEST_XXX_Type(getSessionKey("smcType"))+"/"+getFormat().getYear()+"/xxxx");
			setBankList(getAllBankList());
			setState("modifyDirect");
	    	setPath(MODIFYPATH);
	    	applicationIdList=new ArrayList<String>();
	    	//applicationIdList=ejb.getApplicationIdList(costCenterNo, getSessionKey("smcType"));
	    	EstimateEjb estimateEjb = new EstimateEjb(getSessionKey("region"));
			List<Long> status = new ArrayList<Long>();
			status.add(StandardEstimateStatus.APPROVED_ESTIMATES.getKey());
			//status.add(StandardEstimateStatus.APPLICATION_MODIFIED.getKey());
			applicationIdList = estimateEjb.loadStandEstmatenos(getCostCenterNo(),status,getRegion()); 
			if(applicationIdList == null){
				applicationIdList = new ArrayList<String>();
			}
	    	setApplicationIdList(applicationIdList);
			return SUCCESS;
		}catch (Exception e) {
			return ERROR;
		}
	}
	
	
	public String printDirect(){
		try{
			HttpServletRequest request = ServletActionContext.getRequest();
			//String sessionKey= (String) request.getSession().getAttribute("region");
			pivSubType=request.getParameter("pivSubType");
			//String costCen=request.getParameter("costCen");
			if(pivSubType == null){
				pivSubType = Constants.PIV_SUB_TYPE_TOTAL;
			}
			request.getSession().setAttribute("pivSubType", pivSubType);
			setDescription("APPLICATION FEE");
			setBankList(getAllBankList());
			setLoggedData();
			setCostCenterNo(getSessionKey("costCenterNo"));
			ApplicationEjb ejb=new ApplicationEjb(getSessionKey("region"));
			setFormat(new Format());
			setPivNo("PIV/"+getCostCenterNo()+"/"+PivPrefixType.getEST_XXX_Type(getSessionKey("smcType"))+"/"+getFormat().getYear()+"/xxxx");
			setBankList(getAllBankList());
			setState("printPIV");
	    	setPath(PRINTPATH);
	    	applicationIdList=new ArrayList<String>();
	    	//applicationIdList=ejb.getApplicationIdList(costCenterNo, getSessionKey("smcType"));
	    	EstimateEjb estimateEjb = new EstimateEjb(getSessionKey("region"));
			List<Long> status = new ArrayList<Long>();
			status.add(StandardEstimateStatus.APPROVED_ESTIMATES.getKey());
			//status.add(StandardEstimateStatus.APPLICATION_MODIFIED.getKey());
			applicationIdList = estimateEjb.loadStandEstmatenos(getCostCenterNo(),status,getRegion()); 
			if(applicationIdList == null){
				applicationIdList = new ArrayList<String>();
			}
	    	setApplicationIdList(applicationIdList);
			return SUCCESS;
		}catch (Exception e) {
			return ERROR;
		}
	}
	
	public String issueReInspPivDirect(){
		try{
			setDescription("RE-INSPECTION FEE");
			setBankList(getAllBankList());
			setLoggedData();
			setCostCenterNo(getSessionKey("costCenterNo"));
			//ApplicationEjb ejb=new ApplicationEjb(getSessionKey("region"));
			SpestedyEjb ejb=new SpestedyEjb(getSessionKey("region"));
			setFormat(new Format());
			setPivNo("PIV/"+getCostCenterNo()+"/"+PivPrefixType.APP_NEW_CONN+"/"+getFormat().getYear()+"/xxxx");
			setBankList(getAllBankList());
			setState("issueReInsp");
	    	setPath(ISSUEREINSPPATH);
	    	applicationNoList=new ArrayList<String>();
	    	applicationNoList=ejb.getFailedReferenceNoList(costCenterNo, loggedInUserId);
	    	setApplicationNoList(applicationNoList);
			return SUCCESS;
		}catch (Exception e) {
			return ERROR;
		}
	}
	public String issueReInspPivDirect2(){
		try{
			setDescription("RE-INSPECTION FEE");
			setBankList(getAllBankList());
			setLoggedData();
			setCostCenterNo(getSessionKey("costCenterNo"));
			//ApplicationEjb ejb=new ApplicationEjb(getSessionKey("region"));
			SpestedyEjb ejb=new SpestedyEjb(getSessionKey("region"));
			setFormat(new Format());
			setPivNo("PIV/"+getCostCenterNo()+"/"+PivPrefixType.APP_RE_INSP+"/"+getFormat().getYear()+"/xxxx");
			setBankList(getAllBankList());
			setState("afterestimated");
	    	setPath(ISSUEREINSPAFTERESTIMATEDPATH);
	    	//DW
	    	applicationNoList=new ArrayList<String>();
	    	applicationNoList=ejb.getFailedReferenceNoList(costCenterNo, loggedInUserId);
	    	setApplicationNoList(applicationNoList);
			return SUCCESS;
		}catch (Exception e) {
			return ERROR;
		}
	}
	
	public String confirmReInspPivDirect(){
		try{
			setBankList(getAllBankList());
			setLoggedData();
			setCostCenterNo(getSessionKey("costCenterNo"));
			//ApplicationEjb ejb=new ApplicationEjb(getSessionKey("region"));
			SpestedyEjb ejb=new SpestedyEjb(getSessionKey("region"));
			setFormat(new Format());
			setPivNo("PIV/"+getCostCenterNo()+"/"+PivPrefixType.APP_RE_INSP+"/"+getFormat().getYear()+"/xxxx");
			setBankList(getAllBankList());
			setState("confirmReInsp");
	    	setPath(CONFIRMREINSPPATH);
	    	applicationNoList=new ArrayList<String>();
	    	applicationNoList=ejb.getFailedReferenceNoList(costCenterNo, loggedInUserId);
	    	setApplicationNoList(applicationNoList);
			return SUCCESS;
		}catch (Exception e) {
			return ERROR;
		}
	}
	

	

	//private void updateApplicationStatus(Application application, ApplicationDao applicationDao) {
	//	application.setStatus(PAIDSTATUS);
	//	applicationDao.updateApplicantNoCommit(application, getEm());
	//}
	
	//private void updateApplicationStatus(Application application, ApplicationDao applicationDao) {
	//	application.setStatus(paidStatus);
	//	applicationDao.updateApplicantNoCommit(application, getEm());
	//}

	//EJB
	public String findPivNo(){
		HttpServletRequest request = ServletActionContext.getRequest();
		//String sessionKey= (String) request.getSession().getAttribute("region");
		//String pivSubType=request.getParameter("pivSubType");
		//String costCen=request.getParameter("costCen");
		pivSubType = (String) request.getSession().getAttribute("pivSubType");
		String loggedUserLevel = getSessionKey("userRole");
		if(pivSubType != null){
			pivSubType = Constants.PIV_SUB_TYPE_TOTAL;
		}
		PivDetailPK pivDetailPK=null;
		PivDetail pivDetail=null;
		//PivDetailDao pivDetailDao=null;
		Applicant applicant=null;
		//ApplicantDao applicantDao=null;
		ApplicantEjb applicantEjb;
		PivDetailEjb pivDetailEjb;
		try{
			setBankList(getAllBankList());
			setBranchList(getBranchList());
			BankEjb bankEjb = new BankEjb(getSessionKey("region"));
			branchList = bankEjb.getBranchList(chequeBankCode);
			applicantEjb=new ApplicantEjb(getSessionKey("region"));
			pivDetailEjb=new PivDetailEjb(getSessionKey("region"));
			setErrorMessage(null);
			//setEmf(Persistence.createEntityManagerFactory("SMC"));
			//setEm(emf.createEntityManager());
			setLoggedData();
			setState(getState());
			setHiddenSecondState("findPivNo");
			setPath(getPath());
			
			pivDetailPK =new PivDetailPK();
			pivDetail=new PivDetail();
			//pivDetailDao=new PivDetailDao();
			
			applicant =new Applicant();
			//applicantDao=new ApplicantDao(getEmf());
			String commCostcenter = null;
			if(loggedUserLevel.equals(UserRole.ACCOUNT_REVENUE)){
				commCostcenter = costCenterNo.substring(0, 3).concat(".00");
					
			}else{
				commCostcenter = costCenterNo;
			}
			pivDetailPK.setPivNo(getPivNo());
	    	pivDetailPK.setDeptId(commCostcenter);
	    	//pivDetail=pivDetailDao.findById(pivDetailPK,getEm());
	    	pivDetail=pivDetailEjb.findById(pivDetailPK);
	    	if (pivDetail != null) {
				setPivDetail(pivDetail,pivSubType);
				//applicant=applicantDao.findById(pivDetail.getIdNo(),getEm());
				request.getSession().setAttribute("bankCode", pivDetail.getBankCode());
				request.getSession().setAttribute("branchCode", pivDetail.getBranchCode());
				applicant=applicantEjb.findById(pivDetail.getIdNo());
				if (applicant!=null){
					setApplicantData(applicant);
					if(state.equals("modifyDirect")){
						state="modify";
					}
					if(pivDetail.getStatus() != null && pivDetail.getStatus().equalsIgnoreCase(CONFIRMSTATUS)){
						setStatus(pivDetail.getStatus());
						setErrorMessage("Already Confirmed...");
						return ERROR;
						
					}if(pivDetail.getPaymentMode() != null && pivDetail.getPaymentMode().equalsIgnoreCase(Constants.PAYMENT_MODE_CHEQUE) && getSessionKey("userRole").equalsIgnoreCase("DEO")){
						setStatus(pivDetail.getStatus());
						setErrorMessage("To be confirmed by Account Revenue...");
						return ERROR;
						
					}
					
					System.out.println("state               "+state);
					return SUCCESS;
				}else{
					setErrorMessage("No applicant found");
					return ERROR;
				}
			} else{
				setErrorMessage("No PIV found");
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
			//em=null;
			//getEmf().close(); 
			//emf=null;
			pivDetailPK=null;
			pivDetail=null;
			//pivDetailDao=null;
			applicant=null;
			//applicantDao=null;
			//applicationReference=null;
		}
	}
	
	public String findByReferenceNo(){
		//////PivDetailPK pivDetailPK=null;
		//////PivDetail pivDetail=null;
		//PivDetailDao pivDetailDao=null;
		HttpServletRequest request = ServletActionContext.getRequest();
		//String sessionKey= (String) request.getSession().getAttribute("region");
		String loggedUserLevel = getSessionKey("userRole");
		//pivSubType=request.getParameter("pivSubType");
		//String costCen=request.getParameter("costCen");
		pivSubType = (String) request.getSession().getAttribute("pivSubType");
		if(pivSubType == null){
			pivSubType = Constants.PIV_SUB_TYPE_TOTAL;
		}
		ApplicationPK applicationPK=null;
		Application application=null;
		ApplicationEjb applicationEjb=null;
		Applicant applicant=null;
		//ApplicantDao applicantDao=null;
		ApplicantEjb applicantEjb;
		///////PivDetailEjb pivDetailEjb;
		try{
			setBankList(getAllBankList());
			applicantEjb=new ApplicantEjb(getSessionKey("region"));
			//////pivDetailEjb=new PivDetailEjb(getSessionKey("region"));
			setErrorMessage(null);
			//setEmf(Persistence.createEntityManagerFactory("SMC"));
			//setEm(emf.createEntityManager());
			setLoggedData();
			setState(getState());
			setHiddenSecondState("findByReferenceNo");
			setPath(getPath());
			
			////////pivDetailPK =new PivDetailPK();
			////////pivDetail=new PivDetail();
			String commCostcenter = null;
			if(loggedUserLevel.equals(UserRole.ACCOUNT_REVENUE)){
				commCostcenter = costCenterNo.substring(0, 3).concat(".00");
					
			}else{
				commCostcenter = costCenterNo;
			}
			SpstdesthmtPK spstdesthmtPK = new SpstdesthmtPK();
			spstdesthmtPK.setDeptId(commCostcenter);
			spstdesthmtPK.setStdNo(referenceNo);
			spstdesthmtPK.setApplicationNo(referenceNo);
			SpstdesthmtEjb spstdesthmtEjb = new SpstdesthmtEjb(getSessionKey("region"));
			Spstdesthmt spstdesthmt = spstdesthmtEjb.findById(spstdesthmtPK, getSessionKey("region"));
			
			ApplicationReferenceEjb appReferenceEjb=new ApplicationReferenceEjb(getSessionKey("region"));
			ApplicationReference applicationReference = appReferenceEjb.findByApplicationNo(referenceNo);
			
			applicationPK=new ApplicationPK();
			applicationPK.setApplicationId(applicationReference.getId().getApplicationId());
			applicationPK.setDeptId(commCostcenter);
			application =new Application();
			applicationEjb =new ApplicationEjb(getSessionKey("region"));
			//pivDetailDao=new PivDetailDao();
			
			applicant =new Applicant();
			//applicantDao=new ApplicantDao(getEmf());
			
			//////pivDetailPK.setPivNo(getPivNo());
	    	/////pivDetailPK.setDeptId(getCostCenterNo());
	    	//pivDetail=pivDetailDao.findById(pivDetailPK,getEm());
	    	//pivDetail=pivDetailEjb.findById(pivDetailPK);
	    	
			if (state.equals("new")){
				application=applicationEjb.findByAppId(applicationPK);
			}else if (state.equals("issueReInsp") || state.equals("afterestimated")){	
				application=applicationEjb.findByApplicationNo(referenceNo, costCenterNo);
			}
	    	if (application != null) {
				//setPivDetail(pivDetail);
				setPivDeatilNew(application,spstdesthmt,pivSubType);
				System.out.println("after "+pivNo);
				//applicant=applicantDao.findById(pivDetail.getIdNo(),getEm());
				applicant=applicantEjb.findById(application.getIdNo());
				if (applicant!=null){
					setApplicantData(applicant);
					if ( state.equals("afterestimated")){
						state="afterestimatedDone";
					}
					return SUCCESS;
				}else{
					setErrorMessage("No applicant found");
					return ERROR;
				}
			} else{
				setErrorMessage("No Application found");
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
			//em=null;
			//getEmf().close(); 
			//emf=null;
			//applicationPK=null;
			//application=null;
			//pivDetail=null;
			//pivDetailDao=null;
			//applicant=null;
			//applicantDao=null;
			//applicationReference=null;
		}
	}
	
	public String  findEstNo(){
		setLoggedData();
		setBankList(getAllBankList());
		//return findByReferenceNo();
		SpestedyEjb spestedyEjb=new SpestedyEjb(getSessionKey("region"));
		//SpestedyPK id=new SpestedyPK();
		//id.set
		//spestedyEjb.findById(id)ById(id)
		
		SpstdesthmtPK spstdesthmtPK = new SpstdesthmtPK();
		spstdesthmtPK.setDeptId(costCenterNo);
		spstdesthmtPK.setStdNo(referenceNo);
		spstdesthmtPK.setApplicationNo(referenceNo);
		SpstdesthmtEjb spstdesthmtEjb = new SpstdesthmtEjb(getSessionKey("region"));
		Spstdesthmt spstdesthmt = spstdesthmtEjb.findById(spstdesthmtPK, getSessionKey("region"));
		
	/*	Spestedy spestedy= spestedyEjb.getAppointment(costCenterNo, referenceNo);
		if (spestedy!=null){
			if (spestedy.getStatus().equals("V") || spestedy.getStatus().equals("E") ){
				return execute();
			}else{
				setErrorMessage("Service Estimate has not been created yet. so fail the appointment first then Go to Application>PIV>Issues re ispection PIV ");
				System.out.println("Service Estimate has not been created yet. so fail the appointment first then Go to Application>PIV>Issues re ispection PIV");
				applicationNoList=null;
				return ERROR;
			}
		}else{
			applicationNoList=new ArrayList<String>();
			System.out.println("appointment not found. make a apointment first");
			setErrorMessage("appointment not found. make a apointment first");
			return ERROR;
		}*/
		return SUCCESS;
	}
	
	/*public String findByReferenceNoRINF(){
		//////PivDetailPK pivDetailPK=null;
		//////PivDetail pivDetail=null;
		//PivDetailDao pivDetailDao=null;
		ApplicationPK applicationPK=null;
		Application application=null;
		ApplicationEjb applicationEjb=null;
		Applicant applicant=null;
		//ApplicantDao applicantDao=null;
		ApplicantEjb applicantEjb;
		///////PivDetailEjb pivDetailEjb;
		try{
			setBankList(getAllBankList());
			applicantEjb=new ApplicantEjb(getSessionKey("region"));
			//////pivDetailEjb=new PivDetailEjb(getSessionKey("region"));
			setErrorMessage(null);
			//setEmf(Persistence.createEntityManagerFactory("SMC"));
			//setEm(emf.createEntityManager());
			setLoggedData();
			setState(getState());
			setHiddenSecondState("findByReferenceNo");
			setPath(getPath());
			
			////////pivDetailPK =new PivDetailPK();
			////////pivDetail=new PivDetail();
			
			applicationPK=new ApplicationPK();
			applicationPK.setApplicationId(referenceNo);
			applicationPK.setDeptId(getCostCenterNo());
			application =new Application();
			applicationEjb =new ApplicationEjb(getSessionKey("region"));
			//pivDetailDao=new PivDetailDao();
			
			applicant =new Applicant();
			//applicantDao=new ApplicantDao(getEmf());
			
			//////pivDetailPK.setPivNo(getPivNo());
	    	/////pivDetailPK.setDeptId(getCostCenterNo());
	    	//pivDetail=pivDetailDao.findById(pivDetailPK,getEm());
	    	//pivDetail=pivDetailEjb.findById(pivDetailPK);
	    	
			application=applicationEjb.findByApplicationNo(referenceNo, costCenterNo);
			
	    	if (application != null) {
				//setPivDetail(pivDetail);
				setPivDeatilNew(application);
				//applicant=applicantDao.findById(pivDetail.getIdNo(),getEm());
				applicant=applicantEjb.findById(application.getIdNo());
				if (applicant!=null){
					setApplicantData(applicant);
					return SUCCESS;
				}else{
					setErrorMessage("No applicant found");
					return ERROR;
				}
			} else{
				setErrorMessage("No Application found");
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
			//em=null;
			//getEmf().close(); 
			//emf=null;
			//applicationPK=null;
			//application=null;
			//pivDetail=null;
			//pivDetailDao=null;
			//applicant=null;
			//applicantDao=null;
			//applicationReference=null;
		}
	}
	*/
	
	private void setPivDeatilNew(Application application,Spstdesthmt spstdesthmt,String type) {
		NumberFormat nf = NumberFormat.getInstance();
		nf.setGroupingUsed(true);
		nf.setMaximumFractionDigits(2);
		nf.setMinimumFractionDigits(2);
		
		String amount=null;
		String amountStr=null;
		System.err.println(state);
		if (state.equals("new")){
			Calendar cal = Calendar.getInstance();
			String pivNoPrefix = "PIV/"+costCenterNo+"/"+PivPrefixType.getEST_XXX_Type(getSessionKey("smcType"))+"/"+getFormat().getYear()+"/";
			PivDetailEjb pivDetailEjb=new PivDetailEjb(getSessionKey("region"));
	    	String pivNo = pivDetailEjb.getNextPivNo(pivNoPrefix);
	    	setPivNo(pivNoPrefix+pivNo);
			//setPivNo("PIV/"+spstdesthmt.getId().getStdNo());
			//amount="2000.00";
			//amountStr="Two Thousand Rupees Only";
			//setAmountInWords("Two Thousand Rupees Only");
///////////////////////////////////////////////////////////////////
			WiringLandDetailEjb wiringLandDetailEjb=new WiringLandDetailEjb(getSessionKey("region"));
			WiringLandDetail  wiringLandDetail=new WiringLandDetail();
			WiringLandDetailPK id=new WiringLandDetailPK();
			id.setApplicationId(application.getId().getApplicationId());
			id.setDeptId(application.getId().getDeptId());
			wiringLandDetail=wiringLandDetailEjb.findByAppId(id);
					

			setStreetAddress(wiringLandDetail.getServiceStreetAddress());
			setSuburb(wiringLandDetail.getServiceSuburb());
			setCity(wiringLandDetail.getServiceCity());
			if (wiringLandDetail.getServicePostalCode()!=null){
			setPostalCode(wiringLandDetail.getServicePostalCode().toString());
			
			}
			setFullAddress(getStreetAddress() + ";" + getSuburb() + ";"+getCity() + ";" + postalCode);
			
			
			
	    	
///////////////////////////////////////////////////////////////////			
		}else if (state.equals("issueReInsp") || state.equals("afterestimated")){
			System.err.println("inside");
			setPivNo("PIV/"+getCostCenterNo()+"/RIP/"+getFormat().getYear()+"/xxxx");
			WiringLandDetailEjb wiringLandDetailEjb=new WiringLandDetailEjb(getSessionKey("region"));
			WiringLandDetail  wiringLandDetail=new WiringLandDetail();
			WiringLandDetailPK id=new WiringLandDetailPK();
			id.setApplicationId(application.getId().getApplicationId());
			id.setDeptId(application.getId().getDeptId());
			wiringLandDetail=wiringLandDetailEjb.findByAppId(id);
			System.err.println("inside"+wiringLandDetail.getPhase()+" "+wiringLandDetail.getPhase().toString());
			///////////////////////////////////////////////////////////////////
			setStreetAddress(wiringLandDetail.getServiceStreetAddress());
			setSuburb(wiringLandDetail.getServiceSuburb());
			setCity(wiringLandDetail.getServiceCity());
			if (wiringLandDetail.getServicePostalCode()!=null){
			setPostalCode(wiringLandDetail.getServicePostalCode().toString());
			
			}
			setFullAddress(getStreetAddress() + ";" + getSuburb() + ";"+getCity() + ";" + postalCode);
			/////////////////////////////////////////////////////////////////
			
			if (wiringLandDetail.getPhase().toString().equals("1")){
				amount="340";
				amountStr="Three Thousand Rupees Only";
				setAmountInWords("Three Thousand Rupees Only");
			}else if (wiringLandDetail.getPhase().toString().equals("3")){
				amount="820";
				amountStr="Eight Thousand Rupees Only";
				setAmountInWords("Three Hundred and Fourty Rupees Only");
			}
		}
		//setCostCenterNo(cityMap.mapArea(costCenterNo, serviceCity));
		setFormat(new Format());
		setDate(getFormat().FormatDate("dd/MM/yyyy"));
		//setReferenceNo(getApplicationId());
		setFullName(getFirstName() + " " + getLastName());
		//String postalCode = getPostalCode() == null ? "" : getPostalCode();
		//setFullAddress(getStreetAddress() + " " + getSuburb() + " "
		//		+ getCity() + " " + postalCode);
		//setPivNo(application.getId().getApplicationId());
		System.out.println("pivNodvdfdf "+pivNo);
    	//setPivReceiptNo(pivDetail.getPivReceiptNo());
    	setCostCenterNo(application.getId().getDeptId());
    	//setPayingBranchCode(pivDetail.getBankCode()!=null? pivDetail.getBankCode().toString():"0");
    	//setCebBranch(pivDetail.getCebBranchName()!=null? pivDetail.getCebBranchName().toString():null);
    	//setDate(pivDetail.getPivDate().toString());
    	//setReferenceNo(pivDetail.getAppReferenceNo().toString());
    	setIdNo(application.getIdNo());
    	
    	//setPaymentMode(pivDetail.getPaymentMode());
    	//og.info("pivDetail.getBranchCode()"+pivDetail.getBranchCode());
    	//if (pivDetail.getBranchCode()!=null)
    	//	setPayingBranchCode(pivDetail.getBranchCode().toString());
    	//else setPayingBranchCode(null);
    	//log.info("pivDetail.getChequeBankCode() "+pivDetail.getChequeBankCode());
    	//if (pivDetail.getChequeBankCode()!=null)
    	//	setChequeBankCode(pivDetail.getChequeBankCode().toString());
    	//else setChequeBankCode(null);
    	//setHiddenPaymentMode(pivDetail.getPaymentMode());
    	//setChequeBankCode(pivDetail.getChequeBankCode()!=null? pivDetail.getChequeBankCode().toString():null);
    	//if (pivDetail.getChequeBranchCode()!=null)
    	//	setChequeBranchCode(pivDetail.getChequeBranchCode().toString());
    	//else setChequeBranchCode(null);
    	//setChequeNo(pivDetail.getChequeNo()!=null? pivDetail.getChequeNo().toString():null);
    	
    	//if (pivDetail.getBankCode()!=null)
    	//	setBankCode(pivDetail.getBankCode().toString());
    	//else setBankCode(null);
    	//setAmountInWords(amountStr);
    	System.out.println("xljvxjkdnvxjklnglxfngljkxcgjcfjlgcvjkbncjnbjkcvnb "+amountStr);
    	//setStatus(pivDetail.getStatus());
    	setPreparedBy(loggedInUserId);
    	setCertifiedBy(certifiedBy);
    	//setDescription(pivDetail.get)
    	
    	setElectricityDebtors("0");
    	
    	
    	double piv2Total = 0;
    	double vat = 0;
    	double nbt = 0;
    	double piv2TotalWithSecdepo = 0;
    	double secdepo = 0;
    	//piv2Total = spstdesthmt.getSecDeposit().doubleValue()+spstdesthmt.getTotalCost().doubleValue() - spstdesthmt.getCebCost().doubleValue()-spstdesthmt.getRebateCost().doubleValue();
    	
    	if(spstdesthmt.getTotalCost()!= null){
    		piv2Total = piv2Total + spstdesthmt.getTotalCost().doubleValue();
    	}
    	
    	/**if(spstdesthmt.getVatcost()!= null){
    		piv2Total = piv2Total + spstdesthmt.getVatcost().doubleValue();
    	}
    	if(spstdesthmt.getNbtcost()!= null){
    		piv2Total = piv2Total + spstdesthmt.getNbtcost().doubleValue();
    	}*/
    	if(spstdesthmt.getCebCost()!= null){
    		piv2Total = piv2Total - spstdesthmt.getCebCost().doubleValue();
    	}
    	if(spstdesthmt.getRebateCost()!= null){
    		piv2Total = piv2Total - spstdesthmt.getRebateCost().doubleValue();
    	}
    	
    	if(spstdesthmt.getSecDeposit()!= null){
    		secdepo = spstdesthmt.getSecDeposit().doubleValue();
    		piv2TotalWithSecdepo = piv2Total + spstdesthmt.getSecDeposit().doubleValue();
    		//gayani27012016
    		if(getSessionKey("smcType").equals(ApplicationType.READ_SUPPLY))
    		{
    			piv2TotalWithSecdepo = piv2TotalWithSecdepo/2;
    		}
    	}
    	
    	
    	//setSecurityDeposit(securityDeposit);
    	//serConnOrElecSch = nf.format(piv2Total);
    	
    	//setSerConnOrElecSch(serConnOrElecSch);
    	setMiscellaneousDeposit("0");
    	setApplicationFee("0");
    	setForDishonouredCheque("0");
    	setCashInTransit("0");
    	setTenderDeposit("0");
    	setMiscellaneousIncome("0");
    	//setSubTotal(nf.format(piv2Total));
    	
    	setVat("0");
    	
    	//setGrandTotal(nf.format(piv2TotalWithSecdepo));
    	
    	
    	//amountInWords = NumberToWordsConverter.evaluate(piv2Total);
    	//amountInWords = CurrencyToWords.convert(piv2Total);
    	//setAmountInWords(getAmountInWords());
    	
    	if(type.equalsIgnoreCase(Constants.PIV_SUB_TYPE_SEC_DEPOSIT)){
    		setSecurityDeposit(nf.format(secdepo));
    		setSerConnOrElecSch("0");
    		if(spstdesthmt.getSecDeposit()!= null){
	    		setSubTotal(nf.format(secdepo));
	    		setGrandTotal(nf.format(secdepo));
	    		
				amountInWords = NumberToWordsConverter.evaluate(spstdesthmt.getSecDeposit().doubleValue());
				setAmountInWords(getAmountInWords());
    		}
        	//amountInWords = CurrencyToWords.convert(piv2Total);
        	
    	}else if(type.equalsIgnoreCase(Constants.PIV_SUB_TYPE_STESTMATE)){
    		setSerConnOrElecSch(nf.format(piv2Total));
    		//setVat(String.valueOf(spstdesthmt.getVatcost()));
    		setSecurityDeposit("0");
    		setSubTotal(nf.format(piv2Total));
    		if(spstdesthmt.getVatcost() != null){
    			setVat(String.valueOf(spstdesthmt.getVatcost()));
    			piv2Total = piv2Total + spstdesthmt.getVatcost().doubleValue();
    		}else{
    			setVat("0");
    		}
    		if(spstdesthmt.getNbtcost() != null){
    			setNbt(String.valueOf(spstdesthmt.getNbtcost()));
    			piv2Total = piv2Total + spstdesthmt.getNbtcost().doubleValue();
    		}else{
    			setNbt("0");
    		}
    		
    		setGrandTotal(nf.format(piv2Total));
    		if(spstdesthmt.getTotalCost() != null){
    			amountInWords = NumberToWordsConverter.evaluate(piv2Total);
    			setAmountInWords(getAmountInWords());
    		}
        	//amountInWords = CurrencyToWords.convert(piv2Total);
        	
    	}else if(type.equalsIgnoreCase(Constants.PIV_SUB_TYPE_TOTAL)){
    		setSecurityDeposit(nf.format(secdepo));
    		setSerConnOrElecSch(nf.format(piv2Total));
    		setSubTotal(nf.format(piv2TotalWithSecdepo));
    		if(spstdesthmt.getVatcost() != null){
    			setVat(String.valueOf(spstdesthmt.getVatcost()));
    			piv2TotalWithSecdepo = piv2Total + spstdesthmt.getVatcost().doubleValue();
    		}else{
    			setVat("0");
    		}
    		if(spstdesthmt.getNbtcost() != null){
    			setNbt(String.valueOf(spstdesthmt.getNbtcost()));
    			piv2TotalWithSecdepo = piv2TotalWithSecdepo + spstdesthmt.getNbtcost().doubleValue();
    		}else{
    			setNbt("0");
    		}
        	setGrandTotal(nf.format(piv2TotalWithSecdepo));
        	amountInWords = NumberToWordsConverter.evaluate(piv2TotalWithSecdepo);
        	//amountInWords = CurrencyToWords.convert(piv2Total);
        	setAmountInWords(getAmountInWords());
    	}
    	
    	//setDescription(amountStr)
    	//setLoanReference(pivDetail.getLoanReference()!=null? pivDetail.getLoanReference().toString():null);
    	//setLoanAmount(pivDetail.getLoanAmount()!=null? pivDetail.getLoanAmount().toString():null);
    	//setPayDate(pivDetail.getPaidDate()!=null? pivDetail.getPaidDate().toString():null);
    	//setChequeDate(pivDetail.getChequeDate()!=null? pivDetail.getChequeDate().toString():null);
    	System.out.println("pivNodvdfdf "+pivNo);
    	log.info("setApplicantData");
		
	}

	private void updatePivDetail(PivDetail pivDetail, PivDetailDao pivDetailDao) {
		log.info("66666666666666666666666"+getChequeNo());
		Calendar calendar=Calendar.getInstance();
		//PivDetailDao pivDetailDao=new PivDetailDao();
		//BigDecimal bdBankCode = getChequeBankCode().trim().length() == 0 ? null: new BigDecimal(getChequeBankCode());
		//BigDecimal bdChequeBankCode = getChequeBankCode().trim().length() == 0 ? null: new BigDecimal(getChequeBankCode());
		//BigDecimal bdChequeBranchCode = getChequeBranchCode().trim().length() == 0 ? null: new BigDecimal(getChequeBranchCode());
		BigDecimal bdChequeNo = getChequeNo().trim().length() == 0 ? null: new BigDecimal(getChequeNo());
		
		pivDetail.setBankCode(getChequeBankCode());
		pivDetail.setPaymentMode(getPaymentMode());
		pivDetail.setChequeBankCode(getChequeBankCode());
		pivDetail.setChequeBranchCode(getChequeBranchCode());
		pivDetail.setChequeNo(bdChequeNo);
		pivDetail.setConfirmedBy(getLoggedInUserId());
		pivDetail.setConfirmedDate(calendar.getTime());
		pivDetail.setConfirmedTime(getFormat().FormatTime());
		setStatus(CONFIRMSTATUS);
		pivDetail.setStatus(getStatus());
		pivDetailDao.updatePivDetailNoCommit(pivDetail, getEm());
		
		
	}
	
	private PivDetail createPivDetailModelC(PivDetail pivDetail) {
		log.info("66666666666666666666666"+getChequeNo());
		Calendar calendar=Calendar.getInstance();
		//PivDetailDao pivDetailDao=new PivDetailDao();
		//BigDecimal bdBankCode = getChequeBankCode().trim().length() == 0 ? null: new BigDecimal(getChequeBankCode());
		//BigDecimal bdChequeBankCode = getChequeBankCode().trim().length() == 0 ? null: new BigDecimal(getChequeBankCode());
		//BigDecimal bdChequeBranchCode = getChequeBranchCode().trim().length() == 0 ? null: new BigDecimal(getChequeBranchCode());
		BigDecimal bdChequeNo = getChequeNo().trim().length() == 0 ? null: new BigDecimal(getChequeNo());
		pivDetail.setPivReceiptNo(pivReceiptNo);
		pivDetail.setBankCode(getPayingBankCode());
		pivDetail.setBranchCode(getPayingBranchCode());
		pivDetail.setPaymentMode(getPaymentMode());
		//log.info("66666666666666666666666 "+(getPayDate().length()> 9 || getPayDate()!=null ));
		log.info("############ "+(getPaymentMode().equals("C")));
		if (getPaymentMode().equals("C")){
			if (getPayDate().length()> 9 ){
				pivDetail.setPaidDate(format.StrToDate(getPayDate().substring(0, 10)));
			}else{
				pivDetail.setPaidDate(null);
			}
			pivDetail.setChequeBankCode(null);
			pivDetail.setChequeBranchCode(null);
			pivDetail.setChequeNo(null);
		}else {
			pivDetail.setPaidDate(null);
		}
			
		if (getPaymentMode().equals("Q")){
			pivDetail.setChequeBankCode(getChequeBankCode());
			pivDetail.setChequeBranchCode(getChequeBranchCode());
			pivDetail.setChequeNo(bdChequeNo);
			if (getChequeDate().length()> 9 ){
				pivDetail.setChequeDate(format.StrToDate(getChequeDate().substring(0, 10)));
			}else 
				pivDetail.setChequeDate(null);
		}else {
			pivDetail.setChequeDate(null);
		}
		pivDetail.setConfirmedBy(getLoggedInUserId());
		pivDetail.setConfirmedDate(calendar.getTime());
		pivDetail.setConfirmedTime(getFormat().FormatTime());
		//pivDetail.setDescription(description);
		setStatus(CONFIRMSTATUS);
		
		pivDetail.setStatus(getStatus());
		//pivDetailDao.updatePivDetailNoCommit(pivDetail, getEm());
		return pivDetail;
		
	}
	
    

	public String close(){
    	setLoggedData();
    	log.info("testing one two three");
    	return "close";
    }
    
   /* private void savePivDetail(){
    	log.info("start savePivDetail ");
    	Calendar calendar =Calendar.getInstance();
    	
    	PivDetailPK pivDetailPK =new PivDetailPK();
    	pivDetailPK.setPivNo(getPivNo());
    	pivDetailPK.setDeptId(getCostCenterNo());
    	
    	//BigDecimal appReferenceNo,
		
		
		//BigDecimal bdAappReferenceNo = getReferenceNo().trim().length() == 0 ? new BigDecimal(0): new BigDecimal(getReferenceNo());
		//BigDecimal bdPivAmount = getGrandTotal().trim().length() == 0 ? new BigDecimal(0): new BigDecimal(getGrandTotal());
		//BigDecimal bdCebBranch = getCebBranch().trim().length() == 0 ? null: new BigDecimal(getCebBranch());
		//BigDecimal bdPbBranchCode = getPbBranchCode().trim().length() == 0 ? null: new BigDecimal(getPbBranchCode());
		BigDecimal bdChequeNo = getChequeNo().trim().length() == 0 ? null: new BigDecimal(getChequeNo());
		
		//BigDecimal bdChequeBankCode = getChequeBankCode().trim().length() == 0 ? null: new BigDecimal(getChequeBankCode());
		//BigDecimal bdChequeBranchCode = getChequeBranchCode().trim().length() == 0 ? null: new BigDecimal(getChequeBranchCode());
		
		BigDecimal bdMiscellaneousIncome = getMiscellaneousIncome().trim().length() == 0 ? new BigDecimal(0): new BigDecimal(getMiscellaneousIncome());
		BigDecimal bdElectricityDebtors = getElectricityDebtors().trim().length() == 0 ? new BigDecimal(0): new BigDecimal(getElectricityDebtors());
		BigDecimal bdSecurityDeposit = getSecurityDeposit().trim().length() == 0 ? new BigDecimal(0): new BigDecimal(getSecurityDeposit());
		BigDecimal bdSerConnOrElecSch = getSerConnOrElecSch().trim().length() == 0 ? new BigDecimal(0): new BigDecimal(getSerConnOrElecSch());
		BigDecimal bdTenderDeposit = getTenderDeposit().trim().length() == 0 ? new BigDecimal(0): new BigDecimal(getTenderDeposit());
		BigDecimal bdMiscellaneousDeposit = getMiscellaneousDeposit().trim().length() == 0 ? new BigDecimal(0): new BigDecimal(getMiscellaneousDeposit());
		BigDecimal bdCashInTransit = getCashInTransit().trim().length() == 0 ? new BigDecimal(0): new BigDecimal(getCashInTransit());
		BigDecimal bdForDishonoredCheque = getForDishonouredCheque().trim().length() == 0 ? new BigDecimal(0): new BigDecimal(getForDishonouredCheque());
		BigDecimal bdSubTotal = getSubTotal().trim().length() == 0 ? new BigDecimal(0): new BigDecimal(getSubTotal());
		BigDecimal bdVat = getVat().trim().length() == 0 ? new BigDecimal(0): new BigDecimal(getVat());
		BigDecimal bdGrandTotal= getGrandTotal().trim().length() == 0 ? new BigDecimal(0): new BigDecimal(getGrandTotal());
		BigDecimal bdLoanReference= getLoanReference().trim().length() == 0 ? null: new BigDecimal(getLoanReference());
		BigDecimal bdLoanAmount= getLoanAmount().trim().length() == 0 ? null: new BigDecimal(getLoanAmount());
				
    	setStatus(newStatus);
    	setConfirmedBy(null);
    	setConfirmedDate(null);
    	setConfirmedTime(null);
    	setUpdUser(null);
    	setUpdDate(null);
    	setUpdTime(null);
    	setPivSeqNo("1");
		PivDetail pivDetail=new PivDetail(pivDetailPK, new BigDecimal(getPivSeqNo()), getReferenceNo(), "", "", getIdNo(), calendar.getTime(), bdGrandTotal, getCebBranch(),  new BigDecimal(getPayingBankCode()), getPayingBranchCode(), getPaymentMode(), getChequeBankCode(),getChequeBranchCode() ,bdChequeNo, bdMiscellaneousIncome, bdElectricityDebtors, bdSecurityDeposit, bdSerConnOrElecSch, bdTenderDeposit, bdMiscellaneousDeposit, bdCashInTransit, bdForDishonoredCheque, bdSubTotal, bdVat, bdGrandTotal, bdLoanReference, bdLoanAmount, getPreparedBy(), getCertifiedBy(), getConfirmedBy(), getConfirmedDate(),getConfirmedTime(), getStatus(), getLoggedInUserId(), calendar.getTime(), getFormat().FormatTime(), getUpdUser(), getUpdDate(), getUpdTime());
    	PivDetailDao pivDetailDao=new PivDetailDao();
    	pivDetailDao.createPivNoCommit(pivDetail, getEm());
    	pivDetailPK=null;
    	pivDetailDao=null;
    	calendar=null; 
    	log.info("end savePivDetail ");
    	
    }*/
    
    private PivDetail CreatePivDetailModel(){
    	System.out.println("dddddrrrrrrrrrgggggggg");
    	log.info("start CreatePivDetailModel");
    	Calendar calendar =Calendar.getInstance();
    	
    	PivDetailPK pivDetailPK =new PivDetailPK();
    	pivDetailPK.setPivNo(getPivNo());
    	pivDetailPK.setDeptId(getCostCenterNo());
    	BigDecimal bdChequeNo = getChequeNo().trim().length() == 0 ? null: new BigDecimal(getChequeNo());
				
		//BigDecimal bdMiscellaneousIncome = getMiscellaneousIncome().trim().length() == 0 ? new BigDecimal(0): new BigDecimal(getMiscellaneousIncome());
		BigDecimal bdMiscellaneousIncome = new BigDecimal(0);
		BigDecimal bdElectricityDebtors = getElectricityDebtors().trim().length() == 0 ? new BigDecimal(0): new BigDecimal(getElectricityDebtors());
		BigDecimal bdSecurityDeposit = getSecurityDeposit().trim().length() == 0 ? new BigDecimal(0): new BigDecimal(getSecurityDeposit().replace(",", ""));
		BigDecimal bdSerConnOrElecSch = getSerConnOrElecSch().trim().length() == 0 ? new BigDecimal(0): new BigDecimal(getSerConnOrElecSch().replace(",", ""));
		BigDecimal bdTenderDeposit = getTenderDeposit().trim().length() == 0 ? new BigDecimal(0): new BigDecimal(getTenderDeposit());
		BigDecimal bdMiscellaneousDeposit = getMiscellaneousDeposit().trim().length() == 0 ? new BigDecimal(0): new BigDecimal(getMiscellaneousDeposit());
		BigDecimal bdCashInTransit = getCashInTransit().trim().length() == 0 ? new BigDecimal(0): new BigDecimal(getCashInTransit());
		BigDecimal bdForDishonoredCheque = getForDishonouredCheque().trim().length() == 0 ? new BigDecimal(0): new BigDecimal(getForDishonouredCheque());
		BigDecimal bdSubTotal = getSubTotal().trim().length() == 0 ? new BigDecimal(0): new BigDecimal(getSubTotal().replace(",", ""));
		BigDecimal bdVat = getVat().trim().length() == 0 ? new BigDecimal(0): new BigDecimal(getVat());
		BigDecimal bdGrandTotal= getGrandTotal().trim().length() == 0 ? new BigDecimal(0): new BigDecimal(getGrandTotal().replace(",", ""));
		BigDecimal bdapplicationFees= getGrandTotal().trim().length() == 0 ? new BigDecimal(0): new BigDecimal(getGrandTotal().replace(",", ""));
		
		//BigDecimal bdLoanReference= getLoanReference().trim().length() == 0 ? null: new BigDecimal(getLoanReference());
		BigDecimal bdLoanAmount= getLoanAmount().trim().length() == 0 ?  null: new BigDecimal(getLoanAmount());
		
		//BigDecimal vatAmount= getVat().trim().length() == 0 ?  null: new BigDecimal(getVat());
		BigDecimal nbtAmount= getNbt().trim().length() == 0 ?  null: new BigDecimal(getNbt());
		
		//bdLoanAmount=new BigDecimal(0);		
    	setStatus(NEWSTATUS);
    	setConfirmedBy(null);
    	setConfirmedDate(null);
    	setConfirmedTime(null);
    	setUpdUser(null);
    	setUpdDate(null);
    	setUpdTime(null);
    	//setPivSeqNo("1");
    	
		//PivDetail pivDetail=new PivDetail(pivDetailPK, new BigDecimal(getPivSeqNo()), getReferenceNo(), "", "", getIdNo(), calendar.getTime(), bdGrandTotal, getCebBranch(),  getPayingBankCode(), getPayingBranchCode(), getPaymentMode(), new BigDecimal(getChequeBankCode()),new BigDecimal(getChequeBranchCode()) ,bdChequeNo, bdMiscellaneousIncome, bdElectricityDebtors, bdSecurityDeposit, bdSerConnOrElecSch, bdTenderDeposit, bdMiscellaneousDeposit, bdCashInTransit, bdForDishonoredCheque, bdSubTotal, bdVat, bdGrandTotal, bdLoanReference, bdLoanAmount, getPreparedBy(), getCertifiedBy(), getConfirmedBy(), getConfirmedDate(),getConfirmedTime(), getStatus(), getLoggedInUserId(), calendar.getTime(), getFormat().FormatTime(), getUpdUser(), getUpdDate(), getUpdTime());
    	PivDetail pivDetail=new PivDetail();
    	pivDetail.setId(pivDetailPK);
		pivDetail.setPivSeqNo(new BigDecimal("1"));
		//
		pivDetail.setAppReferenceNo(getReferenceNo());
		pivDetail.setReferenceNo(getReferenceNo());
		
		System.out.println(bdSerConnOrElecSch +"hii " +getSerConnOrElecSch().trim() + "Hii : " +getSecurityDeposit().trim().length() );
		System.out.println(bdSerConnOrElecSch == new BigDecimal(0) );
		System.out.println(getSerConnOrElecSch().trim().equals("0") );
		System.out.println(getSecurityDeposit().trim().length() != 0);
		if(getSerConnOrElecSch().trim().equals("0") && getSecurityDeposit().trim().length() != 0 ){
			System.out.println("Hi inside sec");
			pivDetail.setReferenceType(ReferenceType.SECDEP);
		}else{
			System.out.println("Hi not inside sec");
			pivDetail.setReferenceType(ReferenceType.ESTIMATE);
		}
		
		pivDetail.setIdNo(getIdNo());
		pivDetail.setPivDate(calendar.getTime());
		pivDetail.setPivAmount(bdGrandTotal);
		pivDetail.setCebBranchName(getCebBranch());
		pivDetail.setPivReceiptNo(pivReceiptNo);
		pivDetail.setBankCode(getPayingBankCode()); 
		System.out.println("################# "+ getPayingBankCode());
		pivDetail.setBranchCode(getPayingBranchCode());
		
		pivDetail.setPaymentMode(getPaymentMode());
		
		pivDetail.setChequeBankCode(getChequeBankCode());
		
		pivDetail.setChequeBranchCode(getChequeBranchCode());
		//
		pivDetail.setChequeNo(bdChequeNo);
		pivDetail.setMiscellaneousIncome(bdMiscellaneousIncome);
		pivDetail.setElectricityDebtors(bdElectricityDebtors); 
		pivDetail.setSecurityDeposit(bdSecurityDeposit);
		pivDetail.setSerConnOrElecSch(bdSerConnOrElecSch);
		pivDetail.setTenderDeposit(bdTenderDeposit);
		pivDetail.setMiscellaneousDeposit(bdMiscellaneousDeposit);
		pivDetail.setCashInTransit(bdCashInTransit);
		pivDetail.setForDishonoredCheque(bdForDishonoredCheque);
		pivDetail.setSubTotal(bdSubTotal);
		pivDetail.setVat(bdVat);
		pivDetail.setNbt(nbtAmount);
		pivDetail.setGrandTotal(bdGrandTotal);
		//pivDetail.setLoanReference(getLoanReference());
		
		pivDetail.setLoanAmount(bdLoanAmount);
		pivDetail.setPreparedBy(getPreparedBy());
		//pivDetail.setCertifiedBy(getCertifiedBy()); 
		pivDetail.setConfirmedBy(getConfirmedBy());
		pivDetail.setConfirmedDate(getConfirmedDate());
		pivDetail.setConfirmedTime(getConfirmedTime()); 
		pivDetail.setStatus(getStatus());
		pivDetail.setAddUser(getLoggedInUserId()); 
		pivDetail.setAddDate(calendar.getTime());
		pivDetail.setAddTime(getFormat().FormatTime());
		pivDetail.setUpdUser(null);
		pivDetail.setUpdDate(null); 
		pivDetail.setUpdTime(null);
		pivDetail.setDescription(description);
		//PivDetailDao pivDetailDao=new PivDetailDao();
    	//pivDetailDao.createPivNoCommit(pivDetail, getEm());
    	//pivDetailPK=null;
    	//pivDetailDao=null;s
		
		
    	calendar=null; 
    	log.info("end CreatePivDetailModel ");
    	return pivDetail;
    }
    
    
    private PivDetail CreatePivDetailModelREINSP(){
    	log.info("start CreatePivDetailModel");
    	Calendar calendar =Calendar.getInstance();
    	
    	PivDetailPK pivDetailPK =new PivDetailPK();
    	pivDetailPK.setPivNo(getReferenceNo());
    	pivDetailPK.setDeptId(getCostCenterNo());
    	BigDecimal bdChequeNo = getChequeNo().trim().length() == 0 ? null: new BigDecimal(getChequeNo());
				
		BigDecimal bdMiscellaneousIncome = getMiscellaneousIncome().trim().length() == 0 ? new BigDecimal(0): new BigDecimal(getMiscellaneousIncome());
		BigDecimal bdElectricityDebtors = getElectricityDebtors().trim().length() == 0 ? new BigDecimal(0): new BigDecimal(getElectricityDebtors());
		BigDecimal bdSecurityDeposit = getSecurityDeposit().trim().length() == 0 ? new BigDecimal(0): new BigDecimal(getSecurityDeposit());
		BigDecimal bdSerConnOrElecSch = getSerConnOrElecSch().trim().length() == 0 ? new BigDecimal(0): new BigDecimal(getSerConnOrElecSch());
		BigDecimal bdTenderDeposit = getTenderDeposit().trim().length() == 0 ? new BigDecimal(0): new BigDecimal(getTenderDeposit());
		BigDecimal bdMiscellaneousDeposit = getMiscellaneousDeposit().trim().length() == 0 ? new BigDecimal(0): new BigDecimal(getMiscellaneousDeposit());
		BigDecimal bdCashInTransit = getCashInTransit().trim().length() == 0 ? new BigDecimal(0): new BigDecimal(getCashInTransit());
		BigDecimal bdForDishonoredCheque = getForDishonouredCheque().trim().length() == 0 ? new BigDecimal(0): new BigDecimal(getForDishonouredCheque());
		BigDecimal bdSubTotal = getSubTotal().trim().length() == 0 ? new BigDecimal(0): new BigDecimal(getSubTotal());
		BigDecimal bdVat = getVat().trim().length() == 0 ? new BigDecimal(0): new BigDecimal(getVat());
		BigDecimal bdGrandTotal= getGrandTotal().trim().length() == 0 ? new BigDecimal(0): new BigDecimal(getGrandTotal());
		//BigDecimal bdLoanReference= getLoanReference().trim().length() == 0 ? null: new BigDecimal(getLoanReference());
		BigDecimal bdLoanAmount= getLoanAmount().trim().length() == 0 ?  null: new BigDecimal(getLoanAmount());
		bdLoanAmount=new BigDecimal(0);		
    	setStatus(NEWSTATUS);
    	setConfirmedBy(null);
    	setConfirmedDate(null);
    	setConfirmedTime(null);
    	setUpdUser(null);
    	setUpdDate(null);
    	setUpdTime(null);
    	//setPivSeqNo("1");
    	
		//PivDetail pivDetail=new PivDetail(pivDetailPK, new BigDecimal(getPivSeqNo()), getReferenceNo(), "", "", getIdNo(), calendar.getTime(), bdGrandTotal, getCebBranch(),  getPayingBankCode(), getPayingBranchCode(), getPaymentMode(), new BigDecimal(getChequeBankCode()),new BigDecimal(getChequeBranchCode()) ,bdChequeNo, bdMiscellaneousIncome, bdElectricityDebtors, bdSecurityDeposit, bdSerConnOrElecSch, bdTenderDeposit, bdMiscellaneousDeposit, bdCashInTransit, bdForDishonoredCheque, bdSubTotal, bdVat, bdGrandTotal, bdLoanReference, bdLoanAmount, getPreparedBy(), getCertifiedBy(), getConfirmedBy(), getConfirmedDate(),getConfirmedTime(), getStatus(), getLoggedInUserId(), calendar.getTime(), getFormat().FormatTime(), getUpdUser(), getUpdDate(), getUpdTime());
    	PivDetail pivDetail=new PivDetail();
    	pivDetail.setId(pivDetailPK);
		pivDetail.setPivSeqNo(new BigDecimal("1"));
		//
		pivDetail.setAppReferenceNo(getReferenceNo());
		pivDetail.setReferenceNo(getReferenceNo());
		pivDetail.setReferenceType(ReferenceType.REINSP);
		pivDetail.setIdNo(getIdNo());
		pivDetail.setPivDate(calendar.getTime());
		pivDetail.setPivAmount(bdGrandTotal);
		pivDetail.setCebBranchName(getCebBranch());
		pivDetail.setPivReceiptNo(pivReceiptNo);
		pivDetail.setBankCode(getPayingBankCode()); 
		System.out.println("################# "+ getPayingBankCode());
		pivDetail.setBranchCode(getPayingBranchCode());
		
		pivDetail.setPaymentMode(getPaymentMode());
		
		pivDetail.setChequeBankCode(getChequeBankCode());
		
		pivDetail.setChequeBranchCode(getChequeBankCode());
		//
		pivDetail.setChequeNo(bdChequeNo);
		pivDetail.setMiscellaneousIncome(bdMiscellaneousIncome);
		pivDetail.setElectricityDebtors(bdElectricityDebtors); 
		pivDetail.setSecurityDeposit(bdSecurityDeposit);
		pivDetail.setSerConnOrElecSch(bdSerConnOrElecSch);
		pivDetail.setTenderDeposit(bdTenderDeposit);
		pivDetail.setMiscellaneousDeposit(bdMiscellaneousDeposit);
		pivDetail.setCashInTransit(bdCashInTransit);
		pivDetail.setForDishonoredCheque(bdForDishonoredCheque);
		pivDetail.setSubTotal(bdSubTotal);
		pivDetail.setVat(bdVat);
		pivDetail.setGrandTotal(bdGrandTotal);
		//pivDetail.setLoanReference(getLoanReference());
		
		pivDetail.setLoanAmount(bdLoanAmount);
		pivDetail.setPreparedBy(getPreparedBy());
		//pivDetail.setCertifiedBy(getCertifiedBy()); 
		pivDetail.setConfirmedBy(getConfirmedBy());
		pivDetail.setConfirmedDate(getConfirmedDate());
		pivDetail.setConfirmedTime(getConfirmedTime()); 
		pivDetail.setStatus(getStatus());
		pivDetail.setAddUser(getLoggedInUserId()); 
		pivDetail.setAddDate(calendar.getTime());
		pivDetail.setAddTime(getFormat().FormatTime());
		pivDetail.setUpdUser(null);
		pivDetail.setUpdDate(null); 
		pivDetail.setUpdTime(null);
		pivDetail.setDescription(description);
		//PivDetailDao pivDetailDao=new PivDetailDao();
    	//pivDetailDao.createPivNoCommit(pivDetail, getEm());
    	//pivDetailPK=null;
    	//pivDetailDao=null;
    	calendar=null; 
    	log.info("end CreatePivDetailModel ");
    	return pivDetail;
    }
    
    
    private void setPivDetail(PivDetail pivDetail,String pivSubType){
    	NumberFormat nf = NumberFormat.getInstance();
		nf.setGroupingUsed(true);
		nf.setMaximumFractionDigits(2);
		nf.setMinimumFractionDigits(2);
    	
    	setPivNo(pivDetail.getId().getPivNo());
    	setPivReceiptNo(pivDetail.getPivReceiptNo());
    	setCostCenterNo(pivDetail.getId().getDeptId());
    	setPayingBranchCode(pivDetail.getBankCode()!=null? pivDetail.getBankCode().toString():"0");
    	setCebBranch(pivDetail.getCebBranchName()!=null? pivDetail.getCebBranchName().toString():null);
    	setDate(pivDetail.getPivDate().toString());
    	setReferenceNo(pivDetail.getAppReferenceNo().toString());
    	setIdNo(pivDetail.getIdNo());
    	setPaymentMode(pivDetail.getPaymentMode());
    	log.info("pivDetail.getBranchCode()"+pivDetail.getBranchCode());
    	if (pivDetail.getBranchCode()!=null)
    		setPayingBranchCode(pivDetail.getBranchCode().toString());
    	else setPayingBranchCode(null);
    	log.info("pivDetail.getChequeBankCode() "+pivDetail.getChequeBankCode());
    	if (pivDetail.getChequeBankCode()!=null)
    		setChequeBankCode(pivDetail.getChequeBankCode().toString());
    	else setChequeBankCode(null);
    	setHiddenPaymentMode(pivDetail.getPaymentMode());
    	setChequeBankCode(pivDetail.getChequeBankCode()!=null? pivDetail.getChequeBankCode().toString():null);
    	
    	if (pivDetail.getChequeBranchCode()!=null)
    		setChequeBranchCode(pivDetail.getChequeBranchCode().toString());
    	else setChequeBranchCode(null);
    	
    	hdnChequeBranchCode = pivDetail.getChequeBranchCode()!=null? pivDetail.getChequeBranchCode().toString():null;
    	
    	setChequeNo(pivDetail.getChequeNo()!=null? pivDetail.getChequeNo().toString():null);
    	
    	//if (pivDetail.getBankCode()!=null)
    	//	setBankCode(pivDetail.getBankCode().toString());
    	//else setBankCode(null);
///////////////////////////////////////////////////////////////////
    	if (state.equals("confirmReInsp") ){
	    	Application application=new Application();
	    	ApplicationEjb ejb=new ApplicationEjb(getSessionKey("region"));
	    	log.info("get  "+getReferenceNo());
	    	
	    	application=ejb.findByApplicationNo(getReferenceNo());
	    	
	    	
	    	log.info("application "+application);
	    	WiringLandDetailEjb wiringLandDetailEjb=new WiringLandDetailEjb(getSessionKey("region"));
			WiringLandDetail  wiringLandDetail=new WiringLandDetail();
			WiringLandDetailPK id=new WiringLandDetailPK();
			id.setApplicationId(application.getId().getApplicationId());
			id.setDeptId(pivDetail.getId().getDeptId());
			wiringLandDetail=wiringLandDetailEjb.findByAppId(id);
					
	
			setStreetAddress(wiringLandDetail.getServiceStreetAddress());
			setSuburb(wiringLandDetail.getServiceSuburb());
			setCity(wiringLandDetail.getServiceCity());
			if (wiringLandDetail.getServicePostalCode()!=null){
			setPostalCode(wiringLandDetail.getServicePostalCode().toString());
			
			}
			setFullAddress(getStreetAddress() + ";" + getSuburb() + ";"+getCity() + ";" + postalCode);
    	}else if (state.equals("confirm") || state.equals("view") || state.equals("printPIV")) {
    		//Application application=new Application();
	    	//ApplicationEjb ejb=new ApplicationEjb(getSessionKey("region"));
	    	//log.info("get  "+getReferenceNo());
	    	
	    	//application=ejb.findByApplicationNo(getReferenceNo());
	    	//log.info("application "+application);
    		
    		SpstdesthmtPK spstdesthmtPK = new SpstdesthmtPK();
			spstdesthmtPK.setDeptId(costCenterNo);
			spstdesthmtPK.setStdNo(referenceNo);
			spstdesthmtPK.setApplicationNo(referenceNo);
			SpstdesthmtEjb spstdesthmtEjb = new SpstdesthmtEjb(getSessionKey("region"));
			Spstdesthmt spstdesthmt = spstdesthmtEjb.findById(spstdesthmtPK, getSessionKey("region"));
			
			ApplicationReferenceEjb appReferenceEjb=new ApplicationReferenceEjb(getSessionKey("region"));
			ApplicationReference applicationReference = appReferenceEjb.findByApplicationNo(pivDetail.getReferenceNo());
			
			
			
	    	WiringLandDetailEjb wiringLandDetailEjb=new WiringLandDetailEjb(getSessionKey("region"));
			WiringLandDetail  wiringLandDetail=new WiringLandDetail();
			WiringLandDetailPK id=new WiringLandDetailPK();
			id.setApplicationId(applicationReference.getId().getApplicationId());
			id.setDeptId(pivDetail.getId().getDeptId());
			wiringLandDetail=wiringLandDetailEjb.findByAppId(id);
					
	
			setStreetAddress(wiringLandDetail.getServiceStreetAddress());
			setSuburb(wiringLandDetail.getServiceSuburb());
			setCity(wiringLandDetail.getServiceCity());
			if (wiringLandDetail.getServicePostalCode()!=null){
			setPostalCode(wiringLandDetail.getServicePostalCode().toString());
			
			}
			setFullAddress(getStreetAddress() + ";" + getSuburb() + ";"+getCity() + ";" + postalCode);
    	}
///////////////////////////////////////////////////////////////////	
    	
    	
    	setStatus(pivDetail.getStatus());
    	setPreparedBy(pivDetail.getPreparedBy());
    	setCertifiedBy(certifiedBy);
    	//setDescription(pivDetail.get)
    	
    	//setMiscellaneousIncome(pivDetail.getMiscellaneousIncome()!=null? pivDetail.getMiscellaneousIncome().toString():"0");
    	setMiscellaneousIncome("0");
    	setElectricityDebtors(pivDetail.getElectricityDebtors()!=null? pivDetail.getElectricityDebtors().toString():"0");
    	if(pivSubType.equalsIgnoreCase(Constants.PIV_SUB_TYPE_SEC_DEPOSIT)){
    		setSecurityDeposit(pivDetail.getSecurityDeposit()!=null? nf.format(pivDetail.getSecurityDeposit().doubleValue()):"0");
    		setSerConnOrElecSch("0");
    		setAmountInWords(NumberToWordsConverter.evaluate(pivDetail.getSecurityDeposit().doubleValue()));
    	}else if(pivSubType.equalsIgnoreCase(Constants.PIV_SUB_TYPE_STESTMATE)){
    		setSerConnOrElecSch(pivDetail.getSerConnOrElecSch()!=null? nf.format(pivDetail.getSerConnOrElecSch().doubleValue()):"0");
    		setSecurityDeposit("0");
    		setAmountInWords(NumberToWordsConverter.evaluate(pivDetail.getSerConnOrElecSch().doubleValue()));
    	}else if(pivSubType.equalsIgnoreCase(Constants.PIV_SUB_TYPE_TOTAL)){
    		setSecurityDeposit(pivDetail.getSecurityDeposit()!=null? nf.format(pivDetail.getSecurityDeposit().doubleValue()):"0");
    		setSerConnOrElecSch(pivDetail.getSerConnOrElecSch()!=null? nf.format(pivDetail.getSerConnOrElecSch().doubleValue()):"0");
    		setAmountInWords(NumberToWordsConverter.evaluate(pivDetail.getPivAmount().doubleValue()));
    	}
    	
    	
    	
    	setMiscellaneousDeposit(pivDetail.getMiscellaneousDeposit()!=null? pivDetail.getMiscellaneousDeposit().toString():"0");
    	setForDishonouredCheque(pivDetail.getForDishonoredCheque()!=null? pivDetail.getForDishonoredCheque().toString():"0");
    	setCashInTransit(pivDetail.getCashInTransit()!=null? pivDetail.getCashInTransit().toString():"0");
    	setTenderDeposit(pivDetail.getTenderDeposit()!=null? pivDetail.getTenderDeposit().toString():"0");
    	setSubTotal(pivDetail.getSubTotal()!=null?  nf.format(pivDetail.getSubTotal().doubleValue())+".00":"0");
    	setVat(pivDetail.getVat()!=null? pivDetail.getVat().toString():"0");
    	setNbt(pivDetail.getNbt()!=null? pivDetail.getNbt().toString():"0");
    	setGrandTotal(pivDetail.getGrandTotal()!=null?  nf.format(pivDetail.getGrandTotal().doubleValue())+".00":"0");
    	//setLoanReference(pivDetail.getLoanReference()!=null? pivDetail.getLoanReference().toString():null);
    	setLoanAmount(pivDetail.getLoanAmount()!=null? pivDetail.getLoanAmount().toString():null);
    	setPayDate(pivDetail.getPaidDate()!=null? pivDetail.getPaidDate().toString():null);
    	setChequeDate(pivDetail.getChequeDate()!=null? pivDetail.getChequeDate().toString():null);
    	//setApplicationFee("2000.00");
    	
    	log.info("setApplicantData");
    }
    
    private void setApplicantData(Applicant applicant) {
		log.info("setApplicantData");
		setFirstName(applicant.getFirstName());
		setLastName(applicant.getLastName());
		//setStreetAddress(applicant.getStreetAddress());
		//setSuburb(applicant.getSuburb());
		//setCity(applicant.getCity());
		//setPostalCode(applicant.getPostalCode());
    	setFullName(getFirstName()+" "+getLastName()  );
		//setFullAddress(getStreetAddress() + ";" + getSuburb() + ";"+getCity() + ";" + postalCode);
		//setFullName(applicant.getFirstName()+" "+applicant.getLastName()  );
		//setFullAddress(applicant.getStreetAddress()+" "+applicant.getSuburb()+" "+applicant.getCity()+" "+applicant.getPostalCode());
	}
    
    private void print(PivDetail pivDetail){
    	//log.info("start");
    	
    	PayingInVoucher piv = new PayingInVoucher();
    	piv.setAmountInWords(getAmountInWords());
    	BankEjb bankEjb = new BankEjb(getSessionKey("region"));
		Bank bank = bankEjb.findBankCode(payingBankCode);
		piv.setBankName(bank.getBankName());
		
		BankBranchPK bankBranchPK = new BankBranchPK();
		bankBranchPK.setBankCode(payingBankCode);
		bankBranchPK.setBranchCode(payingBranchCode);
		BankBranch bankBranch = bankEjb.findBankBranchCode(bankBranchPK);
		piv.setBankBranchName(bankBranch.getBranchName());
    	
    	//piv.setBankBranch(getPayingBankCode()+" "+payingBranchCode);
    	//////////////////////////////////////////////////////haw to do
    			//piv.setName(bankBranchName);
    			//piv.setBankBranchName(bankBranchName);
        piv.setCashInTransit(new BigDecimal(0));
        piv.setCEBBranch(getCebBranch());
        //piv.setBankBranch(payingBankCode);
        //log.info("2");
        piv.setCetifiedBy("");
        
        piv.setCostCenterNo(getCostCenterNo());
        piv.setDate(format.FormatStringDate(new Date()));
        piv.setDepositorIDNumber(getIdNo());
        piv.setDepositorName(getFullName());
        //log.info("3");
        piv.setEletricityDebtors(new BigDecimal(0));
        piv.setGrandTotal(new BigDecimal(getGrandTotal()));
        //log.info("33");
        piv.setJobDescription(getDescription());
        
        piv.setMiscellaneousDeposits(new BigDecimal(0));
        //log.info("333");
        piv.setMiscellaneousIncome(new BigDecimal(getMiscellaneousIncome()));
        //log.info("333");
        
        //log.info("4");
        piv.setReferenceNo(getReferenceNo());
        piv.setSecurityDeposit(new BigDecimal(0));
        piv.setServiceConnectionOrElectricitySchemes(new BigDecimal(0));
        ///////////////////////////////////////
        piv.setStreetAddress(getFullAddress());
        piv.setSuburb(getSuburb());
        piv.setCity(getCity());
        piv.setPostalCode(getPostalCode()!=null? getPostalCode().toString():null);
        ///////////////////////////////////////
        piv.setSubTotal(new BigDecimal(getSubTotal()));
        piv.setTenderDeposits(new BigDecimal(0));
        //piv.setVat(new BigDecimal(0));
        piv.setVat(new BigDecimal(getVat()));
        piv.setNbt(new BigDecimal(getNbt()));
        //piv.setn(new BigDecimal(getNbt()));
        piv.setForDishonouredCheques(new BigDecimal(0));
        piv.setBankCode(payingBankCode);
        piv.setBranchCode(payingBranchCode);
        piv.setChequeNo(chequeNo);
        System.err.println("pivNo "+pivNo);
        piv.setPIVNumber(pivNo);
        
        if(loanAmount!=null && loanAmount.trim().length()>0)
              piv.setLoanAmount(new BigDecimal(loanAmount));
        piv.setLoanReference(loanReference);
        System.out.println(piv);
        PrintPiv pe = new  PrintPiv(piv);
       
        pe.Print(false);
        //log.info("end");
    	
    	
    }
    
    public String getStatus() {
		return status;
	}

	public void setStatus(String status) {
		this.status = status;
	}

	/**
     * @return the session
     */
    public Map<String, Object> getSession() {
        return session;

    }

    /**
     * @param session the session to set
     */
    @Override
    public void setSession(Map<String, Object> session) {
        this.session = session;
    }
    
    public String getPivNo() {
		return pivNo;
	}

	public void setPivNo(String pivNo) {
		this.pivNo = pivNo;
	}
	
	public String getPivSeqNo() {
		return pivSeqNo;
	}

	public void setPivSeqNo(String pivSeqNo) {
		this.pivSeqNo = pivSeqNo;
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

	public String getCebBranch() {
		return cebBranch;
	}

	public void setCebBranch(String cebBranch) {
		this.cebBranch = cebBranch;
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

	public String getChequeBankCode() {
		return chequeBankCode;
	}

	public void setChequeBankCode(String chequeBankCode) {
		this.chequeBankCode = chequeBankCode;
	}

	public String getChequeBranchCode() {
		return chequeBranchCode;
	}

	public void setChequeBranchCode(String chequeBranchCode) {
		this.chequeBranchCode = chequeBranchCode;
	}

	public String getPaymentMode() {
		return paymentMode;
	}

	public void setPaymentMode(String paymentMode) {
		this.paymentMode = paymentMode;
	}

	public String getHiddenPaymentMode() {
		return hiddenPaymentMode;
	}

	public void setHiddenPaymentMode(String hiddenPaymentMode) {
		this.hiddenPaymentMode = hiddenPaymentMode;
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

	public String getCertifiedBy() {
		return certifiedBy;
	}

	public void setCertifiedBy(String certifiedBy) {
		this.certifiedBy = certifiedBy;
	}

	public String getDescription() {
		return description;
	}

	public void setDescription(String description) {
		this.description = description;
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
	
	public String getNbt() {
		return nbt;
	}

	public void setNbt(String nbt) {
		this.nbt = nbt;
	}

	public String getGrandTotal() {
		return grandTotal;
	}

	public void setGrandTotal(String grandTotal) {
		this.grandTotal = grandTotal;
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
	
	public String getIdNo() {
		return idNo;
	}

	public void setIdNo(String idNo) {
		this.idNo = idNo;
	}

	public String getLoggedInUserId() {
		return loggedInUserId;
	}

	public void setLoggedInUserId(String loggedInUserId) {
		this.loggedInUserId = loggedInUserId;
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

	public Format getFormat() {
		return format;
	}

	public void setFormat(Format format) {
		this.format = format;
	}
	//Hidden
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
	
	public String getConfirmedBy() {
		return confirmedBy;
	}

	public void setConfirmedBy(String confirmedBy) {
		this.confirmedBy = confirmedBy;
	}

	public Date getConfirmedDate() {
		return confirmedDate;
	}

	public void setConfirmedDate(Date confirmedDate) {
		this.confirmedDate = confirmedDate;
	}

	public String getConfirmedTime() {
		return confirmedTime;
	}

	public void setConfirmedTime(String confirmedTime) {
		this.confirmedTime = confirmedTime;
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
	
	public String getErrorMessage() {
		return errorMessage;
	}

	public void setErrorMessage(String errorMessage) {
		this.errorMessage = errorMessage;
	}


    public Map <String, String[]> getParameters() {
		return parameters;
	}

    @Override
	public void setParameters(Map<String, String[]> parameters) {
		this.parameters=parameters;
	}
    
    public String getPayDate() {
		return payDate;
	}

	public void setPayDate(String payDate) {
		this.payDate = payDate;
	}

	public String getChequeDate() {
		return chequeDate;
	}

	public void setChequeDate(String chequeDate) {
		this.chequeDate = chequeDate;
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
		if(branchList == null){
			this.branchList = new ArrayList<BankBranch>();
		}
		this.branchList = branchList;
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

	public String getPivReceiptNo() {
		return pivReceiptNo;
	}

	public void setPivReceiptNo(String pivReceiptNo) {
		this.pivReceiptNo = pivReceiptNo;
	}
	
	public String getServiceCity() {
		return serviceCity;
	}

	public void setServiceCity(String serviceCity) {
		this.serviceCity = serviceCity;
	}
	public List<String> getApplicationIdList() {
		return applicationIdList;
	}

	public void setApplicationIdList(List<String> applicationIdList) {
		this.applicationIdList = applicationIdList;
	}
	public List<String> getApplicationNoList() {
		return applicationNoList;
	}

	public void setApplicationNoList(List<String> applicationNoList) {
		this.applicationNoList = applicationNoList;
	}
	
	public List<String> getTariffCategoryCode() {
		return tariffCategoryCode;
	}

	public void setTariffCategoryCode(List<String> tariffCategoryCode) {
		this.tariffCategoryCode = tariffCategoryCode;
	}

	public List<String> getTariffCode() {
		return tariffCode;
	}

	public void setTariffCode(List<String> tariffCode) {
		this.tariffCode = tariffCode;
	}
	
	public String getApplicationId() {
		return applicationId;
	}

	public void setApplicationId(String applicationId) {
		this.applicationId = applicationId;
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

	public String getServicePostalCode() {
		return servicePostalCode;
	}

	public void setServicePostalCode(String servicePostalCode) {
		this.servicePostalCode = servicePostalCode;
	}
	
	public String getMessageType() {
		return messageType;
	}

	public void setMessageType(String messageType) {
		this.messageType = messageType;
	}
	
	public String getApplicationFee() {
		return applicationFee;
	}

	public void setApplicationFee(String applicationFee) {
		this.applicationFee = applicationFee;
	}
	
	public String getRegion() {
		return region;
	}

	public void setRegion(String region) {
		this.region = region;
	}

	public String getPivSubType() {
		return pivSubType;
	}

	public void setPivSubType(String pivSubType) {
		this.pivSubType = pivSubType;
	}

    
	public String getHdnChequeBranchCode() {
		return hdnChequeBranchCode;
	}

	public void setHdnChequeBranchCode(String hdnChequeBranchCode) {
		this.hdnChequeBranchCode = hdnChequeBranchCode;
	}

	public String newDirectFromMain(){
		try{
			HttpServletRequest request = ServletActionContext.getRequest();
			//String sessionKey= (String) request.getSession().getAttribute("region");
			pivSubType=request.getParameter("pivSubType");
			
			String sessionKey= (String) request.getSession().getAttribute("region");
			String estimateNo=request.getParameter("estNo");
			String costCen=request.getParameter("costCen");
			//String costCen=request.getParameter("costCen");
			if(pivSubType == null){
				pivSubType = Constants.PIV_SUB_TYPE_TOTAL;
			}
			request.getSession().setAttribute("pivSubType", pivSubType);
			setDescription("STANDARD ESTIMATION PAYMENT");
			setBankList(getAllBankList());
			setBranchList(branchList);
			//System.out.println("1");
			setLoggedData();
			//System.out.println("2");
			ApplicationEjb ejb=new ApplicationEjb(getSessionKey("region"));
			//System.out.println("3");
			setFormat(new Format());
			System.out.println("4 :"+getSessionKey("smcType") +"fff : "+ PivPrefixType.getEST_XXX_Type(getSessionKey("smcType")));
			setPivNo("PIV/"+getCostCenterNo()+"/"+PivPrefixType.getEST_XXX_Type(getSessionKey("smcType"))+"/"+getFormat().getYear()+"/xxxx");
			System.out.println("5 :" + getPivNo());
			//setBankList(getAllBankList());
			//System.out.println("6");
			setState("new");
			//System.out.println("7");
			setPath(NEWPATH);
	    	applicationIdList=new ArrayList<String>();
	    	//System.out.println("8");
	    	//applicationIdList=ejb.getApplicationIdList(costCenterNo, getSessionKey("smcType"));
	    	
	    	//EstimateEjb estimateEjb = new EstimateEjb(getSessionKey("region"));
			//List<Long> status = new ArrayList<Long>();
			//status.add(StandardEstimateStatus.APPROVED_ESTIMATES.getKey());
			//status.add(StandardEstimateStatus.APPLICATION_MODIFIED.getKey());
			applicationIdList.add(estimateNo); 
			if(applicationIdList == null){
				applicationIdList = new ArrayList<String>();
			}
	    	//System.out.println("9");
	    	setApplicationIdList(applicationIdList);
	    	//System.out.println("10");
	    	return SUCCESS;
		}catch (Exception e) {
			return ERROR;
		}
	}
	public String displayBankBranches()
	{
		BankEjb bankEjb = new BankEjb(getSessionKey("region"));
		branchList = bankEjb.getBranchList(chequeBankCode);
		chequeBranchCode = hdnChequeBranchCode;
		return SUCCESS;
	}
    
    

}
