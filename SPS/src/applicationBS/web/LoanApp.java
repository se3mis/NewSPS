package applicationBS.web;

import java.math.BigDecimal;
import java.util.Date;
import java.util.List;
import java.util.Map;
import util.common.LoanDetails;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.apache.struts2.interceptor.ParameterAware;
import org.apache.struts2.interceptor.SessionAware;

import piv.model.PivDetail;
import piv.service.PivDetailEjb;
import piv.service.PivEjb;
import util.common.Format;
import application.model.Spapplon;
import application.model.SpapplonPK;
import application.service.ApplicationEjb;
import application.service.SpapplonEjb;
import application.model.Application;

import com.opensymphony.xwork2.ActionSupport;

import estimate.service.SpestedyEjb;


public class LoanApp extends ActionSupport implements SessionAware, ParameterAware{

	private static final long serialVersionUID = 1L;
	private Map<String, Object> session;
	private Map <String, String[]> parameters;
	private String path;
	private String loggedInUserId;
	private String costCenterNo;
	private String csc;	
	private String interestrate;
	private String nofInstallments;	
	private String applicationNo;
	private String idNo;
	private String loanAmount;
	private String samurdhiId;
	private String sharePrice;
	private String noOfShares;
	private String installment;
	private String statusMsg;
	private String statusMsgErr;
	private String hid_MaxLoanAmt;
	private String hid_MaxSamurdhiLoanAmt;
	private String hid_isFound;
	private String isApprovedBM;
	private String isApprovedDS;
	private String isApprovedSDO;
	private String totInterest;
	private String approvedBy;
	private String memberOfSamurdhi;
	private String isApplyForShareLoan;
	private String hid_NoShares;
	private String hid_SharePrice;
	private String hid_isNew;	

	public String getHid_isNew() {
		return hid_isNew;
	}
	public void setHid_isNew(String hid_isNew) {
		this.hid_isNew = hid_isNew;
	}
	public String getHid_NoShares() {
		return hid_NoShares;
	}
	public void setHid_NoShares(String hid_NoShares) {
		this.hid_NoShares = hid_NoShares;
	}
	public String getHid_SharePrice() {
		return hid_SharePrice;
	}
	public void setHid_SharePrice(String hid_SharePrice) {
		this.hid_SharePrice = hid_SharePrice;
	}
	public String getIsApplyForShareLoan() {
		return isApplyForShareLoan;
	}
	public void setIsApplyForShareLoan(String isApplyForShareLoan) {
		this.isApplyForShareLoan = isApplyForShareLoan;
	}
	public String getHid_isFound() {
		return hid_isFound;
	}
	public void setHid_isFound(String hid_isFound) {
		this.hid_isFound = hid_isFound;
	}

