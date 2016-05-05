package job.web;
import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Map;
import java.util.StringTokenizer;
import java.text.NumberFormat;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.apache.struts2.interceptor.ParameterAware;
import org.apache.struts2.interceptor.SessionAware;

import util.common.ApplicationSubType;
import util.common.ApplicationType;
import util.common.Format;
import util.common.Path;
import application.model.Application;
import application.model.Spapplon;
import application.model.SpapplonPK;
import application.model.WiringLandDetail;
import application.model.WiringLandDetailPK;
import application.service.ApplicantEjb;
import application.service.ApplicationEjb;
import application.service.SpapplonEjb;
import application.service.WiringLandDetailEjb;
import application.model.Applicant;
import estimate.model.Speststd;
import estimate.model.SpeststdPK;
import estimate.model.Spserest;
import estimate.model.SpserestPK;
import estimate.service.SpeststdEjb;
import estimate.service.SpserestEjb;
import export.model.BillUpdateData;
import export.model.MisSmcMaster;
import export.model.MisSmcMasterPK;
import export.service.ExportBillEjb;
import com.opensymphony.xwork2.ActionSupport;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import job.model.Pcesthmt;
import job.model.Sparemap;
import job.model.Spexphst;
import job.model.Spexpjob;
import job.model.SpexpjobPK;
import job.model.Spodrcrd;
import job.model.SpodrcrdPK;
import job.service.ExportEjb;
import job.service.JobEjb;
import job.service.PcesthmtEjb;
import job.service.SpodrcrdEjb;
import org.apache.struts2.interceptor.ServletRequestAware;
import org.apache.struts2.interceptor.ServletResponseAware;
import piv.model.PivDetail;
import piv.service.PivDetailEjb;

 

@SuppressWarnings("serial")
public class SendToBill extends ActionSupport implements SessionAware, ParameterAware,ServletResponseAware,ServletRequestAware{
	private static final Log log = LogFactory.getLog(SendToBill.class);
	private static final String viewPath="Job>Send To Billing";
	
	NumberFormat nf = NumberFormat.getInstance();
    
    
	private HttpServletRequest request;
	private HttpServletResponse response;
	  
	private Map <String, String[]> parameters;
	private String loggedInUserId;
	
	//Error
	private static final String MSG_ERROR="ERROR";
	private static final String MSG_INFO="INFO";
	private static final String MSG_DONE="DONE";
	private static final String MSG_NONE="NONE";
	private String errorMessage;
	private String messageType=MSG_NONE;
	
	

	//session variables
	private Map<String, Object> sessionMap;
	String loggedUser;

	//JSP Fields
	private String costCenterNo;
	private String costCenterName;
	
	//other Fields
	private String path;
	private String state;
	
		//private String fileName1;
	
	//JSP Variables
	private String[] selectedProjectNoList;
	
	

	private List jobList;
	private String[] jobNoList;	
	private int noOfJobs;
	private String[] jobNumberList;
	
	public String[] getJobNumberList() {
		return jobNumberList;
	}

	public void setJobNumberList(String[] jobNumberList) {
		this.jobNumberList = jobNumberList;
	}

	public String execute(){
		System.out.println("###########1");
		//setErrorMessage(null);
		try{
			System.out.println("###########2");
			setLoggedData();
			setPath(viewPath);
			setJobData();
		
		}catch(Exception e){
			System.out.println("###########3");
		}
    	return SUCCESS;
	}
	

	
	//action invoke when Close button is clicked
	public String close() {
		setLoggedData();
		return "close";
	}
	

	private void setJobData () throws Exception
	{
		JobEjb jobEjb = new JobEjb(getSessionKey("region"));
		String jobType=null;
		if (getSessionKey("smcType").equals("NC")){
			jobType="SMC";
		}else if (getSessionKey("smcType").equals("CR")){
			jobType="CRJ";
		}else {
			jobType="XXX";
		}
		System.out.println("*************** "+ costCenterNo);
		jobList = jobEjb.getFinishedJobsForBilling(costCenterNo, jobType );
		//System.out.println("*************** "+ jobType+" "+jobList);
		if(jobList!=null){
    		if(jobList.size()>0)
    			noOfJobs = jobList.size();
    		else 
    			jobList = null;
    	}
	}
	
	
	
