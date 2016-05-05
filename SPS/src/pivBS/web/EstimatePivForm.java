package pivBS.web;

import java.math.BigDecimal;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.List;
import java.util.Map;
import java.text.NumberFormat;



import job.model.Pcesthmt;
import job.service.PcesthmtEjb;
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
import piv.model.PivDetail;
import piv.model.PivDetailPK;
import piv.service.PivDetailEjb;
import piv.service.PivEjb;
import util.common.ApplicationType;
import util.common.CurrencyToWords;
import util.common.EstimateStatus;
import util.common.Format;
import util.common.LoanDetails;
import util.common.PIVStatus;
import util.common.PivPrefixType;
import application.model.Applicant;
import application.model.Application;
import application.model.Spapplon;
import application.model.SpapplonPK;
import application.model.WiringLandDetail;
import application.model.WiringLandDetailPK;
import application.service.ApplicantEjb;
import application.service.ApplicationEjb;
import application.service.SpapplonEjb;
import application.service.WiringLandDetailEjb;
import com.opensymphony.xwork2.ActionSupport;
import costcenter.model.Gldeptin;
import costcenter.service.CostCenterEjb;
import estimate.model.Pcesthtt;
import estimate.model.Speststd;
import estimate.model.SpeststdPK;
import estimate.model.Spstdesthmt;
import estimate.model.SpstdesthmtPK;
import estimate.service.PcesthttEjb;
import estimate.service.SpeststdEjb;
import estimate.service.SpstdesthmtEjb;

@SuppressWarnings("serial")
public class EstimatePivForm extends ActionSupport implements SessionAware, ParameterAware{
	private static final Log log = LogFactory.getLog(PivForm.class);
	private static final String viewPath="Estimation>";
	private Map<String, Object> session;
    private String loggedInUserId;
    private String path;
    NumberFormat nf = NumberFormat.getInstance();


	//private Map<?, ?> parameters;
    private Map <String, String[]> parameters;
   
    public Map <String, String[]> getParameters() {
		return parameters;
	}



	// Persistence
	//private EntityManagerFactory emf;
	//private EntityManager em;
	//Date
	private Format format;
    
    
    
	//JSP Fields
	private String estimateNo;
    private String pivNo;
	private String pivReceiptNo;
    private String pivSeqNo;
    private String payingBankName;
    private String payingBankCode;
    private String payingBranchName;
    private String payingBranchCode;
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
    private String grandTotal;
    private String loanReference;
    private String loanAmount;
    private Date paidDate;
    private Date chequeDate;
    private String statusDesc;
    private List<Bank> bankList = new ArrayList();
    private List<BankBranch> bankBranchList = new ArrayList();
    private String pivType;
    
    
    //Hidden Fields
    private String hiddenPaymentMode;
    private String state;
    private String hiddenSecondState;
    private String hasAppLoan;
    private String referenceType;
    private String region ;
    
    //Other Fields
    private String preparedBy;
    public Date getPaidDate() {
		return paidDate;
	}



	private String preparedTime;
    private String confirmedBy;
    private String confirmedTime;
    private String updUser;
    private Date updDate;
    private String updTime;
    private String isConfirm;
    private String isCancel;
    private String isGenerate;
    private String isGenerateSuccess;
    private String isRevise;
    private String isPrint;
    private String isModify;
    
    public String city;
    public String subUrb;
    public String postalCode;
    public String street;
    public String isPivView;
    private String hdnChequeBranchCode;
    
    //private String isFestival;
    private String isNewService;
    private String isTempConn;
    private String isReConn;
    private String isPhaseChange;
    private String isConversion;
    private String isCostRec;
    private String isLoan;
    private String isMeterChange;
    private String isOtherCR;
    
    //
    private String errorMessage;
    private String successMessage;
    
    /**
    *
    */
    public EstimatePivForm() {
		super();
    } 
    
    
   public void setLoggedData() {
       log.info( getSession());
       setLoggedInUserId(getSessionKey("userName"));
       setCostCenterNo(getSessionKey("costCenterNo"));
       setCebBranch(getSessionKey("costCenterName"));
       region = getSessionKey("region");
   }
   
   public String getSessionKey(String key) {
       return getSession().get(key).toString();
   }
    
    

	public String execute(){
    	setLoggedData();
    	setState("view");
    	setPath(viewPath);
    	setCostCenterNo(getSessionKey("costCenterNo"));
    	Calendar cal = Calendar.getInstance();
		int curYear = cal.get(Calendar.YEAR);
		pivNo = "PIV/"+costCenterNo+"/"+PivPrefixType.getEST_XXX_Type(getSessionKey("smcType"))+"/"+curYear+"/";
		isCostRec = null;
    	return SUCCESS;
    }
	
	/*public String viewtc(){
    	setLoggedData();
    	setState("view");
    	setPath(viewPath);
    	setCostCenterNo(getSessionKey("costCenterNo"));
    	Calendar cal = Calendar.getInstance();
		int curYear = cal.get(Calendar.YEAR);
		pivNo = "PIV/"+costCenterNo+"/"+PivPrefixType.EST_TEMP_CONN+"/"+curYear+"/";
		isCostRec = null;
    	return SUCCESS;
    }
	
	public String viewcr(){
    	setLoggedData();
    	setState("view");
    	setPath(viewPath);
    	setCostCenterNo(getSessionKey("costCenterNo"));
    	Calendar cal = Calendar.getInstance();
		int curYear = cal.get(Calendar.YEAR);
		pivNo = "PIV/"+costCenterNo+"/"+PivPrefixType.EST_CR_JOBS+"/"+curYear+"/";
		isCostRec = "true";
    	return SUCCESS;
    }*/
	
	public void setDefaultsFromMainMenu(){
		//Dhanusha
		/*HttpServletRequest request = ServletActionContext.getRequest();
		String passedEstimateNo=request.getParameter("estNo");
		String costCen=request.getParameter("costCen");
		if(passedEstimateNo!=null && passedEstimateNo.length()>0 && costCen!=null && costCen.length()>0){
			setEstimateNo(passedEstimateNo);
			setCostCenterNo(costCen);
			viewSMCInfo();
		}	*/
	}
	
	public String generate(){
		System.out.println("generate");
    	setLoggedData();
    	setState("view");
    	setCostCenterNo(getSessionKey("costCenterNo"));
    	preparedBy = getSession().get("userName").toString();
    	isConfirm = null;
    	isCancel = null;
    	isRevise = null;
    	isPrint = null;
    	isModify = null;
    	isNewService = "true";
    	isGenerate = "true";
    	isReConn = null;
    	isPhaseChange = null;
    	isConversion = null;
    	isMeterChange = null;
    	isOtherCR = null;
    	isLoan = null;
    	setPath(viewPath);
    	setCostCenterBank();
    	Calendar cal = Calendar.getInstance();
		int curYear = cal.get(Calendar.YEAR);
		estimateNo = costCenterNo+"/"+PivPrefixType.getEST_XXX_Type(getSessionKey("smcType"))+"/"+curYear+"/";
		setDefaultsFromMainMenu();
    	return SUCCESS;
    }
	
	/*public String generatetc1(){
		System.out.println("generate");
    	setLoggedData();
    	setState("view");
    	setCostCenterNo(getSessionKey("costCenterNo"));
    	preparedBy = getSession().get("userName").toString();
    	isConfirm = null;
    	isCancel = null;
    	isRevise = null;
    	isPrint = null;
    	isModify = null;
    	isGenerate = "true";
    	isTempConn = "true";
    	isReConn = null;
    	isPhaseChange = null;
    	isConversion = null;
    	isMeterChange = null;
    	isOtherCR = null;
    	isLoan = null;
    	setPath(viewPath);
    	setCostCenterBank();
    	Calendar cal = Calendar.getInstance();
		int curYear = cal.get(Calendar.YEAR);
		estimateNo = costCenterNo+"/"+PivPrefixType.getAPP_XXX_Type(getSessionKey("smcType"))+"/"+curYear+"/";
		setDefaultsFromMainMenu();
    	return SUCCESS;
    }
	
	public String generateCR(){
		System.out.println("generate");
    	setLoggedData();
    	setState("view");
    	setCostCenterNo(getSessionKey("costCenterNo"));
    	preparedBy = getSession().get("userName").toString();
    	isConfirm = null;
    	isCancel = null;
    	isRevise = null;
    	isPrint = null;
    	isModify = null;
    	isGenerate = "true";
    	isTempConn = null;
    	isReConn = "true";
    	isPhaseChange = null;
    	isConversion = null;
    	isMeterChange = null;
    	isOtherCR = null;
    	isLoan = null;
    	setPath(viewPath);
    	setCostCenterBank();
    	Calendar cal = Calendar.getInstance();
		int curYear = cal.get(Calendar.YEAR);
		estimateNo = costCenterNo+"/"+PivPrefixType.getAPP_XXX_Type(getSessionKey("smcType"))+"/"+curYear+"/";
		setDefaultsFromMainMenu();
    	return SUCCESS;
    }
	
	public String generateCP(){
		System.out.println("generate");
    	setLoggedData();
    	setState("view");
    	setCostCenterNo(getSessionKey("costCenterNo"));
    	preparedBy = getSession().get("userName").toString();
    	isConfirm = null;
    	isCancel = null;
    	isRevise = null;
    	isPrint = null;
    	isModify = null;
    	isGenerate = "true";
    	isTempConn = null;
    	isReConn = null;
    	isPhaseChange = "true";
    	isConversion = null;
    	isMeterChange = null;
    	isOtherCR = null;
    	isLoan = null;
    	setPath(viewPath);
    	setCostCenterBank();
    	Calendar cal = Calendar.getInstance();
		int curYear = cal.get(Calendar.YEAR);
		estimateNo = costCenterNo+"/"+PivPrefixType.getAPP_XXX_Type(getSessionKey("smcType"))+"/"+curYear+"/";
		setDefaultsFromMainMenu();
    	return SUCCESS;
    }
	
	public String generateCC(){
		System.out.println("generate");
    	setLoggedData();
    	setState("view");
    	setCostCenterNo(getSessionKey("costCenterNo"));
    	preparedBy = getSession().get("userName").toString();
    	isConfirm = null;
    	isCancel = null;
    	isRevise = null;
    	isPrint = null;
    	isModify = null;
    	isGenerate = "true";
    	isTempConn = null;
    	isReConn = null;
    	isPhaseChange = null;
    	isConversion = "true";
    	isMeterChange = null;
    	isOtherCR = null;
    	isLoan = null;
    	setPath(viewPath);
    	setCostCenterBank();
    	Calendar cal = Calendar.getInstance();
		int curYear = cal.get(Calendar.YEAR);
		estimateNo = costCenterNo+"/"+PivPrefixType.getAPP_XXX_Type(getSessionKey("smcType"))+"/"+curYear+"/";
		setDefaultsFromMainMenu();
    	return SUCCESS;
    }
	
	public String generateCM(){
		System.out.println("generate meter change PIV");
    	setLoggedData();
    	setState("view");
    	setCostCenterNo(getSessionKey("costCenterNo"));
    	preparedBy = getSession().get("userName").toString();
    	isConfirm = null;
    	isCancel = null;
    	isRevise = null;
    	isPrint = null;
    	isModify = null;
    	isGenerate = "true";
    	isTempConn = null;
    	isReConn = null;
    	isPhaseChange = null;
    	isConversion = null;
    	isMeterChange = "true";
    	isOtherCR = null;
    	isLoan = null;
    	setPath(viewPath);
    	setCostCenterBank();
    	Calendar cal = Calendar.getInstance();
		int curYear = cal.get(Calendar.YEAR);
		estimateNo = costCenterNo+"/"+PivPrefixType.getAPP_XXX_Type(getSessionKey("smcType"))+"/"+curYear+"/";
		setDefaultsFromMainMenu();
    	return SUCCESS;
    }
	
	public String generateOT(){
		System.out.println("generate other CR PIV");
    	setLoggedData();
    	setState("view");
    	setCostCenterNo(getSessionKey("costCenterNo"));
    	preparedBy = getSession().get("userName").toString();
    	isConfirm = null;
    	isCancel = null;
    	isRevise = null;
    	isPrint = null;
    	isModify = null;
    	isGenerate = "true";
    	isTempConn = null;
    	isReConn = null;
    	isPhaseChange = null;
    	isConversion = null;
    	isMeterChange = null;
    	isLoan = null;
    	isOtherCR = "true";
    	setPath(viewPath);
    	setCostCenterBank();
    	Calendar cal = Calendar.getInstance();
		int curYear = cal.get(Calendar.YEAR);
		estimateNo = costCenterNo+"/"+PivPrefixType.getAPP_XXX_Type(getSessionKey("smcType"))+"/"+curYear+"/";
		setDefaultsFromMainMenu();
    	return SUCCESS;
    }
	
	public String generateLoan(){
		setLoggedData();
    	setState("view");
    	setCostCenterNo(getSessionKey("costCenterNo"));
    	preparedBy = getSession().get("userName").toString();
    	isConfirm = null;
    	isCancel = null;
    	isRevise = null;
    	isPrint = null;
    	isModify = null;
    	isNewService = "true";
    	isGenerate = "true";
    	isTempConn = null;
    	isReConn = null;
    	isPhaseChange = null;
    	isConversion = null;
    	isMeterChange = null;
    	isOtherCR = null;
    	isLoan = "true";
    	setPath(viewPath);
    	   	
    	CostCenterEjb costCenterEjb = new CostCenterEjb(getSessionKey("region"));
		Gldeptin gldeptin = costCenterEjb.findById(costCenterNo);
		payingBankCode =  gldeptin.getPosCenter();
		payingBranchCode = gldeptin.getPosA();
		
    	Calendar cal = Calendar.getInstance();
		int curYear = cal.get(Calendar.YEAR);
		estimateNo = costCenterNo+"/"+PivPrefixType.EST_NEW_CONN+"/"+curYear+"/";
    	return SUCCESS;
    }*/
	