	public String getIsApprovedSDO() {
		return isApprovedSDO;
	}
	public void setIsApprovedSDO(String isApprovedSDO) {
		this.isApprovedSDO = isApprovedSDO;
	}
	public String getIsApprovedBM() {
		return isApprovedBM;
	}
	public void setIsApprovedBM(String isApprovedBM) {
		this.isApprovedBM = isApprovedBM;
	}
	public String getIsApprovedDS() {
		return isApprovedDS;
	}
	public void setIsApprovedDS(String isApprovedDS) {
		this.isApprovedDS = isApprovedDS;
	}		
	public String getHid_MaxLoanAmt() {
		return hid_MaxLoanAmt;
	}
	public void setHid_MaxLoanAmt(String hid_MaxLoanAmt) {
		this.hid_MaxLoanAmt = hid_MaxLoanAmt;
	}
	public String getHid_MaxSamurdhiLoanAmt() {
		return hid_MaxSamurdhiLoanAmt;
	}
	public void setHid_MaxSamurdhiLoanAmt(String hid_MaxSamurdhiLoanAmt) {
		this.hid_MaxSamurdhiLoanAmt = hid_MaxSamurdhiLoanAmt;
	}
	public String getStatusMsg() {
		return statusMsg;
	}
	public void setStatusMsg(String statusMsg) {
		this.statusMsg = statusMsg;
	}
	public String getStatusMsgErr() {
		return statusMsgErr;
	}
	public void setStatusMsgErr(String statusMsgErr) {
		this.statusMsgErr = statusMsgErr;
	}
	public String getApplicationNo() {
		return applicationNo;
	}
	public void setApplicationNo(String applicationNo) {
		this.applicationNo = applicationNo;
	}
	public String getIdNo() {
		return idNo;
	}
	public void setIdNo(String idNo) {
		this.idNo = idNo;
	}
	public String getLoanAmount() {
		return loanAmount;
	}
	public void setLoanAmount(String loanAmount) {
		this.loanAmount = loanAmount;
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

	public String getTotInterest() {
		return totInterest;
	}
	public void setTotInterest(String totInterest) {
		this.totInterest = totInterest;
	}
	public String getApprovedBy() {
		return approvedBy;
	}
	public void setApprovedBy(String approvedBy) {
		this.approvedBy = approvedBy;
	}
	
	public String getInstallment() {
		return installment;
	}
	public void setInstallment(String installment) {
		this.installment = installment;
	}

	
	public String getMemberOfSamurdhi() {
		return memberOfSamurdhi;
	}
	public void setMemberOfSamurdhi(String memberOfSamurdhi) {
		this.memberOfSamurdhi = memberOfSamurdhi;
	}

	public String getNofInstallments() {
		return nofInstallments;
	}
	public void setNofInstallments(String nofInstallments) {
		this.nofInstallments = nofInstallments;
	}
	public String getLoggedInUserId() {
		return loggedInUserId;
	}
	public void setLoggedInUserId(String loggedInUserId) {
		this.loggedInUserId = loggedInUserId;
	}
	public String getCostCenterNo() {
		return costCenterNo;
	}
	public String getInterestrate() {
		return interestrate;
	}
	public void setInterestrate(String interestrate) {
		this.interestrate = interestrate;
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



	private static final Log log = LogFactory.getLog(LoanApp.class);
	private static String newPath="";

	
	public Map<String, Object> getSession() {
		return session;
	}
	public void setSession(Map<String, Object> session) {
		this.session = session;
	}
	public Map<String, String[]> getParameters() {
		return parameters;
	}
	public void setParameters(Map<String, String[]> parameters) {
		this.parameters = parameters;
	}
	public String getPath() {
		return path;
	}
	public void setPath(String path) {
		this.path = path;
	}	
	
	public String execute(){		
		try{			
			
			setLoggedData();
			setPath("Application>New Loan Application");
			hid_isNew = "new";
			interestrate = LoanDetails.InterestRate;
			Format format=new Format();
			String appNoPreFix = getSessionKey("costCenterNo")+"/"+"ENC"+"/"+format.getYear()+"/";
			applicationNo = appNoPreFix;
			//DW
			if (getSession().containsKey("applicationNo")){
				applicationNo = getSessionKey("applicationNo");	
				
			}else{
				costCenterNo = getSessionKey("costCenterNo");	
			}
			//DW
			nofInstallments = LoanDetails.LoanPeriod;
			hid_MaxLoanAmt = LoanDetails.MaxAmount;
			hid_MaxSamurdhiLoanAmt = LoanDetails.MasSamurdhiAmt;
			hid_NoShares =  LoanDetails.NO_OF_SHARES;
			hid_SharePrice =  LoanDetails.SHARE_PRICE;
			
        }  catch(Exception ex)	{
        		
        		return "error";
        	}
		return "success";
	}		
	
	public String modify(){		
		try{			
			setLoggedData();
			hid_isNew="";
			setPath("Application>Modify Loan Application");
			Format format=new Format();
			String appNoPreFix = getSessionKey("costCenterNo")+"/"+"ENC"+"/"+format.getYear()+"/";
			if(hid_isFound==null)
				applicationNo = appNoPreFix;
			else if( hid_isFound.length()<=0)
				applicationNo = appNoPreFix;
			interestrate = LoanDetails.InterestRate;
			nofInstallments = LoanDetails.LoanPeriod;
			hid_MaxLoanAmt = LoanDetails.MaxAmount;
			hid_MaxSamurdhiLoanAmt = LoanDetails.MasSamurdhiAmt;
			hid_NoShares =  LoanDetails.NO_OF_SHARES;
			hid_SharePrice =  LoanDetails.SHARE_PRICE;
			
        }  catch(Exception ex)	{
        	System.out.println("The error is......:"+ex);
        		return "error";
        	}
		return "success";
	}
 	public void setLoggedData() {       
        log.info(getSession());
        setLoggedInUserId(getSessionKey("userName"));
        setCostCenterNo(getSessionKey("costCenterNo"));
        setCsc(getSessionKey("costCenterName"));       
    }
 	
 	public String save(){
 		try{
 			
 			ApplicationEjb applicationEjb=new ApplicationEjb(getSessionKey("region"));
 			Application applctn = applicationEjb.findByApplicationNo(applicationNo, getSessionKey("costCenterNo"));
 			SpapplonEjb spLoanejb=new SpapplonEjb(getSessionKey("region")); 				
 			PivDetailEjb pivDetailEjb = new PivDetailEjb (getSessionKey("region")); 					
 			//List<PivDetail>   pivDetailList= pivDetailEjb.getPivDetailByRefNo(getSessionKey("costCenterNo"), applicationNo.toString());
 			PivDetail pivDetail=pivDetailEjb.findByReferenceNo(applicationNo, "EST");		 			
 			if(applctn==null){
 				statusMsgErr ="Error in updating - Application number does not exists";	
 	 			return "success";
 			}else if(applctn.getIsLoanApp()==null){
 				statusMsgErr ="Error in updating - Application has not applied for a loan";	
 	 			return "success";
 			}else if(applctn.getIsLoanApp()!=null && applctn.getIsLoanApp().equals("N")){
 				statusMsgErr ="Error in updating - Application has not applied for a loan";	
 	 			return "success";
 			}if(pivDetail!=null){
 				statusMsgErr ="Error in updating - You have issued a PIV for the estimate";					
				return "success";			
 			}else if(hid_isNew.equals("new")||hid_isNew.length()>0){
 				SpapplonPK id=new SpapplonPK(); 			
 	 			id.setApplicationNo(applicationNo.trim()); 			
 	 			id.setDeptId(getSessionKey("costCenterNo").trim()); 	 			
 	 			Spapplon spapplon=spLoanejb.findById(id);
 	 			
 	 			if(spapplon!=null){
 	 				statusMsgErr ="Error in Saving - Already saved - Please go to modify page to change";	
 	 	 			return "success";
 	 			}
 	 			saveData();
 	 			return "success";
 			}else{
 				
 				saveData();
 				modify();
 				return "success";
 			}
 			
 			
 		
 		}catch(Exception ex){
 			statusMsg = "";
			statusMsgErr ="Error in updating";	
 			return "success";
 		}
 	}
 	
 	private void saveData(){
 			SpapplonEjb spLoanejb=new SpapplonEjb(getSessionKey("region"));
 			Format format=new Format();
			SpapplonPK id=new SpapplonPK(); 			
			id.setApplicationNo(applicationNo);
			id.setDeptId(getSessionKey("costCenterNo"));
			Spapplon spapplon=new Spapplon();  			
			spapplon.setId(id);
			spapplon.setAddDate(new Date());
			spapplon.setAddTime(format.FormatTime());
			if(approvedBy!=null && approvedBy.length()>0)
				spapplon.setAddUser(approvedBy); 			
			
			if(interestrate!=null && interestrate.length()>0)
				spapplon.setInterestRate(new BigDecimal(interestrate));
			if(nofInstallments!=null && nofInstallments.length()>0)
				spapplon.setYears(new BigDecimal(nofInstallments));	
			String samurdhiStatus = memberOfSamurdhi;			
			String samurdhiOfficerStatus = isApprovedSDO;
			String samurdhiBankManagerStatus = isApprovedBM;
			String samurdhiDivisionalSecStatus = isApprovedDS;
			String samurdhiisApplyForShareLoan = isApplyForShareLoan;
			if(samurdhiStatus.equals("N")){				
				if(noOfShares!=null && noOfShares.length()>0)
					spapplon.setNoOfShares(new BigDecimal(noOfShares));
				else
					spapplon.setNoOfShares(new BigDecimal(0));
				
				if(sharePrice!=null && sharePrice.length()>0)
					spapplon.setSamurdhiSharePrice(new BigDecimal(sharePrice));	
				else
					spapplon.setSamurdhiSharePrice(new BigDecimal(0));	
			}else
			{
				spapplon.setSamurdhiId(samurdhiId);
				spapplon.setNoOfShares(new BigDecimal(0));
				spapplon.setSamurdhiSharePrice(new BigDecimal(0));	
			}
				
			if(sharePrice!=samurdhiStatus)
				spapplon.setMemberOfSamurdhi(samurdhiStatus);
			if(sharePrice!=samurdhiDivisionalSecStatus)
				spapplon.setApprovalDs(samurdhiDivisionalSecStatus);
			if(sharePrice!=samurdhiBankManagerStatus)
				spapplon.setApprovalSbm(samurdhiBankManagerStatus);
			if(sharePrice!=samurdhiOfficerStatus)
				spapplon.setApprovalSdo(samurdhiOfficerStatus);
			if(sharePrice!=samurdhiisApplyForShareLoan)
				spapplon.setIsLoan4Share(samurdhiisApplyForShareLoan);
								
			spLoanejb.updateSpapplon(spapplon);
			resetFields();
			setLoggedData();
			execute();
			statusMsg = "Details updated correctly";
			statusMsgErr ="";			
			
 	}
 	
 	public String find(){
 		try{
 			setLoggedData();
 			SpapplonEjb spLoanejb=new SpapplonEjb(getSessionKey("region"));
 			SpapplonPK id=new SpapplonPK(); 			
 			id.setApplicationNo(applicationNo); 			
 			id.setDeptId(getSessionKey("costCenterNo"));
 			
 			Spapplon spapplon=spLoanejb.findById(id);
 			if(spapplon!=null){
 				hid_isFound = "found";
 				if(spapplon.getApprovalDs()!=null)
 					isApprovedDS = spapplon.getApprovalDs();
 				if(spapplon.getApprovalSbm()!=null)
 					isApprovedBM = spapplon.getApprovalSbm();
 				if(spapplon.getApprovalSdo()!=null)
 					isApprovedSDO = spapplon.getApprovalSdo();
 				if(spapplon.getLoanAmount()!=null)
 					loanAmount = spapplon.getLoanAmount().toString();
 				if(spapplon.getIsLoan4Share()!=null)
 					isApplyForShareLoan = spapplon.getIsLoan4Share();
 				if(spapplon.getMemberOfSamurdhi()!=null)
 					memberOfSamurdhi = spapplon.getMemberOfSamurdhi(); 				
 			 				
 				
 				
 				if(memberOfSamurdhi.equals("Y")){ 	 					
 					if(spapplon.getSamurdhiId()!=null)
 						samurdhiId = spapplon.getSamurdhiId();
 					if(spapplon.getSamurdhiSharePrice()!=null)
 						sharePrice = spapplon.getSamurdhiSharePrice().toString();
 					if(spapplon.getNoOfShares()!=null)
 	 					noOfShares = spapplon.getNoOfShares().toString();
 				} 	
 				if(spapplon.getInterestRate()!=null)
 					interestrate = spapplon.getInterestRate().toString();
 	 			if(spapplon.getInstallment()!=null)
 	 				nofInstallments =spapplon.getYears().toString();
 				
 				if(spapplon.getInstallment()!=null)
 					installment = spapplon.getInstallment().toString();
 				if(spapplon.getTotalInterest()!=null)
 					totInterest = spapplon.getTotalInterest().toString();
 				if(spapplon.getAddUser()!=null)
 					approvedBy = spapplon.getAddUser();
 				if(spapplon.getLoanAmount()!=null)
 					loanAmount = spapplon.getLoanAmount().toString();
 				
 			}else{
 				hid_isFound ="";
 				resetFields();
 				statusMsg = "No records found";
 			}
 			
 		}catch(Exception ex){
 			
 		}
 		modify();
 		return "success";
 	}

	
	public String getSessionKey(String key) {
        return getSession().get(key).toString();
    }
	
	public String close(){
		return "closed";
	}
	
	public void resetFields(){
		 interestrate = "";
		 nofInstallments= "";	
		 applicationNo= "";
		 idNo= "";
		 loanAmount= "";
		 samurdhiId= "";
		 sharePrice= "";
		 noOfShares= "";
		 installment= "";		  
		 hid_isFound= "";
		 isApprovedBM= "";
		 isApprovedDS= "";
		 isApprovedSDO= "";
		 totInterest= "";
		 approvedBy= "";
		 memberOfSamurdhi= "";
	}
	
	public void calculateLoanAmounts(double loanAmount, double noOfInstallments,double interestrate){
									
		double monthlyIns =loanAmount/noOfInstallments;	
		double tempterest = (loanAmount/24)*(interestrate/100);				
		double monthlyInterest = (tempterest*((noOfInstallments)+1))/(noOfInstallments);				
		double totPayment = (monthlyIns + monthlyInterest)*(noOfInstallments)-loanAmount;				
		double monthlyInstallment = monthlyIns + monthlyInterest;
							
		
	}
}