	public String export(){
		try{
			
			Format format = new Format();
			List<MisSmcMaster> expMisSmcMasterJobLst = new ArrayList<MisSmcMaster>();
			List<Spexpjob> expSpexpjobJobLst = new ArrayList<Spexpjob>();
			noOfJobs = selectedProjectNoList.length;
			StringTokenizer st = new StringTokenizer(costCenterNo,".");
			String smcAreaCode = st.nextToken();
			String subStation = st.nextToken(smcAreaCode+".");
			ExportEjb exportEjb = new ExportEjb(getSessionKey("region"));
			ExportBillEjb  exportBillEjb=new ExportBillEjb();
			System.out.println(selectedProjectNoList);
			System.out.println(selectedProjectNoList.length);
			System.out.println(selectedProjectNoList);
			for(int i=0;i<selectedProjectNoList.length;i++){
				System.out.println(selectedProjectNoList[i]);
			}
			int k=0;
			if (selectedProjectNoList.length >= 5){
				k=5;
				
			}else{
				k=selectedProjectNoList.length;
			}
			//for(int i=0;i<jobNoList.length;i++)
			for(int i=0;i<k;i++){

				//String selectedJobNo = jobNoList[i].trim();
				String selectedJobNo = selectedProjectNoList[i].trim();
				//job details
				PcesthmtEjb pcesthmtEjb = new PcesthmtEjb(getSessionKey("region"));
				Pcesthmt pcesthmt = pcesthmtEjb.findByJobNo(selectedJobNo, costCenterNo);
				
				//order card details
				SpodrcrdEjb spodrcrdEjb = new SpodrcrdEjb(getSessionKey("region"));
				SpodrcrdPK spodrcrdPK = new SpodrcrdPK();
				spodrcrdPK.setDeptId(costCenterNo);
				spodrcrdPK.setProjectNo(selectedJobNo);
				Spodrcrd spodrcrd = spodrcrdEjb.findById(spodrcrdPK);
				
				String estimateNo = pcesthmt.getId().getEstimateNo();
								
				//Estimate details
				SpeststdPK speststdPK = new SpeststdPK();
				speststdPK.setDeptId(costCenterNo);
				speststdPK.setEstimateNo(estimateNo);
				
				SpeststdEjb speststdEJB = new SpeststdEjb(getSessionKey("region"));
				Speststd speststd = speststdEJB.findById(speststdPK);
				
				
				
						
				//application details
				ApplicationEjb applicationEjb = new ApplicationEjb(getSessionKey("region"));
				Application application = applicationEjb.findByApplicationNo(estimateNo);
				
				//DW
				//PIV details
				PivDetailEjb pivDetailEjb = new PivDetailEjb(getSessionKey("region"));
				PivDetail pivDetail = null;
				if(application.getIsLoanApp().equals("Y")){
					pivDetail = pivDetailEjb.findByReferenceNo(costCenterNo,estimateNo, "ELN");
				}else{
					pivDetail = pivDetailEjb.findByReferenceNo(costCenterNo,estimateNo, "EST");
				}
				
				
				//wiring details
				WiringLandDetailEjb wlEJB = new WiringLandDetailEjb(getSessionKey("region"));
				WiringLandDetailPK wlPK = new WiringLandDetailPK();
				wlPK.setApplicationId(application.getId().getApplicationId());
				wlPK.setDeptId(costCenterNo);
				WiringLandDetail wld = wlEJB.findByAppId(wlPK);
				
				//applicant details
				ApplicantEjb applicantEJB = new ApplicantEjb(getSessionKey("region"));
				Applicant applicant = applicantEJB.findById(application.getIdNo());
				
				//loan details
				SpapplonEjb loanEjb=new SpapplonEjb(getSessionKey("region"));
				SpapplonPK spapplonPK=new SpapplonPK();
				spapplonPK.setDeptId(costCenterNo);
				spapplonPK.setApplicationNo(applicant.getIdNo());				
				Spapplon spappLoan = loanEjb.findById(spapplonPK);		
				MisSmcMaster misSmcMaster = new MisSmcMaster();							
				Sparemap sparemap = exportEjb.findBySmcAreaCode(smcAreaCode);
				
							
				MisSmcMasterPK misSmcMasterPK=new MisSmcMasterPK();
				//misSmcMasterPK.setSmcType(ApplicationType.getAppTypeForBilling(application.getApplicationType()));
				misSmcMasterPK.setSmcType(ApplicationSubType.getAppTypeForBilling(application.getApplicationSubType()));
				if(sparemap!=null)
					misSmcMasterPK.setAreaCode(paddingWithSpace(sparemap.getBillAreaCode(),2));
				
				SpserestEjb restEjb=new SpserestEjb(getSessionKey("region"));
				SpserestPK spserestPK=new SpserestPK();
				spserestPK.setApplicationNo(pcesthmt.getId().getEstimateNo());
				spserestPK.setDeptId(costCenterNo);			
				Spserest spserest = restEjb.findById(spserestPK);
				
				if(spserest!=null && spserest.getSin()!=null){
					if(spserest.getSin().length()>=4)
					{				
						misSmcMasterPK.setCrntDepot(spserest.getSin().substring(0,1).toUpperCase());
					}	
					
				}
		
				String[] selectedJobArray=selectedJobNo.split("/");

				if(selectedJobArray!=null && selectedJobArray.length>2){
					misSmcMasterPK.setYearInstld(selectedJobArray[2]);
					misSmcMasterPK.setJobSerl(new Long(selectedJobArray[3]));
				}
				System.out.println("misSmcMasterPK "+misSmcMasterPK);						
				misSmcMaster.setId(misSmcMasterPK);
				if(applicant!=null && applicant.getLastName()!=null)
					misSmcMaster.setCustLname(paddingWithSpace(applicant.getLastName(),15).toUpperCase());
				if(applicant!=null && applicant.getFirstName()!=null)
					misSmcMaster.setCustFname(paddingWithSpace(applicant.getFirstName(),15).toUpperCase());
				if(applicant!=null && applicant.getStreetAddress()!=null)
					misSmcMaster.setAddress1(paddingWithSpace(applicant.getStreetAddress(),30));
				if(applicant!=null && applicant.getSuburb()!=null)
					misSmcMaster.setAddress2(paddingWithSpace(applicant.getSuburb(),25));
				if(applicant!=null && applicant.getCity()!=null)
					misSmcMaster.setAddress3(paddingWithSpace(applicant.getCity(),20));
				
				if(pivDetail!=null){
					misSmcMaster.setPmntMode(pivDetail.getPaymentMode());
					misSmcMaster.setPmntDate(pivDetail.getPaidDate());
					misSmcMaster.setPivNo(pivDetail.getPivReceiptNo());
					if(pivDetail.getPivSeqNo()!=null)
						misSmcMaster.setPivNoComgen(pivDetail.getId().getPivNo());
				}
				
				if( wld!=null){
					if(wld.getTariffCode()!=null)
						misSmcMaster.setTariffCode(new BigDecimal(wld.getTariffCode()));					
					if(wld.getPhase()!=null)
						misSmcMaster.setNoOfPhase(wld.getPhase());
					if(wld.getConnectionType()!=null)
						misSmcMaster.setConectType(wld.getConnectionType());					
					if(wld.getCustomerType()!=null)
						misSmcMaster.setCustType(wld.getCustomerType().substring(0, 2));
					if(wld.getCustomerType()!=null)
						misSmcMaster.setCustomerType(wld.getCustomerType());
					if(wld.getCustomerCategory()!=null)
						misSmcMaster.setCustomerCategory(wld.getCustomerCategory());	
					if (application.getApplicationType().equals("NC")){
						if(wld.getNeighboursAccNo()!=null)
							misSmcMaster.setNbrAcct(wld.getNeighboursAccNo());	
					}else if (application.getApplicationType().equals("CR")){
						if(wld.getExistingAccNo()!=null)
							misSmcMaster.setAcctNumber(wld.getExistingAccNo());	
					}
				}							
								
				if(spodrcrd!=null && spodrcrd.getKvaRating()!=null)
					misSmcMaster.setKvaRating(spodrcrd.getKvaRating());				
								
				if(speststd!=null){
					if(speststd.getSecurityDeposit()!=null)
						misSmcMaster.setSecDeposit(speststd.getSecurityDeposit());
					if(speststd.getTotalCost()!=null)
						misSmcMaster.setSmCharge(speststd.getFixedCost());									
				}
				
				misSmcMaster.setTblSerl(new BigDecimal(0));
				misSmcMaster.setDeptId(costCenterNo);
				misSmcMaster.setProjectNo(selectedJobNo);
				misSmcMaster.setSubstnCode(paddingWithZero(subStation, 3));
				if(spodrcrd!=null){
					if(spodrcrd.getPackNo()!=null){
						misSmcMaster.setDlyPackNo(paddingWithZero(spodrcrd.getPackNo().trim(),2));
						System.out.println("The pack code is....:"+misSmcMaster.getDlyPackNo());
					}
						
					else
						misSmcMaster.setDlyPackNo("00");
					if(spodrcrd.getReaderCode()!=null)
						misSmcMaster.setReaderCode(paddingWithZero(spodrcrd.getReaderCode().trim(),2));
					else
						misSmcMaster.setReaderCode("00");
					if(spodrcrd.getWalkSeq()!=null)
						misSmcMaster.setWalkSeq(paddingWithZero(spodrcrd.getWalkSeq(),5));
					else
						misSmcMaster.setWalkSeq("00000");
					if(spodrcrd.getNoOfMeters()!=null)
						misSmcMaster.setNoOfMetrs(spodrcrd.getNoOfMeters());				
					if(spodrcrd.getMeterNo1()!=null)
						misSmcMaster.setMetNo1(paddingWithZero(spodrcrd.getMeterNo1(),8));
					if(spodrcrd.getMeterType1()!=null)
						misSmcMaster.setMetType1(spodrcrd.getMeterType1());
					if(spodrcrd.getNoOfDigits1()!=null)
						misSmcMaster.setNoOfDig1(spodrcrd.getNoOfDigits1());
					if(spodrcrd.getInitReading1()!=null)
						misSmcMaster.setInitRdng1(spodrcrd.getInitReading1());
					if(spodrcrd.getMeterNo2()!=null)
						misSmcMaster.setMetNo2(spodrcrd.getMeterNo2());
					if(spodrcrd.getMeterType2()!=null)
						misSmcMaster.setMetType2(spodrcrd.getMeterType2());
					if(spodrcrd.getNoOfDigits2()!=null)
						misSmcMaster.setNoOfDig2(spodrcrd.getNoOfDigits2());
					if(spodrcrd.getInitReading2()!=null)
						misSmcMaster.setInitRdng2(spodrcrd.getInitReading2());
					if(spodrcrd.getMeterNo3()!=null)
						misSmcMaster.setMetNo3(spodrcrd.getMeterNo3());
					if(spodrcrd.getMeterType3()!=null)
						misSmcMaster.setMetType3(spodrcrd.getMeterType3());
					if(spodrcrd.getNoOfDigits3()!=null)
						misSmcMaster.setNoOfDig3(spodrcrd.getNoOfDigits3());
					if(spodrcrd.getInitReading3()!=null)
						misSmcMaster.setInitRdng3(spodrcrd.getInitReading3());
					if(spodrcrd.getAvgConsump()!=null)
						misSmcMaster.setAvgConsmn(spodrcrd.getAvgConsump());
					else
						misSmcMaster.setAvgConsmn(new BigDecimal(0));
					if(spodrcrd.getOldAccNo()!=null)
						misSmcMaster.setAcctNumber(paddingWithSpace(spodrcrd.getOldAccNo(),10));
					if(spodrcrd.getConnectedDate()!=null)
						misSmcMaster.setInitRdDt(spodrcrd.getConnectedDate());
					else
						misSmcMaster.setInitRdDt (new Date());
				}							
				
							
				misSmcMaster.setAdddate(new Date());
				misSmcMaster.setUpdtFlag("W");
				if(applicant!=null && applicant.getIdNo()!=null)
					misSmcMaster.setNicNo(paddingWithSpace(applicant.getIdNo(),10));
			
				if(applicant!=null && applicant.getTelephoneNo()!=null)
					misSmcMaster.setTelphn1(paddingWithSpace(applicant.getTelephoneNo().toString(),10));
				if(applicant!=null && applicant.getEmail()!=null)
					misSmcMaster.setEmail(paddingWithSpace(applicant.getEmail(),30));
						
				if(spappLoan!=null){				
					misSmcMaster.setLoanAmt(spappLoan.getLoanAmount());
					misSmcMaster.setLoanDate(spappLoan.getAddDate());
					misSmcMaster.setInstmntAmt(spappLoan.getInstallment());				
					misSmcMaster.setInstsTopay(spappLoan.getTotalInterest());
					misSmcMaster.setLoanCode(spappLoan.getLoanReference());

				}else{
					misSmcMaster.setLoanAmt(new BigDecimal(0));
					misSmcMaster.setLoanDate(null);
					misSmcMaster.setInstmntAmt(new BigDecimal(0));				
					misSmcMaster.setInstsTopay(new BigDecimal(0));
					misSmcMaster.setLoanCode(null);
				}
				System.err.println(misSmcMaster);
				expMisSmcMasterJobLst.add(misSmcMaster);				
				SpexpjobPK spexpjobPK = new SpexpjobPK();
				spexpjobPK.setDeptId(costCenterNo);
				spexpjobPK.setProjectNo(selectedJobNo);
				
				Spexpjob spexpjob = new Spexpjob();
				spexpjob.setExportedDate(new Date());
				spexpjob.setExportedTime(format.FormatTime());
				spexpjob.setIsTransferred("1");
				spexpjob.setId(spexpjobPK);
				spexpjob.setSmcType(misSmcMaster.getId().getSmcType());
				expSpexpjobJobLst.add(spexpjob);	
				
			}	
			
			Spexphst spexphst = new Spexphst();
			spexphst.setAcknowledge("0");
			spexphst.setDeptId(costCenterNo);
			spexphst.setExportedDate(new Date());
			spexphst.setExportedTime(format.FormatTime());
			spexphst.setFilePath(Path.getBillExportPath());
			spexphst.setNoOfJobs(new BigDecimal(noOfJobs));		
			spexphst.setFileName("TestFile");
			spexphst.setFileSize(new BigDecimal(10));				
			//List<BillUpdateData> list=exportBillEjb.getBillUpdateData(costCenterNo, "U");
			//for(int i=0; i<=list.size()-1; i++){
			//	list.get(i).getAcctNumber()
				//pcestdttDaoRemote.createPcestdtt(list.get(i), webAppName);
			//}
			//setErrorMessage("s;cmsdlkmckmc");
			//System.out.println("@@@@@@@@@@@@@@@@@@@@@@@@@@ "+expMisSmcMasterJobLst);
			//System.out.println("@@@@@@@@@@@@@@@@@@@@@@@@@@ "+expMisSmcMasterJobLst.size());
			exportBillEjb.export(expMisSmcMasterJobLst);			
			exportEjb.insertExportData(spexphst, expSpexpjobJobLst);
		
			setLoggedData();
			execute();
			return SUCCESS;
			
				
		}catch(Exception ex){
			//ex.printStackTrace();
			System.out.println("ERROR MESSEGE" + ex.getMessage());
			setMessageType(MSG_ERROR);
			setErrorMessage(ex.getMessage());
			setLoggedData();
			execute();
			return ERROR;
		
		}
		//setLoggedData();
		//execute();
		//return SUCCESS;
	}
	

	
	