	public String confirm(){
    	setLoggedData();
    	setState("confirm");
    	setCostCenterNo(getSessionKey("costCenterNo"));
    	isConfirm = "true";
    	isCancel = null;
    	isGenerate = null;
    	isRevise = null;
    	isPrint = null;
    	isModify = null;
    	isCostRec = null;
    	isNewService = "true";
    	setPath(viewPath);
    	Calendar cal = Calendar.getInstance();
		int curYear = cal.get(Calendar.YEAR);
		pivNo = "PIV/"+costCenterNo+"/"+PivPrefixType.getEST_XXX_Type(getSessionKey("smcType"))+"/"+curYear+"/";
    	return SUCCESS;
    }
	
	/*public String confirmtc(){
    	setLoggedData();
    	setState("confirm");
    	setCostCenterNo(getSessionKey("costCenterNo"));
    	isConfirm = "true";
    	isCancel = null;
    	isGenerate = null;
    	isRevise = null;
    	isPrint = null;
    	isModify = null;
    	isTempConn = "true";
    	setPath(viewPath);
    	Calendar cal = Calendar.getInstance();
		int curYear = cal.get(Calendar.YEAR);
		isCostRec = null;
		pivNo = "PIV/"+costCenterNo+"/"+PivPrefixType.getAPP_XXX_Type(getSessionKey("smcType"))+"/"+curYear+"/";
    	return SUCCESS;
    }
	
	public String confirmcr(){
    	setLoggedData();
    	setState("confirm");
    	setCostCenterNo(getSessionKey("costCenterNo"));
    	isConfirm = "true";
    	isCancel = null;
    	isGenerate = null;
    	isRevise = null;
    	isPrint = null;
    	isModify = null;
    	isTempConn = "true";
    	setPath(viewPath);
    	Calendar cal = Calendar.getInstance();
		int curYear = cal.get(Calendar.YEAR);
		isCostRec = "true";
		pivNo = "PIV/"+costCenterNo+"/"+PivPrefixType.getAPP_XXX_Type(getSessionKey("smcType"))+"/"+curYear+"/";
    	return SUCCESS;
    }*/
	
	public String cancel(){
    	setLoggedData();
    	setState("cancel");
    	setCostCenterNo(getSessionKey("costCenterNo"));
    	isConfirm = null;
    	isCancel = "true";
    	isGenerate = null;
    	isRevise = null;
    	isPrint = null;
    	isModify = null;
    	setPath(viewPath);
    	isNewService = "true";
    	isCostRec = null;
    	Calendar cal = Calendar.getInstance();
		int curYear = cal.get(Calendar.YEAR);
		
    	pivNo = "PIV/"+costCenterNo+"/"+PivPrefixType.getEST_XXX_Type(getSessionKey("smcType"))+"/"+curYear+"/";
    	//preparedBy = getSession().get("userName").toString();
    	return SUCCESS;
    }
	
	public String print(){
		setLoggedData();
    	setState("print");
    	setCostCenterNo(getSessionKey("costCenterNo"));
    	isConfirm = null;
    	isPrint = "true";
    	isGenerate = null;
    	isRevise = null;
    	isCancel = null;
    	isModify = null;
    	isNewService = "true";
    	isCostRec = null;
    	setPath(viewPath);
    	Calendar cal = Calendar.getInstance();
		int curYear = cal.get(Calendar.YEAR);
		estimateNo = costCenterNo+"/"+PivPrefixType.getEST_XXX_Type(getSessionKey("smcType"))+"/"+curYear+"/";
    	//preparedBy = getSession().get("userName").toString();
    	pivType = "EST";
		return SUCCESS;
    }
	
	/*public String printtc(){
		setLoggedData();
    	setState("print");
    	setCostCenterNo(getSessionKey("costCenterNo"));
    	isConfirm = null;
    	isPrint = "true";
    	isGenerate = null;
    	isRevise = null;
    	isCancel = null;
    	isModify = null;
    	isTempConn = "true";
    	isCostRec = null;
    	setPath(viewPath);
    	Calendar cal = Calendar.getInstance();
		int curYear = cal.get(Calendar.YEAR);
		estimateNo = costCenterNo+"/"+PivPrefixType.getAPP_XXX_Type(getSessionKey("smcType"))+"/"+curYear+"/";
    	//preparedBy = getSession().get("userName").toString();
		pivType = "EST";
    	return SUCCESS;
    }
	
	public String printcr(){
		setLoggedData();
    	setState("print");
    	setCostCenterNo(getSessionKey("costCenterNo"));
    	isConfirm = null;
    	isPrint = "true";
    	isGenerate = null;
    	isRevise = null;
    	isCancel = null;
    	isModify = null;
    	isTempConn = "true";
    	isCostRec = "true";
    	setPath(viewPath);
    	Calendar cal = Calendar.getInstance();
		int curYear = cal.get(Calendar.YEAR);
		estimateNo = costCenterNo+"/"+PivPrefixType.getAPP_XXX_Type(getSessionKey("smcType"))+"/"+curYear+"/";
		pivType = "EST";
    	return SUCCESS;
    }*/
	
	public String modify(){
		setLoggedData();
    	setState("print");
    	setCostCenterNo(getSessionKey("costCenterNo"));
    	isConfirm = null;
    	isPrint = null;
    	isGenerate = null;
    	isRevise = null;
    	isCancel = null;
    	isModify = "true";
    	isNewService = "true";
    	isCostRec = null;
    	setPath(viewPath);
    	Calendar cal = Calendar.getInstance();
		int curYear = cal.get(Calendar.YEAR);
		estimateNo = costCenterNo+"/"+PivPrefixType.getEST_XXX_Type(getSessionKey("smcType"))+"/"+curYear+"/";
    	//preparedBy = getSession().get("userName").toString();
    	pivType = "EST";
		return SUCCESS;
    }
	
	/*public String modifytc(){
		setLoggedData();
    	setState("print");
    	setCostCenterNo(getSessionKey("costCenterNo"));
    	isConfirm = null;
    	isPrint = null;
    	isGenerate = null;
    	isRevise = null;
    	isCancel = null;
    	isModify = "true";
    	isTempConn = "true";
    	isCostRec = null;
    	setPath(viewPath);
    	Calendar cal = Calendar.getInstance();
		int curYear = cal.get(Calendar.YEAR);
		estimateNo = costCenterNo+"/"+PivPrefixType.getAPP_XXX_Type(getSessionKey("smcType"))+"/"+curYear+"/";
		pivType = "EST";
    	return SUCCESS;
    }
	
	public String modifycr(){
		setLoggedData();
    	setState("print");
    	setCostCenterNo(getSessionKey("costCenterNo"));
    	isConfirm = null;
    	isPrint = null;
    	isGenerate = null;
    	isRevise = null;
    	isCancel = null;
    	isModify = "true";
    	isTempConn = "true";
    	isCostRec = "true";
    	setPath(viewPath);
    	Calendar cal = Calendar.getInstance();
		int curYear = cal.get(Calendar.YEAR);
		estimateNo = costCenterNo+"/"+PivPrefixType.getAPP_XXX_Type(getSessionKey("smcType"))+"/"+curYear+"/";
		pivType = "EST";
    	return SUCCESS;
    }
	*/
	
