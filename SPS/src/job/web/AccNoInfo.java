package job.web;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import java.text.NumberFormat;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.apache.struts2.interceptor.ParameterAware;
import org.apache.struts2.interceptor.SessionAware;


import export.model.BillUpdateData;

import export.service.ExportBillEjb;
import com.opensymphony.xwork2.ActionSupport;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
/*import job.model.Pcesthmt;
import job.model.Sparemap;
import job.model.Spexphst;
import job.model.Spexpjob;
import job.model.SpexpjobPK;
import job.model.Spodrcrd;
import job.model.SpodrcrdPK;
import job.service.JobEjb;
import job.service.PcesthmtEjb;
import job.service.SpodrcrdEjb;
import piv.model.PivDetail;
import piv.service.PivDetailEjb;
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
import export.model.MisSmcMaster;
import export.model.MisSmcMasterPK;
import java.util.Date;
import java.math.BigDecimal;
import java.util.StringTokenizer;
*/
import job.service.ExportEjb;
import org.apache.struts2.interceptor.ServletRequestAware;
import org.apache.struts2.interceptor.ServletResponseAware;


 

@SuppressWarnings("serial")
public class AccNoInfo extends ActionSupport implements SessionAware, ParameterAware,ServletResponseAware,ServletRequestAware{
	private static final Log log = LogFactory.getLog(AccNoInfo.class);
	private static final String viewPath="Job>Account Info";
	
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
	private String region;
	private List<BillUpdateData> errorList;
	private List<BillUpdateData> billUpdatedList;
	private List<BillUpdateData> accNoList;
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
			//setJobData();
		
		}catch(Exception e){
			System.out.println("###########3");
		}
    	return SUCCESS;
	}
	
	public String  accNoInfoDirect(){
		try{
			//System.out.println("###########2");
			setLoggedData();
			setPath(viewPath);
			setAccNoCreatedDetail();
			return SUCCESS;
		
		}catch(Exception e){
			//System.out.println("###########3");
			e.printStackTrace();
			return ERROR;
		}
	}
	

	
	//action invoke when Close button is clicked
	public String close() {
		setLoggedData();
		return "close";
	}
	
	private void  setAccNoCreatedDetail() {
		ExportEjb exportEjb=new ExportEjb(region);
		ExportBillEjb billEjb=new ExportBillEjb();
		List<String> jobNoList=exportEjb.getExportedJobWithoutAccNo(costCenterNo);
		System.out.println(jobNoList);
		System.out.println(jobNoList.size());
		System.out.println(jobNoList.isEmpty());
		if(!jobNoList.isEmpty()){
			billUpdatedList =billEjb.getAccountNos(costCenterNo, jobNoList, "U");
			System.out.println(billUpdatedList);
			System.out.println(billUpdatedList.size());
			exportEjb.updateExportedJob(billUpdatedList);
		}
		
		accNoList=exportEjb.getAccNoInfo(costCenterNo);
		System.out.println(accNoList+"  "+accNoList);
	}
	

	/*private void setJobData () throws Exception{
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
	}*/
	
	public String toSendAgain(){
		try{
			//System.out.println("###########2");
			setLoggedData();
			setPath(viewPath);
			updateErrorData();
			setAccNoCreatedDetail();
			return SUCCESS;
		
		}catch(Exception e){
			//System.out.println("###########3");
			e.printStackTrace();
			setAccNoCreatedDetail();
			return ERROR;
		}
	}
	
	public void updateErrorData(){
		List<String> List3=new ArrayList<String>();
		ExportEjb exportEjb=new ExportEjb(region);
		ExportBillEjb billEjb=new ExportBillEjb();
		errorList = billEjb.getErrorInfo(costCenterNo, "S");
		System.out.println(errorList.size());
		List<BillUpdateData> selectedErrorList =new ArrayList<BillUpdateData>();
		for(int i=0;i<selectedProjectNoList.length;i++){
			System.out.println(selectedProjectNoList[i]);
		}
		for(int i=0;i<selectedProjectNoList.length;i++){
			//System.out.println(selectedProjectNoList[i]);
			for(int j=0;j<=errorList.size()-1;j++){
				System.out.println(selectedProjectNoList[i]+" "+errorList.get(j).getProjectNo()+" "+errorList.get(j).getProjectNo().toString().trim().equals(selectedProjectNoList[i].toString().trim()));
				if (errorList.get(j).getProjectNo().toString().trim().equals(selectedProjectNoList[i].toString().trim())){
					selectedErrorList.add(errorList.get(j));
					//return;
				}
				    
			}
		}
		
		//int k=0;
		//if (selectedProjectNoList.length >= 5){
		//	k=5;
			
		//}else{
		//	k=selectedProjectNoList.length;
		//}
		
		//for(int i=0;i<k;i++){
			//String selectedJobNo = selectedProjectNoList[i].trim();
			System.out.println("selectedErrorList "+selectedErrorList);	
			if(!selectedErrorList.isEmpty()){
				List3=exportEjb.updateErrorData(costCenterNo, selectedErrorList); 
			}
			if(!List3.isEmpty()){
				//MisSmcMaster 
				System.out.println(List3);
				billEjb.removeByjobNo(List3);
			}
		//}
	}
	
	
	

	
	
	public void setLoggedData() 
	{
		log.info( getSession());
		setLoggedInUserId(getSessionKey("userName"));
		setCostCenterNo(getSessionKey("costCenterNo"));
		setCostCenterName(getSessionKey("costCenterName"));
		setLoggedInUserLevel( getSessionKey("userRole"));
		setRegion(getSessionKey("region"));
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
	
	public String getRegion() {
		return region;
	}

	public void setRegion(String region) {
		this.region = region;
	}

	

	public List<BillUpdateData> getErrorList() {
		return errorList;
	}

	public void setErrorList(List<BillUpdateData> errorList) {
		this.errorList = errorList;
	}
	
	public List<BillUpdateData> getBillUpdatedList() {
		return billUpdatedList;
	}

	public void setBillUpdatedList(List<BillUpdateData> billUpdatedList) {
		this.billUpdatedList = billUpdatedList;
	}
	
	public List<BillUpdateData> getAccNoList() {
		return accNoList;
	}

	public void setAccNoList(List<BillUpdateData> accNoList) {
		accNoList = accNoList;
	}
	
}//End of class