	public void setLoggedData() 
	{
		log.info( getSession());
		setLoggedInUserId(getSessionKey("userName"));
		setCostCenterNo(getSessionKey("costCenterNo"));
		setCostCenterName(getSessionKey("costCenterName"));
		setLoggedInUserLevel( getSessionKey("userRole"));
	}
	
	private String paddingWithSpace(String word, int maxLen)
	{
		int wordLen = word.length();
		if(wordLen<maxLen)
		{
			for(int i=wordLen;i<maxLen;i++)
			{
				word += " ";
			}
		}
		else
		{
			word = word.substring(0,maxLen);
		}
		return word;
	}
	
	private String paddingWithZero(String word, int maxLen)
	{
		int wordLen = word.length();
		if(wordLen<maxLen)
		{
			String preZeros = "";
			for(int i=wordLen;i<maxLen;i++)
			{
				preZeros += "0";
			}
			word = preZeros+word;
		}
		return word;
	}
	
	
	/************* Getters and Setters ***************/
	
	public String getSessionKey(String key) 
	{
       return getSession().get(key).toString();
	}

	public String getLoggedInUserId() {
		return loggedInUserId;
	}

	public void setLoggedInUserId(String loggedInUserId) {
		this.loggedInUserId = loggedInUserId;
	}