	public String viewRevisePIV(){
    	setLoggedData();
    	setState("view");
    	setPath(viewPath);
    	setCostCenterNo(getSessionKey("costCenterNo"));
    	isRevise = "true";
    	return SUCCESS;
    }
	
	public String generateRevisePIV(){
		System.out.println("generate");
    	setLoggedData();
    	setState("view");
    	setCostCenterNo(getSessionKey("costCenterNo"));
    	preparedBy = getSession().get("userName").toString();
    	isConfirm = null;
    	isCancel = null;
    	isGenerate = "true";
    	isRevise = "true";
    	isModify = null;
    	isPrint = null;
    	setPath(viewPath);
    	setCostCenterBank();
    	return SUCCESS;
    }
	
	public String confirmRevisePIV(){
    	setLoggedData();
    	setState("confirm");
    	setCostCenterNo(getSessionKey("costCenterNo"));
    	isConfirm = "true";
    	isCancel = null;
    	isGenerate = null;
    	isRevise = "true";
    	isModify = null;
    	isPrint = null;
    	setPath(viewPath);
    	return SUCCESS;
    }
	
	public String cancelRevisePIV(){
    	setLoggedData();
    	setState("cancel");
    	setCostCenterNo(getSessionKey("costCenterNo"));
    	isConfirm = null;
    	isCancel = "true";
    	isGenerate = null;
    	isRevise = "true";
    	isModify = null;
    	isPrint = null;
    	setPath(viewPath);
    	//preparedBy = getSession().get("userName").toString();
    	return SUCCESS;
    }
	
	public String printRevisePIV(){
		setLoggedData();
    	setState("print");
    	setCostCenterNo(getSessionKey("costCenterNo"));
    	isConfirm = null;
    	isPrint = "true";
    	isGenerate = null;
    	isRevise = "true";
    	isCancel = null;
    	isModify = null;
    	setPath(viewPath);
    	//preparedBy = getSession().get("userName").toString();
    	return SUCCESS;
    }
	
	public String modifyRevisePIV(){
		setLoggedData();
    	setState("print");
    	setCostCenterNo(getSessionKey("costCenterNo"));
    	isConfirm = null;
    	isPrint = null;
    	isGenerate = null;
    	isRevise = "true";
    	isCancel = null;
    	isModify = "true";
    	setPath(viewPath);
    	//preparedBy = getSession().get("userName").toString();
    	return SUCCESS;
    }
	
	public String clear()
	{
		System.out.println("clearing..");
		this.pivReceiptNo = "";
		setLoggedData();
		setPath(viewPath);
		clearFields();
		this.isPivView = null;
		return SUCCESS;
	}
	
	private void setFormData()
	{
		BankEjb bankEjb = new BankEjb(getSessionKey("region"));
		bankList = bankEjb.getAllBanks();
		
	}
	
	private void setCostCenterBank()
	{
		CostCenterEjb costCenterEjb = new CostCenterEjb(getSessionKey("region"));
		Gldeptin gldeptin = costCenterEjb.findById(costCenterNo);
		if(gldeptin != null){
			payingBankCode =  gldeptin.getBankCode();
			payingBranchCode = gldeptin.getBranchCode();
		}
	}
	
	
	private void clearFields()
	{
		this.pivReceiptNo = "";
		this.amountInWords = "";
		this.chequeBankCode = "";
		this.chequeBranchCode = "";
		this.chequeNo = "";
		this.chequeDate = null;
		this.paymentMode = "";
		this.idNo = "";
		this.loanReference = "";
		this.loanAmount = "";
		this.paidDate = null;
		this.fullName = "";
		this.description = "";
		
		this.miscellaneousDeposit = "";
		this.securityDeposit = "";
		this.serConnOrElecSch = "";
		this.subTotal = "";
		this.grandTotal = "";
		this.miscellaneousIncome = "";
		this.electricityDebtors = "";
		this.tenderDeposit = "";
		this.cashInTransit = "";
		this.vat = "";
		this.forDishonouredCheque = "";
		this.isPivView = "";
		
		this.street = "";
		this.subUrb = "";
		this.city = "";
		this.postalCode = "";
		this.isGenerateSuccess = "";
		
	}
	
	
	public String printPiv()
	{
		PivEjb ejb;
		try
		{
			setLoggedData();
			pivPrint();
			clearFields();
			this.estimateNo = "";
			this.pivNo = "";
			return SUCCESS;
		} 
		catch (Exception e) 
		{
			e.printStackTrace();
			setErrorMessage("Unexpected Error! Please contact system administrator.");
			return ERROR;
		}
	}
	
	public String modifyPiv()
	{
		try
		{
			setLoggedData();
			PivEjb pivEjb = new PivEjb(getSessionKey("region"));
    		PivDetailPK pk = new PivDetailPK();
    		pk.setDeptId(costCenterNo);
    		pk.setPivNo(pivNo);
    		PivDetail piv = pivEjb.findById(pk);
    		piv.setPivReceiptNo(pivReceiptNo);
    		pivEjb.updatePivDetail(piv);
    		setSuccessMessage("PIV Receipt No. changed successfully.");
			clearFields();
			this.estimateNo = "";
			this.pivReceiptNo = "";
			return SUCCESS;
		} 
		catch (Exception e) 
		{
			e.printStackTrace();
			setErrorMessage("Unexpected Error! Please contact system administrator.");
			return ERROR;
		}
	}
	
	
	public String viewPIVDetails()
	{
		System.out.println("viewPIVDetails");
		setLoggedData();
    	setState("view");
    	setPath(viewPath);
    	this.errorMessage = "";
    	clearFields();
    	preparedBy = getSession().get("userName").toString();
    	setFormData();
    	try
    	{
    		PivEjb pivEjb = new PivEjb(getSessionKey("region"));
    		PivDetailPK pk = new PivDetailPK();
    		pk.setDeptId(costCenterNo);
    		pk.setPivNo(pivNo);
    		PivDetail piv = pivEjb.findById(pk);
	    	if(piv==null)
			{
	    		isPivView = null;
				setErrorMessage("PIV does not exist.");
				return ERROR;
			}
			else
			{
				
				String pivStatus = piv.getStatus();
				String refType = piv.getReferenceType();
				/*if(isRevise==null && !refType.equals("EST"))
				{
					setErrorMessage("PIV does not exist.");
					isPivView = null;	
					return ERROR;
				}
				else if(isRevise!=null && isRevise.equals("true") && !refType.equals("JOB"))
				{
					setErrorMessage("PIV does not exist.");
					isPivView = null;	
					return ERROR;
				}*/
				/*else
				{*/
					if(isRevise!=null && isRevise.equals("true"))
					{
						String jobNo = piv.getReferenceNo();
						PcesthmtEjb pcesthmtEjb = new PcesthmtEjb(getSessionKey("region"));
				    	Pcesthmt pcesthmt = pcesthmtEjb.findByJobNo(jobNo, costCenterNo);
				    	estimateNo = pcesthmt.getId().getEstimateNo();
					}
					else
					{
						estimateNo = piv.getReferenceNo();
					}
					//piv printing, view
					if((isConfirm==null || !isConfirm.equals("true") && (isCancel==null || !isCancel.equals("true"))))
					{
						ApplicationEjb applicationEJB = new ApplicationEjb(getSessionKey("region"));
						Application application = applicationEJB.findByApplicationNo(estimateNo);
						hasAppLoan = application.getIsLoanApp();
						setApplicantData(application);
						setPIVData(piv);
						SimpleDateFormat sdf = new SimpleDateFormat("dd/MM/yyyy");
						date = sdf.format(new Date());
						isPivView = "true";
						
						return SUCCESS;
					}
					else
					{//piv cancel, confirm 
						if(pivStatus.equals(PIVStatus.NEW))
						{
							ApplicationEjb applicationEJB = new ApplicationEjb(getSessionKey("region"));
							Application application = applicationEJB.findByApplicationNo(estimateNo);
							if(
								(isTempConn!=null && isTempConn.equals("true") && application.getApplicationType().equals(ApplicationType.TEMP_CONN))
								|| (isCostRec!=null && isCostRec.equals("true") && application.getApplicationType().equals(ApplicationType.COST_RECOVERY))
								|| (isNewService!=null && isNewService.equals("true")))
							{
								hasAppLoan = application.getIsLoanApp();
								setApplicantData(application);
								setPIVData(piv);
								isPivView = "true";
								//setFormData();
								return SUCCESS;
							}
							else
							{
								setErrorMessage("PIV does not exist.");
								isPivView = null;	
								return ERROR;
							}
						}
						else
						{
							if(pivStatus.equals(PIVStatus.PAID))
							{
								setErrorMessage("PIV already confirmed.");
							}
							else if(pivStatus.equals(PIVStatus.DESTROYED))
							{
								setErrorMessage("PIV already cancelled.");
							}
							isPivView = null;
							return ERROR;
						}
					}
					
				//}
			}
    	}
    	catch(Exception e)
    	{
    		e.printStackTrace();
    		setErrorMessage("Unexpected Error! Please retry.");
    		isPivView = null;
			return ERROR;
    	}
		
    	
	}
	
	public String viewSMCInfo()
	{
		if(isRevise!=null && isRevise.equals("true"))
		{
			return viewJobDetails();
		}
		else
		{
			return viewEstimateDetails();
		}
		
	}
	
	
	private String viewEstimateDetails()
	{
		System.out.println("viewEstimateDetails");
		setLoggedData();
    	setState("view");
    	setPath(viewPath);
    	this.errorMessage = "";
    	setFormData();
    	clearFields();
    	if((isPrint!=null && isPrint.equals("true"))||(isModify!=null && isModify.equals("true")))
		{
	    	this.pivNo = "";
		}
    	else if(isGenerate==null)
    	{
    		this.estimateNo = "";
    	}
    	preparedBy = getSession().get("userName").toString();
    	try
    	{
    		//if((isPrint!=null && isPrint.equals("true"))||(isModify!=null && isModify.equals("true")))
		//	{//piv print or modify
    			PivDetailEjb pivDetailEjb = new PivDetailEjb(getSessionKey("region"));
    			PivDetail piv = pivDetailEjb.findByReferenceNo(costCenterNo, estimateNo, pivType);
    			boolean pivExist = true;
    			/*if(piv==null)
    			{
    				piv = pivDetailEjb.findByReferenceNo(costCenterNo, estimateNo, "ELN");
    				if(piv==null)
        			{
    					pivExist = false;
	    			}
    				else
    				{
    					pivExist = true;
    				}
    			}*/
    			if(piv==null)
    			{
    				setErrorMessage("PIV does not exist for this estimate.");
					return ERROR;
    			}
    			else
    			{
    				//if(piv.getStatus().equals(PIVStatus.NEW))
    				//{
    					ApplicationEjb applicationEJB = new ApplicationEjb(getSessionKey("region"));
						Application application = applicationEJB.findByApplicationNo(estimateNo);
						setApplicantData(application);
						
						SpstdesthmtPK spstdesthmtPK = new SpstdesthmtPK();
						spstdesthmtPK.setDeptId(costCenterNo);
						spstdesthmtPK.setStdNo(estimateNo);
						spstdesthmtPK.setApplicationNo(estimateNo);
						SpstdesthmtEjb spstdesthmtEjb = new SpstdesthmtEjb(getSessionKey("region"));
						Spstdesthmt spstdesthmt = spstdesthmtEjb.findById(spstdesthmtPK, getSessionKey("region"));
						
						setEstimateData(spstdesthmt,false);
						setPIVData(piv);
						this.isPivView = "true";
    					return SUCCESS;
    				/*}
    				else
    				{
    					if(isPrint!=null && isPrint.equals("true"))
    						setErrorMessage("Sorry! You cannot print PIV for this estimate.");
    					else if(isModify!=null && isModify.equals("true"))
    						setErrorMessage("Sorry! You cannot modify this PIV.");
    					return ERROR;
    				}*/
    			}
		//	}  //ttttttttttttttttttttttttttttttttttttttttttttt
    		
    		
/*    		else if(isLoan!=null && isLoan.equals("true"))//loan PV generating
    		{
    			PcesthttEjb pcesthttEjb = new PcesthttEjb(getSessionKey("region"));
		    	Pcesthtt pcesthtt = pcesthttEjb.findByEstimationNo(estimateNo,costCenterNo);
				if(pcesthtt==null)
				{
					isPivView = null;
					setErrorMessage("Estimate does not exist.");
					return ERROR;
				}
				else
				{
					BigDecimal estStatus = pcesthtt.getStatus();
					if(!estStatus.toString().equals(EstimateStatus.EST_APPROVED))
					{
						setErrorMessage("Sorry! This estimate has not been approved or already confirmed");
						this.isPivView = null;		
						return ERROR;
					}
					else
					{
						ApplicationEjb applicationEJB = new ApplicationEjb(getSessionKey("region"));
						PivEjb pivDetailEjb = new PivEjb (getSessionKey("region"));
						Application application = applicationEJB.findByApplicationNo(estimateNo);
						hasAppLoan = application.getIsLoanApp();
						if(hasAppLoan!=null && hasAppLoan.equalsIgnoreCase("Y"))
						{
							PivEjb pivEjb = new PivEjb(getSessionKey("region"));
					        boolean canGeneratePIV = false;
					        try
					        {
					        	canGeneratePIV = pivEjb.canGeneratePiv(costCenterNo,estimateNo,"ELN");
					        }
					        catch(Exception e)
					        {e.printStackTrace();}
								
					        if(canGeneratePIV)
					        {
					        
								setApplicantData(application);								
								SpeststdPK speststdPK = new SpeststdPK();
								speststdPK.setDeptId(costCenterNo);
								speststdPK.setEstimateNo(estimateNo);
								SpeststdEjb speststdEJB = new SpeststdEjb(getSessionKey("region"));
								Speststd speststd = speststdEJB.findById(speststdPK);
								
								String msg = setEstimateData(speststd,false);
								if(msg!=null)
								{
									setErrorMessage(msg);
									return ERROR;
								}
								
								if(speststd!=null && speststd.getTotalCost()!=null){
									double totalEstimateCost =  speststd.getTotalCost().doubleValue();									
									if(totalEstimateCost>LoanDetails.LOAN_MAX_LIMIT){
										PivDetail pivDetail = pivDetailEjb.findByReferenceNo(costCenterNo, estimateNo,"EST");
										if(pivDetail==null){
											setErrorMessage("Sorry! First you have to generate the normal PIV for extra amount");
											this.isPivView = null;		
											return ERROR;
										}else if(pivDetail.getStatus()==null || pivDetail.getStatus().length()==0 || !pivDetail.getStatus().equals(util.common.PIVStatus.PAID) ){
											setErrorMessage("Sorry! First you have to confirm the normal PIV for extra amount");
											this.isPivView = null;		
											return ERROR;
										}
									}
								}
								
								
								SimpleDateFormat sdf = new SimpleDateFormat("dd/MM/yyyy");
								date = sdf.format(new Date());
								
								this.isPivView = "true";	
								return SUCCESS;
					        }
					        else
					        {
					        	setErrorMessage("Sorry! Loan PIV already generated for this estimate.");
								this.isPivView = null;		
								return ERROR;
					        }
						}
						else
				        {
				        	setErrorMessage("Sorry! There is no loan, for this estimate.");
							this.isPivView = null;		
							return ERROR;
				        }
					}
				}
    		}*/
    		/*else//normal PIV generating
    		{
		    	PcesthttEjb pcesthttEjb = new PcesthttEjb(getSessionKey("region"));
		    	Pcesthtt pcesthtt = pcesthttEjb.findByEstimationNo(estimateNo,costCenterNo);
				if(pcesthtt==null)
				{
					isPivView = null;
					setErrorMessage("Estimate does not exist.");
					return ERROR;
				}
				else
				{
					BigDecimal estStatus = pcesthtt.getStatus();
					if(!estStatus.toString().equals(EstimateStatus.EST_APPROVED))
					{
						setErrorMessage("Sorry! This estimate has not been approved or already confirmed");
						this.isPivView = null;		
						return ERROR;
					}
					else
					{
						PivEjb pivEjb = new PivEjb(getSessionKey("region"));
				        boolean canGeneratePIV = false;
				        try{
				        	canGeneratePIV = pivEjb.canGeneratePiv(costCenterNo,estimateNo,"EST");
				        }
				        catch(Exception e){e.printStackTrace();}
						
				        if(canGeneratePIV)
				        {
				        	ApplicationEjb applicationEJB = new ApplicationEjb(getSessionKey("region"));
							Application application = applicationEJB.findByApplicationNo(estimateNo);
							hasAppLoan = application.getIsLoanApp();
							
							SpstdesthmtPK spstdesthmtPK = new SpstdesthmtPK();
							spstdesthmtPK.setDeptId(costCenterNo);
							spstdesthmtPK.setStdNo(estimateNo);
							spstdesthmtPK.setApplicationNo(estimateNo);
							SpstdesthmtEjb spstdesthmtEjb = new SpstdesthmtEjb();
							Spstdesthmt spstdesthmt = spstdesthmtEjb.findById(spstdesthmtPK, getSessionKey("region"));
							
							if(hasAppLoan!=null && hasAppLoan.equalsIgnoreCase("Y"))
							{
								SpapplonEjb spapplonEjb = new SpapplonEjb(getSessionKey("region"));
								SpapplonPK spapplonPK = new SpapplonPK();
								spapplonPK.setApplicationNo(estimateNo);
								spapplonPK.setDeptId(costCenterNo);
								
								double loanMax = LoanDetails.LOAN_MAX_LIMIT;
								Spapplon spapplon = spapplonEjb.findById(spapplonPK);
								if(spapplon!=null)
								{
									
									if(spapplon.getIsLoan4Share()!=null && spapplon.getIsLoan4Share().equals("Y"))
									{
										double shareNo = spapplon.getNoOfShares().doubleValue();
									    double sharePrice = spapplon.getSamurdhiSharePrice().doubleValue();
									    loanMax = loanMax-shareNo*sharePrice;
									}
								}
								if(spstdesthmt.getTotalCost().doubleValue()<loanMax)
								{
									setErrorMessage("Sorry! Only loan PIV can be generated for this estimate.");
									this.isPivView = null;		
									return ERROR;
								}
								else
								{
									setApplicantData(application);
									
									String msg = setEstimateData(spstdesthmt,true);
									if(msg!=null)
									{
										setErrorMessage(msg);
										return ERROR;
									}
									SimpleDateFormat sdf = new SimpleDateFormat("dd/MM/yyyy");
									date = sdf.format(new Date());
									
									this.isPivView = "true";	
									return SUCCESS;
								}
							}
							//else
							//{
								//setFormData();
								setApplicantData(application);
								setEstimateData(spstdesthmt,false);
								SimpleDateFormat sdf = new SimpleDateFormat("dd/MM/yyyy");
								date = sdf.format(new Date());
								
								this.isPivView = "true";	
								return SUCCESS;
							//}
							
				        }
				        else
				        {
				        	setErrorMessage("Sorry! PIV already generated for this estimate.");
							this.isPivView = null;		
							return ERROR;
				        }
					}
				}
			//}
*/    	}
    	catch(Exception e)
    	{
    		e.printStackTrace();
    		setErrorMessage("Unexpected Error! Please retry.");
			this.isPivView = null;		
			return ERROR;
    	}
		
    	
	}
	
	
	private String viewJobDetails()
	{
		System.out.println("viewEstimateDetails");
		setLoggedData();
    	setState("view");
    	setPath(viewPath);
    	this.errorMessage = "";
    	setFormData();
    	clearFields();
    	preparedBy = getSession().get("userName").toString();
    	try
    	{
    		if((isPrint!=null && isPrint.equals("true"))||(isModify!=null && isModify.equals("true")))
			{
    			PivDetailEjb pivDetailEjb = new PivDetailEjb(getSessionKey("region"));
    			PivDetail piv = pivDetailEjb.findByReferenceNo(costCenterNo, estimateNo, "JOB");
    			if(piv==null)
    			{
    				setErrorMessage("PIV does not exist for this job.");
					return ERROR;
    			}
    			else
    			{
    				if(piv.getStatus().equals(PIVStatus.NEW))
    				{
    					PcesthmtEjb pcesthmtEjb = new PcesthmtEjb(getSessionKey("region"));
    			    	Pcesthmt pcesthmt = pcesthmtEjb.findByJobNo(estimateNo, costCenterNo);
    			    	String tmpEstNo = pcesthmt.getId().getEstimateNo();
    			    	ApplicationEjb applicationEJB = new ApplicationEjb(getSessionKey("region"));
						Application application = applicationEJB.findByApplicationNo(tmpEstNo);
						setApplicantData(application);
						
						SpeststdPK speststdPK = new SpeststdPK();
						speststdPK.setDeptId(costCenterNo);
						speststdPK.setEstimateNo(tmpEstNo);
						SpeststdEjb speststdEJB = new SpeststdEjb(getSessionKey("region"));
						Speststd speststd = speststdEJB.findById(speststdPK);
						
						SpstdesthmtPK spstdesthmtPK = new SpstdesthmtPK();
						spstdesthmtPK.setDeptId(costCenterNo);
						spstdesthmtPK.setStdNo(tmpEstNo);
						spstdesthmtPK.setApplicationNo(tmpEstNo);
						SpstdesthmtEjb spstdesthmtEjb = new SpstdesthmtEjb(getSessionKey("region"));
						Spstdesthmt spstdesthmt = spstdesthmtEjb.findById(spstdesthmtPK, getSessionKey("region"));
						setEstimateData(spstdesthmt,false);
						setPIVData(piv);
						this.isPivView = "true";
    					return SUCCESS;
    				}
    				else
    				{
    					if(isPrint!=null && isPrint.equals("true"))
    						setErrorMessage("Sorry! You cannot print PIV for this job.");
    					else if(isModify!=null && isModify.equals("true"))
    						setErrorMessage("Sorry! You cannot modify this PIV.");
    					return ERROR;
    				}
    			}
			}
    		else
    		{
		    	PcesthmtEjb pcesthmtEjb = new PcesthmtEjb(getSessionKey("region"));
		    	Pcesthmt pcesthmt = pcesthmtEjb.findByJobNo(estimateNo, costCenterNo);
				if(pcesthmt==null)
				{
					isPivView = null;
					setErrorMessage("Job does not exist.");
					return ERROR;
				}
				else
				{
					Long jobStatus = pcesthmt.getStatus();
					if(!jobStatus.toString().equals(EstimateStatus.JOB_APPROVED))
					{
						setErrorMessage("Sorry! You cannot generate PIV for this job.");
						this.isPivView = null;		
						return ERROR;
					}
					else
					{
						PivEjb pivEjb = new PivEjb(getSessionKey("region"));
				        boolean canGeneratePIV = false;
				        try{
				        	canGeneratePIV = pivEjb.canGeneratePiv(costCenterNo,estimateNo,"JOB");
				        }
				        catch(Exception e){e.printStackTrace();}
						
				        if(canGeneratePIV)
				        {
				        	String tmpEstNo = pcesthmt.getId().getEstimateNo();
					        ApplicationEjb applicationEJB = new ApplicationEjb(getSessionKey("region"));
							Application application = applicationEJB.findByApplicationNo(tmpEstNo);
							setApplicantData(application);
							setJobData(tmpEstNo);
							SimpleDateFormat sdf = new SimpleDateFormat("dd/MM/yyyy");
							date = sdf.format(new Date());
							
							this.isPivView = "true";	
							return SUCCESS;
				        }
				        else
				        {
				        	setErrorMessage("Sorry! PIV already generated for this estimate.");
							this.isPivView = null;		
							return ERROR;
				        }
					}
				}
			}
    	}
    	catch(Exception e)
    	{
    		e.printStackTrace();
    		setErrorMessage("Unexpected Error! Please retry.");
			this.isPivView = null;		
			return ERROR;
    	}
		
    	
	}
	