	public String getPath() {
		return path;
	}

	public void setPath(String path) {
		this.path = path;
	}
	
	public String getCostCenterNo() {
		return costCenterNo;
	}
	public void setCostCenterNo(String costCenterNo) {
		this.costCenterNo = costCenterNo;
	}
	public String getCostCenterName() {
		return costCenterName;
	}
	public void setCostCenterName(String costCenterName) {
		this.costCenterName = costCenterName;
	}
	public String getState() {
		return state;
	}
	public void setState(String state) {
		this.state = state;
	}
	
	public String getErrorMessage() {
		return errorMessage;
	}
	public void setErrorMessage(String errorMessage) {
		this.errorMessage = errorMessage;
	}
	
	public Map<String, Object> getSession() {
		return sessionMap;
	}
	
	@Override
	public void setSession(Map<String, Object> sessionMap) {
		this.sessionMap=sessionMap;
		
	}

	public Map<String, String[]> getParameters() {
		return parameters;
	}
	
	@Override
	public void setParameters(Map<String, String[]> parameters) {
		this.parameters=parameters;
		
	}
	
	public void setServletRequest(HttpServletRequest request){
		this.request = request;
	}

	public HttpServletRequest getServletRequest(){
		return request;
	}

	public void setServletResponse(HttpServletResponse response){
		this.response = response;
	}

	public HttpServletResponse getServletResponse(){
		return response;
	}

	public List getJobList() {
		return jobList;
	}

	public void setJobList(List jobList) {
		this.jobList = jobList;
	}
	
	public String[] getJobNoList() {
		return jobNoList;
	}

	public void setJobNoList(String[] jobNoList) {
		this.jobNoList = jobNoList;
	}
	
	private String loggedInUserLevel;
	public String getLoggedInUserLevel() {
		return loggedInUserLevel;
	}

	public void setLoggedInUserLevel(String loggedInUserLevel) {
		this.loggedInUserLevel = loggedInUserLevel;
	}
	
	public String getMessageType() {
		return messageType;
	}

	public void setMessageType(String messageType) {
		this.messageType = messageType;
	}
	public String[] getSelectedProjectNoList() {
		return selectedProjectNoList;
	}

	public void setSelectedProjectNoList(String[] selectedProjectNoList) {
		this.selectedProjectNoList = selectedProjectNoList;
	}
	
	
}//End of class