	public String displayBankBranches()
	{
		BankEjb bankEjb = new BankEjb(getSessionKey("region"));
		bankBranchList = bankEjb.getBranchList(chequeBankCode);
		chequeBranchCode = hdnChequeBranchCode;
		return SUCCESS;
	}
	
	//EJB
	public String save(){
		PivEjb ejb;
		try
		{
			setLoggedData();
			setFormat(new Format());
			ejb=new PivEjb(getSessionKey("region"));
			PivDetail pivDetail=CreatePivDetailModel();
			Calendar cal = Calendar.getInstance();
			String pivNoPrefix = "PIV/"+costCenterNo+"/"+PivPrefixType.getEST_XXX_Type(getSessionKey("smcType"))+"/"+cal.get(Calendar.YEAR)+"/";
			if(isRevise!=null && isRevise.equals("true"))
				pivNoPrefix = "PIV/"+costCenterNo+"/"+PivPrefixType.JOB_NEW_CONN+"/"+cal.get(Calendar.YEAR)+"/";
			/*else if(isTempConn!=null && isTempConn.equals("true"))
				pivNoPrefix = "PIV/"+costCenterNo+"/"+PivPrefixType.EST_TEMP_CONN+"/"+cal.get(Calendar.YEAR)+"/";
			else if(isReConn!=null && isReConn.equals("true"))
				pivNoPrefix = "PIV/"+costCenterNo+"/"+PivPrefixType.EST_CR_JOBS+"/"+cal.get(Calendar.YEAR)+"/";
			else if(isPhaseChange!=null && isPhaseChange.equals("true"))
				pivNoPrefix = "PIV/"+costCenterNo+"/"+PivPrefixType.EST_CR_JOBS+"/"+cal.get(Calendar.YEAR)+"/";
			else if(isConversion!=null && isConversion.equals("true"))
				pivNoPrefix = "PIV/"+costCenterNo+"/"+PivPrefixType.EST_CR_JOBS+"/"+cal.get(Calendar.YEAR)+"/";
			else if(isOtherCR!=null && isOtherCR.equals("true"))
				pivNoPrefix = "PIV/"+costCenterNo+"/"+PivPrefixType.EST_CR_JOBS+"/"+cal.get(Calendar.YEAR)+"/";
			else if(isMeterChange!=null && isMeterChange.equals("true"))
				pivNoPrefix = "PIV/"+costCenterNo+"/"+PivPrefixType.EST_CR_JOBS+"/"+cal.get(Calendar.YEAR)+"/";
			*/
			pivNo = ejb.createPivAutoId(pivDetail, pivNoPrefix);
			pivPrint();
			clearFields();
			this.estimateNo = "";
			this.pivReceiptNo = "";
			setSuccessMessage("PIV Generated Successfully.");
			return SUCCESS;
		} 
		catch (Exception e) 
		{
			e.printStackTrace();
			setErrorMessage("Unexpected Error! Please contact system administrator.");
			return ERROR;
		}
    }
	
	
	public String saveOnly(){
		PivEjb ejb;
		try
		{
			setLoggedData();
			setFormat(new Format());
			ejb=new PivEjb(getSessionKey("region"));
			PivDetail pivDetail=CreatePivDetailModel();
			Calendar cal = Calendar.getInstance();
			String pivNoPrefix = "PIV/"+costCenterNo+"/"+PivPrefixType.getEST_XXX_Type(getSessionKey("smcType"))+"/"+cal.get(Calendar.YEAR)+"/";
			if(isRevise!=null && isRevise.equals("true"))
				pivNoPrefix = "PIV/"+costCenterNo+"/"+PivPrefixType.JOB_NEW_CONN+"/"+cal.get(Calendar.YEAR)+"/";
			/*else if(isTempConn!=null && isTempConn.equals("true"))
				pivNoPrefix = "PIV/"+costCenterNo+"/"+PivPrefixType.EST_TEMP_CONN+"/"+cal.get(Calendar.YEAR)+"/";
			else if(isReConn!=null && isReConn.equals("true"))
				pivNoPrefix = "PIV/"+costCenterNo+"/"+PivPrefixType.EST_CR_JOBS+"/"+cal.get(Calendar.YEAR)+"/";
			else if(isPhaseChange!=null && isPhaseChange.equals("true"))
				pivNoPrefix = "PIV/"+costCenterNo+"/"+PivPrefixType.EST_CR_JOBS+"/"+cal.get(Calendar.YEAR)+"/";
			else if(isConversion!=null && isConversion.equals("true"))
				pivNoPrefix = "PIV/"+costCenterNo+"/"+PivPrefixType.EST_CR_JOBS+"/"+cal.get(Calendar.YEAR)+"/";
			else if(isOtherCR!=null && isOtherCR.equals("true"))
				pivNoPrefix = "PIV/"+costCenterNo+"/"+PivPrefixType.EST_CR_JOBS+"/"+cal.get(Calendar.YEAR)+"/";
			else if(isMeterChange!=null && isMeterChange.equals("true"))
				pivNoPrefix = "PIV/"+costCenterNo+"/"+PivPrefixType.EST_CR_JOBS+"/"+cal.get(Calendar.YEAR)+"/";
			*/
			pivNo = ejb.createPivAutoId(pivDetail, pivNoPrefix);
			
			//clearFields();
			//this.estimateNo = "";
		//	this.pivReceiptNo = "";
			setSuccessMessage("PIV Generated Successfully.");
			isGenerateSuccess = "true";
			return SUCCESS;
		} 
		catch (Exception e) 
		{
			e.printStackTrace();
			setErrorMessage("Unexpected Error! Please contact system administrator.");
			return ERROR;
		}
    }
	
	//set estimate details to the form fields
	private String setEstimateData(Spstdesthmt spstdesthmt, boolean haveLoan)
	{
		String msg = null;
		nf.setGroupingUsed(true);
		nf.setMaximumFractionDigits(2);
		nf.setMinimumFractionDigits(2);
		double amountInWordsDbl=0;
		double fixedCost = 0;
		
		double secDeposit = 0;
		if(spstdesthmt.getSecDeposit()!=null)
			secDeposit = spstdesthmt.getSecDeposit().doubleValue();
		//double totalCost = 0;
		
		double variableCost = 0;
		
		//spstdesthmt.getCebCost()
		//spstdesthmt.getRebateCost()
			//spstdesthmt.getConCost()
		double total =0;
		if(spstdesthmt.getTotalCost()!=null)
			total = spstdesthmt.getTotalCost().doubleValue();
		//double total =  speststd.getTotalCost().doubleValue();//   convCost+fixedCost+variableCost+othCost+secDeposit+addlDeposit+labourCost+transportCost+overheadCost+dmgCost;
/*		if(isNewService!=null && isNewService.equals("true"))
		{
			if(isLoan!=null && isLoan.equals("true"))
			{
				SpapplonEjb spapplonEjb = new SpapplonEjb(getSessionKey("region"));
				SpapplonPK spapplonPK = new SpapplonPK();
				spapplonPK.setApplicationNo(estimateNo);
				spapplonPK.setDeptId(costCenterNo);
				
				double loanMax = LoanDetails.LOAN_MAX_LIMIT;
				Spapplon spapplon = spapplonEjb.findById(spapplonPK);
				if(spapplon!=null)
				{
					
					if(spapplon.getIsLoan4Share()!=null && spapplon.getIsLoan4Share().equals("Y"))
					{
						double shareNo = spapplon.getNoOfShares().doubleValue();
					    double sharePrice = spapplon.getSamurdhiSharePrice().doubleValue();
					    loanMax = loanMax-shareNo*sharePrice;
					}
				}
				else
				{
					msg = "Please fill the loan details to proceed.";
					return msg;
				}
				double loanAmount = total;
				if(total>loanMax)
					loanAmount = loanMax;
				double serConn = loanAmount-secDeposit-addlDeposit;
				miscellaneousIncome = "0.00";
				electricityDebtors = "0.00";
				securityDeposit = nf.format(secDeposit+addlDeposit);
				serConnOrElecSch = nf.format(serConn);
				tenderDeposit = "0.00";
				miscellaneousDeposit = "0.00";
				cashInTransit = "0.00";
				forDishonouredCheque = "0.00";
				vat = "0.00";
				amountInWordsDbl= loanAmount;
				grandTotal = nf.format(loanAmount);
				subTotal = grandTotal;
				
			}
			else
			{
				if(haveLoan)
				{
					SpapplonEjb spapplonEjb = new SpapplonEjb(getSessionKey("region"));
					SpapplonPK spapplonPK = new SpapplonPK();
					spapplonPK.setApplicationNo(estimateNo);
					spapplonPK.setDeptId(costCenterNo);
					double loanMax = LoanDetails.LOAN_MAX_LIMIT;
					Spapplon spapplon = spapplonEjb.findById(spapplonPK);
					if(spapplon!=null)
					{
						if(spapplon.getIsLoan4Share()!=null && spapplon.getIsLoan4Share().equals("Y"))
						{
							double shareNo = spapplon.getNoOfShares().doubleValue();
						    double sharePrice = spapplon.getSamurdhiSharePrice().doubleValue();
						    loanMax = loanMax-shareNo*sharePrice;
						}
					}
					else
					{
						msg = "Please fill the loan details to proceed.";
						return msg;
					}
					total = total - loanMax;
					miscellaneousIncome = "0.00";
					securityDeposit = "0.00";
					serConnOrElecSch = nf.format(total);
				}
				else
				{
					double serConn = fixedCost+variableCost+capitalCost+convCost;
					miscellaneousIncome = nf.format(othCost+othLabCost);
					securityDeposit = nf.format(secDeposit+addlDeposit);
					serConnOrElecSch = nf.format(serConn);
				}
				
				electricityDebtors = "0.00";
				tenderDeposit = "0.00";
				miscellaneousDeposit = "0.00";
				cashInTransit = "0.00";
				forDishonouredCheque = "0.00";
				vat = "0.00";
				grandTotal = nf.format(total);
				amountInWordsDbl = total;
				subTotal = grandTotal;
			}
		}	
		else if(isTempConn!=null && isTempConn.equals("true"))
		{
			double misc = fixedCost+variableCost+othCost+labourCost+transportCost+overheadCost+dmgCost;
			miscellaneousIncome = nf.format(misc);
			electricityDebtors = "0.00";
			securityDeposit = nf.format(secDeposit+addlDeposit);
			serConnOrElecSch = "0.00";
			tenderDeposit = "0.00";
			miscellaneousDeposit = "0.00";
			cashInTransit = "0.00";
			forDishonouredCheque = "0.00";
			vat = "0.00";
			grandTotal = nf.format(total);
			amountInWordsDbl = total;
			subTotal = grandTotal;
		}
		else if(isReConn!=null && isReConn.equals("true"))
		{
			miscellaneousIncome = "0.00";
			electricityDebtors = "0.00";
			securityDeposit = "0.00";
			serConnOrElecSch = "0.00";
			tenderDeposit = "0.00";
			miscellaneousDeposit = nf.format(total);
			cashInTransit = "0.00";
			forDishonouredCheque = "0.00";
			vat = "0.00";
			grandTotal = nf.format(total);
			amountInWordsDbl = total;
			subTotal = grandTotal;
		}
		else if(isPhaseChange!=null && isPhaseChange.equals("true"))
		{
			double serConn = total-secDeposit;
			miscellaneousIncome = "0.00";
			electricityDebtors = "0.00";
			securityDeposit = nf.format(secDeposit);
			serConnOrElecSch = nf.format(serConn);
			tenderDeposit = "0.00";
			miscellaneousDeposit = "0.00";
			cashInTransit = "0.00";
			forDishonouredCheque = "0.00";
			vat = "0.00";
			grandTotal = nf.format(total);
			amountInWordsDbl = total;
			subTotal = grandTotal;
		}
		else if(isConversion!=null && isConversion.equals("true"))
		{
			double serConn = total-secDeposit;
			miscellaneousIncome = "0.00";
			electricityDebtors = "0.00";
			securityDeposit = nf.format(secDeposit);
			serConnOrElecSch = nf.format(serConn);
			tenderDeposit = "0.00";
			miscellaneousDeposit = "0.00";
			cashInTransit = "0.00";
			forDishonouredCheque = "0.00";
			vat = "0.00";
			grandTotal = nf.format(total);
			amountInWordsDbl = total;
			subTotal = grandTotal;
		}
		else if(isMeterChange!=null && isMeterChange.equals("true"))
		{
			miscellaneousIncome = nf.format(fixedCost);
			electricityDebtors = "0.00";
			securityDeposit = "0.00";
			serConnOrElecSch = "0.00";
			tenderDeposit = "0.00";
			miscellaneousDeposit = "0.00";
			cashInTransit = "0.00";
			forDishonouredCheque = "0.00";
			vat = "0.00";
			grandTotal = nf.format(fixedCost);
			amountInWordsDbl = fixedCost;
			subTotal = grandTotal;
		}
		else if(isOtherCR!=null && isOtherCR.equals("true"))
		{
			miscellaneousIncome = nf.format(total);
			electricityDebtors = "0.00";
			securityDeposit = "0.00";
			serConnOrElecSch = "0.00";
			tenderDeposit = "0.00";
			miscellaneousDeposit = "0.00";
			cashInTransit = "0.00";
			forDishonouredCheque = "0.00";
			vat = "0.00";
			grandTotal = nf.format(total);
			amountInWordsDbl = total;
			subTotal = grandTotal;
		}*/
		amountInWords = CurrencyToWords.convert(amountInWordsDbl);
		BigDecimal subTotalBD = new BigDecimal(Double.toString(total));
		BigDecimal grandTotalBD = new BigDecimal(Double.toString(total));
		return msg;
	}
	
	//set estimate details to the form fields
	private void setJobData(String tmpEstNo)
	{
		nf.setGroupingUsed(true);
		nf.setMaximumFractionDigits(2);
		nf.setMinimumFractionDigits(2);
		
		SpeststdPK speststdPK = new SpeststdPK();
		speststdPK.setDeptId(costCenterNo);
		speststdPK.setEstimateNo(tmpEstNo);
		SpeststdEjb speststdEJB = new SpeststdEjb(getSessionKey("region"));
		Speststd speststd = speststdEJB.findById(speststdPK);
		
		BigDecimal miscellaneousIncomeBD = speststd.getOtherCost();
		BigDecimal securityDepositBD = speststd.getSecurityDeposit();
		
		//double convCost = speststd.getConversionCost().doubleValue();
		double fixedCost = speststd.getFixedCost().doubleValue();
		double secDeposit = securityDepositBD.doubleValue();
		double variableCost = speststd.getVariableCost().doubleValue();
		double othCost = miscellaneousIncomeBD.doubleValue();
		double serConn = fixedCost+variableCost;
		double total = fixedCost+variableCost+othCost+secDeposit;
		BigDecimal serConnOrElecSchBD = new BigDecimal(Double.toString(serConn));
		
		PivDetailEjb pivDetailEjb = new PivDetailEjb(getSessionKey("region"));
		PivDetail pivDetail = pivDetailEjb.findByReferenceNo(costCenterNo, tmpEstNo,"EST");
		
		double piv2Total = pivDetail.getGrandTotal().doubleValue();
		
		double piv3Total = 0;
			if(piv2Total<total)
				piv3Total = total - piv2Total;
			
		miscellaneousIncome = "0.00";
		electricityDebtors = "0.00";
		securityDeposit = "0.00";
		serConnOrElecSch = nf.format(piv3Total);
		tenderDeposit = "0.00";
		miscellaneousDeposit = "0.00";
		cashInTransit = "0.00";
		forDishonouredCheque = "0.00";
		vat = "0.00";
		grandTotal = nf.format(piv3Total);
		subTotal = grandTotal;
		
		amountInWords = CurrencyToWords.convert(piv3Total);
		BigDecimal subTotalBD = new BigDecimal(Double.toString(piv3Total));
		BigDecimal grandTotalBD = new BigDecimal(Double.toString(piv3Total));
	}
	
	private void pivPrint()
	{
		PayingInVoucher piv = new PayingInVoucher();
		
		piv.setAmountInWords(getAmountInWords());
		
		
		
		if(referenceType.equals("EST"))
		{
			BankEjb bankEjb = new BankEjb(getSessionKey("region"));
			Bank bank = bankEjb.findBankCode(payingBankCode);
			piv.setBankName(bank.getBankName());
			
			BankBranchPK bankBranchPK = new BankBranchPK();
			bankBranchPK.setBankCode(payingBankCode);
			bankBranchPK.setBranchCode(payingBranchCode);
			
			BankBranch bankBranch = bankEjb.findBankBranchCode(bankBranchPK);
			piv.setBankBranchName(bankBranch.getBranchName());
			
			
		}
		else
		{
			piv.setBankName(payingBankCode);
			piv.setBankBranchName(payingBranchCode);
		}
		piv.setBankCode(payingBankCode);
		piv.setBranchCode(payingBranchCode);
		piv.setCashInTransit(new BigDecimal(0));
		piv.setCEBBranch(getCebBranch());
		piv.setCetifiedBy("");
		piv.setCity(city);
		piv.setCostCenterNo(costCenterNo);
		piv.setDate(new Date().toString());
		piv.setDepositorIDNumber(idNo);
		piv.setDepositorName(fullName);
		piv.setEletricityDebtors(new BigDecimal(0));
		piv.setGrandTotal(new BigDecimal(grandTotal.replace(",", "")));
		piv.setJobDescription(description);
		piv.setMiscellaneousDeposits(new BigDecimal(0));
		piv.setMiscellaneousIncome(new BigDecimal(miscellaneousIncome.replace(",", "")));
		piv.setPostalCode(postalCode);
		piv.setReferenceNo(estimateNo);
		piv.setSecurityDeposit(new BigDecimal(securityDeposit.replace(",", "")));
		piv.setServiceConnectionOrElectricitySchemes(new BigDecimal(serConnOrElecSch.replace(",", "")));
		piv.setStreetAddress(street);
		piv.setSuburb(subUrb);
		piv.setSubTotal(new BigDecimal(subTotal.replace(",", "")));
		piv.setTenderDeposits(new BigDecimal(0));
		piv.setVat(new BigDecimal(0));
		piv.setForDishonouredCheques(new BigDecimal(0));
		
		piv.setChequeNo(chequeNo);
		if(loanAmount!=null && loanAmount.trim().length()>0)
			piv.setLoanAmount(new BigDecimal(loanAmount));
		piv.setLoanReference(loanReference);
		piv.setPIVNumber(pivNo);
		PrintPiv pe = new  PrintPiv(piv);
		pe.Print(false);

	}
	
	
	//EJB
	public String confirmPiv(){
		System.out.println("confirmPiv");
		try
		{
			//SimpleDateFormat sdf = new SimpleDateFormat("dd/MM/yyyy");
			SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");			
			setLoggedData();
			//setFormData();
			setFormat(new Format());
			PivEjb pivEjb = new PivEjb(getSessionKey("region"));
    		PivDetailPK pk = new PivDetailPK();
    		pk.setDeptId(costCenterNo);
    		pk.setPivNo(pivNo);
    		PivDetail piv = pivEjb.findById(pk);
    		
    		piv.setPivReceiptNo(pivReceiptNo.toUpperCase());
			piv.setPaymentMode(paymentMode);
			if(paidDate!=null)
				piv.setPaidDate(paidDate);
			piv.setChequeBankCode(chequeBankCode);
			piv.setChequeBranchCode(chequeBranchCode);
			if(chequeDate!=null)
				piv.setChequeDate(chequeDate);
			if(getChequeNo()!=null && getChequeNo().trim().length()>0)
				piv.setChequeNo(new BigDecimal(getChequeNo().trim()));
			
			Format f = new Format();
			piv.setConfirmedBy(getLoggedInUserId());
			piv.setConfirmedDate(new Date());
			piv.setConfirmedTime(f.FormatTime());
			piv.setStatus(PIVStatus.PAID);
			
			if(isRevise!=null && isRevise.equals("true"))
			{
				pivEjb.confirmJobPivDetail(piv);
			 }
			else
			{
				if(hasAppLoan!=null && hasAppLoan.equalsIgnoreCase("Y"))
				{
					if(piv.getReferenceType().equals("EST"))
					{
						pivEjb.confirmEstPivDetail(piv,false);
					}
					else if(piv.getReferenceType().equals("ELN"))
					{
						PivDetail otherPiv = pivEjb.findByReferenceNo(costCenterNo,piv.getReferenceNo(),"EST");
						if(otherPiv==null || otherPiv.getStatus().equals(PIVStatus.PAID))
						{
							piv.setStatus(PIVStatus.CONFIRMED);
							pivEjb.confirmEstPivDetail(piv,true);
						}
						else
						{
							setErrorMessage("Please confirm other PIV related to this PIV first.");
							return ERROR;
						}
					}
				}
				else
				{
					//pivEjb.confirmEstPivDetail(piv);
					pivEjb.confirmEstPivDetail(piv,true);
				}
			}
			
			
			clearFields();
			this.pivReceiptNo = "";
			setSuccessMessage("PIV Confirmed Successfully.");
			return SUCCESS;
		} 
		catch (Exception e) 
		{
			e.printStackTrace();
			setErrorMessage("Unexpected Error! Please contact system administrator.");
			return ERROR;
		}
    }
	
	public String cancelPiv(){
		System.out.println("cancelPiv");
		try
		{
			SimpleDateFormat sdf = new SimpleDateFormat("dd/MM/yyyy");
						
			setLoggedData();
			//setFormData();
			bankBranchList = new ArrayList();
			bankList = new ArrayList();
			setFormat(new Format());
			PivEjb pivEjb = new PivEjb(getSessionKey("region"));
    		PivDetailPK pk = new PivDetailPK();
    		pk.setDeptId(costCenterNo);
    		pk.setPivNo(pivNo);
    		PivDetail piv = pivEjb.findById(pk);
    		
			Format f = new Format();
			piv.setCancelUser(getLoggedInUserId());
			piv.setCancelDate(new Date());
			piv.setCancelTime(f.FormatTime());
			piv.setStatus(PIVStatus.DESTROYED);
			
			pivEjb.updatePivDetail(piv);
			
			clearFields();
			this.pivReceiptNo = "";
			setSuccessMessage("PIV Cancelled Successfully.");
			return SUCCESS;
		} 
		catch (Exception e) 
		{
			e.printStackTrace();
			setErrorMessage("Unexpected Error! Please contact system administrator.");
			return ERROR;
		}
    }
	
	
	private void setApplicantData(Application application)
	{
		ApplicantEjb applicantEJB = new ApplicantEjb(getSessionKey("region"));
		Applicant applicant = applicantEJB.findById(application.getIdNo());
		fullName = applicant.getFirstName()+" "+applicant.getLastName();
		/*street = applicant.getStreetAddress();
		subUrb = applicant.getSuburb();
		city = applicant.getCity();*/
		
		idNo = applicant.getIdNo();
		/*if(applicant.getPostalCode()!=null)
			postalCode = applicant.getPostalCode().toString();
		*/
		WiringLandDetailEjb wlEJB = new WiringLandDetailEjb(getSessionKey("region"));
		WiringLandDetailPK wlPK = new WiringLandDetailPK();
		wlPK.setApplicationId(application.getId().getApplicationId());
		wlPK.setDeptId(costCenterNo);
		WiringLandDetail wld = wlEJB.findByAppId(wlPK);
		
		street = wld.getServiceStreetAddress();
		subUrb = wld.getServiceSuburb();
		city = wld.getServiceCity();
		if(wld.getServicePostalCode()!=null)
			postalCode = wld.getServicePostalCode().toString();
		
		fullAddress = street+", "+subUrb+", "+city;
	}
	
	
	//set estimate details to the form fields
	private void setPIVData(PivDetail piv)
	{
		nf.setGroupingUsed(true);
		nf.setMaximumFractionDigits(2);
		nf.setMinimumFractionDigits(2);
		
		SimpleDateFormat sdf = new SimpleDateFormat("dd/MM/yyyy");
		
		
		
		BigDecimal miscellaneousIncomeBD = piv.getMiscellaneousIncome();
		BigDecimal securityDepositBD = piv.getSecurityDeposit();
		BigDecimal serConnOrElecSchBD = piv.getSerConnOrElecSch();
		BigDecimal grandTotalBD = piv.getGrandTotal();
		
		pivReceiptNo = piv.getPivReceiptNo();
		miscellaneousIncome = nf.format(miscellaneousIncomeBD);
		electricityDebtors = nf.format(piv.getElectricityDebtors());
		securityDeposit = nf.format(securityDepositBD);
		serConnOrElecSch = nf.format(serConnOrElecSchBD);
		tenderDeposit = nf.format(piv.getTenderDeposit());
		miscellaneousDeposit = nf.format(piv.getMiscellaneousDeposit());
		cashInTransit = nf.format(piv.getCashInTransit());
		forDishonouredCheque = nf.format(piv.getForDishonoredCheque());
		vat = nf.format(piv.getVat());
		referenceType = piv.getReferenceType();
		grandTotal = nf.format(grandTotalBD);
		subTotal = grandTotal;
		
		amountInWords = CurrencyToWords.convert(grandTotalBD.doubleValue());
		BigDecimal subTotalBD = piv.getSubTotal();
		
		if(piv.getPaidDate()!=null)
			paidDate = piv.getPaidDate();
		if(piv.getBankCode()!=null && piv.getBankCode().trim().length()>0)
			payingBankCode = piv.getBankCode();
		else
			payingBankCode = piv.getPosCenter();
		
		if(piv.getBranchCode()!=null && piv.getBranchCode().trim().length()>0)
			payingBranchCode = piv.getBranchCode();
		else
			payingBranchCode = piv.getPosA();
		
		piv.getBankPaidDate();
		chequeBankCode = piv.getChequeBankCode();
		chequeBranchCode = piv.getChequeBranchCode();
		hdnChequeBranchCode = piv.getChequeBranchCode();
		if(piv.getChequeDate()!=null)
			chequeDate = piv.getChequeDate();
		if(piv.getChequeNo()!=null)
			chequeNo = piv.getChequeNo().toString();
		if(piv.getLoanAmount()!=null)
			loanAmount = nf.format(piv.getLoanAmount());
		loanReference = piv.getLoanReference();
		if(piv.getPaymentMode()==null || piv.getPaymentMode().length()==0)
			paymentMode = "C";
		else
			paymentMode = piv.getPaymentMode();
		date = sdf.format(piv.getPivDate());
		description = piv.getDescription();
		statusDesc = PIVStatus.getPIVStatusDesc(piv.getStatus());
		pivNo = piv.getId().getPivNo();
		
		preparedBy = piv.getPreparedBy();
		preparedTime = sdf.format(piv.getAddDate())+" "+piv.getAddTime();
		if(piv.getConfirmedDate()!=null)
		{
			confirmedBy = piv.getConfirmedBy();
			confirmedTime = sdf.format(piv.getConfirmedDate())+" "+piv.getConfirmedTime();
		}
		if(referenceType.equals("EST"))
		{
			BankEjb bankEjb = new BankEjb(getSessionKey("region"));
			Bank bank = bankEjb.findBankCode(payingBankCode);
			payingBankName = bank.getBankName();
			
			BankBranchPK bankBranchPK = new BankBranchPK();
			bankBranchPK.setBankCode(payingBankCode);
			bankBranchPK.setBranchCode(payingBranchCode);
			
			BankBranch bankBranch = bankEjb.findBankBranchCode(bankBranchPK);
			payingBranchName = bankBranch.getBranchName();
			
			
		}
		else
		{
			payingBankName = payingBankCode;
			payingBranchName = payingBranchCode;
		}
	}
	
	public String close(){
    	setLoggedData();
    	log.info("testing one two three");
    	return "close";
    }
    
   
    private PivDetail CreatePivDetailModel(){
    	log.info("start CreatePivDetailModel ");
    	Calendar calendar =Calendar.getInstance();
    	
    	PivDetailPK pivDetailPK =new PivDetailPK();
    	//pivDetailPK.setPivNo(getPivNo());
    	pivDetailPK.setDeptId(getCostCenterNo());
    	    	
		PivDetail pivDetail=new PivDetail();
    	pivDetail.setId(pivDetailPK);
    	pivDetail.setPivReceiptNo(pivReceiptNo.toUpperCase());
		
		pivDetail.setReferenceNo(estimateNo);
		
		pivDetail.setIdNo(getIdNo());
		pivDetail.setPivDate(calendar.getTime());
		pivDetail.setPivAmount(new BigDecimal(grandTotal.replace(",", "")));
		pivDetail.setCebBranchName(getCebBranch());
		
		if(isLoan!=null && isLoan.equals("true"))
		{
			pivDetail.setPosCenter(getPayingBankCode()); 
			pivDetail.setPosA(getPayingBranchCode());
		}
		else
		{
			pivDetail.setBankCode(getPayingBankCode()); 
			pivDetail.setBranchCode(getPayingBranchCode());
		}
		
		pivDetail.setPaymentMode(getPaymentMode());
		pivDetail.setChequeBankCode(getChequeBankCode());
		pivDetail.setChequeBranchCode(getChequeBankCode());
		if(getChequeNo()!=null && getChequeNo().trim().length()>0)
			pivDetail.setChequeNo(new BigDecimal(getChequeNo().trim()));
		pivDetail.setDescription(description);
		
		//costs
		//pivDetail.setMiscellaneousIncome(new BigDecimal(miscellaneousIncome.replace(",", "")));
		//pivDetail.setElectricityDebtors(new BigDecimal(0)); 
		pivDetail.setSecurityDeposit(new BigDecimal(securityDeposit.replace(",", "")));
		//pivDetail.setSerConnOrElecSch(new BigDecimal(serConnOrElecSch.replace(",", "")));
		//pivDetail.setTenderDeposit(new BigDecimal(0));
		//pivDetail.setMiscellaneousDeposit(new BigDecimal(0));
		//pivDetail.setCashInTransit(new BigDecimal(0));
		//pivDetail.setForDishonoredCheque(new BigDecimal(0));
		pivDetail.setSubTotal(new BigDecimal(subTotal.replace(",", "")));
		pivDetail.setVat(new BigDecimal(0));
		pivDetail.setGrandTotal(new BigDecimal(grandTotal.replace(",", "")));
		
		if(getLoanReference()!=null && getLoanReference().trim().length()>0)
			pivDetail.setLoanReference(getLoanReference().trim());
		if(getLoanAmount()!=null && getLoanAmount().trim().length()>0)
			pivDetail.setLoanAmount(new BigDecimal(getLoanAmount().trim()));
		
		Format f = new Format();
		preparedBy = getLoggedInUserId();
		SimpleDateFormat sdf = new SimpleDateFormat("dd/MM/yyyy");
		preparedTime = sdf.format(calendar.getTime())+" "+f.FormatTime();
		pivDetail.setPreparedBy(preparedBy);
		pivDetail.setAddUser(getLoggedInUserId());
		pivDetail.setAddDate(calendar.getTime());
		pivDetail.setAddTime(f.FormatTime());
		pivDetail.setStatus("N");
		
		
		
		if(isRevise!=null && isRevise.equals("true"))//job revise
		{
			PivDetailEjb pivDetailEjb=new PivDetailEjb(getSessionKey("region"));
	    	int maxNo = pivDetailEjb.getMaxPivSeqNo(costCenterNo, estimateNo, "JOB");
	    	maxNo = maxNo+1;
			pivDetail.setPivSeqNo(new BigDecimal(maxNo));
			pivDetail.setReferenceType("JOB");
		}
		else
		{
			pivDetail.setPivSeqNo(new BigDecimal("1"));
			if(isLoan!=null && isLoan.equals("true"))
				pivDetail.setReferenceType("ELN");
			else
				pivDetail.setReferenceType("EST");
		}
		
		return pivDetail;
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
	
	public String getPivReceiptNo() {
		return pivReceiptNo;
	}

	public void setPivReceiptNo(String pivReceiptNo) {
		this.pivReceiptNo = pivReceiptNo;
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
		if(isRevise!=null && isRevise.equals("true"))
			path = "Job>";
		if(isGenerate!=null && isGenerate.equals("true"))
			path = path+"Generate PIV";
		else if(isConfirm!=null && isConfirm.equals("true"))
			path = path+"Confirm PIV";
		else if(isCancel!=null && isCancel.equals("true"))
			path = path+"Cancel PIV";
		else if(isPrint!=null && isPrint.equals("true"))
			path = path+"Print PIV";
		else if(isModify!=null && isModify.equals("true"))
			path = path+"Modify PIV";
		else
			path = path+"View PIV";
		this.path = path;
	}
	
	public String getConfirmedBy() {
		return confirmedBy;
	}

	public void setConfirmedBy(String confirmedBy) {
		this.confirmedBy = confirmedBy;
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



    @Override
	public void setParameters(Map<String, String[]> parameters) {
		this.parameters=parameters;
	}

	public String getEstimateNo() {
		return estimateNo;
	}

	public void setEstimateNo(String estimateNo) {
		
		this.estimateNo = estimateNo;
	}

	public String getCity() {
		return city;
	}

	public void setCity(String city) {
		this.city = city;
	}

	public String getSubUrb() {
		return subUrb;
	}

	public void setSubUrb(String subUrb) {
		this.subUrb = subUrb;
	}

	public String getPostalCode() {
		return postalCode;
	}

	public void setPostalCode(String postalCode) {
		this.postalCode = postalCode;
	}

	public String getStreet() {
		return street;
	}

	public void setStreet(String street) {
		this.street = street;
	}
/*
	public BigDecimal getMiscellaneousIncomeBD() {
		return miscellaneousIncomeBD;
	}

	public void setMiscellaneousIncomeBD(BigDecimal miscellaneousIncomeBD) {
		this.miscellaneousIncomeBD = miscellaneousIncomeBD;
	}

	public BigDecimal getSecurityDepositBD() {
		return securityDepositBD;
	}

	public void setSecurityDepositBD(BigDecimal securityDepositBD) {
		this.securityDepositBD = securityDepositBD;
	}

	public BigDecimal getSerConnOrElecSchBD() {
		return serConnOrElecSchBD;
	}

	public void setSerConnOrElecSchBD(BigDecimal serConnOrElecSchBD) {
		this.serConnOrElecSchBD = serConnOrElecSchBD;
	}

	public BigDecimal getSubTotalBD() {
		return subTotalBD;
	}

	public void setSubTotalBD(BigDecimal subTotalBD) {
		this.subTotalBD = subTotalBD;
	}

	public BigDecimal getGrandTotalBD() {
		return grandTotalBD;
	}

	public void setGrandTotalBD(BigDecimal grandTotalBD) {
		this.grandTotalBD = grandTotalBD;
	}
*/




	public Date getChequeDate() {
		return chequeDate;
	}


	public void setChequeDate(Date chequeDate) {
		this.chequeDate = chequeDate;
	}

	

	public void setPaidDate(Date paidDate) {
		this.paidDate = paidDate;
	}

	public String getIsConfirm() {
		return isConfirm;
	}

	public void setIsConfirm(String isConfirm) {
		this.isConfirm = isConfirm;
	}

	public String getIsCancel() {
		return isCancel;
	}

	public void setIsCancel(String isCancel) {
		this.isCancel = isCancel;
	}

	public String getIsPivView() {
		return isPivView;
	}

	public void setIsPivView(String isPivView) {
		this.isPivView = isPivView;
	}

	public String getIsGenerate() {
		return isGenerate;
	}

	public void setIsGenerate(String isGenerate) {
		this.isGenerate = isGenerate;
	}

	public String getStatusDesc() {
		return statusDesc;
	}

	public void setStatusDesc(String statusDesc) {
		this.statusDesc = statusDesc;
	}

	public List getBankList() {
		return bankList;
	}

	public void setBankList(List bankList) {
		this.bankList = bankList;
	}

	public List getBankBranchList() {
		return bankBranchList;
	}

	public void setBankBranchList(List bankBranchList) {
		this.bankBranchList = bankBranchList;
	}

	public String getHdnChequeBranchCode() {
		return hdnChequeBranchCode;
	}

	public void setHdnChequeBranchCode(String hdnChequeBranchCode) {
		this.hdnChequeBranchCode = hdnChequeBranchCode;
	}

	public String getIsRevise() {
		return isRevise;
	}

	public void setIsRevise(String isRevise) {
		this.isRevise = isRevise;
	}


	public String getIsPrint() {
		return isPrint;
	}


	public void setIsPrint(String isPrint) {
		this.isPrint = isPrint;
	}


	public String getIsModify() {
		return isModify;
	}


	public void setIsModify(String isModify) {
		this.isModify = isModify;
	}


	public String getPreparedTime() {
		return preparedTime;
	}


	public void setPreparedTime(String preparedTime) {
		this.preparedTime = preparedTime;
	}

	public String getIsNewService() {
		return isNewService;
	}


	public void setIsNewService(String isNewService) {
		this.isNewService = isNewService;
	}


	public String getIsTempConn() {
		return isTempConn;
	}


	public void setIsTempConn(String isTempConn) {
		this.isTempConn = isTempConn;
	}


	public String getIsReConn() {
		return isReConn;
	}


	public void setIsReConn(String isReConn) {
		this.isReConn = isReConn;
	}


	public String getIsPhaseChange() {
		return isPhaseChange;
	}


	public void setIsPhaseChange(String isPhaseChange) {
		this.isPhaseChange = isPhaseChange;
	}


	public String getIsConversion() {
		return isConversion;
	}


	public void setIsConversion(String isConversion) {
		this.isConversion = isConversion;
	}


	public String getIsCostRec() {
		return isCostRec;
	}


	public void setIsCostRec(String isCostRec) {
		this.isCostRec = isCostRec;
	}


	public String getIsLoan() {
		return isLoan;
	}


	public void setIsLoan(String isLoan) {
		this.isLoan = isLoan;
	}


	public String getHasAppLoan() {
		return hasAppLoan;
	}


	public void setHasAppLoan(String hasAppLoan) {
		this.hasAppLoan = hasAppLoan;
	}


	public String getReferenceType() {
		return referenceType;
	}


	public void setReferenceType(String referenceType) {
		this.referenceType = referenceType;
	}


	public String getSuccessMessage() {
		return successMessage;
	}


	public void setSuccessMessage(String successMessage) {
		this.successMessage = successMessage;
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


	public String getIsGenerateSuccess() {
		return isGenerateSuccess;
	}


	public void setIsGenerateSuccess(String isGenerateSuccess) {
		this.isGenerateSuccess = isGenerateSuccess;
	}


	public String getIsMeterChange() {
		return isMeterChange;
	}


	public void setIsMeterChange(String isMeterChange) {
		this.isMeterChange = isMeterChange;
	}


	public String getIsOtherCR() {
		return isOtherCR;
	}


	public void setIsOtherCR(String isOtherCR) {
		this.isOtherCR = isOtherCR;
	}


	public String getPivType() {
		return pivType;
	}


	public void setPivType(String pivType) {
		this.pivType = pivType;
	}


	public String getRegion() {
		return region;
	}


	public void setRegion(String region) {
		this.region = region;
	}
    
    
    
    

}
